package com.baofeng.oa.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.Operator.Actores;
import com.baofeng.oa.entity.Department;
import com.baofeng.oa.entity.Employee;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

@Repository("departmentDAO")
public class DepartmentDAO {
	@Autowired
	private IBaseDAO baseDAO;

	public Department readDepartment(Integer id) {
		try {
			Department department = (Department) this.baseDAO.get(Department.class, id);
			return department;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteDepartment(Department department) {
		try {
			if (department != null) {
				this.baseDAO.saveOrUpdate(department);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean saveDepartment(Department poster) {
		try {
			if (poster != null) {
				this.baseDAO.saveOrUpdate(poster);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public PageResult readAllPagesSkip(int rows, int page, SearchFilter filter, String queryFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
			if (queryFilter != null && !queryFilter.trim().equals(""))
				detachedCriteria.add(Restrictions.like("name", "%" + queryFilter + "%"));
			detachedCriteria.add(Restrictions.eq("actores", Actores.NO));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			detachedCriteria.addOrder(Order.desc("createDT"));
			return this.baseDAO.findByPages(detachedCriteria, rows, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public PageResult readAllPagesParent(int rows, int page, SearchFilter filter, String queryFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Department.class);
			if (queryFilter != null && !queryFilter.trim().equals(""))
				detachedCriteria.add(Restrictions.like("name", "%" + queryFilter + "%"));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			detachedCriteria.addOrder(Order.desc("createDT"));
			return this.baseDAO.findByPages(detachedCriteria, rows, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean readDepartmentByParentId(Integer parentid) {
		try {
			if (parentid != null && parentid.intValue() > 0) {
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Department.class);
				detachedCriteria.createAlias("parent", "p").add(Restrictions.eq("p.id", parentid));
				detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
				detachedCriteria.addOrder(Order.desc("createDT"));
				List<Department> list = (List<Department>) this.baseDAO.findAllByCriteria(detachedCriteria);
				if (list != null && list.size() > 0)
					return true;
				else
					return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String name, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Department.class);
			if (name != null && name.trim().length() > 0)
				detachedCriteria.add(Restrictions.like("name", "%" + name + "%"));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Department> readAllDepartment(SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Department.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<Department>) baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
