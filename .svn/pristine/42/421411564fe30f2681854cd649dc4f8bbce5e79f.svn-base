package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.MenuItem;
import com.baofeng.commons.entity.MenuItemDetails;
import com.baofeng.oa.bean.MenusItemBean;
import com.baofeng.oa.dao.MenusDAO;
import com.baofeng.oa.service.IMenusService;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

@Service("menusService")
public class MenusServiceImpl implements IMenusService {

	@Autowired
	private MenusDAO menusDAO;

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult readAllPages(int pageSize, int curPage, String sortName, String sortOrder) {
		NPageResult page = this.menusDAO.readAllPages(pageSize, curPage, sortName, sortOrder);
		if (page != null && page.getData() != null && page.getData().size() > 0) {
			List<MenusItemBean> list = new ArrayList<MenusItemBean>();
			for (Object o : page.getData()) {
				MenuItem post = (MenuItem) o;
				MenusItemBean bean = new MenusItemBean();
				bean.setDev(post.getDev().toString().equals("YES") ? "否" : post.getDev().toString().equals("NO") ? "是" : "");
				List<String> listStr = new ArrayList<String>();
				if(post.getDetails() != null){
					for (MenuItemDetails details : post.getDetails()) {
						if (details.getOpName() != null)
							listStr.add(details.getOpName());
						else
							listStr.add("N/A");
					}
				}else{
					listStr.add("N/A");
				}
				bean.setDetails(listStr);
				bean.setDivid(post.getDivid());
				bean.setId(post.getId());
				bean.setIndexs(post.getIndexs());
				bean.setName(post.getName());
				bean.setUrl(post.getUrl());
				if (post.getItem() != null) {
					bean.setParentid(post.getItem().getId().toString());
					bean.setParentName(post.getItem().getName());
				} else {
					bean.setParentid("N/A");
					bean.setParentName("N/A");
				}
				list.add(bean);
			}
			page.setData(list);
		}
		return page;
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageResult readAllPagesSkip(int rows, int page, SearchFilter filter, String queryFilter) {
		return this.menusDAO.readAllPagesSkip(rows, page, filter, queryFilter);
	}

	@Override
	public MenuItem readMenu(Integer itemId) {
		if(itemId != null){
			return this.menusDAO.readMenu(itemId);
		}
		return null;
	}

	@Override
	@SuppressWarnings("serial")
	public boolean addMenu(MenuItem poster,final String[] details) {
		Set<String> $menuList = new TreeSet<String>() {
			{
				if (details != null && details.length > 0) {
					for (String $menu : details) {
						add($menu);
					}
				}
			}
		};
		if (poster != null && poster.getId() != null && poster.getId().intValue() > 0) {
			MenuItem $post = this.readMenu(poster.getId());
			if ($post != null) {
				List<MenuItemDetails> menuList = new ArrayList<MenuItemDetails>($post.getDetails());
				if (menuList != null && menuList.size() > 0) {
					for (MenuItemDetails $menu : menuList) {
						if (!$menuList.contains($menu.getOpName())) {
							$post.getDetails().remove($menu);
						}
						$menuList.remove($menu);
					}
				}
				for (String $menu : $menuList) {
					MenuItemDetails $details = new MenuItemDetails();
					$details.setOpName($menu);
					$details.setItem($post);
					this.menusDAO.getBaseDAO().save($details);
				}
				poster = $post;
			}
		} else {
			this.menusDAO.saveMenuItem(poster);
			if (details != null && details.length > 0) {
				for (String $details : $menuList) {
					if($details.indexOf(":") != -1){
						String[]  $detail = $details.split(":");
						MenuItemDetails menuItemDetails = new MenuItemDetails();
						menuItemDetails.setOpName($detail[0]);
						menuItemDetails.setOpkey($detail[1]);
						menuItemDetails.setItem(poster);
						this.menusDAO.saveMenuItemDetails(menuItemDetails);
					}else{
						MenuItemDetails menuItemDetails = new MenuItemDetails();
						menuItemDetails.setOpName($details);
						menuItemDetails.setItem(poster);
						this.menusDAO.saveMenuItemDetails(menuItemDetails);
					}
					
				}
			}
			return true;
		}
		return this.menusDAO.saveMenuItem(poster);
	}

	@Override
	public boolean deleteMenus(Integer id) {
		if(id != null){
			MenuItem menu =this.menusDAO.readMenu(id);
			if(menu != null){
				List<MenuItemDetails> list = menu.getDetails();
				if( list != null && list.size() >0){
					for(MenuItemDetails details : list){
						details.setItem(null);
						if(!this.menusDAO.deleteMenuItemDetails(details)){
							return false;
						}
					}
				}
				if(this.menusDAO.deleteMenus(menu)){
					return true;
				}
			}
		}
		return false;
	}


	
	
}
