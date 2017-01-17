package com.baofeng.work;

import java.math.BigDecimal;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.FinSalariesAdjusts;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IActoresService;
import com.baofeng.oa.service.IEmployeeService;

/**
 * 功能：调薪申请调薪动作
 * */
public class MidAdjustOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(MidAdjustOnWorkListener.class);

	public MidAdjustOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {
		FinSalariesAdjusts adjust = (FinSalariesAdjusts) analysis(arg);
		if (adjust != null) {
			IActoresService actoresService = (IActoresService) commService.getService().get(IActoresService.class.getName());
			IEmployeeService employeeService = (IEmployeeService) commService.getService().get(IEmployeeService.class.getName());
			WorkManagers workManagers = (WorkManagers) commService.getService().get(WorkManagers.class.getName());
			try {
				Integer actId = adjust.getActores().getId();
				Actores actores = actoresService.findActoresById(actId);
				if (actores != null) {
					if (actores.getGenre() == LineGenres.OFFLINE && adjust.getGenre() == LineGenres.OFFLINE) {
						Employee emp = actores.getEmployee();
						if (emp != null && emp.getGenrer() == Genres.ZHENGSHI) {
							Float salary = emp.getSalary();
							emp.setOtherSubsidy(adjust.getNbasicSalary().subtract(new BigDecimal(salary)).floatValue());
							actores.setBasicSalary(adjust.getNbasicSalary().floatValue());
							actores.setPushMoney(adjust.getNpushMoney().intValue());
						} else {
							Float salary = emp.getSalary();
							emp.setProbationSalary(adjust.getNbasicSalary().subtract(new BigDecimal(salary)).floatValue());
							emp.setOtherSubsidy(adjust.getNbasicSalary().subtract(new BigDecimal(salary)).floatValue());
							actores.setProbationSalary(adjust.getNbasicSalary().floatValue());
							actores.setBasicSalary(adjust.getNbasicSalary().floatValue());
							actores.setPushMoney(adjust.getNpushMoney().intValue());
						}
						emp.setPushMoney(adjust.getNpushMoney());
						employeeService.saveEmployee(emp);
					} else {
						actores.setMinimumGuarantee(adjust.getNbasicSalary().floatValue());
						actores.setPushMoney(adjust.getNpushMoney().intValue());
					}
					actoresService.saveActoresBySession(actores);
					workManagers.onEvents(WorkNames.ACTORES_MID, actores);
				}
			} catch (Exception e) {
				logger.error("调薪申请调薪动作失败", e);
			}
		}
	}
}
