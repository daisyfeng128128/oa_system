package com.baofeng.commons.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.bean.RoleUsersBean;
import com.baofeng.commons.dao.RolesDAO;
import com.baofeng.commons.dao.UsersDAO;
import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.Operator;
import com.baofeng.commons.entity.Operator.Login;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.entity.Roles;
import com.baofeng.commons.service.IUsersService;
import com.baofeng.oa.entity.BranchUser;
import com.baofeng.oa.service.IBranchUserService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.MD5;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Service("usersService")
public class UsersServiceImpl implements IUsersService {

	@Autowired
	private RolesDAO rolesDAO;
	@Autowired
	private UsersDAO usersDAO;
	@Autowired
	private IBranchUserService branchUserService;

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter) {
		NPageResult page = this.usersDAO.readAllPages(pageSize, curPage, sortName, sortOrder, filter);
		List<RoleUsersBean> $rows = new ArrayList<RoleUsersBean>();
		if (page.getData() != null && page.getData().size() > 0) {
			for (Object o : page.getData()) {
				RoleUsers users = (RoleUsers) o;
				RoleUsersBean bean = new RoleUsersBean();
				bean.setId(users.getId() + "");
				bean.setAccounts(users.getUser().getAccounts());
				bean.setPhone(users.getUser().getPhone());
				bean.setEmail(users.getUser().getEmail());
				if (users.getDetails().size() > 0) {
					StringBuilder roleName = new StringBuilder();
					for (Roles role : users.getDetails()) {
						roleName.append(role.getName()).append(",");
					}
					if (roleName.length() > 0)
						roleName.deleteCharAt(roleName.length() - 1);
					bean.setRoles(roleName.toString());
				}
				bean.setCreateDT(users.getCreateDT());
				bean.setStatus(users.getUser().getStatus() == EntityStatus.NORMAL ? "正常" : "删除");
				$rows.add(bean);
			}
			page.setData($rows);
		}
		return page;
	}

	@Override
	public boolean addRoleUsers(String userId, String roleId, Integer branchs) {
		RoleUsers ruser = new RoleUsers();
		Operator user = this.readOperator(userId);
		if (user != null) {
			user.setPassword(MD5.MD5Encode(Constants.hexToString("61313233343536")));
			user.setIslogin(Login.YES);
			ruser.setUser(user);
			ruser.setBranchs(branchs);
			String[] $roles = roleId.split(",");
			for (String $ids : $roles) {
				if ($ids != null && $ids.trim().length() > 0) {
					Roles roles = this.rolesDAO.readRoles(Integer.valueOf($ids));
					if (roles != null) {
						ruser.getDetails().add(roles);
					}
				}
			}
			return this.usersDAO.saveRoleUsers(ruser);
		}
		return false;
	}

	@Override
	public Operator readOperator(String userId) {
		return this.usersDAO.readOperator(userId);
	}

	@Override
	public boolean deleteRoleUsers(String id) {
		try {
			RoleUsers users = this.readRoleUsers(id);
			if (users != null) {
				BranchUser  branchUser = this.branchUserService.readBranchLoginbyNum(users.getUser().getNumber());
				if (branchUser != null) {
					this.branchUserService.deletebyNum(branchUser.getUser().getUser().getNumber(),users);
				}else{
					Operator operator = users.getUser();
					users.setUser(null);
					users.getDetails().clear();
					operator.setIslogin(Login.NO);
					this.usersDAO.saveRoleUsers(users);
					this.usersDAO.getBaseDAO().update(operator);
					this.usersDAO.getBaseDAO().delete(users);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public RoleUsers readRoleUsers(String id) {
		return this.usersDAO.readRoleUsers(id);
	}

	@Override
	public boolean updateRoleUsers(String id, String roleId) {
		try {
			RoleUsers users = this.readRoleUsers(id);
			if (users != null) {
				users.getDetails().clear();
				String[] $roles = roleId.split(",");
				for (String $ids : $roles) {
					if ($ids != null && $ids.trim().length() > 0) {
						Roles roles = this.rolesDAO.readRoles(Integer.valueOf($ids));
						if (roles != null) {
							users.getDetails().add(roles);
						}
					}
				}
				this.usersDAO.saveRoleUsers(users);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updatePasswd(String id, String pwd) {
		try {
			RoleUsers users = this.readRoleUsers(id);
			if (users != null) {
				Operator operator = users.getUser();
				operator.setPassword(MD5.MD5Encode(pwd));
				this.usersDAO.getBaseDAO().update(operator);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean readRoleUsersByUId(String userId) {
		if (userId != null && userId.trim().length() > 0) {
			RoleUsers users = this.usersDAO.readRoleUsersByUserId(Integer.valueOf(userId));
			if (users != null)
				return true;
		}
		return false;
	}

	@Override
	public RoleUsers findRoleUsersBySession(Integer empId) {
		return this.usersDAO.readRoleUsersBySession(empId);
	}

	@Override
	public boolean saveRoleUsersBySession(RoleUsers users) {
		return this.usersDAO.saveRoleUsersBySession(users);
	}

	@Override
	public boolean updatePersonPasswd(Operator user) {
		Operator $user = this.usersDAO.readOperator(user.getId().toString());
		$user.setPassword(user.getPassword());
		return this.usersDAO.updatePersonPasswd($user);
	}

	@Override
	public RoleUsers findRoleUsersByNumber(Integer number) {
		if (number != null && number.intValue() > 0) {
			return this.usersDAO.findRoleUsersByNumber(number);
		}
		return null;
	}

}
