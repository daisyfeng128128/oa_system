package com.baofeng.oa.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.FinPlatformsIncome;
import com.baofeng.oa.entity.FinSalariesOnline;
import com.baofeng.oa.entity.Managers;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("finSalariesOnlineDAO")
public class FinSalariesOnlineDAO {
	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public void updateFinPlatformsIncome(FinPlatformsIncome finIncome) {
		try {
			if (finIncome != null && finIncome.getId() != null && finIncome.getId().intValue() > 0) {
				this.baseDAO.update(finIncome);
			} else {
				this.baseDAO.save(finIncome);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean addSalary(FinSalariesOnline post) {
		try {
			if (post != null && post.getId() != null && post.getId().intValue() > 0) {
				this.baseDAO.update(post);
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
	public boolean findValidation(String name, Integer id, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalariesOnline.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("entityClass", name));
			detachedCriteria.add(Restrictions.eq("entityId", id));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<FinSalariesOnline> list = (List<FinSalariesOnline>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null & list.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, Date date1, Date date2, String name, String fastArg, Integer all,
			List<Integer> listId, String sex, SearchFilter filter) {
		try {
			StringBuilder buildHql = new StringBuilder();
			StringBuilder conHql = new StringBuilder();
			StringBuilder $conHql = new StringBuilder();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String $date1 = sdf.format(date1);
			String $date2 = sdf.format(date2);
			String con = String.valueOf(" f.id = %d ");
			buildHql.append("from  Actores a,FinSalariesOnline f  where f.status = 1 and f.entityId = a.id ");
			buildHql.append(" and f.entityClass = '" + name + "'");
			if (filter.getRules() != null && filter.getRules().size() > 0)
				buildHql.append(" and f.branchs = " + filter.getRules().get(0).getData());
			if (all == null || all != 1)
				buildHql.append(" and f.realitySalary > " + new BigDecimal(0));
			if (sex != null && sex.trim().length() > 0) {
				buildHql.append(" and a.sex = " + Enum.valueOf(Sex.class, sex).ordinal());
			}

			buildHql.append(" and f.createDT between '" + $date1 + "' and '" + $date2 + "'");
			if (fastArg != null && fastArg.trim().length() > 0) {
				conHql.append(" f.name like '%" + fastArg + "%' ");
				conHql.append(" or f.aliasname like '%" + fastArg + "%'");
			}
			if (listId != null && listId.size() > 0) {
				for (Integer id : listId) {
					$conHql.append(String.format(con, id)).append(" or ");
				}
				$conHql.setLength($conHql.length() - 3);
			}
			if (conHql != null && conHql.length() > 0)
				buildHql.append(String.format(" and (%s) ", conHql));
			if ($conHql != null && $conHql.length() > 0)
				buildHql.append(String.format(" and (%s) ", $conHql));
			buildHql.append(" order by f.createDT desc");
			return this.baseDAO.NfindByPages(buildHql, buildHql, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public FinSalariesOnline readFinSalariesOnline(String id) {
		try {
			if (id != null && id.trim().length() > 0) {
				FinSalariesOnline online = (FinSalariesOnline) this.baseDAO.get(FinSalariesOnline.class, Integer.valueOf(id));
				return online;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public FinSalariesOnline findFinSalariesOnlineByActores(Integer id, String name, Date $date1, Date $date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalariesOnline.class);
			detachedCriteria.add(Restrictions.eq("entityId", id));
			detachedCriteria.add(Restrictions.eq("entityClass", name));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<FinSalariesOnline> detailsList = (List<FinSalariesOnline>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (detailsList != null && detailsList.size() > 0) {
				return detailsList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean saveFinSalariesOnline(FinSalariesOnline salaries) {
		try {
			if (salaries != null && salaries.getId() != null && salaries.getId().intValue() > 0) {
				this.baseDAO.update(salaries);
			} else {
				this.baseDAO.save(salaries);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public FinPlatformsIncome findPlatformsIncomeById(Integer id, String platformsId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinPlatformsIncome.class);
			detachedCriteria.createAlias("finSalariesOnline", "fins").add(Restrictions.eq("fins.id", id));
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

	public boolean savePlatformsIncome(FinPlatformsIncome incomeDetails) {
		try {
			if (incomeDetails != null && incomeDetails.getId() != null && incomeDetails.getId().intValue() > 0) {
				this.baseDAO.update(incomeDetails);
			} else {
				this.baseDAO.save(incomeDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<FinSalariesOnline> readAllFinSalariesOnlineManager(Date date1, Date date2, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalariesOnline.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("entityClass", Managers.class.getName()));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return (List<FinSalariesOnline>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FinSalariesOnline> readAllFinSalariesOnlineActores(Date date1, Date date2, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalariesOnline.class);
			detachedCriteria.add(Restrictions.gt("realitySalary", new BigDecimal(0)));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("entityClass", Actores.class.getName()));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return (List<FinSalariesOnline>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FinSalariesOnline> findAllFinSalariesOnline(Date $date1, Date $date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalariesOnline.class);
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<FinSalariesOnline>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<FinPlatformsIncome> findIncomeList(Integer salariesId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinPlatformsIncome.class);
			detachedCriteria.createAlias("finSalariesOnline", "fins").add(Restrictions.eq("fins.id", salariesId));
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
	public List<Integer> readFinPlatformsIncome(String plat) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinPlatformsIncome.class);
			detachedCriteria.add(Restrictions.eq("platId", Integer.valueOf(plat)));
			detachedCriteria.add(Restrictions.isNotNull("finSalariesOnline"));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<FinPlatformsIncome> list = (List<FinPlatformsIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
			Set<Integer> set = new HashSet<Integer>();
			if (list != null && list.size() > 0) {
				for (FinPlatformsIncome post : list) {
					if (post.getFinSalariesOnline() != null) {
						set.add(post.getFinSalariesOnline().getId());
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
	public List<FinSalariesOnline> readAllFinSalariesOnline(Date date1, Date date2, List<Integer> listId, String sex, Integer branchs) {
		try {
			StringBuilder buildHql = new StringBuilder();
			StringBuilder conHql = new StringBuilder();
			StringBuilder $conHql = new StringBuilder();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String $date1 = sdf.format(date1);
			String $date2 = sdf.format(date2);
			String con = String.valueOf(" f.id = %d ");
			buildHql.append("from  Actores a,FinSalariesOnline f  where f.status = 1 and f.entityId = a.id ");
			buildHql.append(" and f.entityClass = '" + Actores.class.getName() + "'");
			buildHql.append(" and f.realitySalary > " + new BigDecimal(0));
			buildHql.append(" and f.branchs = '" + branchs + "'");
			if (sex != null && sex.trim().length() > 0) {
				buildHql.append(" and a.sex = " + Enum.valueOf(Sex.class, sex).ordinal());
			}

			buildHql.append(" and f.createDT between '" + $date1 + "' and '" + $date2 + "'");
			if (listId != null && listId.size() > 0) {
				for (Integer id : listId) {
					$conHql.append(String.format(con, id)).append(" or ");
				}
				$conHql.setLength($conHql.length() - 3);
			}
			if (conHql != null && conHql.length() > 0)
				buildHql.append(String.format(" and (%s) ", conHql));
			if ($conHql != null && $conHql.length() > 0)
				buildHql.append(String.format(" and (%s) ", $conHql));
			buildHql.append(" order by f.createDT desc");
			List<Object> $list = (List<Object>) this.baseDAO.find(buildHql.toString());
			List<FinSalariesOnline> list = new ArrayList<FinSalariesOnline>();
			if ($list != null && $list.size() > 0) {
				for (Object o : $list) {
					Object[] $o = (Object[]) o;
					for (Object post : $o) {
						if (FinSalariesOnline.class.isInstance(post)) {
							list.add((FinSalariesOnline) post);
						}
					}
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public BigDecimal findSumOnlineActores(Integer branchs, Class<Actores> clazz, Date $date1, Date $date2) {
		BigDecimal actoresOutlay = new BigDecimal(0.00);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalariesOnline.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.eq("entityClass", clazz.getName()));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.setProjection(Projections.sum("realExpenditure"));
			List<Object> detailsList = (List<Object>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (detailsList != null && detailsList.size() > 0) {
				Object o = detailsList.get(0);
				if (o instanceof BigDecimal) {
					actoresOutlay = (BigDecimal) o;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return actoresOutlay;
	}

	@SuppressWarnings("unchecked")
	public BigDecimal findSumOnlineManagers(Integer branchs, Class<Managers> clazz, Date $date1, Date $date2) {
		BigDecimal managerOutlay = new BigDecimal(0.00);
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalariesOnline.class);
			detachedCriteria.add(Restrictions.eq("branchs", branchs));
			detachedCriteria.add(Restrictions.eq("entityClass", clazz.getName()));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.setProjection(Projections.sum("realExpenditure"));
			List<Object> detailsList = (List<Object>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (detailsList != null && detailsList.size() > 0) {
				Object o = detailsList.get(0);
				if (o instanceof BigDecimal) {
					managerOutlay = (BigDecimal) o;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return managerOutlay;
	}

	@SuppressWarnings("unchecked")
	public List<FinSalariesOnline> findFinSalariesOnlineByClass(Class<Actores> clazz, Date $date1, Date $date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalariesOnline.class);
			detachedCriteria.add(Restrictions.eq("entityClass", clazz.getName()));
			detachedCriteria.add(Restrictions.between("createDT", $date1, $date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			List<FinSalariesOnline> detailsList = (List<FinSalariesOnline>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (detailsList != null && detailsList.size() > 0) {
				return detailsList;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
