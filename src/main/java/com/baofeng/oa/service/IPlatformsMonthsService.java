package com.baofeng.oa.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.entity.PlatformsMonths;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface IPlatformsMonthsService {

	void addPlatformsMonths(PlatformsMonths months);

	boolean savePlatformsMonths(PlatformsMonths months);

	boolean updatePlatformsMonthsOutlay(Integer id);

	NPageResult<?> readAllPlatformsMonths(int pageSize, int curPage, String sortName, String sortOrder, String date, SearchFilter filter, List<RoleDetailsAtts> platList);

	boolean updateIncome(List<PlatformsMonths> list);

	PlatformsMonths findPlatformsMonths(Integer branchs, String platId, Date date1, Date date2);

	List<PlatformsMonths> findAllPlatformsMonths(Date date1, Date date2);

	boolean updateTopUp(List<PlatformsMonths> list);

	NPageResult<?> readAllMonthsOutlay(int pageSize, int curPage, String sortName, String sortOrder, String date, SearchFilter filter, List<RoleDetailsAtts> platList);

	List<PlatformsMonths> readAllPlatformsMonthsByDate(String date);

	PlatformsMonths findPlatformsMonthsByMId(Integer mid);

	BigDecimal findSumCurrentIncome(Integer branchs, Date $date1, Date $date2);
}
