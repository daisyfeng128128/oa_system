package com.baofeng.oa.service;

import com.baofeng.commons.entity.MenuItem;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

public interface IMenusService {

	NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder);

	PageResult<?> readAllPagesSkip(int rows, int page, SearchFilter $filter, String filter);

	MenuItem readMenu(Integer itemId);

	boolean addMenu(MenuItem menu, String[] details);

	boolean deleteMenus(Integer id);


}
