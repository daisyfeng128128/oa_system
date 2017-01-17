package com.baofeng.commons.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.bean.RoleUsersBean;
import com.baofeng.commons.entity.MenuItem;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.commons.service.IOperatorService;
import com.baofeng.oa.bean.EmployeeBean;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.ResultMsg;

@Controller
public class OperatorController extends BaseController {

	@Autowired
	private IOperatorService userService;
	@Autowired
	private IMonitorLogService monitorLogService;
	@Autowired
	private IBranchOfficeService branchOfficeService;

	public IOperatorService getUserService() {
		return userService;
	}

	public void setUserService(IOperatorService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page/login");
		String agent = request.getHeader("User-Agent").toLowerCase();
		Boolean chrome = false;
		if (agent != null && (agent.indexOf("chrome") > 0) || agent.indexOf("firefox") > 0 || agent.indexOf("safari") > 0) {
			chrome = true;
		}
		mav.addObject("chrome", chrome);
		return mav;
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public ModelAndView userLogin(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page/login");
		String agent = request.getHeader("User-Agent").toLowerCase();
		Boolean chrome = false;
		if (agent != null && (agent.indexOf("chrome") > 0) || agent.indexOf("firefox") > 0 || agent.indexOf("safari") > 0) {
			chrome = true;
		}
		mav.addObject("chrome", chrome);
		if (!chrome) {
			return mav;
		}
		RoleUsers user = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);
		if (user != null) {
			mav.setViewName("page/baseframe");
			mav.addObject("user", user);
		}
		return mav;
	}

	@RequestMapping(value = "/user/admins", method = RequestMethod.GET)
	public ModelAndView userAdLogin(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page/adLogin");
		RoleUsers user = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);
		if (user != null) {
			mav.setViewName("page/baseframe");
			mav.addObject("user", user);
		}
		return mav;
	}

	@RequestMapping(value = "/user/adLogin", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView login(String loginName, String loginPwd, Integer branchs, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("page/adLogin");
		if (loginName != null && loginName.trim().length() > 0 && loginPwd != null && loginPwd.trim().length() > 0) {
			if (branchs == null)
				branchs = -1;
			RoleUsers user = this.userService.validation(loginName, loginPwd, branchs);
			if (user != null && user.getUser() != null) {
				mav.addObject("user", user);
				mav.setViewName("page/baseframe");
				request.getSession().setAttribute(Constants.CURRENT_USER, user);
				request.getSession().setAttribute(Constants.CURRENT_BRANCHS, user.getBranchs().toString());
				this.monitorLogService.logsLogin(request);
			}
		}
		return mav;
	}

	@ResponseBody
	@RequestMapping("/jump")
	public ResultMsg loginTwo(String loginName, String loginPwd, Integer branchs, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (loginName != null && loginName.trim().length() > 0 && loginPwd != null && loginPwd.trim().length() > 0) {
			RoleUsers user = this.userService.validation(loginName, loginPwd, branchs);
			if (user != null && user.getUser() != null) {
				msg.setResultStatus(200);
				request.getSession().setAttribute(Constants.CURRENT_USER, user);
				request.getSession().setAttribute(Constants.CURRENT_BRANCHS, user.getBranchs().toString());
				this.monitorLogService.logsLogin(request);
			}
		}
		return msg;
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page/main");
		RoleUsers user = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);
		if (user != null) {
			mav.addObject("user", user);
		}
		return mav;
	}

	@RequestMapping(value = "/left", method = RequestMethod.GET)
	public ModelAndView left(Integer itemId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page/left");
		RoleUsers user = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);
		if (user != null) {
			List<MenuItem> itemList = this.userService.readMenuItem(user, itemId);
			BranchOffice $branchs = this.branchOfficeService.readBranchOffice(user.getBranchs());
			if (itemList != null) {
				mav.addObject("itemList", itemList);
			}
			mav.addObject("user", user);
			mav.addObject("branchs_msg", $branchs == null ? "" : $branchs.getIpgp());
		}
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/user/logout", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView logout(Model model, HttpServletRequest request) {
		request.getSession().removeAttribute(Constants.CURRENT_USER);
		ModelAndView mav = new ModelAndView("page/logout");
		return mav;
	}

	/**
	 * 功能：搜索用户
	 * */
	@ResponseBody
	@RequestMapping(value = "/user/search", method = RequestMethod.POST)
	public List<EmployeeBean> seachOperator(@RequestBody EmployeeBean bean, HttpServletRequest request) {
		List<EmployeeBean> result = new ArrayList<EmployeeBean>();
		if (bean != null && bean.getName().trim().length() > 0) {
			result = this.userService.readEmployeeBeanList(bean.getName(), Boolean.valueOf(bean.getLoadTable()), Boolean.valueOf(bean.getSeachEmp()),
					Boolean.valueOf(bean.getSeachAct()), super.searchFilter(request));
		}
		return result;
	}

	/**
	 * 功能：搜索员工
	 * */
	@ResponseBody
	@RequestMapping(value = "/user/searchEmp", method = RequestMethod.POST)
	public List<RoleUsersBean> searchEmp(@RequestBody EmployeeBean bean, HttpServletRequest request) {
		List<RoleUsersBean> result = new ArrayList<RoleUsersBean>();
		if (bean != null && bean.getName().trim().length() > 0) {
			result = this.userService.readRoleUsers(bean.getName(), super.searchFilter(request));
		}
		return result;
	}

}
