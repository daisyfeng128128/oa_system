package com.baofeng.work;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.Grant;
import com.baofeng.oa.entity.FinSalariesOnline;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.oa.entity.PlatformsManagerOutlay;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IFinSalariesOnlineService;
import com.baofeng.oa.service.IPlatformsManagerOutlayService;
import com.baofeng.oa.service.IPlatformsManagerService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;
/**
 * 功能：线上管理工资计算
 * */
public class FinSalariesManagerOnlineWorkListener implements BaseObserver {
	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(FinSalariesManagerOnlineWorkListener.class);

	public FinSalariesManagerOnlineWorkListener(CommService commService) {
		this.commService = commService;
	}
	@Override
	public void update(Observable o, Object arg) {
		FinSalariesOnline data = (FinSalariesOnline) analysis(arg);
		Date $date1 = CustomDateUtils.Handler.currentMonthFirstDay(data.getCreateDT());
		Date $date2 = CustomDateUtils.Handler.currentMonthEndDay(data.getCreateDT());

		IFinSalariesOnlineService finSalariesOnlineService = (IFinSalariesOnlineService) commService.getService().get(IFinSalariesOnlineService.class.getName());
		IPlatformsManagerService platformsManagerService = (IPlatformsManagerService) commService.getService().get(IPlatformsManagerService.class.getName());
		IPlatformsManagerOutlayService platformsManagerOutlayService = (IPlatformsManagerOutlayService) commService.getService().get(IPlatformsManagerOutlayService.class.getName());

		try {
			List<PlatformsManager> managerList = platformsManagerService.findAllPlatformsManager();
			if (managerList != null && managerList.size() > 0) {
				for (PlatformsManager pmanager : managerList) {
					Managers manager = pmanager.getManager();
					if (manager.getEntryTime() != null && !manager.getEntryTime().before($date2))
						continue;
					FinSalariesOnline salaries = null;
					if (manager.getLabour() != ActoresLabour.SYSTEM) {
						salaries = finSalariesOnlineService.findFinSalariesOnlineByActores(manager.getId(), Managers.class.getName(), $date1, $date2);
						if (salaries == null) {
							salaries = new FinSalariesOnline();
							salaries.setName(manager.getRealname());
							salaries.setAliasname(manager.getAliasname());
							salaries.setEntityId(manager.getId());
							salaries.setEntityClass(Managers.class.getName());
							salaries.setCreateDT($date1);
							salaries.setBranchs(manager.getBranchs());
							finSalariesOnlineService.saveFinSalariesOnline(salaries);
						}
						if (salaries != null && salaries.getBilled() != Grant.PUBLISHED) {
							// 平台汇总
							BigDecimal attendance = new BigDecimal(0.00);// 出勤
							BigDecimal pushMoney = new BigDecimal(0.00);// 提成总数
							BigDecimal platSubsidy = new BigDecimal(0.00);// 平台补贴
							BigDecimal platDeduct = new BigDecimal(0.00);// 平台扣除
							List<PlatformsManagerOutlay> outlayList = platformsManagerOutlayService.findPlatformsManagerOutlayListNew(manager.getId(), $date1, $date2);
							if (outlayList != null && outlayList.size() > 0) {
								for (PlatformsManagerOutlay outlay : outlayList) {
									if (outlay.getPushMoney() != null)
										pushMoney = pushMoney.add(new BigDecimal(outlay.getPushMoney()));
									if (outlay.getOtherAllowance() != null)
										platSubsidy = platSubsidy.add(new BigDecimal(outlay.getOtherAllowance()));
									if (outlay.getDeduct() != null)
										platDeduct = platDeduct.add(new BigDecimal(outlay.getDeduct()));
									if (outlay.getAttendance() != null && outlay.getAttendance().floatValue() > attendance.floatValue())
										attendance = new BigDecimal(outlay.getAttendance());
								}
							}

							// 底薪
							BigDecimal basicSalary = new BigDecimal(manager.getBasicSalary() == null ? 0.00 : manager.getBasicSalary());
							if (attendance.doubleValue() <= 0)
								basicSalary = new BigDecimal(0.00);
							if (attendance.doubleValue() > 0 && attendance.doubleValue() < Constants.DEFAULT_ATTENDANCE) {
								basicSalary = basicSalary.divide(new BigDecimal(30), 4, BigDecimal.ROUND_HALF_UP).multiply(attendance);
							}
							salaries.setBasicSalary(basicSalary);

							// 提成工资
							salaries.setPushMoneySalary(pushMoney);
							// 平台扣除
							salaries.setPlatDeduct(platDeduct);
							// 平台补贴
							salaries.setPlatSubsidy(platSubsidy);
							// 考勤
							salaries.setAttendance(attendance);
							// 餐补
							BigDecimal foodSubsidy = new BigDecimal(manager.getFoodSubsidy() == null ? 0.00 : manager.getFoodSubsidy());
							foodSubsidy = foodSubsidy.multiply(attendance);
							if (attendance.doubleValue() >= Constants.DEFAULT_ATTENDANCE) {
								foodSubsidy = foodSubsidy.multiply(new BigDecimal(Constants.DEFAULT_ATTENDANCE));
							}
							salaries.setFoodSubsidy(foodSubsidy);

							// 实发工资
							BigDecimal realitySalary = new BigDecimal(0.00);
							realitySalary = realitySalary.add(salaries.getPushMoneySalary()).add(salaries.getPlatSubsidy()).add(salaries.getFoodSubsidy())
									.add(salaries.getOtherSubsidy()).add(salaries.getBasicSalary()).subtract(salaries.getOtherDeduct());
							realitySalary = realitySalary.doubleValue() < 0 ? new BigDecimal(0.00) : realitySalary;
							salaries.setRealitySalary(realitySalary.setScale(2, BigDecimal.ROUND_HALF_UP));

							// 实际支出
							BigDecimal realExpenditure = new BigDecimal(0.00);
							realExpenditure = realExpenditure.add(salaries.getRealitySalary());
							salaries.setRealExpenditure(realExpenditure);
							finSalariesOnlineService.saveFinSalariesOnline(salaries);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("线上管理工资计算错误", e);
		}
	
	}

}
