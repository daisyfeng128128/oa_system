package com.baofeng.oa.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.entity.ProjectManagement;
import com.baofeng.utils.DetachedCriteriaUtil;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

@Repository("projectManagementDAO")
public class ProjectManagementDAO {
	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readAllPagesProject(int pageSize, int curPage,String sortName, String sortOrder, String queryFilter,SearchFilter filter, String queryFilter2) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProjectManagement.class);if (queryFilter != null && !queryFilter.trim().equals(""))
				detachedCriteria.add(Restrictions.like("projectName", "%" + queryFilter + "%"));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.desc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, filter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public NPageResult readAllPages(int pageSize, int curPage, String sortName,String sortOrder, String name, SearchFilter searchFilter) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProjectManagement.class);
			if (name != null && name.trim().length() > 0)
				detachedCriteria.add(Restrictions.like("projectName", "%" + name + "%"));
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			detachedCriteria.addOrder(Order.asc("createDT"));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, searchFilter);
			return baseDAO.NfindByPages(detachedCriteria, pageSize, curPage);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ProjectManagement findProject(Integer id) {
		try {
			ProjectManagement project = (ProjectManagement) this.baseDAO.get(ProjectManagement.class, id);
			return project;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveProject(ProjectManagement post) {
		try {
			if (post != null) {
				this.baseDAO.saveOrUpdate(post);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean deleteProject(ProjectManagement project) {
		try {
			if (project != null) {
				this.baseDAO.saveOrUpdate(project);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public PageResult<?> readAllPagesSkip(int rows, int page, SearchFilter fil,String filter, List<RoleDetailsAtts> platList) {
		try {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ProjectManagement.class);
			if (platList != null && platList.size() > 0) {
				Disjunction disjunction = Restrictions.disjunction();
				for (RoleDetailsAtts atts : platList) {
					disjunction.add(Restrictions.eq("id", Integer.valueOf(atts.getOpkey())));
				}
				detachedCriteria.add(disjunction);
			} else {
				detachedCriteria.add(Restrictions.eq("id", -1));
			}
			detachedCriteria.add(Restrictions.eq("status", EntityStatus.NORMAL));
			DetachedCriteriaUtil.addSearchFilter(detachedCriteria, fil);
			detachedCriteria.addOrder(Order.desc("createDT"));
			return this.baseDAO.findByPages(detachedCriteria, rows, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
