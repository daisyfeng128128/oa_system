package com.baofeng.oa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.DisciplinaryBean;
import com.baofeng.oa.entity.Disciplinary;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;


public interface IDisciplinaryService {

	NPageResult<?> readPages(int pageSize, int curPage, String sortName,String sortOrder, Integer type,String date, String fastArg, Integer id,
			SearchFilter searchFilter, String classQuery);

	boolean saveDisciplinary(DisciplinaryBean bean, RoleUsers users,Integer branchs, HttpServletRequest request);

	DisciplinaryBean readDisciplinaryBean(Integer id);

	boolean delete(List<String> list, RoleUsers users,HttpServletRequest request);

	boolean updatedisciplinary(List<String> list, RoleUsers users,HttpServletRequest request);

	boolean updateReject(DisciplinaryBean bean, RoleUsers users,HttpServletRequest request);

	boolean saveDisc(DisciplinaryBean bean, RoleUsers users, Integer branchs,HttpServletRequest request);

	List<Disciplinary> readAllDisciplinary(SearchFilter searchFilter);

	HSSFWorkbook export(List<Disciplinary> listOn);

	Disciplinary readDisciplinary(Integer id);

	List<String> readDisciplinaryDate(SearchFilter searchFilter);

}
