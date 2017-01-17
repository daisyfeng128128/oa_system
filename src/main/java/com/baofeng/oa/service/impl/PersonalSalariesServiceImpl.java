package com.baofeng.oa.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.oa.dao.PersonalSalariesDAO;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.FinSalaryEmployee;
import com.baofeng.oa.entity.FinSalaryOffline;
import com.baofeng.oa.service.IPersonalSalariesService;

@Service("personalSalariesService")
public class PersonalSalariesServiceImpl implements IPersonalSalariesService {

	@Autowired
	private PersonalSalariesDAO personalSalariesDAO;
	 

	@Override
	public FinSalaryEmployee getSalaryInfomation(Integer number, Date $date1, Date $date2) {
		return this.personalSalariesDAO.readFinSalaryEmployee(number,$date1,$date2);
	}

	@Override
	public Employee readEmployeeByNumber(Integer number) {
		if (number != null && number > 0) {
			Employee employee = this.personalSalariesDAO.readEmployee(number);
			return employee;
		}
		return null;
	}

	@Override
	public boolean updateEmployee(Integer number, String newCard, String newCardAddress,HttpServletRequest request) {
		if(number != null){
			Employee employee = this.readEmployeeByNumber(number);
			if (employee != null) {
				if(newCard != null && newCard.trim().length() >0){
					employee.setBankCard(newCard);
					employee.setBankAddress(newCardAddress);
				}
			}
			if(this.personalSalariesDAO.saveEmp(employee)){
				return true;
			}
			
		}
		return false;
	}

	@Override
	public FinSalaryOffline getSalaryOfflineInfomation(Integer number,
			Date $date1, Date $date2) {
		return this.personalSalariesDAO.readFinSalaryOffline(number,$date1,$date2);
	}

 
}
