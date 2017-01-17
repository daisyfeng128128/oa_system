package com.baofeng.oa.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.MenuItem;
import com.baofeng.commons.entity.MenuItem.Dev;
import com.baofeng.commons.entity.MenuItemDetails;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.bean.MenusItemBean;
import com.baofeng.oa.service.IMenusService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.ResultMsg;
import com.baofeng.utils.SearchFilter;

/**
 * 直播平台
 */
@Controller
@RequestMapping("/menus")
public class MenusController extends BaseController {

	@Autowired
	private IMenusService menusService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/menusManager");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("menus/show.do"));
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 功能：菜单分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, HttpServletRequest request) {
		NPageResult pages = null;
		pages = this.menusService.readAllPages(pageSize, curPage, sortName, sortOrder);
		return pages;
	}

	/**
	 * 功能：添加修改
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(Integer id, String name, String url, Integer itemId, String dev, String divid, Integer indexs, String[] details,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/menusManager");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("menus/show.do"));
		mav.addObject("attsList", attsList);
		MenuItem menu = new MenuItem();
		MenuItem post = this.menusService.readMenu(itemId);
		if (id != null && id.intValue() > 0)
			menu.setId(id);
		menu.setDev(Enum.valueOf(Dev.class, dev));
		menu.setDivid(divid);
		menu.setIndexs(indexs);
		menu.setItem(post);
		menu.setName(name);
		menu.setUrl(url);
		if (this.menusService.addMenu(menu, details)) {
			mav.addObject("result", "success");
		}
		return mav;
	}

	/**
	 * 功能：上级菜单分页数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPagesSkip", method = RequestMethod.POST)
	public PageResult<?> readPagesSkip(int page, int rows, String filter, HttpServletRequest request) {
		PageResult<?> pages = null;
		SearchFilter $filter = new SearchFilter();
		pages = this.menusService.readAllPagesSkip(rows, page, $filter, filter);
		return pages;
	}

	/**
	 * 功能：上级菜单分页数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResultMsg deleteMenus(Integer id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		if (this.menusService.deleteMenus(id)) {
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
	public MenusItemBean read(Integer id) {
		MenusItemBean bean = new MenusItemBean();
		MenuItem menu = this.menusService.readMenu(id);
		if (menu != null) {
			bean.setId(menu.getId());
			bean.setDev(menu.getDev().toString());
			bean.setDivid(menu.getDivid());
			bean.setIndexs(menu.getIndexs());
			bean.setName(menu.getName());
			bean.setUrl(menu.getUrl());
			if(menu.getItem() != null){
				bean.setParentid(menu.getItem().getId().toString());
				bean.setParentName(menu.getItem().getName());
			}
			Set<String> detailsList = new HashSet<String>();
			if (menu.getDetails() != null && menu.getDetails().size() > 0) {
				for (MenuItemDetails details : menu.getDetails()) {
					detailsList.add(details.getOpName()+":"+details.getOpkey());
				}
			}
			bean.setDetails(new ArrayList<String>(detailsList));
		}
		return bean;
	}

}
