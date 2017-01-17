package com.baofeng.oa.service;

import java.util.List;

import com.baofeng.oa.bean.TalentBean;
import com.baofeng.oa.entity.Talent;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface ITalentService {

	NPageResult<?> readAllPages(int pageSize, int curPage, String sortName, String sortOrder, String fastArg, SearchFilter searchFilter);

	boolean addTalent(Talent talent);

	TalentBean readTalent(Integer valueOf);

	boolean delete(Integer valueOf);

	List<Talent> findAllTalent();

	Talent findTalentSession(Integer empId);

	boolean saveTalent(Talent talent);

}
