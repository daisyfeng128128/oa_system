package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.oa.bean.PlatformsManagerOutlayBean;
import com.baofeng.oa.dao.PlatformsManagerOutlayDAO;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.oa.entity.PlatformsManagerOutlay;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmpAttendanceService;
import com.baofeng.oa.service.IPlatformsManagerOutlayService;
import com.baofeng.oa.service.IPlatformsManagerService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.SearchFilter;

@Service("managerOutlayService")
public class PlatformsManagerOutlayServiceImpl implements IPlatformsManagerOutlayService {

	@Autowired
	private PlatformsManagerOutlayDAO managerOutlayDAO;
	@Autowired
	private IPlatformsManagerService platformsManagerService;
	@Autowired
	private IEmpAttendanceService empAttendanceService;
	@Autowired
	private IBranchOfficeService branchOfficeService;

	@Override
	public void addManagerOutlay(List<PlatformsManagerOutlay> listOut) {
		if (listOut != null && listOut.size() > 0) {
			for (PlatformsManagerOutlay platformsManagerOutlay : listOut) {
				this.managerOutlayDAO.addPlatformsManagerOutlay(platformsManagerOutlay);
			}
		}
	}

	/**
	 * 功能：管理支出数据同步验证
	 * */
	@Override
	public boolean findValidation(Integer manId, Integer monthsId, String platId, String channelId, Date date1, Date date2) {
		return this.managerOutlayDAO.findValidation(manId, monthsId, platId, channelId, date1, date2);
	}

	@Override
	public List<PlatformsManagerOutlayBean> readAllPlatformsManagerOutlay(String platId, Integer mId, String date, String online, SearchFilter searchFilter) {
		Date $date = Constants.convert(date, Constants.format7);
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay($date);
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay($date);
		List<PlatformsManagerOutlayBean> listBean = new ArrayList<PlatformsManagerOutlayBean>();
		List<PlatformsManagerOutlay> list = this.managerOutlayDAO.readAllPlatformsManagerOutlay(platId, mId, date1, date2, online, searchFilter);
		if (list != null && list.size() > 0) {
			BranchOffice branchs = null;
			for (PlatformsManagerOutlay post : list) {
				PlatformsManagerOutlayBean bean = new PlatformsManagerOutlayBean();
				bean.setAttendance(post.getAttendance());
				Float attendance = this.empAttendanceService.findAttendanceByNumberAndDate(post.getNumber(), post.getBranchs(), date);
				if (LineGenres.OFFLINE == Enum.valueOf(LineGenres.class, online)) {
					bean.setAttendance(attendance);
				}
				bean.setAudit(post.getAudit());
				bean.setAliasname(post.getAliasname());
				bean.setBasicSalary(post.getBasicSalary());
				bean.setChannel(post.getChannel());
				bean.setChannelId(post.getChannelId());
				bean.setId(post.getId());
				bean.setManagerId(post.getManagerId());
				bean.setMealAllowance(post.getMealAllowance());
				bean.setName(post.getName());
				if (post.getNumber() != null) {
					if (branchs == null)
						branchs = this.branchOfficeService.readBranchOffice(post.getBranchs());
					if (branchs != null)
						bean.setNumber(branchs.getNumberHead() + String.format("%04d", post.getNumber()));
					else
						bean.setNumber(String.format("%04d", post.getNumber()));
				} else
					bean.setNumber("-");
				bean.setOtherAllowance(post.getOtherAllowance());
				bean.setPlatforms(post.getPlatforms());
				bean.setPlatformsId(post.getPlatformsId());
				bean.setPositions(post.getPositions() == null ? "-" : post.getPositions());
				bean.setPushMoney(post.getPushMoney());
				bean.setRemarks(post.getRemarks());
				bean.setGenre(post.getGenre().toString());
				bean.setDeduct(post.getDeduct());

				// 底薪
				Float basicSalary = post.getBasicSalary();
				bean.setRealBasicSalary(String.format("%.2f", basicSalary));
				if (post.getGenre() == LineGenres.ONLINE) {
					if (post.getAttendance() != null && post.getAttendance().floatValue() < Float.valueOf(30).floatValue()) {
						basicSalary = (basicSalary / 30) * post.getAttendance().floatValue();
						bean.setRealBasicSalary(String.format("%.2f", basicSalary));
					}
				} else {
					if (post.getAttendance() != null && post.getAttendance().floatValue() < Constants.DEFAULT_ATTENDANCE) {
						basicSalary = (basicSalary / Constants.DEFAULT_MAXNUM) * post.getAttendance().floatValue();
						bean.setRealBasicSalary(String.format("%.2f", basicSalary));
					}
				}

				// 餐补
				Float mealAllowance = post.getMealAllowance() == null ? Float.valueOf("0.00") : post.getMealAllowance();
				if (post.getAttendance() != null && post.getAttendance().floatValue() > 0 && post.getMealAllowance() != null && post.getMealAllowance().floatValue() > 0) {
					if (post.getAttendance().floatValue() < Constants.DEFAULT_ATTENDANCE) {
						mealAllowance = mealAllowance.floatValue() * post.getAttendance().floatValue();
					} else {
						mealAllowance = mealAllowance.floatValue() * Double.valueOf(Constants.DEFAULT_ATTENDANCE).floatValue();
					}
					bean.setRealMealAllowance(String.format("%.2f", mealAllowance));
				}

				// 合计
				Float sumTotal = Float.valueOf(0.00f);
				if (basicSalary != null)
					sumTotal = sumTotal.floatValue() + basicSalary.floatValue();
				if (post.getPushMoney() != null)
					sumTotal = sumTotal.floatValue() + post.getPushMoney().floatValue();
				if (mealAllowance != null)
					sumTotal = sumTotal.floatValue() + mealAllowance.floatValue();
				if (post.getOtherAllowance() != null)
					sumTotal = sumTotal.floatValue() + post.getOtherAllowance().floatValue();
				if (post.getDeduct() != null)
					sumTotal = sumTotal.floatValue() - post.getDeduct().floatValue();
				bean.setTotal(String.format("%.2f", sumTotal));
				listBean.add(bean);
			}
		}
		return listBean;
	}

