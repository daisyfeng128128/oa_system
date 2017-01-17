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
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IManagerService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsManagerService;

public class EmployeeLeaveOnWorkListener implements BaseObserver {
	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeLeaveOnWorkListener.class);

	public EmployeeLeaveOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {
		Employee employee = (Employee) analysis(arg);
		IActoresService actoresService = (IActoresService) commService.getService().get(IActoresService.class.getName());
		IManagerService managerService = (IManagerService) commService.getService().get(IManagerService.class.getName());
		IEmployeeService employeeService = (IEmployeeService) commService.getService().get(IEmployeeService.class.getName());
		IPlatformsManagerService platformsManagerService = (IPlatformsManagerService) commService.getService().get(IPlatformsManagerService.class.getName());
		IPlatformsActoresService platformsActoresService = (IPlatformsActoresService) commService.getService().get(IPlatformsActoresService.class.getName());
		try {
			Managers manager = managerService.findManagerByEmpIdSession(employee.getId());
			if (manager != null && employee.getGenrer() == Genres.PENDING) {
				manager.setGenrer(Genres.LIZHI);
				managerService.saveManagerBySession(manager);
				platformsManagerService.deletePlatformsManagerBySession(manager.getId());
			}
			Actores actores = actoresService.findActoresBySession(employee.getId());
			if (actores != null && employee.getGenrer() == Genres.PENDING) {
				actores.setGenrer(Genres.LIZHI);
				actoresService.saveActoresBySession(actores);
				platformsActoresService.deletePlatformsActoresBySession(actores.getId());
			}
			employee.setGenrer(Genres.LIZHI);
			employeeService.saveEmployee(employee);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("员工档案数据离职同步", e);
		}

	}

}
