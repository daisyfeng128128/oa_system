package com.baofeng.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.ActoresBean;
import com.baofeng.oa.bean.FinSalariesAdjustsBean;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.FinSalariesAdjusts;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface IFinSalariesAdjustsService {

	List<ActoresBean> readPlatformsActores(Actores actores, List<RoleDetailsAtts> platList);

	NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter, String ads, List<RoleDetailsAtts> platList);

	boolean updatePassedFinSalariesAdjusts(String ids, HttpServletRequest request);

	boolean updateRejectFinSalariesAdjusts(String ids, HttpServletRequest request);

	boolean deleteFinSalariesAdjusts(String ids, HttpServletRequest request);

	FinSalariesAdjusts readFinSalariesAdjuest(String id);

	boolean saveFinSalariesAdjusts(FinSalariesAdjustsBean bean, RoleUsers users, Integer branchs, HttpServletRequest request);

	boolean checkUp(Integer id);
}
