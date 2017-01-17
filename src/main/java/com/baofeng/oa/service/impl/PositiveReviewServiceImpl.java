package com.baofeng.oa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.Operator;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.PositiveReviewBean;
import com.baofeng.oa.bean.PositiveReviewPassBean;
import com.baofeng.oa.dao.PositiveReviewDao;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.oa.entity.PositiveReview;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IPositiveReviewService;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

@Service("positiveReviewService")
public class PositiveReviewServiceImpl implements IPositiveReviewService {
	
	@Autowired
	private PositiveReviewDao positiveReviewDao;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private IMonitorLogService monitorLogService;
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult readPagesActores(int pageSize, int curPage, String sortName, String sortOrder, List<RoleDetailsAtts> platList, Integer type, SearchFilter searchFilter) {
		NPageResult page = this.positiveReviewDao.readPagesActores(pageSize, curPage, sortName, sortOrder, platList, type, searchFilter);
		if (page != null && page.getData() != null && page.getData().size() > 0) {
			List<String> listPlatId = new ArrayList<String>();
			if (platList != null && platList.size() > 0) {
				for (RoleDetailsAtts atts : platList) {
					listPlatId.add(atts.getOpkey());
				}
			}
			Map<Integer, PositiveReviewBean> mapOn = new HashMap<Integer, PositiveReviewBean>();
			Map<Integer, PositiveReviewBean> mapOf = new HashMap<Integer, PositiveReviewBean>();
			BranchOffice branchOffice = null;
			for (Object o : page.getData()) {
				Employee emp = null;
				PositiveReviewBean bean = new PositiveReviewBean();
				if (type == 1) {
					PlatformsActores pact = (PlatformsActores) o;
					if (listPlatId.contains(pact.getPlat().getId().toString())) {
						emp = pact.getActores().getEmployee();
						bean.setPlatName(pact.getPlat().getPlatName());
					}
				}
				if (type == 2) {
					PlatformsManager pMan = (PlatformsManager) o;
					if (listPlatId.contains(pMan.getPlat().getId().toString())) {
						emp = pMan.getManager().getEmployee();
						bean.setPlatName(pMan.getPlat().getPlatName());
					}
				}
				if (emp != null) {
					PositiveReview posi = this.positiveReviewDao.readPositiveReviewByNumber(emp.getNumber());
					if (posi != null) {
						if (posi.getType() == 0)
							continue;
						else if (posi.getType() == 2) {
							bean.setIsExtend(0);
							bean.setReason(posi.getReason());
							bean.setExAppPer(posi.getExAppPer());
							mapOn.put(emp.getId(), bean);
						}
					} else {
						bean.setIsExtend(1);
						mapOf.put(emp.getId(), bean);
					}
					bean.setId(emp.getId());
					bean.setJoinDate(emp.getJoinDate());
					bean.setName(emp.getName());
					bean.setEmpType(1);
					bean.setNickName(emp.getAliasname());
					if (branchOffice == null)
						branchOffice = this.branchOfficeService.readBranchOffice(emp.getBranchs());
					if (branchOffice != null)
						bean.setNumber(branchOffice.getNumberHead() + String.format("%04d", emp.getNumber()));
					else
						bean.setNumber(String.format("%04d", emp.getNumber()));
				}
			}
			Collection<PositiveReviewBean> valueCollection = mapOn.values();
			Collection<PositiveReviewBean> valueCollectionOf = mapOf.values();
			List<PositiveReviewBean> list = new ArrayList<PositiveReviewBean>(valueCollection);
			list.addAll(valueCollectionOf);
			page.setCurPage(1);
			page.setTotalRows(list.size());
			page.setData(list);
		}
		return page;
	}
	
