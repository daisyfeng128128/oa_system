package com.baofeng.oa.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.FinSalaryEmployee;
import com.baofeng.oa.entity.FinSalaryOffline;
import com.baofeng.utils.IBaseDAO;

@Repository("personalSalariesDAO")
public class PersonalSalariesDAO {

	@Autowired
	private IBaseDAO baseDAO;

	@SuppressWarnings("unchecked")
	public FinSalaryEmployee readFinSalaryEmployee(Integer number, Date $date1,Date $date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryEmployee.class);
			detachedCriteria.add(Restrictions.eq("number", number));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<FinSalaryEmployee> list = (List<FinSalaryEmployee>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				FinSalaryEmployee employee = list.get(0);
				return employee;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	public Employee readEmployee(Integer number) { 
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
			detachedCriteria.add(Restrictions.eq("number", number));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL)); 
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<Employee> list = (List<Employee>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				Employee employee = list.get(0);
				return employee;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveEmp(Employee employee) {
		try {
			if (employee != null) {
				this.baseDAO.saveOrUpdate(employee);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public FinSalaryOffline readFinSalaryOffline(Integer number, Date $date1,
			Date $date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryOffline.class);
			detachedCriteria.add(Restrictions.eq("number", number));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<FinSalaryOffline> list = (List<FinSalaryOffline>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				FinSalaryOffline employee = list.get(0);
				return employee;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
