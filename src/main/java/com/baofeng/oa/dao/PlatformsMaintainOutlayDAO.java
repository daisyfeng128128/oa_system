package com.baofeng.oa.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.PlatformsMaintainOutlay;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.SearchFilter;

@Repository("maintainOutlayDAO")
public class PlatformsMaintainOutlayDAO {
	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsMaintainOutlay> readAllPlatformsMaintainOutlay(String platId, Integer mId, Date date1, Date date2, SearchFilter searchFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMaintainOutlay.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mId));
			detachedCriteria.add(Restrictions.eq("platformsId", platId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, searchFilter);
			return (List<PlatformsMaintainOutlay>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean savePlatformsMaintainOutlay(PlatformsMaintainOutlay post) {
		try {
			if (post != null && post.getId() != null && post.getId().intValue() > 0) {
				this.baseDAO.update(post);
			} else {
				this.baseDAO.save(post);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public PlatformsMaintainOutlay readPlatformsMaintainOutlay(Integer id) {
		try {
			if (id != null && id.intValue() > 0) {
				return (PlatformsMaintainOutlay) this.baseDAO.get(PlatformsMaintainOutlay.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean deletePlatformsMaintainOutlay(PlatformsMaintainOutlay platformsMaintainOutlay) {
		if (platformsMaintainOutlay != null) {
			this.baseDAO.delete(platformsMaintainOutlay);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsMaintainOutlay> readPlatformsMaintainOutlayList(Integer monthsId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMaintainOutlay.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", monthsId));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return (List<PlatformsMaintainOutlay>) outlayList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public PlatformsMonthsOutlay readPlatformsMonthsOutlay(Integer id) {
		PlatformsMonthsOutlay outlay = (PlatformsMonthsOutlay) this.baseDAO.get(PlatformsMonthsOutlay.class, id);
		return outlay;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsMaintainOutlay> readAllPlatformsMaintainOutlayByDate(Integer monthid) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMaintainOutlay.class);
			detachedCriteria.createAlias("months", "months").add(Restrictions.eq("months.id", monthid));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<PlatformsMaintainOutlay> outlayList = (List<PlatformsMaintainOutlay>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return  outlayList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

}
