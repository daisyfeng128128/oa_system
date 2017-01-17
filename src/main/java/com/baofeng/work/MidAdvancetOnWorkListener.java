package com.baofeng.work;

import java.math.BigDecimal;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.AdvanceSalary;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IActoresService;
import com.baofeng.oa.service.IEmployeeService;

public class MidAdvancetOnWorkListener implements BaseObserver {
	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(MidAdvancetOnWorkListener.class);

	public MidAdvancetOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		AdvanceSalary adjust = (AdvanceSalary) analysis(arg);
		IActoresService actoresService = (IActoresService) commService.getService().get(IActoresService.class.getName());
		IEmployeeService employeeService = (IEmployeeService)  commService.getService().get(IEmployeeService.class.getName());
		WorkManagers workManagers = (WorkManagers) commService.getService().get(WorkManagers.class.getName());
		try {
			Integer actId = adjust.getActores().getId();
			Actores actores = actoresService.findActoresById(actId);
			if (actores != null) {
				if (actores.getGenre() == LineGenres.OFFLINE && adjust.getGenre() == LineGenres.OFFLINE) {
					Employee emp = actores.getEmployee();
					if (emp != null && emp.getGenrer() == Genres.ZHENGSHI) {
						actores.setBasicSalary(adjust.getSbasicSalary().floatValue());
					} else {
						Float salary = emp.getSalary();
						emp.setProbationSalary(adjust.getSbasicSalary().subtract(new BigDecimal(salary)).floatValue());
						emp.setOtherSubsidy(adjust.getSbasicSalary().subtract(new BigDecimal(salary)).floatValue());
						actores.setProbationSalary(adjust.getSbasicSalary().floatValue());
						actores.setBasicSalary(adjust.getSbasicSalary().floatValue());
					}
					employeeService.saveEmployee(emp);
				} else {
					actores.setMinimumGuarantee(adjust.getSbasicSalary().floatValue());
				}
				actoresService.saveActoresBySession(actores);
				workManagers.onEvents(WorkNames.ACTORES_MID, actores);
			}
		} catch (Exception e) {
			logger.error("申请借款动作失败", e);
		}
	

	}

}
