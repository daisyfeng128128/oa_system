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
import com.baofeng.oa.entity.EmpAttendance;
import com.baofeng.oa.entity.PlatformsThirdManagerOutlay;
import com.baofeng.utils.Constants;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("empAttendanceDAO")
public class EmpAttendanceDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("unchecked")
	public List<EmpAttendance> readAllEmpAttendanceByDate(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsThirdManagerOutlay.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<EmpAttendance> list = (List<EmpAttendance>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, String id, Date date1, Date date2, String fastArg, SearchFilter searchFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmpAttendance.class);
			detachedCriteria.createAlias("employee", "employee");
			detachedCriteria.createAlias("employee.depart", "depart");
			if (fastArg != null && fastArg.trim().length() > 0) {
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
			if (id != null && id.trim().length() > 0)
				detachedCriteria.add(Restrictions.eq("depart.id", Integer.valueOf(id)));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("employee.number"));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, searchFilter);
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addEmpAttendance(EmpAttendance emp) {
		try {
			if (emp != null && emp.getId() != null && emp.getId().intValue() > 0) {
				this.baseDAO.update(emp);
			} else {
				this.baseDAO.save(emp);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public EmpAttendance readAttendanceById(Integer id) {
		try {
			return (EmpAttendance) this.baseDAO.get(EmpAttendance.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EmpAttendance> findAllEmpAttendanceByDate(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsThirdManagerOutlay.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<EmpAttendance> list = (List<EmpAttendance>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public EmpAttendance readAttendanceByNumberAndDate(Integer number, Integer branchs, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmpAttendance.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.createAlias("employee", "employee").add(Restrictions.eq("employee.number", number));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<EmpAttendance> list = (List<EmpAttendance>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public EmpAttendance readAllEmpAttendanceByDateByNumber(Integer number, Integer branchs, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmpAttendance.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.createAlias("employee", "employee").add(Restrictions.eq("employee.number", number));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<EmpAttendance> list = (List<EmpAttendance>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<String> readFinSalaryEmpDate(SearchFilter filter) {
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
			String hql = "select createDT from EmpAttendance e where status = 1 order by e.createDT desc";
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

	@SuppressWarnings("unchecked")
	public EmpAttendance readEmpAttendance(Integer number, Integer branchs, Date currentBeginDay, Date currentEndDay) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmpAttendance.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.createAlias("employee", "employee").add(Restrictions.eq("employee.number", number));
			detachedCriteria.add(Restrictions.between("createDT", currentBeginDay, currentEndDay));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<EmpAttendance> list = (List<EmpAttendance>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean updateEmpAttendance(EmpAttendance empa) {
		try {
			if (empa != null && empa.getId() != null && empa.getId().intValue() > 0) {
				this.baseDAO.update(empa);
			} else {
				this.baseDAO.save(empa);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
