package com.baofeng.oa.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baofeng.oa.bean.FinSalaryBean;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.Department;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.FinPlatformsIncome;
import com.baofeng.oa.entity.FinSalaries;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface IFinSalariesService {

	boolean findValidation(Employee emp, Date date1, Date date2);

	boolean saveFinSalaries(FinSalaries finSalaries);

	FinSalaries findFinSalariesByNumber(Integer number, Integer branchs, Date $date1, Date $date2);

	FinPlatformsIncome findPlatformsIncomeById(Integer salariesId, String platformsId);

	void savePlatformsIncome(FinPlatformsIncome incomeDetails);

	List<FinSalaries> findAllFinSalaries(Date $date1, Date $date2);

	List<FinPlatformsIncome> findIncomeList(Integer salariesId);

	List<String> readFinSalaryDate();

	NPageResult<?> readAllSalaryEmployee(int pageSize, int curPage, String sortName, String sortOrder, String id, String date, SearchFilter filter, Integer type, String fastArg);

	NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, String date, Integer type, String fastArg, Integer all, String plat, String sex,
			SearchFilter filter);

	FinSalaryBean readSalary(Integer id);

	boolean addFinSalaries(FinSalaries post);

	FinSalaries readFinSalaries(String string);

	boolean addSalariesOffline(FinSalaries post);

	List<FinSalaries> readAllFinSalariess(String date, SearchFilter searchFilter);

	List<FinSalaries> readAllFinSalariessManager(String date, SearchFilter searchFilter);

	List<FinSalaries> readAllFinSalariessActores(String date, SearchFilter searchFilter);

	List<FinSalaries> readAllFinSalariesOffActores(String date, String sex, String plat, SearchFilter searchFilter);

	HSSFWorkbook export(List<FinSalaries> list);

	HSSFWorkbook exportPart(List<FinSalaries> listActoresOff);

	boolean saveFinSalaryOfflineByList(List<String> list, String date);

	FinSalaries readFinSalaryById(Integer id);

	BigDecimal findSumAllEmployees(Integer branchs, Date $date1, Date $date2);

	NPageResult<?> readPagesShowSalaries(int pageSize, int curPage, String sortName, String sortOrder, String date, String name, SearchFilter searchFilter);

	List<FinPlatformsIncome> findPlatformsIncomeByList(Date $date1, Date $date2);

	List<FinSalaries> findFinSalariesByActores(Class<Actores> class1, Date $date1, Date $date2);

	Map<String, List<FinSalaries>> readAllFinSalariessbyDep(String date,
			SearchFilter searchFilter, List<Department> listdep);

}
