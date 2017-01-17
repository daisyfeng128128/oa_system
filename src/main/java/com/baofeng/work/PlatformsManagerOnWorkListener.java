package com.baofeng.work;

import java.util.Date;
import java.util.Observable;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.oa.entity.PlatformsManagerOutlay;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IPlatformsManagerOutlayService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;

public class PlatformsManagerOnWorkListener implements BaseObserver {
	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(PlatformsManagerOnWorkListener.class);

	public PlatformsManagerOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	@Override
	public void update(Observable o, Object arg) {
		PlatformsManager manager = (PlatformsManager) analysis(arg);
		String date = Constants.convert(new Date(), Constants.format7);
		Date date1 = DateUtils.addMonths(Constants.convert(date, Constants.format7), -1);
		Date date2 = DateUtils.addMonths(Constants.convert(date + "-" + CustomDateUtils.Handler.daysInMonth(date) + " 23:59:59", Constants.format2), -1);
		IPlatformsManagerOutlayService platformsManagerOutlayService = (IPlatformsManagerOutlayService) commService.getService().get(IPlatformsManagerOutlayService.class.getName());
		IPlatformsMonthsOutlayService platformsMonthsOutlayService = (IPlatformsMonthsOutlayService) commService.getService().get(IPlatformsMonthsOutlayService.class.getName());
		try {
			PlatformsManagerOutlay outlay = platformsManagerOutlayService.findPlatformsManagerOutlay(manager.getId());
			if (outlay != null) {
				outlay.setBasicSalary(new Float(manager.getManager().getBasicSalary()));
				if (manager.getManager().getLabour() == ActoresLabour.SYSTEM)
					if (manager.getManager().getGenrer() == Genres.SHIYONG)
						outlay.setBasicSalary(new Float(manager.getManager().getProbationSalary()));
				outlay.setMealAllowance(manager.getManager().getFoodSubsidy());
			} else {
				outlay = new PlatformsManagerOutlay();
				outlay.setBranchs(manager.getBranchs());
				outlay.setAliasname(manager.getManager().getAliasname());
				outlay.setBasicSalary(new Float(manager.getManager().getBasicSalary()));
				if (manager.getManager().getLabour() == ActoresLabour.SYSTEM)
					if (manager.getManager().getGenrer() == Genres.SHIYONG)
						outlay.setBasicSalary(new Float(manager.getManager().getProbationSalary()));
				if (manager.getChannel() != null) {
					outlay.setChannel(manager.getChannel().getChannels());
					outlay.setChannelId(manager.getChannel().getId().toString());
				}
				outlay.setPositions(manager.getManager().getPositions());
				outlay.setMealAllowance(manager.getManager().getFoodSubsidy());
				outlay.setGenre(manager.getManager().getGenre());
				outlay.setManagerId(manager.getId().toString());
				outlay.setName(manager.getManager().getRealname());
				outlay.setNumber(manager.getManager().getNumber());
				if (manager.getPlat() != null) {
					outlay.setPlatforms(manager.getPlat().getPlatName());
					outlay.setPlatformsId(manager.getPlat().getId().toString());
					PlatformsMonthsOutlay monthsOutlay = platformsMonthsOutlayService.findPlatformsMonthsOutlay(manager.getPlat().getId().toString(), manager.getBranchs(), date1,
							date2);
					if (monthsOutlay != null)
						outlay.setMonths(monthsOutlay);
				}
				outlay.setPositions(manager.getManager().getPositions());
				outlay.setCreateDT(date1);
			}
			platformsManagerOutlayService.savePlatformsManagerOutlay(outlay);
		} catch (Exception e) {
			logger.error("频道助理数据同步", e);
		}
	}

}
