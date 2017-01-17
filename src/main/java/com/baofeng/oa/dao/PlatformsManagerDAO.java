package com.baofeng.oa.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.entity.PlatformsChannels;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("platformsManagerDAO")
public class PlatformsManagerDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public PlatformsManager readManager(Integer id) {
		try {
			PlatformsManager manager = (PlatformsManager) this.baseDAO.get(PlatformsManager.class, id);
			return manager;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean savePlatformsManager(PlatformsManager post) {
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

	public PlatformsChannels readPlatformsChannels(Integer channelid) {
		try {
			PlatformsChannels platformsChannels = (PlatformsChannels) this.baseDAO.get(PlatformsChannels.class, channelid);
			return platformsChannels;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deletePlatforms(PlatformsManager platformsManager) {
		try {
			if (platformsManager != null) {
				this.baseDAO.delete(platformsManager);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public NPageResult<?> readAllPages(int pageSize, int currentPage, String sortName, String sortOrder, Integer platid, String fastArg, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManager.class);
			detachedCriteria.createAlias("manager", "manager");
			if (fastArg != null && fastArg.trim().length() > 0) {
				Disjunction disjunction = Restrictions.disjunction();
				try {
					disjunction.add(Restrictions.eq("manager.number", Integer.valueOf(fastArg)));
					disjunction.add(Restrictions.like("manager.realname", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("manager.aliasname", fastArg, MatchMode.ANYWHERE));
				} catch (Exception e) {
					disjunction.add(Restrictions.like("manager.realname", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("manager.aliasname", fastArg, MatchMode.ANYWHERE));
				}
				detachedCriteria.add(disjunction);
			}
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.createAlias("plat", "plat").add(Restrictions.eq("plat.id", platid));
			detachedCriteria.addOrder(Order.desc("manager.number"));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, currentPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsManager> findAllPlatformsManager() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManager.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<PlatformsManager>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean readPlatformsManager(Integer id, Integer platId, Integer channelId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManager.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.createAlias("plat", "plat").add(Restrictions.eq("plat.id", Integer.valueOf(platId)));
			if (channelId != null && channelId.intValue() > 0)
				detachedCriteria.createAlias("channel", "channel").add(Restrictions.eq("channel.id", Integer.valueOf(channelId)));
			detachedCriteria.createAlias("employee", "emp").add(Restrictions.eq("emp.id", Integer.valueOf(id)));
			List<PlatformsManager> list = (List<PlatformsManager>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsManager> readPlatformsManagerByEmpId(Integer empId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManager.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("labour", ActoresLabour.SYSTEM));
			detachedCriteria.createAlias("employee", "emp").add(Restrictions.eq("emp.id", Integer.valueOf(empId)));
			List<PlatformsManager> list = (List<PlatformsManager>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Managers> readPlatformsManagerBeanList(String realname, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Managers.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			try {
				detachedCriteria.add(Restrictions.or(Restrictions.eq("number", Integer.valueOf(realname)), Restrictions.like("realname", realname.trim(), MatchMode.ANYWHERE),
						Restrictions.like("aliasname", realname.trim(), MatchMode.ANYWHERE)));
			} catch (Exception e) {
				detachedCriteria.add(Restrictions.or(Restrictions.like("realname", realname.trim(), MatchMode.ANYWHERE),
						Restrictions.like("aliasname", realname.trim(), MatchMode.ANYWHERE)));
			}
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return (List<Managers>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsManager> readAllPlatformsManager(Integer id, Integer platId, Integer channelId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManager.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			detachedCriteria.createAlias("manager", "manager").add(Restrictions.eq("manager.id", id));
			detachedCriteria.createAlias("plat", "plat").add(Restrictions.eq("plat.id", platId));
			if (channelId != null && channelId.intValue() > 0)
				detachedCriteria.createAlias("channel", "channel").add(Restrictions.eq("channel.id", channelId));
			return (List<PlatformsManager>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<?> findPlatformsManagerBySession(Integer manId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManager.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.createAlias("manager", "m").add(Restrictions.eq("m.id", manId));
			List<?> list = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean savePlatformsManagerBySession(PlatformsManager plam) {
		try {
			if (plam.getId() != null && plam.getId().intValue() > 0) {
				this.baseDAO.mrege(plam);
			} else {
				this.baseDAO.save(plam);
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean deletePlatformsManagerBySession(PlatformsManager plam) {
		try {
			if (plam != null) {
				this.baseDAO.delete(plam);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public PlatformsManager readPlatformsManagerByMId(Integer mid) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManager.class);
			detachedCriteria.createAlias("manager", "m").add(Restrictions.eq("m.id", mid));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<PlatformsManager> mlist = (List<PlatformsManager>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (mlist != null && mlist.size() > 0) {
				return mlist.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
