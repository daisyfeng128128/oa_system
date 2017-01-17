package com.baofeng.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.ManagerOnlineBean;
import com.baofeng.oa.bean.ManagersBean;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.entity.PlatformsChannels;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface IManagerService {

	Managers readManagers(Integer id);

	boolean addManager(Managers post, HttpServletRequest request);
	boolean addManager(Managers post,Integer platId,Integer channe, HttpServletRequest request);

	PlatformsChannels readPlatformsChannels(Integer channelid);

	boolean deleteManagers(Integer id, HttpServletRequest request);

	List<Managers> readAllManagers();

	boolean readManager(Integer id);

	List<Managers> readManagersByEmpId(Integer id);

	boolean saveManager(Managers manager);

	boolean addLeaveManager(Managers manager);

	ManagersBean readManagersBean(Integer id);

	boolean saveManagerBySession(Managers manager);

	Managers findManagerByEmpIdSession(Integer emid);

	List<Managers> findAllManagers();

	Managers findLoadManagers(Integer id);

	NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, String genrer, SearchFilter filter, RoleUsers users, List<RoleDetailsAtts> platList,
			List<RoleDetailsAtts> attsList, String genre, String fastArg);

	List<Managers> findAllOnlineManagers();

	List<Managers> readAllManagersByPhone(String id, String phone);

	boolean checkManager(Managers post);

	ManagersBean editManagerById(Integer id);

	ManagerOnlineBean editManagerOnlineById(Integer id);

}
