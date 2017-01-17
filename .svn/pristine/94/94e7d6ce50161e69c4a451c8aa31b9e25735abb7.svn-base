package com.baofeng.commons.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.MonitorLog;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("monitorLogDAO")
public class MonitorLogDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public boolean saveMonitorLog(MonitorLog mlog) {
		try {
			if (mlog != null && mlog.getId() != null && mlog.getId().intValue() > 0)
				this.baseDAO.mrege(mlog);
			else
				this.baseDAO.save(mlog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readPagesLog(int pageSize, int curPage, String sortName, String sortOrder, Integer type, String fastArg, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MonitorLog.class);
			detachedCriteria.add(Restrictions.eq("type", type));
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
