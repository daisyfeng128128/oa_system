package com.baofeng.commons.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.baofeng.commons.dao.OperatorDao;
import com.baofeng.commons.entity.MenuItem;
import com.baofeng.commons.entity.MenuItemDetails;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.RoleDetails;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleDetailsAtts.Enabled;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.entity.Roles;
import com.baofeng.commons.service.IMenuitemService;
import com.baofeng.commons.service.IOperatorService;
import com.baofeng.commons.service.IRolesService;
import com.baofeng.oa.entity.BranchUser;
import com.baofeng.utils.Constants;
import com.baofeng.utils.SearchFilter;
import com.baofeng.utils.SearchRule;

public class BaseController {

	@Autowired
	private OperatorDao userDao;
	@Autowired
	private IRolesService rolesService;
	@Autowired
	private IOperatorService userService;
	@Autowired
	private IMenuitemService menuitemService;

	public RoleUsers getSessionAttributeUser(HttpServletRequest request) {
		if (request.getSession().getAttribute(Constants.CURRENT_USER) != null) {
			RoleUsers users = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);
			return users;
		}
		return null;
	}

	/** 读取session属性 */
	public Integer getSessionAttribute(HttpServletRequest request, String attributes) {
		if (request.getSession().getAttribute(attributes) != null) {
			String branchs = (String) request.getSession().getAttribute(attributes);
			return Integer.valueOf(branchs);
		}
		return null;
	}

	/** 分公司验证条件 */
	public SearchFilter searchFilter(HttpServletRequest request, Object... args) {
		SearchFilter filter = new SearchFilter();
		if (request.getSession().getAttribute(Constants.CURRENT_BRANCHS) != null) {
			Integer branchs = Integer.valueOf(0);
			if (args != null && args.length > 0) {
				try {
					branchs = Integer.valueOf(args[0].toString());
				} catch (Exception e) {
				}
			}
			if (branchs.intValue() == 0) {
				String $ = (String) request.getSession().getAttribute(Constants.CURRENT_BRANCHS);
				branchs = Integer.valueOf($);
			}
			if (Integer.valueOf(branchs) > 0) {
				SearchRule rule = new SearchRule();
				rule.setField("branchs");
				rule.setOp("eq");
				rule.setData(branchs);
				List<SearchRule> rules = new ArrayList<SearchRule>();
				rules.add(rule);
				filter.setGroupOp("eq");
				filter.setRules(rules);
			}
		}
		return filter;
	}

	/** 分公司验证条件 */
	public SearchFilter searchBranchsFilter(HttpServletRequest request, Integer branchs) {
		SearchFilter filter = new SearchFilter();
		if (request.getSession().getAttribute(Constants.CURRENT_BRANCHS) != null) {
			if (branchs.intValue() == 0) {
				String $ = (String) request.getSession().getAttribute(Constants.CURRENT_BRANCHS);
				branchs = Integer.valueOf($);
			}
			if (Integer.valueOf(branchs) > 0) {
				SearchRule rule = new SearchRule();
				rule.setField("branchs");
				rule.setOp("eq");
				rule.setData(branchs);
				List<SearchRule> rules = new ArrayList<SearchRule>();
				rules.add(rule);
				filter.setGroupOp("eq");
				filter.setRules(rules);
			}
		}
		return filter;
	}

	/** 验证用户密码 */
	public boolean isUserLoging(HttpServletRequest request, String passwd) {
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		RoleUsers operator = this.userService.validation(users.getUser().getAccounts(), passwd, users.getUser().getBranchs());
		if (operator != null && operator.getUser() != null) {
			return true;
		}
		return false;
	}

	/**
	 * 功能： 判断是否存在某个权限
	 * */
	public boolean isHaveAttribute(HttpServletRequest request, String attsUrl, String key) {
		List<RoleDetailsAtts> attsList = this.attributeRoleDetails(request, attsUrl);
		if (key != null && key.trim().length() > 0) {
			for (int i = 0; i < attsList.size(); i++) {
				RoleDetailsAtts atts = attsList.get(i);
				if (key.contains(atts.getOpkey())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 功能：页面操作权限
	 * */
	public List<RoleDetailsAtts> attributeRoleDetails(HttpServletRequest request, String attsUrl, String... args) {
		List<RoleDetailsAtts> attsList = new ArrayList<RoleDetailsAtts>();
		Map<String, List<RoleDetailsAtts>> $map = new HashMap<String, List<RoleDetailsAtts>>();
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (users != null) {
			List<String> $attsList = new ArrayList<String>();
			MenuItem $menu = this.menuitemService.readMenuItem(attsUrl);
			if ($menu != null && $menu.getDetails() != null && $menu.getDetails().size() > 0) {
				List<MenuItemDetails> detailsList = $menu.getDetails();
				for (MenuItemDetails details : detailsList) {
					try {
						Integer.parseInt(details.getOpkey());
					} catch (Exception e) {
						RoleDetailsAtts atts = new RoleDetailsAtts();
						atts.setOpName(details.getOpName());
						atts.setOpkey(details.getOpkey());
						atts.setViews(Enabled.YES);
						if (details.getViews() != null && "N".equals(details.getViews()))
							atts.setViews(Enabled.NO);
						$attsList.add(atts.getOpkey());
					}
				}
			}
			if (users.getUser().getGenrer() == Genres.MANAGER || users.getUser().getGenrer() == Genres.SUPERS) {
				MenuItem menu = this.menuitemService.readMenuItem(attsUrl);
				if (menu != null && menu.getDetails() != null && menu.getDetails().size() > 0) {
					List<MenuItemDetails> detailsList = menu.getDetails();
					for (MenuItemDetails details : detailsList) {
						try {
							Integer.parseInt(details.getOpkey());
						} catch (Exception e) {
							RoleDetailsAtts atts = new RoleDetailsAtts();
							atts.setOpName(details.getOpName());
							atts.setOpkey(details.getOpkey());
							atts.setViews(Enabled.YES);
							if (details.getViews() != null && "N".equals(details.getViews()))
								atts.setViews(Enabled.NO);
							attsList.add(atts);
						}
					}
				}
			} else {
				List<Roles> rolesList = new ArrayList<Roles>();
				RoleUsers $users = this.userDao.readRoleUsers(users.getId());
				if ($users != null && $users.getBranchs() == users.getBranchs()) {
					rolesList.addAll($users.getDetails());
				} else {
					BranchUser $ = this.userDao.findBranchUser(users.getId(), users.getBranchs());
					if ($ != null && $.getDetails() != null && $.getDetails().size() > 0) {
						rolesList.addAll($.getDetails());
					} else {
						if ($ != null && $.getRole() != null)
							rolesList.add($.getRole());
					}
				}
				for (Roles roles : rolesList) {
					roles = this.rolesService.readRoles(roles.getId());
					List<RoleDetails> detailsList = roles.getDetails();
					for (RoleDetails details : detailsList) {
						if (attsUrl.equals(details.getItemUrl())) {
							if (details.getDetails() != null && details.getDetails().size() > 0) {
								if (!$map.containsKey(attsUrl)) {
									$map.put(details.getItemUrl(), details.getDetails());
								}else{
									Map<String, RoleDetailsAtts> map = new HashMap<String, RoleDetailsAtts>();
									for(RoleDetailsAtts atts  :$map.get(details.getItemUrl())){
										map.put(atts.getOpkey(), atts);
									}
									for(RoleDetailsAtts atts :details.getDetails()){
										map.put(atts.getOpkey(), atts);
									}
									List<RoleDetailsAtts> list = new ArrayList<RoleDetailsAtts>(map.values());
									$map.put(details.getItemUrl(), list);
								}
							}
						}
					}
				}
			}
			if ($map.get(attsUrl) != null) {
				List<RoleDetailsAtts> $temp = $map.get(attsUrl);
				for (RoleDetailsAtts atts : $temp) {
					try {
						Integer.parseInt(atts.getOpkey());
					} catch (Exception e) {
						if (atts.getViews() != null && "N".equals(atts.getViews())) {
							atts.setViews(Enabled.NO);
						}
						attsList.add(atts);
					}
				}
			}
			if (args != null && args.length > 0) {
				List<String> remAtts = Arrays.asList(args);
				List<RoleDetailsAtts> $atts = new ArrayList<RoleDetailsAtts>();
				for (int i = 0; i < attsList.size(); i++) {
					RoleDetailsAtts atts = attsList.get(i);
					if (remAtts.contains(atts.getOpkey())) {
						$atts.add(atts);
					}
					if (!$attsList.contains(atts.getOpkey())) {
						$atts.add(atts);
					}
				}
				attsList.removeAll($atts);
			}
			if ($attsList.size() > 0) {
				List<RoleDetailsAtts> $atts = new ArrayList<RoleDetailsAtts>();
				for (int i = 0; i < attsList.size(); i++) {
					RoleDetailsAtts atts = attsList.get(i);
					if (!$attsList.contains(atts.getOpkey())) {
						$atts.add(atts);
					}
				}
				attsList.removeAll($atts);
			}
		}
		return attsList;
	}

	/**
	 * 功能：页面平台权限
	 * 
	 * @param searchFilter
	 * */
	public List<RoleDetailsAtts> platformsRoleDetails(HttpServletRequest request, String attsUrl) {
		List<RoleDetailsAtts> platList = new ArrayList<RoleDetailsAtts>();
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (users != null) {
			attsUrl = attsUrl == null ? "plats/show.do" : attsUrl;
			if (users.getUser().getGenrer() == Genres.MANAGER || users.getUser().getGenrer() == Genres.SUPERS) {
				MenuItem menu = this.menuitemService.readMenuItem(attsUrl);
				if (menu != null && menu.getDetails() != null && menu.getDetails().size() > 0) {
					List<MenuItemDetails> detailsList = menu.getDetails();
					for (MenuItemDetails details : detailsList) {
						try {
							Integer.parseInt(details.getOpkey());
							RoleDetailsAtts atts = new RoleDetailsAtts();
							atts.setOpName(details.getOpName());
							atts.setOpkey(details.getOpkey());
							platList.add(atts);
						} catch (Exception e) {
						}
					}
				}
			} else {
				List<Roles> rolesList = new ArrayList<Roles>();
				RoleUsers $users = this.userDao.readRoleUsers(users.getId());
				if ($users != null && $users.getBranchs() == users.getBranchs()) {
					rolesList.addAll($users.getDetails());
				} else {
					BranchUser $ = this.userDao.findBranchUser(users.getId(), users.getBranchs());
					if ($ != null && $.getDetails() != null && $.getDetails().size() > 0) {
						rolesList.addAll($.getDetails());
					} else {
						if ($ != null && $.getRole() != null)
							rolesList.add($.getRole());
					}
				}
				for (Roles roles : rolesList) {
					roles = this.rolesService.readRoles(roles.getId());
					List<RoleDetails> detailsList = roles.getDetails();
					for (RoleDetails details : detailsList) {
						if (attsUrl.equals(details.getItemUrl())) {
							if (details.getDetails() != null && details.getDetails().size() > 0) {
								List<RoleDetailsAtts> roleDetails = details.getDetails();
								platList.addAll(roleDetails);
								for (RoleDetailsAtts atts : details.getDetails()) {
									try {
										Integer.parseInt(atts.getOpkey());
									} catch (Exception e) {
										platList.remove(atts);
									}
								}
							}
						}
					}
				}

			}
		}
		return platList;
	}
}
