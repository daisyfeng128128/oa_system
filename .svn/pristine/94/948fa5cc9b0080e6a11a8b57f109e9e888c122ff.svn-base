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
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.PlatformsAudits;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.ResultMsg;

/**
 * 功能： 营收总表
 * */
@Controller
@RequestMapping("/outlay")
public class PlatformsMonthsOutlayController extends BaseController {

	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IPlatformsMonthsOutlayService platformsMonthsOutlayService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView outlay(Integer platId, Integer mId, String date, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/platMonthsOutlay");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("outlay/show.do"), "impr");
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
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
		mav.addObject("platList", platList);
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
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
			PlatformsAudits audits = this.platformsMonthsOutlayService.readPlatfromsAuditesByMid(mId);
			PlatformsMonthsOutlay outlay = this.platformsMonthsOutlayService.readPlatformsMonthsOutlayByHead(mId, branchs, platId, date);
			String auditMsg = String.valueOf("等待提交");
			if (outlay != null) {
				switch (outlay.getAudit()) {
				case WRITE:
					auditMsg = String.valueOf("等待提交");
					break;
				case AUDIT:
					auditMsg = String.valueOf("等待业务负责人审批");
					break;
				case PASS:
					auditMsg = String.valueOf("业务负责人审批通过,等待财务负责人审批");
					break;
				case FAILURE:
					String failure = String.valueOf("");
					if (audits != null && audits.getRejectMsg() != null)
						failure = audits.getRejectMsg();
					auditMsg = String.valueOf("审批驳回|" + failure);
					break;
				case FINALPASS:
					auditMsg = String.valueOf("财务负责人审批通过");
					break;
				default:
					break;
				}
				msg.setExData(outlay);
			}
			Object[] rest = new Object[2];
			List<ResultMsg> dateList = new ArrayList<ResultMsg>();
			List<String> $dateList = this.platformsMonthsOutlayService.readAllPlatformsMonthsOutlayDate();
			if (dateList != null) {
				for (String text : $dateList) {
					ResultMsg $msg = new ResultMsg();
					$msg.setData("outlay/show.do");
					$msg.setResultMessage(text);
					dateList.add($msg);
				}
			}
			List<RoleDetailsAtts> areaList = super.platformsRoleDetails(request, String.valueOf("branchOff/show.do"));
			rest[0] = dateList;
			rest[1] = areaList;
			msg.setData(rest);
			msg.setResultMessage(auditMsg);
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
			PlatformsMonthsOutlayBean bean = this.platformsMonthsOutlayService.readAllPlatformsMonthsOutlay(platId, mId, date, super.searchFilter(request, branchs));
			if (bean != null) {
				msg.setData(bean);
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能：保存营收总表数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultMsg savePlatformsMonthsOutlay(@RequestBody PlatformsMonthsOutlayBean bean) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (this.platformsMonthsOutlayService.savePlatformsMonthsOutlay(bean)) {
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能：财务审批
	 * */
	@ResponseBody
	@RequestMapping(value = "/finalpass", method = RequestMethod.POST)
	public ResultMsg finalpassPlatformsMonthsOutlay(@RequestBody PlatformsMonthsOutlayBean bean, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		if (super.isUserLoging(request, bean.getLastMonths())) {
			if (this.platformsMonthsOutlayService.updatePlatformsMonthsOutlay(bean, "finalpass")) {
				msg.setResultStatus(200);
			}
		} else {
			msg.setResultMessage("登陆密码有误!");
			msg.setResultStatus(100);
		}
		return msg;
	}

	/**
	 * 功能：业务审批
	 * */
	@ResponseBody
	@RequestMapping(value = "/pass", method = RequestMethod.POST)
	public ResultMsg passPlatformsMonthsOutlay(@RequestBody PlatformsMonthsOutlayBean bean) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (this.platformsMonthsOutlayService.updatePlatformsMonthsOutlay(bean, "pass")) {
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能：审核驳回
	 * */
	@ResponseBody
	@RequestMapping(value = "/reject", method = RequestMethod.POST)
	public ResultMsg rejectPlatformsMonthsOutlay(@RequestBody PlatformsMonthsOutlayBean bean) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (this.platformsMonthsOutlayService.updatePlatformsMonthsOutlay(bean, "reject")) {
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能：提交审核
	 * */
	@ResponseBody
	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	public ResultMsg auditPlatformsMonthsOutlay(@RequestBody PlatformsMonthsOutlayBean bean) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (this.platformsMonthsOutlayService.updatePlatformsMonthsOutlay(bean, "audit")) {
			msg.setResultStatus(200);
		}
		return msg;
	}

}
