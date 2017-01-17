package com.baofeng.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.PositiveReviewPassBean;
import com.baofeng.oa.entity.PositiveReview;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface IPositiveReviewService {

	@SuppressWarnings("rawtypes")
	NPageResult readPagesActores(int pageSize, int curPage, String sortName, String sortOrder, List<RoleDetailsAtts> platList, Integer type, SearchFilter searchFilter);

	boolean addApply(Integer id, RoleUsers users, Integer empType, String apply, Integer type);

	@SuppressWarnings("rawtypes")
	NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, List<RoleDetailsAtts> platList, Integer type,
			SearchFilter searchFilter);

	boolean updatePass(PositiveReviewPassBean bean, RoleUsers users, HttpServletRequest request);

	boolean updateCheckPosi(RoleUsers users, Integer id, String apply, Integer type);

	PositiveReview readPositiveReviewById(Integer id);

	boolean updatePosi(PositiveReview posi, HttpServletRequest request);

}
