package com.baofeng.commons.service;

import com.baofeng.commons.entity.Operator;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface IUsersService {

	NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter);

	boolean addRoleUsers(String userId, String roleId, Integer branchs);

	Operator readOperator(String userId);

	boolean deleteRoleUsers(String id);

	RoleUsers readRoleUsers(String id);

	boolean updateRoleUsers(String id, String roleId);

	boolean updatePasswd(String id, String pwd);

	boolean readRoleUsersByUId(String userId);

	boolean saveRoleUsersBySession(RoleUsers users);

	boolean updatePersonPasswd(Operator user);

	RoleUsers findRoleUsersBySession(Integer empId);

	RoleUsers findRoleUsersByNumber(Integer number);
}
