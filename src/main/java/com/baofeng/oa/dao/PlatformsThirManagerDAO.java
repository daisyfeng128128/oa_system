package com.baofeng.oa.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.PlatformsChannels;
import com.baofeng.oa.entity.PlatformsThirdManager;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

@Repository("platformsThirManagerDAO")
public class PlatformsThirManagerDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public NPageResult<?> readAllPages(int pageSize, int currentPage, String sortName, String sortOrder, String majia, String realname, Integer channelid, Integer platid, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsThirdManager.class);
			if (majia != null && majia.trim().length() > 0)
				detachedCriteria.add(Restrictions.like("majia", "%" + majia + "%"));
			if (realname != null && realname.trim().length() > 0)
				detachedCriteria.add(Restrictions.like("realname", "%" + realname + "%"));
			if (platid != null && platid.intValue() > 0)
				detachedCriteria.createAlias("plat", "plat").add(Restrictions.eq("plat.id", platid));
			if (channelid != null && channelid.intValue() > 0)
				detachedCriteria.createAlias("channel", "channel").add(Restrictions.eq("channel.id", channelid));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, currentPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public PlatformsThirdManager readThirdManager(Integer id) {
		try {
			PlatformsThirdManager platformsThirdManager = (PlatformsThirdManager) this.baseDAO.get(PlatformsThirdManager.class, id);
			return platformsThirdManager;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean save(PlatformsThirdManager thirdManager) {
		try {
			if(thirdManager != null){
				this.baseDAO.saveOrUpdate(thirdManager);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
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

	public PageResult<?> readPagesChannel(int rows, int page, Integer platid, SearchFilter filter, String queryFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsChannels.class);
			if (queryFilter != null && !queryFilter.trim().equals(""))
				detachedCriteria.add(Restrictions.like("channels", "%" + queryFilter + "%"));
			if (platid != null && platid.intValue()>0)
				detachedCriteria.createAlias("plat", "plat").add(Restrictions.eq("plat.id", platid));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return this.baseDAO.findByPages(detachedCriteria, rows, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<?> findAllPlatformsThirdManager() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsThirdManager.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<?> readPlatformsManagerBySession(Integer tmanId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsThirdManager.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
/* */			detachedCriteria.createAlias("thirdManager", "m").add(Restrictions.eq("m.id", tmanId));
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

}