	@Override
	public boolean addApply(Integer id, RoleUsers users, Integer empType, String applies, Integer type) {
		if (id != null && id.intValue() > 0) {
			Employee emp = this.employeeService.readEmployee(id);
			if (emp != null) {
				PositiveReview posi = this.positiveReviewDao.readPositiveReviewByNumber(emp.getNumber());
				Employee apply = this.employeeService.readEmployee(users.getUser().getId());
				if (posi != null) {
					posi.setType(0);
					if (apply != null)
						posi.setApplicant(apply.getName());
				} else {
					posi = new PositiveReview();
					if (empType == 1) {
						List<PlatformsActores> listPac = this.positiveReviewDao.readAllPacByEmpId(emp.getId());
						if (listPac != null && listPac.size() > 0) {
							for (PlatformsActores pac : listPac) {
								posi.setPlatId(pac.getPlat().getId());
								posi.setPlatName(pac.getPlat().getPlatName());
								break;
							}
						}
					} else if (empType == 2) {
						List<PlatformsManager> listPam = this.positiveReviewDao.readAllPmaByEmpId(emp.getId());
						if (listPam != null && listPam.size() > 0) {
							for (PlatformsManager pac : listPam) {
								posi.setPlatId(pac.getPlat().getId());
								posi.setPlatName(pac.getPlat().getPlatName());
								break;
							}
						}
					}
					if (apply != null)
						posi.setApplicant(apply.getName());
					posi.setBranchs(emp.getBranchs());
					posi.setEmpType(empType);
					posi.setJoinDate(emp.getJoinDate());
					posi.setName(emp.getName());
					posi.setNickName(emp.getAliasname());
					posi.setNumber(emp.getNumber());
					posi.setType(0);
				}
				String date = CustomDateUtils.format(new Date(), CustomDateUtils.format6);
				String $applies = posi.getReason() != null ? posi.getReason() : "";
				if (type == 2) {
					if (applies != null && applies.trim().length() > 0)
						applies = date + "    " + posi.getApplicant() + "    " + "延长试用期原因:" + applies;
					posi.setType(2);
				} else {
					applies = date + "    " + posi.getApplicant() + "    转正评语:" + applies;
					if (apply != null)
						posi.setApplicant(apply.getName());
				}
				if ($applies.trim().length() > 0)
					$applies = $applies + "</br>" + applies;
				else
					$applies = applies;
				posi.setReason($applies);
				if (this.positiveReviewDao.addApply(posi)) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, List<RoleDetailsAtts> platList, Integer type, SearchFilter searchFilter) {

		NPageResult page = this.positiveReviewDao.readPages(pageSize, curPage, sortName, sortOrder, platList, type, searchFilter);
		if (page != null && page.getData() != null && page.getData().size() > 0) {
			List<PositiveReviewBean> list = new ArrayList<PositiveReviewBean>();
			BranchOffice branchOffice = null;
			for (Object o : page.getData()) {
				PositiveReview posi = (PositiveReview) o;
				PositiveReviewBean bean = new PositiveReviewBean();
				bean.setApplicant(posi.getApplicant());
				bean.setConfirmationDate(posi.getConfirmationDate());
				bean.setExAppPer(posi.getExAppPer());
				bean.setId(posi.getId());
				bean.setJoinDate(posi.getJoinDate());
				bean.setName(posi.getName());
				bean.setNickName(posi.getNickName());
				bean.setPlatName(posi.getPlatName());
				bean.setReason(posi.getReason());
				bean.setType(posi.getType());
				if (branchOffice == null)
					branchOffice = this.branchOfficeService.readBranchOffice(posi.getBranchs());
				if (branchOffice != null)
					bean.setNumber(branchOffice.getNumberHead() + String.format("%04d", posi.getNumber()));
				else
					bean.setNumber(String.format("%04d", posi.getNumber()));
				list.add(bean);
			}
			page.setData(list);
		}
		return page;
	}
	
	@Override
	public boolean updatePass(PositiveReviewPassBean bean, RoleUsers users, HttpServletRequest request) {
		if (bean.getId() != null && bean.getId().intValue() > 0) {
			PositiveReview posi = this.positiveReviewDao.readPositiveReview(bean.getId());
			if (posi != null) {
				Employee apply = this.employeeService.readEmployee(users.getUser().getId());
				posi.setType(1);
				posi.setConfirmationDate(new Date());
				if (apply != null)
					posi.setExAppPer(apply.getName());
				String $applies = posi.getReason() != null ? posi.getReason() : "";
				String date = CustomDateUtils.format(new Date(), CustomDateUtils.format6);
				String appli = bean.getEditApply();
				if (appli != null && appli.trim().length() > 0)
					appli = date + "    " + posi.getExAppPer() + "    通过评语:" + appli;
				if ($applies.trim().length() > 0)
					$applies = $applies + "</br>" + appli;
				else
					$applies = appli;
				posi.setReason($applies);
				if (this.positiveReviewDao.addApply(posi)) {
					Employee emp = this.employeeService.readEmployeeByNumber(posi.getNumber());
					BigDecimal newSalary = new BigDecimal(bean.getNewSalary() != null && bean.getNewSalary().trim().length() > 0? bean.getNewSalary() : "0") ;
					BigDecimal oldSalary = new BigDecimal(emp.getSalary());
					newSalary = newSalary.subtract(oldSalary);
					emp.setOtherSubsidy(newSalary.floatValue());
					emp.setPushMoney(new BigDecimal(bean.getNewPushMoney() !=  null &&bean.getNewPushMoney().trim().length() > 0? bean.getNewPushMoney() : "0.00"));
					if (this.employeeService.updateEmp(emp)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean updateCheckPosi(RoleUsers users, Integer id, String apply, Integer type) {
		if (id != null && id.intValue() > 0) {
			PositiveReview posi = this.positiveReviewDao.readPositiveReview(id);
			if (posi != null) {
				Employee emp = this.employeeService.readEmployee(users.getUser().getId());
				String date = CustomDateUtils.format(new Date(), CustomDateUtils.format6);
				posi.setType(2);
				if (emp != null)
					posi.setExAppPer(emp.getName());
				String $applies = posi.getReason() != null ? posi.getReason() : "";
				if (apply != null && apply.trim().length() > 0)
					apply = date + "    " + posi.getExAppPer() + "    延长试用期原因:" + apply;
				if ($applies.trim().length() > 0)
					$applies = $applies + "</br>" + apply;
				else
					$applies = apply;
				posi.setReason($applies);
				if (this.positiveReviewDao.addApply(posi)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public PositiveReview readPositiveReviewById(Integer id) {
		return	this.positiveReviewDao.readPositiveReview(id);
	}
	
	@Override
	public boolean updatePosi(PositiveReview posi,HttpServletRequest request) {
		if(this.positiveReviewDao.addApply(posi)){
			Employee emp = this.employeeService.readEmployeeByNumber(posi.getNumber());
			emp.setGenrer(Operator.Genres.ZHENGSHI);
			emp.setRegularDate(new Date());
			if (this.employeeService.updateEmp(emp)) {
				this.workManagers.onEvents(WorkNames.EMPLOYEE_ZHUANZHENG, emp);
				this.monitorLogService.logsUpdate(request, "员工: " + emp.getName() + " ID: " + emp.getId() + "  (转正)");
				return true;
			}
		}
		return false;
	}

}