	@Override
	public boolean updateManagerOutlay(List<PlatformsManagerOutlay> list) {
		if (list != null && list.size() > 0) {
			for (PlatformsManagerOutlay post : list) {
				PlatformsManagerOutlay outlay = this.managerOutlayDAO.readPlatformsManagerOutlay(post.getId());
				String date = Constants.convert(outlay.getCreateDT(), Constants.format7);
				if (outlay.getGenre() == LineGenres.OFFLINE) {
					Float attendance = this.empAttendanceService.findAttendanceByNumberAndDate(outlay.getNumber(), outlay.getBranchs(), date);
					outlay.setAttendance(attendance != null ? attendance : 0.0f);
				} else {
					outlay.setAttendance(post.getAttendance());
				}
				outlay.setPushMoney(post.getPushMoney());
				outlay.setOtherAllowance(post.getOtherAllowance());
				outlay.setDeduct(post.getDeduct());
				outlay.setRemarks(post.getRemarks());
				post = outlay;
				post.make();
				if (!this.managerOutlayDAO.update(post)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public List<?> findPlatformsManagerOutlayList(Integer manaId) {
		return this.managerOutlayDAO.readPlatformsManagerOutlayListBySession(manaId);
	}

	@Override
	public PlatformsManagerOutlay findPlatformsManagerOutlay(Integer manId) {
		return this.managerOutlayDAO.findPlatformsManagerOutlay(manId);
	}

	@Override
	public boolean savePlatformsManagerOutlay(PlatformsManagerOutlay outlay) {
		return this.managerOutlayDAO.savePlatformsManagerOutlayBySession(outlay);
	}

	@Override
	public List<PlatformsManagerOutlay> findPlatformsManagerOutlayListNew(Integer manaId, Date date1, Date date2) {
		List<?> list = this.platformsManagerService.findPlatformsManagerBySession(manaId);
		List<PlatformsManagerOutlay> listOut = new ArrayList<PlatformsManagerOutlay>();
		if (list != null && list.size() > 0) {
			for (Object o : list) {
				PlatformsManager platMan = (PlatformsManager) o;
				PlatformsManagerOutlay outlay = this.managerOutlayDAO.readPlatformsManagerOutlayListByMid(platMan.getId(), date1, date2);
				if (outlay != null)
					listOut.add(outlay);
			}
		}
		return listOut;
	}

	@Override
	public boolean deletePlatformsManagers(PlatformsManager pmanager) {
		PlatformsManagerOutlay outlay = this.findPlatformsManagerOutlay(pmanager.getId());
		if (outlay != null) {
			outlay.setMonths(null);
			return this.managerOutlayDAO.deletePlatformsManagers(outlay);
		}
		return false;
	}

	@Override
	public boolean findActValidation(String name, Integer mid, String platId, String channelId, Date date1, Date date2) {
		return this.managerOutlayDAO.findActValidation(name, mid, platId, channelId, date1, date2);
	}

	@Override
	public List<PlatformsManagerOutlay> findPlatformsManagerOutlayList(Integer mid, Date date) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(date);
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(date);
		return this.managerOutlayDAO.findPlatformsManagerOutlayList(mid, date1, date2);
	}
	
	@Override
	public PlatformsManagerOutlay readPlatformsManagerOutlay(Integer number, Integer branchs) {
		Date date1 = CustomDateUtils.Handler.lastMonthFirstDay();
		Date date2 = CustomDateUtils.Handler.lastMonthEndDay();
		PlatformsManagerOutlay manageros = this.managerOutlayDAO.readPlatformsManagerOutlay(number, branchs, date1, date2);
		if (manageros != null) {
			return manageros;
		}
		return null;
	}
}
