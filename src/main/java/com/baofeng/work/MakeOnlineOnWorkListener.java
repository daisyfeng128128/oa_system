package com.baofeng.work;

import java.math.BigDecimal;
import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.entity.PlatformsOnlineIncome;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsOnlineIncomeService;
import com.baofeng.oa.service.IPlatformsService;
/**
 * 功能：计算线上艺人报表数据
 * */
public class MakeOnlineOnWorkListener implements BaseObserver {
	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(MakeOnlineOnWorkListener.class);

	public MakeOnlineOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	@Override
	public void update(Observable o, Object arg) {
		try {
			PlatformsMonthsOutlay outlay = (PlatformsMonthsOutlay) analysis(arg);
			IPlatformsService platformsService = (IPlatformsService) commService.getService().get(IPlatformsService.class.getName());
			IPlatformsActoresService platformsActoresService = (IPlatformsActoresService) commService.getService().get(IPlatformsActoresService.class.getName());
			IPlatformsOnlineIncomeService onlineIncomeService = (IPlatformsOnlineIncomeService) commService.getService().get(IPlatformsOnlineIncomeService.class.getName());
			List<PlatformsOnlineIncome> onlineList = onlineIncomeService.findPlatformsOnlineList(outlay.getId(), outlay.getCreateDT());
			if (onlineList != null && onlineList.size() > 0) {
				for (PlatformsOnlineIncome income : onlineList) {
					Platforms platforms = platformsService.findPlatforms(Integer.valueOf(income.getPlatformsId()));
					if (income.getPactId() != null && income.getPactId().trim().length() > 0) {
						Integer pactId = Integer.valueOf(income.getPactId().trim());
						PlatformsActores pactores = platformsActoresService.findPlatformsActoresById(pactId);
						if (pactores != null) {
							income.setPushMoney(new BigDecimal(pactores.getActores().getPushMoney()));
							income.setMinimumGuarantee(pactores.getCostArtists() == null ? new BigDecimal(0) : pactores.getCostArtists());
						}
					}
					income.make(platforms.getTax() == null ? new BigDecimal(0.00) : platforms.getTax());
					onlineIncomeService.savePlatformsOnlineOutlay(income);
				}
			}
		
		} catch (Exception e) {
			logger.error("计算线上艺人报表数据错误");
		}
	}

}
