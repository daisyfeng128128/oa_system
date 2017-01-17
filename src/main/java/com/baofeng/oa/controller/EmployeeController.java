package com.baofeng.oa.controller;

import java.math.BigDecimal;
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
import com.baofeng.commons.dao.UsersDAO;
import com.baofeng.commons.entity.Operator.Actores;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.EmpTransferBean;
import com.baofeng.oa.bean.EmployeeBean;
import com.baofeng.oa.entity.BaseEnums.Account;
import com.baofeng.oa.entity.BaseEnums.EmpTransfer;
import com.baofeng.oa.entity.BaseEnums.EmployeType;
import com.baofeng.oa.entity.Department;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.EmployeeLeave;
import com.baofeng.oa.entity.EmployeeTransfer;
import com.baofeng.oa.entity.Positions;
import com.baofeng.oa.service.IBranchUserService;
import com.baofeng.oa.service.IDepartmentService;
import com.baofeng.oa.service.IEmpTransferService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IManagerService;
import com.baofeng.oa.service.IPositionsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.ResultMsg;
import com.baofeng.work.WorkManagers;

/**
 * 功能： 员工管理
 * */
@Controller
@RequestMapping("/emp")
public class EmployeeController extends BaseController {

	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IPositionsService positionsService;
	@Autowired
	private IMonitorLogService monitorLogService;
	@Autowired
	private IManagerService managerService;
	@Autowired
	private IEmpTransferService empTransferService;
	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private IBranchUserService branchUserService;
	@Autowired
	private UsersDAO usersDAO;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(String gid, String linktype, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/empList");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("emp/show.do"));
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 功能：试用
	 * */
	@RequestMapping(value = "/showProbationer", method = RequestMethod.GET)
	public ModelAndView showProbationer(Integer id, Integer type, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/probationer");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("emp/showProbationer.do"));
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 页面：离职
	 * */
	@RequestMapping(value = "/empLeave", method = RequestMethod.GET)
	public ModelAndView empLeave(Integer id, Integer type, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/empLeave");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("emp/empLeave.do"));
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 页面：生日
	 * */
	@RequestMapping(value = "/empBirth", method = RequestMethod.GET)
	public ModelAndView empBirth(Integer id, Integer type, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/empBirth");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("emp/empBirth.do"));
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 功能：员工转岗
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
		Employee post = this.employeeService.readEmployee(Integer.valueOf(bean.getId()));
		EmployeeTransfer $empTransfer = this.empTransferService.readTraEmployee(post.getNumber());
		RoleUsers user = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);
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
		empTransfer.setOldDepartName(post.getDepart().getName());
		empTransfer.setOldPositionName(post.getPosition().getName());
		empTransfer.setName(post.getName());
		empTransfer.setEmpTransfer(EmpTransfer.UNKNOWN);
		empTransfer.setTransferDate(d);
		empTransfer.setBranchs(post.getBranchs());
		if (post.getAliasname() != null) {
			empTransfer.setAliasname(post.getAliasname());
		}
		if (user != null) {
			empTransfer.setCreateName(user.getUser().getAccounts());
		}
		if (bean.getEmpId() != null && !("").equals(bean.getEmpId())) {
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
	 * 功能：编辑
	 * */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public EmployeeBean read(Integer id) {
		Employee post = this.employeeService.readEmployee(id);
		boolean manager = this.managerService.readManager(id);
		if (post != null) {
			EmployeeBean bean = new EmployeeBean();
			if (post.getPosition() != null) {
				bean.setPosId(post.getPosition().getId());
				bean.setPosName(post.getPosition().getName());
			}
			bean.setId(post.getId());
			bean.setNum(post.getNumber().toString());
			bean.setAliasname(post.getAliasname());
			bean.setBirth(post.getBirth());
			if (post.getDepart() != null) {
				bean.setDepId(post.getDepart().getId());
				bean.setDepName(post.getDepart().getName());
			}
			bean.setEmergencyContact(post.getEmergencyContact());
			bean.setEmergencyPhone(post.getEmergencyPhone());
			if (post.getSupervisor() != null) {
				bean.setEmpId(post.getSupervisor().getId());
				bean.setEmpName(post.getSupervisor().getName());
			} else {
				bean.setEmpName("");
			}
			bean.setGenrer(post.getGenrer() == Genres.SHIYONG ? "SHIYONG" : "ZHENGSHI");
			bean.setSex(post.getSex() == Sex.WOMAN ? "WOMAN" : "MAN");
			bean.setJiaojin(post.getJiaojin().toString());
			if (post.getSocialSecurity() != null)
				bean.setSocialSecurity(post.getSocialSecurity().toString());
			bean.setAccount(post.getAccount() == Account.CHENGZHEN ? "CHENGZHEN" : "NONGCUN");
			if (post.getEmployeType() != null) {
				bean.setEmployeType(post.getEmployeType().toString());
			} else {
				if (manager == true) {
					bean.setEmployeType(EmployeType.MANAGER.name());
				}
				if (post.getActores() == Actores.YES) {
					bean.setEmployeType(EmployeType.ACTORES.name());
				}
				if (manager == false && post.getActores() == Actores.NO) {
					bean.setEmployeType(EmployeType.EMPLOYEE.name());
				}
			}
			if (post.getEmployeType() == EmployeType.ACTORES || post.getActores() == Actores.YES) {
				bean.setPushMoney(post.getPushMoney() == null ? "-" : String.valueOf(post.getPushMoney().intValue()));
				bean.setActoresBSalary(post.getActoresBSalary());
				bean.setShareBankCard(post.getShareBankCard());
				bean.setShareBankAddress(post.getShareBankAddress());
				bean.setSpeciality(post.getSpeciality());
			}

			bean.setHostRegister(post.getHostRegister());
			bean.setIdcard(post.getIdcard());
			bean.setIdImage(post.getIdImage());
			bean.setIntroducer(post.getIntroducer());
			bean.setJoinDate(post.getJoinDate());
			bean.setRegularDate(post.getRegularDate());
			bean.setContractDT(post.getContractDT());
			bean.setLiveAdress(post.getLiveAdress());
			bean.setPhone(post.getPhone());
			bean.setQq(post.getQq());
			bean.setHostAddress(post.getHostAddress());
			bean.setName(post.getName());
			bean.setNamepy(post.getNamepy());

			bean.setEmail(post.getEmail());
			bean.setCpfAccounts(post.getCpfAccounts());
			bean.setBankCard(post.getBankCard());
			bean.setBankAddress(post.getBankAddress());

			Float probationSalary = Float.valueOf(0);
			if (post.getProbationSalary() != null)
				probationSalary = post.getProbationSalary();
			bean.setProbationSalary(probationSalary);
			if (post.getOtherSubsidy() != null)
				bean.setOtherSubsidy(post.getOtherSubsidy());
			if (post.getSalary() != null)
				bean.setSalary(post.getSalary());
			if (post.getFoodSubsidyE() != null) {
				bean.setFoodSubsidyE(post.getFoodSubsidyE());
			}
			if (post.getOtherSubsidyE() != null) {
				bean.setOtherSubsidyE(post.getOtherSubsidyE());
			}
			if (post.getJobSubsidyE() != null) {
				bean.setJobSubsidyE(post.getJobSubsidyE());
			}

			if (post.getPFcardinalNumber() != null)
				bean.setPFcardinalNumber(post.getPFcardinalNumber());
			if (post.getSScardinalNumber() != null)
				bean.setSScardinalNumber(post.getSScardinalNumber());

			bean.setRemarks(post.getRemarks());

			return bean;
		}
		return null;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, String name, String snumber, String snamepy, Integer sposid, Integer sdepid,
			String fastArg, String type, HttpServletRequest request) {
		NPageResult pages = null;
		pages = this.employeeService.readAllPages(pageSize, curPage, sortName, sortOrder, name, snumber, snamepy, sposid, sdepid, fastArg, type, super.searchFilter(request));
		return pages;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPagesBirth", method = RequestMethod.POST)
	public NPageResult readPagesBirth(int pageSize, int curPage, String sortName, String sortOrder, String name, String snumber, String snamepy, Integer sposid, Integer sdepid,
			String fastArg, String type, HttpServletRequest request) {
		NPageResult pages = null;
		pages = this.employeeService.readPagesBirth(pageSize, curPage, sortName, sortOrder, super.searchFilter(request));
		return pages;
	}

	/**
	 * 功能：删除
	 * */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResultMsg delete(Integer id, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("errors");
		if (this.employeeService.deleteEmployee(id, request)) {
			msg.setResultStatus(200);
			msg.setResultMessage("success");
		}
		return msg;
	}

	/**
	 * 功能：分页
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPagesSkip", method = RequestMethod.POST)
	public PageResult<?> readPagesSkip(int page, int rows, String filter, HttpServletRequest request) {
		PageResult<?> pages = null;
		pages = this.employeeService.readAllPagesSkip(rows, page, super.searchFilter(request), filter);
		return pages;
	}

	/**
	 * 功能：添加修改
	 * */
	@ResponseBody
	@RequestMapping(value = "/empsave", method = RequestMethod.POST)
	public ResultMsg save(Integer id, String name, String password, String namepy, Integer sex, String actores, String idcard, String hostRegister, String aliasname, String birth,
			String phone, String qq, String email, String liveAdress, String hostAddress, String emergencyContact, String emergencyPhone, Integer depid, Integer posid,
			String regularDate, Integer empid, String introducer, String joinDate, Integer genrer, String contractDT, String cpfAccounts, String account, String bankCard,
			String bankAddress, String probationSalary, String salary, String otherSubsidy, String PFcardinalNumber, String SScardinalNumber, String shareBankCard,
			String shareBankAddress, String jiaojin, String speciality, String pushMoney, String remarks, String socialSecurity, String foodSubsidyE, String actoresBSalary,
			String otherSubsidyE, String jobSubsidyE, Integer employeType, HttpServletRequest request) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		Employee post = new Employee();
		post.setBranchs(branchs);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (id != null && id > 0) {
			post.setId(id);
			boolean manager = this.managerService.readManager(id);
			if (manager == true) {
				post.setEmployeType(EmployeType.MANAGER);
			}
			if (manager == false && post.getActores() == Actores.NO) {
				post.setEmployeType(EmployeType.EMPLOYEE);
			}
		}
		if (contractDT != null && contractDT.trim().length() > 0) {
			Date date = sdf.parse(contractDT);
			post.setContractDT(date);
		}
		if (PFcardinalNumber != null && PFcardinalNumber.trim().length() > 0)
			post.setPFcardinalNumber(Float.valueOf(PFcardinalNumber));
		if (SScardinalNumber != null && SScardinalNumber.trim().length() > 0)
			post.setSScardinalNumber(Float.valueOf(SScardinalNumber));

		if (account != null)
			post.setAccount(Enum.valueOf(Account.class, account));
		if (jiaojin != null)
			post.setJiaojin(Integer.valueOf(jiaojin));
		if (socialSecurity != null)
			post.setSocialSecurity(Integer.valueOf(socialSecurity));
		post.setCpfAccounts(cpfAccounts);
		post.setBankCard(bankCard);
		post.setBankAddress(bankAddress);
		post.setSalary(Float.valueOf(salary));
		if (probationSalary != null && probationSalary.trim().length() > 0) {
			post.setProbationSalary(Float.valueOf(probationSalary));
		}
		if (probationSalary != null && otherSubsidy.trim().length() > 0) {
			post.setOtherSubsidy(Float.valueOf(otherSubsidy));
		}
		if (foodSubsidyE != null && foodSubsidyE.trim().length() > 0) {
			post.setFoodSubsidyE(Float.valueOf(foodSubsidyE));
		}
		if (otherSubsidyE != null && otherSubsidyE.trim().length() > 0) {
			post.setOtherSubsidyE(Float.valueOf(otherSubsidyE));
		}
		if (jobSubsidyE != null && jobSubsidyE.trim().length() > 0) {
			post.setJobSubsidyE(Float.valueOf(jobSubsidyE));
		}
		post.setRemarks(remarks);

		if (name == null || "".equals(name)) {
			msg.setResultStatus(101);
			msg.setData("");
			return msg;
		}
		if (password != null && password.trim().length() > 0)
			post.setPassword(password);

		post.setName(name);
		post.setNamepy(namepy);
		if (sex == 1) {
			post.setSex(Sex.WOMAN);
		} else if (sex == 2) {
			post.setSex(Sex.MAN);
		} else {
			post.setSex(Sex.OTHER);
		}

		if (employeType == 1) {
			post.setEmployeType(EmployeType.EMPLOYEE);
		} else if (employeType == 3) {
			post.setEmployeType(EmployeType.TALENT);
		} else if (employeType == 4) {
			post.setEmployeType(EmployeType.MANAGER);
		} else if (employeType == 2 || post.getActores() == Actores.YES) {
			post.setEmployeType(EmployeType.ACTORES);
			if (actoresBSalary != null && actoresBSalary.trim().length() > 0) {
				post.setActoresBSalary(Float.valueOf(actoresBSalary));
			}
			if (pushMoney != null && pushMoney.trim().length() > 0) {
				post.setPushMoney(new BigDecimal(pushMoney));
			}
			post.setShareBankCard(shareBankCard);
			post.setShareBankAddress(shareBankAddress);
			post.setSpeciality(speciality);
		}
		if (genrer == 1) {
			post.setGenrer(Genres.SHIYONG);
		} else if (genrer == 2) {
			post.setGenrer(Genres.ZHENGSHI);
		}
		post.setIdcard(idcard);
		post.setHostRegister(hostRegister);
		post.setAliasname(aliasname);
		post.setPhone(phone);
		post.setQq(qq);
		post.setEmail(email);
		post.setLiveAdress(liveAdress);
		post.setHostAddress(hostAddress);
		post.setEmergencyContact(emergencyContact);
		post.setEmergencyPhone(emergencyPhone);
		post.setIntroducer(introducer);
		if (birth != null && birth.trim().length() > 0) {
			Date birthday = sdf.parse(birth);
			post.setBirth(birthday);
		}
		if (regularDate != null && regularDate.trim().length() > 0) {
			Date date = sdf.parse(regularDate);
			post.setRegularDate(date);
		}
		if (joinDate != null && joinDate.trim().length() > 0) {
			Date date = sdf.parse(joinDate);
			post.setJoinDate(date);
		}
		if (empid != null && empid > 0) {
			Employee emp = this.employeeService.readEmployee(empid);
			post.setSupervisor(emp);
		}
		if (depid != null && depid.intValue() > 0) {
			Department sup = this.departmentService.readDepartment(depid);
			post.setDepart(sup);
		}
		if (posid != null && posid > 0) {
			Positions posi = this.positionsService.readPositions(posid);
			post.setPosition(posi);
		}

		if (this.employeeService.addEmployee(post, request)) {
			msg.setResultStatus(200);
		}

		return msg;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPagesProbationer", method = RequestMethod.POST)
	public NPageResult readPagesProbationer(int pageSize, int curPage, String sortName, String sortOrder, String type, String fastArg, HttpServletRequest request) {
		NPageResult pages = null;
		if (type.equals("leave")) {
			pages = this.employeeService.readAllPagesLeave(pageSize, curPage, sortName, sortOrder, fastArg, super.searchFilter(request));
		} else {
			pages = this.employeeService.readAllPagesProbationer(pageSize, curPage, sortName, sortOrder, fastArg, super.searchFilter(request));
		}
		return pages;
	}

	/**
	 * 功能：编辑试用人员
	 * */
	@ResponseBody
	@RequestMapping(value = "/editProbationer", method = RequestMethod.GET)
	public EmployeeBean editProbationer(Integer id) {
		Employee post = this.employeeService.readEmployee(id);
		if (post != null) {
			EmployeeBean bean = new EmployeeBean();
			bean.setId(post.getId());
			bean.setEmail(post.getEmail());
			bean.setPhone(post.getPhone());
			bean.setJoinDate(post.getJoinDate());
			bean.setName(post.getName());
			bean.setRegularDate(post.getRegularDate());
			return bean;
		}
		return null;
	}

	/**
	 * 功能：设为转正或离职
	 * */
	@RequestMapping(value = "/updateProbationer", method = RequestMethod.POST)
	public ModelAndView updateProbationer(Integer id, Integer type, String reason, String tt, HttpServletRequest request) {
		ModelAndView mav = null;
		if ("show".equals(tt)) {
			Employee emp = this.employeeService.readEmployee(id);
			RoleUsers users = this.usersDAO.findRoleUsersByNumber(emp.getNumber());
			this.branchUserService.deletebyNum(emp.getNumber(),users);
			mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/empList");
			List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("emp/show.do"));
			mav.addObject("attsList", attsList);
		} else {
			mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/probationer");
			List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("emp/showProbationer.do"));
			mav.addObject("attsList", attsList);
		}

		if (this.employeeService.updateEmployee(id, type, reason, request)) {
			mav.addObject("result", "success");
		}
		return mav;
	}

	/**
	 * 功能：修改转正日期
	 * */
	@RequestMapping(value = "/addEmp", method = RequestMethod.POST)
	public ModelAndView addEmp(Integer id, String regularDate, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/probationer");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("emp/showProbationer.do"));
		mav.addObject("attsList", attsList);
		Employee post = new Employee();
		if (id != null && id > 0) {
			post.setId(id);
		}
		if (regularDate != null) {
			post.setRegularDate(CustomDateUtils.format(regularDate, CustomDateUtils.format1));
		}
		if (this.employeeService.addEmp(post)) {
			this.monitorLogService.logsUpdate(request, "员工: " + post.getName() + " ID: " + post.getId() + " (调整试用期)");
			mav.addObject("result", "success");
		}
		return mav;
	}

	/**
	 * 功能： 查看离职详情
	 * */

	@ResponseBody
	@RequestMapping(value = "/editLeave", method = RequestMethod.GET)
	public EmployeeBean editLeave(Integer id) {
		EmployeeLeave empLeave = this.employeeService.readEmployeeLeave(id);
		Employee post = empLeave.getEmployee();
		if (post != null) {
			EmployeeBean bean = new EmployeeBean();
			bean.setId(post.getId());
			bean.setAliasname(post.getAliasname());
			bean.setBirth(post.getBirth());
			if (post.getDepart() != null) {
				bean.setDepId(post.getDepart().getId());
				bean.setDepName(post.getDepart().getName());
			}
			bean.setEmergencyContact(post.getEmergencyContact());
			bean.setEmergencyPhone(post.getEmergencyPhone());
			if (post.getSupervisor() != null) {
				bean.setEmpId(post.getSupervisor().getId());
				bean.setEmpName(post.getSupervisor().getName());
			} else {
				bean.setEmpName("");
			}
			bean.setGenrer(post.getGenrer() == Genres.SHIYONG ? "SHIYONG" : post.getGenrer() == Genres.ZHENGSHI ? "ZHENGSHI" : "PENDING");
			bean.setSex(post.getSex() == Sex.WOMAN ? "WOMAN" : "MAN");
			bean.setActores(post.getActores() == Actores.YES ? "YES" : "NO");
			bean.setJiaojin(post.getJiaojin().toString());
			bean.setAccount(post.getAccount() == Account.CHENGZHEN ? "CHENGZHEN" : "NONGCUN");

			bean.setHostRegister(post.getHostRegister());
			bean.setIdcard(post.getIdcard());
			bean.setIdImage(post.getIdImage());
			bean.setIntroducer(post.getIntroducer());
			bean.setJoinDate(post.getJoinDate());
			bean.setRegularDate(post.getRegularDate());
			bean.setContractDT(post.getContractDT());
			bean.setLiveAdress(post.getLiveAdress());
			bean.setPhone(post.getPhone());
			bean.setQq(post.getQq());
			bean.setHostAddress(post.getHostAddress());
			bean.setName(post.getName());
			bean.setNamepy(post.getNamepy());

			bean.setEmail(post.getEmail());
			bean.setCpfAccounts(post.getCpfAccounts());
			bean.setBankCard(post.getBankCard());
			bean.setBankAddress(post.getBankAddress());

			Float probationSalary = Float.valueOf(0);
			if (post.getProbationSalary() != null)
				probationSalary = post.getProbationSalary();
			bean.setProbationSalary(probationSalary);
			if (post.getOtherSubsidy() != null)
				bean.setOtherSubsidy(post.getOtherSubsidy());
			if (post.getSalary() != null)
				bean.setSalary(post.getSalary());

			if (post.getPFcardinalNumber() != null)
				bean.setPFcardinalNumber(post.getPFcardinalNumber());
			if (post.getSScardinalNumber() != null)
				bean.setSScardinalNumber(post.getSScardinalNumber());

			bean.setShareBankCard(post.getShareBankCard());
			bean.setShareBankAddress(post.getShareBankAddress());
			bean.setSpeciality(post.getSpeciality());
			bean.setRemarks(post.getRemarks());

			if (post.getPosition() != null) {
				bean.setPosId(post.getPosition().getId());
				bean.setPosName(post.getPosition().getName());
			}
			return bean;
		}
		return null;
	}
}
