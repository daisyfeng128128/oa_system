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
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.Positions;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IPositionsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 部门管理
 */
@Controller
@RequestMapping("/positions")
public class PositionsController extends BaseController {

	@Autowired
	private IPositionsService positionsService;
	@Autowired
	private IEmployeeService employeeService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/positions");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("positions/show.do"));
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, String name, HttpServletRequest request) {
		NPageResult pages = null;
		pages = this.positionsService.readAllPages(pageSize, curPage, sortName, sortOrder, name, super.searchFilter(request));
		return pages;
	}

	/**
	 * 功能：删除
	 * */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResultMsg delete(Integer id,HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("errors");
		if (id != null && id.intValue() > 0) {
			List<Employee> list = this.employeeService.readAllEmpByPid(id);
			if (list != null && list.size() > 0) {
				msg.setResultMessage("error");
				msg.setResultStatus(201);
				msg.setData("部门下有员工不可删除！");
				return msg;
			}
		}
		if (this.positionsService.deletePositions(id,request)) {
			msg.setResultStatus(200);
			msg.setResultMessage("success");
		}
		return msg;
	}

	/**
	 * 功能：添加修改
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(Integer id, String name, String described, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/positions");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("positions/show.do"));
		mav.addObject("attsList", attsList);
		Positions post = new Positions();
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		post.setBranchs(branchs);
		if (id != null)
			post.setId(id);
		post.setName(name);
		post.setDescribed(described);
		if (this.positionsService.addPositions(post,request)) {
			mav.addObject("result", "success");
		}
		return mav;
	}

	/**
	 * 功能：编辑
	 * */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public Positions read(Integer id) {
		Positions positions = this.positionsService.readPositions(id);
		if (positions != null) {
			return positions;
		}
		return null;
	}

}
