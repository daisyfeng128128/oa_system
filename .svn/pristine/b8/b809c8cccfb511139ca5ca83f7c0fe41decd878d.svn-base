package com.baofeng.commons.service;

import java.util.List;

import com.baofeng.commons.bean.RoleUsersBean;
import com.baofeng.commons.entity.MenuItem;
import com.baofeng.commons.entity.Operator;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.EmployeeBean;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

public interface IOperatorService {

	Operator validation(String user);

	/** 登录验证 */
	RoleUsers validation(String loginName, String loginPwd, Integer branchs);

	/** 展示菜单 */
	List<MenuItem> readMenuItem(RoleUsers user, Integer itemId);

	/** 添加管理用户 */
	boolean addUser(Operator user);

	/** 读取系统管理用户 */
	Operator readUser(Integer id);

	/** 删除系统管理用户 */
	boolean delUser(Integer id);

	Operator readOperator(Integer id);

	PageResult<?> readAllPagesDetails(int rows, int page, int gid);

	boolean disableOperator(Integer id);

	boolean deleteOperator(Integer id);

	boolean updateOperator(Operator user);

	PageResult<?> readAllPage(Integer pageSize, Integer currentPage, SearchFilter filter);

	List<EmployeeBean> readEmployeeBeanList(String paramers, boolean actores, boolean emp, boolean act, SearchFilter filter);

	List<RoleUsersBean> readRoleUsers(String name, SearchFilter searchFilter);

}
