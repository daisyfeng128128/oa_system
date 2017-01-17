package com.baofeng.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.bean.DepartmentComboxBean;
import com.baofeng.oa.entity.Department;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.service.IDepartmentService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 部门管理
 */
@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private IEmployeeService employeeService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/department");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("department/show.do"));
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, String name, HttpServletRequest request) {
		NPageResult pages = null;
		pages = this.departmentService.readAllPages(pageSize, curPage, sortName, sortOrder, name, super.searchFilter(request));
		return pages;
	}

	/**
	 * 功能：删除
	 * */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResultMsg delete(Integer id, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("errors");
		if (this.departmentService.readDepartmentByParentId(id)) {
			msg.setResultStatus(100);
			msg.setData("有下属部门不能删除！");
			return msg;
		}
		if (this.departmentService.deleteDepartment(id, request)) {
			msg.setResultStatus(200);
			msg.setResultMessage("success");
		}
		return msg;
	}

	/**
	 * 功能：添加修改
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(Integer id, String name, Integer empid, Integer parentid, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/department");
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("department/show.do"));
		mav.addObject("attsList", attsList);
		Department post = new Department();
		post.setBranchs(branchs);
		if (id != null && id.intValue() > 0)
			post.setId(id);
		if (empid != null && empid > 0) {
			Employee emp = this.employeeService.readEmployee(empid);
			post.setManager(emp);
		}
		if (parentid != null && parentid.intValue() > 0) {
			Department parent = this.departmentService.readDepartment(parentid);
			post.setParent(parent);
		}

		post.setName(name);
		if (this.departmentService.addDepartment(post, request)) {
			mav.addObject("result", "success");
		}
		return mav;
	}

	/**
	 * 功能：编辑
	 * */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public DepartmentComboxBean read(Integer id) {
		Department department = this.departmentService.readDepartment(id);
		if (department != null) {
			DepartmentComboxBean bean = new DepartmentComboxBean();
			bean.setId(department.getId());
			bean.setName(department.getName());
			if (department.getManager() != null) {
				bean.setManager(department.getManager().getId());
			}
			if (department.getParent() != null) {
				bean.setParent(department.getParent().getId());
			}
			return bean;
		}
		return null;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPagesSkip", method = RequestMethod.POST)
	public PageResult<?> readPagesSkip(int page, int rows, String filter, HttpServletRequest request) {
		PageResult<?> pages = null;
		pages = this.departmentService.readAllPagesSkip(rows, page, super.searchFilter(request), filter);
		return pages;
	}

	@ResponseBody
	@RequestMapping(value = "/readPagesParent", method = RequestMethod.POST)
	public PageResult<?> readPagesParent(int page, int rows, Integer did, String filter, HttpServletRequest request) {
		PageResult<?> pages = null;
		pages = this.departmentService.readPagesParent(rows, page, did, super.searchFilter(request), filter);
		return pages;
	}

	/**
	 * 功能：编辑
	 * */
	@ResponseBody
	@RequestMapping(value = "/parent", method = RequestMethod.GET)
	public ResultMsg parent(Integer id, Integer parentid) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		Department parent = this.departmentService.readDepartment(parentid);
		if (parent.getParent().getId() != null && parent.getParent().getId() == id) {
			msg.setResultStatus(201);
			msg.setData("父级部门不能为现在的子集部门！");
		}
		return msg;
	}

}
