package com.baofeng.oa.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.MenuItem;
import com.baofeng.commons.entity.MenuItemDetails;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

@Repository("menusDAO")
public class MenusDAO {
	
	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readAllPages(int pageSize, int curPage, String sortName, String sortOrder) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MenuItem.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public PageResult readAllPagesSkip(int rows, int page, SearchFilter filter, String queryFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MenuItem.class);
			if (queryFilter != null && !queryFilter.trim().equals(""))
				detachedCriteria.add(Restrictions.like("name", "%" + queryFilter + "%"));
			detachedCriteria.add(Restrictions.isNull("item"));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return this.baseDAO.findByPages(detachedCriteria, rows, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public MenuItem readMenu(Integer id) {
		try {
			if(id != null && id.intValue()>0){
				return (MenuItem)this.baseDAO.get(MenuItem.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean saveMenuItem(MenuItem poster) {
		try {
			if (poster != null) {
				this.baseDAO.saveOrUpdate(poster);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean saveMenuItemDetails(MenuItemDetails menuItemDetails) {
		try {
			if(menuItemDetails != null) {
				this.baseDAO.saveOrUpdate(menuItemDetails);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean deleteMenuItemDetails(MenuItemDetails details) {
		if(details != null){
			this.baseDAO.delete(details);
			return true;
		}
		return false;
	}

	public boolean deleteMenus(MenuItem menu) {
		try {
			if(menu != null){
				this.baseDAO.delete(menu);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	
	
}
