package com.baofeng.oa.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.service.IMenuitemService;
import com.baofeng.oa.bean.ProjectManagementBean;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.ProjectManagement;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.oa.service.IProjectManagementService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 项目管理
 */
@Controller
@RequestMapping("/projectManagement")
public class ProjectManagementController extends BaseController {

	@Autowired
	private IProjectManagementService projectManagementService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IMenuitemService menuitemService;
	@Autowired
	private IPlatformsService platformsService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(String gid, String linktype, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/projectManagement");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("projectManagement/show.do"));
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 功能：项目列表
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPagesProject", method = RequestMethod.POST)
	public NPageResult<?> readPagesProject(int pageSize, int curPage, String sortName, String sortOrder, Integer did, String filter, HttpServletRequest request) {
		NPageResult<?> pages = this.projectManagementService.PagesProject(pageSize, curPage, sortName, sortOrder, did, null, filter);
		return pages;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, String name, HttpServletRequest request) {
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("projectManagement/show.do"));
		NPageResult pages = this.projectManagementService.readAllPages(pageSize, curPage, sortName, sortOrder, name, platList, attsList, null);
		return pages;
	}

	/**
	 * 功能：添加修改
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultMsg save(@RequestBody ProjectManagementBean bean,HttpServletRequest request){
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		ProjectManagement post = null;
		if (bean.getId() != null && bean.getId().intValue() > 0){
			post=this.projectManagementService.readProjectM(Integer.valueOf(bean.getId()));
			post.setId(bean.getId());
		}else{
			post= new ProjectManagement();
		}
		post.setBranchs(branchs);
		post.setProjectName(bean.getProjectName());
		post.setRemarks(bean.getRemarks());
		if (bean.getPlatIds() != null) {
			List<Platforms> ppList = new ArrayList<Platforms>();
			for (String pid : bean.getPlatIds()) {
				Platforms plat = this.platformsService.findPlatforms(Integer.valueOf(pid));
				ppList.add(plat);
			}
			post.setProjectPlat(ppList);
		}
		if (bean.getEmpidP()!= null) {
			Employee emp = this.employeeService.readEmployee(bean.getEmpidP());
			post.setProjectManager(emp);
		} else if (bean.getEmpids() != null) {
			Employee emp = this.employeeService.readEmployee(bean.getEmpids());
			post.setProjectManager(emp);
		}
		if (this.projectManagementService.addProject(post)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能：编辑
	 * */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ProjectManagementBean read(Integer id) {
		ProjectManagementBean bean = new ProjectManagementBean();
		ProjectManagement project = this.projectManagementService.readProjectM(id);
		if (project != null) {
			bean.setId(project.getId());
			bean.setProjectName(project.getProjectName());
			bean.setRemarks(project.getRemarks());
			if (project.getProjectPlat() != null) {
				Set<String> ppList = new HashSet<String>();
					for (Platforms pp :project.getProjectPlat()) {
						ppList.add(pp.toString());
				}
				bean.setProjectPlat(new ArrayList<String>(ppList));
			}
			if (project.getProjectManager() != null)
				bean.setProjectManager(project.getProjectManager().getId().toString());
		}
		return bean;
	}

	/**
	 * 功能：删除
	 * */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResultMsg delete(Integer id, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (this.projectManagementService.deleteProject(id, request)) {
			msg.setResultStatus(200);
		}
		return msg;
	}

}
