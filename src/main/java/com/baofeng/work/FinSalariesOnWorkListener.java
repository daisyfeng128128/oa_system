package com.baofeng.work;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.baofeng.commons.entity.Operator.Actores;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.BaseEnums.Account;
import com.baofeng.oa.entity.BaseEnums.Grant;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.EmployeeLeave;
import com.baofeng.oa.entity.FinSalaries;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmpAttendanceService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IFinSalariesService;
import com.baofeng.utils.BuildAccumulationFunds;
import com.baofeng.utils.BuildSocialSecurity;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;

public class FinSalariesOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(FinSalariesOnWorkListener.class);

	public FinSalariesOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		FinSalaries $finSalaries = (FinSalaries) analysis(arg);
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay($finSalaries.getCreateDT());
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay($finSalaries.getCreateDT());

		IEmployeeService employeeService = (IEmployeeService) commService.getService().get(IEmployeeService.class.getName());
		IBranchOfficeService branchOfficeService = (IBranchOfficeService) commService.getService().get(IBranchOfficeService.class.getName());
		IFinSalariesService finSalariesService = (IFinSalariesService) commService.getService().get(IFinSalariesService.class.getName());
		IEmpAttendanceService attendanceService = (IEmpAttendanceService) commService.getService().get(IEmpAttendanceService.class.getName());
		try {
			List<Employee> empList = employeeService.findAllEmployees(date2);
			if (empList != null && empList.size() > 0) {
				for (Employee emp : empList) {
					if (emp.getGenrer() != Genres.LIZHI && emp.getGenrer() != Genres.MANAGER && emp.getGenrer() != Genres.SUPERS
							&& !finSalariesService.findValidation(emp, date1, date2)) {
						FinSalaries finSalaries = new FinSalaries();
						finSalaries.setName(emp.getName());
						finSalaries.setNumber(emp.getNumber());
						finSalaries.setAccount(emp.getAccount());
						finSalaries.setBranchs(emp.getBranchs());
						finSalaries.setAliasname(emp.getAliasname());
						finSalaries.setDeptName(emp.getDepart() != null ? emp.getDepart().getName() : "");
						finSalaries.setPositionsName(emp.getPosition() != null ? emp.getPosition().getName() : "");
						finSalaries.setIsActores(emp.getActores() == Actores.YES);
						finSalaries.setIsProvident(emp.getJiaojin() == 0 ? false : true);
						finSalaries.setIsSocial(emp.getJiaojin() == 0 ? false : true);
						finSalaries.setEmployeType(emp.getEmployeType());
						finSalaries.setCreateDT(date1);
						finSalaries.setEmployee(emp);
						finSalaries.setBilled(Grant.UNKNOWN);
						finSalaries.setBasicSalary(new BigDecimal(emp.getSalary() == null ? 0.00f : emp.getSalary()));
						finSalaries.setPostAllowance(new BigDecimal(emp.getJobSubsidyE() == null ? 0f : emp.getJobSubsidyE()));
						finSalaries.setCompanyOtheSub(new BigDecimal(emp.getOtherSubsidyE() == null ? 0f : emp.getOtherSubsidyE()));
						if (emp.getGenrer() == Genres.SHIYONG) {
							finSalaries.setSubsidySalary(new BigDecimal(emp.getProbationSalary() == null ? 0.00 : emp.getProbationSalary()));
						} else {
							if (emp.getGenrer() == Genres.PENDING) {
								EmployeeLeave leave = employeeService.findEmployeeLeaveByEmpId(emp.getId());
								if (leave != null) {
									if (leave.getGenrer() != null && leave.getGenrer() == Genres.SHIYONG) {
										finSalaries.setSubsidySalary(new BigDecimal(emp.getProbationSalary() == null ? 0.00 : emp.getProbationSalary()));
									} else {
										finSalaries.setSubsidySalary(new BigDecimal(emp.getOtherSubsidy() == null ? 0.00 : emp.getOtherSubsidy()));
									}
								}
							} else {
								finSalaries.setSubsidySalary(new BigDecimal(emp.getOtherSubsidy() == null ? 0.00 : emp.getOtherSubsidy()));
							}
						}
						// 考勤
						Float attednance = attendanceService.findAttendanceByNumberAndDate(emp.getNumber(), emp.getBranchs(), date1, date2);
						finSalaries.setAttendance(new BigDecimal(attednance));
						if (attednance.floatValue() < Constants.DEFAULT_ATTENDANCE) {
							BigDecimal $atts = new BigDecimal(Constants.DEFAULT_ATTENDANCE - attednance.doubleValue());
							BigDecimal $salaries = finSalaries.getBasicSalary().add(finSalaries.getSubsidySalary());
							BigDecimal casualLeave = $salaries.divide(new BigDecimal(Constants.DEFAULT_ATTENDANCE), 4, BigDecimal.ROUND_HALF_UP).multiply($atts);
							finSalaries.setCasualLeave(casualLeave);
						}

						// 餐补
						BigDecimal foodSubsidy = new BigDecimal(emp.getFoodSubsidyE() == null ? 0.00 : emp.getFoodSubsidyE());
						if (attednance.doubleValue() >= Constants.DEFAULT_ATTENDANCE)
							foodSubsidy = foodSubsidy.multiply(new BigDecimal(Constants.DEFAULT_ATTENDANCE));
						else
							foodSubsidy = foodSubsidy.multiply(new BigDecimal(attednance));
						finSalaries.setFoodSubsidy(foodSubsidy);

						// 社保
						if (emp.getJiaojin() != null && emp.getJiaojin().intValue() == Integer.valueOf(1).intValue()) {
							boolean towns = emp.getAccount() == Account.CHENGZHEN;
							BigDecimal SScardinalNumber = new BigDecimal(emp.getSScardinalNumber());
							BranchOffice $branchs = branchOfficeService.readBranchOffice(emp.getBranchs());
							JSONObject json = JSONObject.parseObject($branchs.getSocialsecurity());
							Map<String, BigDecimal> social = BuildSocialSecurity.make(SScardinalNumber, json, towns);

							finSalaries.setPn_pension(social.get("pn_pension"));
							finSalaries.setPn_medicare(social.get("pn_medicare"));
							finSalaries.setPn_unemployment(social.get("pn_unemployment"));

							finSalaries.setCp_pension(social.get("cp_pension"));
							finSalaries.setCp_medicare(social.get("cp_medicare"));
							finSalaries.setCp_unemployment(social.get("cp_unemployment"));
							finSalaries.setCp_occupationalInjury(social.get("cp_occupationalInjury"));
							finSalaries.setCp_fertility(social.get("cp_fertility"));

							// 公积金
							BigDecimal PFcardinalNumber = new BigDecimal(emp.getPFcardinalNumber());
							Map<String, BigDecimal> aFunds = BuildAccumulationFunds.make(PFcardinalNumber, json, towns);
							finSalaries.setPn_provident(aFunds.get("pn_provident"));
							finSalaries.setCp_provident(aFunds.get("cp_provident"));

							// 个人合计
							finSalaries.setPn_totalSocial(social.get("pn_totalSocial").add(aFunds.get("pn_provident")));
							// 公司合计
							finSalaries.setCp_totalSocial(social.get("cp_totalSocial").add(aFunds.get("cp_provident")));
						}
						finSalaries.make(true);
						finSalariesService.saveFinSalaries(finSalaries);
					}
				}
			}
		} catch (Exception e) {
			logger.error("同步员工薪资有误", e);
		}
	}
}
