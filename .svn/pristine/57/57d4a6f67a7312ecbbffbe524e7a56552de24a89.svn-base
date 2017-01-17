package com.baofeng.work;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Observable;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.dao.PlatformsMonthsOutlayDAO;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.PlatformsMonths;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IPlatformsMonthsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;

/**
 * 功能：自动计算营收总表
 * */
public class MakeOutlayOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(MakeOutlayOnWorkListener.class);

	public MakeOutlayOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		try {
			PlatformsMonthsOutlay outlay = (PlatformsMonthsOutlay) analysis(arg);
			PlatformsMonthsOutlayDAO outlayDao = (PlatformsMonthsOutlayDAO) commService.getService().get(PlatformsMonthsOutlayDAO.class.getName());
			IPlatformsMonthsService monthsService = (IPlatformsMonthsService) commService.getService().get(IPlatformsMonthsService.class.getName());
			makeOutlay(outlayDao, outlay, outlay.getCreateDT());
			outlay.make();
			makeMonths(monthsService, outlay);
			outlayDao.savePlatformsMonthsOutlay(outlay);
		} catch (Exception e) {
			if (logger.isErrorEnabled())
				logger.error("自己动计算营收总表错误", e);
		}

	}

	/**
	 * 功能:计算数据
	 * */
	private void makeOutlay(PlatformsMonthsOutlayDAO outlayDao, PlatformsMonthsOutlay outlay, Date date) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(date);
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(date);
		// 上月后台收入
		Date $date1 = DateUtils.addMonths(date1, -1);
		Date $date2 = DateUtils.addMonths(date2, -1);
		BigDecimal lastMonths = new BigDecimal(0.00);
		PlatformsMonthsOutlay $last = outlayDao.findPlatformsMonthsOutlyaByLast(outlay.getBranchs(), outlay.getPlatId(), $date1, $date2);
		if ($last != null && $last.getCurrentMonths() != null) {
			lastMonths = $last.getCurrentMonths();
			outlay.setLastMonths(lastMonths);
		}
		
		// 增涨率
		BigDecimal growthRate = new BigDecimal(0.00);
		BigDecimal currentMonths = outlay.getCurrentMonths() == null ? new BigDecimal(0.00) : outlay.getCurrentMonths();
		if (lastMonths != null && lastMonths.doubleValue() > 0 && currentMonths != null && currentMonths.doubleValue() > 0) {
			growthRate = currentMonths.subtract(lastMonths).divide(lastMonths, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		}
		if (lastMonths.doubleValue() == 0 && currentMonths.doubleValue() > 0)
			growthRate = new BigDecimal(100);
		outlay.setGrowthRate(growthRate);

		// 线上艺人本月收入
		BigDecimal sumOnlineIncome = outlayDao.findOnlineIncome(outlay.getId());
		outlay.setOnlineIncome(sumOnlineIncome);
		// 线上艺人上月收入
		BigDecimal sumLastOnlineIncome = outlayDao.findLastOnlineIncome(outlay.getBranchs(), outlay.getPlatId(), $date1, $date2);
		outlay.setLastOnlineIncome(sumLastOnlineIncome);
		BigDecimal onlineRate = new BigDecimal(0.00);
		if (sumLastOnlineIncome != null && sumLastOnlineIncome.doubleValue() > 0 && sumOnlineIncome != null && sumOnlineIncome.doubleValue() > 0)
			onlineRate = sumOnlineIncome.subtract(sumLastOnlineIncome).divide(sumLastOnlineIncome, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		if (sumLastOnlineIncome.doubleValue() == 0 && sumOnlineIncome.doubleValue() > 0)
			onlineRate = new BigDecimal(100);
		outlay.setOnlineRate(onlineRate);

		// 线下艺人本月收入
		BigDecimal sumOfflineIncome = outlayDao.findOfflineIncome(outlay.getId());
		outlay.setOfflineIncome(sumOfflineIncome);
		// 线下艺人上月收入
		BigDecimal sumLastOfflineIncome = outlayDao.findLastOfflineIncome(outlay.getBranchs(), outlay.getPlatId(), $date1, $date2);
		outlay.setLastOfflineIncome(sumLastOfflineIncome);
		BigDecimal offlineRate = new BigDecimal(0.00);
		if (sumLastOfflineIncome != null && sumLastOfflineIncome.doubleValue() > 0 && sumOfflineIncome != null && sumOfflineIncome.doubleValue() > 0)
			offlineRate = sumOfflineIncome.subtract(sumLastOfflineIncome).divide(sumLastOfflineIncome, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));

		if (sumLastOfflineIncome.doubleValue() == 0 && sumOfflineIncome.doubleValue() > 0)
			offlineRate = new BigDecimal(100);
		outlay.setOfflineRate(offlineRate);

		// 管理线上支出
		BigDecimal sumOnlineMg = outlayDao.findManagerOutlay(outlay.getId(), 30, LineGenres.ONLINE);
		outlay.setOnlineOutlay(sumOnlineMg);
		// 管理线下支出
		BigDecimal sumofflineMg = outlayDao.findManagerOutlay(outlay.getId(), Constants.DEFAULT_MAXNUM, LineGenres.OFFLINE);
		outlay.setOfflineOutlay(sumofflineMg);
		// 线上艺人支出
		BigDecimal actOnlineOutlay = outlayDao.findActOnlineOutlay(outlay.getId());
		outlay.setActOnlineOutlay(actOnlineOutlay);
		// 线下艺人支出
		BigDecimal actOfflineOutlay = outlayDao.findActOfflineOutlay(outlay.getId());
		outlay.setActOfflineOutlay(actOfflineOutlay);
		// 维护费用
		BigDecimal sumMaintain = outlayDao.findMaintain(outlay.getId());
		outlay.setMaintain(sumMaintain);
		// 活动支出
		BigDecimal sumActivity = outlayDao.findActivity(outlay.getId());
		outlay.setActivity(sumActivity);
		// 劳务服务费
		BigDecimal sumOfflineLabor = outlayDao.findOfflineLaborService(outlay.getId());
		BigDecimal sumOnlineLabor = outlayDao.findOnlineLaborService(outlay.getId());
		outlay.setLaborService(sumOfflineLabor.add(sumOnlineLabor));
	}

	/**
	 * 功能：同步应收
	 * */
	private void makeMonths(IPlatformsMonthsService monthsService, PlatformsMonthsOutlay outlay) {
		PlatformsMonths months = monthsService.findPlatformsMonthsByMId(outlay.getId());
		if (months == null)
			months = new PlatformsMonths();
		months.setReceivable(outlay.getCurrentMonths());
		months.setOnlineTotal(outlay.getOnlineIncome());
		months.setOfflineTotal(outlay.getOfflineIncome());
		months.setOtherIncome(outlay.getTransferIncome());
		months.setIncomeTotal(outlay.getTotalIncome());
		months.setOutlayTotal(outlay.getOutlay());
		months.setNetProfit(outlay.getNetIncome());
		months.setPlatforms(outlay.getPlatforms());
		months.setPlatId(outlay.getPlatId());
		months.setBranchs(outlay.getBranchs());
		months.setMonths(outlay);
		monthsService.savePlatformsMonths(months);
	}

}
