package com.baofeng.oa.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.FinSalariesTalent;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("finSalariesTalentDAO")
public class FinSalariesTalentDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("unchecked")
	public boolean findValidation(Integer id, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalariesTalent.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.createAlias("talent", "talent").add(Restrictions.eq("talent.id", id));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<FinSalariesTalent> list = (List<FinSalariesTalent>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addFinSalariesTalent(FinSalariesTalent fin) {
		try {
			if (fin != null && fin.getId() != null && fin.getId().intValue() > 0) {
				this.baseDAO.update(fin);
			} else {
				this.baseDAO.save(fin);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, Date date1, Date date2, String fastArg, Integer all, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalariesTalent.class);
			if (fastArg != null && fastArg.trim().length() > 0) {
				Disjunction disjunction = Restrictions.disjunction();
				disjunction.add(Restrictions.like("name", fastArg, MatchMode.ANYWHERE));
				disjunction.add(Restrictions.like("aliasname", fastArg, MatchMode.ANYWHERE));
				detachedCriteria.add(disjunction);
			}
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public FinSalariesTalent readFinSalariesTalent(Integer id) {
		try {
			if (id != null && id.intValue() > 0) {
				FinSalariesTalent finSalariesTalent = (FinSalariesTalent) this.baseDAO.get(FinSalariesTalent.class, id);
				return finSalariesTalent;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<FinSalariesTalent> readAllFinSalariesTalent(Date date1, Date date2, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalariesTalent.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return (List<FinSalariesTalent>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<FinSalariesTalent> findAllFinSalariesTalent(Date $date1, Date $date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalariesTalent.class);
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<FinSalariesTalent>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public BigDecimal findSumOnlineTalents(Integer branchs, Date $date1, Date $date2) {
		BigDecimal talentOutlay = new BigDecimal(0.00);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalariesTalent.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.setProjection(Projections.sum("realExpenditure"));
			List<Object> detailsList = (List<Object>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (detailsList != null && detailsList.size() > 0) {
				Object o = detailsList.get(0);
				if (o instanceof BigDecimal) {
					talentOutlay = (BigDecimal) o;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return talentOutlay;
	}
}
