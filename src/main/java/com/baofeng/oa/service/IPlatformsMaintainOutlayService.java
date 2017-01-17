package com.baofeng.oa.service;

import java.util.List;

import com.baofeng.oa.bean.PlatformsMaintainOutlayBean;
import com.baofeng.oa.entity.PlatformsMaintainOutlay;
import com.baofeng.utils.SearchFilter;

public interface IPlatformsMaintainOutlayService {

	List<PlatformsMaintainOutlayBean> readAllPlatformsMaintainOutlay(String platId, Integer mId, String date, SearchFilter searchFilter);

	boolean savePlatformsMaintainOutlay(PlatformsMaintainOutlay post);

	boolean deleteById(Integer id);

	boolean updatePlatformsMaintainOutlay(Integer id);

	List<PlatformsMaintainOutlay> readAllPlatformsMaintainOutlayByDate(Integer monthid);

}
