package com.baofeng.oa.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.ElectEquipent;
import com.baofeng.oa.entity.OfficeSupplies;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("purchaseDAO")
public class PurchaseDAO {
	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readPages(int pageSize, int curPage, String sortName,String sortOrder,Integer type,String fastArg, SearchFilter searchFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ElectEquipent.class);
			detachedCriteria.createAlias("employee", "employee");
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
			if (sortName != null && sortName.trim().length() > 0)
				detachedCriteria.add(Restrictions.like("realname", "%" + sortName + "%"));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			if(type!= null && type==2)
				detachedCriteria.add(Restrictions.eq("purType", 2));
			detachedCriteria.addOrder(Order.asc("applyDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, searchFilter);
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public boolean saveElectEquipent(ElectEquipent electEquipent) {
		try {
			if (electEquipent != null && electEquipent.getId() != null && electEquipent.getId().intValue() > 0) {
				this.baseDAO.update(electEquipent);
			} else {
				this.baseDAO.save(electEquipent);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ElectEquipent readElectEquipent(Integer id) {
		try {
			ElectEquipent electEquipent = (ElectEquipent) this.baseDAO.get(ElectEquipent.class, id);
			return electEquipent;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readPagesOff(int pageSize, int curPage, String sortName,String sortOrder,Integer type,String fastArg, SearchFilter searchFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OfficeSupplies.class);
			detachedCriteria.createAlias("employee", "employee");
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
			if (sortName != null && sortName.trim().length() > 0)
				detachedCriteria.add(Restrictions.like("realname", "%" + sortName + "%"));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			if(type!= null && type==1)
				detachedCriteria.add(Restrictions.eq("purType", 1));
			detachedCriteria.addOrder(Order.asc("applyDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, searchFilter);
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public OfficeSupplies readOfficeSupplies(Integer id) {
		try {
			OfficeSupplies officeSupplies = (OfficeSupplies) this.baseDAO.get(OfficeSupplies.class, id);
			return officeSupplies;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveOfficeSupplies(OfficeSupplies officeSupplies) {
		try {
			if (officeSupplies != null && officeSupplies.getId() != null && officeSupplies.getId().intValue() > 0) {
				this.baseDAO.update(officeSupplies);
			} else {
				this.baseDAO.save(officeSupplies);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	

}
