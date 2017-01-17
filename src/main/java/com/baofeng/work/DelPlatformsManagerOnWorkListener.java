package com.baofeng.work;

import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.PlatformsManager;
/**
 * 功能：平台管理删除同步删除报表
 * */
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IPlatformsManagerOutlayService;

public class DelPlatformsManagerOnWorkListener implements BaseObserver {
	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(DelPlatformsManagerOnWorkListener.class);

	public DelPlatformsManagerOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		PlatformsManager platformsManager = (PlatformsManager) analysis(arg);
		IPlatformsManagerOutlayService platformsManagerOutlayService = (IPlatformsManagerOutlayService) commService.getService().get(IPlatformsManagerOutlayService.class.getName());
		try {
			platformsManagerOutlayService.deletePlatformsManagers(platformsManager);
		} catch (Exception e) {
			logger.error("平台管理删除同步删除报表错误", e);
		}
	
	}

}
