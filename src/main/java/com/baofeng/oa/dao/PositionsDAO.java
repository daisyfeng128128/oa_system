package com.baofeng.oa.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.Positions;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;



@Repository("positionsDAO")
public class PositionsDAO {
	
	@Autowired
	private IBaseDAO baseDAO;

	public boolean savePositions(Positions poster) {
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
 
	public Positions readPositions(Integer id) {
		try {
			Positions positions = (Positions) this.baseDAO.get(Positions.class, id);
			return positions;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

	public boolean deletePositions(Positions positions) {
		try {
			if (positions != null) {
				this.baseDAO.saveOrUpdate(positions);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	
	 
	@SuppressWarnings("rawtypes")
	public NPageResult readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String name, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Positions.class);
			if (name != null && name.trim().length() > 0)
				detachedCriteria.add(Restrictions.like("name", "%" + name + "%"));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
