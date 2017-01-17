package com.baofeng.oa.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baofeng.oa.bean.FinSalariesTalentBean;
import com.baofeng.oa.entity.FinSalariesTalent;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface IFinSalariesTalentService {

	boolean findValidation(Integer id, Date date1, Date date2);

	void addFinSalariesTalentList(List<FinSalariesTalent> listSal);

	NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, String date, String fastArg, Integer all, SearchFilter searchFilter);

	boolean save(Integer branchs, List<FinSalariesTalentBean> listBean);

	boolean saveFinSalariesTalentByList(List<String> list);

	List<FinSalariesTalent> readAllFinSalariesTalent(String date, SearchFilter searchFilter);

	FinSalariesTalentBean readSalariesTalent(String id);

	boolean addFinSalariesTalent(FinSalariesTalent talent);

	List<FinSalariesTalent> findAllFinSalariesTalent(Date $date1, Date $date2);

	BigDecimal findSumOnlineTalents(Integer branchs, Date $date1, Date $date2);

}
