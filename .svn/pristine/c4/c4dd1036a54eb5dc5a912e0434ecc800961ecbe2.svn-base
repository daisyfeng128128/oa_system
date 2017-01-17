package com.baofeng.oa.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
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
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.oa.entity.FinPlatformsIncome;
import com.baofeng.oa.entity.FinSalaries;
import com.baofeng.utils.Constants;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("finSalaryAllDAO")
public class FinSalaryAllDAO {
	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String id, Date date1, Date date2, String fastArg, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaries.class);
			if (fastArg != null && fastArg.trim().length() > 0) {
				Disjunction disjunction = Restrictions.disjunction();
				try {
					disjunction.add(Restrictions.eq("number", Integer.valueOf(fastArg)));
					disjunction.add(Restrictions.like("name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("aliasname", fastArg, MatchMode.ANYWHERE));
				} catch (Exception e) {
					disjunction.add(Restrictions.like("name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("aliasname", fastArg, MatchMode.ANYWHERE));
				}
				detachedCriteria.add(disjunction);
			}
			detachedCriteria.createAlias("employee", "employee").createAlias("employee.depart", "depart");
			if (id != null && id.trim().length() > 0)
				detachedCriteria.add(Restrictions.eq("depart.id", Integer.valueOf(id)));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("number"));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public FinSalaries readFinSalaryById(Integer id) {
		try {
			FinSalaries finSalaries = (FinSalaries) this.baseDAO.get(FinSalaries.class, id);
			return finSalaries;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addFinSalary(FinSalaries post) {
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

	@SuppressWarnings("rawtypes")
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, Date date1, Date date2, Integer type, String fastArg, Integer all,
			List<Integer> listId, String sex) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaries.class);
			Disjunction disjunction = Restrictions.disjunction();
			if (listId != null && listId.size() > 0) {
				for (Integer id : listId) {
					disjunction.add(Restrictions.eq("id", id));
				}
			}
			if (sex != null && sex.trim().length() > 0) {
				detachedCriteria.createAlias("employee", "emp").add(Restrictions.eq("emp.sex", Enum.valueOf(Sex.class, sex)));
			}
			if (type != null && type == 2) {
				detachedCriteria.add(Restrictions.eq("isActores", true));
			} else if (type != null && type == 1) {
				disjunction.add(Restrictions.eq("positionsName", "频道管理"));
				disjunction.add(Restrictions.eq("positionsName", "频道助管"));
				disjunction.add(Restrictions.eq("positionsName", "平台运营经理"));
			}
			if (all == null || all != 1)
				detachedCriteria.add(Restrictions.gt("realitySalary", new BigDecimal(0)));
			if (fastArg != null && fastArg.trim().length() > 0) {
				try {
					disjunction.add(Restrictions.eq("number", Integer.valueOf(fastArg)));
					disjunction.add(Restrictions.like("name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("aliasname", fastArg, MatchMode.ANYWHERE));
				} catch (Exception e) {
					disjunction.add(Restrictions.like("name", fastArg, MatchMode.ANYWHERE));
					disjunction.add(Restrictions.like("aliasname", fastArg, MatchMode.ANYWHERE));
				}

			}
			detachedCriteria.add(disjunction);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("number"));
			return baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public FinSalaries readFinSalariesOff(String id) {
		try {
			if (id != null && id.trim().length() > 0) {
				FinSalaries online = (FinSalaries) this.baseDAO.get(FinSalaries.class, Integer.valueOf(id));
				return online;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<FinSalaries> readAllFinSalariess(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaries.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("deptName"));
			detachedCriteria.addOrder(Order.desc("number"));
			return (List<FinSalaries>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<FinSalaries> readAllFinSalariessManager(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaries.class);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.eq("positionsName", "频道管理"));
			disjunction.add(Restrictions.eq("positionsName", "频道助管"));
			disjunction.add(Restrictions.eq("positionsName", "平台运营经理"));
			detachedCriteria.add(disjunction);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("number"));
			return (List<FinSalaries>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<FinSalaries> readAllFinSalariessActores(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaries.class);
			detachedCriteria.add(Restrictions.eq("positionsName", "艺人主播"));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("number"));
			return (List<FinSalaries>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<String> readFinSalaryDate() {
		try {
			Set<String> set = new TreeSet<>(new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					String[] str1 = s1.split("-");
					String[] str2 = s2.split("-");
					int num = Integer.valueOf(str2[0]) - Integer.valueOf(str1[0]);
					int num1 = num == 0 ? Integer.valueOf(str2[1]) - Integer.valueOf(str1[1]) : num;
					return num1;
				}
			});
			String hql = "select createDT from FinSalaries e where status = 1";
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
	public List<Integer> readFinPlatformsIncome(String plat) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinPlatformsIncome.class);
			detachedCriteria.add(Restrictions.eq("platId", Integer.valueOf(plat)));
			detachedCriteria.add(Restrictions.isNotNull("finSalaries"));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<FinPlatformsIncome> list = (List<FinPlatformsIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
			Set<Integer> set = new HashSet<Integer>();
			if (list != null && list.size() > 0) {
				for (FinPlatformsIncome post : list) {
					if (post.getFinSalaries() != null) {
						set.add(post.getFinSalaries().getId());
					}
				}
			} else {
				set.add(-1);
			}
			return new ArrayList<Integer>(set);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FinSalaries> readAllFinSalariesOffActores(Date date1, Date date2, List<Integer> listId, String sex) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaries.class);
			Disjunction disjunction = Restrictions.disjunction();
			if (listId != null && listId.size() > 0) {
				for (Integer id : listId) {
					disjunction.add(Restrictions.eq("id", id));
				}
			}
			if (sex != null && sex.trim().length() > 0) {
				detachedCriteria.createAlias("employee", "emp").add(Restrictions.eq("emp.sex", Enum.valueOf(Sex.class, sex)));
			}
			detachedCriteria.add(disjunction);
			detachedCriteria.add(Restrictions.eq("positionsName", "艺人主播"));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("number"));
			return (List<FinSalaries>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
