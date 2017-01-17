package com.baofeng.oa.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baofeng.oa.bean.PlatformsOfflineIncomeBean;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsOfflineIncome;
import com.baofeng.utils.SearchFilter;

public interface IPlatformsOfflineIncomeService {

	void addPlatformsOfflineIncome(List<PlatformsOfflineIncome> listOn);

	List<PlatformsOfflineIncomeBean> readAllPlatformsOfflineIncome(String platId, Integer mId, String date, SearchFilter searchFilter);

	boolean update(List<PlatformsOfflineIncome> list);

	void savePlatformsOfflineOutlay(PlatformsOfflineIncome outlay);

	PlatformsOfflineIncome findPlatformsOfflineIncomeBySession(Integer id);

	boolean savePlatformsOfflineIncomeBySession(PlatformsOfflineIncome $offline);

	boolean findValidation(Integer pactId, Integer branchs, Integer mid, String platId, String channelId, Date date1, Date date2);

	boolean deletePlatformsActores(PlatformsActores pactores);

	boolean findActValidation(String actsName, Integer branchs, Integer mid, String platId, String channelId, Date date1, Date date2);

	PlatformsOfflineIncomeBean editOff(PlatformsOfflineIncomeBean bean);

	HSSFWorkbook export(List<PlatformsOfflineIncomeBean> listOff);

	List<PlatformsOfflineIncome> findPlatformsOfflineList(Integer mid, Date date);

	List<PlatformsOfflineIncome> findPlatformsOfflineIncomeById(Integer actId, Date date1, Date date2);

	boolean findPlatformsOfflineIncomeByNumber(Integer number, Integer platId, Date $date1, Date $date2);

	PlatformsOfflineIncome readAllPlatformsOfflineIncome(String channel,
			Integer branchs);

	boolean addPlatformsOfflineIncome(PlatformsOfflineIncome lineIncome,
			HttpServletRequest request);

}
