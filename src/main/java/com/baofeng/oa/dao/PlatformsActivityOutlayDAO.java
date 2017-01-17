package com.baofeng.oa.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.PlatformsActivityOutlay;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.SearchFilter;

@Repository("activityOutlayDAO")
public class PlatformsActivityOutlayDAO {

	@Autowired
	private IBaseDAO baseDAO;

	@SuppressWarnings("unchecked")
	public List<PlatformsActivityOutlay> readAllPlatformsActivityOutlay(String platId, Integer mId, Date date1, Date date2, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsActivityOutlay.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mId));
			detachedCriteria.add(Restrictions.eq("platformsId", platId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return (List<PlatformsActivityOutlay>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean savePlatformsActivityOutlay(PlatformsActivityOutlay post) {
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

	public PlatformsActivityOutlay readPlatformsActivityOutlay(Integer id) {
		try {
			if (id != null && id.intValue() > 0) {
				return (PlatformsActivityOutlay) this.baseDAO.get(PlatformsActivityOutlay.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;

	}

	public boolean deletePlatformsActivityOutlay(PlatformsActivityOutlay outlay) {
		try {
			if (outlay != null) {
				this.baseDAO.delete(outlay);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsActivityOutlay> readPlatformsActivityOutlayList(Integer monthsId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsActivityOutlay.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", monthsId));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return (List<PlatformsActivityOutlay>) outlayList;
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

	public PlatformsActivityOutlay findActivityOutlayByPlatId(String text, Integer platId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsActivityOutlay.class);
			detachedCriteria.add(Restrictions.eq("activity", text));
			detachedCriteria.add(Restrictions.eq("platformsId", platId.toString()));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				PlatformsActivityOutlay outlay = (PlatformsActivityOutlay) outlayList.get(0);
				return outlay;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}
}
