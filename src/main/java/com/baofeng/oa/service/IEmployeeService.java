package com.baofeng.oa.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.EmployeeLeave;
import com.baofeng.oa.entity.EmployeeTransfer;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

public interface IEmployeeService {

	Employee readEmployee(Integer empid);

	Employee readEmployeeByNumber(Integer number);

	PageResult<?> readAllPagesSkip(int rows, int page, SearchFilter $filter, String filter);

	boolean addEmployee(Employee post, HttpServletRequest request);

	NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String name, String snumber, String snamepy, Integer sposid, Integer sdepid,
			String fastArg, String type, SearchFilter filter);

	boolean deleteEmployee(Integer id, HttpServletRequest request);

	NPageResult<?> readAllPagesProbationer(int pageSize, int curPage, String sortName, String sortOrder, String fastArg, SearchFilter filter);

	boolean updateEmployee(Integer id, Integer type, String reason, HttpServletRequest request);

	boolean addEmp(Employee post);

	List<Employee> readAllEmpByPid(Integer id);

	NPageResult<?> readAllPagesLeave(int pageSize, int curPage, String sortName, String sortOrder, String fastArg, SearchFilter filter);

	List<Employee> findAllEmployee();

	boolean saveEmployee(Employee emp);

	List<Employee> findEmployeeByPending();

	List<Employee> findAllEmployees();

	List<Employee> findAllEmployees(Date date2);

	List<Employee> findAllEmployeesNeLeave();

	EmployeeLeave findEmployeeLeaveByEmpId(Integer id);

	EmployeeLeave readEmployeeLeave(Integer id);

	NPageResult<?> readPagesBirth(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter);

	Employee readEmployeebyNumber(Integer number, Integer branchs);

	boolean updateEmp(Employee emp);

	boolean saveTransferEmployee(EmployeeTransfer empTransfer, HttpServletRequest request);

}
