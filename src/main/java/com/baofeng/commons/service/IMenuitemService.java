package com.baofeng.commons.service;

import java.util.Date;
import java.util.List;

import com.baofeng.commons.entity.MenuItem;
import com.baofeng.commons.entity.Operator;
import com.baofeng.commons.entity.Roles;
import com.baofeng.utils.PageResult;

public interface IMenuitemService {

	PageResult<?> readAllPages(int rows, int page, Operator user);

	void createMenuItem(MenuItem menuItem);

	PageResult<?> readMenuPages(int rows, int page, Roles roles);

	List<?> readAllMenu();

	MenuItem readMenuItem(String url);

	void deleteMentItemPlatforms(String string, String id);

	boolean saveInit();

	void updateMentItemPlatforms(String url, String name, String key, Integer branchs, Date createDT);

}
