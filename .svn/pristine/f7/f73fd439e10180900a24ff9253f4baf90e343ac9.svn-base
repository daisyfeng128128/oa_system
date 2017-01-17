package com.baofeng.oa.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.bean.EmpTransferBean;
import com.baofeng.oa.entity.EmployeeTransfer;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("empTransferDAO")
public class EmpTransferDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter searchFilter, String ads, List<RoleDetailsAtts> platList) {
			try {
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeTransfer.class);
				detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
				detachedCriteria.addOrder(Order.asc("transferDate"));
				return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EmpTransferBean> readEmpTransfer(Integer number, String name, List<RoleDetailsAtts> platList) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmpTransferBean.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			try {
				detachedCriteria.add(Restrictions.eq("number", number));
				detachedCriteria.add(Restrictions.eq("name", name));
			} catch (Exception e) {
				detachedCriteria.add(Restrictions.eq("number", number));
				detachedCriteria.add(Restrictions.eq("name", name));
			}
			detachedCriteria.addOrder(Order.desc("transferDate"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, null);
			List<EmpTransferBean> list = (List<EmpTransferBean>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0 && list.get(0) != null) {
				return (List<EmpTransferBean>) list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public EmployeeTransfer readTraEmployee(Integer number) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeTransfer.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("number", number));
			List<EmployeeTransfer> detailsList = (List<EmployeeTransfer>) this.baseDAO.findAllByCriteria(detachedCriteria);
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
	public List<EmployeeTransfer> readTraEmployeeByDate(Date $date1, Date $date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeTransfer.class);
			detachedCriteria.add(Restrictions.between("transferDate", $date1, $date2));
			List<EmployeeTransfer> detailsList = (List<EmployeeTransfer>) this.baseDAO.findAllByCriteria(detachedCriteria);
			return detailsList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveEmployeeTra(EmployeeTransfer employeeT) {
		try {
			if (employeeT != null && employeeT.getId() != null && employeeT.getId().intValue() > 0) {
				this.baseDAO.mrege(employeeT);
			} else {
				this.baseDAO.save(employeeT);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
