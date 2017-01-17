package com.baofeng.oa.controller;

import java.math.BigDecimal;
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
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.PositiveReviewBean;
import com.baofeng.oa.bean.PositiveReviewPassBean;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.PositiveReview;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IPositiveReviewService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 功能 ： 转正审核
 * */
@Controller
@RequestMapping("/positiveReview")
public class PositiveReviewController extends BaseController {

	@Autowired
	private IPositiveReviewService positiveReviewService;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IEmployeeService employeeService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(Integer branchs, Integer empType, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/positiveReview");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("positiveReview/show.do"));
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		empType = empType == null ? 1 : empType;
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("attsList", attsList);
		mav.addObject("empType", empType);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	@RequestMapping(value = "/showCh", method = RequestMethod.GET)
	public ModelAndView showCh(Integer branchs, Integer empType, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/positiveCheck");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("positiveReview/showCh.do"));
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		empType = empType == null ? 1 : empType;
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("attsList", attsList);
		mav.addObject("empType", empType);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPagesActores", method = RequestMethod.POST)
	public NPageResult readPagesActores(int pageSize, int curPage, String sortName, String sortOrder, Integer type, Integer branchs,
			HttpServletRequest request) {
		NPageResult pages = null;
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (users != null) {
			List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
			pages = this.positiveReviewService.readPagesActores(pageSize, curPage, sortName, sortOrder, platList, type,
					super.searchFilter(request, branchs));
		}
		return pages;
	}

	/**
	 * 功能：分页数据 审核
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, Integer type, Integer branchs,
			HttpServletRequest request) {
		NPageResult pages = null;
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (users != null) {
			List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
			pages = this.positiveReviewService.readPages(pageSize, curPage, sortName, sortOrder, platList, type, super.searchFilter(request, branchs));
		}
		return pages;
	}

	/**
	 * 功能：提交转正申请
	 * */
	@ResponseBody
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public ResultMsg apply(Integer id, Integer empType, String apply, Integer type, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (this.positiveReviewService.addApply(id, users, empType, apply, type)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**	
	 * 功能 ： 通过
	 * */
	@ResponseBody
	@RequestMapping(value = "/pass", method = RequestMethod.POST)
	public ResultMsg pass(@RequestBody PositiveReviewPassBean bean, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (this.positiveReviewService.updatePass(bean, users,request)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}
	
	/**	
	 * 功能 ： 人事转正
	 * */
	@ResponseBody
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public ResultMsg reg(Integer id, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		PositiveReview posi = this.positiveReviewService.readPositiveReviewById(id);
		if(posi != null ){
				posi.setType(3);
				posi.setConfirmationDate(new Date());
				if(this.positiveReviewService.updatePosi(posi,request)){
					msg.setResultMessage("success");
					msg.setResultStatus(200);
				}
		}
		return msg;
	}

	/**
	 * 功能 : 审核页 延长时间
	 * */
	@ResponseBody
	@RequestMapping(value = "/applyCheck", method = RequestMethod.POST)
	public ResultMsg applyCheck(Integer id, String apply, Integer type, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (this.positiveReviewService.updateCheckPosi(users, id, apply, type)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}
	
	/**
	 * 功能： 通过修改薪资
	 * */
	@ResponseBody
	@RequestMapping(value="/edit",method = RequestMethod.GET)
	public ResultMsg edit(Integer id ){
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		PositiveReviewBean bean = new PositiveReviewBean(); 
		PositiveReview pos = this.positiveReviewService.readPositiveReviewById(id );
		if(pos != null ){
			Employee emp = this.employeeService.readEmployeeByNumber(pos.getNumber());
			if(emp != null ){
				BranchOffice branchOffice = this.branchOfficeService.readBranchOffice(emp.getBranchs());
				if (branchOffice != null)
					bean.setNumber(branchOffice.getNumberHead() + String.format("%04d", emp.getNumber()));
				else
					bean.setNumber(String.format("%04d", emp.getNumber()));
				BigDecimal salary = new BigDecimal(emp.getSalary() != null ? emp.getSalary() : 0f);
				BigDecimal otherSubsidy = new BigDecimal(emp.getOtherSubsidy() != null ? emp.getOtherSubsidy() : 0f);
				salary = salary.add(otherSubsidy);
				bean.setSalary(salary.toString());
				bean.setPushMoney(emp.getPushMoney() == null ? "-" : String.format("%d%% %n", emp.getPushMoney().intValue()));
				bean.setIntPush(emp.getPushMoney() == null ? "0" : emp.getPushMoney().toString());
			}
			bean.setEmpType(pos.getEmpType());
			bean.setName(pos.getName());
			msg.setData(bean);
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}
}
