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
import com.baofeng.oa.bean.PlatformsMonthsOutlayBean;
import com.baofeng.oa.bean.PlatformsMonthsTotalOutlayBean;
import com.baofeng.oa.entity.PlatformsMonthsTotalOutlay;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.oa.service.IPlatformsMonthsTotalOutlayService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.ResultMsg;

/**
 * 功能： 月报总表
 * */
@Controller
@RequestMapping("/totaloutlay")
public class PlatformsMonthsTotalOutlayController extends BaseController {

	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IPlatformsMonthsTotalOutlayService platformsMonthsTotalOutlayService;
	@Autowired
	private IPlatformsMonthsOutlayService platformsMonthsOutlayService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView outlay(Integer platId, Integer mId, String date, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/platMonthsTotalOutlay");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("totaloutlay/show.do"));
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		if (date == null) {
			List<String> dateList = this.platformsMonthsOutlayService.readAllPlatformsMonthsOutlayDate();
			if (dateList != null && dateList.size() > 0) {
				date = dateList.get(0);
			}
		}
		date = date == null ? String.format("%s-%s", String.format("%tY", new Date()), String.format("%tm", new Date())) : date;
		String dateMsg = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		mav.addObject("mId", mId);
		mav.addObject("date", date);
		mav.addObject("dateMsg", dateMsg);
		mav.addObject("platId", platId);
		mav.addObject("platList", platList);
		mav.addObject("attsList", attsList);

		return mav;
	}
	
	/**
	 * 功能：获取头部数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/readBaseTabCommon", method = RequestMethod.POST)
	public ResultMsg readBaseTabCommon(HttpServletRequest request, String platId, Integer mId, String date, Integer branchs) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (platId != null && platId.trim().length() > 0 && date != null && date.trim().length() > 0) {
			PlatformsMonthsTotalOutlay outlay = this.platformsMonthsTotalOutlayService.readPlatformsMonthsOutlayByHead(mId, branchs, platId, date);
			if (outlay != null) {
				msg.setExData(outlay);
			}
			Object[] rest = new Object[2];
			List<ResultMsg> dateList = new ArrayList<ResultMsg>();
			List<String> $dateList = this.platformsMonthsTotalOutlayService.readAllPlatformsMonthsOutlayDate();
			if (dateList != null) {
				for (String text : $dateList) {
					ResultMsg $msg = new ResultMsg();
					$msg.setData("totaloutlay/show.do");
					$msg.setResultMessage(text);
					dateList.add($msg);
				}
			}
			
			List<RoleDetailsAtts> areaList = super.platformsRoleDetails(request, String.valueOf("branchOff/show.do"));
			rest[0] = dateList;
			rest[1] = areaList;
			msg.setData(rest);
			msg.setResultStatus(200);
		}
		return msg;
	}
	
	/**
	 * 功能：获取所有
	 * */
	@ResponseBody
	@RequestMapping(value = "/readAllPlatformsMonthsOutlay", method = RequestMethod.POST)
	public ResultMsg readAllPlatformsMonthsOutlay(HttpServletRequest request, String platId, Integer mId, Integer branchs, String date) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (platId != null && platId.trim().length() > 0 && date != null && date.trim().length() > 0) {
			PlatformsMonthsTotalOutlayBean bean1 = this.platformsMonthsTotalOutlayService.readAllPlatformsMonthsOutlay(platId, mId, date, super.searchFilter(request, branchs));
			if (bean1 != null) {
				msg.setData(bean1);
				msg.setResultStatus(200);
			}
		}
		return msg;
	}
	
	/**
	 * 功能：保月报总表数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultMsg savePlatformsMonthsOutlay(@RequestBody PlatformsMonthsOutlayBean bean) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (this.platformsMonthsTotalOutlayService.savePlatformsMonthsOutlay(bean)) {
			msg.setResultStatus(200);
		}
		return msg;
	}
	

}
