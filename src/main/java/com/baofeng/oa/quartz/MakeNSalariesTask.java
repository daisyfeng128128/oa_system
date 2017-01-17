package com.baofeng.oa.quartz;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.baofeng.oa.entity.FinSalaries;
import com.baofeng.oa.entity.FinSalariesOnline;
import com.baofeng.oa.entity.FinSalariesTalent;
import com.baofeng.utils.Constants;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

/**
 * 功能： 财务系统数据同步
 * */
public class MakeNSalariesTask {
	@Autowired
	private WorkManagers workManagers;
	private static final Logger logger = LoggerFactory.getLogger(MakeNSalariesTask.class);

	public void doExec() {
		String date = Constants.convert(new Date(), Constants.format7);
		Date date1 = DateUtils.addMonths(Constants.convert(date, Constants.format7), -1);

		makeFinSalaries(date1);
		logger.info("员工工资同步成功!");

		makeFinSalariresOnline(date1);
		logger.info("线上员工工资同步成功!");

		makeFinSalariresTalent(date1);
		logger.info("星探工资同步成功");

	}

	/**
	 * 功能：计算所有员工工交资
	 * */
	private void makeFinSalaries(Date date1) {
		FinSalaries salaries = new FinSalaries();
		salaries.setCreateDT(date1);
		this.workManagers.onEvents(WorkNames.FINSALARIES, salaries);
	}

	/**
	 * 功能：计算所有线上员工工交资
	 * */
	private void makeFinSalariresOnline(Date date1) {
		FinSalariesOnline salariesOnline = new FinSalariesOnline();
		salariesOnline.setCreateDT(date1);
		this.workManagers.onEvents(WorkNames.FINSALARIESONLINE, salariesOnline);
	}

	/**
	 * 功能：同步星探数据
	 * */
	private void makeFinSalariresTalent(Date date1) {
		FinSalariesTalent salariesTalent = new FinSalariesTalent();
		salariesTalent.setCreateDT(date1);
		this.workManagers.onEvents(WorkNames.FINSALARIESTALENT, salariesTalent);
	}
}
