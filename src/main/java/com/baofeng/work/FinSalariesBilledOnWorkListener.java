package com.baofeng.work;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.Audit;
import com.baofeng.oa.entity.BaseEnums.Grant;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.FinSalaries;
import com.baofeng.oa.entity.FinSalariesOnline;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IActoresService;
import com.baofeng.oa.service.IFinSalariesOnlineService;
import com.baofeng.oa.service.IFinSalariesService;
import com.baofeng.oa.service.IManagerService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsManagerService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.utils.CustomDateUtils;
/**
 * 功能：检查发工资状态
 * */
public class FinSalariesBilledOnWorkListener implements BaseObserver {
	
	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(FinSalariesBilledOnWorkListener.class);

	public FinSalariesBilledOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	@Override
	public void update(Observable o, Object arg) {

		FinSalaries salaries = (FinSalaries)analysis(arg);
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(salaries.getCreateDT());
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(salaries.getCreateDT());
		IFinSalariesService finSalariesService = (IFinSalariesService) commService.getService().get(IFinSalariesService.class.getName());
		IFinSalariesOnlineService finSalariesOnlineService = (IFinSalariesOnlineService) commService.getService().get(IFinSalariesOnlineService.class.getName());
		List<FinSalaries> salariesList = finSalariesService.findAllFinSalaries(date1, date2);
		if (salariesList != null && salariesList.size() > 0) {
			for (FinSalaries $salaries : salariesList) {
				if ($salaries != null && $salaries.getBilled() != Grant.PUBLISHED && $salaries.getRealitySalary().doubleValue() > 0) {
					if (this.isOfflineUnbilled(date1, date2, $salaries)) {
						$salaries.setBilled(Grant.UNBILLED);
					} else {
						$salaries.setBilled(Grant.UNKNOWN);
					}
					finSalariesService.saveFinSalaries($salaries);
				}
			}
		}

		List<FinSalariesOnline> onlineList = finSalariesOnlineService.findAllFinSalariesOnline(date1, date2);
		if (onlineList != null && onlineList.size() > 0) {
			for (FinSalariesOnline $online : onlineList) {
				if ($online != null && $online.getBilled() != Grant.PUBLISHED && $online.getRealitySalary().doubleValue() > 0) {
					if (this.isOnlineBilled(date1, date2, $online)) {
						$online.setBilled(Grant.UNBILLED);
					} else {
						$online.setBilled(Grant.UNKNOWN);
					}
					finSalariesOnlineService.saveFinSalariesOnline($online);
				}
			}
		}
	
	}

	private boolean isOnlineBilled(Date date1, Date date2, FinSalariesOnline $online) {
		boolean fIndex = Boolean.FALSE;
		IActoresService actoresService = (IActoresService) commService.getService().get(IActoresService.class.getName());
		IManagerService managerService = (IManagerService)  commService.getService().get(IManagerService.class.getName());
		IPlatformsManagerService platformsManagerService = (IPlatformsManagerService)  commService.getService().get(IPlatformsManagerService.class.getName());
		IPlatformsActoresService platformsActoresService = (IPlatformsActoresService)  commService.getService().get(IPlatformsActoresService.class.getName());
		IPlatformsMonthsOutlayService platformsMonthsOutlayService = (IPlatformsMonthsOutlayService)  commService.getService().get(IPlatformsMonthsOutlayService.class.getName());
		try {
			if ($online != null && $online.getEntityClass().equals(Actores.class.getName())) {
				Set<Integer> pHash = new HashSet<Integer>();
				Actores actores = actoresService.findActoresById($online.getEntityId());
				List<PlatformsActores> pactList = platformsActoresService.findAllPlatformsActoresByActId(actores);
				if (pactList != null && pactList.size() > 0) {
					for (PlatformsActores pactores : pactList) {
						pHash.add(pactores.getPlat().getId());
					}
				}
				boolean $ = true;
				for (Integer pId : pHash) {
					if (!platformsMonthsOutlayService.findMonthsOutlay(actores.getBranchs(), pId, date1, date2, Audit.PASS)
							&& !platformsMonthsOutlayService.findMonthsOutlay(actores.getBranchs(), pId, date1, date2, Audit.FINALPASS)) {
						$ = false;
						break;
					}
				}
				fIndex = $;
			} else if ($online.getEntityClass().equals(Managers.class.getName())) {
				Set<Integer> pHash = new HashSet<Integer>();
				Managers manager = managerService.findLoadManagers($online.getEntityId());
				List<?> pmanaList = (manager == null ? null : platformsManagerService.findPlatformsManagerBySession(manager.getId()));
				if (manager != null && pmanaList != null && pmanaList.size() > 0) {
					for (Object $manager : pmanaList) {
						PlatformsManager pmanager = (PlatformsManager) $manager;
						pHash.add(pmanager.getPlat().getId());
					}
					boolean $ = true;
					for (Integer pId : pHash) {
						if (!platformsMonthsOutlayService.findMonthsOutlay(manager.getBranchs(), pId, date1, date2, Audit.FINALPASS)) {
							$ = false;
							break;
						}
					}
					fIndex = $;
				} else {
					fIndex = true;
				}
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled())
				logger.error("检查线上人员发工状态错误", e);
			fIndex = false;
		}
		return fIndex;
	}

