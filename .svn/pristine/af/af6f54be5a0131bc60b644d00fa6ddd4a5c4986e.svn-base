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
import com.baofeng.oa.bean.FinSalariesTalentBean;
import com.baofeng.oa.entity.FinSalariesTalent;
import com.baofeng.oa.service.IFinSalariesTalentService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

/**
 * 星探工资
 * */
@Controller
@RequestMapping("/finSalariesTalent")
public class FinSalariesTalentController extends BaseController {
	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private IFinSalariesTalentService finSalariesTalentService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(String date, Integer all, Integer type, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/finSalariesTalent");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("finSalaries/actshow.do"));
		if (date == null)
			date = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		String vdate = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		String $current = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		all = all != null ? all : 0;
		mav.addObject("isEdit", true);
		if (!date.equals($current)) {
			mav.addObject("isEdit", false);
		}
		mav.addObject("all", all);
		mav.addObject("date", date);
		mav.addObject("vdate", vdate);
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 功能 ： 获取所有线下艺人或主播
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(HttpServletRequest request, int pageSize, int curPage, String sortName, String sortOrder, String date, String fastArg, Integer all) {
		NPageResult<?> pages = this.finSalariesTalentService.readPages(pageSize, curPage, sortName, sortOrder, date, fastArg, all, super.searchFilter(request));
		return pages;
	}

	/**
	 * 功能 ：保存星探工资
	 * */
	@ResponseBody
	@RequestMapping("/save")
	public ResultMsg save(@RequestBody List<FinSalariesTalentBean> listBean, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		if (this.finSalariesTalentService.save(branchs, listBean)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能： 发放工资
	 * */
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
			if (this.finSalariesTalentService.saveFinSalariesTalentByList(list)) {
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能： 计算工资
	 * */
	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ResultMsg check(String date, String type) {
		ResultMsg rsg = new ResultMsg();
		rsg.setResultStatus(100);
		// 计算星探
		FinSalariesTalent post = new FinSalariesTalent();
		post.setCreateDT(Constants.convert(date, Constants.format7));
		this.workManagers.onEvents(WorkNames.FINSALARIESTALENT, post);
		return rsg;
	}

	/** 详细 */
	@ResponseBody
	@RequestMapping("/edit")
	public FinSalariesTalentBean edit(String id) {
		if (id != null && id.trim().length() > 0) {
			FinSalariesTalentBean bean = this.finSalariesTalentService.readSalariesTalent(id);
			return bean;
		}
		return null;
	}

	/** 保存 */
	@ResponseBody
	@RequestMapping("/saveFin")
	public ResultMsg saveFin(HttpServletRequest request, String id, String taSalary, String remarks, String otherSubsidy, String otherDeduct) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		if (id != null && id.trim().length() > 0) {
			FinSalariesTalent talent = new FinSalariesTalent();
			talent.setBranchs(branchs);
			if (id != null && id.trim().length() > 0) {
				talent.setId(Integer.valueOf(id));
			}
			talent.setTaSalary(taSalary == null ? new BigDecimal(0.00) : taSalary.equals("") ? new BigDecimal(0.00) : new BigDecimal(taSalary));
			talent.setRemarks(remarks);
			talent.setOtherDeduct(otherDeduct == null ? new BigDecimal(0.00) : otherDeduct.equals("") ? new BigDecimal(0.00) : new BigDecimal(otherDeduct));
			talent.setOtherSubsidy(otherSubsidy == null ? new BigDecimal(0.00) : otherSubsidy.equals("") ? new BigDecimal(0.00) : new BigDecimal(otherSubsidy));
			if (this.finSalariesTalentService.addFinSalariesTalent(talent)) {
				msg.setResultMessage("success");
				msg.setResultStatus(200);
			}
		}
		return msg;
	}
}
