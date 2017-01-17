package com.baofeng.oa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.oa.bean.PlatformsMaintainOutlayBean;
import com.baofeng.oa.dao.BigtopDAO;
import com.baofeng.oa.dao.PlatformsMaintainOutlayDAO;
import com.baofeng.oa.entity.BaseEnums.Audit;
import com.baofeng.oa.entity.BigTopDetails;
import com.baofeng.oa.entity.PlatformsMaintainOutlay;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.service.IPlatformsMaintainOutlayService;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.Message.Head;
import com.baofeng.utils.SearchFilter;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

@Service("maintainOutlayService")
public class PlatformsMaintainOutlayServiceImpl implements IPlatformsMaintainOutlayService {
	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private BigtopDAO bigtopDAO;
	@Autowired
	private PlatformsMaintainOutlayDAO maintainOutlayDAO;

	/**
	 * 功能：维护费支出数据静态化
	 * */
	@Override
	public boolean updatePlatformsMaintainOutlay(Integer monthsId) {
		List<PlatformsMaintainOutlay> outlayList = this.maintainOutlayDAO.readPlatformsMaintainOutlayList(monthsId);
		if (outlayList != null && outlayList.size() > 0) {
			for (PlatformsMaintainOutlay outlay : outlayList) {
				outlay.setAudit(Audit.PASS);
				// 净支出
				Float netOutlay = Float.valueOf(0.00f);
				if (outlay.getPersonalGift() != null && outlay.getPersonalGift().floatValue() > 0)
					netOutlay = netOutlay + outlay.getPersonalGift().floatValue();
				if (outlay.getTransferFee() != null && outlay.getTransferFee().floatValue() > 0)
					netOutlay = netOutlay + outlay.getTransferFee().floatValue();
				if (outlay.getTopup() != null && outlay.getTopup().floatValue() > 0)
					netOutlay = netOutlay + outlay.getTopup().floatValue();
				if (outlay.getTax() != null && outlay.getTax().floatValue() > 0)
					netOutlay = netOutlay + outlay.getTax().floatValue();
				if (outlay.getRestitution() != null && outlay.getRestitution().floatValue() > 0)
					netOutlay = netOutlay - outlay.getRestitution().floatValue();
				outlay.setNetOutlay(netOutlay);
				this.maintainOutlayDAO.savePlatformsMaintainOutlay(outlay);
			}
			return true;
		}
		return false;
	}

	@Override
	public List<PlatformsMaintainOutlayBean> readAllPlatformsMaintainOutlay(String platId, Integer mId, String date,SearchFilter searchFilter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		if (platId != null) {
			List<PlatformsMaintainOutlay> list = this.maintainOutlayDAO.readAllPlatformsMaintainOutlay(platId, mId, date1, date2,searchFilter);
			List<PlatformsMaintainOutlayBean> listBean = new ArrayList<PlatformsMaintainOutlayBean>();
			if (list != null && list.size() > 0) {
				for (PlatformsMaintainOutlay post : list) {
					PlatformsMaintainOutlayBean bean = new PlatformsMaintainOutlayBean();
					BigDecimal personalGift = new BigDecimal(post.getPersonalGift() != null ? post.getPersonalGift() : 0.00);
					BigDecimal transferFee = new BigDecimal(post.getTransferFee() != null ? post.getTransferFee() : 0.00);
					BigDecimal topUp = new BigDecimal(post.getTopup() != null ? post.getTopup() : 0.00);
					topUp = topUp.add(transferFee).add(personalGift);
					bean.setAudit(post.getAudit());
					bean.setChannel(post.getChannel());
					bean.setId(post.getId());
					bean.setName(post.getName());
					bean.setNumber(post.getNumber());
					bean.setOutlay(post.getOutlay());
					bean.setRemarks(post.getRemarks());
					bean.setRestitution(post.getRestitution());
					bean.setTopup(topUp.floatValue());
					bean.setTax(String.format("%.2f", post.getTax() != null ? post.getTax() : 0.00));
					// 净支出
					BigDecimal netOutlay = new BigDecimal(post.getPersonalGift() != null ? post.getPersonalGift() : 0);
					netOutlay = netOutlay.add(new BigDecimal(post.getTransferFee() != null ? post.getTransferFee() : 0));
					netOutlay = netOutlay.add(new BigDecimal(post.getTopup() != null ? post.getTopup() : 0));
					netOutlay = netOutlay.add(post.getTax() != null ? post.getTax() : new BigDecimal(0.00));
					netOutlay = netOutlay.subtract(new BigDecimal(post.getRestitution() != null ? post.getRestitution() : 0));
					bean.setNetOutlay(String.format("%.2f", netOutlay));
					listBean.add(bean);
				}
			}
			return listBean;
		}
		return null;
	}

	@Override
	public boolean savePlatformsMaintainOutlay(PlatformsMaintainOutlay post) {
		if (post != null) {
			if (post.getMonths() != null && post.getMonths().getId().intValue() > 0) {
				PlatformsMonthsOutlay months = this.maintainOutlayDAO.readPlatformsMonthsOutlay(post.getMonths().getId());
				if (months != null) {
					post.setMonths(months);
					BigDecimal tax = new BigDecimal(0.00);
					tax = new BigDecimal(post.getTopup() != null ? post.getTopup() : 0.00f).multiply(new BigDecimal(0.06));
					post.setTax(tax);
				}
			}
			if (this.maintainOutlayDAO.savePlatformsMaintainOutlay(post)) {
				this.workManagers.onEvents(WorkNames.BIGOUT, post);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		if (id != null && id.intValue() > 0) {
			PlatformsMaintainOutlay outlay = this.maintainOutlayDAO.readPlatformsMaintainOutlay(id);
			if (outlay != null) {
				outlay.setMonths(null);
				this.maintainOutlayDAO.savePlatformsMaintainOutlay(outlay);
				BigTopDetails details = this.bigtopDAO.readBigtopByOutlay(outlay.getPlatformsId(), outlay.getId());
				if (details != null)
					this.bigtopDAO.deleteBigTopDetails(details);
				if (this.maintainOutlayDAO.deletePlatformsMaintainOutlay(outlay)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<PlatformsMaintainOutlay> readAllPlatformsMaintainOutlayByDate(Integer monthid) {
		return this.maintainOutlayDAO.readAllPlatformsMaintainOutlayByDate(monthid);
	}

	public static void main(String[] args) {
		System.out.println(Head.valueOf("DEL"));
	}

}
