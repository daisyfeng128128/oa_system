package com.baofeng.oa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.ActoresBean;
import com.baofeng.oa.bean.AdvanceSalaryBean;
import com.baofeng.oa.dao.AdvanceSalaryDAO;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.AdvanceSalary;
import com.baofeng.oa.entity.BaseEnums.Advance;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BaseEnums.Repay;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.service.IActoresService;
import com.baofeng.oa.service.IAdvanceSalaryService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

@Service("advanceSalaryService")
public class AdvanceSalaryServiceImpl implements IAdvanceSalaryService{
	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private IActoresService actoresService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IPlatformsService platformsService;
	@Autowired
	private IMonitorLogService monitorLogService;
	@Autowired
	private AdvanceSalaryDAO advanceSalaryDAO;

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName,
			String sortOrder, SearchFilter searchFilter, String ads,List<RoleDetailsAtts> platList,String repay) {
		NPageResult rows = this.advanceSalaryDAO.readPages(pageSize, curPage, sortName, sortOrder, searchFilter, ads, platList,repay);
		if (rows != null && rows.getData().size() > 0) {
			List<AdvanceSalaryBean> beanList = new ArrayList<AdvanceSalaryBean>();
			for (Object o : rows.getData()) {
				AdvanceSalary adjust = (AdvanceSalary) o;
				AdvanceSalaryBean bean = new AdvanceSalaryBean();
				bean.setId(adjust.getId().toString());
				bean.setRealname(adjust.getRealname());
				bean.setAliasname(adjust.getAliasname());
				bean.setSex(adjust.getSex() == Sex.WOMAN ? "女" : "男");
				bean.setPlatName(adjust.getPlatName());
				bean.setGenre(adjust.getGenre() == LineGenres.ONLINE ? "线上艺人" : "线下艺人");
				bean.setSbasicSalary(adjust.getSbasicSalary().toString());
				bean.setAdvance(adjust.getAdvance().toString());
				bean.setAdvanceSalary(adjust.getAdvanceSalary().toString());
				bean.setAdvance(adjust.getAdvance() == Advance.UNKNOWN ? "待借" : (adjust.getAdvance() == Advance.PASSED ? "已借" : "拒绝"));
				if (adjust.getAdvance() == Advance.PASSED) {
					bean.setRepay(adjust.getRepay() == Repay.NOTPASS ? "未还款" :  "已还款");
				}
				bean.setEmployee(adjust.getEmployee() == null ? "" : adjust.getEmployee().getName());
				bean.setCreateDT(adjust.getCreateDT());
				bean.setPassTime(adjust.getPassTime());
				bean.setRepayTime(adjust.getRepayTime());
				beanList.add(bean);
			}
			rows.setData(beanList);
		}
		return rows;
	}

	@Override
	public List<ActoresBean> readPlatformsActores(Actores actores,List<RoleDetailsAtts> platList) {
		return this.advanceSalaryDAO.readPlatformsActores(actores, platList);
	}

	@Override
	public boolean saveAdvanceSalary(AdvanceSalaryBean bean,
			RoleUsers users, Integer branchs, HttpServletRequest request) {
		AdvanceSalary adjust = new AdvanceSalary();
		Platforms platforms = this.platformsService.readPlatforms(Integer.parseInt(bean.getPlatName()));
		Actores actores = this.actoresService.readActoresById(Integer.parseInt(bean.getId()));
		Employee employee = this.employeeService.readEmployee(users.getUser().getId());
		if (actores != null) {
			adjust.setBranchs(branchs);
			adjust.setRealname(actores.getRealname());
			adjust.setAliasname(actores.getAliasname());
			adjust.setSex(actores.getSex());
			adjust.setPlatId(platforms.getId().toString());
			adjust.setPlatName(platforms.getPlatName());
			adjust.setGenre(actores.getGenre());
			if (actores.getGenre() == LineGenres.ONLINE)
				adjust.setSbasicSalary(new BigDecimal(actores.getMinimumGuarantee() == null ? 0.00 : actores.getMinimumGuarantee()));
			else
				adjust.setSbasicSalary(new BigDecimal(actores.getBasicSalary() == null ? 0.00 : actores.getBasicSalary()));
			adjust.setAdvanceSalary(new BigDecimal(bean.getAdvanceSalary()));
			adjust.setActores(actores);
			adjust.setAdvance(Advance.UNKNOWN);
			adjust.setRepay(Repay.NOTPASS);
			adjust.setEmployee(employee);
			if (this.advanceSalaryDAO.saveAdvanceSalary(adjust)) {
				this.monitorLogService.logsAdd(request, "借支申请" + (adjust.getRealname() == null ? adjust.getAliasname() : adjust.getRealname()) + " ID: " + adjust.getId());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateRejectAdvanceSalary(String ids,HttpServletRequest request) {
		String[] $ids = ids.split(",");
		for (String id : $ids) {
			AdvanceSalary adjust = this.readAdvanceSalary(id);
			if (adjust != null) {
				adjust.setAdvance(Advance.FAILED);
				if (this.advanceSalaryDAO.saveAdvanceSalary(adjust)) {
					this.monitorLogService.logsUpdate(request, "拒绝借支申请" + adjust.getRealname() + " ID: " + adjust.getId());
				}
			}
		}
		return true;
	}

	@Override
	public boolean updatePassedAdvanceSalary(String ids,HttpServletRequest request) {
		String[] $ids = ids.split(",");
		for (String id : $ids) {
			AdvanceSalary adjust = this.readAdvanceSalary(id);
			if (adjust != null) {
				adjust.setAdvance(Advance.PASSED);
				adjust.setPassTime(new Date());
				if (this.advanceSalaryDAO.saveAdvanceSalary(adjust)) {
					this.workManagers.onEvents(WorkNames.ADVANCEDSALARY, adjust);
					this.monitorLogService.logsUpdate(request, "通过借支申请" + adjust.getRealname() + " ID: " + adjust.getId());
				}
			}
		}
		return true;
	}
	
	@Override
	public AdvanceSalary readAdvanceSalary(String id) {
		return this.advanceSalaryDAO.readAdvanceSalary(id);
	}
	
	public boolean updateRepay(String ids, HttpServletRequest request) {
		String[] $ids = ids.split(",");
		for (String id : $ids) {
			AdvanceSalary adjust = this.readAdvanceSalary(id);
			if (adjust != null) {
				adjust.setRepay(Repay.PASSED);
				adjust.setRepayTime(new Date());
				if (this.advanceSalaryDAO.saveAdvanceSalary(adjust)) {
					this.workManagers.onEvents(WorkNames.ADVANCEDSALARY, adjust);
					this.monitorLogService.logsUpdate(request, "还款成功" + adjust.getRealname() + " ID: " + adjust.getId());
				}
			}
		}
		return true;
	}

}
