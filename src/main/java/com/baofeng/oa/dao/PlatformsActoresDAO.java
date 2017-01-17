package com.baofeng.oa.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsChannels;
import com.baofeng.oa.entity.PlatformsOfflineIncome;
import com.baofeng.oa.entity.PlatformsOnlineIncome;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

@Repository("platformsActoresDAO")
public class PlatformsActoresDAO {
	@Autowired
	private IBaseDAO baseDAO;

	public PageResult<?> readPagesChannel(int rows, int page,
			SearchFilter $filter, String queryFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(PlatformsChannels.class);
			if (queryFilter != null && !queryFilter.trim().equals(""))
				detachedCriteria.add(Restrictions.like("channels", "%"
						+ queryFilter + "%"));
			detachedCriteria
					.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return this.baseDAO.findByPages(detachedCriteria, rows, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PageResult<?> readPagesEmp(int rows, int page, SearchFilter $filter,
			String queryFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(Employee.class);
			if (queryFilter != null && !queryFilter.trim().equals(""))
				detachedCriteria.add(Restrictions.like("name", "%"
						+ queryFilter + "%"));
			detachedCriteria
					.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return this.baseDAO.findByPages(detachedCriteria, rows, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveActores(Actores post) {
		try {
			if (post != null && post.getId() != null
					&& post.getId().intValue() > 0) {
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

	public Platforms readPlatforms(Integer id) {
		try {
			return (Platforms) this.baseDAO.get(Platforms.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Actores readActores(Integer id) {
		try {
			return (Actores) this.baseDAO.get(Actores.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("rawtypes")
	public NPageResult readAllPages(int pageSize, int currentPage,
			String sortName, String sortOrder, Integer platid, LineGenres type,
			Genres genrer, String name, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(Actores.class);
			if (type != null)
				detachedCriteria.add(Restrictions.eq("genre", type));
			if (genrer != null)
				detachedCriteria.add(Restrictions.eq("genrer", genrer));
			if (name != null && name.trim().length() > 0)
				detachedCriteria.add(Restrictions
						.like("name", "%" + name + "%"));
			detachedCriteria
					.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO
					.NfindByPages(detachedCriteria, pageSize, currentPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public PlatformsActores readPlatformsActores(Integer id) {
		try {
			return (PlatformsActores) this.baseDAO.get(PlatformsActores.class,
					id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean savePlatformsActores(PlatformsActores platformsActores) {
		try {
			if (platformsActores != null && platformsActores.getId() != null
					&& platformsActores.getId().intValue() > 0) {
				this.baseDAO.update(platformsActores);
			} else {
				this.baseDAO.save(platformsActores);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readPagesPlatformActores(int pageSize, int curPage,
			String sortName, String sortOrder, Integer platid, String fastArg,
			SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(PlatformsActores.class);
			detachedCriteria
					.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.createAlias("plat", "plat").add(
					Restrictions.eq("plat.id", platid));
			detachedCriteria.createAlias("actores", "a");
			detachedCriteria.addOrder(Order.desc("a.number"));
			if (fastArg != null && fastArg.trim().length() > 0) {
				Disjunction disjunction = Restrictions.disjunction();
				try {
					disjunction.add(Restrictions.eq("a.number",
							Integer.valueOf(fastArg)));
					disjunction.add(Restrictions.like("a.realname", fastArg,
							MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("a.aliasname", fastArg,
							MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("a.phone", fastArg,
							MatchMode.ANYWHERE));
				} catch (Exception e) {
					disjunction.add(Restrictions.like("a.realname", fastArg,
							MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("a.aliasname", fastArg,
							MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("a.phone", fastArg,
							MatchMode.END));
				}
				detachedCriteria.add(disjunction);
			}
			detachedCriteria.addOrder(Order.desc("channel"));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public List findAllPlatformsActores(LineGenres genre) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(PlatformsActores.class);
			detachedCriteria
					.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.createAlias("actores", "actores").add(
					Restrictions.eq("actores.genre", genre));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public PlatformsActores readPlatformsActores(Integer platId,
			Integer channelsId, Integer actId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(PlatformsActores.class);
			detachedCriteria
					.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.createAlias("plat", "plat").add(
					Restrictions.eq("plat.id", platId));
			if (channelsId != null && channelsId.intValue() > 0)
				detachedCriteria.createAlias("channel", "chan").add(
						Restrictions.eq("chan.id", channelsId));
			detachedCriteria.createAlias("actores", "act").add(
					Restrictions.eq("act.id", actId));
			List<?> list = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				PlatformsActores platActores = (PlatformsActores) list.get(0);
				return platActores;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public Actores readActoresByEmployee(Integer empId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(Actores.class);
			detachedCriteria
					.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions
					.eq("labour", ActoresLabour.SYSTEM));
			detachedCriteria.createAlias("employee", "emp").add(
					Restrictions.eq("emp.id", empId));
			List<?> list = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				Actores act = (Actores) list.get(0);
				return act;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public PlatformsOnlineIncome readPlatformsActoresByPactToOnline(
			Integer pactId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(PlatformsOnlineIncome.class);
			detachedCriteria
					.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("pactId", pactId == null ? "0"
					: pactId.toString()));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO
					.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				PlatformsOnlineIncome outlay = (PlatformsOnlineIncome) outlayList
						.get(0);
				if (outlay != null)
					return outlay;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public PlatformsOfflineIncome readPlatformsActoresByPactToOffline(
			Integer pactId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(PlatformsOfflineIncome.class);
			detachedCriteria
					.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("pactId", pactId == null ? "0"
					: pactId.toString()));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO
					.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				PlatformsOfflineIncome outlay = (PlatformsOfflineIncome) outlayList
						.get(0);
				if (outlay != null)
					return outlay;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean savePlatformsOnlineIncome(PlatformsOnlineIncome online) {
		try {
			if (online.getId() != null && online.getId().intValue() > 0) {
				this.baseDAO.update(online);
			} else {
				this.baseDAO.save(online);
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean savePlatformsOfflineIncome(PlatformsOfflineIncome offline) {
		try {
			if (offline.getId() != null && offline.getId().intValue() > 0) {
				this.baseDAO.update(offline);
			} else {
				this.baseDAO.save(offline);
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Actores> readActoresByEmpId(Integer empId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Actores.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("labour", ActoresLabour.SYSTEM));
			detachedCriteria.createAlias("employee", "emp").add(Restrictions.eq("emp.id", empId));
			List<Actores> list = (List<Actores>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean deletePlatformsActores(PlatformsActores platformsActores) {
		try {
			this.baseDAO.delete(platformsActores);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsActores> findPlatformsActoresList(Integer actId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsActores.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.createAlias("actores", "a").add(Restrictions.eq("a.id", actId));
			List<PlatformsActores> list = (List<PlatformsActores>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean savePlatformsActoresBySession(PlatformsActores pact) {
		try {
			if (pact != null && pact.getId() != null
					&& pact.getId().intValue() > 0) {
				this.baseDAO.update(pact);
			} else {
				this.baseDAO.save(pact);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public Actores readActoresBySession(Integer empId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(Actores.class);
			detachedCriteria
					.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions
					.eq("labour", ActoresLabour.SYSTEM));
			detachedCriteria.createAlias("employee", "em").add(
					Restrictions.eq("em.id", empId));
			List<Actores> list = (List<Actores>) this.baseDAO
					.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				Actores act = (Actores) list.get(0);
				return act;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public PlatformsActores readPlatformsActoresBySession(Integer actId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(PlatformsActores.class);
			detachedCriteria
					.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.createAlias("actores", "a").add(
					Restrictions.eq("a.id", actId));
			List<PlatformsActores> list = (List<PlatformsActores>) this.baseDAO
					.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				PlatformsActores act = (PlatformsActores) list.get(0);
				return act;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean deletePlatformsActoresBySession(PlatformsActores pact) {
		try {
			if (pact != null) {
				this.baseDAO.delete(pact);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsActores> findAllPlatformsActores() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(PlatformsActores.class);
			detachedCriteria
					.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<PlatformsActores>) this.baseDAO
					.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String readAllPlatNames(Integer id) {
		try {
			String querString = "select GROUP_CONCAT(p.platName) from platforms p where p.id IN (select pa.plat_id from platformsActores pa where pa.act_id =?)";
			Query query = this.baseDAO.getCurrentSession().createSQLQuery(
					querString);
			query.setInteger(0, id);
			Object v = query.uniqueResult();
			if (v != null) {
				return (String) v;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsActores> readPlatformsActoresByActoresId(
			Integer anchorsId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(PlatformsActores.class);
			detachedCriteria
					.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.createAlias("actores", "actores").add(
					Restrictions.eq("actores.id", anchorsId));
			detachedCriteria.add(Restrictions.eq("mainPlatform", 1));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<PlatformsActores>) this.baseDAO
					.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public PlatformsActores readPlatformsActoresByNUmber(Integer numbers) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsActores.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("number", numbers));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<PlatformsActores> list =  (List<PlatformsActores>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if(list != null && list.size() > 0)
			  return list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
