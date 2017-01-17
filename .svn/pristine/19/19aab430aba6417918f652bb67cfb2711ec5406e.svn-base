package com.baofeng.oa.dao;

import java.math.BigDecimal;
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
import com.baofeng.oa.entity.FinPlatfromsIncome;
import com.baofeng.oa.entity.FinSalaryOffline;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsOfflineIncome;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;

@Repository("finSalaryOfflineDAO")
public class FinSalaryOfflineDAO {
	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, Date date1, Date date2, Integer type, String fastArg) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryOffline.class);
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
			if(type == null || type != 1)
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

	@SuppressWarnings("unchecked")
	public PlatformsOfflineIncome findPlatformsOfflineIncomeById(Integer pactId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsOfflineIncome.class);
			detachedCriteria.add(Restrictions.eq("pactId", pactId.toString()));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			List<PlatformsOfflineIncome> list = (List<PlatformsOfflineIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean addSalary(FinSalaryOffline salary) {
		try {
			if (salary != null && salary.getId() != null && salary.getId().intValue() > 0) {
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

	public boolean findValidation(Integer id, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryOffline.class);
			detachedCriteria.add(Restrictions.eq("offlineActoreId", id));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			List<?> salaryList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (salaryList != null && salaryList.size() > 0) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsActores> readAllPlatformsActores(Integer pactId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsActores.class);
			detachedCriteria.createAlias("actores", "actores").add(Restrictions.eq("actores.id", pactId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<PlatformsActores>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public FinSalaryOffline readFinOffline(Integer id) {
		try {
			return (FinSalaryOffline) this.baseDAO.get(FinSalaryOffline.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FinSalaryOffline> findAllFinSalaryOfflineByDate(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryOffline.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			return (List<FinSalaryOffline>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FinSalaryOffline> findSalaryOfflineByDate(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryOffline.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			return (List<FinSalaryOffline>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addOnlySalary(FinSalaryOffline post) {
		try {
			if(post != null && post.getId()!=null && post.getId().intValue()>0){
				this.baseDAO.mrege(post);
			}else{
				this.baseDAO.save(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public FinPlatfromsIncome findPlatformsOfflineIncomeByPlatId(Integer platId, Integer id) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinPlatfromsIncome.class);
			detachedCriteria.createAlias("salaryOffline", "salaryOffline").add(Restrictions.eq("salaryOffline.id", id));
			detachedCriteria.add(Restrictions.eq("platId", platId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<FinPlatfromsIncome> list= 	(List<FinPlatfromsIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if(list != null && list.size()>0){
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateFinPlatformsIncome(FinPlatfromsIncome finPlatfromsIncome) {
		try {
			if(finPlatfromsIncome != null && finPlatfromsIncome.getId()!= null && finPlatfromsIncome.getId().intValue()>0){
				this.baseDAO.update(finPlatfromsIncome);
			}else{
				this.baseDAO.save(finPlatfromsIncome);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<FinSalaryOffline> readAllFinSalaryOfflineByDate(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryOffline.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<FinSalaryOffline>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
