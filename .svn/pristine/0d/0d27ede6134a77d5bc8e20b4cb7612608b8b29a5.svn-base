package com.baofeng.oa.quartz;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.baofeng.oa.entity.EmployeeTransfer;
import com.baofeng.utils.Constants;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

/**
 * 功能：每月转岗
 * */
public class TransferTask {

	@Autowired
	private WorkManagers workManagers;

	private static final Logger logger = LoggerFactory.getLogger(TransferTask.class);

	public void doExec() {
		String date = Constants.convert(new Date(), Constants.format7);
		Date date1 = DateUtils.addMonths(Constants.convert(date, Constants.format7), -1);
		
		addEmpTransferTask(date1);
		logger.info("普通员工转职同步成功!");
		addActTransferTask(date1);
		logger.info("艺人转职同步成功!");
		addManTransferTask(date1);
		logger.info("管理人员转职同步成功!");
		addTalTransferTask(date1);
		logger.info("星探转职同步成功!");
	}
	
	/**
	 * 功能： 每月同步转岗数据
	 * */
	private void addEmpTransferTask(Date date1) {
		EmployeeTransfer employeeTransfer = new EmployeeTransfer();
		employeeTransfer.setCreateDT(date1);
		this.workManagers.onEvents(WorkNames.EMPLOYEE_TRANSFER, employeeTransfer);
	}
	/**
	 * 功能： 每月同步转岗数据
	 * */
	private void addActTransferTask(Date date1) {
		EmployeeTransfer employeeTransfer = new EmployeeTransfer();
		employeeTransfer.setCreateDT(date1);
		this.workManagers.onEvents(WorkNames.ACTORES_TRANSFER, employeeTransfer);
	}
	/**
	 * 功能： 每月同步转岗数据
	 * */
	private void addManTransferTask(Date date1) {
		EmployeeTransfer employeeTransfer = new EmployeeTransfer();
		employeeTransfer.setCreateDT(date1);
		this.workManagers.onEvents(WorkNames.MANAGER_TRANSFER, employeeTransfer);
	}
	/**
	 * 功能： 每月同步转岗数据
	 * */
	private void addTalTransferTask(Date date1) {
		EmployeeTransfer employeeTransfer = new EmployeeTransfer();
		employeeTransfer.setCreateDT(date1);
		this.workManagers.onEvents(WorkNames.TALENT_TRANSFER, employeeTransfer);
	}
}
