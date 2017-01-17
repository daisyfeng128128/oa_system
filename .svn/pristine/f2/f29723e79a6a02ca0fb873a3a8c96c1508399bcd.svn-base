package com.baofeng.oa.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

/**
 * 功能： 每天统计艺人成本不对的艺人
 * */
public class CostCheckTask {

	@Autowired
	private WorkManagers workManagers;

	public void doExec() {
		/** 统计部门成本 */
		this.workManagers.onEvents(WorkNames.COSTCHECK, true);
	}
}
