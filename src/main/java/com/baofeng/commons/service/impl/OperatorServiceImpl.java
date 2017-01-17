package com.baofeng.commons.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.bean.RoleUsersBean;
import com.baofeng.commons.dao.MenuitemDAO;
import com.baofeng.commons.dao.OperatorDao;
import com.baofeng.commons.entity.MenuItem;
import com.baofeng.commons.entity.MenuItem.Dev;
import com.baofeng.commons.entity.MenuItemDetails;
import com.baofeng.commons.entity.Operator;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.RoleDetails;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleDetailsAtts.Enabled;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.entity.Roles;
import com.baofeng.commons.service.IOperatorService;
import com.baofeng.oa.bean.EmployeeBean;
import com.baofeng.oa.dao.BranchUserDAO;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.EmployeType;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.BranchUser;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.utils.MD5;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

@Service("userService")
public class OperatorServiceImpl implements IOperatorService {

	@Autowired
	private OperatorDao userDao;
	@Autowired
	private MenuitemDAO menuitemDAO;
	@Autowired
	private BranchUserDAO branchLoginDAO;
	@Autowired
	private IBranchOfficeService branchOfficeService;

	/**
	 * 功能：账号存在验证
	 * */
	@Override
	public Operator validation(String users) {
		Operator user = this.userDao.findUser(users);
		if (user != null)
			return user;
		return null;
	}

	/**
	 * 功能：登录验证
	 * */
	@Override
	public RoleUsers validation(String loginName, String loginPwd, Integer branchs) {
		RoleUsers users = new RoleUsers();
		if (branchs != null && branchs == -1) {
			Operator user = this.userDao.findUser(loginName, MD5.MD5Encode(loginPwd));
			if (user != null && user.getBranchs() == null || user.getBranchs() == Integer.valueOf(0)) {
				users.setUser(user);
				users.setBranchs(Integer.valueOf(0));
			}
		} else {
			Operator user = this.userDao.findUser(loginName, branchs, MD5.MD5Encode(loginPwd));
			if (user != null) {
				users.setUser(user);
				users.setBranchs(user.getBranchs());
			} else {
				RoleUsers rusers = this.userDao.findRoleUsers(loginName, MD5.MD5Encode(loginPwd), branchs);
				if (rusers != null) {
					users = rusers;
				} else {
					RoleUsers $user = this.userDao.findRoleUsers(loginName, MD5.MD5Encode(loginPwd));
					if ($user != null) {
						BranchUser buser = this.branchLoginDAO.readBranchUser($user.getId(), branchs);
						if (buser != null) {
							users.setId(buser.getUser().getId());
							users.setUser(buser.getUser().getUser());
							users.setBranchs(buser.getTarBranchs().getId());
						}
					}
				}
			}
		}
		return users;
	}

