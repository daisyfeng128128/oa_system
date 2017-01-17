package com.baofeng.work;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.Account;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.Grant;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.EmployeeLeave;
import com.baofeng.oa.entity.FinPlatformsIncome;
import com.baofeng.oa.entity.FinSalaries;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsOfflineIncome;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IActoresService;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmpAttendanceService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IFinSalariesService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsOfflineIncomeService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.BuildAccumulationFunds;
import com.baofeng.utils.BuildSocialSecurity;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;

/**
 * 功能：线下艺人工资计算
 * */
public class FinSalariesActoresOfflineOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(FinSalariesActoresOfflineOnWorkListener.class);

	public FinSalariesActoresOfflineOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {
		FinSalaries data = (FinSalaries) analysis(arg);
		Date $date1 = CustomDateUtils.Handler.currentMonthFirstDay(data.getCreateDT());
		Date $date2 = CustomDateUtils.Handler.currentMonthEndDay(data.getCreateDT());
		IActoresService actoresService = (IActoresService) commService.getService().get(IActoresService.class.getName());
		IEmployeeService employeeService = (IEmployeeService) commService.getService().get(IEmployeeService.class.getName());
		IPlatformsService platformsService = (IPlatformsService) commService.getService().get(IPlatformsService.class.getName());
		IBranchOfficeService branchOfficeService = (IBranchOfficeService) commService.getService().get(IBranchOfficeService.class.getName());
		IEmpAttendanceService attendanceService = (IEmpAttendanceService) commService.getService().get(IEmpAttendanceService.class.getName());
		IFinSalariesService finSalariesService = (IFinSalariesService) commService.getService().get(IFinSalariesService.class.getName());
		IPlatformsActoresService platformsActoresService = (IPlatformsActoresService) commService.getService().get(IPlatformsActoresService.class.getName());
		IPlatformsOfflineIncomeService platformsOfflineIncomeService = (IPlatformsOfflineIncomeService) commService.getService()
				.get(IPlatformsOfflineIncomeService.class.getName());
		try {
			Map<Integer, Actores> $map = new HashMap<Integer, Actores>();
			List<FinSalaries> salariesList = finSalariesService.findFinSalariesByActores(Actores.class, $date1, $date2);
			List<PlatformsActores> pactoresList = platformsActoresService.findAllPlatformsActores(LineGenres.OFFLINE);
			for (FinSalaries salaries : salariesList) {
				Actores actores = actoresService.findActoresBySession(salaries.getEmployee().getId());
				if (actores != null && !$map.containsKey(actores.getId()))
					$map.put(actores.getId(), actores);
			}
			for (PlatformsActores pactores : pactoresList) {
				Actores actores = pactores.getActores();
				if (!$map.containsKey(actores.getId()))
					$map.put(actores.getId(), actores);
			}
			List<Actores> actoresList = new ArrayList<Actores>($map.values());
			if (actoresList != null && actoresList.size() > 0) {
				for (Actores actores : actoresList) {
					Boolean isNumber = false;
					FinSalaries salaries = null;
					Employee employee = null;
					if (actores.getLabour() == ActoresLabour.SYSTEM && actores.getEmployee() != null) {
						employee = actores.getEmployee();
						if (employee.getJoinDate() != null && !employee.getJoinDate().before($date2) && actores.getGenre() == LineGenres.OFFLINE)
							continue;
						salaries = finSalariesService.findFinSalariesByNumber(employee.getNumber(), employee.getBranchs(), $date1, $date2);
						if (salaries == null && employee != null) {
							salaries = new FinSalaries();
							isNumber = Boolean.valueOf(true);
							salaries.setNumber(employee.getNumber());
							salaries.setName(employee.getName());
							salaries.setAliasname(employee.getAliasname());
							if (employee.getDepart() != null)
								salaries.setDeptName(employee.getDepart().getName());
							if (employee.getPosition() != null)
								salaries.setPositionsName(employee.getPosition().getName());
							salaries.setAccount(employee.getAccount());
							salaries.setEmployee(employee);
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

							/** 出勤 */
							Float attendance = attendanceService.findAttendanceByNumberAndDate(salaries.getNumber(), salaries.getBranchs(), $date1, $date2);
							salaries.setAttendance(new BigDecimal(attendance));
							if (attendance.floatValue() <= Constants.DEFAULT_ATTENDANCE) {
								BigDecimal $atts = new BigDecimal(Constants.DEFAULT_ATTENDANCE - attendance.doubleValue());
								BigDecimal $salaries = salaries.getBasicSalary().add(salaries.getSubsidySalary());
								BigDecimal casualLeave = $salaries.divide(new BigDecimal(Constants.DEFAULT_ATTENDANCE), 4, BigDecimal.ROUND_HALF_UP).multiply($atts);
								salaries.setCasualLeave(casualLeave);
							} else {
								salaries.setCasualLeave(new BigDecimal(0));
							}

							/** 底薪 */
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

							/** 底薪扣税 */
							BigDecimal sarlarsTax = new BigDecimal(0.00);
							BigDecimal tax = new BigDecimal(10);
							BigDecimal tax1 = salaries.getBasicSalary().multiply(tax).divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP);
							BigDecimal tax2 = salaries.getSubsidySalary().multiply(tax).divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP);
							BigDecimal $casul = salaries.getCasualLeave().multiply(tax).divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP);
							if (employee.getGenrer() == Genres.SHIYONG) {
								sarlarsTax = tax1.add(tax2);
								salaries.setSubsidySalary(new BigDecimal(employee.getProbationSalary() == null ? 0.00 : employee.getProbationSalary()));
							} else {
								if (employee.getGenrer() == Genres.PENDING) {
									EmployeeLeave leave = employeeService.findEmployeeLeaveByEmpId(employee.getId());
									if (leave != null) {
										if (leave.getGenrer() != null && leave.getGenrer() == Genres.SHIYONG) {
											sarlarsTax = tax1.add(tax2);
											salaries.setSubsidySalary(new BigDecimal(employee.getProbationSalary() == null ? 0.00 : employee.getProbationSalary()));
										} else {
											if (employee.getJiaojin() == 0)
												sarlarsTax = tax1.add(tax2);
											else
												sarlarsTax = tax2;
											salaries.setSubsidySalary(new BigDecimal(employee.getOtherSubsidy() == null ? 0.00 : employee.getOtherSubsidy()));
										}
									}
								} else {
									if (employee.getJiaojin() == 0)
										sarlarsTax = tax1.add(tax2);
									else
										sarlarsTax = tax2;
									salaries.setSubsidySalary(new BigDecimal(employee.getOtherSubsidy() == null ? 0.00 : employee.getOtherSubsidy()));
								}
							}
							sarlarsTax = new BigDecimal(Math.max(sarlarsTax.subtract($casul).doubleValue(), 0));
							sarlarsTax = new BigDecimal(0.00);

							/** 税后总业绩计算 */
							List<PlatformsOfflineIncome> incomeList = platformsOfflineIncomeService.findPlatformsOfflineIncomeById(actores.getId(), $date1, $date2);
							if (incomeList != null && incomeList.size() > 0) {
								for (PlatformsOfflineIncome details : incomeList) {
									FinPlatformsIncome incomeDetails = finSalariesService.findPlatformsIncomeById(salaries.getId(), details.getPlatformsId());
									if (incomeDetails == null) {
										incomeDetails = new FinPlatformsIncome();
										incomeDetails.setFinSalaries(salaries);
										incomeDetails.setBranchs(details.getBranchs());
										incomeDetails.setPlatName(details.getPlatforms());
										incomeDetails.setPlatId(Integer.valueOf(details.getPlatformsId()));
									}
									BigDecimal deductTax = new BigDecimal(0.00);// 扣税
									BigDecimal duductGift = new BigDecimal(0.00);// 礼物返还
									BigDecimal income = new BigDecimal(0.00);// 平台业绩
									BigDecimal platDeduct = new BigDecimal(0.00);// 平台扣除
									BigDecimal platSubsidy = new BigDecimal(0.00);// 平台补贴
									if (details.getDeductTax() != null)
										deductTax = details.getDeductTax();
									if (details.getDuductGift() != null)
										duductGift = details.getDuductGift();
									if (details.getPlatDeduct() != null)
										platDeduct = details.getPlatDeduct();
									if (details.getPlatSubsidy() != null)
										platSubsidy = details.getPlatSubsidy();
									if (details.getIncome() != null)
										income = details.getIncome();
									/** 扣税、扣返还 */
									incomeDetails.setPlatDeduct(platDeduct);
									incomeDetails.setPlatSubsidy(platSubsidy);
									incomeDetails.setIncome(income.subtract(deductTax).subtract(duductGift));
									finSalariesService.savePlatformsIncome(incomeDetails);
								}
							}
							/** 总业绩,提成比例,提成工资 */
							BigDecimal $totalIncome = new BigDecimal(0.00);
							BigDecimal $platDeduct = new BigDecimal(0.00);
							BigDecimal $platSubsidy = new BigDecimal(0.00);
							BigDecimal $pushMoneySalars = new BigDecimal(0.00);
							salaries.setPushMoney(new BigDecimal(actores.getPushMoney() == null ? 0.00 : actores.getPushMoney()));
							List<FinPlatformsIncome> $detailsList = finSalariesService.findIncomeList(salaries.getId());
							if ($detailsList != null && $detailsList.size() > 0) {
								for (FinPlatformsIncome $details : $detailsList) {
									if ($details.getPlatSubsidy() == null)
										$details.setPlatSubsidy(new BigDecimal(0.00));
									if ($details.getPlatDeduct() == null)
										$details.setPlatDeduct(new BigDecimal(0.00));
									Platforms $platforms = platformsService.findPlatforms($details.getPlatId());
									BigDecimal $tax = $platforms.getTax() == null ? new BigDecimal(0.00) : $platforms.getTax();
									BigDecimal $pushSalaries = $details.getIncome().multiply(salaries.getPushMoney()).divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP);
									BigDecimal $tax3 = $pushSalaries.multiply($tax).divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP);
									BigDecimal $tax4 = $details.getPlatSubsidy().multiply($tax).divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP);

									sarlarsTax = sarlarsTax.add($tax3).add($tax4);

									$platSubsidy = $platSubsidy.add($details.getPlatSubsidy());
									$pushMoneySalars = $pushMoneySalars.add($pushSalaries);
									$platDeduct = $platDeduct.add($details.getPlatDeduct());
									$totalIncome = $totalIncome.add($details.getIncome());
								}
							}
							salaries.setPlatLeave($platDeduct);
							salaries.setPlatSubsidy($platSubsidy);
							salaries.setTotalIncome($totalIncome);
							salaries.setPushMoneyActores($pushMoneySalars);

							/** 其他补贴扣税 */
							BigDecimal $tax5 = salaries.getOtherSubsidy().multiply(tax).divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP);
							sarlarsTax = sarlarsTax.add($tax5);

							salaries.setActoresIncomeTax(sarlarsTax);

							/** 社保、公积金 */
							if (isNumber && employee.getGenrer() == Genres.ZHENGSHI) {
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
									salaries.setPn_totalSocial(social.get("pn_totalSocial"));
									salaries.setCp_totalSocial(social.get("cp_totalSocial"));
								}

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
							salaries.make(false);
							finSalariesService.saveFinSalaries(salaries);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("线下艺人计算有误", e);
		}
	}
}
