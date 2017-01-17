package com.baofeng.oa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.bean.PlatformsManagerOutlayBean;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.PlatformsManagerOutlay;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IPlatformsManagerOutlayService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.ResultMsg;

/**
 * 功能 ： 频道助理支出
 */
@Controller
public class PlatformsManagerOutlayController extends BaseController {

	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IPlatformsManagerOutlayService ManagerOutlayService;
	@Autowired
	private IPlatformsMonthsOutlayService platformsMonthsOutlayService;

	@RequestMapping(value = "/managerOnline/show", method = RequestMethod.GET)
	public ModelAndView managerOnline(Integer platId, Integer mId, Integer branchs, String date, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/platManagerOutlay");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("outlay/show.do"), "impr", "pass", "reject", "audit", "finalpass");
		boolean isEdit = super.isHaveAttribute(request, String.valueOf("outlay/show.do"), String.valueOf("pass"));
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		Integer maxmum = Constants.DEFAULT_MAXNUM;
		if (date == null) {
			List<String> dateList = this.platformsMonthsOutlayService.readAllPlatformsMonthsOutlayDate();
			if (dateList != null && dateList.size() > 0) {
				date = dateList.get(0);
			}
		}
		date = date == null ? String.format("%s-%s", String.format("%tY", new Date()), String.format("%tm", new Date())) : date;
		String dateMsg = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("isEdit", isEdit);
		mav.addObject("mId", mId);
		mav.addObject("date", date);
		mav.addObject("dateMsg", dateMsg);
		mav.addObject("maxmum", maxmum);
		mav.addObject("platId", platId);
		mav.addObject("attsList", attsList);
		mav.addObject("platList", platList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		mav.addObject("online", LineGenres.ONLINE);
		return mav;
	}

	@RequestMapping(value = "/managerOffline/show", method = RequestMethod.GET)
	public ModelAndView managerOffline(Integer platId, Integer mId, Integer branchs, String date, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/platManagerOutlay");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("outlay/show.do"), "pass", "reject", "audit", "finalpass");
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		boolean isEdit = super.isHaveAttribute(request, String.valueOf("outlay/show.do"), String.valueOf("pass"));
		Integer maxmum = Constants.DEFAULT_MAXNUM;
		date = date == null ? String.format("%s-%s", String.format("%tY", new Date()), String.format("%tm", new Date())) : date;
		String dateMsg = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("isEdit", isEdit);
		mav.addObject("mId", mId);
		mav.addObject("date", date);
		mav.addObject("dateMsg", dateMsg);
		mav.addObject("maxmum", maxmum);
		mav.addObject("platId", platId);
		mav.addObject("attsList", attsList);
		mav.addObject("platList", platList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		mav.addObject("online", LineGenres.OFFLINE);
		return mav;
	}

	/**
	 * 功能：获取所有
	 * */
	@ResponseBody
	@RequestMapping(value = "/managerOutlay/readAllPlatformsManagerOutlay", method = RequestMethod.POST)
	public ResultMsg readAllPlatformsManagerOutlay(HttpServletRequest request, String platId, Integer branchs, Integer mId, String date, String online) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		msg.setResultMessage("error");
		PlatformsMonthsOutlay bean = this.platformsMonthsOutlayService.readPlatformsMonthsOutlay(platId, mId, date, super.searchFilter(request, branchs));
		if (bean != null && bean.getId() != null) {
			List<PlatformsManagerOutlayBean> list = this.ManagerOutlayService.readAllPlatformsManagerOutlay(platId, Integer.valueOf(bean.getId()), date, online,
					super.searchFilter(request, branchs));
			if (list != null && list.size() > 0) {
				msg.setData(list);
				msg.setResultMessage("success");
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能：修改
	 * */
	@RequestMapping(value = "/managerOutlay/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, @RequestBody List<PlatformsManagerOutlayBean> listBean) throws Exception {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/platManagerOutlay");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("managerOutlay/show.do"));
		mav.addObject("attsList", attsList);
		List<PlatformsManagerOutlay> list = new ArrayList<PlatformsManagerOutlay>();
		if (listBean != null && listBean.size() > 0) {
			for (PlatformsManagerOutlayBean bean : listBean) {
				PlatformsManagerOutlay outlay = new PlatformsManagerOutlay();
				outlay.setId(bean.getId());
				outlay.setAttendance(bean.getAttendance());
				outlay.setPushMoney(bean.getPushMoney());
				outlay.setOtherAllowance(bean.getOtherAllowance());
				outlay.setDeduct(bean.getDeduct());
				outlay.setRemarks(bean.getRemarks());
				list.add(outlay);
			}
		}
		if (this.ManagerOutlayService.updateManagerOutlay(list)) {
			mav.addObject("result", "success");
		}
		return mav;
	}

}
