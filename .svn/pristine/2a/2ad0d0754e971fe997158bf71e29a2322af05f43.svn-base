package com.baofeng.work;

import java.math.BigDecimal;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsOfflineIncome;
import com.baofeng.oa.entity.PlatformsOnlineIncome;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsOfflineIncomeService;
import com.baofeng.oa.service.IPlatformsOnlineIncomeService;

/**
 * 功能：同步修改艺人资料
 * */
public class MidActoresOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(MidActoresOnWorkListener.class);

	public MidActoresOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {
		Actores actores = (Actores) analysis(arg);
		WorkManagers workManagers = (WorkManagers) this.commService.getService().get(WorkManagers.class.getName());
		IEmployeeService employeeService = (IEmployeeService) commService.getService().get(IEmployeeService.class.getName());
		IPlatformsActoresService platformsActoresService = (IPlatformsActoresService) commService.getService().get(IPlatformsActoresService.class.getName());
		IPlatformsOnlineIncomeService onlineIncomeService = (IPlatformsOnlineIncomeService) commService.getService().get(IPlatformsOnlineIncomeService.class.getName());
		IPlatformsOfflineIncomeService offlineIncomeService = (IPlatformsOfflineIncomeService) commService.getService().get(IPlatformsOfflineIncomeService.class.getName());
		try {
			if (actores.getLabour() == ActoresLabour.SYSTEM && actores.getGenre() == LineGenres.OFFLINE) {
				Employee employee = actores.getEmployee();
				if (employee != null) {
					Float $salary = employee.getSalary() == null ? 0.00f : employee.getSalary();
					Float $ap = actores.getProbationSalary() == null ? 0.00f : actores.getProbationSalary();
					Float $ab = actores.getBasicSalary() == null ? 0.00f : actores.getBasicSalary();
					Float $probationSalary = $ap.floatValue() - $salary.floatValue();
					Float $otherSubsidy = $ab.floatValue() - $salary.floatValue();
					employee.setProbationSalary($probationSalary);
					employee.setOtherSubsidy($otherSubsidy);
					employeeService.saveEmployee(employee);
				}
			}
			List<PlatformsActores> actList = platformsActoresService.findPlatformsActoresList(actores.getId());
			if (actList != null && actList.size() > 0) {
				for (PlatformsActores pact : actList) {
					pact.setPushMoney(actores.getPushMoney());
					if (pact.getMainPlatform() != null && pact.getMainPlatform().intValue() == Integer.valueOf(1).intValue()) {
						if (actores.getGenre() == LineGenres.OFFLINE) {
							if (actores.getGenrer() == Genres.ZHENGSHI) {
								pact.setCostArtists(new BigDecimal(actores.getBasicSalary()));
							} else {
								pact.setCostArtists(new BigDecimal(actores.getProbationSalary()));
							}
						} else {
							pact.setCostArtists(new BigDecimal(actores.getMinimumGuarantee()));
						}
					} else {
						pact.setCostArtists(new BigDecimal(0.00));
					}
					if (platformsActoresService.savePlatformsActoresBySession(pact)) {
					}
				}
				if (actores.getGenre() == LineGenres.ONLINE) {
					for (PlatformsActores pact : actList) {
						PlatformsOnlineIncome $online = onlineIncomeService.findPlatformsOnlineIncomeBySession(pact.getId());
						if ($online != null) {
							$online.setName(actores.getRealname());
							$online.setAliasname(actores.getAliasname());
							$online.setMinimumGuarantee(pact.getCostArtists());
							$online.setPushMoney(new BigDecimal(actores.getPushMoney()));
							if (onlineIncomeService.savePlatformsOnlineOutlayBySession($online)) {
							}
						}
					}
				} else {
					for (PlatformsActores pact : actList) {
						PlatformsOfflineIncome $offline = offlineIncomeService.findPlatformsOfflineIncomeBySession(pact.getId());
						if ($offline != null) {
							$offline.setName(actores.getRealname());
							$offline.setAliasname(actores.getAliasname());
							$offline.setBasicSalary(pact.getCostArtists());
							$offline.setPushMoney(new BigDecimal(actores.getPushMoney()));
							if (offlineIncomeService.savePlatformsOfflineIncomeBySession($offline)) {
							}
						}
					}
				}
				workManagers.onEvents(WorkNames.MAKEALL, actores);
			}
		} catch (Exception e) {
			logger.error("修改艺人资料同步到报表", e);
		}
	}
}
