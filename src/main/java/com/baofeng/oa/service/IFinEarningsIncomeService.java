package com.baofeng.oa.service;

import java.util.Date;
import java.util.List;

import com.baofeng.oa.entity.FinEarningsIncome;
import com.baofeng.oa.entity.FinPlatformsIncome;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface IFinEarningsIncomeService {

	boolean addEarnings(FinEarningsIncome finEarn);

	boolean deleteFinEarningsIncome(FinEarningsIncome earnIncome);

	List<FinEarningsIncome> findAllFinEarningsIncome(Date date1, Date date2);

	FinPlatformsIncome findPlatformsIncomeById(Integer id, String platformsId);

	NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, String date, String fastArg, String type, SearchFilter searchFilter);

	
	
}
