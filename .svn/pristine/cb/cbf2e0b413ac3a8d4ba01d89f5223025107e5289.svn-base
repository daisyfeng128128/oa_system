package com.baofeng.commons.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.entity.MenuItem;
import com.baofeng.commons.entity.MenuItemDetails;
import com.baofeng.commons.entity.RoleDetails;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleDetailsAtts.Enabled;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.entity.Roles;
import com.baofeng.commons.service.IMenuitemService;
import com.baofeng.commons.service.IOperatorService;
import com.baofeng.commons.service.IRolesService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 功能：角色管理
 */
@Controller
@RequestMapping("/roles")
public class RolesController extends BaseController {

	@Autowired
	private IRolesService rolesService;
	@Autowired
	private IOperatorService userService;
	@Autowired
	private IMenuitemService menuitemService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("page/roles");
		RoleUsers users = getSessionAttributeUser(request);
		List<MenuItem> roleList = this.userService.readMenuItem(users, null);
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("roles/show.do"));
		if (roleList != null && roleList.size() > 0) {
			mav.addObject("menuList", roleList);
		}
		mav.addObject("attsList", attsList);
		return mav;
	}
	
	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, HttpServletRequest request) {
		NPageResult<?> pages = this.rolesService.readAllPages(pageSize, curPage, sortName, sortOrder, super.searchFilter(request));
		return pages;
	}

	/**
	 * 功能：添加修改
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(Integer id, String name, String describes, Integer[] roles, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("page/roles");
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("roles/show.do"));
		mav.addObject("attsList", attsList);
		Map<Integer, Object> urlMap = new HashMap<Integer, Object>();
		List<?> list = this.menuitemService.readAllMenu();
		if (list != null && list.size() > 0) {
			for (Object menu : list) {
				MenuItem m = (MenuItem) menu;
				urlMap.put(m.getId(), m.getUrl());
				if (m.getDetails() != null && m.getDetails().size() > 0) {
					for (MenuItemDetails details : m.getDetails()) {
						urlMap.put(details.getId().intValue() + Integer.MIN_VALUE, details);
					}
				}
			}
		}
		Roles $roles = new Roles();
		$roles.setId(id);
		$roles.setName(name);
		$roles.setBranchs(branchs);
		$roles.setDescribed(describes);
		Map<RoleDetails, List<RoleDetailsAtts>> map = new HashMap<RoleDetails, List<RoleDetailsAtts>>();
		if (roles != null && roles.length > 0) {
			for (Integer roleId : roles) {
				if (urlMap.get(roleId) != null) {
					RoleDetails details = new RoleDetails();
					details.setBranchs(branchs);
					details.setItemUrl(urlMap.get(roleId).toString());
					List<RoleDetailsAtts> pageDetails = new ArrayList<RoleDetailsAtts>();
					String[] roleDetails = request.getParameterValues("roleDetails" + roleId);
					if (roleDetails != null && roleDetails.length > 0) {
						for (String attsId : roleDetails) {
							MenuItemDetails $details = (MenuItemDetails) urlMap.get(Integer.valueOf(attsId) + Integer.MIN_VALUE);
							RoleDetailsAtts page = new RoleDetailsAtts();
							page.setBranchs(branchs);
							page.setOpkey($details.getOpkey());
							page.setOpName($details.getOpName());
							page.setViews(Enabled.YES);
							if ($details.getViews() != null && "N".equals($details.getViews()))
								page.setViews(Enabled.NO);
							page.setRoleDetails(details);
							pageDetails.add(page);
						}
					}
					map.put(details, pageDetails);
				}
			}
		}
		if (this.rolesService.addRoles(id, name, describes, $roles, map)) {
			List<?> menuList = this.menuitemService.readAllMenu();
			if (list != null && list.size() > 0) {
				mav.addObject("menuList", menuList);
			}
			mav.addObject("result", "success");
		}
		return mav;
	}

	/**
	 * 功能：编辑读取数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public Roles edit(Integer id) {
		if (id != null && id.intValue() > 0) {
			Roles roles = this.rolesService.readRoles(id);
			if (roles != null) {
				List<RoleDetails> details = new ArrayList<RoleDetails>();
				Map<String, Object> urlMap = new HashMap<String, Object>();
				List<?> list = this.menuitemService.readAllMenu();
				if (list != null && list.size() > 0) {
					for (Object menu : list) {
						MenuItem m = (MenuItem) menu;
						urlMap.put(m.getUrl(), m);
						if (m.getDetails() != null && m.getDetails().size() > 0) {
							for (MenuItemDetails $details : m.getDetails()) {
								urlMap.put(m.getId() + $details.getOpkey(), $details);
							}
						}
					}
				}
				for (RoleDetails $details : roles.getDetails()) {
					MenuItem menu = (MenuItem) urlMap.get($details.getItemUrl());
					if (menu != null) {
						$details.setItemId(menu.getId());
						if ($details.getDetails() != null) {
							List<RoleDetailsAtts> attsDetails = new ArrayList<RoleDetailsAtts>();
							for (RoleDetailsAtts atts : $details.getDetails()) {
								MenuItemDetails mdetails = (MenuItemDetails) urlMap.get(menu.getId() + atts.getOpkey());
								if (mdetails != null && mdetails.getId() != null) {
									atts.setItemMId(mdetails.getId());
									attsDetails.add(atts);
								}
							}
							$details.setDetails(attsDetails);
						}
						details.add($details);
					}
				}
				roles.setDetails(details);
				return roles;
			}
		}
		return null;
	}

	/**
	 * 功能：删除角色
	 * */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResultMsg delete(Integer id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(Integer.valueOf(100));
		if (id != null && id.intValue() > 0) {
			if (this.rolesService.deleteRoles(id)) {
				msg.setResultStatus(Integer.valueOf(200));
			}
		}
		return msg;
	}

	/**
	 * 功能：获取角色列表
	 * */
	@ResponseBody
	@RequestMapping(value = "/readList", method = RequestMethod.POST)
	public List<Roles> readRolesList(HttpServletRequest request) {
		List<Roles> result = new ArrayList<Roles>();
		List<Roles> list = this.rolesService.readRolesAlls(super.searchFilter(request));
		if (list != null && list.size() > 0) {
			result.addAll(list);
		}
		return result;
	}

}
