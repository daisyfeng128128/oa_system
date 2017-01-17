package com.baofeng.work;

import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.oa.entity.PlatformsManagerOutlay;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IManagerService;
import com.baofeng.oa.service.IPlatformsManagerOutlayService;
import com.baofeng.oa.service.IPlatformsManagerService;

/**
 * 功能：修改员工同步修改线下管理
 * */
public class EmployeeManagerOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeManagerOnWorkListener.class);

	public EmployeeManagerOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {
		Employee emp = (Employee) analysis(arg);
		WorkManagers workManagers = (WorkManagers) commService.getService().get(WorkManagers.class.getName());
		IManagerService managerService = (IManagerService) commService.getService().get(IManagerService.class.getName());
		IPlatformsManagerService platformsManagerService = (IPlatformsManagerService) commService.getService().get(IPlatformsManagerService.class.getName());
		IPlatformsManagerOutlayService managerOutlayService = (IPlatformsManagerOutlayService) commService.getService().get(IPlatformsManagerOutlayService.class.getName());
		try {
			Managers managers = managerService.findManagerByEmpIdSession(emp.getId());
			if (managers != null) {
				managers.setPositions(emp.getPosition() != null ? emp.getPosition().getName() : "");
				managers.setNumber(emp.getNumber());
				managers.setBranchs(emp.getBranchs());
				managers.setRealname(emp.getName());
				managers.setSex(emp.getSex());
				managers.setAliasname(emp.getAliasname());
				managers.setAddress(emp.getLiveAdress());
				managers.setIdcard(emp.getIdcard());
				managers.setIdImage(emp.getIdImage());
				managers.setGenre(LineGenres.OFFLINE);
				managers.setPhone(emp.getPhone());
				managers.setQq(emp.getQq());
				managers.setEntryTime(emp.getJoinDate());
				Float salary = emp.getSalary() == null ? new Float(0.00) : emp.getSalary();
				Float probationSalary = emp.getProbationSalary() == null ? new Float(0.00) : emp.getProbationSalary();
				Float otherSubsidy = emp.getOtherSubsidy() == null ? new Float(0.00) : emp.getOtherSubsidy();
				Float foodSubsidy = emp.getFoodSubsidyE() == null ? new Float(0.00) : emp.getFoodSubsidyE();
				managers.setFoodSubsidy(foodSubsidy);
				managers.setBasicSalary(salary + otherSubsidy);
				managers.setProbationSalary(salary + probationSalary);
				managers.setGenrer(emp.getGenrer());
				managers.setLabour(ActoresLabour.SYSTEM);
			} else {
				managers = new Managers();
				managers.setPositions(emp.getPosition() != null ? emp.getPosition().getName() : "");
				managers.setNumber(emp.getNumber());
				managers.setBranchs(emp.getBranchs());
				managers.setRealname(emp.getName());
				managers.setSex(emp.getSex());
				managers.setAliasname(emp.getAliasname());
				managers.setAddress(emp.getLiveAdress());
				managers.setIdcard(emp.getIdcard());
				managers.setIdImage(emp.getIdImage());
				managers.setGenre(LineGenres.OFFLINE);
				managers.setPhone(emp.getPhone());
				managers.setQq(emp.getQq());
				managers.setEntryTime(emp.getJoinDate());
				Float salary = emp.getSalary() == null ? new Float(0.00) : emp.getSalary();
				Float probationSalary = emp.getProbationSalary() == null ? new Float(0.00) : emp.getProbationSalary();
				Float otherSubsidy = emp.getOtherSubsidy() == null ? new Float(0.00) : emp.getOtherSubsidy();
				Float foodSubsidy = emp.getFoodSubsidyE() == null ? new Float(0.00) : emp.getFoodSubsidyE();
				managers.setFoodSubsidy(foodSubsidy);
				managers.setBasicSalary(salary + otherSubsidy);
				managers.setProbationSalary(salary + probationSalary);
				managers.setGenrer(emp.getGenrer());
				managers.setLabour(ActoresLabour.SYSTEM);
				managers.setEmployee(emp);
			}
			boolean fts = false;
			if (managers.getId() != null)
				fts = true;
			if (managerService.saveManager(managers)) {
				if (fts) {
					PlatformsManager manas = platformsManagerService.readPlatformsManagerByMId(managers.getId());
					if (manas != null) {
						List<?> managerosList = managerOutlayService.findPlatformsManagerOutlayList(manas.getId());
						if (managerosList != null && managerosList.size() > 0) {
							for (Object $o : managerosList) {
								if ($o instanceof PlatformsManagerOutlay) {
									PlatformsManagerOutlay ms = (PlatformsManagerOutlay) $o;
									if (ms.getBranchs() == managers.getBranchs()) {
										if (managers.getGenrer() == Genres.ZHENGSHI) {
											ms.setBasicSalary(managers.getBasicSalary());
										} else {
											ms.setBasicSalary(managers.getProbationSalary());
										}
										managerOutlayService.savePlatformsManagerOutlay(ms);
										workManagers.onEvents(WorkNames.MAKEMANAGER, ms.getMonths());
										workManagers.onEvents(WorkNames.MAKEOUTLAY, ms.getMonths());
										workManagers.onEvents(WorkNames.MAKETOTALOUTLAY, ms.getMonths());
									}
								}
							}
						}
					}
				}
				logger.info("添加员工同步为线下管理成功!");
			}
		} catch (Exception e) {
			logger.error("添加员工同步为线下管理失败", e);
		}
	}

}
