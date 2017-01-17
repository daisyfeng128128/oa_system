package com.baofeng.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.EquipmentCollarBean;
import com.baofeng.oa.entity.EquipmentCollar;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;



public interface IUseApplicationService {

	NPageResult<?> readPages(int pageSize, int curPage, String sortName,String sortOrder, Integer type, String fastArg,
			SearchFilter searchFilter);

	boolean saveEquipmentCollar(List<EquipmentCollarBean> list,RoleUsers users, Integer branchs, HttpServletRequest request);

	boolean midEquipment(EquipmentCollarBean bean, RoleUsers users,Integer branchs, HttpServletRequest request);

	boolean deleteEquipment(List<String> list, HttpServletRequest request);

	EquipmentCollarBean readEquipmentCollarBean(Integer id);

	EquipmentCollar readEquipmentCollar(Integer id);




}
