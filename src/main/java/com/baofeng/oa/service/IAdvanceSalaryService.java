package com.baofeng.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.ActoresBean;
import com.baofeng.oa.bean.AdvanceSalaryBean;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.AdvanceSalary;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface IAdvanceSalaryService {

	NPageResult<?> readPages(int pageSize, int curPage, String sortName,String sortOrder, 
			SearchFilter searchFilter, String ads,List<RoleDetailsAtts> platList, String repay);

	List<ActoresBean> readPlatformsActores(Actores actores,List<RoleDetailsAtts> platList);

	boolean updateRejectAdvanceSalary(String ids, HttpServletRequest request);

	boolean updatePassedAdvanceSalary(String ids, HttpServletRequest request);

	AdvanceSalary readAdvanceSalary(String id);

	boolean saveAdvanceSalary(AdvanceSalaryBean bean, RoleUsers users,Integer branchs, HttpServletRequest request);

	boolean updateRepay(String ids, HttpServletRequest request);


}
