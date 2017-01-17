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
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IFinEarningsIncomeService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;

/**
 * 功能：收益明细
 * */
@Controller
@RequestMapping("finEarningsIncome")
public class FinEarningsIncomeController extends BaseController {

	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IFinEarningsIncomeService finEarningsIncomeService;
	@Autowired
	private IPlatformsService platformsService;

	/** 收益明细 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(String date, String type, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/finEarningsIncome");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("finEarningsIncome/show.do"));
		if (date == null)
			date = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		List<Platforms> list = this.platformsService.findAllPlatforms(super.searchFilter(request));
		String html = "";
		if (list != null && list.size() > 0) {
			for (Platforms plat : list) {
				html += " <th w_index=\"plat" + plat.getId() + "\"  w_sort=\"plat" + plat.getId() + "\"   w_align=\"center\" width=\"1%;\">" + plat.getPlatName() + "</th>";
			}
		}
		type = type == null ? "off" : type;
		String dateMsg = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		String $current = String.format("%s-%s", String.format("%tY", DateUtils.addMonths(new Date(), -1)), String.format("%tm", DateUtils.addMonths(new Date(), -1)));

		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();

		mav.addObject("isEdit", true);
		if (!date.equals($current)) {
			mav.addObject("isEdit", false);
		}
		mav.addObject("type", type);
		mav.addObject("date", date);
		mav.addObject("dateMsg", dateMsg);
		mav.addObject("attsList", attsList);
		mav.addObject("html", html);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(HttpServletRequest request, int pageSize, int curPage, String sortName, String sortOrder, String date, Integer branchs, String fastArg, String type) {
		return this.finEarningsIncomeService.readPages(pageSize, curPage, sortName, sortOrder, date, fastArg, type, super.searchFilter(request, branchs));
	}
}
