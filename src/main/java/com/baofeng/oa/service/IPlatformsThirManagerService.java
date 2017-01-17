package com.baofeng.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.oa.entity.PlatformsChannels;
import com.baofeng.oa.entity.PlatformsThirdManager;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

public interface IPlatformsThirManagerService {

	NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String majia, String realname, Integer channelid, Integer platid,
			SearchFilter filter);

	boolean deleteThirdManager(Integer id, HttpServletRequest request);

	PlatformsThirdManager readThirdManager(Integer id);

	PlatformsChannels readPlatformsChannels(Integer channelid);

	boolean addThirdManager(PlatformsThirdManager post, HttpServletRequest request);

	PageResult<?> readPagesChannel(int rows, int page, Integer platid, SearchFilter $filter, String filter);

	List<PlatformsThirdManager> findAllPlatformsThirdManager();

	List<?> findPlatformsThirdManagerBySession(Integer tmanId);
}
