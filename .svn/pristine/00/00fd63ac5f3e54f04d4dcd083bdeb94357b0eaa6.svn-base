package com.baofeng.work;

import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.oa.entity.PlatformsManagerOutlay;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IPlatformsManagerOutlayService;
import com.baofeng.oa.service.IPlatformsManagerService;

public class MidManagersOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(MidManagersOnWorkListener.class);

	public MidManagersOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	@Override
	public void update(Observable a, Object arg) {

		Managers manager = (Managers) analysis(arg);
		IPlatformsManagerService platformsManagerService = (IPlatformsManagerService) commService.getService().get(IPlatformsManagerService.class.getName());
		IPlatformsManagerOutlayService platformsManagerOutlayService = (IPlatformsManagerOutlayService) commService.getService().get(IPlatformsManagerOutlayService.class.getName());
		try {
			List<?> managerList = platformsManagerService.findPlatformsManagerBySession(manager.getId());
			if (managerList != null && managerList.size() > 0) {
				for (Object o : managerList) {
					PlatformsManager manger = (PlatformsManager) o;
					manger.setManager(manager);
					platformsManagerService.savePlatformsManager(manger);
					List<?> outlayList = platformsManagerOutlayService.findPlatformsManagerOutlayList(manger.getId());
					if (outlayList != null && outlayList.size() > 0) {
						for (Object $o : outlayList) {
							PlatformsManagerOutlay $outlay = (PlatformsManagerOutlay) $o;
							if ($outlay != null) {
								$outlay.setAliasname(manager.getAliasname());
								$outlay.setBasicSalary(manager.getBasicSalary());
								$outlay.setName(manager.getRealname());
								$outlay.setGenre(manager.getGenre());
								if (manager.getLabour() == ActoresLabour.SYSTEM)
									if (manager.getGenrer() == Genres.SHIYONG)
										manager.setBasicSalary(manager.getProbationSalary());
								$outlay.setMealAllowance(manager.getFoodSubsidy());
								$outlay.setPositions(manager.getPositions());
								platformsManagerOutlayService.savePlatformsManagerOutlay($outlay);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("修改管理助理资料同步到报表", e);
		}
	
		
	}

}
