package com.baofeng.oa.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.EmployeType;
import com.baofeng.oa.entity.Department;
import com.baofeng.oa.entity.FinPlatformsIncome;
import com.baofeng.oa.entity.FinSalaries;
import com.baofeng.utils.Constants;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("finSalariesDAO")
public class FinSalariesDAO {
	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("unchecked")
	public boolean findValidation(Integer number, Integer branchs, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaries.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("number", number));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<FinSalaries> list = (List<FinSalaries>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				if (list.get(0) != null)
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}

	public boolean saveFinSalaries(FinSalaries finSalaries) {
		try {
			if (finSalaries != null && finSalaries.getId() != null && finSalaries.getId().intValue() > 0) {
				this.baseDAO.update(finSalaries);
			} else {
				this.baseDAO.save(finSalaries);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public FinSalaries findFinSalariesByNumber(Integer number, Integer branchs, Date $date1, Date $date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaries.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("number", number));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			List<FinSalaries> detailsList = (List<FinSalaries>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (detailsList != null && detailsList.size() > 0) {
				return detailsList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public FinPlatformsIncome findPlatformsIncomeById(Integer salariesId, String platformsId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinPlatformsIncome.class);
			detachedCriteria.createAlias("finSalaries", "fins").add(Restrictions.eq("fins.id", salariesId));
			detachedCriteria.add(Restrictions.eq("platId", Integer.valueOf(platformsId)));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<FinPlatformsIncome> list = (List<FinPlatformsIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public void savePlatformsIncome(FinPlatformsIncome incomeDetails) {
		try {
			if (incomeDetails != null && incomeDetails.getId() != null && incomeDetails.getId().intValue() > 0) {
				this.baseDAO.update(incomeDetails);
			} else {
				this.baseDAO.save(incomeDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<FinSalaries> findAllFinSalaries(Date $date1, Date $date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaries.class);
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<FinSalaries>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FinPlatformsIncome> findIncomeList(Integer salariesId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinPlatformsIncome.class);
			detachedCriteria.createAlias("finSalaries", "fins").add(Restrictions.eq("fins.id", salariesId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<FinPlatformsIncome> detailsList = (List<FinPlatformsIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (detailsList != null && detailsList.size() > 0) {
				return detailsList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
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

	@SuppressWarnings("rawtypes")
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, Date date1, Date date2, Integer type, String fastArg, Integer all,
			List<Integer> listId, String sex, SearchFilter filter) {
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
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
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

	@SuppressWarnings("unchecked")
	public List<FinSalaries> readAllFinSalariess(Date date1, Date date2, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaries.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("deptName"));
			detachedCriteria.addOrder(Order.desc("number"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return (List<FinSalaries>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FinSalaries> readAllFinSalariessManager(Date date1, Date date2, SearchFilter filter) {
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
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return (List<FinSalaries>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FinSalaries> readAllFinSalariessActores(Date date1, Date date2, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaries.class);
			detachedCriteria.add(Restrictions.eq("positionsName", "艺人主播"));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("number"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return (List<FinSalaries>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FinSalaries> readAllFinSalariesOffActores(Date date1, Date date2, List<Integer> listId, String sex, SearchFilter filter) {
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
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return (List<FinSalaries>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public BigDecimal findSumAllEmployees(Integer branchs, Date $date1, Date $date2) {
		BigDecimal allOutlay = new BigDecimal(0.00);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaries.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.setProjection(Projections.sum("realExpenditure"));
			List<Object> detailsList = (List<Object>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (detailsList != null && detailsList.size() > 0) {
				Object o = detailsList.get(0);
				if (o instanceof BigDecimal) {
					allOutlay = (BigDecimal) o;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return allOutlay;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readPagesShowSalaries(int pageSize, int curPage, String sortName, String sortOrder, Date date1, Date date2, String name, SearchFilter searchFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaries.class);
			if (name != null && name.trim().length() > 0) {
				Disjunction disjunction = Restrictions.disjunction();
				try {
					disjunction.add(Restrictions.eq("number", Integer.valueOf(name)));
					disjunction.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
				} catch (Exception e) {
					disjunction.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
				}
				detachedCriteria.add(disjunction);
			} else {
				detachedCriteria.add(Restrictions.eq("id", -3));
			}
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("number"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, searchFilter);
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FinPlatformsIncome> findPlatformsIncomeByList(Date $date1, Date $date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinPlatformsIncome.class);
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<FinPlatformsIncome> incomeList = (List<FinPlatformsIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (incomeList != null && incomeList.size() > 0) {
				return incomeList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<FinSalaries> findFinSalariesByActores(Class<Actores> clazz, Date $date1, Date $date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaries.class);
			detachedCriteria.add(Restrictions.or(Restrictions.eq("isActores", Boolean.valueOf("true")), Restrictions.eq("employeType", EmployeType.ACTORES)));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<FinSalaries> finList = (List<FinSalaries>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (finList != null && finList.size() > 0) {
				return finList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Map<String, List<FinSalaries>> readAllFinSalariessbyDep(Date date1, Date date2,SearchFilter searchFilter,List<Department> listdep) {
		Map<String, List<FinSalaries>> map = new HashMap<String, List<FinSalaries>>();
		try {
			if (listdep != null) {
				for (Department department : listdep) {
					DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaries.class);
					detachedCriteria.createAlias("employee", "employee").createAlias("employee.depart", "depart");
					detachedCriteria.add(Restrictions.eq("depart.id", department.getId()));
					detachedCriteria.add(Restrictions.between("createDT", date1, date2));
					detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
					detachedCriteria.addOrder(Order.desc("createDT"));
					List<FinSalaries> finList = (List<FinSalaries>) this.baseDAO.findAllByCriteria(detachedCriteria);
					if (finList != null) {
						map.put(department.getId().toString(), finList);
					}
				}
				return (Map<String, List<FinSalaries>>) map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}
}
