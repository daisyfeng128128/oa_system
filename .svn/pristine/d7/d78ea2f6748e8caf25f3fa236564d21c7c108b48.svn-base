package com.baofeng.oa.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baofeng.oa.entity.BigTopDetails;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface IBigtopService {

	List<String> readBigtopByDate();

	boolean saveBigtop(BigTopDetails details);

	BigTopDetails readBigtop(String id);

	NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter filter, String platId, String date);

	boolean saveBigtop(String id, String askfees, String createDT, String date, String platId, Integer branchs);

	Map<String, String> readLoader(String date, String platId, SearchFilter searchFilter);

	BigTopDetails findBigtopByOutlay(String platId, Integer outlayId);

	boolean deleteBigtopByOutlay(BigTopDetails details);

	BigTopDetails findBigtopByTopup(Integer platId, Date date1, Date date2);

}
