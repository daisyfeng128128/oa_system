package com.baofeng.oa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.bean.PlatformsMonthsBean;
import com.baofeng.oa.dao.PlatformsDAO;
import com.baofeng.oa.dao.PlatformsMonthsDAO;
import com.baofeng.oa.dao.PlatformsMonthsOutlayDAO;
import com.baofeng.oa.dao.PlatformsOfflineIncomeDAO;
import com.baofeng.oa.dao.PlatformsOnlineIncomeDAO;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsMonths;
import com.baofeng.oa.service.IPlatformsMonthsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Service("platformsMonthsService")
public class PlatformsMonthsServiceImpl implements IPlatformsMonthsService {

	@Autowired
	private PlatformsDAO platformsDAO;
	@Autowired
	private PlatformsMonthsDAO platformsMonthsDAO;
	@Autowired
	private PlatformsMonthsOutlayDAO platformsMonthsOutlayDAO;
	@Autowired
	private PlatformsOnlineIncomeDAO platformsOnlineIncomeDAO;
	@Autowired
	private PlatformsOfflineIncomeDAO offlineIncome;

	@Override
	public PlatformsMonths findPlatformsMonthsByMId(Integer mid) {
		return this.platformsMonthsDAO.findAllPlatformsMonths(mid);
	}

	/**
	 * 功能：平台流水数据静态化
	 * */
	@Override
	public boolean updatePlatformsMonthsOutlay(Integer monthsId) {
		return false;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readAllPlatformsMonths(int pageSize, int curPage, String sortName, String sortOrder, String date, SearchFilter filter, List<RoleDetailsAtts> platList) {
		Date $date = Constants.convert(date, Constants.format7);
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay($date);
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay($date);
		NPageResult page = this.platformsMonthsDAO.readAllPlatformsMonths(date1, date2, pageSize, curPage, filter);
		if (page.getData() != null && page.getData().size() > 0) {
			List<PlatformsMonthsBean> resultList = new ArrayList<PlatformsMonthsBean>();
			for (Object o : page.getData()) {
				PlatformsMonths months = (PlatformsMonths) o;
				for (RoleDetailsAtts atts : platList) {
					if (months.getPlatId().equals(atts.getOpkey())) {
						PlatformsMonthsBean bean = new PlatformsMonthsBean();
						bean.setId(months.getId().toString());
						bean.setPlatforms(months.getPlatforms());
						bean.setRemarks(months.getRemarks());
						bean.setChIncome(String.format("%.2f", months.getChIncome() == null ? 0.00f : months.getChIncome()));
						bean.setChOutlay(String.format("%.2f", months.getChOutlay() == null ? 0.00f : months.getChOutlay()));
						Platforms plat = this.platformsDAO.findPlatforms(Integer.valueOf(months.getPlatId()));
						if (plat != null) {
							switch (plat.getIncomeGenre()) {
							case SHUIQIAN:
								bean.setGenre("税前");
								break;
							case SHUIHOU:
								bean.setGenre("税后");
								break;
							case KAIPIAOSHUI:
								bean.setGenre("开票税");
								break;
							default:
								break;
							}
						}
						bean.setBgIncome(String.format("%.2f", months.getBgIncome() == null ? 0.00f : months.getBgIncome()));
						bean.setTaxSubsidy(String.format("%.2f", months.getTaxSubsidy() == null ? 0.00f : months.getTaxSubsidy()));
						bean.setReceivable(String.format("%.2f", months.getReceivable() == null ? 0.00f : months.getReceivable()));
						bean.setOnlineTotal(String.format("%.2f", months.getOnlineTotal() == null ? 0.00f : months.getOnlineTotal()));
						bean.setOfflineTotal(String.format("%.2f", months.getOfflineTotal() == null ? 0.00f : months.getOfflineTotal()));
						bean.setIncomeTotal(String.format("%.2f", months.getIncomeTotal() == null ? 0.00f : months.getIncomeTotal()));
						bean.setOutlayTotal(String.format("%.2f", months.getOutlayTotal() == null ? 0.00f : months.getOutlayTotal()));
						bean.setNetProfit(String.format("%.2f", months.getNetProfit() == null ? 0.00f : months.getNetProfit()));
						bean.setOtherIncome(String.format("%.2f", months.getOtherIncome() == null ? 0.00f : months.getOtherIncome()));
						bean.setRemarks(months.getRemarks());
						resultList.add(bean);
					}
				}
			}
			page.setTotalRows(resultList.size());
			page.setData(resultList);
		}
		return page;
	}

	@Override
	public BigDecimal findSumCurrentIncome(Integer branchs, Date $date1, Date $date2) {
		return this.platformsMonthsDAO.findSumCurrentIncome(branchs, $date1, $date2);
	}

	@Override
	public void addPlatformsMonths(PlatformsMonths months) {
		this.platformsMonthsDAO.addPlatformsMonths(months);
	}

	@Override
	public PlatformsMonths findPlatformsMonths(Integer branchs, String platId, Date date1, Date date2) {
		return this.platformsMonthsDAO.findPlatformsMonths(branchs, platId, date1, date2);
	}

	@Override
	public boolean savePlatformsMonths(PlatformsMonths months) {
		return this.platformsMonthsDAO.savePlatformsMonths(months);
	}

	@Override
	public boolean updateIncome(List<PlatformsMonths> list) {
		if (list != null && list.size() > 0) {
			for (PlatformsMonths months : list) {
				PlatformsMonths $months = this.platformsMonthsDAO.readPlatformsMonths(months.getId());
				$months.setTaxSubsidy(months.getTaxSubsidy());
				if (!this.platformsMonthsDAO.updateMonths($months)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public List<PlatformsMonths> findAllPlatformsMonths(Date date1, Date date2) {
		return this.platformsMonthsDAO.findAllPlatformsMonths(date1, date2);
	}

	@Override
	public boolean updateTopUp(List<PlatformsMonths> list) {
		if (list != null && list.size() > 0) {
			for (PlatformsMonths months : list) {
				PlatformsMonths $months = this.platformsMonthsDAO.readPlatformsMonths(months.getId());
				$months.setChIncome(months.getChIncome());
				$months.setChOutlay(months.getChOutlay());
				$months.setRemarks(months.getRemarks());
				if (!this.platformsMonthsDAO.updateMonths($months)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readAllMonthsOutlay(int pageSize, int curPage, String sortName, String sortOrder, String date, SearchFilter filter, List<RoleDetailsAtts> platList) {
		Date $date = Constants.convert(date, Constants.format7);
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay($date);
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay($date);
		NPageResult page = this.platformsMonthsDAO.readAllMonthsOutlay(date1, date2, pageSize, curPage, filter);
		if (page.getData() != null && page.getData().size() > 0) {
			List<PlatformsMonthsBean> resultList = new ArrayList<PlatformsMonthsBean>();
			for (Object o : page.getData()) {
				PlatformsMonths months = (PlatformsMonths) o;
				for (RoleDetailsAtts atts : platList) {
					if (months.getPlatId().equals(atts.getOpkey())) {
						PlatformsMonthsBean bean = new PlatformsMonthsBean();
						bean.setId(months.getId().toString());
						bean.setPlatforms(months.getPlatforms());
						bean.setRemarks(months.getRemarks());
						bean.setChIncome(String.format("%.2f", months.getChIncome() == null ? 0.00f : months.getChIncome()));
						bean.setChOutlay(String.format("%.2f", months.getChOutlay() == null ? 0.00f : months.getChOutlay()));

						resultList.add(bean);
					}
				}
			}
			page.setTotalRows(resultList.size());
			page.setData(resultList);
		}
		return page;

	}

	@Override
	public List<PlatformsMonths> readAllPlatformsMonthsByDate(String date) {
		List<PlatformsMonths> resultList = new ArrayList<PlatformsMonths>();
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		List<PlatformsMonths> detailsList = this.platformsMonthsDAO.readAllPlatformsMonthsByDate(date1, date2);
		if (detailsList != null && detailsList.size() > 0) {
			for (PlatformsMonths months : detailsList) {
				Platforms plat = this.platformsDAO.findPlatforms(Integer.valueOf(months.getPlatId()));
				if (plat != null) {
					switch (plat.getIncomeGenre()) {
					case SHUIQIAN:
						months.setGenre("税前");
						break;
					case SHUIHOU:
						months.setGenre("税后");
						break;
					case KAIPIAOSHUI:
						months.setGenre("开票税");
						break;
					default:
						break;
					}
				}
				resultList.add(months);
			}
		}
		return resultList;
	}
}
