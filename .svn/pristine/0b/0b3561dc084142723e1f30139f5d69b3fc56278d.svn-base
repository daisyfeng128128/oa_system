package com.baofeng.work;

import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IPlatformsManagerService;

public class LeavelManagersOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(LeavelManagersOnWorkListener.class);

	public LeavelManagersOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	@Override
	public void update(Observable o, Object arg) {
		Managers manager = (Managers) analysis(arg);
		IPlatformsManagerService platformsManagerService = (IPlatformsManagerService) commService.getService().get(IPlatformsManagerService.class.getName());
		try {
			if (manager.getGenrer() == Genres.LIZHI) {
				if (platformsManagerService.deletePlatformsManagerBySession(manager.getId())) {
					logger.info(manager.getAliasname() + "离职同步成功!");
				}
			}
		} catch (Exception e) {
			logger.error("同步管理助理离职错误", e);
		}
	
	}

}
