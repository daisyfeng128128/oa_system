package com.baofeng.commons.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.Operator;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.utils.BaseException;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("usersDAO")
public class UsersDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RoleUsers.class);
			detachedCriteria.createAlias("details", "details").add(Restrictions.isNotNull("details"));
			detachedCriteria.addOrder(Order.asc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new BaseException("查询所有分页异常");
		}
	}

	public Operator readOperator(String userId) {
		if (userId != null && userId.trim().length() > 0) {
			Operator operator = (Operator) this.baseDAO.get(Operator.class, Integer.valueOf(userId));
			if (operator != null)
				return operator;
		}
		return null;
	}

	public boolean saveRoleUsers(RoleUsers roleUsers) {
		try {
			if (roleUsers != null && roleUsers.getId() != null && roleUsers.getId().intValue() > 0) {
				this.baseDAO.update(roleUsers);
			} else {
				this.baseDAO.save(roleUsers);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public RoleUsers readRoleUsers(String id) {
		if (id != null && id.trim().length() > 0) {
			RoleUsers users = (RoleUsers) this.baseDAO.get(RoleUsers.class, Integer.valueOf(id));
			if (users != null)
				return users;
		}
		return null;
	}

	public RoleUsers readRoleUsersByUserId(Integer id) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RoleUsers.class);
			detachedCriteria.createAlias("user", "user").add(Restrictions.eq("user.id", Integer.valueOf(id)));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> list = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				RoleUsers users = (RoleUsers) list.get(0);
				return users;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public RoleUsers readRoleUsersBySession(Integer empId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RoleUsers.class);
			detachedCriteria.createAlias("user", "user").add(Restrictions.eq("user.id", Integer.valueOf(empId)));
			List<?> list = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				RoleUsers users = (RoleUsers) list.get(0);
				return users;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean saveRoleUsersBySession(RoleUsers users) {
		try {
			if (users != null && users.getId() != null && users.getId().intValue() > 0) {
				this.baseDAO.mrege(users);
			} else {
				this.baseDAO.save(users);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updatePersonPasswd(Operator user) {
		try {
			if (user != null) {
				this.baseDAO.update(user);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public RoleUsers findRoleUsersByNumber(Integer number) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RoleUsers.class);
			detachedCriteria.createAlias("user", "user").add(Restrictions.eq("user.number", Integer.valueOf(number)));
			List<?> list = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				RoleUsers users = (RoleUsers) list.get(0);
				return users;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}
}
