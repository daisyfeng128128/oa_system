package com.baofeng.oa.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.entity.FinSalaryOnline;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.oa.service.ISalaryOnlineService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;
import com.baofeng.utils.SearchFilter;

/***
 * 功能 ： 平台场控支出
 */
@Controller
@RequestMapping("/salaryOnline")
public class SalaryOnlineController extends BaseController {
	@Autowired
	private ISalaryOnlineService salaryOnlineService;
	@Autowired
	private IPlatformsService platformsService;

	/** 线上艺人工资 */
	@RequestMapping(value = "/devFinAOnline", method = RequestMethod.GET)
	public ModelAndView devFinAOnline(String date, String see, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/finSalaryActOnline");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("salaryOffline/devFinAOff.do"));
		if (date == null)
			date = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		List<Platforms> Allplatforms = this.platformsService.findAllPlatforms(super.searchFilter(request));

		String html = "";
		for (Platforms post : Allplatforms) {
			html += " <th w_index=\"plat" + post.getId() + "\" w_align=\"center\" width=\"1%;\">" + post.getPlatName() + "</th>";
		}
		String vdate = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		String $current = String.format("%s-%s", String.format("%tY", DateUtils.addMonths(new Date(), -1)),
				String.format("%tm", DateUtils.addMonths(new Date(), -1)));
		mav.addObject("isEdit", true);
		if (!date.equals($current)) {
			mav.addObject("isEdit", false);
		}
		mav.addObject("see", see);
		mav.addObject("vdate", vdate);
		mav.addObject("html", html);
		mav.addObject("date", date);
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 功能：获取所有
	 * */
	@ResponseBody
	@RequestMapping(value = "/readAllSalaryOnline", method = RequestMethod.POST)
	public NPageResult<?> readAllSalaryOnline(int pageSize, int curPage, String sortName, String sortOrder, String date, String see, String fastArg) {
		SearchFilter filter = new SearchFilter();
		NPageResult<?> pages = this.salaryOnlineService.readAllSalaryOnline(pageSize, curPage, sortName, sortOrder, date, see, filter, fastArg);

		return pages;
	}

	/**
	 * 功能：计算工资
	 * */
	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ResultMsg check(HttpServletRequest request, String date) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (this.salaryOnlineService.check(date)) {
			msg.setResultStatus(200);
			msg.setResultMessage("success");
		}
		return msg;
	}

	/**
	 * 功能：修改
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, @RequestBody List<FinSalaryOnline> listOn) throws Exception {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/finSalaryActOnline");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("salaryOnline/devFinAOnline.do"));
		mav.addObject("attsList", attsList);
		List<FinSalaryOnline> list = new ArrayList<FinSalaryOnline>();
		if (listOn != null && listOn.size() > 0) {
			for (FinSalaryOnline post : listOn) {
				FinSalaryOnline online = this.salaryOnlineService.readFinOnline(post.getId());
				online.setRemarks(post.getRemarks());
				online.setBasicSalary(post.getBasicSalary());
				online.setOtherSubsidy(post.getOtherSubsidy());
				online.setOtherDeduct(post.getOtherDeduct());

				// 计算实发工资
				BigDecimal realitySalary = new BigDecimal(0);
				realitySalary = online.getPushMoneySalary().doubleValue() > online.getBasicSalary().doubleValue() ? online.getPushMoneySalary() : online
						.getBasicSalary();
				realitySalary = realitySalary.subtract(online.getDeductTax()).add(online.getOtherSubsidy()).subtract(online.getOtherDeduct());
				online.setRealitySalary(realitySalary);

				// 计算艺人盈亏
				BigDecimal netProfit = online.getTotalIncome();
				netProfit = netProfit.subtract(realitySalary).add(online.getOtherDeduct());
				online.setNetProfit(netProfit);
				list.add(online);
			}
		}
		if (this.salaryOnlineService.updateSalary(list)) {
			mav.addObject("result", "success");
		}
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "grant", method = RequestMethod.POST)
	public ResultMsg grant(String id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.trim().length() > 0) {
			List<String> list = null;
			if (id.indexOf(",") != -1) {
				String[] ids = id.split(",");
				list = Arrays.asList(ids);
			} else {
				list = new ArrayList<String>();
				list.add(id);
			}
			if (this.salaryOnlineService.saveFinSalaryOfflineByList(list)) {
				msg.setResultStatus(200);
			}
		}
		return msg;
	}
}
