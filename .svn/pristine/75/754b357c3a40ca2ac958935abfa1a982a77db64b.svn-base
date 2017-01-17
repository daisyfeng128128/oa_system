package com.baofeng.commons.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.MenuItem;
import com.baofeng.commons.entity.Operator;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.entity.Roles;
import com.baofeng.utils.BaseException;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.PageResult;

@Repository("menuitemDAO")
public class MenuitemDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	/**
	 * 功能：读取所有菜单
	 * 
	 * @param itemId
	 * */
	@SuppressWarnings("unchecked")
	public List<MenuItem> readAllMenuItems() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MenuItem.class);
			return (List<MenuItem>) baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 功能：读取当前用户角色
	 * */
	public RoleUsers readRoleUsers(Operator user) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RoleUsers.class);
			detachedCriteria.createAlias("user", "user").add(Restrictions.eq("user.id", user.getId()));
			List<?> list = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				RoleUsers ruser = (RoleUsers) list.get(0);
				return ruser;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public PageResult readAllPages(int rows, int page, Operator user) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MenuItem.class);
			detachedCriteria.createAlias("item", "item").add(Restrictions.isNotNull("item.id"));
			return baseDAO.findByPages(detachedCriteria, rows, page);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public PageResult readAllPage(int rows, int page, Roles user) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MenuItem.class);
			detachedCriteria.createAlias("item", "item").add(Restrictions.isNotNull("item.id"));
			return baseDAO.findByPages(detachedCriteria, rows, page);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public boolean saveMenuItem(MenuItem menuItem) {
		try {
			if (menuItem != null && menuItem.getId() != null && menuItem.getId().intValue() > 0)
				this.baseDAO.update(menuItem);
			else
				this.baseDAO.save(menuItem);
			return true;
		} catch (Exception ex) {
			throw new BaseException("添加系统菜单异常");
		}
	}

	@SuppressWarnings("unchecked")
	public boolean validateMenu() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MenuItem.class);
			List<MenuItem> list = (List<MenuItem>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return false;
	}

	public MenuItem readMenuitem(Integer id) {
		if (id != null && id.intValue() > 0) {
			MenuItem menu = (MenuItem) this.baseDAO.get(MenuItem.class, id);
			return menu;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<MenuItem> readAllMenu() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MenuItem.class);
			detachedCriteria.createAlias("item", "item").add(Restrictions.isNotNull("item.id"));
			return (List<MenuItem>) baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public MenuItem readMenuItem(String url) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MenuItem.class);
			detachedCriteria.add(Restrictions.eq("url", url));
			List<?> list = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				for (Object o : list) {
					MenuItem menu = (MenuItem) o;
					if (menu.getDetails() != null && menu.getDetails().size() > 0) {
						return menu;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
