package com.baofeng.oa.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.bean.FinancialOPExpenditureBean;
import com.baofeng.oa.bean.FinancialReportsBean;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.FinancialNReports;
import com.baofeng.oa.entity.FinancialOPExpenditure;
import com.baofeng.oa.entity.FinancialReports;
import com.baofeng.oa.entity.PlatformsMonths;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IFinancialReportsService;
import com.baofeng.oa.service.IPlatformsMonthsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.ResultMsg;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

/**
 * 功能： 财务报表
 * */
@Controller
@RequestMapping("/financialReports")
public class FinancialReportsController extends BaseController {
	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IFinancialReportsService financialReportsService;
	@Autowired
	private IPlatformsMonthsService platformsMonthsService;

	/** 财务报表 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView devFinState(String date, Integer branchs, Integer type, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/finDetails");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("financialReports/show.do"));
		if (date == null)
			date = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		String dateMsg = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		String $current = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();

		mav.addObject("isEdit", true);
		if (!date.equals($current)) {
			mav.addObject("isEdit", false);
		}
		mav.addObject("date", date);
		mav.addObject("type", type);
		mav.addObject("dateMsg", dateMsg);
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/** 运营支出 */
	@RequestMapping(value = "/showOPE", method = RequestMethod.GET)
	public ModelAndView showOPE(String date, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/financialOPExpenditure");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("financialReports/showOPE.do"));
		if (date == null)
			date = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		String vdate = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		String $current = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		mav.addObject("isEdit", true);
		if (!date.equals($current)) {
			mav.addObject("isEdit", false);
		}
		mav.addObject("date", date);
		mav.addObject("vdate", vdate);
		mav.addObject("attsList", attsList);
		return mav;
	}

	/** 获取数据 */
	@ResponseBody
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public Map<String, String> devFinState1(String date, Integer branchs, HttpServletRequest request) {
		Map<String, String> map = this.financialReportsService.readFinancialReportsByDate(date, super.searchFilter(request, branchs));
		return map;
	}

	/**
	 * 功能：修改
	 * */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultMsg save(@RequestBody FinancialReportsBean beanList, HttpServletRequest request) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Integer id = beanList.getId();
		FinancialReports financialReports = this.financialReportsService.readFinancialReports(id);
		// 计算公司基本运营总计
		BigDecimal oldOutlay = new BigDecimal(0.0);
		if (financialReports != null && financialReports.getDetails() != null) {
			for (FinancialOPExpenditure post : financialReports.getDetails()) {
				oldOutlay = oldOutlay.add(post.getExpenditure());
			}
		}
		BigDecimal newOutlay = new BigDecimal(0.0);
		List<FinancialOPExpenditure> list = new ArrayList<FinancialOPExpenditure>();
		if (beanList.getDetails() != null) {
			for (FinancialOPExpenditureBean bean : beanList.getDetails()) {
				if ((bean.getExpenditure() != null && bean.getExpenditure().trim().length() > 0 && Double.valueOf(bean.getExpenditure()) != null && Double.valueOf(bean
						.getExpenditure()) >= Double.valueOf(0.0)) || (bean.getRemarks() != null && bean.getRemarks().trim().length() > 0)) {
					FinancialOPExpenditure post = this.financialReportsService.readFinancialOPExpenditure(bean.getName(), id.toString());
					if (post != null) {
						post.setExpenditure(new BigDecimal(bean.getExpenditure() != null ? bean.getExpenditure() : "0"));
						post.setRemarks(bean.getRemarks());
					} else {
						FinancialOPExpenditure $post = new FinancialOPExpenditure();
						$post.setExpenditure(new BigDecimal(bean.getExpenditure() != null ? bean.getExpenditure() : "0"));
						$post.setName(bean.getName());
						$post.setRemarks(bean.getRemarks());
						$post.setReports(financialReports);
						post = $post;
					}
					newOutlay = newOutlay.add(post.getExpenditure());
					list.add(post);
				}
			}
		}
		// 本月总支出
		BigDecimal currentExpenditure = financialReports.getCurrentExpenditure();
		currentExpenditure = currentExpenditure.subtract(oldOutlay);
		currentExpenditure = currentExpenditure.add(newOutlay);
		financialReports.setCurrentExpenditure(currentExpenditure);

		/** 本月净利润 */
		BigDecimal currentProfit = new BigDecimal(0.0);
		currentProfit = financialReports.getCurrentIncome().subtract(financialReports.getCurrentExpenditure());
		financialReports.setCurrentProfit(currentProfit);
		/** 公司环比增长 */
		BigDecimal cp_growthRate = new BigDecimal(0.0);
		// 计算环比增长 = （本月净利润 - 上月净利润）/上月净利润
		if (financialReports.getCurrentIncome().doubleValue() > Double.valueOf(0.0).doubleValue()
				&& financialReports.getLastProfit().doubleValue() > Double.valueOf(0.0).doubleValue())
			cp_growthRate = financialReports.getCurrentProfit().subtract(financialReports.getLastProfit()).divide(financialReports.getLastProfit(), 4, BigDecimal.ROUND_HALF_UP)
					.multiply(new BigDecimal(100));
		if (financialReports.getLastProfit().doubleValue() == Double.valueOf(0.0).doubleValue()
				&& financialReports.getCurrentIncome().doubleValue() > Double.valueOf(0.0).doubleValue())
			cp_growthRate = new BigDecimal(100);
		financialReports.setCp_growthRate(cp_growthRate);
		if (this.financialReportsService.save(list)) {
			if (this.financialReportsService.addFinancialReports(financialReports)) {
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能：校险
	 * */
	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ResultMsg check(String date, HttpServletRequest request) {
		ResultMsg rsg = new ResultMsg();
		rsg.setResultStatus(100);
		Integer branchs = super.getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		FinancialNReports post = new FinancialNReports();
		post.setBranchs(branchs);
		post.setCreateDT(Constants.convert(date, Constants.format7));
		this.workManagers.onEvents(WorkNames.CHECK_SALARY_FINANCIALREPORTS, post);
		return rsg;
	}

	/** 导出excel */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, String date) throws Exception {
		FinancialNReports financialReports = this.financialReportsService.readReportsByDate(date, super.searchFilter(request));
		// 查询平台流水
		List<PlatformsMonths> list = this.platformsMonthsService.readAllPlatformsMonthsByDate(date);
		HSSFWorkbook wb = this.financialReportsService.export(financialReports, list);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + new Date().toString() + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
}
