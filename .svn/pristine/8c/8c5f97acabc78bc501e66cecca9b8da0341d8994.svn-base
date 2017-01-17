package com.baofeng.oa.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.Department;
import com.baofeng.oa.entity.FinPlatformsIncome;
import com.baofeng.oa.entity.FinSalaries;
import com.baofeng.oa.entity.FinSalariesOnline;
import com.baofeng.oa.entity.FinSalariesTalent;
import com.baofeng.oa.entity.Managers;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface IFinSalariesOnlineService {

	void updateFinPlatformsIncome(FinPlatformsIncome finIncome);

	void addSalary(List<FinSalariesOnline> listCheck);

	boolean findValidation(String name, Integer id, Date date1, Date date2);

	NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, String date, Integer type, String fastArg, Integer all, String plat, String sex,
			SearchFilter searchFilter);

	FinSalariesOnline readFinSalariesOnline(String id);

	boolean addSalariesOnline(FinSalariesOnline post);

	boolean saveFinSalariesOnline(FinSalariesOnline salaries);

	FinPlatformsIncome findPlatformsIncomeById(Integer id, String platformsId);

	void savePlatformsIncome(FinPlatformsIncome incomeDetails);

	FinSalariesOnline findFinSalariesOnlineByActores(Integer id, String name, Date $date1, Date $date2);

	boolean updateFinSalariesOnline(List<String> listId);

	List<FinSalariesOnline> readAllFinSalariesOnlineManager(String date, SearchFilter searchFilter);

	List<FinSalariesOnline> readAllFinSalariesOnlineActores(String date, SearchFilter searchFilter);

	HSSFWorkbook export(List<FinSalaries> listOff, List<FinSalaries> listActoresOff, List<FinSalariesOnline> listOnline, List<FinSalariesOnline> listActoresOnline,
			List<FinSalariesTalent> listTalent);

	boolean addFinSalariesOnline(FinSalariesOnline fin);

	List<FinSalariesOnline> findAllFinSalariesOnline(Date $date1, Date $date2);

	List<FinPlatformsIncome> findIncomeList(Integer salariesId);

	List<FinSalariesOnline> readAllFinSalariesOnline(String date, String plat, String sex, Integer branchs);

	HSSFWorkbook partExcel(List<FinSalariesOnline> list);

	BigDecimal findSumOnlineActores(Integer branchs, Class<Actores> class1, Date $date1, Date $date2);

	BigDecimal findSumOnlineManagers(Integer branchs, Class<Managers> class1, Date $date1, Date $date2);

	List<FinSalariesOnline> findFinSalariesOnlineByClass(Class<Actores> class1, Date $date1, Date $date2);

	HSSFWorkbook exportFinSalaries(Map<String, List<FinSalaries>> list,
			List<Department> listdep);

}
