package com.baofeng.work;

import java.util.List;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.PlatformsManagerOutlay;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IEmpAttendanceService;
import com.baofeng.oa.service.IPlatformsManagerOutlayService;
import com.baofeng.utils.Constants;
/**
 * 功能：自动计算线下管理报表数据
 * */
public class MakeManagerOnWorkListener implements BaseObserver {
	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(MakeManagerOnWorkListener.class);

	public MakeManagerOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	@Override
	public void update(Observable o, Object arg) {
		try {
			PlatformsMonthsOutlay months = (PlatformsMonthsOutlay)analysis(arg);
			IEmpAttendanceService empAttendanceService = (IEmpAttendanceService) commService.getService().get(IEmpAttendanceService.class.getName());
			IPlatformsManagerOutlayService platformsManagerOutlayService = (IPlatformsManagerOutlayService) commService.getService().get(IPlatformsManagerOutlayService.class.getName());
			List<PlatformsManagerOutlay> managerList = platformsManagerOutlayService.findPlatformsManagerOutlayList(months.getId(), months.getCreateDT());
			if (managerList != null && managerList.size() > 0) {
				for (PlatformsManagerOutlay manager : managerList) {
					if (manager != null && manager.getGenre() == LineGenres.OFFLINE) {
						String date = Constants.convert(months.getCreateDT(), Constants.format7);
						Float attendance = empAttendanceService.findAttendanceByNumberAndDate(manager.getNumber(), manager.getBranchs(), date);
						manager.setAttendance(attendance != null ? attendance : 0.0f);
					}
					manager.make();
					platformsManagerOutlayService.savePlatformsManagerOutlay(manager);
				}
			}
		} catch (Exception e) {
			logger.error("自动计算线下管理报表数据失败");
		}
	
	
	}

}
