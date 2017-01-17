package com.baofeng.work;

import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IPlatformsOfflineIncomeService;
import com.baofeng.oa.service.IPlatformsOnlineIncomeService;

public class DelPlatformsActoresOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(DelPlatformsActoresOnWorkListener.class);

	public DelPlatformsActoresOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	@Override
	public void update(Observable o, Object arg) {
		PlatformsActores pactores = (PlatformsActores) analysis(arg);
		IPlatformsOnlineIncomeService onlineIncomeService = (IPlatformsOnlineIncomeService) commService.getService().get(IPlatformsOnlineIncomeService.class.getName());
		IPlatformsOfflineIncomeService offlineIncomeService = (IPlatformsOfflineIncomeService)  commService.getService().get(IPlatformsOfflineIncomeService.class.getName());
		try {
			onlineIncomeService.deletePlatformsActores(pactores);
			offlineIncomeService.deletePlatformsActores(pactores);
		} catch (Exception e) {
			logger.error("停播同步删除报表错误", e);
		}
	
		
	}

}
