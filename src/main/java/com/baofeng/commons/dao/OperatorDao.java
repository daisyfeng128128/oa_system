package com.baofeng.commons.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.MenuItem;
import com.baofeng.commons.entity.Operator;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.Operator.Login;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.entity.Roles;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BranchUser;
import com.baofeng.oa.entity.Employee;
import com.baofeng.utils.BaseException;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

@Repository("userDao")
public class OperatorDao {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public Operator findUser(String loginName) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Operator.class);
		detachedCriteria.add(Restrictions.eq("accounts", loginName));
		detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
		List<?> list = this.baseDAO.findAllByCriteria(detachedCriteria);
		if (list.size() > 0) {
			return (Operator) list.get(0);
		}
		return null;
	}

	public Operator findUser(String loginName, String loginPwd) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Operator.class);
			detachedCriteria.add(Restrictions.eq("accounts", loginName));
			detachedCriteria.add(Restrictions.eq("password", loginPwd));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("islogin", Login.YES));
			detachedCriteria.add(Restrictions.or(Restrictions.eq("genrer", Genres.MANAGER), Restrictions.eq("genrer", Genres.SUPERS)));
			List<?> list = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list.size() > 0) {
				return (Operator) list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 功能：查询管理用户
	 * 
	 * @param loginName
	 * @param loginPwd
	 * */
	public Operator findUser(String loginName, Integer branchs, String loginPwd) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Operator.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.eq("accounts", loginName));
			detachedCriteria.add(Restrictions.eq("password", loginPwd));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("islogin", Login.YES));
			detachedCriteria.add(Restrictions.or(Restrictions.eq("genrer", Genres.MANAGER), Restrictions.eq("genrer", Genres.SUPERS)));
			List<?> list = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list.size() > 0) {
				return (Operator) list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public RoleUsers findRoleUsers(String loginName, String loginPwd) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RoleUsers.class);
			detachedCriteria.createAlias("user", "user").add(Restrictions.eq("user.accounts", loginName)).add(Restrictions.eq("user.password", loginPwd))
					.add(Restrictions.eq("user.status", EntityStatus.NORMAL)).add(Restrictions.eq("user.islogin", Login.YES))
					.add(Restrictions.or(Restrictions.eq("user.genrer", Genres.SHIYONG), Restrictions.eq("user.genrer", Genres.ZHENGSHI)));
			List<?> list = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list.size() > 0) {
				RoleUsers user = (RoleUsers) list.get(0);
				if (user != null)
					return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public RoleUsers findRoleUsers(String loginName, String loginPwd, Integer branchs) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RoleUsers.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.createAlias("user", "user").add(Restrictions.eq("user.accounts", loginName)).add(Restrictions.eq("user.password", loginPwd))
					.add(Restrictions.eq("user.status", EntityStatus.NORMAL)).add(Restrictions.eq("user.islogin", Login.YES))
					.add(Restrictions.or(Restrictions.eq("user.genrer", Genres.SHIYONG), Restrictions.eq("user.genrer", Genres.ZHENGSHI)));
			List<?> list = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list.size() > 0) {
				RoleUsers rusers = (RoleUsers) list.get(0);
				if (rusers != null)
					return rusers;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 功能：添加系统管理用户
	 * */
	public boolean saveUser(Operator user) {
		try {
			if (user != null && user.getId() != null && user.getId().intValue() > 0)
				this.baseDAO.mrege(user);
			else
				this.baseDAO.save(user);
			return true;
		} catch (Exception ex) {
			throw new BaseException("添加系统管理会员异常");
		}
	}

	/**
	 * 功能：读取分页数据
	 * */
	@SuppressWarnings("rawtypes")
	public PageResult readAllPages(int pageSize, int currentPage, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Operator.class);
			detachedCriteria.add(Restrictions.eq("delFlag", Integer.valueOf(0)));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.findByPages(detachedCriteria, pageSize, currentPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new BaseException("查询所有分页异常");
		}
	}

	/**
	 * 功能：读取系统管理用户
	 * */
	public Operator readUser(Integer id) {
		Operator user = (Operator) this.baseDAO.get(Operator.class, id);
		return user;
	}

	/**
	 * 功能：删除系统 管理用户
	 * */
	public boolean deleteUser(Integer id) {
		Operator user = readUser(id);
		if (user != null) {
			this.baseDAO.update(user);
			return true;
		}
		return false;
	}

	/**
	 * 功能：读取帐号
	 * */
	public Operator readOperator(Integer id) {
		if (id != null && id.intValue() > 0) {
			Operator operator = (Operator) this.baseDAO.get(Operator.class, id);
			return operator;
		}
		return null;
	}

	/**
	 * 功能：读取顶部菜单
	 * */
	@SuppressWarnings("unchecked")
	public List<MenuItem> readMenuTopList(Operator user) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MenuItem.class);
			detachedCriteria.addOrder(Order.asc("indexs"));
			List<MenuItem> list = (List<MenuItem>) this.baseDAO.findAllByCriteria(detachedCriteria);
			Set<MenuItem> temp = new HashSet<MenuItem>();
			if (list != null && list.size() > 0) {
				for (Iterator<MenuItem> it = list.iterator(); it.hasNext();) {
					MenuItem menu = it.next();
					if (menu.getItem() != null) {
						temp.add(menu.getItem());
					}
				}
			}
			return new ArrayList<MenuItem>(temp);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public MenuItem readMenuItem(Integer itemId) {
		MenuItem item = (MenuItem) this.baseDAO.get(MenuItem.class, itemId.intValue());
		if (item != null)
			return item;
		return null;
	}

	/**
	 * 功能：读取左边菜单
	 * */
	@SuppressWarnings("unchecked")
	public List<MenuItem> readMenuLeftList(Operator user) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MenuItem.class);
			detachedCriteria.addOrder(Order.asc("indexs"));
			List<MenuItem> list = (List<MenuItem>) this.baseDAO.findAllByCriteria(detachedCriteria);
			Set<MenuItem> temp = new HashSet<MenuItem>();
			if (list != null && list.size() > 0) {
				for (Iterator<MenuItem> it = list.iterator(); it.hasNext();) {
					MenuItem menu = it.next();
					if (menu.getItem() != null) {
						temp.add(menu.getItem());
					}
					temp.add(menu);
				}
			}
			return new ArrayList<MenuItem>(temp);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 功能：根据地址读取菜单
	 * */
	@SuppressWarnings("unchecked")
	public MenuItem readUserPagesMenuItem(String itemId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MenuItem.class);
			try {
				detachedCriteria.add(Restrictions.eq("id", Integer.valueOf(itemId)));
			} catch (Exception e) {
				detachedCriteria.add(Restrictions.eq("url", itemId));
			}
			List<MenuItem> list = (List<MenuItem>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				MenuItem menu = list.get(0);
				return menu;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateOperator(Operator user) {
		if (user != null) {
			this.baseDAO.saveOrUpdate(user);
			return true;
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public PageResult readAllPage(Integer pageSize, Integer currentPage, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Roles.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			PageResult page = baseDAO.findByPages(detachedCriteria, pageSize, currentPage);
			return page;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 功能：搜索用户
	 * 
	 * @param act
	 * */
	public List<?> readEmployeeList(String paramers, boolean act, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			if (act)
				detachedCriteria.add(Restrictions.eq("actores", com.baofeng.commons.entity.Operator.Actores.YES));
			else 
				detachedCriteria.add(Restrictions.isNotNull("employeType"));
			try {
				detachedCriteria.add(Restrictions.eq("number", Integer.valueOf(paramers)));
			} catch (Exception e) {
				detachedCriteria.add(Restrictions.or(Restrictions.like("name", paramers.trim(), MatchMode.ANYWHERE),
						Restrictions.like("aliasname", paramers.trim(), MatchMode.ANYWHERE)));
			}
			detachedCriteria.add(Restrictions.ne("genrer", Genres.LIZHI));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			List<?> list = this.baseDAO.findByDetachedCriteria(detachedCriteria, 0, 10);
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 功能：搜索艺人表数
	 * */
	public List<?> readActoresList(String paramers, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Actores.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.or(
					Restrictions.eq("phone", paramers),
					Restrictions.or(Restrictions.eq("qq", paramers),
							Restrictions.or(Restrictions.like("realname", paramers, MatchMode.ANYWHERE), Restrictions.like("aliasname", paramers, MatchMode.ANYWHERE)))));
			detachedCriteria.add(Restrictions.ne("genrer", Genres.LIZHI));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			List<?> list = this.baseDAO.findByDetachedCriteria(detachedCriteria, 0, 10);
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public RoleUsers readRoleUsers(Integer id) {
		if (id != null) {
			RoleUsers ruser = (RoleUsers) this.baseDAO.get(RoleUsers.class, id);
			if (ruser != null)
				return ruser;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<RoleUsers> readRoleUsers(String name, SearchFilter searchFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RoleUsers.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.createAlias("user", "user");
			try {
				detachedCriteria.add(Restrictions.eq("user.number", Integer.valueOf(name)));
			} catch (Exception e) {
				detachedCriteria.add(Restrictions.like("user.accounts", name.trim(), MatchMode.ANYWHERE));
			}
			detachedCriteria.add(Restrictions.ne("user.genrer", Genres.LIZHI));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, searchFilter);
			List<RoleUsers> list = (List<RoleUsers>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public BranchUser findBranchUser(Integer uid, Integer branchs) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BranchUser.class);
			detachedCriteria.createAlias("user", "u").add(Restrictions.eq("u.id", uid));
			detachedCriteria.createAlias("tarBranchs", "t").add(Restrictions.eq("t.id", branchs));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<BranchUser> branchList = (List<BranchUser>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (branchList != null && branchList.size() > 0) {
				BranchUser buser = branchList.get(0);
				return buser;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

}
