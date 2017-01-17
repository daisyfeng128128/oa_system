package com.baofeng.work;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.FinEarningsIncome;
import com.baofeng.oa.entity.FinPlatformsIncome;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsOfflineIncome;
import com.baofeng.oa.entity.PlatformsOnlineIncome;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IFinEarningsIncomeService;
import com.baofeng.oa.service.IFinSalariesOnlineService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsOfflineIncomeService;
import com.baofeng.oa.service.IPlatformsOnlineIncomeService;
import com.baofeng.utils.CustomDateUtils;

/**
 * 功能： 统计收益明细
 * */
public class FinEaringsIncomeOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(FinEaringsIncomeOnWorkListener.class);

	public FinEaringsIncomeOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {
		FinEarningsIncome finEarningsIncome = (FinEarningsIncome) analysis(arg);

		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(finEarningsIncome.getCreateDT());
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(finEarningsIncome.getCreateDT());
		IFinEarningsIncomeService finEarningsIncomeService = (IFinEarningsIncomeService) commService.getService().get(IFinEarningsIncomeService.class.getName());
		IPlatformsActoresService platformsActoresService = (IPlatformsActoresService) commService.getService().get(IPlatformsActoresService.class.getName());
		IFinSalariesOnlineService finSalariesOnlineService = (IFinSalariesOnlineService) commService.getService().get(IFinSalariesOnlineService.class.getName());
		IPlatformsOnlineIncomeService platformsOnlineIncomeService = (IPlatformsOnlineIncomeService) commService.getService().get(IPlatformsOnlineIncomeService.class.getName());
		IPlatformsOfflineIncomeService platformsOfflineIncomeService = (IPlatformsOfflineIncomeService) commService.getService()
				.get(IPlatformsOfflineIncomeService.class.getName());
		try {
			List<PlatformsActores> listPlatAct = platformsActoresService.findAllPlatformsActores();
			List<FinEarningsIncome> listFin = finEarningsIncomeService.findAllFinEarningsIncome(date1, date2);
			// 艺人map
			Map<Integer, Actores> actoresMap = new HashMap<Integer, Actores>();

			// 艺人map
			Map<Integer, Actores> $actoresMap = new HashMap<Integer, Actores>();
			// 收益map
			Map<Integer, FinEarningsIncome> finMap = new HashMap<Integer, FinEarningsIncome>();
			if (listPlatAct != null && listPlatAct.size() > 0) {
				for (PlatformsActores platAct : listPlatAct) {
					Actores actores = platAct.getActores();
					if (actores != null && actores.getEntryTime() != null && !actores.getEntryTime().before(date2))
						continue;
					actoresMap.put(platAct.getActores().getId(), platAct.getActores());
					$actoresMap.put(platAct.getActores().getId(), platAct.getActores());
				}
			}
			if (listFin != null && listFin.size() > 0) {
				for (FinEarningsIncome income : listFin) {
					finMap.put(income.getActores_id(), income);
				}
			}
			Set<Integer> actoresIdSet = actoresMap.keySet();
			Set<Integer> fin_IdSet = finMap.keySet();
			actoresIdSet.removeAll(fin_IdSet);
			if (actoresIdSet != null && actoresIdSet.size() > 0) {
				for (Integer actoresId : actoresIdSet) {
					Actores actores = actoresMap.get(actoresId);
					if (actores != null) {
						FinEarningsIncome finEarn = new FinEarningsIncome();
						finEarn.setActores_id(actoresId);
						finEarn.setAliasname(actores.getAliasname());
						finEarn.setName(actores.getRealname());
						finEarn.setNumber(actores.getNumber());
						finEarn.setGenre(actores.getGenre());
						finEarn.setCreateDT(date1);
						finEarn.setBranchs(actores.getBranchs());
						finEarningsIncomeService.addEarnings(finEarn);
					}
				}
			}
			Set<Integer> actoreIdSet = $actoresMap.keySet();
			fin_IdSet.removeAll(actoreIdSet);
			if (fin_IdSet != null && fin_IdSet.size() > 0) {
				for (Integer actores_id : fin_IdSet) {
					FinEarningsIncome earnIncome = finMap.get(actores_id);
					finEarningsIncomeService.deleteFinEarningsIncome(earnIncome);
				}
			}

			List<FinEarningsIncome> incomeList = finEarningsIncomeService.findAllFinEarningsIncome(date1, date2);
			if (incomeList != null && incomeList.size() > 0) {
				for (FinEarningsIncome income : incomeList) {
					BigDecimal totalIncome = new BigDecimal(0.00);// 税后总业绩
					List<PlatformsOnlineIncome> listIncome = platformsOnlineIncomeService.findPlatformsOnlineIncomeById(income.getActores_id(), date1, date2);
					if (listIncome != null && listIncome.size() > 0) {
						for (PlatformsOnlineIncome details : listIncome) {
							FinPlatformsIncome incomeDetails = finEarningsIncomeService.findPlatformsIncomeById(income.getId(), details.getPlatformsId());
							if (incomeDetails == null) {
								incomeDetails = new FinPlatformsIncome();
								incomeDetails.setFinEarningsIncome(income);
								incomeDetails.setBranchs(details.getBranchs());
								incomeDetails.setPlatName(details.getPlatforms());
								incomeDetails.setPlatId(Integer.valueOf(details.getPlatformsId()));
							}
							BigDecimal deductTax = new BigDecimal(0.00);// 分成扣税
							BigDecimal duductGift = new BigDecimal(0.00);// 礼物返还
							if (details.getDeductTax() != null)
								deductTax = details.getDeductTax();
							if (details.getDuductGift() != null)
								duductGift = details.getDuductGift();

							BigDecimal $income = (details.getBgIncome() == null ? new BigDecimal(0.00) : details.getBgIncome());
							$income = $income.subtract(deductTax).subtract(duductGift);// 扣税、扣返还
							incomeDetails.setIncome($income);
							totalIncome = totalIncome.add($income);

							finSalariesOnlineService.savePlatformsIncome(incomeDetails);
						}
					}
					List<PlatformsOfflineIncome> incomeOffList = platformsOfflineIncomeService.findPlatformsOfflineIncomeById(income.getActores_id(), date1, date2);
					if (incomeOffList != null && incomeOffList.size() > 0) {
						for (PlatformsOfflineIncome details : incomeOffList) {
							FinPlatformsIncome incomeDetails = finEarningsIncomeService.findPlatformsIncomeById(income.getId(), details.getPlatformsId());
							if (incomeDetails == null) {
								incomeDetails = new FinPlatformsIncome();
								incomeDetails.setFinEarningsIncome(income);
								incomeDetails.setBranchs(details.getBranchs());
								incomeDetails.setPlatName(details.getPlatforms());
								incomeDetails.setPlatId(Integer.valueOf(details.getPlatformsId()));
							}
							BigDecimal deductTax = new BigDecimal(0.00);// 分成扣税
							BigDecimal duductGift = new BigDecimal(0.00);// 礼物返还
							if (details.getDeductTax() != null)
								deductTax = details.getDeductTax();
							if (details.getDuductGift() != null)
								duductGift = details.getDuductGift();
							BigDecimal $income = (details.getIncome() == null ? new BigDecimal(0.00) : details.getIncome());
							$income = $income.subtract(deductTax).subtract(duductGift);// 扣税、扣返还
							incomeDetails.setIncome($income);
							totalIncome = totalIncome.add($income);
							finSalariesOnlineService.savePlatformsIncome(incomeDetails);
						}
					}
					Actores actores = $actoresMap.get(income.getActores_id());
					if (actores != null && actores.getGenre() == LineGenres.ONLINE) {
						income.setBasicSalary(new BigDecimal(actores.getMinimumGuarantee() != null ? actores.getMinimumGuarantee() : 0.00f));
					} else if (actores != null) {
						income.setBasicSalary(new BigDecimal(actores.getBasicSalary() != null ? actores.getBasicSalary() : 0.00f));
					}
					income.setPushMoney(new BigDecimal(actores.getPushMoney() != null ? actores.getPushMoney() : 0.00));
					BigDecimal pushMoneySalaries = new BigDecimal(0.00);
					if (income.getBasicSalary() != null && income.getPushMoney() != null) {
						pushMoneySalaries = income.getTotalIncome().multiply(income.getPushMoney()).divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP);
						if (income.getBasicSalary().doubleValue() > pushMoneySalaries.doubleValue())
							pushMoneySalaries = income.getBasicSalary();
					}
					income.setPushMoneySalary(pushMoneySalaries);

					// 税后总业绩
					income.setTotalIncome(totalIncome);
					finEarningsIncomeService.addEarnings(income);
				}
			}

		} catch (Exception e) {
			logger.error("同步艺人收益有误", e);
		}

	}

}
