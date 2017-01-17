package com.baofeng.work;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Observable;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BaseEnums.PlatformGenre;
import com.baofeng.oa.entity.PlatformsActores;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.entity.PlatformsOfflineIncome;
import com.baofeng.oa.entity.PlatformsOnlineIncome;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;

public class PlatformsActoresOnWorkListener implements BaseObserver {

	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(PlatformsActoresOnWorkListener.class);

	public PlatformsActoresOnWorkListener(CommService commService) {
		this.commService = commService;
	}

	@Override
	public void update(Observable o, Object arg) {

		PlatformsActores pact = (PlatformsActores) analysis(arg);
		String date = Constants.convert(new Date(), Constants.format7);
		Date date1 = DateUtils.addMonths(Constants.convert(date, Constants.format7), -1);
		Date date2 = DateUtils.addMonths(Constants.convert(date + "-" + CustomDateUtils.Handler.daysInMonth(date) + " 23:59:59", Constants.format2), -1);
		IPlatformsActoresService platformsActoresService = (IPlatformsActoresService) commService.getService().get(IPlatformsActoresService.class.getName());
		IPlatformsMonthsOutlayService platformsMonthsOutlayService = (IPlatformsMonthsOutlayService) commService.getService().get(IPlatformsMonthsOutlayService.class.getName());
		try {
			if (pact.getActores().getGenre() == LineGenres.ONLINE) {
				PlatformsOnlineIncome online = platformsActoresService.findPlatformsActoresByPactToOnline(pact.getId());
				if (online != null) {
					online.setPushMoney(new BigDecimal(pact.getPushMoney()));
					online.setAliasname(pact.getActores().getAliasname());
					online.setName(pact.getActores().getRealname());
					online.setMinimumGuarantee(pact.getCostArtists());
					if (pact.getCostArtists() == null) {
						online.setMinimumGuarantee(new BigDecimal(pact.getActores().getMinimumGuarantee() == null ? 0 : pact.getActores().getMinimumGuarantee()));
					}
					if (pact.getChannel() != null) {
						online.setChannel(pact.getChannel().getChannels());
						online.setChannelId(pact.getChannel().getId().toString());
					} else {
						if (pact.getPlat().getFormGenre() == PlatformGenre.ZHIBOJIAN)
							online.setChannel(pact.getNumber());
					}
				} else {
					online = new PlatformsOnlineIncome();
					online.setBranchs(pact.getBranchs());
					online.setAliasname(pact.getActores().getAliasname() == null ? "" : pact.getActores().getAliasname());
					online.setName(pact.getActores().getRealname() == null ? "" : pact.getActores().getRealname());
					online.setPushMoney(new BigDecimal(pact.getPushMoney()));
					online.setMinimumGuarantee(pact.getCostArtists());
					if (pact.getCostArtists() == null) {
						online.setMinimumGuarantee(new BigDecimal(pact.getActores().getMinimumGuarantee() == null ? 0 : pact.getActores().getMinimumGuarantee()));
					}
					if (pact.getChannel() != null) {
						online.setChannel(pact.getChannel().getChannels());
						online.setChannelId(pact.getChannel().getId().toString());
					} else {
						if (pact.getPlat().getFormGenre() == PlatformGenre.ZHIBOJIAN)
							online.setChannel(pact.getNumber());
					}
					online.setPlatforms(pact.getPlat().getPlatName());
					online.setPlatformsId(pact.getPlat().getId().toString());
					online.setPactId(pact.getId().toString());
					PlatformsMonthsOutlay monthsOutlay = platformsMonthsOutlayService.findPlatformsMonthsOutlay(pact.getPlat().getId().toString(), pact.getBranchs(), date1, date2);
					if (monthsOutlay != null)
						online.setMonths(monthsOutlay);
					online.setCreateDT(date1);
				}
				platformsActoresService.savePlatformsOnlineIncome(online);
			} else if (pact.getActores().getGenre() == LineGenres.OFFLINE) {
				PlatformsOfflineIncome offline = platformsActoresService.findPlatformsActoresByPactToOffline(pact.getId());
				if (offline != null) {
					offline.setPushMoney(new BigDecimal(pact.getPushMoney()));
					offline.setBasicSalary(pact.getCostArtists());
					offline.setAliasname(pact.getActores().getAliasname());
					offline.setName(pact.getActores().getRealname());
					if (pact.getCostArtists() == null) {
						offline.setBasicSalary(new BigDecimal(pact.getActores().getBasicSalary() == null ? 0 : pact.getActores().getBasicSalary()));
					}
					if (pact.getChannel() != null) {
						offline.setChannel(pact.getChannel().getChannels());
						offline.setChannelId(pact.getChannel().getId().toString());
					} else {
						if (pact.getPlat().getFormGenre() == PlatformGenre.ZHIBOJIAN)
							offline.setChannel(pact.getNumber());
					}
				} else {
					offline = new PlatformsOfflineIncome();
					offline.setBranchs(pact.getBranchs());
					offline.setNumber(pact.getActores().getNumber());
					offline.setAliasname(pact.getActores().getAliasname() == null ? "" : pact.getActores().getAliasname());
					offline.setName(pact.getActores().getRealname() == null ? "" : pact.getActores().getRealname());
					offline.setPushMoney(new BigDecimal(pact.getPushMoney().floatValue()));
					offline.setBasicSalary(pact.getCostArtists());
					if (pact.getCostArtists() == null) {
						offline.setBasicSalary(new BigDecimal(pact.getActores().getBasicSalary() == null ? 0 : pact.getActores().getBasicSalary()));
					}
					if (pact.getChannel() != null) {
						offline.setChannel(pact.getChannel().getChannels());
						offline.setChannelId(pact.getChannel().getId().toString());
					} else {
						if (pact.getPlat().getFormGenre() == PlatformGenre.ZHIBOJIAN)
							offline.setChannel(pact.getNumber());
					}
					offline.setPlatforms(pact.getPlat().getPlatName());
					offline.setPlatformsId(pact.getPlat().getId().toString());
					offline.setPactId(pact.getId().toString());
					PlatformsMonthsOutlay monthsOutlay = platformsMonthsOutlayService.findPlatformsMonthsOutlay(pact.getPlat().getId().toString(), pact.getBranchs(), date1, date2);
					if (monthsOutlay != null)
						offline.setMonths(monthsOutlay);
					offline.setCreateDT(date1);
				}
				platformsActoresService.savePlatformsOfflineIncome(offline);
			}
		} catch (Exception e) {
			logger.error("平台艺人数据同步", e);
		}

	}

}
