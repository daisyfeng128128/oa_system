package com.baofeng.work;

import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.FinancialNReports;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.utils.Constants;

/**
 * 功能：计算报表
 * */
public class MakeAllOnWorkListener implements BaseObserver {

	private final CommService commService;
	private ExecutorService executors;

	private static final Logger logger = LoggerFactory.getLogger(MakeOutlayOnWorkListener.class);

	public MakeAllOnWorkListener(CommService commService) {
		this.commService = commService;
		if (this.executors == null)
			this.executors = Executors.newCachedThreadPool();
	}

	@Override
	public void update(Observable o, Object arg) {
		Actores actores = (Actores) analysis(arg);
		executors.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				try {
					String date = Constants.convert(new Date(), Constants.format7);
					Date date1 = DateUtils.addMonths(Constants.convert(date, Constants.format7), -1);
					FinancialNReports post = new FinancialNReports();
					post.setCreateDT(Constants.convert(date, Constants.format7));
					WorkManagers workManagers = (WorkManagers) commService.getService().get(WorkManagers.class.getName());
					IPlatformsMonthsOutlayService platformsMonthsOutlayService = (IPlatformsMonthsOutlayService) commService.getService().get(
							IPlatformsMonthsOutlayService.class.getName());
					List<PlatformsMonthsOutlay> monthsList = platformsMonthsOutlayService.findPlatformsMonthsOutlayList(date1);
					if (monthsList != null && monthsList.size() > 0) {
						for (PlatformsMonthsOutlay outlay : monthsList) {
							boolean ft = false;
							if (actores != null && outlay.getBranchs() == actores.getBranchs()) {
								ft = true;
							} else if (actores == null) {
								ft = true;
							}
							if (ft) {
								workManagers.onEvents(WorkNames.MAKEMANAGER, outlay);
								workManagers.onEvents(WorkNames.MAKEONLINE, outlay);
								workManagers.onEvents(WorkNames.MAKEOFFINE, outlay);
								workManagers.onEvents(WorkNames.MAKEOUTLAY, outlay);
								workManagers.onEvents(WorkNames.MAKETOTALOUTLAY, outlay);
								workManagers.onEvents(WorkNames.CHECK_SALARY_FINANCIALREPORTS, post);
							}
						}
					}
				} catch (Exception e) {
					if (logger.isErrorEnabled())
						logger.error("自动计算报表数据错误", e);
				}
				return null;
			}
		});
	}
}