	private boolean isOfflineUnbilled(Date date1, Date date2, FinSalaries $salaries) {
		boolean fIndex = Boolean.FALSE;
		IPlatformsManagerService platformsManagerService = (IPlatformsManagerService) commService.getService().get(IPlatformsManagerService.class.getName());
		IManagerService managerService = (IManagerService) commService.getService().get(IManagerService.class.getName());
		IActoresService actoresService = (IActoresService) commService.getService().get(IActoresService.class.getName());
		IPlatformsActoresService platformsActoresService = (IPlatformsActoresService) commService.getService().get(IPlatformsActoresService.class.getName());
		IPlatformsMonthsOutlayService platformsMonthsOutlayService = (IPlatformsMonthsOutlayService) commService.getService().get(IPlatformsMonthsOutlayService.class.getName());
		try {
			if ($salaries != null && $salaries.getIsActores()) {
				Set<Integer> pHash = new HashSet<Integer>();
				Employee employee = $salaries.getEmployee();
				Actores actores = actoresService.findActoresBySession(employee.getId());
				List<PlatformsActores> pactList = platformsActoresService.findAllPlatformsActoresByActId(actores);
				if (pactList != null && pactList.size() > 0) {
					for (PlatformsActores pactores : pactList) {
						pHash.add(pactores.getPlat().getId());
					}
				}
				boolean $ = true;
				for (Integer pId : pHash) {
					if (!platformsMonthsOutlayService.findMonthsOutlay(actores.getBranchs(), pId, date1, date2, Audit.PASS)
							&& !platformsMonthsOutlayService.findMonthsOutlay(actores.getBranchs(), pId, date1, date2, Audit.FINALPASS)) {
						$ = false;
						break;
					}
				}
				fIndex = $;
			} else if ($salaries != null && !$salaries.getIsActores()) {
				Employee employee = $salaries.getEmployee();
				Set<Integer> pHash = new HashSet<Integer>();
				Managers manager = managerService.findManagerByEmpIdSession(employee.getId());
				List<?> pmanaList = (manager == null ? null : platformsManagerService.findPlatformsManagerBySession(manager.getId()));
				if (manager != null && pmanaList != null && pmanaList.size() > 0) {
					for (Object $manager : pmanaList) {
						PlatformsManager pmanager = (PlatformsManager) $manager;
						pHash.add(pmanager.getPlat().getId());
					}
					boolean $ = true;
					for (Integer pId : pHash) {
						if (!platformsMonthsOutlayService.findMonthsOutlay(manager.getBranchs(), pId, date1, date2, Audit.FINALPASS)) {
							$ = false;
							break;
						}
					}
					fIndex = $;
				} else {
					fIndex = true;
				}
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled())
				logger.error("检查线下发工状态错误", e);
			fIndex = false;
		}
		return fIndex;
	}
}
