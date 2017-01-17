package com.baofeng.commons.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.utils.NPageResult;

@Controller
@RequestMapping("/mlog")
public class MonitorLogController extends BaseController {

	@Autowired
	private IMonitorLogService monitorLogService;

	public IMonitorLogService getMonitorLogService() {
		return monitorLogService;
	}

	public void setMonitorLogService(IMonitorLogService monitorLogService) {
		this.monitorLogService = monitorLogService;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(Integer type) {
		ModelAndView mav = new ModelAndView("page/monitorLog");
		type = type == null ? 1 : type;
		mav.addObject("type", type);
		return mav;
	}

	/**
	 * 功能：查询数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, Integer type, String fastArg, HttpServletRequest request) {
		NPageResult pages = null;
		pages = this.monitorLogService.readAllPages(pageSize, curPage, sortName, sortOrder, type, fastArg, super.searchFilter(request));
		return pages;
	}
}
