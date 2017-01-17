package com.baofeng.oa.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baofeng.oa.bean.PlatformsMonthsOutlayBean;
import com.baofeng.oa.bean.PlatformsMonthsTotalOutlayBean;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsAudits;
import com.baofeng.oa.entity.PlatformsMonthsTotalOutlay;
import com.baofeng.utils.SearchFilter;

public interface IPlatformsMonthsTotalOutlayService {

	void addPlatformsMonthsOutlay(List<PlatformsMonthsTotalOutlay> listOutlay);

	PlatformsMonthsTotalOutlayBean readAllPlatformsMonthsOutlay(String platId, Integer mId, String date, SearchFilter searchFilter);

	PlatformsMonthsTotalOutlay readPlatformsMonthsOutlay(String platId);

	boolean savePlatformsMonthsOutlay(PlatformsMonthsOutlayBean bean);

	List<String> readAllPlatformsMonthsOutlayDate();

	PlatformsMonthsTotalOutlay savePlatformsMonthsOutlay(Integer branchs, Platforms plat, Date date1, Date date2);

	PlatformsMonthsTotalOutlay readPlatformsMonthsOutlay(String platId, Integer mId, String date, SearchFilter filter);

	PlatformsMonthsTotalOutlay readPlatformsMonthsOutlayByHead(Integer mId, Integer branchs, String platId, String date);

	PlatformsAudits readPlatfromsAuditesByMid(Integer mId);

	boolean updatePlatformsMonthsOutlay(Integer monthsId);

	List<PlatformsMonthsTotalOutlay> findPlatformsMonthsOutlay(Date date1, Date date2);

	PlatformsMonthsTotalOutlay findPlatformsMonthsOutlay(String platId, Integer branchs, Date date1, Date date2);

	BigDecimal findSumOfflineIncome(Integer id);

	BigDecimal findSumOnlineIncome(Integer id);

	List<PlatformsMonthsTotalOutlay> readAllPlatformsMonthsOutlayByDate(Date date1, Date date2);

	PlatformsMonthsTotalOutlay readPlatformsMonths(Date date1, Date date2, Integer platId);

	List<PlatformsMonthsTotalOutlay> findPlatformsMonthsOutlayList(Date date);

}
