package com.baofeng.oa.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.FinSalaryEmployee;
import com.baofeng.oa.entity.FinSalaryManager;
import com.baofeng.oa.entity.FinancialNReports;
import com.baofeng.oa.entity.FinancialOPExpenditure;
import com.baofeng.oa.entity.FinancialReports;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.SearchFilter;

@Repository("financialReportsDAO")
public class FinancialReportsDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("unchecked")
	public FinancialNReports findFinancialReportsByDate(Integer branchs, Date months1, Date months2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinancialNReports.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.between("createDT", months1, months2));
			List<FinancialNReports> list = (List<FinancialNReports>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0 && list.get(0) != null) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean addFinancialReports(FinancialReports financialReports) {
		try {
			if (financialReports != null && financialReports.getId() != null && financialReports.getId().intValue() > 0) {
				this.baseDAO.mrege(financialReports);
			} else {
				this.baseDAO.save(financialReports);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public FinancialReports readFinancialReports(Integer id) {
		try {
			return (FinancialReports) this.baseDAO.get(FinancialReports.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public FinancialOPExpenditure readFinancialOPExpenditure(String name, String finId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinancialOPExpenditure.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("name", name));
			detachedCriteria.createAlias("reports", "reports").add(Restrictions.eq("reports.id", Integer.valueOf(finId)));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<FinancialOPExpenditure> list = (List<FinancialOPExpenditure>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean save(FinancialOPExpenditure post) {
		try {
			if (post != null && post.getId() != null && post.getId().intValue() > 0) {
				this.baseDAO.mrege(post);
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
	public List<FinancialOPExpenditure> findFinancialOPExpenditure(Integer finId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinancialOPExpenditure.class);
			detachedCriteria.createAlias("reports", "reports").add(Restrictions.eq("reports.id", finId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<FinancialOPExpenditure>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 功能：总计普通员工公司实际支出
	 * */
	@SuppressWarnings("unchecked")
	public BigDecimal sumRealExpenditure(Date $date1, Date $date2) {
		BigDecimal big = new BigDecimal(0.00);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryEmployee.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.setProjection(Projections.sum("realExpenditure"));
			List<Object> rsList = (List<Object>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (rsList != null && rsList.size() > 0) {
				Object o = rsList.get(0);
				return big.add(new BigDecimal((Long) o));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return big;
		}
		return big;
	}

	@SuppressWarnings("unchecked")
	public BigDecimal sumManagerRealExpenditure(Date $date1, Date $date2) {
		BigDecimal big = new BigDecimal(0.00);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryManager.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.setProjection(Projections.sum("netSalaryOutlay"));
			List<Object> rsList = (List<Object>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (rsList != null && rsList.size() > 0) {
				Object o = rsList.get(0);
				return big.add(new BigDecimal((Long) o));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return big;
		}
		return big;
	}

	@SuppressWarnings("unchecked")
	public FinancialNReports readReportsByDate(Date date1, Date date2, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinancialNReports.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			List<FinancialNReports> list = (List<FinancialNReports>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean addFinancialNReports(FinancialNReports financialReports) {
		try {
			if (financialReports != null && financialReports.getId() != null && financialReports.getId().intValue() > 0) {
				this.baseDAO.mrege(financialReports);
			} else {
				this.baseDAO.save(financialReports);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public FinancialNReports findFinancialReportsByDateNew(Date date1, Date date2, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinancialNReports.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			List<FinancialNReports> list = (List<FinancialNReports>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0 && list.get(0) != null) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
