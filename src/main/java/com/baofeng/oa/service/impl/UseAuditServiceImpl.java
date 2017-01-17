package com.baofeng.oa.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.EquipmentCollarBean;
import com.baofeng.oa.dao.UseAuditDAO;
import com.baofeng.oa.entity.BaseEnums.Auditp;
import com.baofeng.oa.entity.BaseEnums.GrantEquip;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.EquipmentCollar;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IUseAuditService;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Service("useAuditService")
public class UseAuditServiceImpl implements IUseAuditService {
	@Autowired
	private UseAuditDAO useAuditDAO;
	@Autowired
	private IEmployeeService employeeService;

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName,String sortOrder, Integer type, String fastArg, Integer id,
			SearchFilter searchFilter,String classQuery) {
		NPageResult rows = this.useAuditDAO.readPages(pageSize, curPage, sortName, sortOrder, fastArg,id,searchFilter,type,classQuery);
		if (rows !=null  && rows.getData().size() > 0) {
			List<EquipmentCollarBean> beanList = new ArrayList<EquipmentCollarBean>();
			for (Object o : rows.getData()) {
					EquipmentCollar equipmentCollar = (EquipmentCollar) o;
					EquipmentCollarBean bean = new EquipmentCollarBean();
					bean.setId(equipmentCollar.getId());
					bean.setNum(String.valueOf(equipmentCollar.getEmployee().getNumber()));
					bean.setRealname(equipmentCollar.getRealname());
					bean.setAliasname(equipmentCollar.getAliasname());
					bean.setDepart(equipmentCollar.getDepart());
					bean.setAuditp(equipmentCollar.getAuditp() == Auditp.PENDING ? "待审核" : (equipmentCollar.getAuditp() == Auditp.UNKNOWN)? "审核中" : (equipmentCollar.getAuditp() == Auditp.PASSED ? "通过" : "未通过"));
					bean.setGrantEquip(equipmentCollar.getGrantEquip() == GrantEquip.UNKNOWN ? "待发放" : (equipmentCollar.getGrantEquip() == GrantEquip.PASSED ? "发放完成" : ""));
					bean.setApplyDT(equipmentCollar.getApplyDT());
					bean.setRemarks(equipmentCollar.getRemarks());
					if (equipmentCollar.getCompleteDT() != null ) {
						bean.setCompleteDT(equipmentCollar.getCompleteDT());
					}
					if (equipmentCollar.getRejectMsg() != null) {
						bean.setRejectMsg(equipmentCollar.getRejectMsg());;
					}
					bean.setGoodsname(equipmentCollar.getGoodsname());
					bean.setModel(equipmentCollar.getModel());
					bean.setNumbers(equipmentCollar.getNumbers().toString());
					bean.setPlace(equipmentCollar.getPlace());
					bean.setProcess(equipmentCollar.getProcess());
					beanList.add(bean);
				}
				rows.setData(beanList);
			}
		return rows;
	}

	@Override
	public boolean updatedeptPass(List<String> list, RoleUsers users,HttpServletRequest request) {
		if (list != null && list.size() > 0) {
			for (String id : list) {
				EquipmentCollar equipmentCollar = this.useAuditDAO.readEquipmentCollar(Integer.valueOf(id));
				Employee employee = this.employeeService.readEmployee(users.getUser().getId());
				if (equipmentCollar != null) {
					equipmentCollar.setAuditp(Auditp.UNKNOWN);
					String date = CustomDateUtils.format(new Date(), CustomDateUtils.format6);
					equipmentCollar.setReason(date+"  "+employee.getName() + ":通过部门审核"+ "</br>");
					equipmentCollar.setProcess(2);
					this.useAuditDAO.saveEquipmentCollar(equipmentCollar); 
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updateequiPass(List<String> list, RoleUsers users,HttpServletRequest request) {
		if (list != null && list.size() > 0) {
			for (String id : list) {
				EquipmentCollar equipmentCollar = this.useAuditDAO.readEquipmentCollar(Integer.valueOf(id));
				if (equipmentCollar != null) {
					equipmentCollar.setAuditp(Auditp.PASSED);
					equipmentCollar.setGrantEquip(GrantEquip.UNKNOWN);
					equipmentCollar.setProcess(3);
					this.useAuditDAO.saveEquipmentCollar(equipmentCollar); 
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updateEnd(List<String> list, RoleUsers users,HttpServletRequest request) {
		if (list != null && list.size() > 0) {
			for (String id : list) {
				EquipmentCollar equipmentCollar = this.useAuditDAO.readEquipmentCollar(Integer.valueOf(id));
				Employee employee = this.employeeService.readEmployee(users.getUser().getId());
				if (equipmentCollar != null) {
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
					try {
						equipmentCollar.setCompleteDT(sdf.parse(sdf.format(new Date())));
						equipmentCollar.setGrantEquip(GrantEquip.PASSED);
						String date = CustomDateUtils.format(new Date(), CustomDateUtils.format6);
						equipmentCollar.setIssuer(date+"  "+employee.getName() + ":通过发放"+ "</br>");
						equipmentCollar.setProcess(4);
						this.useAuditDAO.saveEquipmentCollar(equipmentCollar);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updateReject(EquipmentCollarBean bean, RoleUsers users,HttpServletRequest request) {
			if(bean.getId() != null){
				EquipmentCollar equipmentCollar = this.useAuditDAO.readEquipmentCollar(Integer.valueOf(bean.getId()));
				if (equipmentCollar != null) {
					equipmentCollar.setAuditp(Auditp.NOTPASS);
					equipmentCollar.setRejectMsg(bean.getRejectMsg());
					if (this.useAuditDAO.saveEquipmentCollar(equipmentCollar)) {
						return true;
					}
				}
			}
		return false;
	}

	@Override
	public EquipmentCollar checkReason(Integer id) {
		return this.useAuditDAO.readEquipmentCollar(id);
	}

}
