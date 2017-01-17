package com.baofeng.commons.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.baofeng.commons.entity.Operator;
import com.baofeng.commons.service.IMenuitemService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.ResultMsg;

@Controller
@RequestMapping("/menu")
public class MenuitemController {

	@Autowired
	private IMenuitemService menuitemService;

	public IMenuitemService getMenuitemService() {
		return menuitemService;
	}

	public void setMenuitemService(IMenuitemService menuitemService) {
		this.menuitemService = menuitemService;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView mav = new ModelAndView("menuitem");
		return mav;
	}

	@RequestMapping(value = "/devshow", method = RequestMethod.GET)
	public ModelAndView devshow() {
		ModelAndView mav = new ModelAndView("page/dev");
		return mav;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public PageResult readPages(int page, int rows, HttpServletRequest request) {
		Operator user = (Operator) request.getSession().getAttribute(Constants.CURRENT_USER);
		if (user != null) {
			PageResult pages = this.menuitemService.readAllPages(rows, page, user);
			return pages;
		}
		return null;
	}
	
	/**
	 * 功能：初始化数据
	 * 
	 * @throws IOException
	 * */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public void init(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResultMsg result = new ResultMsg();
		result.setResultMessage("errors");
		if (this.menuitemService.saveInit()) {
			result.setResultMessage("success");
			result.setResultStatus(Integer.valueOf(200));
		}
		response.getWriter().write(JSON.toJSONString(result));
	}
}
