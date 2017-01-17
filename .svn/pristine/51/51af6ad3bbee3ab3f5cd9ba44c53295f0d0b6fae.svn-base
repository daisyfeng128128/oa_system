package com.baofeng.work;

import java.math.BigDecimal;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.entity.PlatformsOfflineIncome;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsOfflineIncomeService;
import com.baofeng.oa.service.IPlatformsService;

/**
 * 功能：计算线下艺人报表数据
 * */
public class MakeOfflineOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(MakeOfflineOnWorkListener.class);

	public MakeOfflineOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {
		try {
			PlatformsMonthsOutlay outlay = (PlatformsMonthsOutlay) analysis(arg);
			IPlatformsService platformsService = (IPlatformsService) commService.getService().get(IPlatformsService.class.getName());
			IPlatformsActoresService platformsActoresService = (IPlatformsActoresService) commService.getService().get(IPlatformsActoresService.class.getName());
			IPlatformsOfflineIncomeService offlineIncomeService = (IPlatformsOfflineIncomeService) commService.getService().get(IPlatformsOfflineIncomeService.class.getName());
			List<PlatformsOfflineIncome> offlineList = offlineIncomeService.findPlatformsOfflineList(outlay.getId(), outlay.getCreateDT());
			if (offlineList != null && offlineList.size() > 0) {
				for (PlatformsOfflineIncome income : offlineList) {
					Platforms platforms = platformsService.findPlatforms(Integer.parseInt(income.getPlatformsId()));
					if (income.getPactId() != null && income.getPactId().trim().length() > 0) {
						Integer pactId = Integer.valueOf(income.getPactId().trim());
						PlatformsActores pactores = platformsActoresService.findPlatformsActoresById(pactId);
						if (pactores != null) {
							income.setPushMoney(new BigDecimal(pactores.getActores().getPushMoney()));
							income.setBasicSalary(pactores.getCostArtists() == null ? new BigDecimal(0) : pactores.getCostArtists());
						}
					}
					income.make(platforms.getTax() == null ? new BigDecimal(0.00) : platforms.getTax());
					offlineIncomeService.savePlatformsOfflineOutlay(income);
				}
			}
		} catch (Exception e) {
			logger.error("计算线下艺人报表数据错误");
		}
	}
}
