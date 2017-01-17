package com.baofeng.oa.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.entity.BigTopDetails;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.service.IBigtopService;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 功能：大型充值
 * */
@Controller
@RequestMapping("/bigtop")
public class BigTopController extends BaseController {

	@Autowired
	private IBigtopService bigtopService;
	@Autowired
	private IBranchOfficeService branchOfficeService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(String date, String platId, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/bigtop");
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("bigtop/show.do"));
		if (date == null)
			date = Constants.convert(new Date(), Constants.format12);
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("date", date);
		mav.addObject("platId", platId);
		mav.addObject("dateMsg", String.format("%s年", date));
		mav.addObject("platList", platList);
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/**
	 * 功能：所有数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, String platId, String date, Integer branchs, HttpServletRequest request) {
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (users != null) {
			NPageResult<?> pages = this.bigtopService.readPages(pageSize, curPage, sortName, sortOrder, super.searchFilter(request, branchs), platId, date);
			return pages;
		}
		return null;
	}

	/**
	 * 功能：添加修改
	 * */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultMsg saveBigtop(String id, String askfees, String createDT, String date, String platId, HttpServletRequest request) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (askfees != null && askfees.trim().length() > 0 && createDT != null && createDT.trim().length() > 0 && date != null && date.trim().length() > 0) {
			Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
			if (this.bigtopService.saveBigtop(id, askfees, createDT, date, platId, branchs)) {
				msg.setResultStatus(200);
				msg.setResultMessage("success");
			}
		}
		return msg;
	}

	/**
	 * 功能：日期
	 * */
	@ResponseBody
	@RequestMapping(value = "/readDateList", method = RequestMethod.POST)
	public ResultMsg readDateList(HttpServletRequest request) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Object[] rest = new Object[2];
		List<String> dateList = this.bigtopService.readBigtopByDate();
		List<RoleDetailsAtts> areaList = super.platformsRoleDetails(request, String.valueOf("branchOff/show.do"));
		rest[0] = dateList;
		rest[1] = areaList;
		msg.setData(rest);
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/readLoader", method = RequestMethod.GET)
	public ResultMsg readLoader(String date, String platId, Integer branchs, HttpServletRequest request) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Map<String, String> dataList = this.bigtopService.readLoader(date, platId, super.searchFilter(request, branchs));
		if (dataList != null && dataList.size() > 0) {
			msg.setData(dataList);
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/readBigtopDetails", method = RequestMethod.GET)
	public ResultMsg readBigtopDetails(String id, HttpServletRequest request) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		BigTopDetails details = this.bigtopService.readBigtop(id);
		if (details != null) {
			msg.setData(details);
			msg.setResultStatus(200);
		}
		return msg;
	}

}
