package com.baofeng.oa.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

/**
 * 功能：自动计算报表数据
 * */
public class MakeOutlayTask {

	@Autowired
	private WorkManagers workManagers;

	public void doExec() {
		/** 计算报表 */
		this.workManagers.onEvents(WorkNames.MAKEALL);
	}
}
