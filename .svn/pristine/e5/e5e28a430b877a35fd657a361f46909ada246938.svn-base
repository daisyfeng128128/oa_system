package com.baofeng.oa.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.entity.EmployeeTransfer;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;


public interface IEmpTransferService {

	NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter searchFilter,String ads, List<RoleDetailsAtts> platList);

	EmployeeTransfer readTraEmployee(Integer number);

	List<EmployeeTransfer> readTraEmployee(Date $date1, Date $date2);

	boolean saveEmployeeTra(EmployeeTransfer employeeT);

	boolean updatePassedEmpTransfer(String ids, HttpServletRequest request);

	
}
