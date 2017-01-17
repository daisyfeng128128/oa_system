package com.baofeng.oa.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.BaseEnums.Audit;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.PlatformsManagerOutlay;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.SearchFilter;

@Repository("managerOutlayDAO")
public class PlatformsManagerOutlayDAO {
	@Autowired
	private IBaseDAO baseDAO;

	public void addPlatformsManagerOutlay(PlatformsManagerOutlay platformsManagerOutlay) {
		if (platformsManagerOutlay != null) {
			this.baseDAO.save(platformsManagerOutlay);
		}
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsManagerOutlay> readAllPlatformsManagerOutlay(String platId, Integer mId, Date beginDate, Date endDate, String online, SearchFilter searchFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManagerOutlay.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mId));
			detachedCriteria.add(Restrictions.eq("platformsId", platId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("genre", Enum.valueOf(LineGenres.class, online)));
			detachedCriteria.addOrder(Order.desc("channel"));
			detachedCriteria.addOrder(Order.desc("number"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, searchFilter);
			return (List<PlatformsManagerOutlay>) baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public PlatformsManagerOutlay readPlatformsManagerOutlay(Integer id) {
		try {
			if (id != null && id.intValue() > 0) {
				return (PlatformsManagerOutlay) this.baseDAO.get(PlatformsManagerOutlay.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean update(PlatformsManagerOutlay post) {
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

	public boolean savePlatformsManagerOutlayBySession(PlatformsManagerOutlay outlay) {
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

	public PlatformsManagerOutlay findPlatformsManagerOutlay(Integer manId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManagerOutlay.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("managerId", manId == null ? "0" : manId.toString()));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				PlatformsManagerOutlay outlay = (PlatformsManagerOutlay) outlayList.get(0);
				if (outlay != null)
					return outlay;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean findValidation(Integer manId, Integer monthsId, String platId, String channelId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManagerOutlay.class);
			detachedCriteria.add(Restrictions.eq("managerId", manId.toString()));
			detachedCriteria.add(Restrictions.eq("platformsId", platId.toString()));
			if (channelId != null && channelId.trim().length() > 0)
				detachedCriteria.add(Restrictions.eq("channelId", channelId.toString()));
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", monthsId));
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
	public List<PlatformsManagerOutlay> readPlatformsManagerOutlayList(Integer monthsId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManagerOutlay.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", monthsId));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return (List<PlatformsManagerOutlay>) outlayList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<?> readPlatformsManagerOutlayListBySession(Integer manaId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManagerOutlay.class);
			detachedCriteria.add(Restrictions.eq("managerId", manaId.toString()));
			detachedCriteria.add(Restrictions.eq("audit", Audit.WRITE));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return (List<PlatformsManagerOutlay>) outlayList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public PlatformsManagerOutlay readPlatformsManagerOutlayListByMid(Integer manaId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManagerOutlay.class);
			detachedCriteria.add(Restrictions.eq("managerId", manaId.toString()));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<PlatformsManagerOutlay> outlayList = (List<PlatformsManagerOutlay>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return outlayList.get(0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean deletePlatformsManagers(PlatformsManagerOutlay outlay) {
		try {
			if (outlay != null) {
				this.baseDAO.delete(outlay);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean findActValidation(String name, Integer mid, String platId, String channelId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManagerOutlay.class);
			detachedCriteria.add(Restrictions.eq("name", name));
			detachedCriteria.add(Restrictions.eq("platformsId", platId.toString()));
			if (channelId != null && channelId.trim().length() > 0)
				detachedCriteria.add(Restrictions.eq("channelId", channelId.toString()));
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mid));
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
	public List<PlatformsManagerOutlay> findPlatformsManagerOutlayList(Integer mid, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManagerOutlay.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", mid));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			List<PlatformsManagerOutlay> outlayList = (List<PlatformsManagerOutlay>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return outlayList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public PlatformsManagerOutlay readPlatformsManagerOutlay(Integer number, Integer branchs, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManagerOutlay.class);
			detachedCriteria.add(Restrictions.eq("number", number.intValue()));
			detachedCriteria.add(Restrictions.eq("branchs", branchs.intValue()));
			detachedCriteria.add(Restrictions.ge("createDT", date1));
			detachedCriteria.add(Restrictions.le("createDT", date2));
			List<PlatformsManagerOutlay> outlayList = (List<PlatformsManagerOutlay>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return outlayList.get(0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}
}
