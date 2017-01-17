package com.baofeng.oa.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.entity.Interview;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.SearchFilter;

public interface IInterviewService {

	List<String> readAllInterviewDate();

	NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, String date, Integer type, String fastArg, List<RoleDetailsAtts> platList, SearchFilter searchFilter, boolean flag);

	PageResult<?> readAllPagesSkip(int rows, int page, SearchFilter $filter, String filter);

	boolean addInterview(Interview post);

	Interview readInterview(String id);

	boolean delete(String id);

	boolean addInterviewSuccess(String interId, String plat);

	boolean addNoEntry(String id);

	boolean addPlat(Interview inter);

	boolean addReje(String id, String track, RoleUsers users);

	boolean addEmpl(String id, String launchTime, RoleUsers users);

	boolean addInter(String interId, RoleUsers users, String beliveTime);

	boolean addTrackingResults(String id, String trackingResults);

	boolean updateNoPassPre(String id, RoleUsers users);

	boolean addPrePass(String id, String chTransition, String plat, RoleUsers users);

	List<Interview> readAllInterview(String date, SearchFilter searchFilter);

	HSSFWorkbook export(List<Interview> listOff);

	
	
}
