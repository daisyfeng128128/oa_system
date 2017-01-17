package com.baofeng.oa.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baofeng.oa.bean.BranchOfficeBean;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

public interface IBranchOfficeService {

	@SuppressWarnings("rawtypes")
	NPageResult readAllPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter);

	BranchOfficeBean readBranch(Integer valueOf);

	boolean addBranch(BranchOffice branch);

	boolean delete(Integer valueOf);

	List<BranchOfficeBean> readAllBranchsByLogin();

	PageResult<?> readAllPagesSkip(int rows, int page, SearchFilter $filter, String filter, Integer integer);

	BranchOffice readBranchOffice(Integer valueOf);

	PageResult<?> readPagesAllRole(int rows, int page, SearchFilter $filter, String filter);

	List<BranchOffice> findBranchOfficeList();

	JSONObject readBranchOfficeSoc(Integer branchs);

	boolean updateBranchs(BranchOfficeBean bean);

}
