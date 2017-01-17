package com.baofeng.oa.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.FinEarningsIncome;
import com.baofeng.oa.entity.FinPlatformsIncome;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("finEarningsIncomeDAO")
public class FinEarningsIncomeDAO {
	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public boolean addFinEarningsIncome(FinEarningsIncome finEarn) {
		try {
			if (finEarn != null && finEarn.getId() != null && finEarn.getId().intValue() > 0) {
				this.baseDAO.update(finEarn);
			} else {
				this.baseDAO.save(finEarn);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteFinEarningsIncome(FinEarningsIncome earnIncome) {
		try {
			this.baseDAO.delete(earnIncome);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	public List<FinEarningsIncome> findAllFinEarningsIncome(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinEarningsIncome.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<FinEarningsIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public FinPlatformsIncome findPlatformsIncomeById(Integer id, String platformsId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinPlatformsIncome.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("platId", Integer.valueOf(platformsId)));
			detachedCriteria.createAlias("finEarningsIncome", "fin").add(Restrictions.eq("fin.id", id));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<FinPlatformsIncome> list = (List<FinPlatformsIncome>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public FinEarningsIncome readFinEarningsIncome(Integer id) {
		try {
			if (id != null && id.intValue() > 0) {
				return (FinEarningsIncome) this.baseDAO.get(FinEarningsIncome.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, String fastArg, Date date1, Date date2, String type, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FinEarningsIncome.class);
			if ("off".equals(type)) {
				detachedCriteria.add(Restrictions.eq("genre", LineGenres.OFFLINE));
			}else{
				detachedCriteria.add(Restrictions.eq("genre", LineGenres.ONLINE));
			}
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
