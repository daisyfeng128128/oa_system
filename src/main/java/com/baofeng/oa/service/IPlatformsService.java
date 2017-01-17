package com.baofeng.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsChannels;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

public interface IPlatformsService {

	public NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String name, List<RoleDetailsAtts> platList, List<RoleDetailsAtts> attsList,
			SearchFilter searchFilter);

	boolean addPlatforms(Platforms post, String[] channels, HttpServletRequest request);

	Platforms readPlatforms(Integer id);

	boolean deletePlatforms(Integer id, HttpServletRequest request);

	public PageResult<?> readPagesPlat(int rows, int page, SearchFilter $filter, String filter);

	NPageResult<?> PagesPlat(int pageSize, int curPage, String sortName, String sortOrder, Integer id, SearchFilter filter, String queryFilter);

	public PlatformsChannels readChannels(Integer channelsId);

	public boolean savePlatformsActores(PlatformsActores platformsActores);

	public boolean readPlatformsByChan(String id, String channels);

	public List<Platforms> findAllPlatforms(SearchFilter searchFilter);

	public Platforms findPlatforms(Integer platId);

	public PageResult<?> readAllPagesSkip(int rows, int page, SearchFilter fil, String filter, List<RoleDetailsAtts> platList);

	public List<Platforms> findAllPlatforms();

	PlatformsActores readPlatformsActores(Integer pactId);

	public PageResult<?> readPagesAllPlat(int rows, int page,SearchFilter $filter, String filter);

}
