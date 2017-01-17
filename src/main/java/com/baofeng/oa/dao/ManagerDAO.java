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
import com.baofeng.commons.entity.Operator;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.FinSalaries;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.entity.PlatformsChannels;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("managerDAO")
public class ManagerDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public Managers readManager(Integer id) {
		try {
			Managers manager = (Managers) this.baseDAO.get(Managers.class, id);
			return manager;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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

	public boolean deletePlatforms(Managers Managers) {
		try {
			if (Managers != null) {
				this.baseDAO.delete(Managers);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readAllPages(int pageSize, int currentPage, String sortName, String sortOrder, String genrer, SearchFilter filter, String genre, String fastArg) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Managers.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			if (genrer != null) {
				detachedCriteria.add(Restrictions.eq("genrer", Genres.LIZHI));
			} else
				detachedCriteria.add(Restrictions.ne("genrer", Genres.LIZHI));

			if (fastArg != null && fastArg.trim().length() > 0) {
				Disjunction disjunction = Restrictions.disjunction();
				try {
					disjunction.add(Restrictions.eq("number", Integer.valueOf(fastArg)));
					disjunction.add(Restrictions.like("realname", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("aliasname", fastArg, MatchMode.ANYWHERE));
				} catch (Exception e) {
					disjunction.add(Restrictions.like("realname", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("aliasname", fastArg, MatchMode.ANYWHERE));
				}
				detachedCriteria.add(disjunction);
			}
			if (genre != null && genre.trim().length() > 0) {
				detachedCriteria.add(Restrictions.eq("genre", Enum.valueOf(LineGenres.class, genre)));
			}

			detachedCriteria.addOrder(Order.desc("number"));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, currentPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String genrer, SearchFilter filter, List<RoleDetailsAtts> platList,
			Operator user, String genre, String fastArg) {
		try {
			StringBuilder conHql = new StringBuilder();
			String con = String.valueOf("pm.plat.id = %s");
			if (platList != null && platList.size() > 0) {
				for (RoleDetailsAtts atts : platList) {
					conHql.append(String.format(con, atts.getOpkey())).append(" or ");
				}
				if (conHql.length() > 0)
					conHql.delete(conHql.length() - 3, conHql.length());
			}
			if (conHql.length() > 0) {
				StringBuilder buildHql = new StringBuilder();
				buildHql.append("from Managers m,PlatformsManager pm where m.id = pm.manager.id and m.status = 1 ");
				if (filter != null && filter.getRules().size() > 0)
					buildHql.append(" and m.branchs = " + filter.getRules().get(0).getData());
				if (genrer != null)
					buildHql.append(" and m.genrer = " + Genres.LIZHI.ordinal());
				else
					buildHql.append(" and m.genrer <> " + Genres.LIZHI.ordinal());
				if (genre != null && genre.trim().length() > 0) {
					buildHql.append(" and m.genre = " + Enum.valueOf(LineGenres.class, genre).ordinal());
				}
				if (buildHql.length() > 0 && fastArg != null) {
					try {
						conHql.append(" or m.number = " + Integer.valueOf(fastArg));
						conHql.append(" or m.operator.id = " + user.getId());
						conHql.append(" or m.aliasname like '%" + fastArg + "%'");
						conHql.append(" or m.realname like '%" + fastArg + "%'");
					} catch (Exception e) {
						conHql.append(" or m.operator.id = " + user.getId());
						conHql.append(" or m.aliasname like '%" + fastArg + "%'");
						conHql.append(" or m.realname like '%" + fastArg + "%'");
					}
				} else {
					conHql.append(" or m.operator.id = " + user.getId());
				}
				buildHql.append(String.format(" and (%s) ", conHql));
				buildHql.append(" group by m.id");
				buildHql.append(" order by m.number desc,m.createDT desc");
				return this.baseDAO.NfindByPages(buildHql, buildHql, pageSize, curPage);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Managers> readAllManagers() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Managers.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.ne("genrer", Genres.LIZHI));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<Managers>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean readManagers(Integer id) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Managers.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.createAlias("employee", "emp").add(Restrictions.eq("emp.id", Integer.valueOf(id)));
			List<Managers> list = (List<Managers>) this.baseDAO.findAllByCriteria(detachedCriteria);
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
	public List<Managers> readManagersByEmpId(Integer empId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Managers.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("labour", ActoresLabour.SYSTEM));
			detachedCriteria.createAlias("employee", "emp").add(Restrictions.eq("emp.id", Integer.valueOf(empId)));
			List<Managers> list = (List<Managers>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean saveManager(Managers post) {
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

	@SuppressWarnings("unchecked")
	public Managers findManagerByEmpIdSession(int empId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Managers.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("labour", ActoresLabour.SYSTEM));
			detachedCriteria.createAlias("employee", "emp").add(Restrictions.eq("emp.id", Integer.valueOf(empId)));
			List<Managers> list = (List<Managers>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				Managers manager = list.get(0);
				return manager;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean saveManagerBySession(Managers manager) {
		try {
			if (manager.getId() != null && manager.getId().intValue() > 0) {
				this.baseDAO.update(manager);
			} else {
				this.baseDAO.save(manager);
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Managers> findAllOnlineManagers() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Managers.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("genre", LineGenres.ONLINE));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<Managers>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Managers> readAllManagersByPhone(String id, String phone) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Managers.class);
			if (id != null && id.trim().length() > 0)
				detachedCriteria.add(Restrictions.ne("id", Integer.valueOf(id)));
			detachedCriteria.add(Restrictions.eq("phone", phone));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("genre", LineGenres.ONLINE));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<Managers>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean checkManager(Managers post) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Managers.class);
			detachedCriteria.add(Restrictions.eq("realname", post.getRealname()));
			detachedCriteria.add(Restrictions.eq("aliasname", post.getAliasname()));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("branchs", post.getBranchs()));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<Managers> list = (List<Managers>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean readManagersByNumber(FinSalaries salaries) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Managers.class);
			detachedCriteria.add(Restrictions.eq("branchs", salaries.getBranchs()));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("number", salaries.getEmployee().getNumber()));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<Managers> manlist = (List<Managers>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (manlist != null && manlist.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
