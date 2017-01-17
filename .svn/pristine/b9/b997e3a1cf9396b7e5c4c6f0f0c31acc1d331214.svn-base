package com.baofeng.oa.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.FinSalaryEmployee;
import com.baofeng.utils.Constants;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;

@Repository("finSalaryEmployeeDAO")
public class FinSalaryEmployeeDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("unchecked")
	public boolean validation(Integer number, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryEmployee.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("number", number));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<FinSalaryEmployee> salaryList = (List<FinSalaryEmployee>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (salaryList != null && salaryList.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean saveFinSalaryEmployee(FinSalaryEmployee salary) {
		try {
			if (salary.getId() != null && salary.getId().intValue() > 0) {
				this.baseDAO.update(salary);
			} else {
				this.baseDAO.save(salary);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readAllSalaryEmployee(Date date1, Date date2, int pageSize, int curPage, Integer type, String fastArg) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryEmployee.class);
			if (fastArg != null && fastArg.trim().length() > 0) {
				Disjunction disjunction = Restrictions.disjunction();
				try {
					disjunction.add(Restrictions.eq("number", Integer.valueOf(fastArg)));
					disjunction.add(Restrictions.like("name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("positionsName", fastArg, MatchMode.ANYWHERE));
				} catch (Exception e) {
					disjunction.add(Restrictions.like("name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("deptName", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("positionsName", fastArg, MatchMode.ANYWHERE));
				}
				detachedCriteria.add(disjunction);
			}
			if (type == null || type != 1)
				detachedCriteria.add(Restrictions.gt("realitySalary", new BigDecimal(0)));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.asc("number"));
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public FinSalaryEmployee readFinSalaryEmployee(Integer id) {
		if (id != null && id.intValue() > 0) {
			FinSalaryEmployee salary = (FinSalaryEmployee) this.baseDAO.get(FinSalaryEmployee.class, id);
			if (salary != null)
				return salary;
		}
		return null;
	}

	/**
	 * 功能：获取数据所有日期
	 * */
	@SuppressWarnings("unchecked")
	public List<String> readFinSalaryEmployeeDate() {
		try {
			Set<String> set = new HashSet<String>();
			String hql = "select createDT from FinSalaryEmployee e where status = 1";
			List<Object> list = (List<Object>) this.baseDAO.find(hql);
			if (list != null && list.size() > 0) {
				for (Object o : list) {
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

	@SuppressWarnings("unchecked")
	public List<FinSalaryEmployee> findAllFinSalaryEmployeeByDate(Date $date1, Date $date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryEmployee.class);
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			return (List<FinSalaryEmployee>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FinSalaryEmployee> readFinSalaryEmployeeByDate(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryEmployee.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			return (List<FinSalaryEmployee>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
