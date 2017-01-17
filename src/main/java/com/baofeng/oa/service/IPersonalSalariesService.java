package com.baofeng.oa.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.FinSalaryEmployee;
import com.baofeng.oa.entity.FinSalaryOffline;

public interface IPersonalSalariesService {

	FinSalaryEmployee getSalaryInfomation(Integer number,Date $date1, Date $date2);

	Employee readEmployeeByNumber(Integer number); 

	boolean updateEmployee(Integer number, String newCard,
			String newCardAddress, HttpServletRequest request);

	FinSalaryOffline getSalaryOfflineInfomation(Integer number, Date $date1, Date $date2);

}
