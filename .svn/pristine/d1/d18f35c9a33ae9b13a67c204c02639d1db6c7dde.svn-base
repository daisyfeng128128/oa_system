package com.baofeng.oa.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.PlatformsThirdManagerOutlay;
import com.baofeng.utils.IBaseDAO;

@Repository("thirdManagerOutlayDAO")
public class PlatformsThirdManagerOutlayDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public void addPlatformsManagerOutlay(PlatformsThirdManagerOutlay platformsThirdManagerOutlay) {
		if (platformsThirdManagerOutlay != null) {
			this.baseDAO.save(platformsThirdManagerOutlay);
		}

	}

	@SuppressWarnings("unchecked")
	public List<PlatformsThirdManagerOutlay> readAllPlatformsThirdManagerOutlay(String platId, Integer mId, Date beginDate, Date endDate) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsThirdManagerOutlay.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mId));
			detachedCriteria.add(Restrictions.eq("platformsId", platId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			detachedCriteria.addOrder(Order.asc("channel"));
			return (List<PlatformsThirdManagerOutlay>) baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public PlatformsThirdManagerOutlay readPlatformsThirdManagerOutlay(Integer id) {
		try {
			if (id != null && id.intValue() > 0) {
				return (PlatformsThirdManagerOutlay) this.baseDAO.get(PlatformsThirdManagerOutlay.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean update(PlatformsThirdManagerOutlay post) {
		try {
			if (post != null) {
				this.baseDAO.update(post);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean savePlatformsThirdManagerOutlayBySession(PlatformsThirdManagerOutlay outlay) {
		try {
			if (outlay.getId() != null && outlay.getId().intValue() > 0) {
				this.baseDAO.update(outlay);
			} else {
				this.baseDAO.save(outlay);
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public PlatformsThirdManagerOutlay readPlatformsThirdManagerOutlayByTmanId(Integer tmanId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsThirdManagerOutlay.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("thirdManagerId", tmanId == null ? "0" : tmanId.toString()));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				PlatformsThirdManagerOutlay outlay = (PlatformsThirdManagerOutlay) outlayList.get(0);
				if (outlay != null)
					return outlay;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean findValidation(Integer thirdId, Integer monthsId, String platId, String channelId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsThirdManagerOutlay.class);
			detachedCriteria.add(Restrictions.eq("thirdManagerId", thirdId.toString()));
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", monthsId));
			detachedCriteria.add(Restrictions.eq("platformsId", platId.toString()));
			if (channelId != null && channelId.trim().length() > 0)
				detachedCriteria.add(Restrictions.eq("channelId", channelId.toString()));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsThirdManagerOutlay> readPlatformsThirdManagerOutlayList(Integer monthsId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsThirdManagerOutlay.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", monthsId));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return (List<PlatformsThirdManagerOutlay>) outlayList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public PlatformsThirdManagerOutlay readPlatformsThirdManagerOutlayByTid(Integer tmanId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsThirdManagerOutlay.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("thirdManagerId", tmanId == null ? "0" : tmanId.toString()));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				PlatformsThirdManagerOutlay outlay = (PlatformsThirdManagerOutlay) outlayList.get(0);
				if (outlay != null)
					return outlay;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsThirdManagerOutlay> readPlatformsThirdManagerOutlayByList(Integer tmanId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsThirdManagerOutlay.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("thirdManagerId", tmanId == null ? "0" : tmanId.toString()));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			List<PlatformsThirdManagerOutlay> outlayList = (List<PlatformsThirdManagerOutlay>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return outlayList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}
}
