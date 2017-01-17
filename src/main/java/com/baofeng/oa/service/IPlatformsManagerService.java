package com.baofeng.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.oa.bean.PlatformsManagerBean;
import com.baofeng.oa.entity.PlatformsChannels;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface IPlatformsManagerService {

	PlatformsManager readPlatformsManager(Integer id);

	boolean addPlatManager(PlatformsManager post, HttpServletRequest request);

	boolean addPlatManagers(PlatformsManager post, HttpServletRequest request);
	
	PlatformsChannels readPlatformsChannels(Integer channelid);

	boolean deletePlatformsManager(Integer id, HttpServletRequest request);

	NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, Integer platid, String fastArg, SearchFilter filter);

	boolean readPlatformsManager(Integer id, Integer platId, Integer channelId);

	List<PlatformsManager> readPlatformsManagerByEmpId(Integer id);

	boolean savePlatformsManager(PlatformsManager manager);

	List<PlatformsManagerBean> readPlatformsManagerBeanList(String realname, SearchFilter searchFilter, Integer branchId);

	boolean verify(Integer id, Integer platId, Integer channelId);

	List<?> findPlatformsManagerBySession(Integer manId);

	boolean savePlatformsManagerBySession(PlatformsManager plam);

	boolean deletePlatformsManagerBySession(Integer manId);

	List<PlatformsManager> findAllPlatformsManager();

	PlatformsManager readPlatformsManagerByMId(Integer mid);

}
