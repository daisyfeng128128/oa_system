package com.baofeng.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.entity.ProjectManagement;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;


public interface IProjectManagementService {

	NPageResult<?> PagesProject(int pageSize, int curPage, String sortName,String sortOrder, Integer did, SearchFilter filter, String queryFilter);

	NPageResult<?> readAllPages(int pageSize, int curPage, String sortName,String sortOrder, String name, List<RoleDetailsAtts> platList,
			List<RoleDetailsAtts> attsList, SearchFilter searchFilter);

	boolean addProject(ProjectManagement post);

	ProjectManagement readProjectM(Integer id);

	boolean deleteProject(Integer id, HttpServletRequest request);



}
