package com.baofeng.oa.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.bean.ActoresBean;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.Adjust;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.FinSalariesAdjusts;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("finSalariesAdjustsDAO")
public class FinSalariesAdjustsDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter, String ads,
			List<RoleDetailsAtts> platList) {
		try {
			StringBuilder conHql = new StringBuilder();
			String con = String.valueOf("fa.platId = '%s'");
			if (platList != null && platList.size() > 0) {
				for (RoleDetailsAtts atts : platList) {
					conHql.append(String.format(con, atts.getOpkey())).append(" or ");
				}
				if (conHql.length() > 0)
					conHql.delete(conHql.length() - 3, conHql.length());
			}
			if (conHql.length() > 0) {
				StringBuilder buildHql = new StringBuilder().append("from FinSalariesAdjusts fa where fa.status = 1 ");
				if (filter.getRules() != null && filter.getRules().size() > 0)
					buildHql.append(" and fa.branchs = " + filter.getRules().get(0).getData());
				if (ads != null && ads.trim().length() > 0) {
					Adjust adjust = Enum.valueOf(Adjust.class, ads);
					if (adjust == Adjust.UNKNOWN)
						buildHql.append("and fa.adjust = " + adjust.ordinal() + " ");
					else
						buildHql.append("and fa.adjust <> " + Adjust.UNKNOWN.ordinal() + " ");
				}
				buildHql.append(String.format("and (%s) ", conHql));
				buildHql.append("order by fa.createDT desc");
				return this.baseDAO.NfindByPages(buildHql, buildHql, pageSize, curPage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ActoresBean> readPlatformsActores(Actores actores, List<RoleDetailsAtts> platList) {
		try {
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
				StringBuilder buildHql = new StringBuilder()
						.append("from Actores a,PlatformsActores pa where a.id = pa.actores.id and a.status = 1 ");
				if (actores.getRealname() != null && actores.getRealname().trim().length() > 0) {
					try {
						Integer.parseInt(actores.getRealname());
						buildHql.append("and a.number = " + actores.getRealname() + " ");
					} catch (Exception e) {
						buildHql.append("and (a.realname like '%" + actores.getRealname() + "%' or a.aliasname like '%" + actores.getRealname()
								+ "%') ");
					}
				}
				buildHql.append(String.format("and (%s) ", conHql));
				buildHql.append(" order by a.number desc,a.createDT desc");
				List<Actores> pactList = (List<Actores>) this.baseDAO.find(buildHql.toString());
				if (pactList != null && pactList.size() > 0) {
					Set<ActoresBean> set = new HashSet<ActoresBean>();
					for (Object o : pactList) {
						if (o instanceof Object[]) {
							Object[] o2 = (Object[]) o;
							Actores $actores = (Actores) o2[0];
							ActoresBean bean = new ActoresBean();
							bean.setId($actores.getId());
							bean.setNumber($actores.getNumber());
							bean.setRealname($actores.getRealname());
							bean.setAliasname($actores.getAliasname());
							bean.setSex($actores.getSex().toString());
							bean.setGenre($actores.getGenre().toString());
							if ($actores.getGenre() == LineGenres.ONLINE)
								bean.setBasicSalary($actores.getMinimumGuarantee());
							else
								bean.setBasicSalary($actores.getBasicSalary());
							bean.setPushMoney($actores.getPushMoney());
							set.add(bean);
						}
					}
					return new ArrayList<ActoresBean>(set);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean saveFinSalariesAdjusts(FinSalariesAdjusts adjust) {
		try {
			if (adjust != null && adjust.getId() != null && adjust.getId().intValue() > 0) {
				this.baseDAO.update(adjust);
			} else {
				this.baseDAO.save(adjust);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public FinSalariesAdjusts readFinSalariesAdjuest(String id) {
		FinSalariesAdjusts adjust = (FinSalariesAdjusts) this.baseDAO.get(FinSalariesAdjusts.class, Integer.parseInt(id));
		if (adjust != null)
			return adjust;
		return null;
	}

	@SuppressWarnings("unchecked")
	public PlatformsMonthsOutlay readPlatformsMonthsOutlay(Integer id, Date date1, Date date2, Integer branchs) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonthsOutlay.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			if(id != null && id.intValue() > 0)
				detachedCriteria.add(Restrictions.eq("platId", String.valueOf(id)));
			else
				detachedCriteria.add(Restrictions.eq("platId", String.valueOf(-1)));
			List<PlatformsMonthsOutlay> list =(List<PlatformsMonthsOutlay>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if(list != null && list.size() > 0)
				return list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
