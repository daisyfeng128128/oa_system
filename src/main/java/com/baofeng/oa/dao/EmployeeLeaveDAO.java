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
import com.baofeng.oa.entity.EmployeeLeave;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("employeeLeaveDAO")
public class EmployeeLeaveDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public EmployeeLeave readEmployeeLeave(Integer id) {
		try {
			EmployeeLeave employee = (EmployeeLeave) this.baseDAO.get(EmployeeLeave.class, id);
			return employee;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveEmpl(EmployeeLeave post) {
		try {
			if (post != null) {
				this.baseDAO.saveOrUpdate(post);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readAllPagesLeave(int pageSize, int curPage, String sortName, String sortOrder, String fastArg, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeLeave.class);
			if (fastArg != null && fastArg.trim().length() > 0) {

				detachedCriteria.createAlias("employee", "emp");
				detachedCriteria.createAlias("employee.position", "position");
				detachedCriteria.createAlias("employee.depart", "depart");
				Disjunction disjunction = Restrictions.disjunction();
				try {
					disjunction.add(Restrictions.eq("emp.number", Integer.valueOf(fastArg)));
					disjunction.add(Restrictions.like("emp.name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("emp.namepy", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("emp.aliasname", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("position.name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("depart.name", fastArg, MatchMode.ANYWHERE));
				} catch (Exception e) {
					disjunction.add(Restrictions.like("emp.name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("emp.namepy", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("emp.aliasname", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("position.name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("depart.name", fastArg, MatchMode.ANYWHERE));
				}
				detachedCriteria.add(disjunction);
			}
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("leaveTime"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public boolean readempleave(Integer id) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeLeave.class);
			detachedCriteria.createAlias("employee", "employee").add(Restrictions.eq("employee.id", id));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<EmployeeLeave> list = (List<EmployeeLeave>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public EmployeeLeave findEmployeeLeaveByEmpId(Integer id) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeLeave.class);
			detachedCriteria.createAlias("employee", "employee").add(Restrictions.eq("employee.id", id));
			List<EmployeeLeave> list = (List<EmployeeLeave>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
