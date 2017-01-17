package com.baofeng.oa.service;

import java.util.Date;
import java.util.List;

import com.baofeng.oa.bean.PlatformsActivityOutlayBean;
import com.baofeng.oa.entity.PlatformsActivityOutlay;
import com.baofeng.utils.SearchFilter;

public interface IPlatformsActivityOutlayService {

	List<PlatformsActivityOutlayBean> readAllPlatformsActivityOutlay(String platId, Integer mId, String date, SearchFilter searchFilter);

	boolean savePlatformsActivityOutlay(PlatformsActivityOutlay post);

	boolean deleteById(Integer id);

	boolean updatePlatformsActivityOutlay(Integer id);

	PlatformsActivityOutlay findActivityOutlayByPlatId(String key, Integer platId, Date date1, Date date2);

}
