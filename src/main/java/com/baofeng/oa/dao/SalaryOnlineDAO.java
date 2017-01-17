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
import com.baofeng.oa.entity.FinSalaryOnline;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("salaryOnlineDAO")
public class SalaryOnlineDAO {

	@Autowired
	private IBaseDAO baseDAO;

	@SuppressWarnings("unchecked")
	public List<SalaryOnlineDAO> readSalaryOnline(Integer id) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryOnline.class);
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<?> outlayList = this.baseDAO.findAllByCriteria(detachedCriteria);
			if (outlayList != null && outlayList.size() > 0) {
				return (List<SalaryOnlineDAO>) outlayList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readAllSalaryOnline(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter, String see, Date date1,
			Date date2, String fastArg) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryOnline.class);
			if (fastArg != null && fastArg.trim().length() > 0) {
				Disjunction disjunction = Restrictions.disjunction();

				disjunction.add(Restrictions.like("name", fastArg, MatchMode.ANYWHERE));
				disjunction.add(Restrictions.like("aliasname", fastArg, MatchMode.ANYWHERE));
				detachedCriteria.add(disjunction);
			}
			if (!"all".equals(see)) {
				detachedCriteria.add(Restrictions.gt("realitySalary", new BigDecimal(0)));
			}
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addSalary(FinSalaryOnline post) {
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

	public boolean addIncome(FinPlatfromsIncome finPlatfromsIncome) {
		try {
			if (finPlatfromsIncome != null && finPlatfromsIncome.getId() != null && finPlatfromsIncome.getId().intValue() > 0) {
				this.baseDAO.update(finPlatfromsIncome);
			} else {
				this.baseDAO.save(finPlatfromsIncome);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean findValidation(Integer id, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryOnline.class);
			detachedCriteria.add(Restrictions.eq("actoresId", id));
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

	public FinSalaryOnline readSalaryOnlineByid(Integer id) {
		try {
			return (FinSalaryOnline) this.baseDAO.get(FinSalaryOnline.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FinSalaryOnline> findAllFinSalaryOnlineByDate(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryOnline.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			return (List<FinSalaryOnline>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public FinPlatfromsIncome findAllFinSalaryOnlineByDate(Integer salaryOnlineId, String platformsId, Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinPlatfromsIncome.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("platId", Integer.valueOf(platformsId)));
			detachedCriteria.createAlias("salaryOnline", "sonle").add(Restrictions.eq("sonle.id", salaryOnlineId));
			List<FinPlatfromsIncome> finList = (List<FinPlatfromsIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (finList != null && finList.size() > 0) {
				FinPlatfromsIncome income = finList.get(0);
				return income;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean updateFinPlatformsIncome(FinPlatfromsIncome finIncome) {
		try {
			if (finIncome != null && finIncome.getId() != null && finIncome.getId().intValue() > 0) {
				this.baseDAO.update(finIncome);
			} else {
				this.baseDAO.save(finIncome);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FinSalaryOnline> readAllsalaryOnlineByDate(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinSalaryOnline.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			List<FinSalaryOnline> finList = (List<FinSalaryOnline>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (finList != null && finList.size() > 0) {
				return finList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

}
