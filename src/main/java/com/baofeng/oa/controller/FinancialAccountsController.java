package com.baofeng.oa.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.baofeng.oa.bean.FinancialAccountsBean;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IFinancialAccountsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.ResultMsg;

/**
 * 财务支出
 * */
@Controller
@RequestMapping("/finCheck")
public class FinancialAccountsController extends BaseController {

	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IFinancialAccountsService financialAccountsService;

	/** 财务支出 */
	@RequestMapping(value = "/showOPE", method = RequestMethod.GET)
	public ModelAndView showOPE(String date, Integer branchs, Integer type, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/financialCheck");
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("finCheck/showOPE.do"));
		date = date == null ? Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7) : date;
		String dateMsg = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		Map<String, Map<String, Object>> master = this.financialAccountsService.readAllFinancialAccountsNew("master", date, super.searchFilter(request, branchs));
		Map<String, Map<String, Object>> other = this.financialAccountsService.readAllFinancialAccountsNew("other", date, super.searchFilter(request, branchs));
		mav.addObject("attsList", attsList);
		mav.addObject("platList", platList);
		mav.addObject("master", master.values());
		mav.addObject("other", other.values());
		mav.addObject("masterTotal", this.financialAccountsService.sumFinancialAccounts(master, "total"));
		mav.addObject("masterTotal_salaries", this.financialAccountsService.sumFinancialAccounts(master, "salraies", "social", "accumulation", "total"));
		mav.addObject("otherTotal", this.financialAccountsService.sumFinancialAccounts(other, "total"));
		mav.addObject("date", date);
		mav.addObject("type", type);
		mav.addObject("dateMsg", dateMsg);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/**
	 * 功能：添加支出项
	 * */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultMsg add(HttpServletRequest request, String name, String subjects, String money, String date) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		msg.setResultMessage("error");
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		if (name != null && name.trim().length() > 0 && subjects != null && subjects.trim().length() > 0) {
			if (!this.financialAccountsService.findFinancialAccounts(name, subjects, date)) {
				if (this.financialAccountsService.addFinancialAccounts(name, subjects, money, date, branchs)) {
					msg.setResultStatus(200);
					msg.setResultMessage("success");
				}
			}
		}
		return msg;
	}

	/**
	 * 功能：修改
	 * */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultMsg save(HttpServletRequest request, @RequestBody List<FinancialAccountsBean> paramsList) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		msg.setResultMessage("error");
		if (this.financialAccountsService.addFinancialAccounts(paramsList, searchFilter(request))) {
			msg.setResultStatus(200);
			msg.setResultMessage("success");
		}
		return msg;
	}
}
