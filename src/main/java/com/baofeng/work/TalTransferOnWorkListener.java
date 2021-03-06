package com.baofeng.work;

import java.util.Date;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.EmpTransfer;
import com.baofeng.oa.entity.BaseEnums.EmployeType;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BaseEnums.Settlement;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.EmployeeTransfer;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.oa.entity.PlatformsManagerOutlay;
import com.baofeng.oa.entity.Talent;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IEmpTransferService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IManagerService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsManagerOutlayService;
import com.baofeng.oa.service.IPlatformsManagerService;
import com.baofeng.oa.service.ITalentService;
import com.baofeng.utils.CustomDateUtils;

public class TalTransferOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(TalTransferOnWorkListener.class);

	public TalTransferOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {
		EmployeeTransfer employeeTransfer = (EmployeeTransfer) analysis(arg);
		Date $date1 = CustomDateUtils.Handler.currentMonthFirstDay(employeeTransfer.getCreateDT());
		Date $date2 = CustomDateUtils.Handler.currentMonthEndDay(employeeTransfer.getCreateDT());

		IManagerService managerService = (IManagerService) commService.getService().get(IManagerService.class.getName());
		ITalentService talentService = (ITalentService) commService.getService().get(ITalentService.class.getName());
		IEmployeeService employeeService = (IEmployeeService) commService.getService().get(IEmployeeService.class.getName());
		IPlatformsManagerService platformsManagerService = (IPlatformsManagerService) commService.getService().get(IPlatformsManagerService.class.getName());
		IPlatformsActoresService platformsActoresService = (IPlatformsActoresService) commService.getService().get(IPlatformsActoresService.class.getName());
		IEmpTransferService empTransferService = (IEmpTransferService) commService.getService().get(IEmpTransferService.class.getName());
		IPlatformsManagerOutlayService managerOutlayService = (IPlatformsManagerOutlayService) commService.getService().get(IPlatformsManagerOutlayService.class.getName());
		WorkManagers workManagers = (WorkManagers) commService.getService().get(WorkManagers.class.getName());

		List<EmployeeTransfer> $employeeTransfer = null;
		try {
			$employeeTransfer = (List<EmployeeTransfer>) empTransferService.readTraEmployee($date1, $date2);
			if ($employeeTransfer.size() > 0) {
				for (EmployeeTransfer employeeT : $employeeTransfer) {
					Employee emp = employeeService.readEmployeebyNumber(employeeT.getNumber(), employeeT.getBranchs());
					if (emp != null) {
						if (emp.getEmployeType() == EmployeType.TALENT) {
							if (employeeT.getEmployeType() == EmployeType.ACTORES) {
								Actores actores = new Actores();
								actores.setBranchs(emp.getBranchs());
								actores.setNumber(emp.getNumber());
								actores.setRealname(emp.getName());
								actores.setSex(emp.getSex());
								actores.setAliasname(emp.getAliasname());
								actores.setAddress(emp.getLiveAdress());
								actores.setIdcard(emp.getIdcard());
								actores.setGenre(LineGenres.OFFLINE);
								actores.setPhone(emp.getPhone());
								emp.setPosition(employeeT.getNewPosition());
								emp.setDepart(employeeT.getNewDepart());
								emp.setSupervisor(employeeT.getSupervisor());
								actores.setQq(emp.getQq());
								actores.setEmail(emp.getEmail());
								actores.setGenrer(emp.getGenrer());
								actores.setEntryTime(employeeTransfer.getTransferDate());
								emp.setGenrer(Genres.ZHENGSHI);
								actores.setLabour(ActoresLabour.SYSTEM);
								emp.setEmployeType(EmployeType.ACTORES);
								employeeT.setEmpTransfer(EmpTransfer.PASSED);
								actores.setEmployee(emp);
								actores.setCreateDT($date1);
								actores.setSettl(Settlement.SINGLEPLATFORM);
								if (employeeService.saveEmployee(emp))
									empTransferService.saveEmployeeTra(employeeT);
								boolean ft = false;
								if (actores.getId() != null)
									ft = true;
								if (platformsActoresService.saveActores(actores)) {
									if (ft)
										workManagers.onEvents(WorkNames.ACTORES_MID, actores);
									logger.info("添加员工同步为线下艺人成功!");
								}
							} else if (employeeT.getEmployeType() == EmployeType.EMPLOYEE) {
								Talent talent = talentService.findTalentSession(emp.getId());
								if (talent != null) {
									emp.setPosition(employeeT.getNewPosition());
									emp.setDepart(employeeT.getNewDepart());
									emp.setSupervisor(employeeT.getSupervisor());
									emp.setEmployeType(EmployeType.EMPLOYEE);
									emp.setCreateDT($date1);
									employeeT.setEmpTransfer(EmpTransfer.PASSED);
									emp.setGenrer(Genres.ZHENGSHI);
									if (employeeService.saveEmployee(emp))
										empTransferService.saveEmployeeTra(employeeT);
								}
							} else if (employeeT.getEmployeType() == EmployeType.MANAGER) {
								Managers managers = new Managers();
								managers.setPositions(employeeT.getNewPosition() != null ? employeeT.getNewPositionName() : "");
								emp.setDepart(employeeT.getNewDepart());
								emp.setSupervisor(employeeT.getSupervisor());
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
								managers.setCreateDT($date1);
								managers.setGenrer(emp.getGenrer());
								emp.setGenrer(Genres.ZHENGSHI);
								managers.setLabour(ActoresLabour.SYSTEM);
								employeeT.setEmpTransfer(EmpTransfer.PASSED);
								emp.setEmployeType(EmployeType.MANAGER);
								managers.setEmployee(emp);
								employeeService.saveEmployee(emp);
								empTransferService.saveEmployeeTra(employeeT);
								if (employeeService.saveEmployee(emp))
									empTransferService.saveEmployeeTra(employeeT);

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
							}
						}
					}
					/*
					 * employeeService.saveEmployee(employee);
					 */}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("员工档案数据星探转岗同步", e);
		}
	}

}
