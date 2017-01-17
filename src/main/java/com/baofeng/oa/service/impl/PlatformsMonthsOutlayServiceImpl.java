package com.baofeng.oa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.oa.bean.PlatformsMonthsOutlayBean;
import com.baofeng.oa.dao.PlatformsDAO;
import com.baofeng.oa.dao.PlatformsMonthsOutlayDAO;
import com.baofeng.oa.entity.BaseEnums.Audit;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsAudits;
import com.baofeng.oa.entity.PlatformsMonths;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.oa.service.IPlatformsMonthsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.SearchFilter;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

@Service("platformsMonthsOutlayService")
public class PlatformsMonthsOutlayServiceImpl implements IPlatformsMonthsOutlayService {
	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private IPlatformsMonthsService platformsMonthsService;
	@Autowired
	private PlatformsDAO platformsDAO;
	@Autowired
	private PlatformsMonthsOutlayDAO platformsMonthsOutlayDAO;

	@Override
	public void addPlatformsMonthsOutlay(List<PlatformsMonthsOutlay> listOutlay) {
		if (listOutlay != null && listOutlay.size() > 0) {
			for (PlatformsMonthsOutlay post : listOutlay) {
				this.platformsMonthsOutlayDAO.addPlatformsMonthsOutlay(post);
			}
		}
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<String> readAllPlatformsMonthsOutlayDate() {
		List<PlatformsMonthsOutlay> list = this.platformsMonthsOutlayDAO.readAllPlatformsMonthsOutlayDate();
		Set<String> $list = new TreeSet<>(new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				String[] str1 = s1.split("-");
				String[] str2 = s2.split("-");
				int num = Integer.valueOf(str2[0]) - Integer.valueOf(str1[0]);
				int num1 = num == 0 ? Integer.valueOf(str2[1]) - Integer.valueOf(str1[1]) : num;
				return num1;
			}
		});
		if (list != null && list.size() > 0) {
			for (PlatformsMonthsOutlay months : list) {
				$list.add(Constants.convert(months.getCreateDT(), Constants.format7));
			}
		}
		return new ArrayList($list);
	}

	/**
	 * 功能：生成营收总表数据
	 * */
	@Override
	public PlatformsMonthsOutlay savePlatformsMonthsOutlay(Integer branchs, Platforms plat, Date date1, Date date2) {
		PlatformsMonthsOutlay $outlay = null;
		if (plat != null) {
			$outlay = this.platformsMonthsOutlayDAO.buildPlatformsMonthsOutlay(branchs, plat.getId(), date1, date2);
			if ($outlay == null) {
				$outlay = new PlatformsMonthsOutlay();
				$outlay.setCreateDT(date1);
				$outlay.setBranchs(branchs);
				$outlay.setPlatId(plat.getId().toString());
				$outlay.setPlatforms(plat.getPlatName());
				this.platformsMonthsOutlayDAO.savePlatformsMonthsOutlay($outlay);
			}
			PlatformsMonths $months = this.platformsMonthsService.findPlatformsMonths(branchs, plat.getId().toString(), date1, date2);
			if ($months == null && $outlay != null) {
				$months = new PlatformsMonths();
				$months.setMonths($outlay);
				$months.setCreateDT(date1);
				$months.setBranchs(branchs);
				$months.setPlatId(plat.getId().toString());
				$months.setPlatforms(plat.getPlatName());
				this.platformsMonthsService.savePlatformsMonths($months);
			}
		}
		return $outlay;
	}

	@Override
	public boolean updatePlatformsMonthsOutlay(Integer monthsId) {
		return false;
	}

	/**
	 * 功能：获取数据
	 * */
	@Override
	public PlatformsMonthsOutlayBean readAllPlatformsMonthsOutlay(String platId, Integer mId, String date, SearchFilter filter) {
		Date $date = Constants.convert(date, Constants.format7);
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay($date);
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay($date);
		PlatformsMonthsOutlayBean bean = new PlatformsMonthsOutlayBean();
		PlatformsMonthsOutlay outlay = this.platformsMonthsOutlayDAO.readPlatformsMonthsOutlay(platId, mId, date1, date2, filter);
		if (outlay != null) {
			bean.setId(outlay.getId().toString());
			bean.setAudit(outlay.getAudit());
			bean.setRemarks(outlay.getRemarks() == null ? "" : outlay.getRemarks());

			BigDecimal laborService = new BigDecimal(String.valueOf(outlay.getLaborService() == null ? "0.00" : outlay.getLaborService()));
			BigDecimal divident = new BigDecimal(String.valueOf(outlay.getDivident() == null ? "0.00" : outlay.getDivident()));
			BigDecimal currentMonths = new BigDecimal(String.valueOf(outlay.getCurrentMonths() == null ? "0.00" : outlay.getCurrentMonths()));
			BigDecimal growthRate = new BigDecimal(String.valueOf(outlay.getGrowthRate() == null ? "0.00" : outlay.getGrowthRate()));
			BigDecimal lastMonths = new BigDecimal(String.valueOf(outlay.getLastMonths() == null ? "0.00" : outlay.getLastMonths()));

			BigDecimal lastOnlineIncome = new BigDecimal(String.valueOf(outlay.getLastOnlineIncome() == null ? "0.00" : outlay.getLastOnlineIncome()));
			BigDecimal onlineIncome = new BigDecimal(String.valueOf(outlay.getOnlineIncome() == null ? "0.00" : outlay.getOnlineIncome()));
			BigDecimal onlineRate = new BigDecimal(String.valueOf(outlay.getOnlineRate() == null ? "0.00" : outlay.getOnlineRate()));

			BigDecimal lastOfflineIncome = new BigDecimal(String.valueOf(outlay.getLastOfflineIncome() == null ? "0.00" : outlay.getLastOfflineIncome()));
			BigDecimal offlineIncome = new BigDecimal(String.valueOf(outlay.getOfflineIncome() == null ? "0.00" : outlay.getOfflineIncome()));
			BigDecimal offlineRate = new BigDecimal(String.valueOf(outlay.getOfflineRate() == null ? "0.00" : outlay.getOfflineRate()));

			BigDecimal onlineOutlay = new BigDecimal(String.valueOf(outlay.getOnlineOutlay() == null ? "0.00" : outlay.getOnlineOutlay()));
			BigDecimal offlineOutlay = new BigDecimal(String.valueOf(outlay.getOfflineOutlay() == null ? "0.00" : outlay.getOfflineOutlay()));
			BigDecimal actOnline = new BigDecimal(String.valueOf(outlay.getActOnlineOutlay() == null ? "0.00" : outlay.getActOnlineOutlay()));
			BigDecimal actOffline = new BigDecimal(String.valueOf(outlay.getActOfflineOutlay() == null ? "0.00" : outlay.getActOfflineOutlay()));
			BigDecimal maintain = new BigDecimal(String.valueOf(outlay.getMaintain() == null ? "0.00" : outlay.getMaintain()));
			BigDecimal activity = new BigDecimal(String.valueOf(outlay.getActivity() == null ? "0.00" : outlay.getActivity()));
			BigDecimal $outlay = new BigDecimal(String.valueOf(outlay.getOutlay() == null ? "0.00" : outlay.getOutlay()));
			BigDecimal netIncome = new BigDecimal(String.valueOf(outlay.getNetIncome() == null ? "0.00" : outlay.getNetIncome()));
			BigDecimal totalIncome = outlay.getTotalIncome() == null ? new BigDecimal(0.00) : outlay.getTotalIncome();
			BigDecimal transferIncome = new BigDecimal(String.valueOf(outlay.getTransferIncome() == null ? "0.00" : outlay.getTransferIncome()));

			BigDecimal selftop = new BigDecimal(String.valueOf(outlay.getSelftop() == null ? "0.00" : outlay.getSelftop()));
			BigDecimal society = new BigDecimal(String.valueOf(outlay.getSociety() == null ? "0.00" : outlay.getSociety()));
			BigDecimal anchors = new BigDecimal(String.valueOf(outlay.getAnchors() == null ? "0.00" : outlay.getAnchors()));

			bean.setDivident(divident.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setCurrentMonths(currentMonths.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setGrowthRate(String.format("%.2f%% %n", growthRate.setScale(4, BigDecimal.ROUND_HALF_UP)));
			bean.setLastMonths(lastMonths.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setLaborService(laborService.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

			bean.setLastOnlineIncome(lastOnlineIncome.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setOnlineIncome(onlineIncome.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setOnlineRate(String.format("%.2f%% %n", onlineRate.setScale(4, BigDecimal.ROUND_HALF_UP)));

			bean.setLastOfflineIncome(lastOfflineIncome.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setOfflineIncome(offlineIncome.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setOfflineRate(String.format("%.2f%% %n", offlineRate.setScale(4, BigDecimal.ROUND_HALF_UP)));

			bean.setOnlineOutlay(onlineOutlay.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setOfflineOutlay(offlineOutlay.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setActOnlineOutlay(actOnline.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setActOfflineOutlay(actOffline.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setMaintain(maintain.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setActivity(activity.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setOutlay($outlay.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setTotalIncome(totalIncome.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setNetIncome(netIncome.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setTransferIncome(transferIncome.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setSelftop(selftop.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setSociety(society.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			bean.setAnchors(anchors.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
		return bean;
	}

	/**
	 * 功能：保存数据
	 * */
	@Override
	public boolean savePlatformsMonthsOutlay(PlatformsMonthsOutlayBean bean) {
		PlatformsMonthsOutlay outlay = new PlatformsMonthsOutlay();
		if (bean.getId() != null && bean.getId().trim().length() > 0) {
			outlay = this.readPlatformsMonthsOutlay(bean.getId());
			if (outlay != null) {
				if (bean.getCurrentMonths() != null && bean.getCurrentMonths().trim().length() > 0)
					outlay.setCurrentMonths(new BigDecimal(bean.getCurrentMonths()));
				outlay.setRemarks(bean.getRemarks());
				if (bean.getDivident() != null && bean.getDivident().trim().length() > 0)
					outlay.setDivident(new BigDecimal(bean.getDivident()));
				if (bean.getTransferIncome() != null && bean.getTransferIncome().trim().length() > 0)
					outlay.setTransferIncome(new BigDecimal(bean.getTransferIncome()));
				if (bean.getSelftop() != null && bean.getSelftop().trim().length() > 0)
					outlay.setSelftop(new BigDecimal(bean.getSelftop()));
				if (bean.getSociety() != null && bean.getSociety().trim().length() > 0)
					outlay.setSociety(new BigDecimal(bean.getSociety()));
				if (bean.getAnchors() != null && bean.getAnchors().trim().length() > 0)
					outlay.setAnchors(new BigDecimal(bean.getAnchors()));
			}
		} else {
			if (bean.getCurrentMonths() != null && bean.getCurrentMonths().trim().length() > 0)
				outlay.setCurrentMonths(new BigDecimal(bean.getCurrentMonths()));
			outlay.setRemarks(bean.getRemarks());
			if (bean.getPlatId() != null && bean.getPlatId().trim().length() > 0)
				outlay.setPlatId(bean.getPlatId());
			if (bean.getDivident() != null && bean.getDivident().trim().length() > 0)
				outlay.setDivident(new BigDecimal(bean.getDivident()));
			if (bean.getTransferIncome() != null && bean.getTransferIncome().trim().length() > 0)
				outlay.setTransferIncome(new BigDecimal(bean.getTransferIncome()));
			if (bean.getSelftop() != null && bean.getSelftop().trim().length() > 0)
				outlay.setSelftop(new BigDecimal(bean.getSelftop()));
			if (bean.getSociety() != null && bean.getSociety().trim().length() > 0)
				outlay.setSociety(new BigDecimal(bean.getSociety()));
			if (bean.getAnchors() != null && bean.getAnchors().trim().length() > 0)
				outlay.setAnchors(new BigDecimal(bean.getAnchors()));
		}
		this.workManagers.onEvents(WorkNames.MAKEOUTLAY, outlay);

		this.workManagers.onEvents(WorkNames.MAKETOTALOUTLAY, outlay);

		return true;
	}

	@Override
	public PlatformsMonthsOutlay readPlatformsMonthsOutlay(String id) {
		if (id != null && id.trim().length() > 0)
			return this.platformsMonthsOutlayDAO.readAllPlatformsMonthsOutlay(id);
		return null;
	}

	@Override
	public PlatformsAudits readPlatfromsAuditesByMid(Integer mId) {
		return this.platformsMonthsOutlayDAO.readPlatfromsAuditesByMid(mId);
	}

	/**
	 * 功能：第一次打开获取当月记录
	 * */
	@Override
	public PlatformsMonthsOutlay readPlatformsMonthsOutlayByHead(Integer mId, Integer branchs, String platId, String date) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		return this.platformsMonthsOutlayDAO.findPlatformsMonthsOutlyaByLast(branchs, platId, date1, date2);
	}

	@Override
	public PlatformsMonthsOutlay readPlatformsMonthsOutlay(String platId, Integer mId, String date, SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		PlatformsMonthsOutlay outlay = this.platformsMonthsOutlayDAO.readPlatformsMonthsOutlay(platId, mId, date1, date2, filter);
		if (outlay != null) {
			return outlay;
		}
		return null;
	}

	@Override
	public boolean updatePlatformsMonthsOutlay(PlatformsMonthsOutlayBean bean, String audit) {
		if (bean.getId() != null && bean.getPlatId() != null) {
			PlatformsMonthsOutlay outlay = this.platformsMonthsOutlayDAO.readAllPlatformsMonthsOutlay(bean.getId());
			if (outlay != null) {
				if ("pass".equals(audit)) {
					outlay.setAudit(Audit.PASS);
				} else if ("reject".equals(audit)) {
					if (outlay.getAudit() == Audit.PASS) {
						outlay.setAudit(Audit.AUDIT);
					} else {
						outlay.setAudit(Audit.FAILURE);
					}
				} else if ("audit".equals(audit)) {
					outlay.setAudit(Audit.AUDIT);
				} else if ("finalpass".equals(audit)) {
					outlay.setAudit(Audit.FINALPASS);
				}
				if (this.platformsMonthsOutlayDAO.savePlatformsMonthsOutlay(outlay)) {
					if ("reject".equals(audit)) {
						PlatformsAudits audits = new PlatformsAudits();
						audits.setPlatId(outlay.getPlatId());
						audits.setPlatforms(outlay.getPlatforms());
						audits.setRejectMsg(bean.getRemarks());
						Platforms plat = this.platformsDAO.findPlatforms(Integer.valueOf(outlay.getPlatId()));
						if (plat != null && plat.getPlatManager() != null)
							audits.setPlatManager(plat.getPlatManager().getName());
						audits.setMonths(outlay);
						this.platformsMonthsOutlayDAO.savePlatformsAudits(audits);
					}
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<PlatformsMonthsOutlay> findPlatformsMonthsOutlay(Date date1, Date date2) {
		return this.platformsMonthsOutlayDAO.readAllPlatformsMonthsOutlayByAudit(date1, date2);
	}

	@Override
	public boolean updatePlatformsMonthsOutlayByAudit(Integer id, String audit) {
		PlatformsMonthsOutlay outlay = this.readPlatformsMonthsOutlay(id.toString());
		if (outlay != null) {
			outlay.setAudit(Audit.AUDIT);
			this.platformsMonthsOutlayDAO.savePlatformsMonthsOutlay(outlay);
			return true;
		}
		return false;
	}

	@Override
	public PlatformsMonthsOutlay findPlatformsMonthsOutlay(String platId, Integer branchs, Date date1, Date date2) {
		return this.platformsMonthsOutlayDAO.findPlatformsMonthsOutlay(platId, branchs, date1, date2);
	}

	@Override
	public BigDecimal findSumOfflineIncome(Integer id) {
		BigDecimal sumOfflineIncome = this.platformsMonthsOutlayDAO.findOfflineIncome(id);
		if (sumOfflineIncome != null)
			return sumOfflineIncome;
		else
			return new BigDecimal(0.00);
	}

	@Override
	public BigDecimal findSumOnlineIncome(Integer id) {
		BigDecimal sumOnlineIncome = this.platformsMonthsOutlayDAO.findOnlineIncome(id);
		if (sumOnlineIncome != null)
			return sumOnlineIncome;
		else
			return new BigDecimal(0.00);
	}

	@Override
	public List<PlatformsMonthsOutlay> readAllPlatformsMonthsOutlayByDate(Date date1, Date date2) {
		return this.platformsMonthsOutlayDAO.readAllPlatformsMonthsOutlayByDate(date1, date2);
	}

	@Override
	public PlatformsMonthsOutlay readPlatformsMonths(Date date1, Date date2, Integer platId) {
		if (platId != null && platId.intValue() > 0) {
			return this.platformsMonthsOutlayDAO.readPlatformsMonths(date1, date2, platId);
		}
		return null;
	}

	@Override
	public List<PlatformsMonthsOutlay> findPlatformsMonthsOutlayList(Date date) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(date);
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(date);
		return this.platformsMonthsOutlayDAO.findPlatformsMonthsOutlayList(date1, date2);
	}

	@Override
	public boolean findMonthsOutlay(Integer branchs, Integer pId, Date date1, Date date2, Audit audit) {
		PlatformsMonthsOutlay monthsOutlay = this.platformsMonthsOutlayDAO.findPlatformsMonthsOutlyaByLast(branchs, pId.toString(), date1, date2);
		if (monthsOutlay != null && monthsOutlay.getAudit() == audit) {
			return true;
		}
		return false;
	}

	@Override
	public PlatformsMonthsOutlay readPlatformsMonthsOutlay(Integer pid, Integer branchs, Date date1, Date date2) {
		if (pid != null && branchs != null && date1 != null && date2 != null) {
			PlatformsMonthsOutlay $outlay = this.platformsMonthsOutlayDAO.buildPlatformsMonthsOutlay(branchs, pid, date1, date2);
			if ($outlay != null)
				return $outlay;
		}
		return null;
	}
}
