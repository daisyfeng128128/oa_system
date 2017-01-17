package com.baofeng.oa.controller;

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
import com.baofeng.oa.bean.PlatformsActivityOutlayBean;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.PlatformsActivityOutlay;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IPlatformsActivityOutlayService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.ResultMsg;

/**
 * 功能： 运营支出
 * */
@Controller
@RequestMapping("/activityOutlay")
public class PlatformsActivityOutlayController extends BaseController {

	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IPlatformsActivityOutlayService activityOutlayService;
	@Autowired
	private IPlatformsMonthsOutlayService platformsMonthsOutlayService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(String platId, Integer mId, Integer branchs, String date, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/activityOutlay");
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("outlay/show.do"), "impr", "pass", "reject", "audit", "finalpass");
		boolean isEdit = super.isHaveAttribute(request, String.valueOf("outlay/show.do"), String.valueOf("pass"));
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
		mav.addObject("platId", platId);
		mav.addObject("attsList", attsList);
		mav.addObject("platList", platList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/**
	 * 功能：获取所有
	 * */
	@ResponseBody
	@RequestMapping(value = "/readAllPlatformsActivityOutlay", method = RequestMethod.POST)
	public List<PlatformsActivityOutlayBean> readAllPlatformsActivityOutlay(HttpServletRequest request, String platId, Integer mId, Integer branchs, String date) {
		if (platId != null && platId.trim().length() > 0 && date != null && date.trim().length() > 0) {
			PlatformsMonthsOutlay bean = this.platformsMonthsOutlayService.readPlatformsMonthsOutlay(platId, mId, date, searchFilter(request, branchs));
			if (bean != null) {
				return this.activityOutlayService.readAllPlatformsActivityOutlay(platId, Integer.valueOf(bean.getId()), date, super.searchFilter(request, branchs));
			}
		}
		return null;
	}

	/**
	 * 功能：添加修改
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@RequestBody PlatformsActivityOutlay post, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/activityOutlay");
		if (this.activityOutlayService.savePlatformsActivityOutlay(post)) {
			mav.addObject("result", "success");
		}
		return mav;
	}

	/**
	 * 功能：删除
	 * */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResultMsg delete(Integer id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("errors");
		if (this.activityOutlayService.deleteById(id)) {
			msg.setResultStatus(200);
			msg.setResultMessage("success");
		}
		return msg;
	}

}
