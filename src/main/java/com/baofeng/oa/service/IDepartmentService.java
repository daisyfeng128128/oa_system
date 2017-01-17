package com.baofeng.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.oa.bean.TreeviewBean;
import com.baofeng.oa.entity.Department;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

public interface IDepartmentService {

	NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String name, SearchFilter filter);

	boolean deleteDepartment(Integer id, HttpServletRequest request);

	boolean addDepartment(Department post, HttpServletRequest request);

	Department readDepartment(Integer id);

	PageResult<?> readAllPagesSkip(int rows, int page, SearchFilter $filter, String filter);

	PageResult<?> readPagesParent(int rows, int page, Integer did, SearchFilter $filter, String filter);

	boolean readDepartmentByParentId(Integer id);

	List<TreeviewBean> readTreeViewBeans(SearchFilter filter);

	List<Department> readAllDepartment(SearchFilter filter);

}
