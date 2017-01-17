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
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("actoresOnlineDAO")
public class ActoresDAO {
	@Autowired
	private IBaseDAO baseDAO;

	@SuppressWarnings("rawtypes")
	public NPageResult readPagesPlatformsActores(int pageSize, int curPage, String sortName, String sortOrder, String genre, String isleave, SearchFilter filter,
			List<RoleDetailsAtts> platList, Operator operator, RoleUsers users, String srealname, String saliasname, String sphone, String fastArg, String snumber) {
		try {
			StringBuilder conHql = new StringBuilder();
			StringBuilder $conHql = new StringBuilder();
			String con = String.valueOf("pa.plat.id = %s");
			if (platList != null && platList.size() > 0) {
				for (RoleDetailsAtts atts : platList) {
					conHql.append(String.format(con, atts.getOpkey())).append(" or ");
				}
				if (conHql.length() > 0)
					conHql.delete(conHql.length() - 3, conHql.length());
			}
			if (conHql.length() > 0) {
				StringBuilder buildHql = new StringBuilder();
				buildHql.append("from Actores a,PlatformsActores pa where a.id = pa.actores.id and a.status = 1 ");
				buildHql.append(" and a.branchs = " + filter.getRules().get(0).getData());
				if (snumber != null && snumber.trim().length() > 0) {
					try {
						buildHql.append("and a.realname = " + Integer.valueOf(snumber));
					} catch (Exception e) {
					}
				}

				if (srealname != null && srealname.trim().length() > 0)
					buildHql.append("and a.realname like '%" + srealname + "%' ");
				if (saliasname != null && saliasname.trim().length() > 0)
					buildHql.append("and a.aliasname like '%" + saliasname + "%' ");
				if (sphone != null && sphone.trim().length() > 0)
					buildHql.append("and a.phone like '%" + sphone + "%' ");

				if ("Leave".equals(genre)) {
					buildHql.append("and a.genrer = " + Genres.LIZHI.ordinal() + " ");
				} else {
					buildHql.append("and a.genrer <> " + Genres.LIZHI.ordinal() + " ");
					buildHql.append("and a.genre = " + Enum.valueOf(LineGenres.class, genre).ordinal() + " ");
				}
				if (buildHql.length() > 0) {
					conHql.append(" or a.operator.id = " + operator.getId());
					if (fastArg != null && fastArg.trim().length() > 0) {
						try {
							$conHql.append("  a.realname like '%" + fastArg + "%' ");
							$conHql.append(" or a.aliasname like '%" + fastArg + "%' ");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else {
					conHql.append(" a.operator.id = " + operator.getId());
				}
				buildHql.append(String.format(" and (%s) ", conHql));
				if ($conHql.length() > 0)
					buildHql.append(String.format(" and (%s) ", $conHql));
				buildHql.append(" group by a.id");
				buildHql.append(" order by a.number desc,a.createDT desc");
				return this.baseDAO.NfindByPages(buildHql, buildHql, pageSize, curPage);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readPagesActoresOnline(int pageSize, int curPage, String sortName, String sortOrder, String genre, String isleave, SearchFilter filter, String srealname,
			String saliasname, String sphone, String fastArg, String snumber) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Actores.class);
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
			if (snumber != null && snumber.trim().length() > 0) {
				try {
					detachedCriteria.add(Restrictions.eq("number", Integer.valueOf(snumber)));
				} catch (Exception e) {
				}
			}
			if (srealname != null && srealname.trim().length() > 0)
				detachedCriteria.add(Restrictions.like("realname", srealname, MatchMode.ANYWHERE));

			if (saliasname != null && saliasname.trim().length() > 0)
				detachedCriteria.add(Restrictions.like("aliasname", saliasname, MatchMode.ANYWHERE));

			if (sphone != null && sphone.trim().length() > 0)
				detachedCriteria.add(Restrictions.like("phone", sphone, MatchMode.ANYWHERE));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			if (genre.equals("Leave")) {
				detachedCriteria.add(Restrictions.eq("genrer", Genres.LIZHI));
			} else {
				detachedCriteria.add(Restrictions.ne("genrer", Genres.LIZHI));
				detachedCriteria.add(Restrictions.eq("genre", Enum.valueOf(LineGenres.class, genre)));
			}
			detachedCriteria.addOrder(Order.desc("number"));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean saveActores(Actores actor) {
		try {
			if (actor != null && actor.getId() != null && actor.getId().intValue() > 0) {
				this.baseDAO.update(actor);
			} else {
				this.baseDAO.save(actor);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Actores readActoresOnline(Integer id) {
		try {
			return (Actores) this.baseDAO.get(Actores.class, id);
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

	@SuppressWarnings("unchecked")
	public Actores readActoresByphone(String phone) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Actores.class);
			detachedCriteria.add(Restrictions.eq("phone", phone));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("genre", Enum.valueOf(LineGenres.class, "ONLINE")));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<Actores> list = (List<Actores>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				Actores actores = list.get(0);
				return actores;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public Actores readActoresByNum(Integer num) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Actores.class);
			detachedCriteria.add(Restrictions.eq("number", num));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("genre", Enum.valueOf(LineGenres.class, "OFFLINE")));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<Actores> list = (List<Actores>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				Actores actores = list.get(0);
				return actores;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Actores findActoresBySession(Integer empId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Actores.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("labour", ActoresLabour.SYSTEM));
			detachedCriteria.createAlias("employee", "em").add(Restrictions.eq("em.id", empId));
			List<?> list = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				Actores actores = (Actores) list.get(0);
				return actores;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean saveActoresBySession(Actores actores) {
		try {
			if (actores.getId() != null && actores.getId().intValue() > 0) {
				this.baseDAO.update(actores);
			} else {
				this.baseDAO.save(actores);
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Actores> findAllActoresOffline() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Actores.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("genre", LineGenres.OFFLINE));
			detachedCriteria.add(Restrictions.ne("genrer", Genres.LIZHI));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<Actores>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Actores> findAllActoresOnline() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Actores.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("genre", LineGenres.ONLINE));
			detachedCriteria.add(Restrictions.ne("genrer", Genres.LIZHI));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<Actores>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Actores findActoresById(Integer offlineActoreId) {
		try {
			if (offlineActoreId != null && offlineActoreId.intValue() > 0) {
				Actores actores = (Actores) this.baseDAO.get(Actores.class, offlineActoreId);
				if (actores != null) {
					return actores;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public boolean checkActores(Actores post) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Actores.class);
			detachedCriteria.add(Restrictions.eq("realname", post.getRealname()));
			detachedCriteria.add(Restrictions.eq("aliasname", post.getAliasname()));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("branchs", post.getBranchs()));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<Actores> list = (List<Actores>) this.baseDAO.findAllByCriteria(detachedCriteria);
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
	public List<Platforms> findPlat(List<RoleDetailsAtts> platList, Integer id) {
		StringBuilder conHql = new StringBuilder();
		String con = String.valueOf("pa.plat.id = %s");
		if (platList != null && platList.size() > 0) {
			for (RoleDetailsAtts atts : platList) {
				conHql.append(String.format(con, atts.getOpkey())).append(" or ");
			}
			if (conHql.length() > 0)
				conHql.delete(conHql.length() - 3, conHql.length());
		}
		if (conHql.length() > 0) {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Platforms.class);
			detachedCriteria.add(Restrictions.eq("id",id ));
			return (List<Platforms>) this.baseDAO.findAllByCriteria(detachedCriteria);
		}
		return null;
	}

}
