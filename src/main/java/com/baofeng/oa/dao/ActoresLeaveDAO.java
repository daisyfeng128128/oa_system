package com.baofeng.oa.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.ActoresLeave;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("ActoresLeaveDAO")
public class ActoresLeaveDAO {

	@Autowired
	private IBaseDAO baseDAO;
	
	public boolean saveactLeave(ActoresLeave post) {
		try {
			if (post != null) {
				this.baseDAO.saveOrUpdate(post);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
		
	}
	 

	@SuppressWarnings("rawtypes")
	public NPageResult readPagesActoresLeave(int pageSize, int curPage,
			String sortName, String sortOrder, SearchFilter filter) {
			try {
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ActoresLeave.class);
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
