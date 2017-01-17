package com.baofeng.work;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.Grant;
import com.baofeng.oa.entity.FinPlatformsIncome;
import com.baofeng.oa.entity.FinSalariesOnline;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.entity.PlatformsManagerOutlay;
import com.baofeng.oa.entity.PlatformsOnlineIncome;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IActoresService;
import com.baofeng.oa.service.IFinSalariesOnlineService;
import com.baofeng.oa.service.IManagerService;
import com.baofeng.oa.service.IPlatformsManagerOutlayService;
import com.baofeng.oa.service.IPlatformsOnlineIncomeService;
import com.baofeng.utils.BuildIncomeTax;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;
/**
 * 功能：线上薪资
 * */
public class FinSalariesOnlineOnWorkListener implements BaseObserver {
	
	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(FinSalariesOnlineOnWorkListener.class);

	public FinSalariesOnlineOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		FinSalariesOnline $finSalariesOnline = (FinSalariesOnline) analysis(arg);
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay($finSalariesOnline.getCreateDT());
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay($finSalariesOnline.getCreateDT());
		IFinSalariesOnlineService finSalariesOnlineService = (IFinSalariesOnlineService) commService.getService().get(IFinSalariesOnlineService.class.getName());
		IActoresService actoresService = (IActoresService) commService.getService().get(IActoresService.class.getName());
		IManagerService managerService = (IManagerService) commService.getService().get(IManagerService.class.getName());
		IPlatformsOnlineIncomeService platformsOnlineIncomeService = (IPlatformsOnlineIncomeService) commService.getService().get(IPlatformsOnlineIncomeService.class.getName());
		IPlatformsManagerOutlayService platformsManagerOutlayService = (IPlatformsManagerOutlayService) commService.getService().get(IPlatformsManagerOutlayService.class.getName());
		try {
			makeActores(date1, date2, finSalariesOnlineService, actoresService, managerService, platformsOnlineIncomeService, platformsManagerOutlayService);
			makeManager(date1, date2, finSalariesOnlineService, managerService, platformsManagerOutlayService);
		} catch (Exception e) {
			logger.error("同步员工线上工资有误", e);
		}
	
	}

	private void makeActores(Date date1, Date date2, IFinSalariesOnlineService finSalariesOnlineService, IActoresService actoresService, IManagerService managerService,
			IPlatformsOnlineIncomeService platformsOnlineIncomeService, IPlatformsManagerOutlayService platformsManagerOutlayService) {
		// 同步线上艺人工资
		List<Actores> actoresList = actoresService.findAllActoresOnline();
		List<FinSalariesOnline> listCheck = new ArrayList<FinSalariesOnline>();
		if (actoresList != null && actoresList.size() > 0) {
			for (Actores actores : actoresList) {
				if (!finSalariesOnlineService.findValidation(actores.getClass().getName(), actores.getId(), date1, date2)) {
					FinSalariesOnline salary = new FinSalariesOnline();
					salary.setEntityClass(actores.getClass().getName());
					salary.setEntityId(actores.getId());
					salary.setBranchs(actores.getBranchs());
					salary.setName(actores.getRealname());
					salary.setAliasname(actores.getAliasname());
					salary.setActoresIncomeTax(new BigDecimal(0.00));
					salary.setPushMoney(new BigDecimal(actores.getPushMoney() == null ? 0 : actores.getPushMoney()));
					salary.setBasicSalary(new BigDecimal(actores.getMinimumGuarantee() == null ? 0 : actores.getMinimumGuarantee()));
					salary.setBilled(Grant.UNKNOWN);
					salary.setCreateDT(date1);

					BigDecimal totalIncome = new BigDecimal(0);
					BigDecimal attendance = new BigDecimal(0);
					// 平台扣除
					BigDecimal platDeduct = new BigDecimal(0.00);
					// 平台补贴
					BigDecimal platSubsidy = new BigDecimal(0);
					List<PlatformsOnlineIncome> platIncomeList = platformsOnlineIncomeService.findPlatformsOnlineIncomeById(actores.getId(), date1, date2);
					if (platIncomeList != null && platIncomeList.size() > 0) {
						for (PlatformsOnlineIncome onlineIncome : platIncomeList) {
							FinPlatformsIncome finIncome = new FinPlatformsIncome();
							finIncome.setBranchs(salary.getBranchs());
							finIncome.setFinSalariesOnline(salary);
							finIncome.setPlatId(Integer.valueOf(onlineIncome.getPlatformsId()));
							finIncome.setPlatName(onlineIncome.getPlatforms());
							BigDecimal deductTax = new BigDecimal(0);// 分成扣税
							BigDecimal duductGift = new BigDecimal(0.00);// 礼物返还
							// 分成扣税
							if (onlineIncome.getDeductTax() != null)
								deductTax = onlineIncome.getDeductTax();
							if (onlineIncome.getDuductGift() != null)
								duductGift = onlineIncome.getDuductGift();
							// 平台补贴
							if (onlineIncome.getPlatSubsidy() != null) {
								platSubsidy = platSubsidy.add(onlineIncome.getPlatSubsidy());
							}
							// 平台平台扣除
							if (onlineIncome.getPlatDeduct() != null) {
								platDeduct = platDeduct.add(onlineIncome.getPlatDeduct());
							}
							BigDecimal $income = (onlineIncome.getBgIncome() == null ? new BigDecimal(0.00) : onlineIncome.getBgIncome());
							$income = $income.subtract(deductTax).subtract(duductGift);// 扣税、扣返还
							finIncome.setIncome($income);
							totalIncome = totalIncome.add($income);
							if (onlineIncome.getAttendance() != null && onlineIncome.getAttendance().doubleValue() > attendance.doubleValue())
								attendance = onlineIncome.getAttendance();
							finSalariesOnlineService.updateFinPlatformsIncome(finIncome);
						}
					}
					// 税后总业绩
					if (totalIncome != null) {
						salary.setTotalIncome(totalIncome);
					}
					if (platDeduct != null) {
						salary.setPlatDeduct(platDeduct);
					}
					if (platSubsidy != null) {
						salary.setPlatSubsidy(platSubsidy);
					}
					// 出勤天数
					if (attendance != null) {
						salary.setAttendance(attendance);
					}
					// 保底
					if (attendance != null && attendance.doubleValue() < Double.valueOf(30).doubleValue()) {
						BigDecimal minimumGuarantee = new BigDecimal(actores.getMinimumGuarantee());
						minimumGuarantee = minimumGuarantee.divide(new BigDecimal(30), 4, BigDecimal.ROUND_HALF_UP).multiply(salary.getAttendance());
						salary.setBasicSalary(minimumGuarantee);
					}
					// 提成比例
					BigDecimal pushMoney = new BigDecimal(0);
					if (actores != null && actores.getPushMoney() != null) {
						pushMoney = new BigDecimal(actores.getPushMoney());
					}
					salary.setPushMoney(pushMoney);
					// 提成 = max(税后总业绩 *提成比例,保底)
					if (salary.getBasicSalary() != null && salary.getPushMoney() != null) {
						salary.setPushMoneySalary(salary.getTotalIncome().multiply(salary.getPushMoney()).divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
					}
					if (salary.getBasicSalary().doubleValue() > salary.getPushMoneySalary().doubleValue()) {
						salary.setPushMoneySalary(salary.getBasicSalary());
					}
					// 实发工资 = 提成 - 平台扣除 + 平台补贴
					BigDecimal realitySalary = new BigDecimal(0);
					realitySalary = salary.getPushMoneySalary().add(platSubsidy).subtract(platDeduct);
					realitySalary = realitySalary.doubleValue() < 0 ? new BigDecimal(0.00) : realitySalary;
					salary.setRealitySalary(realitySalary);

					// 实际盈亏 =税后总业绩 - 实发工资
					salary.setNetProfit(salary.getTotalIncome().subtract(salary.getRealitySalary()));
					listCheck.add(salary);
				}
			}
		}
		finSalariesOnlineService.addSalary(listCheck);
	}

	private void makeManager(Date date1, Date date2, IFinSalariesOnlineService finSalariesOnlineService, IManagerService managerService,
			IPlatformsManagerOutlayService platformsManagerOutlayService) {
		// 同步线上管理工资
		List<FinSalariesOnline> listCheck = new ArrayList<FinSalariesOnline>();
		List<Managers> managerList = managerService.findAllOnlineManagers();
		if (managerList != null && managerList.size() > 0) {
			for (Managers manager : managerList) {
				if (!finSalariesOnlineService.findValidation(manager.getClass().getName(), manager.getId(), date1, date2)) {
					FinSalariesOnline salary = new FinSalariesOnline();
					salary.setAliasname(manager.getAliasname());
					salary.setCreateDT(date1);
					salary.setBranchs(manager.getBranchs());
					salary.setEntityClass(manager.getClass().getName());
					salary.setEntityId(manager.getId());
					salary.setName(manager.getRealname());
					salary.setActoresIncomeTax(new BigDecimal(0.00));
					salary.setBilled(Grant.UNKNOWN);

					List<PlatformsManagerOutlay> listOutlay = platformsManagerOutlayService.findPlatformsManagerOutlayListNew(manager.getId(), date1, date2);
					if (listOutlay != null && listOutlay.size() > 0) {
						/** 提成总数 */
						BigDecimal pushMoney = new BigDecimal(0);
						/** 平台补贴 */
						BigDecimal platSubsidy = new BigDecimal(0);
						/** 出勤天数 */
						BigDecimal attendance = new BigDecimal(0);
						/** 平台扣除 */
						BigDecimal platDeduct = new BigDecimal(0.00);
						for (Object o : listOutlay) {
							PlatformsManagerOutlay out = (PlatformsManagerOutlay) o;
							if (out != null) {
								pushMoney = pushMoney.add(new BigDecimal(out.getPushMoney()));
								platSubsidy = platSubsidy.add(new BigDecimal(out.getOtherAllowance()));
								platDeduct = platDeduct.add(new BigDecimal(out.getDeduct()));
								if (out.getAttendance() != null && out.getAttendance().doubleValue() > 0 && attendance.doubleValue() < out.getAttendance().doubleValue()) {
									attendance = new BigDecimal(out.getAttendance());
								}
							}
						}
						/** 助理工资对象 */
						salary.setAttendance(attendance);
						salary.setPushMoney(pushMoney);
						/** 底薪 */
						BigDecimal basicSalary = new BigDecimal(0.00);
						if (attendance.doubleValue() > 0 && manager.getGenrer() == Genres.ZHENGSHI) {
							if (attendance.doubleValue() >= new BigDecimal(Constants.DEFAULT_ATTENDANCE).doubleValue()) {
								basicSalary = new BigDecimal(manager.getBasicSalary());
							} else {
								BigDecimal $basicSalary = new BigDecimal(manager.getBasicSalary());
								$basicSalary = $basicSalary.divide(new BigDecimal(Constants.DEFAULT_MAXNUM), 4, BigDecimal.ROUND_HALF_UP).multiply(attendance);
								basicSalary = $basicSalary;
							}
						} else if (attendance.doubleValue() > 0 && manager.getGenrer() == Genres.SHIYONG) {
							if (attendance.doubleValue() >= new BigDecimal(Constants.DEFAULT_ATTENDANCE).doubleValue()) {
								basicSalary = new BigDecimal(manager.getProbationSalary());
							} else {
								BigDecimal $basicSalary = new BigDecimal(manager.getProbationSalary());
								$basicSalary = $basicSalary.divide(new BigDecimal(Constants.DEFAULT_MAXNUM), 4, BigDecimal.ROUND_HALF_UP).multiply(attendance);
								basicSalary = $basicSalary;
							}
						}
						/** 底薪 */
						salary.setBasicSalary(new BigDecimal(manager.getBasicSalary()));
						platDeduct = platDeduct.add(salary.getBasicSalary().subtract(basicSalary).setScale(4, BigDecimal.ROUND_HALF_UP));
						salary.setPlatSubsidy(platSubsidy);
						salary.setPlatDeduct(platDeduct);
						/** 个人所得税 */
						BigDecimal $temp = salary.getBasicSalary();
						salary.setTaxableSalary($temp);
						salary.setIndividualIncomeTax(BuildIncomeTax.makeTax(new BigDecimal(3500), $temp));

						/** 实发工资 */
						BigDecimal realitySalary = new BigDecimal(0.0);
						realitySalary = realitySalary.add(pushMoney).add(salary.getOtherSubsidy()).add(salary.getBasicSalary()).add(salary.getPlatSubsidy());
						realitySalary = realitySalary.doubleValue() < 0 ? new BigDecimal(0.00) : realitySalary;
						salary.setRealitySalary(realitySalary.setScale(2, BigDecimal.ROUND_HALF_UP));

						/** 实际支出 */
						BigDecimal netSalaryOutlay = new BigDecimal(0);
						netSalaryOutlay = netSalaryOutlay.add(salary.getRealitySalary());
					}
					listCheck.add(salary);
				}
			}
			finSalariesOnlineService.addSalary(listCheck);
		}
	}
}
