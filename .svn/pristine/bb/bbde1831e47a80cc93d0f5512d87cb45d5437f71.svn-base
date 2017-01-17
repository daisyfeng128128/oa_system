package com.baofeng.oa.quartz;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.baofeng.oa.entity.EmpAttendance;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

/**
 * 功能：每月考勤数据
 * */
public class AttendanceTask {

	@Autowired
	private WorkManagers workManagers;

	private static final Logger logger = LoggerFactory.getLogger(AttendanceTask.class);

	public void doExec() {
		try {
			addEmpAttendance(CustomDateUtils.Handler.lastMonthFirstDay());
		} catch (Exception e) {
			logger.error("考勤数据同步错误", e);
		}
	}
	
	/**
	 * 功能： 每月同步考勤数据
	 * */
	private void addEmpAttendance(Date date1) {
		EmpAttendance empAttendance = new EmpAttendance();
		empAttendance.setCreateDT(date1);
		this.workManagers.onEvents(WorkNames.EMPATTENDANCEEMPLOYEE, empAttendance);
	}
}
