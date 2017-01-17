package com.baofeng.oa.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.bean.ActoresBean;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.AdvanceSalary;
import com.baofeng.oa.entity.BaseEnums.Advance;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("advanceSalaryDAO")
public class AdvanceSalaryDAO {
	
	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActoresBean> readPlatformsActores(Actores actores,List<RoleDetailsAtts> platList) {
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
				StringBuilder buildHql = new StringBuilder().append("from Actores a,PlatformsActores pa where a.id = pa.actores.id and a.status = 1 ");
				if (actores.getRealname() != null && actores.getRealname().trim().length() > 0) {
					try {
						Integer.parseInt(actores.getRealname());
						buildHql.append("and a.number = " + actores.getRealname() + " ");
					} catch (Exception e) {
						buildHql.append("and (a.realname like '%" + actores.getRealname() + "%' or a.aliasname like '%" + actores.getRealname() + "%') ");
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
	
	@SuppressWarnings("rawtypes")
	public NPageResult readPages(int pageSize, int curPage, String sortName,String sortOrder, SearchFilter searchFilter,
			String ads,List<RoleDetailsAtts> platList, String repay) {
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
				StringBuilder buildHql = new StringBuilder().append("from AdvanceSalary fa where fa.status = 1 ");
				if (searchFilter.getRules() != null && searchFilter.getRules().size() > 0)
					buildHql.append(" and fa.branchs = " + searchFilter.getRules().get(0).getData()+" ");
				if (ads != null && ads.trim().length() > 0) {
					Advance adjust = Enum.valueOf(Advance.class, ads);
					if (adjust == Advance.UNKNOWN)
						buildHql.append("and fa.advance = " + adjust.ordinal() + " ");
					else if(adjust == Advance.PASSED)
						buildHql.append("and fa.advance = " + Advance.PASSED.ordinal() + " ");
					else if(adjust == Advance.FAILED)
						buildHql.append("and fa.advance = " + Advance.FAILED.ordinal() + " ");	
				}
				buildHql.append(String.format("and (%s) ", conHql));
				buildHql.append("order by fa.repay asc ,fa.createDT desc");
			
				return this.baseDAO.NfindByPages(buildHql, buildHql, pageSize, curPage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean saveAdvanceSalary(AdvanceSalary adjust) {
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

	public AdvanceSalary readAdvanceSalary(String id) {
		AdvanceSalary adjust = (AdvanceSalary) this.baseDAO.get(AdvanceSalary.class, Integer.parseInt(id));
		if (adjust != null)
			return adjust;
		return null;
	}

}
