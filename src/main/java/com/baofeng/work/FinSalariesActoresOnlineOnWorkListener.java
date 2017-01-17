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

import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.Grant;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.FinPlatformsIncome;
import com.baofeng.oa.entity.FinSalariesOnline;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsOnlineIncome;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IActoresService;
import com.baofeng.oa.service.IFinSalariesOnlineService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsOnlineIncomeService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.CustomDateUtils;
/**
 * 功能：线上艺人工资计算
 * */
public class FinSalariesActoresOnlineOnWorkListener implements BaseObserver {
	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(FinSalariesActoresOnlineOnWorkListener.class);

	public FinSalariesActoresOnlineOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		FinSalariesOnline data = (FinSalariesOnline) analysis(arg);
		Date $date1 = CustomDateUtils.Handler.currentMonthFirstDay(data.getCreateDT());
		Date $date2 = CustomDateUtils.Handler.currentMonthEndDay(data.getCreateDT());

		IActoresService actoresService = (IActoresService) commService.getService().get(IActoresService.class.getName());
		IPlatformsService platformsService = (IPlatformsService) commService.getService().get(IPlatformsService.class.getName());
		IPlatformsActoresService platformsActoresService = (IPlatformsActoresService) commService.getService().get(IPlatformsActoresService.class.getName());
		IPlatformsOnlineIncomeService platformsOnlineIncomeService = (IPlatformsOnlineIncomeService) commService.getService().get(IPlatformsOnlineIncomeService.class.getName());
		IFinSalariesOnlineService finSalariesOnlineService = (IFinSalariesOnlineService) commService.getService().get(IFinSalariesOnlineService.class.getName());
		try {
			Map<Integer, Actores> $map = new HashMap<Integer, Actores>();
			List<FinSalariesOnline> salariesList = finSalariesOnlineService.findFinSalariesOnlineByClass(Actores.class, $date1, $date2);
			List<PlatformsActores> pactoresList = platformsActoresService.findAllPlatformsActores(LineGenres.ONLINE);
			for (FinSalariesOnline salaries : salariesList) {
				Actores actores = actoresService.findActoresById(salaries.getEntityId());
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
					if (actores.getEntryTime() != null && !actores.getEntryTime().before($date2) && actores.getGenre() == LineGenres.OFFLINE)
						continue;
					FinSalariesOnline salaries = finSalariesOnlineService.findFinSalariesOnlineByActores(actores.getId(), Actores.class.getName(), $date1, $date2);
					if (salaries == null) {
						salaries = new FinSalariesOnline();
						salaries.setName(actores.getRealname());
						salaries.setAliasname(actores.getAliasname());
						salaries.setEntityId(actores.getId());
						salaries.setEntityClass(Actores.class.getName());
						salaries.setCreateDT($date1);
						salaries.setBranchs(actores.getBranchs());
						salaries.setPushMoney(new BigDecimal(actores.getPushMoney() == null ? 0.00 : actores.getPushMoney()));
						finSalariesOnlineService.saveFinSalariesOnline(salaries);
					}
					if (salaries != null && salaries.getBilled() != Grant.PUBLISHED) {
						salaries.setName(actores.getRealname());
						salaries.setAliasname(actores.getAliasname());

						/** 保底扣税 */
						BigDecimal sarlarsTax = new BigDecimal(0.00);

						// 税后总业绩计算
						BigDecimal attendance = new BigDecimal(0.00);// 出勤
						List<PlatformsOnlineIncome> incomeList = platformsOnlineIncomeService.findPlatformsOnlineIncomeById(actores.getId(), $date1, $date2);
						if (incomeList != null && incomeList.size() > 0) {
							for (PlatformsOnlineIncome details : incomeList) {
								FinPlatformsIncome incomeDetails = finSalariesOnlineService.findPlatformsIncomeById(salaries.getId(), details.getPlatformsId());
								if (incomeDetails == null) {
									incomeDetails = new FinPlatformsIncome();
									incomeDetails.setFinSalariesOnline(salaries);
									incomeDetails.setBranchs(details.getBranchs());
									incomeDetails.setPlatName(details.getPlatforms());
									incomeDetails.setPlatId(Integer.valueOf(details.getPlatformsId()));
								}
								BigDecimal deductTax = new BigDecimal(0.00);// 分成扣税
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
								if (details.getBgIncome() != null)
									income = details.getBgIncome();
								incomeDetails.setPlatDeduct(platDeduct);
								incomeDetails.setPlatSubsidy(platSubsidy);
								incomeDetails.setIncome(income.subtract(deductTax).subtract(duductGift));// 扣税、扣返还
								if (details.getAttendance() != null && details.getAttendance().doubleValue() > attendance.doubleValue())
									attendance = details.getAttendance();
								finSalariesOnlineService.savePlatformsIncome(incomeDetails);
							}
						}
						salaries.setAttendance(attendance);

						/** 保底 */
						BigDecimal minimumGuarantee = new BigDecimal(actores.getMinimumGuarantee() == null ? 0.00 : actores.getMinimumGuarantee());
						salaries.setBasicSalary(minimumGuarantee);
						if (attendance.doubleValue() < 30) {
							minimumGuarantee = minimumGuarantee.divide(new BigDecimal(30), 4, BigDecimal.ROUND_HALF_UP).multiply(attendance);
						}

						/** 总业绩,提成比例,提成工资 */
						BigDecimal $totalIncome = new BigDecimal(0.00);
						BigDecimal $platDeduct = new BigDecimal(0.00);
						BigDecimal $platSubsidy = new BigDecimal(0.00);
						BigDecimal $pushMoneySalars = new BigDecimal(0.00);
						salaries.setPushMoney(new BigDecimal(actores.getPushMoney() == null ? 0.00 : actores.getPushMoney()));
						List<FinPlatformsIncome> $detailsList = finSalariesOnlineService.findIncomeList(salaries.getId());
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
						salaries.setPlatDeduct($platDeduct);
						salaries.setPlatSubsidy($platSubsidy);
						salaries.setTotalIncome($totalIncome);
						salaries.setPushMoneySalary($pushMoneySalars);

						/** 其他补贴扣税 */
						BigDecimal $tax5 = salaries.getOtherSubsidy().multiply(new BigDecimal(10)).divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP);
						sarlarsTax = sarlarsTax.add($tax5);

						BigDecimal $basic = minimumGuarantee.multiply(new BigDecimal(1.1));
						BigDecimal $push = $pushMoneySalars.add($platSubsidy).add(salaries.getOtherSubsidy()).subtract(sarlarsTax);
						if ($push.doubleValue() < $basic.doubleValue()) {
							salaries.setPushMoneySalary($basic);
							sarlarsTax = $basic.subtract(minimumGuarantee);
							sarlarsTax = sarlarsTax.add($platSubsidy.multiply(new BigDecimal(10)).divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP));
							salaries.setActoresIncomeTax(sarlarsTax);
						} else {
							salaries.setActoresIncomeTax(sarlarsTax);
							salaries.setPushMoneySalary($pushMoneySalars);
						}

						/** 实发工资 = 提成 - 平台扣除 + 平台补贴 */
						BigDecimal realitySalary = new BigDecimal(0.00).add(salaries.getPlatSubsidy()).add(salaries.getPushMoneySalary()).subtract(salaries.getPlatDeduct())
								.subtract(salaries.getOtherDeduct()).subtract(salaries.getActoresIncomeTax());

						if ($push.doubleValue() > $basic.doubleValue()) {
							realitySalary = realitySalary.add(salaries.getOtherSubsidy());
						}
						salaries.setRealitySalary(realitySalary);
						salaries.setRealExpenditure(realitySalary.add(sarlarsTax));
						salaries.setNetProfit($totalIncome.subtract(realitySalary));
						finSalariesOnlineService.saveFinSalariesOnline(salaries);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("计算线上艺人工资失败", e);
		}
	
		
	}

}
