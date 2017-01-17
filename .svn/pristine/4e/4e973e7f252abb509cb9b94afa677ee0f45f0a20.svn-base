package com.baofeng.oa.service;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.oa.entity.Positions;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;


public interface IPositionsService {
 

	NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String name, SearchFilter filter);

	boolean addPositions(Positions post,HttpServletRequest request);
	 
	boolean deletePositions(Integer id,HttpServletRequest request);
	
	Positions readPositions(Integer id);

}