	/**
	 * 功能：展示菜单
	 * */
	@Override
	public List<MenuItem> readMenuItem(RoleUsers user, Integer itemId) {
		List<MenuItem> resultList = new ArrayList<MenuItem>();
		try {
			List<MenuItem> list = this.menuitemDAO.readAllMenuItems();
			if (user != null) {
				if (user.getUser().getGenrer() != Genres.MANAGER && user.getUser().getGenrer() != Genres.SUPERS) {
					RoleUsers roleUsers = this.userDao.readRoleUsers(user.getId());
					List<Roles> rolesList = new ArrayList<Roles>();
					if (roleUsers != null && roleUsers.getBranchs() == user.getBranchs()) {
						rolesList.addAll(roleUsers.getDetails());
					} else {
						BranchUser buser = this.userDao.findBranchUser(user.getId(), user.getBranchs());
						if (buser != null && buser.getDetails() != null && buser.getDetails().size() > 0) {
							rolesList.addAll(buser.getDetails());
						} else {
							if (buser != null && buser.getRole() != null)
								rolesList.add(buser.getRole());
						}
					}
					Map<String, MenuItem> srcHashMap = new HashMap<String, MenuItem>();
					Map<String, MenuItem> descHashMap = new HashMap<String, MenuItem>();
					for (MenuItem item : list) {
						srcHashMap.put(item.getUrl(), item);
					}
					for (Roles roles : rolesList) {
						roles = (Roles) this.userDao.getBaseDAO().get(Roles.class, roles.getId());
						List<RoleDetails> detailsList = roles.getDetails();
						for (RoleDetails $details : detailsList) {
							List<RoleDetailsAtts> btnList = $details.getDetails();
							MenuItem $item = srcHashMap.get($details.getItemUrl());
							if ($item != null && $item.getItem() != null) {
								MenuItem parent = $item.getItem();
								if (!descHashMap.containsKey(Integer.toBinaryString(parent.getId()))) {
									descHashMap.put(Integer.toBinaryString(parent.getId()), parent);
								}
								List<String> $1 = new ArrayList<String>();
								for (RoleDetailsAtts atts : btnList) {
									if (atts.getOpkey().equals("menu") && atts.getViews() == Enabled.NO) {
										$item.setDev(Dev.YES);
									}
									$1.add(atts.getOpkey());
								}
								List<MenuItemDetails> $2t = $item.getDetails();
								for (int i = 0; i < $2t.size(); i++) {
									MenuItemDetails btn = $2t.get(i);
									if (!$1.contains(btn.getOpkey())) {
										$2t.remove(btn);
									}
								}
								$item.setDetails($2t);
								descHashMap.put($details.getItemUrl(), $item);
							}
						}
					}
					resultList.addAll(descHashMap.values());
				} else {
					resultList.addAll(list);
				}
			}
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addUser(Operator user) {
		if (user != null && user.getId() != null && user.getId().intValue() > 0) {
			Operator $operator = this.readOperator(user.getId());
			if ($operator != null) {
				if (user.getPassword().length() != 32) {
					$operator.setPassword(MD5.MD5Encode(user.getPassword()));
				}
				$operator.setPhone(user.getPhone());
				$operator.setEmail(user.getEmail());
				user = $operator;
			}
		} else {
			user.setPassword(MD5.MD5Encode(user.getPassword()));
			user.setCreateDT(new Date());
		}
		return this.userDao.saveUser(user);
	}

	/**
	 * 功能：读取帐号
	 * */
	@Override
	public Operator readOperator(Integer id) {
		return this.userDao.readOperator(id);
	}

	/**
	 * 功能：禁用|启用
	 * */
	@Override
	public boolean disableOperator(Integer id) {
		if (id != null && id.intValue() > 0) {
			Operator operator = this.readOperator(id);
			if (operator != null) {
				this.userDao.saveUser(operator);
				return true;
			}
		}
		return false;
	}

	/**
	 * 功能：删除帐号
	 * */
	@Override
	public boolean deleteOperator(Integer id) {
		if (id != null && id.intValue() > 0) {
			Operator operator = this.readOperator(id);
			if (operator != null) {
				this.userDao.saveUser(operator);
				return true;
			}
		}
		return false;
	}

	/**
	 * 功能：读取系统管理用户
	 * */
	@Override
	public Operator readUser(Integer id) {
		if (id != null && id.intValue() > 0)
			return this.userDao.readUser(id);
		return null;
	}

	/**
	 * 功能：删除系统管理用户
	 * */
	@Override
	public boolean delUser(Integer id) {
		if (id != null && id.intValue() > 0)
			return this.userDao.deleteUser(id);
		return false;
	}

	@Override
	public boolean updateOperator(Operator user) {
		return this.userDao.updateOperator(user);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResult readAllPagesDetails(int rows, int page, int gid) {

		return null;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResult readAllPage(Integer pageSize, Integer currentPage, SearchFilter filter) {
		return this.userDao.readAllPage(pageSize, currentPage, filter);
	}

	/**
	 * 功能：搜索用户
	 * */
	@Override
	public List<EmployeeBean> readEmployeeBeanList(String paramers, boolean actores, boolean emp, boolean act, SearchFilter filter) {
		List<EmployeeBean> resutlList = new ArrayList<EmployeeBean>();
		if (emp) {
			List<?> empList = this.userDao.readEmployeeList(paramers, act, filter);
			if (empList != null && empList.size() > 0) {
				for (Object o : empList) {
					Employee $emp = (Employee) o;
					EmployeeBean bean = new EmployeeBean();
					bean.setId($emp.getId());
					bean.setNum(String.format("%04d", $emp.getNumber()));
					bean.setName($emp.getName());
					bean.setAliasname($emp.getAliasname());
					bean.setLoadTable(Employee.class.getName());
					if ($emp.getDepart() != null) {
						bean.setDepId($emp.getDepart().getId());
						bean.setDepName($emp.getDepart().getName());
					}
					if ($emp.getPosition() != null) {
						bean.setPosId($emp.getPosition().getId());
						bean.setPosName($emp.getPosition().getName());
					}
					if ("".equals($emp.getEmployeType()) & $emp.getActores() == com.baofeng.commons.entity.Operator.Actores.YES) {
						bean.setEmployeType(EmployeType.ACTORES.toString());
					} else {
						bean.setEmployeType($emp.getEmployeType().toString());
					}
					resutlList.add(bean);
				}
			}
		}
		if (actores) {
			List<?> actorsList = this.userDao.readActoresList(paramers, filter);
			if (actorsList != null && actorsList.size() > 0) {
				for (Object $o : actorsList) {
					if ($o instanceof Actores) {
						Actores $act = (Actores) $o;
						EmployeeBean bean = new EmployeeBean();
						bean.setId($act.getId());
						if ($act.getNumber() != null)
							bean.setNum(String.format("%04d", $act.getNumber()));
						else
							bean.setNum("N/A");
						bean.setName($act.getRealname());
						bean.setAliasname($act.getAliasname());
						bean.setLoadTable(Actores.class.getName());
						resutlList.add(bean);
					}
				}
			}
		}
		return resutlList;
	}

	@Override
	public List<RoleUsersBean> readRoleUsers(String name, SearchFilter searchFilter) {
		List<RoleUsersBean> resutlList = new ArrayList<RoleUsersBean>();
		List<?> usersList = this.userDao.readRoleUsers(name, searchFilter);
		if (usersList != null && usersList.size() > 0) {
			BranchOffice branch = null;
			for (int i = 0; i < usersList.size(); i++) {
				Object o = usersList.get(i);
				RoleUsers users = (RoleUsers) o;
				RoleUsersBean bean = new RoleUsersBean();
				bean.setId(users.getId().toString());
				if (branch == null)
					branch = this.branchOfficeService.readBranchOffice(users.getUser().getBranchs());
				if (branch != null)
					bean.setNum(branch.getNumberHead() + String.format("%04d", users.getUser().getNumber()));
				bean.setAccounts(users.getUser().getAccounts());
				resutlList.add(bean);
				if (i > 9)
					break;
			}
			return resutlList;
		}
		return null;
	}
}
