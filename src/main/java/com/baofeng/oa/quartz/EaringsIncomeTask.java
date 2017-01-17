package com.baofeng.oa.quartz;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.baofeng.oa.entity.FinEarningsIncome;
import com.baofeng.oa.entity.SalariesStatistics;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

/**
 * 同步收益明细
 * */
public class EaringsIncomeTask {
	private static final Logger logger = LoggerFactory.getLogger(AttendanceTask.class);
	@Autowired
	private WorkManagers workManagers;

	public void doExec() {
		try {
			addEaringsIncome(CustomDateUtils.Handler.lastMonthFirstDay());
		} catch (Exception e) {
			logger.error("艺人同步错误", e);
		}
		try {
			addSalariesStatistics(CustomDateUtils.Handler.lastMonthFirstDay());
		} catch (Exception e) {
			logger.error("工资统计同步有误!", e);
		}
	}

	private void addEaringsIncome(Date date) {
		FinEarningsIncome finEarningsIncome = new FinEarningsIncome();
		finEarningsIncome.setCreateDT(date);
		this.workManagers.onEvents(WorkNames.FINEARINGSINCOME, finEarningsIncome);
	}

	private void addSalariesStatistics(Date date) {
		SalariesStatistics salariesStatistics = new SalariesStatistics();
		salariesStatistics.setCreateDT(date);
		this.workManagers.onEvents(WorkNames.SALARIESSTATISTICS, salariesStatistics);
	}

}
