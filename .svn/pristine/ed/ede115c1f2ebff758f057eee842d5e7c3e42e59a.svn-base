package com.baofeng.oa.quartz;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.baofeng.oa.entity.FinSalaries;
import com.baofeng.oa.entity.FinSalariesOnline;
import com.baofeng.oa.entity.FinSalariesTalent;
import com.baofeng.utils.Constants;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

/**
 * 功能：每日凌晨计算工资
 * */
public class MakeActSelariesTask {
	@Autowired
	private WorkManagers workManagers;

	public void doExec() {
		String date = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		// 验证平台艺人
		FinSalaries $vfins = new FinSalaries();
		$vfins.setCreateDT(Constants.convert(date, Constants.format7));
		this.workManagers.onEvents(WorkNames.FINSALARIESVALIDATION, $vfins);

		// 计算线下艺人
		FinSalaries post = new FinSalaries();
		post.setCreateDT(Constants.convert(date, Constants.format7));
		this.workManagers.onEvents(WorkNames.FINSALARIESACTORESOFFICE, post);
		// 计算线下管理
		this.workManagers.onEvents(WorkNames.FINSALARIESMANAGEROFFLINE, post);

		// 计算线上艺人
		FinSalariesOnline $post = new FinSalariesOnline();
		$post.setCreateDT(Constants.convert(date, Constants.format7));
		this.workManagers.onEvents(WorkNames.FINSALARIESACTORESONLINE, $post);
		// 计算线上管理
		this.workManagers.onEvents(WorkNames.FINSALARIESMANAGER, $post);

		// 计算星探
		FinSalariesTalent talent = new FinSalariesTalent();
		post.setCreateDT(Constants.convert(date, Constants.format7));
		this.workManagers.onEvents(WorkNames.FINSALARIESTALENT, talent);
		// 计算可发
		this.workManagers.onEvents(WorkNames.FINSALARIESTALENT, post);
	}
}
