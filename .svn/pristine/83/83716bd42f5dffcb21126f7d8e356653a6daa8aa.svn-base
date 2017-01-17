package com.baofeng.oa.service;

import java.util.Date;
import java.util.List;

import com.baofeng.oa.bean.FinStatisticcsBean;
import com.baofeng.oa.bean.SalariesStatisticsBean;
import com.baofeng.oa.entity.SalariesStatistics;
import com.baofeng.utils.SearchFilter;

public interface ISalariesStatisticsService {

	List<SalariesStatistics> findSalariesStatistics(Date date1, Date date2);

	SalariesStatisticsBean readPages(Date date1, Date date2, SearchFilter searchFilter);

	List<FinStatisticcsBean> findAllFinStatisticcsBean(Date date1, Date date2);

	boolean addSalariesStatistics(SalariesStatistics post);

	boolean deleteSalariesStatistics(SalariesStatistics post);

	List<FinStatisticcsBean> findAllFinStatisticcsBeanOnline(Date date1, Date date2);

	List<FinStatisticcsBean> findAllFinStatisticcsBeanTalent(Date date1, Date date2);


}
