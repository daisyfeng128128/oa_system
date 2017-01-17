package com.baofeng.oa.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.PlatformsMonths;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("platformsMonthsDAO")
public class PlatformsMonthsDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public void addPlatformsMonths(PlatformsMonths months) {
		try {
			this.baseDAO.save(months);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PlatformsMonths findPlatformsMonths(Integer branchs, String platId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonths.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.eq("platId", String.valueOf(platId)));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<?> list = baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				PlatformsMonths months = (PlatformsMonths) list.get(0);
				if (months != null)
					return months;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean savePlatformsMonths(PlatformsMonths months) {
		try {
			if (months != null && months.getId() != null && months.getId().intValue() > 0) {
				this.baseDAO.update(months);
			} else {
				this.baseDAO.save(months);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public NPageResult<?> readAllPlatformsMonths(Date date1, Date date2, int pageSize, int curPage, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonths.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PlatformsMonths findAllPlatformsMonths(Integer monthsId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonths.class);
			detachedCriteria.createAlias("months", "mt").add(Restrictions.eq("mt.id", monthsId));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				PlatformsMonths months = (PlatformsMonths) outlayList.get(0);
				return months;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean savePlatformsMonthsBySession(PlatformsMonths outlay) {
		try {
			if (outlay.getId() != null && outlay.getId().intValue() > 0) {
				this.baseDAO.update(outlay);
			} else {
				this.baseDAO.save(outlay);
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public PlatformsMonths readPlatformsMonths(Integer id) {
		try {
			if (id != null && id.intValue() > 0) {
				return (PlatformsMonths) this.baseDAO.get(PlatformsMonths.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean updateMonths(PlatformsMonths $months) {
		try {
			if ($months != null && $months.getId() != null && $months.getId().intValue() > 0) {
				this.baseDAO.update($months);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsMonths> findAllPlatformsMonths(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonths.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<PlatformsMonths>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("rawtypes")
	public NPageResult readAllMonthsOutlay(Date date1, Date date2, int pageSize, int curPage, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonths.class);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.gt("chIncome", new BigDecimal(0)));
			disjunction.add(Restrictions.gt("chOutlay", new BigDecimal(0)));
			detachedCriteria.add(disjunction);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsMonths> readAllPlatformsMonthsByDate(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonths.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<PlatformsMonths>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public BigDecimal findSumCurrentIncome(Integer branchs, Date $date1, Date $date2) {
		BigDecimal currentIncome = new BigDecimal(0.00);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsMonths.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.setProjection(Projections.sum("incomeTotal"));
			List<Object> detailsList = (List<Object>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (detailsList != null && detailsList.size() > 0) {
				Object o = detailsList.get(0);
				if (o instanceof BigDecimal) {
					currentIncome = (BigDecimal) o;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return currentIncome;
	}
}
