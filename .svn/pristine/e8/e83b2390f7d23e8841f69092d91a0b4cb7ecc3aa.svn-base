package com.baofeng.oa.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.oa.entity.PositiveReview;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Repository("positiveReviewDao")
public class PositiveReviewDao {
	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readPagesActores(int pageSize, int curPage, String sortName, String sortOrder, List<RoleDetailsAtts> platList, Integer type,
			SearchFilter searchFilter) {
		try {
			DetachedCriteria detachedCriteria = null;
			if (type == 1) {
				detachedCriteria = DetachedCriteria.forClass(PlatformsActores.class);
				detachedCriteria.createAlias("actores","actores");
				detachedCriteria.add(Restrictions.eq("actores.genrer",Genres.SHIYONG ));
				detachedCriteria.add(Restrictions.eq("actores.genre",LineGenres.OFFLINE));
				detachedCriteria.addOrder(Order.asc("mainPlatform"));
			} else if (type == 2){
				detachedCriteria = DetachedCriteria.forClass(PlatformsManager.class);
				detachedCriteria.createAlias("manager", "manager");
				detachedCriteria.add(Restrictions.eq("manager.genre", LineGenres.OFFLINE));
				detachedCriteria.add(Restrictions.eq("manager.genrer",Genres.SHIYONG ));
			}
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, searchFilter);
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	@SuppressWarnings("rawtypes")
//	public NPageResult readPagesActores(int pageSize, int curPage, String sortName, String sortOrder, List<RoleDetailsAtts> platList, Integer type,
//			SearchFilter searchFilter) {
//		try {
//			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
//			if (type == 1) {
//				Disjunction disjunction = Restrictions.disjunction();
//				disjunction.add(Restrictions.eq("employeType", EmployeType.ACTORES));
//				disjunction.add(Restrictions.eq("actores", Actores.YES));
//				detachedCriteria.add(disjunction);
//			} else if (type == 2)
//				detachedCriteria.add(Restrictions.eq("employeType", EmployeType.MANAGER));
//			detachedCriteria.add(Restrictions.eq("genrer", Operator.Genres.SHIYONG));
//			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
//			detachedCriteria.addOrder(Order.asc("joinDate"));
//			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, searchFilter);
//			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	@SuppressWarnings("unchecked")
	public PositiveReview readPositiveReviewByNumber(Integer number) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PositiveReview.class);
			detachedCriteria.add(Restrictions.eq("number", number));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			List<PositiveReview> list = (List<PositiveReview>) this.baseDAO.findAllByCriteria(detachedCriteria);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsActores> readAllPacByEmpId(Integer id) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsActores.class);
			detachedCriteria.createAlias("actores", "actores").createAlias("actores.employee", "emp").add(Restrictions.eq("emp.id", id));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("mainPlatform"));
			return (List<PlatformsActores>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<PlatformsManager> readAllPmaByEmpId(Integer id) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PlatformsManager.class);
			detachedCriteria.createAlias("manager", "manager").createAlias("manager.employee", "emp").add(Restrictions.eq("emp.id", id));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<PlatformsManager>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addApply(PositiveReview posi) {
		try {
			if (posi != null && posi.getId() != null && posi.getId().intValue() > 0) {
				this.baseDAO.update(posi);
			} else {
				this.baseDAO.save(posi);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, List<RoleDetailsAtts> platList, Integer type, SearchFilter searchFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PositiveReview.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.ne("type", 2));
			if(type!= null && type==1)
				detachedCriteria.add(Restrictions.eq("empType", 1));
			else
				detachedCriteria.add(Restrictions.eq("empType", 2));
			Disjunction disjunction = Restrictions.disjunction();
			for (RoleDetailsAtts atts : platList) {
				disjunction.add(Restrictions.eq("platId", Integer.valueOf(atts.getOpkey())));
			}
			detachedCriteria.add(disjunction);
			detachedCriteria.addOrder(Order.asc("type"));
			detachedCriteria.addOrder(Order.desc("joinDate"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, searchFilter);
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public PositiveReview readPositiveReview(Integer id) {
		try {
			if(id != null && id.intValue() > 0){
				return (PositiveReview)this.baseDAO.get(PositiveReview.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
