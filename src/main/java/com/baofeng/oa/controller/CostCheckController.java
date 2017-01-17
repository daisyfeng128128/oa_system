package com.baofeng.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.service.ICostCheckService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;

/**
 * 艺人成本核对
 * */
@Controller
@RequestMapping("/costCheck")
public class CostCheckController extends BaseController {

	@Autowired
	private ICostCheckService costCheckService;

	/** 线下艺人成本 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/costCheckOff");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("costCheck/show.do"));
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, HttpServletRequest request) {
		NPageResult pages = null;
		pages = this.costCheckService.readAllPages(pageSize, curPage, sortName, sortOrder, super.searchFilter(request));
		return pages;
	}

}
