package com.baofeng.oa.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.EmployeeLeave;
import com.baofeng.oa.entity.EmployeeTransfer;
import com.baofeng.oa.entity.Positions;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

@Repository("employeeDAO")
public class EmployeeDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public Employee readEmployee(Integer id) {
		try {
			Employee employee = (Employee) this.baseDAO.get(Employee.class, id);
			return employee;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public PageResult readAllPagesSkip(int rows, int page, SearchFilter filter, String queryFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Positions.class);
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

	@SuppressWarnings("rawtypes")
	public NPageResult readAllPages(int pageSize, int currentPage, String sortName, String sortOrder, String name, String snumber, String snamepy, Integer sposid, Integer sdepid,
			String fastArg, String type, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
			if (name != null && name.trim().length() > 0)
				detachedCriteria.add(Restrictions.like("name", "%" + name + "%"));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.ne("genrer", Genres.LIZHI));
			detachedCriteria.createAlias("position", "position");
			detachedCriteria.createAlias("depart", "depart");
			if (fastArg != null && fastArg.trim().length() > 0) {
				Disjunction disjunction = Restrictions.disjunction();
				try {
					disjunction.add(Restrictions.eq("number", Integer.valueOf(fastArg)));
					disjunction.add(Restrictions.like("name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("namepy", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("aliasname", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("position.name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("depart.name", fastArg, MatchMode.ANYWHERE));
				} catch (Exception e) {
					disjunction.add(Restrictions.like("name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("namepy", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("aliasname", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("position.name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("depart.name", fastArg, MatchMode.ANYWHERE));
				}
				detachedCriteria.add(disjunction);
			}
			if (snumber != null && snumber.trim().length() > 0) {
				try {
					detachedCriteria.add(Restrictions.eq("number", Integer.valueOf(snumber)));
				} catch (Exception e) {
					detachedCriteria.add(Restrictions.eq("number", Integer.valueOf(0)));
				}
			}
			if (snamepy != null && snamepy.trim().length() > 0) {
				detachedCriteria.add(Restrictions.or(Restrictions.like("name", "%" + snamepy + "%"), Restrictions.like("aliasname", "%" + snamepy + "%")));
			}

			if (sposid != null && sposid.intValue() > 0) {
				detachedCriteria.add(Restrictions.eq("position.id", sposid));
			}
			if (sdepid != null && sdepid.intValue() > 0) {
				detachedCriteria.add(Restrictions.eq("depart.id", sdepid));
			}
			detachedCriteria.addOrder(Order.desc("number"));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, currentPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean saveEmp(Employee post) {
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
	public Employee readEmployeeByDate() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
			detachedCriteria.addOrder(Order.desc("number"));
			List<Employee> list = (List<Employee>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				Employee emp = list.get(0);
				return emp;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> readEmpByName(String name) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
			detachedCriteria.add(Restrictions.eq("name", name));
			return (List<Employee>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteEmployee(Employee employee) {
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

	@SuppressWarnings("rawtypes")
	public NPageResult readAllPagesProbationer(int pageSize, int curPage, String sortName, String sortOrder, String fastArg, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
			if (fastArg != null && fastArg.trim().length() > 0) {
				detachedCriteria.createAlias("position", "position");
				detachedCriteria.createAlias("depart", "depart");
				Disjunction disjunction = Restrictions.disjunction();
				try {
					disjunction.add(Restrictions.eq("number", Integer.valueOf(fastArg)));
					disjunction.add(Restrictions.like("name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("namepy", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("aliasname", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("position.name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("depart.name", fastArg, MatchMode.ANYWHERE));
				} catch (Exception e) {
					disjunction.add(Restrictions.like("name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("namepy", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("aliasname", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("position.name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("depart.name", fastArg, MatchMode.ANYWHERE));
				}
				detachedCriteria.add(disjunction);
			}
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("genrer", Genres.SHIYONG));
			detachedCriteria.addOrder(Order.desc("number"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> readAllEmpByPid(Integer id) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.createAlias("position", "position").add(Restrictions.eq("position.id", id));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<Employee>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> readAllEmployee() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.createAlias("depart", "depart").add(Restrictions.ne("depart.name", "艺人部"));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<Employee>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findEmployeeByPending() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("genrer", Genres.PENDING));
			detachedCriteria.add(Restrictions.between("leaveTime", CustomDateUtils.Handler.lastMonthFirstDay(), CustomDateUtils.Handler.lastMonthEndDay()));
			return (List<Employee>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> readAllEmployees() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<Employee>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Employee readEmployeeByNumber(Integer number) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
			detachedCriteria.add(Restrictions.eq("number", number));
			detachedCriteria.add(Restrictions.ne("genrer", Genres.LIZHI));
			List<Employee> list = (List<Employee>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				Employee emp = list.get(0);
				return emp;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> readAllEmployees(Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.le("joinDate", date2));
			return (List<Employee>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployeesNeLeave() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.ne("genrer", Genres.LIZHI));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<Employee>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public EmployeeLeave readEmployeeLeave(Integer id) {
		try {
			EmployeeLeave employee = (EmployeeLeave) this.baseDAO.get(EmployeeLeave.class, id);
			return employee;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public NPageResult readPagesBirth(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter) {
		try {
			StringBuilder buildHql = new StringBuilder();
			Date date = new Date();
			if (date.getMonth() + 1 == 12) {
				buildHql.append("from Employee e where month(e.birth) in (month(now()),1 ) and e.genrer in (0,1)");
				if (filter.getRules() != null && filter.getRules().size() > 0)
					buildHql.append(" and e.branchs = " + filter.getRules().get(0).getData());
			} else {
				buildHql.append("from Employee e where month(e.birth) in (month(now()), month(now())+1)and e.genrer in (0,1)");
				if (filter.getRules() != null && filter.getRules().size() > 0)
					buildHql.append(" and e.branchs = " + filter.getRules().get(0).getData());
			}
			buildHql.append(" order by e.birth asc");
			return this.baseDAO.NfindByPages(buildHql, buildHql, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Employee readEmployeeByBranchs(Integer branchs) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.addOrder(Order.desc("number"));
			List<Employee> list = (List<Employee>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				Employee emp = list.get(0);
				return emp;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveTransferEmployee(EmployeeTransfer post) {
		try {
			if (post != null && post.getId() != null && post.getId().intValue() > 0) {
				this.baseDAO.update(post);
				return true;
			} else {
				this.baseDAO.save(post);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public Employee readEmployeebyNum(Integer number, Integer branchs) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
		detachedCriteria.add(Restrictions.eq("branchs", branchs));
		detachedCriteria.add(Restrictions.eq("number", number));
		List<Employee> list = (List<Employee>) this.baseDAO.findAllByCriteria(detachedCriteria);
		if (list != null && list.size() > 0) {
			Employee emp = list.get(0);
			return emp;
		}
		return null;
	}
}
