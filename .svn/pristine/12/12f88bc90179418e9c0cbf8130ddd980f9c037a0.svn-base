package com.baofeng.work;

import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IActoresService;
import com.baofeng.oa.service.IManagerService;

public class EmployeeGenrerOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeGenrerOnWorkListener.class);

	public EmployeeGenrerOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {
		Employee employee = (Employee)analysis(arg);
		IManagerService managerService = (IManagerService) commService.getService().get(IManagerService.class.getName());
		IActoresService actoresService = (IActoresService) commService.getService().get(IActoresService.class.getName());
		try {
			Managers manager = managerService.findManagerByEmpIdSession(employee.getId());
			if (manager != null) {
				if (employee.getGenrer() == Genres.ZHENGSHI) {
					manager.setGenrer(Genres.ZHENGSHI);
					managerService.saveManagerBySession(manager);
				}
			}
			Actores actores = actoresService.findActoresBySession(employee.getId());
			if (actores != null) {
				if (employee.getGenrer() == Genres.ZHENGSHI) {
					actores.setGenrer(Genres.ZHENGSHI);
					actoresService.saveActoresBySession(actores);
				}
			}
		} catch (Exception e) {
			logger.error("员工档案数据转正同步", e);
		}
	}

}
