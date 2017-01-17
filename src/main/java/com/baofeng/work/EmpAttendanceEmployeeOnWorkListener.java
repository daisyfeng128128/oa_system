package com.baofeng.work;

import java.util.Date;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.EmpAttendance;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IEmpAttendanceService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;

/**
 * 功能：生成考勤数据
 * */
public class EmpAttendanceEmployeeOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(EmpAttendanceEmployeeOnWorkListener.class);

	public EmpAttendanceEmployeeOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {
		EmpAttendance $empAttendance = (EmpAttendance) analysis(arg);
		String date = Constants.convert($empAttendance.getCreateDT(), Constants.format7);
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		IEmployeeService employeeService = (IEmployeeService) commService.getService().get(IEmployeeService.class.getName());
		IEmpAttendanceService empAttendanceService = (IEmpAttendanceService) commService.getService().get(IEmpAttendanceService.class.getName());
		try {
			List<Employee> empList = employeeService.findAllEmployeesNeLeave();
			if (empList != null && empList.size() > 0) {
				for (Employee $emp : empList) {
					if (empAttendanceService.readAllEmpAttendanceByDateByNumber($emp.getNumber(), $emp.getBranchs(), date1, date2) == null) {
						EmpAttendance atte = new EmpAttendance();
						atte.setBranchs($emp.getBranchs());
						atte.setNumber($emp.getNumber());
						if (atte.getNumber() < 6)
							atte.setAttendance(26f);
						atte.setCreateDT(Constants.convert(date, Constants.format7));
						atte.setEmployee($emp);
						atte.setBranchs($emp.getBranchs());
						empAttendanceService.addEmpAttendance(atte);
					}
				}
			}
		} catch (Exception e) {
			logger.error("同步员工考勤有误", e);
		}
	}
}
