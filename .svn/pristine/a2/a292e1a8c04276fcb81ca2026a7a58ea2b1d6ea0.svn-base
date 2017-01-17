package com.baofeng.oa.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baofeng.oa.bean.PlatformsMonthsOutlayBean;
import com.baofeng.oa.entity.BaseEnums.Audit;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsAudits;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.utils.SearchFilter;

public interface IPlatformsMonthsOutlayService {

	void addPlatformsMonthsOutlay(List<PlatformsMonthsOutlay> listOutlay);

	PlatformsMonthsOutlayBean readAllPlatformsMonthsOutlay(String platId, Integer mId, String date, SearchFilter searchFilter);

	PlatformsMonthsOutlay readPlatformsMonthsOutlay(String platId);

	boolean savePlatformsMonthsOutlay(PlatformsMonthsOutlayBean bean);

	List<String> readAllPlatformsMonthsOutlayDate();

	boolean updatePlatformsMonthsOutlay(PlatformsMonthsOutlayBean bean, String audit);

	PlatformsMonthsOutlay savePlatformsMonthsOutlay(Integer branchs, Platforms plat, Date date1, Date date2);

	PlatformsMonthsOutlay readPlatformsMonthsOutlay(String platId, Integer mId, String date, SearchFilter filter);

	PlatformsMonthsOutlay readPlatformsMonthsOutlayByHead(Integer mId, Integer branchs, String platId, String date);

	PlatformsAudits readPlatfromsAuditesByMid(Integer mId);

	boolean updatePlatformsMonthsOutlay(Integer monthsId);

	List<PlatformsMonthsOutlay> findPlatformsMonthsOutlay(Date date1, Date date2);

	boolean updatePlatformsMonthsOutlayByAudit(Integer id, String audit);

	PlatformsMonthsOutlay findPlatformsMonthsOutlay(String platId, Integer branchs, Date date1, Date date2);

	BigDecimal findSumOfflineIncome(Integer id);

	BigDecimal findSumOnlineIncome(Integer id);

	List<PlatformsMonthsOutlay> readAllPlatformsMonthsOutlayByDate(Date date1, Date date2);

	PlatformsMonthsOutlay readPlatformsMonths(Date date1, Date date2, Integer platId);

	boolean findMonthsOutlay(Integer branchs, Integer pId, Date date1, Date date2, Audit audit);

	List<PlatformsMonthsOutlay> findPlatformsMonthsOutlayList(Date date);

	PlatformsMonthsOutlay readPlatformsMonthsOutlay(Integer pid, Integer branchs, Date date1, Date date2);
}
