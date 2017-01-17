package com.baofeng.oa.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baofeng.oa.bean.PlatformsOnlineIncomeBean;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsOnlineIncome;
import com.baofeng.utils.SearchFilter;

public interface IPlatformsOnlineIncomeService {

	void addPlatformsOnlineIncome(List<PlatformsOnlineIncome> listOn);

	List<PlatformsOnlineIncomeBean> readAllPlatformsOnlineIncome(String platId, Integer mId, String date, SearchFilter filter);

	boolean updateIncome(List<PlatformsOnlineIncome> list);

	void savePlatformsOnlineOutlay(PlatformsOnlineIncome outlay);

	PlatformsOnlineIncome findPlatformsOnlineIncomeBySession(Integer id);

	List<PlatformsOnlineIncome> findPlatformsOnlineIncomeById(Integer id, Date date1, Date date2);

	boolean savePlatformsOnlineOutlayBySession(PlatformsOnlineIncome $online);

	boolean findValidation(Integer actId, Integer branchs, Integer mId);

	boolean deletePlatformsActores(PlatformsActores pactores);

	boolean findActValidation(String actsName, Integer branchs, Integer mid, String platId, String channelId, Date date1, Date date2);

	PlatformsOnlineIncomeBean editOn(PlatformsOnlineIncomeBean bean);

	HSSFWorkbook export(List<PlatformsOnlineIncomeBean> listOn);

	List<PlatformsOnlineIncome> findPlatformsOnlineList(Integer mid, Date date);

	boolean findPlatformsOnlineIncemeByName(String name, String aliasname, Integer platId, Date $date1, Date $date2);

	PlatformsOnlineIncome readAllPlatformsOnlineIncome(String channel,
			Integer branchs);

	boolean addPlatformsOnlineIncome(PlatformsOnlineIncome lineIncome,
			HttpServletRequest request);


}
