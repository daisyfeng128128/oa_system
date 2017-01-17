package com.baofeng.oa.service;

import java.util.Date;
import java.util.List;

import com.baofeng.oa.entity.FinPlatfromsIncome;
import com.baofeng.oa.entity.FinSalaryOnline;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface ISalaryOnlineService {
	NPageResult<?> readAllSalaryOnline(int pageSize, int curPage, String sortName, String sortOrder, String date, String see, SearchFilter filter, String fastArg);

	boolean updateSalary(List<FinSalaryOnline> list);

	FinSalaryOnline readFinOnline(Integer id);

	void addSalary(List<FinSalaryOnline> salaryList);

	boolean addIncome(FinPlatfromsIncome finPlatfromsIncome);

	boolean findSalaryOnline(Integer id, Date date1, Date date2);

	List<FinSalaryOnline> findAllFinSalaryOnlineByDate(Date date1, Date date2);

	boolean check(String date);

	FinPlatfromsIncome findFinPlatfromsIncome(Integer salaryId, String platformsId, Date date1, Date date2);

	boolean updateFinPlatformsIncome(FinPlatfromsIncome finIncome);

	boolean saveFinSalaryOfflineByList(List<String> list);

	List<FinSalaryOnline> readAllsalaryOnlineByDate(Date date1, Date date2);
}
