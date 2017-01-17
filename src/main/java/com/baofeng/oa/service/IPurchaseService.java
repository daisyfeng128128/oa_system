package com.baofeng.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.ElectEquipentBean;
import com.baofeng.oa.bean.OfficeSuppliesBean;
import com.baofeng.oa.entity.ElectEquipent;
import com.baofeng.oa.entity.OfficeSupplies;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;


public interface IPurchaseService {


	boolean deleteElectEquipent(Integer id, HttpServletRequest request);

	ElectEquipent readElectEquipent(Integer id);

	ElectEquipentBean readElectEquipentBean(Integer id);

	OfficeSupplies readOfficeSupplies(Integer id);

	OfficeSuppliesBean readOfficeSuppliesBean(Integer id);

	boolean deleteOfficeSupplies(Integer id, HttpServletRequest request);

	boolean saveOfficeSupplies(List<OfficeSuppliesBean> list, RoleUsers users,
			Integer branchs, HttpServletRequest request);

	NPageResult<?> readPagesR(int pageSize, int curPage, String sortName,
			String sortOrder, Integer type, String fastArg,
			SearchFilter searchFilter);

	NPageResult<?> readPages(int pageSize, int curPage, String sortName,
			String sortOrder, Integer type, String fastArg,
			SearchFilter searchFilter);

	boolean saveElectEquipent(List<ElectEquipentBean> list, RoleUsers users,
			Integer branchs, HttpServletRequest request);

	boolean modElectEquipent(ElectEquipentBean bean, RoleUsers users,
			Integer branchs, HttpServletRequest request);

	boolean saveOfficeSupplies(OfficeSuppliesBean bean, RoleUsers users,
			Integer branchs, HttpServletRequest request);

}
