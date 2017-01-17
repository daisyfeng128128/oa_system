package com.baofeng.oa.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.oa.bean.FinStatisticcsBean;
import com.baofeng.oa.entity.SalariesStatistics;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.SearchFilter;

@Repository("salariesStatisticsDAO")
public class SalariesStatisticsDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("unchecked")
	public List<SalariesStatistics> findSalariesStatistics(Date date1, Date date2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SalariesStatistics.class);
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<SalariesStatistics> list = (List<SalariesStatistics>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if(list!= null && list.size()>0){
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<SalariesStatistics> readPage(Date date1, Date date2, SearchFilter filter) {
		try {
			DetachedCriteria detachedCriteria=DetachedCriteria.forClass(SalariesStatistics.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.between("createDT", date1, date2));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return (List<SalariesStatistics>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<FinStatisticcsBean> findAllFinStatisticcsBean(Date date1, Date date2) {
		try {
			String sql = "select f.positionsName,f.deptName ,count(*) as amount ,sum(f.realExpenditure) as realTotal FROM finSalaries f where f.createDT between ? and ? group by f.deptName";
			SQLQuery query = this.baseDAO.getCurrentSession().createSQLQuery(sql);
			query.setDate(0, date1);
			query.setDate(1, date2);
			List<?> list =query.list();
			List<FinStatisticcsBean> listBean = new ArrayList<FinStatisticcsBean>();
			if(list != null && list.size()>0){
				for(Object o : list){
					if (o instanceof Object[]) {
						Object[] $o = (Object[]) o;
						FinStatisticcsBean bean = new FinStatisticcsBean();
						bean.setPositionsName((String)$o[0]);
						bean.setDeptName((String)$o[1]);
						bean.setAmount(Integer.valueOf($o[2].toString()) );
						bean.setRealTotal((BigDecimal)$o[3]);
						listBean.add(bean);
					}
					
				}
			}
			return listBean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addSalariesStatistics(SalariesStatistics post) {
		try {
			if(post != null && post.getId()!= null  && post.getId().intValue()>0){
				this.baseDAO.update(post);
			}else{
				this.baseDAO.save(post);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteSalariesStatistics(SalariesStatistics post) {
		try {
			this.baseDAO.delete(post);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public List<FinStatisticcsBean> findAllFinStatisticcsBeanOnline(Date date1, Date date2) {
		try {
			String sql = "select f.entityClass as deptName ,count(*) as amount ,sum(f.realExpenditure) as realTotal FROM finSalariesOnline f where f.createDT between ? and ? group by f.entityClass";
			SQLQuery query = this.baseDAO.getCurrentSession().createSQLQuery(sql);
			query.setDate(0, date1);
			query.setDate(1, date2);
			List<?> list =query.list();
			List<FinStatisticcsBean> listBean = new ArrayList<FinStatisticcsBean>();
			if(list != null && list.size()>0){
				for(Object o : list){
					if (o instanceof Object[]) {
						Object[] $o = (Object[]) o;
						FinStatisticcsBean bean = new FinStatisticcsBean();
						bean.setDeptName((String)$o[0]);
						bean.setAmount(Integer.valueOf($o[1].toString()) );
						bean.setRealTotal((BigDecimal)$o[2]);
						listBean.add(bean);
					}
					
				}
			}
			return listBean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<FinStatisticcsBean> findAllFinStatisticcsBeanTalent(Date date1, Date date2) {
		try {
			String sql = "select count(*) as amount ,sum(f.realitySalary) as realTotal FROM finSalariesTalent f where f.createDT between ? and ? ";
			SQLQuery query = this.baseDAO.getCurrentSession().createSQLQuery(sql);
			query.setDate(0, date1);
			query.setDate(1, date2);
			List<?> list =query.list();
			List<FinStatisticcsBean> listBean = new ArrayList<FinStatisticcsBean>();
			if(list != null && list.size()>0){
				for(Object o : list){
					if (o instanceof Object[]) {
						Object[] $o = (Object[]) o;
						FinStatisticcsBean bean = new FinStatisticcsBean();
						bean.setAmount(Integer.valueOf($o[0].toString()) );
						bean.setRealTotal((BigDecimal)$o[1]);
						listBean.add(bean);
					}
					
				}
			}
			return listBean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	 
	
}
