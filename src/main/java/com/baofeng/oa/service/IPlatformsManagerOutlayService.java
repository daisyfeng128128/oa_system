package com.baofeng.oa.service;

import java.util.Date;
import java.util.List;

import com.baofeng.oa.bean.PlatformsManagerOutlayBean;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.oa.entity.PlatformsManagerOutlay;
import com.baofeng.utils.SearchFilter;

public interface IPlatformsManagerOutlayService {

	void addManagerOutlay(List<PlatformsManagerOutlay> listOut);

	List<PlatformsManagerOutlayBean> readAllPlatformsManagerOutlay(String platId, Integer mId, String date, String online, SearchFilter searchFilter);

	boolean updateManagerOutlay(List<PlatformsManagerOutlay> list);

	boolean savePlatformsManagerOutlay(PlatformsManagerOutlay outlay);

	List<?> findPlatformsManagerOutlayList(Integer manaId);

	boolean findValidation(Integer id, Integer id2, String platId, String channelId, Date date1, Date date2);

	PlatformsManagerOutlay findPlatformsManagerOutlay(Integer id);

	List<PlatformsManagerOutlay> findPlatformsManagerOutlayListNew(Integer manaId, Date date1, Date date2);

	boolean deletePlatformsManagers(PlatformsManager pmanager);

	boolean findActValidation(String string, Integer mid, String platId, String channelId, Date date1, Date date2);

	List<PlatformsManagerOutlay> findPlatformsManagerOutlayList(Integer mid, Date createDT);

	PlatformsManagerOutlay readPlatformsManagerOutlay(Integer number, Integer branchs);
}
