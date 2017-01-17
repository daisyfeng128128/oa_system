package com.baofeng.oa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.baofeng.commons.entity.Operator.Actores;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.EmpTransferBean;
import com.baofeng.oa.entity.BaseEnums.EmpTransfer;
import com.baofeng.oa.entity.BaseEnums.EmployeType;
import com.baofeng.oa.entity.Department;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.EmployeeTransfer;
import com.baofeng.oa.entity.Positions;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IDepartmentService;
import com.baofeng.oa.service.IEmpTransferService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IPositionsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;
import com.baofeng.work.WorkManagers;

/**
 * 员工转职
 */
@Controller
@RequestMapping("/empTransfer")
public class EmpTransferController extends BaseController {

	@Autowired
	private IEmpTransferService empTransferService;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IPositionsService positionsService;
	@Autowired
	private WorkManagers workManagers;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request, String empTransfer, Integer branchs) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/empTransfer");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("empTransfer/show.do"));
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		mav.addObject("empTransfer", empTransfer == null ? "UNKNOWN" : empTransfer);
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		return mav;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, String ads, Integer branchs, HttpServletRequest request) {
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		NPageResult<?> pages = this.empTransferService.readPages(pageSize, curPage, sortName, sortOrder, super.searchFilter(request, branchs), ads, platList);
		return pages;
	}

	/**
	 * 功能：添加转岗申请
	 * 
	 * @throws ParseException
	 * */
	@ResponseBody
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public ResultMsg transfer(@RequestBody EmpTransferBean bean, HttpServletRequest request) throws ParseException {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String $current = sdf.format(date);
		Date d = sdf.parse($current);
		RoleUsers user = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);
		Employee post = this.employeeService.readEmployee(Integer.valueOf(bean.getId()));
		EmployeeTransfer $empTransfer = this.empTransferService.readTraEmployee(post.getNumber());
		EmployeeTransfer empTransfer = null;
		if ($empTransfer == null) {
			empTransfer = new EmployeeTransfer();
		} else {
			empTransfer = $empTransfer;
		}
		if (bean.getId() != null && Integer.valueOf(bean.getId()) > 0)
			empTransfer.setNumber(post.getNumber());
		if (Integer.valueOf(bean.getEmployeType()) == 1) {
			empTransfer.setEmployeType(EmployeType.EMPLOYEE);
		} else if (Integer.valueOf(bean.getEmployeType()) == 3) {
			empTransfer.setEmployeType(EmployeType.TALENT);
		} else if (Integer.valueOf(bean.getEmployeType()) == 4) {
			empTransfer.setEmployeType(EmployeType.MANAGER);
		} else if (Integer.valueOf(bean.getEmployeType()) == 2 || post.getActores() == Actores.YES) {
			empTransfer.setEmployeType(EmployeType.ACTORES);
		}
		empTransfer.setOldDepart(post.getDepart());
		empTransfer.setOldPosition(post.getPosition());
		empTransfer.setOldPositionName(post.getPosition().getName());
		empTransfer.setOldDepartName(post.getDepart().getName());
		empTransfer.setName(post.getName());
		empTransfer.setEmpTransfer(EmpTransfer.UNKNOWN);
		empTransfer.setTransferDate(d);
		empTransfer.setBranchs(post.getBranchs());
		if (user != null) {
			empTransfer.setCreateName(user.getUser().getAccounts());
		}
		if (post.getAliasname() != null) {
			empTransfer.setAliasname(post.getAliasname());
		}
		if (bean.getEmpId() != null) {
			Employee emp = this.employeeService.readEmployee(Integer.valueOf(bean.getEmpId()));
			empTransfer.setSupervisor(emp);
			empTransfer.setSupervisorName(emp.getName());
		}
		if (bean.getDepId() != null) {
			Department sup = this.departmentService.readDepartment(Integer.valueOf(bean.getDepId()));
			empTransfer.setNewDepart(sup);
			empTransfer.setNewDepartName(sup.getName());
		}
		if (bean.getPosId() != null) {
			Positions posi = this.positionsService.readPositions(Integer.valueOf(bean.getPosId()));
			empTransfer.setNewPosition(posi);
			empTransfer.setNewPositionName(posi.getName());
		}
		if (this.employeeService.saveTransferEmployee(empTransfer, request)) {
			msg.setResultStatus(200);
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
			if (this.empTransferService.updatePassedEmpTransfer(ids, request)) {
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

}
