package com.baofeng.oa.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsChannels;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

@Repository("platformsDAO")
public class PlatformsDAO {
	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String name, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Platforms.class);
			if (name != null && name.trim().length() > 0)
				detachedCriteria.add(Restrictions.like("platName", "%" + name + "%"));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.asc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean savePlatforms(Platforms poster) {
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

	public Platforms findPlatforms(Integer id) {
		try {
			Platforms platforms = (Platforms) this.baseDAO.get(Platforms.class, id);
			return platforms;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deletePlatforms(Platforms platforms) {
		try {
			if (platforms != null) {
				this.baseDAO.saveOrUpdate(platforms);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public PageResult<?> readPagesPlat(int rows, int page, SearchFilter filter, String queryFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Platforms.class);
			if (queryFilter != null && !queryFilter.trim().equals(""))
				detachedCriteria.add(Restrictions.like("platName", "%" + queryFilter + "%"));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return this.baseDAO.findByPages(detachedCriteria, rows, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public NPageResult<?> readAllPagesPlat(int pageSize, int currentPage, String sortName, String sortOrder, String name, SearchFilter filter, String queryFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Platforms.class);
			if (queryFilter != null && !queryFilter.trim().equals(""))
				detachedCriteria.add(Restrictions.like("platName", "%" + queryFilter + "%"));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, currentPage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PlatformsChannels readChannels(Integer channelsId) {
		try {
			PlatformsChannels channels = (PlatformsChannels) this.baseDAO.get(PlatformsChannels.class, channelsId);
			return channels;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean savePlatformsActores(PlatformsActores platformsActores) {
		try {
			if (platformsActores != null) {
				this.baseDAO.saveOrUpdate(platformsActores);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Platforms> readPlatformsAlls() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Platforms.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<Platforms> platList = (List<Platforms>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (platList != null && platList.size() > 0) {
				return platList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public PlatformsChannels readPlatformsChannels(String platId, String channels) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsChannels.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("channels", channels));
			detachedCriteria.createAlias("plat", "plat").add(Restrictions.eq("plat.id", Integer.valueOf(platId)));
			List<PlatformsChannels> chanList = (List<PlatformsChannels>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (chanList != null && chanList.size() > 0) {
				PlatformsChannels $channels = chanList.get(0);
				return $channels;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Platforms> findAllPlatforms(SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Platforms.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return (List<Platforms>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PageResult<?> readAllPagesSkip(int rows, int page, SearchFilter fil, String filter, List<RoleDetailsAtts> platList) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Platforms.class);
			if (platList != null && platList.size() > 0) {
				Disjunction disjunction = Restrictions.disjunction();
				for (RoleDetailsAtts atts : platList) {
					disjunction.add(Restrictions.eq("id", Integer.valueOf(atts.getOpkey())));
				}
				detachedCriteria.add(disjunction);
			} else {
				detachedCriteria.add(Restrictions.eq("id", -1));
			}
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, fil);
			detachedCriteria.addOrder(Order.desc("createDT"));
			return this.baseDAO.findByPages(detachedCriteria, rows, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Platforms> findAllPlatforms() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Platforms.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<Platforms>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PageResult<?> readPagesAllPlat(int rows, int page,SearchFilter $filter, String filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Platforms.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return this.baseDAO.findByPages(detachedCriteria, rows, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
