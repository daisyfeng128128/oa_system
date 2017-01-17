package com.baofeng.work;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.BaseEnums.Account;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.Grant;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.EmployeeLeave;
import com.baofeng.oa.entity.FinSalaries;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.oa.entity.PlatformsManagerOutlay;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmpAttendanceService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IFinSalariesService;
import com.baofeng.oa.service.IPlatformsManagerOutlayService;
import com.baofeng.oa.service.IPlatformsManagerService;
import com.baofeng.utils.BuildAccumulationFunds;
import com.baofeng.utils.BuildSocialSecurity;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;

/**
 * 功能：线下管理工资计算
 * */
public class FinSalariesManagerOfflineOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(FinSalariesManagerOfflineOnWorkListener.class);

	public FinSalariesManagerOfflineOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {
		FinSalaries data = (FinSalaries) analysis(arg);
		Date $date1 = CustomDateUtils.Handler.currentMonthFirstDay(data.getCreateDT());
		Date $date2 = CustomDateUtils.Handler.currentMonthEndDay(data.getCreateDT());

		IFinSalariesService finSalariesService = (IFinSalariesService) commService.getService().get(IFinSalariesService.class.getName());
		IBranchOfficeService branchOfficeService = (IBranchOfficeService) commService.getService().get(IBranchOfficeService.class.getName());
		IEmpAttendanceService attendanceService = (IEmpAttendanceService) commService.getService().get(IEmpAttendanceService.class.getName());
		IPlatformsManagerService platformsManagerService = (IPlatformsManagerService) commService.getService().get(IPlatformsManagerService.class.getName());
		IPlatformsManagerOutlayService platformsManagerOutlayService = (IPlatformsManagerOutlayService) commService.getService()
				.get(IPlatformsManagerOutlayService.class.getName());
		IEmployeeService employeeService = (IEmployeeService) commService.getService().get(IEmployeeService.class.getName());
		try {
			List<PlatformsManager> managerList = platformsManagerService.findAllPlatformsManager();
			if (managerList != null && managerList.size() > 0) {
				for (PlatformsManager pmanager : managerList) {
					Managers manager = pmanager.getManager();
					Boolean isNumber = false;
					Employee employee = null;
					FinSalaries salaries = null;
					if (manager.getLabour() == ActoresLabour.SYSTEM) {
						employee = manager.getEmployee();
						salaries = finSalariesService.findFinSalariesByNumber(employee.getNumber(), employee.getBranchs(), $date1, $date2);
						if (salaries == null && employee != null) {
							if (employee.getJoinDate() != null && !employee.getJoinDate().before($date2))
								continue;
							isNumber = Boolean.valueOf(true);
							salaries = new FinSalaries();
							salaries.setNumber(employee.getNumber());
							salaries.setName(employee.getName());
							salaries.setAliasname(employee.getAliasname());
							if (employee.getDepart() != null)
								salaries.setDeptName(employee.getDepart().getName());
							if (employee.getPosition() != null)
								salaries.setPositionsName(employee.getPosition().getName());
							salaries.setAccount(employee.getAccount());
							salaries.setEmployee(employee);
							salaries.setPushMoney(new BigDecimal(0.00));
							salaries.setIsActores(employee.getActores() == com.baofeng.commons.entity.Operator.Actores.YES);
							salaries.setCreateDT($date1);
							salaries.setBranchs(employee.getBranchs());
							salaries.setEmployeType(employee.getEmployeType());
							salaries.setPostAllowance(new BigDecimal(employee.getJobSubsidyE() == null ? 0f : employee.getJobSubsidyE()));
							salaries.setCompanyOtheSub(new BigDecimal(employee.getOtherSubsidyE() == null ? 0f : employee.getOtherSubsidyE()));
							finSalariesService.saveFinSalaries(salaries);
						}
						if (salaries != null && salaries.getBilled() != Grant.PUBLISHED) {
							salaries.setIsSocial(employee.getJiaojin() == 0 ? false : true);
							salaries.setIsProvident(employee.getJiaojin() == 0 ? false : true);

							// 同步修改字段
							salaries.setBasicSalary(new BigDecimal(employee.getSalary()));
							if (employee.getGenrer() == Genres.SHIYONG) {
								salaries.setSubsidySalary(new BigDecimal(employee.getProbationSalary() == null ? 0.00 : employee.getProbationSalary()));
							} else {
								if (employee.getGenrer() == Genres.PENDING) {
									EmployeeLeave leave = employeeService.findEmployeeLeaveByEmpId(employee.getId());
									if (leave != null) {
										if (leave.getGenrer() != null && leave.getGenrer() == Genres.SHIYONG) {
											salaries.setSubsidySalary(new BigDecimal(employee.getProbationSalary() == null ? 0.00 : employee.getProbationSalary()));
										} else {
											salaries.setSubsidySalary(new BigDecimal(employee.getOtherSubsidy() == null ? 0.00 : employee.getOtherSubsidy()));
										}
									}
								} else {
									salaries.setSubsidySalary(new BigDecimal(employee.getOtherSubsidy() == null ? 0.00 : employee.getOtherSubsidy()));
								}
							}

							// 出勤
							Float attendance = attendanceService.findAttendanceByNumberAndDate(salaries.getNumber(), salaries.getBranchs(), $date1, $date2);
							salaries.setAttendance(new BigDecimal(attendance));
							if (attendance.floatValue() <= Constants.DEFAULT_ATTENDANCE) {
								BigDecimal $atts = new BigDecimal(Constants.DEFAULT_ATTENDANCE - attendance.doubleValue());
								BigDecimal $salaries = salaries.getBasicSalary().add(salaries.getSubsidySalary() != null ? salaries.getSubsidySalary() : new BigDecimal(0.00));
								BigDecimal casualLeave = $salaries.divide(new BigDecimal(Constants.DEFAULT_ATTENDANCE), 4, BigDecimal.ROUND_HALF_UP).multiply($atts);
								salaries.setCasualLeave(casualLeave);
							}

							// 平台汇总
							BigDecimal pushMoneySalary = new BigDecimal(0.00);// 提成总数
							BigDecimal platSubsidy = new BigDecimal(0.00);// 平台补贴
							BigDecimal platDeduct = new BigDecimal(0.00);// 平台扣除
							List<PlatformsManagerOutlay> outlayList = platformsManagerOutlayService.findPlatformsManagerOutlayListNew(manager.getId(), $date1, $date2);
							if (outlayList != null && outlayList.size() > 0) {
								for (PlatformsManagerOutlay outlay : outlayList) {
									if (outlay.getPushMoney() != null)
										pushMoneySalary = pushMoneySalary.add(new BigDecimal(outlay.getPushMoney()));
									if (outlay.getOtherAllowance() != null)
										platSubsidy = platSubsidy.add(new BigDecimal(outlay.getOtherAllowance()));
									if (outlay.getDeduct() != null)
										platDeduct = platDeduct.add(new BigDecimal(outlay.getDeduct()));
								}
							}

							// 提成工资
							salaries.setPushMoneySalary(pushMoneySalary);

							// 社保公积金
							if (isNumber && employee.getGenrer() == Genres.ZHENGSHI) {
								// 社保
								boolean towns = employee.getAccount() == Account.CHENGZHEN;
								BigDecimal SScardinalNumber = new BigDecimal(employee.getSScardinalNumber());
								BranchOffice $branchs = branchOfficeService.readBranchOffice(employee.getBranchs());
								JSONObject json = JSONObject.parseObject($branchs.getSocialsecurity());
								Map<String, BigDecimal> social = BuildSocialSecurity.make(SScardinalNumber, json, towns);
								if (salaries.getIsSocial()) {
									salaries.setPn_pension(social.get("pn_pension"));
									salaries.setPn_medicare(social.get("pn_medicare"));
									salaries.setPn_unemployment(social.get("pn_unemployment"));
									salaries.setCp_pension(social.get("cp_pension"));
									salaries.setCp_medicare(social.get("cp_medicare"));
									salaries.setCp_unemployment(social.get("cp_unemployment"));
									salaries.setCp_occupationalInjury(social.get("cp_occupationalInjury"));
									salaries.setCp_fertility(social.get("cp_fertility"));
								}

								// 公积金
								BigDecimal PFcardinalNumber = new BigDecimal(employee.getPFcardinalNumber());
								Map<String, BigDecimal> aFunds = BuildAccumulationFunds.make(PFcardinalNumber, json, towns);
								if (salaries.getIsProvident()) {
									salaries.setPn_provident(aFunds.get("pn_provident"));
									salaries.setCp_provident(aFunds.get("cp_provident"));
								}
								if (salaries.getIsSocial()) {
									salaries.setPn_totalSocial(social.get("pn_totalSocial"));
									salaries.setCp_totalSocial(social.get("cp_totalSocial"));
								}
								if (salaries.getIsProvident()) {
									salaries.setPn_totalSocial(salaries.getPn_totalSocial().add(aFunds.get("pn_provident")));
									salaries.setCp_totalSocial(salaries.getCp_totalSocial().add(aFunds.get("cp_provident")));
								}
							}

							// 平台扣除
							salaries.setPlatLeave(platDeduct);
							// 平台补贴
							salaries.setPlatSubsidy(platSubsidy);

							// 餐补
							BigDecimal foodSubsidy = new BigDecimal(manager.getFoodSubsidy() == null ? 0.00 : manager.getFoodSubsidy());
							if (attendance.doubleValue() >= Constants.DEFAULT_ATTENDANCE)
								foodSubsidy = foodSubsidy.multiply(new BigDecimal(Constants.DEFAULT_ATTENDANCE));
							else
								foodSubsidy = foodSubsidy.multiply(new BigDecimal(attendance));
							salaries.setFoodSubsidy(foodSubsidy);
							salaries.make(true);
							finSalariesService.saveFinSalaries(salaries);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("线下管理工资计算有误", e);
		}
	}
}
