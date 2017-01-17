package com.baofeng.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.ElectEquipentBean;
import com.baofeng.oa.bean.OfficeSuppliesBean;
import com.baofeng.oa.entity.ElectEquipent;
import com.baofeng.oa.entity.OfficeSupplies;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;


public interface IPurchaseAuditService {

	NPageResult<?> readPages(int pageSize, int curPage, String sortName,String sortOrder,Integer id, String fastArg,Integer type,SearchFilter searchFilter, String classQuery);

	NPageResult<?> readPagesR(int pageSize, int curPage, String sortName,String sortOrder, Integer type,Integer id,String fastArg, SearchFilter searchFilter, String classQuery);

	boolean updatedeptPass(List<String> list, RoleUsers users,HttpServletRequest request);

	boolean updateequiPass(List<String> list, RoleUsers users,HttpServletRequest request);

	boolean updatepersonPass(List<String> list, RoleUsers users,HttpServletRequest request);

	boolean updateheadPass(List<String> list, RoleUsers users,HttpServletRequest request);

	boolean updatestartEl(List<String> list, RoleUsers users,HttpServletRequest request);

	boolean updatendEl(ElectEquipentBean bean, RoleUsers users,HttpServletRequest request);

	boolean updatedeptPassOff(List<String> list, RoleUsers users,HttpServletRequest request);

	boolean updatepersonPassOff(List<String> list, RoleUsers users,HttpServletRequest request);

	boolean updateheadPassOff(List<String> list, RoleUsers users,HttpServletRequest request);

	boolean updatestartOff(List<String> list, RoleUsers users,HttpServletRequest request);

	boolean updatendOff(OfficeSuppliesBean bean, RoleUsers users,HttpServletRequest request);

	boolean updateRejEl(ElectEquipentBean bean, RoleUsers users,HttpServletRequest request);

	boolean updateRejOff(OfficeSuppliesBean bean, RoleUsers users,HttpServletRequest request);

	List<ElectEquipent> readElectEquipentbyBranch(Integer branchs);

	List<OfficeSupplies> readOfficeSuppliesbyBranch(Integer branchs);

	ElectEquipent checkReasonEl(ElectEquipentBean bean, RoleUsers users,HttpServletRequest request);

	ElectEquipent checkReason(Integer id);

	OfficeSupplies checkReasonOff(Integer id);

	

}
