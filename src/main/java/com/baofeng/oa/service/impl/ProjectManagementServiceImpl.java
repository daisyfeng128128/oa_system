package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.BaseEntity.EntityStatus;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.ProjectManagementBean;
import com.baofeng.oa.dao.PlatformsActoresDAO;
import com.baofeng.oa.dao.ProjectManagementDAO;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.ProjectManagement;
import com.baofeng.oa.service.IProjectManagementService;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;
import com.baofeng.work.WorkManagers;

@Service("projectManagementService")
public class ProjectManagementServiceImpl implements IProjectManagementService {
	
	@Autowired
	private ProjectManagementDAO projectManagementDAO;
	@Autowired
	private IMonitorLogService monitorLogService;
	@Autowired
	private PlatformsActoresDAO platformsActoresDAO;
	@Autowired
	private WorkManagers workManagers;

	@Override
	@SuppressWarnings("rawtypes")
	public NPageResult<?> PagesProject(int pageSize, int curPage,String sortName, String sortOrder, Integer did,SearchFilter filter, String queryFilter) {
		NPageResult $page = this.projectManagementDAO.readAllPagesProject(pageSize, curPage, sortName, sortOrder, queryFilter, filter, queryFilter);
		return $page;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readAllPages(int pageSize, int curPage,String sortName, String sortOrder, String name,
			List<RoleDetailsAtts> platList, List<RoleDetailsAtts> attsList,SearchFilter searchFilter) {
		NPageResult rows = this.projectManagementDAO.readAllPages(pageSize, curPage, sortName, sortOrder, name, searchFilter);
		if (rows != null && rows.getData().size() > 0) {
			List<ProjectManagementBean> list = new ArrayList<ProjectManagementBean>();
			boolean isViews = false;
			for (RoleDetailsAtts atts : attsList) {
				if ("views".equals(atts.getOpkey())) {
					isViews = true;
					break;
				}
			}
			List<Integer> projectid = new ArrayList<Integer>();
			for (RoleDetailsAtts atts : platList) {
				projectid.add(Integer.valueOf(atts.getOpkey()));
			}
			for (Object o : rows.getData()) {
				ProjectManagement post = (ProjectManagement) o;
				if (isViews || projectid.contains(post.getId().intValue())) {
					ProjectManagementBean bean = new ProjectManagementBean();
					if (post.getProjectPlat() != null) {
						List<String> ppList = new ArrayList<String>();
							for (Platforms pp :post.getProjectPlat()) {
								ppList.add(pp.getPlatName());
						}
						bean.setProjectPlat(new ArrayList<String>(ppList));
					}
					bean.setId(post.getId());
					bean.setProjectName(post.getProjectName());
					bean.setDate(post.getCreateDT());
					bean.setRemarks(post.getRemarks());
					if (post.getProjectManager() != null)
						bean.setProjectManager(post.getProjectManager().getName());
					list.add(bean);
				}
			}
			rows.setData(new ArrayList(list));
		}
		return rows;
	}

	@Override
	public boolean addProject(ProjectManagement post) {
		if (post != null && post.getId() != null) {
			ProjectManagement $post = this.readProject(post.getId());
			if ($post != null) {
					List<Platforms> ppList = new ArrayList<Platforms>();
					for (Platforms pp :$post.getProjectPlat()) {
						ppList.add(pp);
					}
						$post.setProjectPlat(ppList);
				$post.setProjectName(post.getProjectName());
				$post.setProjectPlat(post.getProjectPlat());
				post = $post;
			}
			if (this.projectManagementDAO.saveProject(post)) {
				return true;
			}
		}else{
			if (this.projectManagementDAO.saveProject(post)) {
				return true;
			}
		}
		return false;
	}

	private ProjectManagement readProject(Integer id) {
		if (id != null) {
			return this.projectManagementDAO.findProject(id);
		}
		return null;
	}

	@Override
	public ProjectManagement readProjectM(Integer id) {
		if (id != null) {
			return this.projectManagementDAO.findProject(id);
		}
		return null;
	}

	@Override
	public boolean deleteProject(Integer id, HttpServletRequest request) {
		if (id != null && id.intValue() > 0) {
			ProjectManagement project = this.readProject(id);
			if (project != null) {
				project.setStatus(EntityStatus.DELETED);
				if (this.projectManagementDAO.deleteProject(project)) {
					return true;	
				}
			}
		}
		return false;
	}

}
