package com.baofeng.oa.quartz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

/**
 * 功能：自动处理离职
 * */
public class AutoExecTask {

	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private IEmployeeService employeeService;

	private static final Logger logger = LoggerFactory.getLogger(AutoExecTask.class);

	public void doExec() {
		try {
			List<Employee> empList = this.employeeService.findEmployeeByPending();
			if (empList != null && empList.size() > 0) {
				for (Employee emp : empList) {
					if (emp != null) {
						this.workManagers.onEvents(WorkNames.EMPLOYEE_LIZHI, emp);
					}
				}
			}
		} catch (Exception e) {
			logger.error("自动离职", e);
		}
	}
}