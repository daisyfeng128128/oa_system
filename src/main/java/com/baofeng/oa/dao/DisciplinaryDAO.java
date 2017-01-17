package com.baofeng.oa.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.BaseEnums.Punishment;
import com.baofeng.oa.entity.Disciplinary;
import com.baofeng.utils.Constants;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("disciplinaryDAO")
public class DisciplinaryDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readPages(int pageSize, int curPage, String sortName,String sortOrder, Integer type, Date date1, Date date2, String fastArg, Integer id,
			SearchFilter searchFilter, String classQuery) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Disciplinary.class);
			detachedCriteria.createAlias("employee", "employee");
			if (fastArg != null && fastArg.trim().length() > 0) {
				if (fastArg.equals( "未交")) {
					detachedCriteria.add(Restrictions.eq("punishment",Punishment.UNKNOWN ));
				}else if (fastArg.equals( "已交")) {
					detachedCriteria.add(Restrictions.eq("punishment",Punishment.PASSED ));
				}else{
					Disjunction disjunction = Restrictions.disjunction();
					try {
						disjunction.add(Restrictions.eq("employee.number", Integer.valueOf(fastArg)));
						disjunction.add(Restrictions.like("employee.name", fastArg, MatchMode.ANYWHERE));
						disjunction.add(Restrictions.like("employee.aliasname", fastArg, MatchMode.ANYWHERE));
					} catch (Exception e) {
						disjunction.add(Restrictions.like("employee.name", fastArg, MatchMode.ANYWHERE));
						disjunction.add(Restrictions.like("employee.aliasname", fastArg, MatchMode.ANYWHERE));
					}
					detachedCriteria.add(disjunction);
				}
			}
			if (id != null)
				detachedCriteria.add(Restrictions.eq("depart.id", Integer.valueOf(id)));
			if(classQuery != null && !classQuery.equals(""))
				detachedCriteria.add(Restrictions.eq("punishmentItems", classQuery));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.addOrder(Order.asc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, searchFilter);
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Disciplinary readDisciplinaryById(Integer id) {
		try {
			Disciplinary disciplinary = (Disciplinary) this.baseDAO.get(Disciplinary.class, id);
			return disciplinary;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveDisciplinary(Disciplinary disciplinary) {
		try {
			if (disciplinary != null && disciplinary.getId() != null && disciplinary.getId().intValue() > 0) {
				this.baseDAO.update(disciplinary);
			} else {
				this.baseDAO.save(disciplinary);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Disciplinary> readAllDisciplinary(SearchFilter searchFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Disciplinary.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, searchFilter);
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<Disciplinary>) baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Disciplinary readDisciplinary(Integer id) {
		try {
			Disciplinary disciplinary = (Disciplinary) this.baseDAO.get(Disciplinary.class, id);
			return disciplinary;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> readDisciplinaryDate(SearchFilter searchFilter) {
		try {
			Set<String> set = new TreeSet(new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					String[] str1 = s1.split("-");
					String[] str2 = s2.split("-");
					int num = Integer.valueOf(str2[0]) - Integer.valueOf(str1[0]);
					int num1 = num == 0 ? Integer.valueOf(str2[1]) - Integer.valueOf(str1[1]) : num;
					return num1;
				}
			});
			String hql = "select createDT from Disciplinary e where status = 1 order by e.createDT desc";
			List<Object> listObj = (List<Object>) this.baseDAO.find(hql);
			if (listObj != null && listObj.size() > 0) {
				for (Object o : listObj) {
					Date date = (Date) o;
					set.add(Constants.convert(date, Constants.format7));
				}
			}
			return new ArrayList<String>(set);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
