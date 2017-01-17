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
import com.baofeng.oa.entity.FinancialAccounts;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.SearchFilter;

@Repository("financialAccountsDAO")
public class FinancialAccountsDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("unchecked")
	public List<FinancialAccounts> readAllFinancialAccounts(Integer branchs, String subjects, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinancialAccounts.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.eq("oneSubjectKey", subjects));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.addOrder(Order.asc("indexs"));
			detachedCriteria.addOrder(Order.asc("createDT"));
			return (List<FinancialAccounts>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public FinancialAccounts readAllByName(String twoSubject, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinancialAccounts.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("twoSubject", twoSubject));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<FinancialAccounts> list = (List<FinancialAccounts>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				if (list.get(0) != null) {
					return list.get(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addFinancialAccounts(FinancialAccounts fin) {
		try {
			if (fin != null && fin.getId() != null && fin.getId().intValue() > 0) {
				this.baseDAO.update(fin);
			} else {
				this.baseDAO.save(fin);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean saveFinancialAccounts(FinancialAccounts financial) {
		try {
			if (financial != null && financial.getId() != null && financial.getId().intValue() > 0)
				this.baseDAO.update(financial);
			else
				this.baseDAO.save(financial);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public FinancialAccounts readFinancialAccounts(String two, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinancialAccounts.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("twoSubject", two));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			List<FinancialAccounts> facList = (List<FinancialAccounts>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (facList != null && facList.size() > 0) {
				if (facList.get(0) != null) {
					return facList.get(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 功能：总计所有项目支出
	 * */
	@SuppressWarnings("unchecked")
	public BigDecimal sumFinancialAccounts(String $one, Date date1, Date date2) {
		BigDecimal result = new BigDecimal(0.00);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinancialAccounts.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("oneSubjectKey", $one));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.setProjection(Projections.projectionList().add(Projections.sum("fiscalCharges")));
			List<Object> facList = (List<Object>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (facList != null && facList.size() > 0) {
				Object o = facList.get(0);
				if (o != null) {
					result = result.add(new BigDecimal(o.toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public FinancialAccounts readFinancialByOneName(String one, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinancialAccounts.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("oneSubjectKey", one));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			List<FinancialAccounts> facList = (List<FinancialAccounts>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (facList != null && facList.size() > 0) {
				if (facList.get(0) != null) {
					return facList.get(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public FinancialAccounts readFinancialAccounts(String one, String two, Integer branchs, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinancialAccounts.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("oneSubjectKey", one));
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.eq("twoSubject", two));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			List<FinancialAccounts> facList = (List<FinancialAccounts>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (facList != null && facList.size() > 0) {
				if (facList.get(0) != null) {
					return facList.get(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public FinancialAccounts findFinancialAccountsByName(String name, String subjects, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinancialAccounts.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("twoSubjectMsg", name));
			detachedCriteria.add(Restrictions.eq("oneSubjectKey", subjects));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			List<FinancialAccounts> facList = (List<FinancialAccounts>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (facList != null && facList.size() > 0) {
				if (facList.get(0) != null) {
					return facList.get(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<FinancialAccounts> readAllFinancialAccountsNew(String subjects, Date date1, Date date2, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinancialAccounts.class);
			detachedCriteria.add(Restrictions.eq("oneSubjectKey", subjects));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.addOrder(Order.asc("indexs"));
			detachedCriteria.addOrder(Order.asc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return (List<FinancialAccounts>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
