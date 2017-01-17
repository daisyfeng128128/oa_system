package com.baofeng.commons.service;

import java.util.List;
import java.util.Map;

import com.baofeng.commons.entity.RoleDetails;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.Roles;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface IRolesService {

	Roles readRoles(Integer id);

	boolean deleteRoles(Integer id);

	boolean addRoles(Integer id, String name, String described, Roles roles, Map<RoleDetails, List<RoleDetailsAtts>> map);

	NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter);

	List<Roles> readRolesAlls(SearchFilter filter);

	boolean saveRolesBySession(Roles roles);

	Roles findRolesBySession(String ruleName);

	Roles findRolesByRolesName(String string, Integer branchs);

}
