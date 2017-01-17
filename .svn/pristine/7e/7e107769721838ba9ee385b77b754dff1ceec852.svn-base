package com.baofeng.oa.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.Operator.Actores;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.FinSalaryEmployee;
import com.baofeng.oa.service.IPersonalSalariesService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.ResultMsg;

/**
 * 功能： 个人中心薪酬福利
 * */

@Controller
@RequestMapping("/salaries")
public class PersonalSalariesController extends BaseController {

	@Autowired
	private IPersonalSalariesService personalSalariesService;

	/**
	 * 功能：快捷链接
	 * */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(String date, Integer type, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/personalSalaries");

		if (date == null)
			date = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);

		RoleUsers user = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);

		Integer number = user.getUser().getNumber();
		Actores utype = user.getUser().getActores();
		Employee cardemp = this.personalSalariesService.readEmployeeByNumber(number);
		FinSalaryEmployee emp = null;
		BigDecimal pn_heji = new BigDecimal(0.00);
		BigDecimal cp_heji = new BigDecimal(0.00);
		BigDecimal else_heji = new BigDecimal(0.00);
		if (cardemp != null) {
		} else {
			cardemp = new Employee();
			emp = new FinSalaryEmployee();
		}

		mav.addObject("utype", utype);
		mav.addObject("salaryemp", emp);
		mav.addObject("employee", cardemp);
		mav.addObject("pn_heji", pn_heji);
		mav.addObject("cp_heji", cp_heji);
		mav.addObject("else_heji", else_heji);
		mav.addObject("date", date);
		return mav;
	}

	/**
	 * 功能：设为转正或离职
	 * */
	@ResponseBody
	@RequestMapping(value = "/updateCard", method = RequestMethod.POST)
	public ResultMsg updateCard(String newCard, String newCardAddress, HttpServletRequest request) {

		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("errors");
		RoleUsers user = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);
		Integer number = user.getUser().getNumber();

		if (this.personalSalariesService.updateEmployee(number, newCard, newCardAddress, request)) {
			msg.setResultStatus(200);
			msg.setResultMessage("success");
		}
		return msg;
	}

}
