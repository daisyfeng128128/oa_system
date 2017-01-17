package com.baofeng.oa.controller;

import java.util.ArrayList;
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
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.ActoresBean;
import com.baofeng.oa.bean.FinSalariesAdjustsBean;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IFinSalariesAdjustsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 功能： 薪资调整
 * */
@Controller
@RequestMapping("/adjusts")
public class FinSalariesAdjustsController extends BaseController {

	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IFinSalariesAdjustsService finSalariesAdjustsService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request, String adjust,Integer branchs) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/finSalariesAdjusts");
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("adjusts/show.do"));
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("adjust", adjust == null ? "UNKNOWN" : adjust);
		mav.addObject("attsList", attsList);
		mav.addObject("platList", platList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/**
	 * 功能：分页查询
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, String ads,Integer branchs, HttpServletRequest request) {
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		NPageResult<?> pages = this.finSalariesAdjustsService.readPages(pageSize, curPage, sortName, sortOrder, super.searchFilter(request,branchs), ads, platList);
		return pages;
	}

	/**
	 * 功能：搜索
	 * */
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public List<ActoresBean> search(@RequestBody Actores actores, HttpServletRequest request) {
		List<ActoresBean> result = new ArrayList<ActoresBean>();
		if (actores != null && actores.getRealname() != null && actores.getRealname().trim().length() > 0) {
			List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
			result = this.finSalariesAdjustsService.readPlatformsActores(actores, platList);
		}
		return result;
	}

	/**
	 * 功能：添加调薪申请
	 * */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultMsg save(@RequestBody FinSalariesAdjustsBean bean, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (bean.getId() != null && bean.getId().trim().length() > 0) {
			if (this.finSalariesAdjustsService.saveFinSalariesAdjusts(bean, users, branchs, request)) {
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能：通过审核
	 * */
	@ResponseBody
	@RequestMapping(value = "/passed", method = RequestMethod.POST)
	public ResultMsg passed(String ids, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (ids != null && ids.trim().length() > 0) {
			if (this.finSalariesAdjustsService.updatePassedFinSalariesAdjusts(ids, request)) {
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能：拒绝审核
	 * */
	@ResponseBody
	@RequestMapping(value = "/reject", method = RequestMethod.POST)
	public ResultMsg reject(String ids, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (ids != null && ids.trim().length() > 0) {
			if (this.finSalariesAdjustsService.updateRejectFinSalariesAdjusts(ids, request)) {
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能：删除数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public ResultMsg del(String ids, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (ids != null && ids.trim().length() > 0) {
			if (this.finSalariesAdjustsService.deleteFinSalariesAdjusts(ids, request)) {
				msg.setResultStatus(200);
			}
		}
		return msg;
	}
	
	/**
	 * 功能： 检查是否可以调薪
	 * */
	@ResponseBody
	@RequestMapping(value = "/check" ,method =RequestMethod.POST)
	private ResultMsg checkUp(Integer id){
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if(id != null && id.intValue() > 0){
			if(this.finSalariesAdjustsService.checkUp(id)){
				msg.setResultStatus(101);
				msg.setResultMessage("success");
			}else{
				msg.setResultStatus(200);
				msg.setResultMessage("success");
			}
		}
		return msg;
	}
}
