package com.baofeng.work;

import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IPlatformsManagerService;

/**
 * 功能：删除助理同步删除平台关系
 * */
public class DelManagersOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(PlatformsManagerOnWorkListener.class);

	public DelManagersOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {
		Managers manager = (Managers) analysis(arg);
		IPlatformsManagerService platformsManagerService = (IPlatformsManagerService) commService.getService().get(IPlatformsManagerService.class.getName());
		try {
			platformsManagerService.deletePlatformsManagerBySession(manager.getId());
		} catch (Exception e) {
			logger.error("删除助理时同步删除平台关系错误", e);
		}

	}

}
