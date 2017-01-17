package com.baofeng.work;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Observable;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.FinancialNReports;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IFinSalariesOnlineService;
import com.baofeng.oa.service.IFinSalariesService;
import com.baofeng.oa.service.IFinSalariesTalentService;
import com.baofeng.oa.service.IFinancialAccountsService;
import com.baofeng.oa.service.IFinancialReportsService;
import com.baofeng.oa.service.IPlatformsMonthsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;

/**
 * 功能：同步计算财务报表
 * */
public class ReportsOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(ReportsOnWorkListener.class);

	public ReportsOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {
		FinancialNReports $financialReports = (FinancialNReports) analysis(arg);
		IFinancialReportsService finReportsService = (IFinancialReportsService) commService.getService().get(IFinancialReportsService.class.getName());
		IFinSalariesService finSalariesService = (IFinSalariesService) commService.getService().get(IFinSalariesService.class.getName());
		IFinSalariesOnlineService finSalariesOnlineService = (IFinSalariesOnlineService) commService.getService().get(IFinSalariesOnlineService.class.getName());
		IPlatformsMonthsService platformsMonthsService = (IPlatformsMonthsService) commService.getService().get(IPlatformsMonthsService.class.getName());
		IFinancialAccountsService financialAccountsService = (IFinancialAccountsService) commService.getService().get(IFinancialAccountsService.class.getName());
		IFinSalariesTalentService finSalariesTalentService = (IFinSalariesTalentService) commService.getService().get(IFinSalariesTalentService.class.getName());
		try {
			String date = Constants.convert($financialReports.getCreateDT(), Constants.format7);
			Date $date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
			Date $date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
			FinancialNReports reports = finReportsService.findFinancialReportsByDate($financialReports.getBranchs(), $date1, $date2);
			if (reports == null) {
				reports = new FinancialNReports();
				reports.setBranchs($financialReports.getBranchs());
				reports.setCreateDT($date1);
			}
			
			// 当月收入
			BigDecimal currentIncome = platformsMonthsService.findSumCurrentIncome(reports.getBranchs(), $date1, $date2);
			// 线上艺人
			BigDecimal onlineActroes = finSalariesOnlineService.findSumOnlineActores(reports.getBranchs(), Actores.class, $date1, $date2);
			// 线上管理
			BigDecimal onlineManager = finSalariesOnlineService.findSumOnlineManagers(reports.getBranchs(), Managers.class, $date1, $date2);
			// 线上星探
			BigDecimal onlineTalent = finSalariesTalentService.findSumOnlineTalents(reports.getBranchs(), $date1, $date2);
			// 所有员工
			BigDecimal allEmployee = finSalariesService.findSumAllEmployees(reports.getBranchs(), $date1, $date2);

			// 财务支出
			BigDecimal financeOutlay = new BigDecimal(0);
			Map<String, Map<String, Object>> master = financialAccountsService.readAllFinancialAccounts("master", reports.getBranchs(), date);
			Map<String, Map<String, Object>> other = financialAccountsService.readAllFinancialAccounts("other", reports.getBranchs(), date);
			Object masterTotal = financialAccountsService.sumFinancialAccounts(master, "salraies", "social", "accumulation", "total");
			Object otherTotal = financialAccountsService.sumFinancialAccounts(other, "total");
			financeOutlay = financeOutlay.add(new BigDecimal(masterTotal.toString())).add(new BigDecimal(otherTotal.toString()));

			// 当月支出
			BigDecimal outlay = onlineActroes.add(onlineManager).add(onlineTalent).add(allEmployee).add(financeOutlay);
			// 当月利润
			BigDecimal currentProfit = currentIncome.subtract(outlay);
			// 上月利润
			BigDecimal lastProfit = new BigDecimal(0.00);
			// 环比增长
			BigDecimal growthRate = new BigDecimal(0.00);
			{
				FinancialNReports $reports = finReportsService.findFinancialReportsByDate(reports.getBranchs(), DateUtils.addMonths($date1, -1), DateUtils.addMonths($date2, -1));
				if ($reports != null && $reports.getCurrentProfit() != null)
					lastProfit = $reports.getCurrentProfit();
				if (currentProfit.doubleValue() > Double.valueOf(0.00) && lastProfit.doubleValue() > Double.valueOf(0.00))
					growthRate = currentProfit.subtract(lastProfit).divide(lastProfit, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
				if (lastProfit.doubleValue() == Double.valueOf(0.00) && currentIncome.doubleValue() > Double.valueOf(0.00))
					growthRate = new BigDecimal(100);
			}

			reports.setCp_growthRate(growthRate);
			reports.setLastProfit(lastProfit);
			reports.setCurrentProfit(currentProfit);
			reports.setCurrentIncome(currentIncome);
			reports.setCurrentExpenditure(outlay);

			reports.setOnlineActs(onlineActroes);// 线上艺人支出
			reports.setOnlineMans(onlineManager);// 线上管理支出
			reports.setOnlineTalent(onlineTalent);// 线上星探
			reports.setOfflineEmps(allEmployee);// 线下所有员工支出
			reports.setAllTotal(onlineActroes.add(onlineManager).add(onlineTalent).add(allEmployee));// 工资合计{sumOnlineIncome、onlineMans、onlineTalent、offlineEmps}
			reports.setFinancing(financeOutlay);// 财务支出
			reports.setFinancingTotal(financeOutlay);// 财务支出合计{financing}
			reports.setTotalOutlays(outlay);// 公司总支出合计
			finReportsService.addFinancialNReports(reports);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("校验财务报表失败", e);
		}
	}
}
