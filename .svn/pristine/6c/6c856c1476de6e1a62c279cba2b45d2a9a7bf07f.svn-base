package com.baofeng.oa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.oa.bean.PlatformsActivityOutlayBean;
import com.baofeng.oa.dao.PlatformsActivityOutlayDAO;
import com.baofeng.oa.entity.BaseEnums.Audit;
import com.baofeng.oa.entity.PlatformsActivityOutlay;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.service.IPlatformsActivityOutlayService;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.SearchFilter;

@Service("activityOutlayService")
public class PlatformsActivityOutlayServiceImpl implements IPlatformsActivityOutlayService {

	@Autowired
	private PlatformsActivityOutlayDAO activityOutlayDAO;

	/**
	 * 功能：活动支出数据静态化
	 * */
	@Override
	public boolean updatePlatformsActivityOutlay(Integer monthsId) {
		List<PlatformsActivityOutlay> outlayList = this.activityOutlayDAO.readPlatformsActivityOutlayList(monthsId);
		if (outlayList != null && outlayList.size() > 0) {
			for (PlatformsActivityOutlay outlay : outlayList) {
				outlay.setAudit(Audit.PASS);
				this.activityOutlayDAO.savePlatformsActivityOutlay(outlay);
			}
			return true;
		}
		return false;
	}

	@Override
	public List<PlatformsActivityOutlayBean> readAllPlatformsActivityOutlay(String platId, Integer mId, String date,SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		if (platId != null) {
			List<PlatformsActivityOutlay> list = this.activityOutlayDAO.readAllPlatformsActivityOutlay(platId, mId, date1, date2,filter);
			List<PlatformsActivityOutlayBean> listBean = new ArrayList<PlatformsActivityOutlayBean>();
			if (list != null && list.size() > 0) {
				for (PlatformsActivityOutlay post : list) {
					PlatformsActivityOutlayBean bean = new PlatformsActivityOutlayBean();
					bean.setActivity(post.getActivity());
					bean.setAudit(post.getAudit());
					bean.setAwardsPersonnel(post.getAwardsPersonnel());
					if (post.getProject() != null && !post.getProject().equals("null")) {
						bean.setProject(post.getProject());
					} else {
						bean.setProject("");
					}
					bean.setTax(String.format("%.2f", post.getTax() != null ? post.getTax() : new BigDecimal(0.00)));
					bean.setChannel(post.getChannel());
					bean.setChannelId(post.getChannelId());
					bean.setId(post.getId());
					bean.setPlatforms(post.getPlatforms());
					bean.setRemarks(post.getRemarks());
					if (post.getNum() != null) {
						bean.setNum(post.getNum().toString());
					}
					// 总计金额
					if (post.getTotalMoney() != null) {
						bean.setTotalMoney(String.format("%.2f", post.getTotalMoney()));
					}
					listBean.add(bean);
				}
			}
			return listBean;
		}
		return null;
	}

	@Override
	public boolean savePlatformsActivityOutlay(PlatformsActivityOutlay post) {
		if (post != null) {
			if (post.getMonths() != null && post.getMonths().getId() != null && post.getMonths().getId().intValue() > 0) {
				PlatformsMonthsOutlay months = this.activityOutlayDAO.readPlatformsMonthsOutlay(post.getMonths().getId());
				if (months != null) {
					post.setMonths(months);
					if (post.getTax() != null && post.getTax().intValue() == 1) {
						BigDecimal tax = new BigDecimal(0.00);
						tax = new BigDecimal(post.getTotalMoney() != null ? post.getTotalMoney() : 0.00f).multiply(new BigDecimal(0.06)).setScale(2, BigDecimal.ROUND_HALF_UP);
						post.setTax(tax);
					} else {
						post.setTax(new BigDecimal(0.00));
					}
				}
			}
			if (this.activityOutlayDAO.savePlatformsActivityOutlay(post)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		if (id != null && id.intValue() > 0) {
			PlatformsActivityOutlay outlay = this.activityOutlayDAO.readPlatformsActivityOutlay(id);
			outlay.setMonths(null);
			this.activityOutlayDAO.savePlatformsActivityOutlay(outlay);
			if (this.activityOutlayDAO.deletePlatformsActivityOutlay(outlay)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public PlatformsActivityOutlay findActivityOutlayByPlatId(String key, Integer platId, Date date1, Date date2) {
		if (key != null && key.trim().length() > 0) {
			return this.activityOutlayDAO.findActivityOutlayByPlatId(key, platId, date1, date2);
		}
		return null;
	}

}
