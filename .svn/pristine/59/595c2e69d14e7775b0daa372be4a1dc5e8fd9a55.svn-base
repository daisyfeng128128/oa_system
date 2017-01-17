package com.baofeng.work;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.FinPlatformsIncome;
import com.baofeng.oa.entity.FinSalaries;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IFinSalariesService;
import com.baofeng.oa.service.IPlatformsOfflineIncomeService;
import com.baofeng.oa.service.IPlatformsOnlineIncomeService;
import com.baofeng.utils.CustomDateUtils;

/**
 * 功能：验证艺人与平台报表数据关系
 * */
public class FinSalariesValidationOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(FinSalariesValidationOnWorkListener.class);

	public FinSalariesValidationOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {
		FinSalaries data = (FinSalaries) analysis(arg);
		Date $date1 = CustomDateUtils.Handler.currentMonthFirstDay(data.getCreateDT());
		Date $date2 = CustomDateUtils.Handler.currentMonthEndDay(data.getCreateDT());
		IFinSalariesService finSalariesService = (IFinSalariesService) commService.getService().get(IFinSalariesService.class.getName());
		IPlatformsOnlineIncomeService platformsOnlineIncomeService = (IPlatformsOnlineIncomeService) commService.getService().get(IPlatformsOnlineIncomeService.class.getName());
		IPlatformsOfflineIncomeService platformsOfflineIncomeService = (IPlatformsOfflineIncomeService) commService.getService()
				.get(IPlatformsOfflineIncomeService.class.getName());
		try {
			List<FinPlatformsIncome> incomList = finSalariesService.findPlatformsIncomeByList(DateUtils.addMonths($date1, 1), DateUtils.addMonths($date2, 1));
			if (incomList != null && incomList.size() > 0) {
				for (FinPlatformsIncome income : incomList) {
					if (income.getFinSalaries() != null
							&& !platformsOfflineIncomeService.findPlatformsOfflineIncomeByNumber(income.getFinSalaries().getNumber(), income.getPlatId(), $date1, $date2)) {
						income.setIncome(new BigDecimal(0.00));
						income.setPlatDeduct(new BigDecimal(0.00));
						income.setPlatSubsidy(new BigDecimal(0.00));
					} else if (income.getFinSalariesOnline() != null
							&& !platformsOnlineIncomeService.findPlatformsOnlineIncemeByName(income.getFinSalariesOnline().getName(), income.getFinSalariesOnline().getAliasname(),
									income.getPlatId(), $date1, $date2)) {
						income.setIncome(new BigDecimal(0.00));
						income.setPlatDeduct(new BigDecimal(0.00));
						income.setPlatSubsidy(new BigDecimal(0.00));
					}
					finSalariesService.savePlatformsIncome(income);
				}
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled())
				logger.error("验证艺人与平台报表数据关系", e);
		}
	}
}
