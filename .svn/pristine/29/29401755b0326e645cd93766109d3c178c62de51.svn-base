package com.baofeng.oa.service;

import java.util.List;

import com.baofeng.oa.entity.CostCheck;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;


public interface ICostCheckService {

	boolean addCostCheckList(List<CostCheck> list);

	NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter);

	boolean deleteCostCheck();

}
