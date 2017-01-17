package com.baofeng.oa.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.Roles;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

@Repository("branchOfficeDAO")
public class BranchOfficeDAO {

	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BranchOffice.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.asc("createDT"));
			return this.baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BranchOffice readBranch(Integer id) {
		try {
			if (id != null && id.intValue() > 0) {
				BranchOffice branch = (BranchOffice) this.baseDAO.get(BranchOffice.class, id);
				if (branch != null) {
					return branch;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addBranch(BranchOffice post) {
		try {
			if (post != null && post.getId() != null && post.getId().intValue() > 0) {
				this.baseDAO.update(post);
			} else {
				this.baseDAO.save(post);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(BranchOffice post) {
		try {
			if (post != null) {
				this.baseDAO.delete(post);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<BranchOffice> readAllBranchsByLogin() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BranchOffice.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.add(Restrictions.eq("islogin", 1));
			detachedCriteria.addOrder(Order.asc("createDT"));
			return (List<BranchOffice>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public PageResult<?> readAllPagesSkip(int rows, int page, SearchFilter filter, String queryFilter, Integer branchsId) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BranchOffice.class);
			if (queryFilter != null && !queryFilter.trim().equals(""))
				detachedCriteria.add(Restrictions.like("ipgp", "%" + queryFilter + "%"));
			detachedCriteria.add(Restrictions.ne("id", branchsId));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return this.baseDAO.findByPages(detachedCriteria, rows, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PageResult<?> readPagesAllRole(int rows, int page, SearchFilter filter, String queryFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Roles.class);
			if (queryFilter != null && !queryFilter.trim().equals(""))
				detachedCriteria.add(Restrictions.like("name", "%" + queryFilter + "%"));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return this.baseDAO.findByPages(detachedCriteria, rows, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<BranchOffice> findBranchOfficeList() {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BranchOffice.class);
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			return (List<BranchOffice>) this.baseDAO.findAllByCriteria(detachedCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
