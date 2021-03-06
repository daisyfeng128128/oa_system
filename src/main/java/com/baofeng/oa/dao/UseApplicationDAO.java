package com.baofeng.oa.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.entity.EquipmentCollar;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("useApplicationDAO")
public class UseApplicationDAO {
	
	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public NPageResult<?> readPages(int pageSize, int curPage, String sortName,String sortOrder, String fastArg, SearchFilter searchFilter,
			Integer type) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EquipmentCollar.class);
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
			detachedCriteria.addOrder(Order.asc("applyDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, searchFilter);
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public boolean saveEquipmentCollar(EquipmentCollar equipmentCollar) {
		try {
			if (equipmentCollar != null && equipmentCollar.getId() != null && equipmentCollar.getId().intValue() > 0) {
				this.baseDAO.update(equipmentCollar);
			} else {
				this.baseDAO.save(equipmentCollar);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public EquipmentCollar readEquipmentCollar(Integer id) {
		try {
			EquipmentCollar equipmentCollar = (EquipmentCollar) this.baseDAO.get(EquipmentCollar.class, id);
			return equipmentCollar;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
