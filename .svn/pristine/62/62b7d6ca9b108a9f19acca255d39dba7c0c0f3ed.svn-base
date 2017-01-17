package com.baofeng.work;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baofeng.oa.dao.PlatformsMonthsTotalOutlayDAO;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.entity.PlatformsMonthsTotalOutlay;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.utils.CustomDateUtils;
/**
 * 功能：自动计算月报总表
 * */
public class MakeTotalOutlayOnWorkListener implements BaseObserver {
	
	private CommService commService;

	private static final Logger logger = LoggerFactory.getLogger(MakeTotalOutlayOnWorkListener.class);

	public MakeTotalOutlayOnWorkListener(CommService commService) {
		this.commService = commService;
	}
	
	@Override
	public void update(Observable o, Object arg) {

		try {
			PlatformsMonthsOutlay outlay = (PlatformsMonthsOutlay) analysis(arg);
			PlatformsMonthsTotalOutlayDAO toutlayDao = (PlatformsMonthsTotalOutlayDAO) commService.getService().get(PlatformsMonthsTotalOutlayDAO.class.getName());
			IPlatformsMonthsOutlayService outlayService = (IPlatformsMonthsOutlayService) commService.getService().get(IPlatformsMonthsOutlayService.class.getName());
			Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(outlay.getCreateDT());
			Date date2 = CustomDateUtils.Handler.currentMonthEndDay(outlay.getCreateDT());
			List<PlatformsMonthsOutlay> platformsMonthsOutlayList = (List<PlatformsMonthsOutlay>) outlayService.readAllPlatformsMonthsOutlayByDate(date1, date2);
			Map<String, PlatformsMonthsTotalOutlay> map = new HashMap<String, PlatformsMonthsTotalOutlay>();
			if (platformsMonthsOutlayList != null) {
				for (PlatformsMonthsOutlay $outlay : platformsMonthsOutlayList) {
					PlatformsMonthsTotalOutlay totalOutlay = null;
					if (map.containsKey($outlay.getPlatId())) {
						totalOutlay = map.get($outlay.getPlatId());
					} else {
						totalOutlay = new PlatformsMonthsTotalOutlay();
					}
					if ($outlay.getCurrentMonths() == null)
						$outlay.setCurrentMonths(new BigDecimal(0));
					totalOutlay.setCurrentMonths($outlay.getCurrentMonths().add(totalOutlay.getCurrentMonths()));

					if ($outlay.getLastMonths() == null)
						$outlay.setLastMonths(new BigDecimal(0));
					totalOutlay.setLastMonths($outlay.getLastMonths().add(totalOutlay.getLastMonths()));

					if ($outlay.getLastOnlineIncome() == null)
						$outlay.setLastOnlineIncome(new BigDecimal(0));
					totalOutlay.setLastOnlineIncome($outlay.getLastOnlineIncome().add(totalOutlay.getLastOnlineIncome()));

					if ($outlay.getOnlineIncome() == null)
						$outlay.setOnlineIncome(new BigDecimal(0));
					totalOutlay.setOnlineIncome($outlay.getOnlineIncome().add(totalOutlay.getOnlineIncome()));

					if ($outlay.getLastOfflineIncome() == null)
						$outlay.setLastOfflineIncome(new BigDecimal(0));
					totalOutlay.setLastOfflineIncome($outlay.getLastOfflineIncome().add(totalOutlay.getLastOfflineIncome()));

					if ($outlay.getOfflineIncome() == null)
						$outlay.setOfflineIncome(new BigDecimal(0));
					totalOutlay.setOfflineIncome($outlay.getOfflineIncome().add(totalOutlay.getOfflineIncome()));

					if ($outlay.getTransferIncome() == null)
						$outlay.setTransferIncome(new BigDecimal(0));
					totalOutlay.setTransferIncome($outlay.getTransferIncome().add(totalOutlay.getTransferIncome()));

					if ($outlay.getDivident() == null)
						$outlay.setDivident(new BigDecimal(0));
					totalOutlay.setDivident($outlay.getDivident().add(totalOutlay.getDivident()));

					if ($outlay.getSelftop() == null)
						$outlay.setSelftop(new BigDecimal(0));
					totalOutlay.setSelftop($outlay.getSelftop().add(totalOutlay.getSelftop()));

					if ($outlay.getSociety() == null)
						$outlay.setSociety(new BigDecimal(0));
					totalOutlay.setSociety($outlay.getSociety().add(totalOutlay.getSociety()));

					if ($outlay.getAnchors() == null)
						$outlay.setAnchors(new BigDecimal(0));
					totalOutlay.setAnchors($outlay.getAnchors().add(totalOutlay.getAnchors()));

					if ($outlay.getOnlineOutlay() == null)
						$outlay.setOnlineOutlay(new BigDecimal(0));
					totalOutlay.setOnlineOutlay($outlay.getOnlineOutlay().add(totalOutlay.getOnlineOutlay()));

					if ($outlay.getOfflineOutlay() == null)
						$outlay.setOfflineOutlay(new BigDecimal(0));
					totalOutlay.setOfflineOutlay($outlay.getOfflineOutlay().add(totalOutlay.getOfflineOutlay()));

					if ($outlay.getActOnlineOutlay() == null)
						$outlay.setActOnlineOutlay(new BigDecimal(0));
					totalOutlay.setActOnlineOutlay($outlay.getActOnlineOutlay().add(totalOutlay.getActOnlineOutlay()));

					if ($outlay.getActOfflineOutlay() == null)
						$outlay.setActOfflineOutlay(new BigDecimal(0));
					totalOutlay.setActOfflineOutlay($outlay.getActOfflineOutlay().add(totalOutlay.getActOfflineOutlay()));

					if ($outlay.getMaintain() == null)
						$outlay.setMaintain(new BigDecimal(0));
					totalOutlay.setMaintain($outlay.getMaintain().add(totalOutlay.getMaintain()));

					if ($outlay.getActivity() == null)
						$outlay.setActivity(new BigDecimal(0));
					totalOutlay.setActivity($outlay.getActivity().add(totalOutlay.getActivity()));

					if ($outlay.getTotalIncome() == null)
						$outlay.setTotalIncome(new BigDecimal(0));
					totalOutlay.setTotalIncome($outlay.getTotalIncome().add(totalOutlay.getTotalIncome()));

					if ($outlay.getOutlay() == null)
						$outlay.setOutlay(new BigDecimal(0));
					totalOutlay.setOutlay($outlay.getOutlay().add(totalOutlay.getOutlay()));

					if ($outlay.getNetIncome() == null)
						$outlay.setNetIncome(new BigDecimal(0));
					totalOutlay.setNetIncome($outlay.getNetIncome().add(totalOutlay.getNetIncome()));

					if ($outlay.getLaborService() == null)
						$outlay.setLaborService(new BigDecimal(0));
					totalOutlay.setLaborService($outlay.getLaborService().add(totalOutlay.getLaborService()));

					totalOutlay.setPlatId($outlay.getPlatId());
					totalOutlay.setPlatforms($outlay.getPlatforms());
					totalOutlay.setRemarks($outlay.getRemarks());

					BigDecimal growthRate = new BigDecimal(0.00);
					BigDecimal onlineRate = new BigDecimal(0.00);
					BigDecimal offlineRate = new BigDecimal(0.00);

					BigDecimal lastMonths = $outlay.getLastMonths() == null ? new BigDecimal(0.00) : totalOutlay.getLastMonths().add($outlay.getLastMonths());
					BigDecimal currentMonths = $outlay.getCurrentMonths() == null ? new BigDecimal(0.00) : totalOutlay.getCurrentMonths().add($outlay.getCurrentMonths());
					if (lastMonths != null && lastMonths.doubleValue() > 0 && currentMonths != null && currentMonths.doubleValue() > 0) {
						growthRate = currentMonths.subtract(lastMonths).divide(lastMonths, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
					}
					if (lastMonths.doubleValue() == 0 && currentMonths.doubleValue() > 0)
						growthRate = new BigDecimal(100);
					totalOutlay.setGrowthRate(growthRate);

					BigDecimal lastOnlineIncome = $outlay.getLastOnlineIncome() == null ? new BigDecimal(0.00) : totalOutlay.getLastOnlineIncome().add(
							$outlay.getLastOnlineIncome());
					BigDecimal lineIncome = $outlay.getOnlineIncome() == null ? new BigDecimal(0.00) : totalOutlay.getOnlineIncome().add($outlay.getOnlineIncome());
					if (lastOnlineIncome != null && lastOnlineIncome.doubleValue() > 0 && lineIncome != null && lineIncome.doubleValue() > 0) {
						onlineRate = lineIncome.subtract(lastOnlineIncome).divide(lastOnlineIncome, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
					}
					if (lastOnlineIncome.doubleValue() == 0 && lineIncome.doubleValue() > 0)
						onlineRate = new BigDecimal(100);
					totalOutlay.setOnlineRate(onlineRate);

					BigDecimal lastOfflineIncome = $outlay.getLastOfflineIncome() == null ? new BigDecimal(0.00) : totalOutlay.getLastOfflineIncome().add(
							$outlay.getLastOfflineIncome());
					BigDecimal offlineIncome = $outlay.getOfflineIncome() == null ? new BigDecimal(0.00) : totalOutlay.getOfflineIncome().add($outlay.getOfflineIncome());
					if (lastOfflineIncome != null && lastOfflineIncome.doubleValue() > 0 && offlineIncome != null && offlineIncome.doubleValue() > 0)
						offlineRate = offlineIncome.subtract(lastOfflineIncome).divide(lastOfflineIncome, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
					if (lastOfflineIncome.doubleValue() == 0 && offlineIncome.doubleValue() > 0)
						offlineRate = new BigDecimal(100);
					totalOutlay.setOfflineRate(offlineRate);
					map.put($outlay.getPlatId(), totalOutlay);
				}

				for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
					String key = it.next();
					PlatformsMonthsTotalOutlay $ = map.get(key);
					PlatformsMonthsTotalOutlay $$ = toutlayDao.readPlatformsMonthsTotalOutlay(Integer.valueOf(key), date1, date2);
					if ($$ == null) {
						$$ = new PlatformsMonthsTotalOutlay();
						$$.setCreateDT(date1);
					}
					$$.setGrowthRate($.getGrowthRate());
					$$.setOnlineRate($.getOnlineRate());
					$$.setOfflineRate($.getOfflineRate());
					$$.setCurrentMonths($.getCurrentMonths());
					$$.setLastMonths($.getLastMonths());
					$$.setLastOnlineIncome($.getLastOnlineIncome());
					$$.setOnlineIncome($.getOnlineIncome());
					$$.setLastOfflineIncome($.getLastOfflineIncome());
					$$.setOfflineIncome($.getOfflineIncome());
					$$.setTransferIncome($.getTransferIncome());
					$$.setDivident($.getDivident());
					$$.setSelftop($.getSelftop());
					$$.setSociety($.getSociety());
					$$.setAnchors($.getAnchors());
					$$.setOnlineOutlay($.getOnlineOutlay());
					$$.setOfflineOutlay($.getOfflineOutlay());
					$$.setActOnlineOutlay($.getActOnlineOutlay());
					$$.setActOfflineOutlay($.getActOfflineOutlay());
					$$.setMaintain($.getMaintain());
					$$.setActivity($.getActivity());
					$$.setTotalIncome($.getTotalIncome());
					$$.setOutlay($.getOutlay());
					$$.setNetIncome($.getNetIncome());
					$$.setLaborService($.getLaborService());
					$$.setPlatId($.getPlatId());
					$$.setPlatforms($.getPlatforms());
					$$.setRemarks($.getRemarks());
					toutlayDao.savePlatformsMonthsOutlay($$);
				}
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled())
				logger.error("自动计算月报总表错误", e);
		}
	
	}

}
