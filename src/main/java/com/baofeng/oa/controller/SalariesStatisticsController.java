package com.baofeng.oa.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.bean.SalariesStatisticsBean;
import com.baofeng.oa.service.ISalariesStatisticsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.ResultMsg;

/**
 * 功能：部门统计
 * */
@Controller
@RequestMapping("/salariesStatistics")
public class SalariesStatisticsController extends BaseController {
	@Autowired
	private ISalariesStatisticsService salariesStatisticsService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(String date, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/salariesStatistics");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("salariesStatistics/show.do"));
		if (date == null)
			date = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		String vdate = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		String $current = String.format("%s-%s", String.format("%tY", DateUtils.addMonths(new Date(), -1)),
				String.format("%tm", DateUtils.addMonths(new Date(), -1)));
		mav.addObject("isEdit", true);
		if (!date.equals($current)) {
			mav.addObject("isEdit", false);
		}
		mav.addObject("date", date);
		mav.addObject("vdate", vdate);
		mav.addObject("attsList", attsList);
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/readPages", method = RequestMethod.GET)
	public ResultMsg readPages(String date, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		SalariesStatisticsBean bean = this.salariesStatisticsService.readPages(date1, date2, super.searchFilter(request));
		if (bean != null) {
			msg.setData(bean);
			msg.setResultStatus(200);
			msg.setResultMessage("success");
		}
		return msg;
	}

}
