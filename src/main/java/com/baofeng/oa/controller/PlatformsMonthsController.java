package com.baofeng.oa.controller;

import java.math.BigDecimal;
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
import com.baofeng.oa.bean.PlatformsMonthsBean;
import com.baofeng.oa.entity.BaseEnums.Audit;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.PlatformsMonths;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.oa.service.IPlatformsMonthsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 功能： 平台流水
 * */
@Controller
@RequestMapping("/months")
public class PlatformsMonthsController extends BaseController {

	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IPlatformsMonthsService platformsMonthsService;
	@Autowired
	private IPlatformsMonthsOutlayService platformsMonthsOutlayService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(Integer platId, String date, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/platMonths");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("months/show.do"));
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		boolean isSee = super.isHaveAttribute(request, String.valueOf("months/show.do"), String.valueOf("topUp"));
		if (date == null) {
			List<String> dateList = this.platformsMonthsOutlayService.readAllPlatformsMonthsOutlayDate();
			if (dateList != null && dateList.size() > 0) {
				date = dateList.get(0);
			}
		}
		boolean isInput = super.isHaveAttribute(request, String.valueOf("outlay/show.do"), String.valueOf("pass"));
		date = date == null ? String.format("%s-%s", String.format("%tY", new Date()), String.format("%tm", new Date())) : date;
		String dateMsg = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		String $current = String.format("%s-%s", String.format("%tY", new Date()), String.format("%tm", new Date()));
		Date date1 = Constants.convert(date, Constants.format7);
		Date date2 = Constants.convert(date + "-" + CustomDateUtils.Handler.daysInMonth(date) + " 23:59:59", Constants.format2);
		List<PlatformsMonthsOutlay> listOut = this.platformsMonthsOutlayService.readAllPlatformsMonthsOutlayByDate(date1, date2);
		boolean flag = false;
		if (listOut != null && listOut.size() > 0) {
			for (PlatformsMonthsOutlay post : listOut) {
				if (post.getAudit() != Audit.PASS) {
					if (post.getAudit() == Audit.AUDIT && isInput) {
						flag = true;
					} else if (post.getAudit() != Audit.AUDIT) {
						flag = true;
					}
				}
			}
		}
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("isSee", isSee);
		mav.addObject("isEdit", true);
		mav.addObject("flag", flag);
		if (!date.equals($current)) {
			mav.addObject("isEdit", false);
		}
		mav.addObject("date", date);
		mav.addObject("dateMsg", dateMsg);
		mav.addObject("platId", platId);
		mav.addObject("platList", platList);
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/**
	 * 功能：获取日期列表
	 * */
	@ResponseBody
	@RequestMapping(value = "/readDate", method = RequestMethod.POST)
	public ResultMsg readBaseTabCommon() {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		List<ResultMsg> restList = new ArrayList<ResultMsg>();
		List<String> dateList = this.platformsMonthsOutlayService.readAllPlatformsMonthsOutlayDate();
		if (dateList != null) {
			for (String text : dateList) {
				ResultMsg $msg = new ResultMsg();
				$msg.setData("months/show.do");
				$msg.setResultMessage(text);
				restList.add($msg);
			}
			msg.setData(restList);
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能：获取所有
	 * */
	@ResponseBody
	@RequestMapping(value = "/readAllPlatformsMonths", method = RequestMethod.POST)
	public NPageResult<?> readAllPlatformsMonths(int pageSize, int curPage, String sortName, String sortOrder, String date, Integer branchs, HttpServletRequest request) {
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		return this.platformsMonthsService.readAllPlatformsMonths(pageSize, curPage, sortName, sortOrder, date, super.searchFilter(request, branchs), platList);
	}

	/**
	 * 功能：获取所有大型支出
	 * */
	@ResponseBody
	@RequestMapping(value = "/readAllMonthsOutlay", method = RequestMethod.POST)
	public NPageResult<?> readAllMonthsOutlay(int pageSize, int curPage, String sortName, String sortOrder, String date, Integer branchs, HttpServletRequest request) {
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		NPageResult<?> pages = this.platformsMonthsService.readAllMonthsOutlay(pageSize, curPage, sortName, sortOrder, date, super.searchFilter(request, branchs), platList);
		return pages;
	}

	/**
	 * 功能：修改
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, @RequestBody List<PlatformsMonthsBean> listBean) throws Exception {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/platMonths");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("months/show.do"));
		mav.addObject("attsList", attsList);
		List<PlatformsMonths> list = new ArrayList<PlatformsMonths>();
		if (listBean != null && listBean.size() > 0) {
			for (PlatformsMonthsBean bean : listBean) {
				PlatformsMonths months = new PlatformsMonths();
				months.setId(Integer.valueOf(bean.getId()));
				if (bean.getTaxSubsidy() != null)
					months.setTaxSubsidy(new BigDecimal(bean.getTaxSubsidy()));
				list.add(months);
			}
		}
		if (this.platformsMonthsService.updateIncome(list)) {
			mav.addObject("result", "success");
		}
		return mav;
	}

	/**
	 * 功能：修改充值
	 * */
	@RequestMapping(value = "/saveTop", method = RequestMethod.POST)
	public ModelAndView saveTop(HttpServletRequest request, @RequestBody List<PlatformsMonthsBean> listBean) throws Exception {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/platMonths");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("months/show.do"));
		mav.addObject("attsList", attsList);
		List<PlatformsMonths> list = new ArrayList<PlatformsMonths>();
		if (listBean != null && listBean.size() > 0) {
			for (PlatformsMonthsBean bean : listBean) {
				PlatformsMonths months = new PlatformsMonths();
				months.setId(Integer.valueOf(bean.getId()));
				if (bean.getChIncome() != null)
					months.setChIncome(new BigDecimal(bean.getChIncome()));
				if (bean.getChOutlay() != null)
					months.setChOutlay(new BigDecimal(bean.getChOutlay()));
				if (bean.getRemarks() != null) {
					months.setRemarks(bean.getRemarks());
				}
				list.add(months);
			}
		}
		if (this.platformsMonthsService.updateTopUp(list)) {
			mav.addObject("result", "success");
		}
		return mav;
	}

}
