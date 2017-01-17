package com.baofeng.commons.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.bean.RoleUsersBean;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.service.IUsersService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.MD5;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 功能：登陆帐号管理
 * */
@Controller
@RequestMapping("/users")
public class UsersController extends BaseController {

	@Autowired
	private IUsersService usersService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page/users");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("users/show.do"));
		mav.addObject("attsList", attsList);
		return mav;
	}

	@RequestMapping(value = "/midPwd", method = RequestMethod.GET)
	public ModelAndView midPwd(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page/midPwd");
		return mav;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, HttpServletRequest request) {
		NPageResult<?> pages = this.usersService.readAllPages(pageSize, curPage, sortName, sortOrder, super.searchFilter(request));
		return pages;
	}

	/**
	 * 功能：添加登陆帐号
	 * */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultMsg saveRoleUser(@RequestBody RoleUsersBean user, HttpServletRequest request) {
		ResultMsg result = new ResultMsg();
		result.setResultStatus(Integer.valueOf(100));
		result.setResultMessage("error");
		if (user != null && user.getUserId() != null && user.getUserId().trim().length() > 0 && user.getRoleId() != null && user.getRoleId().trim().length() > 0) {
			Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
			if (!this.usersService.readRoleUsersByUId(user.getUserId())) {
				if (this.usersService.addRoleUsers(user.getUserId(), user.getRoleId(), branchs)) {
					result.setResultStatus(Integer.valueOf(200));
					result.setResultMessage("success");
				}
			} else {
				result.setResultStatus(Integer.valueOf(101));
			}
		}
		return result;
	}

	/**
	 * 功能：删除登陆帐号
	 * */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResultMsg deleteRoleUser(String id) {
		ResultMsg result = new ResultMsg();
		result.setResultStatus(Integer.valueOf(100));
		result.setResultMessage("error");
		if (id != null && id.trim().length() > 0) {
			if (this.usersService.deleteRoleUsers(id)) {
				result.setResultStatus(Integer.valueOf(200));
				result.setResultMessage("success");
			}
		}
		return result;
	}

	/**
	 * 功能：修改登陆用户角色
	 * */
	@ResponseBody
	@RequestMapping(value = "/updateRoleUsers", method = RequestMethod.POST)
	public ResultMsg updateRoleUsers(@RequestBody RoleUsersBean user) {
		ResultMsg result = new ResultMsg();
		result.setResultStatus(Integer.valueOf(100));
		result.setResultMessage("error");
		if (user != null && user.getId() != null && user.getId().trim().length() > 0 && user.getRoleId() != null && user.getRoleId().trim().length() > 0) {
			if (this.usersService.updateRoleUsers(user.getId(), user.getRoleId())) {
				result.setResultStatus(Integer.valueOf(200));
				result.setResultMessage("success");
			}
		}
		return result;
	}

	/**
	 * 功能：重置密码
	 * */
	@ResponseBody
	@RequestMapping(value = "/updatePasswd", method = RequestMethod.POST)
	public ResultMsg updatePasswd(@RequestBody RoleUsersBean user) {
		ResultMsg result = new ResultMsg();
		result.setResultStatus(Integer.valueOf(100));
		result.setResultMessage("error");
		if (user != null && user.getId() != null && user.getId().trim().length() > 0 && user.getPwd() != null && user.getPwd().trim().length() > 0) {
			if (this.usersService.updatePasswd(user.getId(), user.getPwd())) {
				result.setResultStatus(Integer.valueOf(200));
				result.setResultMessage("success");
			}
		}
		return result;
	}

	/**
	 * 功能：重置密码
	 * */
	@ResponseBody
	@RequestMapping(value = "/updatePersonPasswd", method = RequestMethod.POST)
	public ResultMsg updatePasswd(String oldPassWord, String newPassWord, HttpServletRequest request) {
		ResultMsg result = new ResultMsg();
		result.setResultStatus(Integer.valueOf(100));
		result.setResultMessage("error");
		RoleUsers user = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);
		if (user != null && user.getUser() != null) {
			if (user.getUser().getPassword().equals(MD5.MD5Encode(oldPassWord))) {
				user.getUser().setPassword(MD5.MD5Encode(newPassWord));
				if (this.usersService.updatePersonPasswd(user.getUser())) {
					result.setResultMessage("success");
					result.setResultStatus(200);
				}
			}
		}
		return result;
	}
}
