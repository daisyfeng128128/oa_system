package com.baofeng.commons.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.RoleDetails;
import com.baofeng.commons.entity.Roles;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("rolesDAO")
public class RolesDAO {

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
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Roles.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Roles readRoles(Integer id) {
		Roles roles = (Roles) this.baseDAO.get(Roles.class, id);
		return roles;
	}

	public boolean saveRoles(Roles roles) {
		try {
			if (roles != null && roles.getId() != null && roles.getId().intValue() > 0) {
				this.baseDAO.update(roles);
			} else {
				this.baseDAO.save(roles);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void saveRoleDetails(RoleDetails rd) {
		try {
			if (rd != null) {
				this.baseDAO.saveOrUpdate(rd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<RoleDetails> readRolesDetailsByRid(Integer rid) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RoleDetails.class);
			detachedCriteria.createAlias("role", "role").add(Restrictions.eq("role.id", rid));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			return (List<RoleDetails>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteRolesDetails(RoleDetails roleDetails) {
		try {
			if (roleDetails != null) {
				this.baseDAO.delete(roleDetails);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Roles> readRolesAlls(SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Roles.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return (List<Roles>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Roles readRolesBySession(String ruleName) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Roles.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("name", ruleName));
			List<?> roleList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (roleList != null && roleList.size() > 0) {
				Roles roles = (Roles) roleList.get(0);
				return roles;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean saveRolesBySession(Roles roles) {
		try {
			if (roles.getId() != null && roles.getId().intValue() > 0) {
				this.baseDAO.update(roles);
			} else {
				this.baseDAO.save(roles);
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public Roles findRolesByRolesName(String name, Integer branchs) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Roles.class);
			detachedCriteria.add(Restrictions.eq("name", name));
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<?> roleList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (roleList != null && roleList.size() > 0) {
				Roles roles = (Roles) roleList.get(0);
				return roles;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
