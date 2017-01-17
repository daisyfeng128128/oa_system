package com.baofeng.oa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.ManagerOnlineBean;
import com.baofeng.oa.bean.ManagersBean;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IManagerService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 功能： 频道助理
 * */
@Controller
@RequestMapping("/manager")
public class ManagerController extends BaseController {

	@Autowired
	private IManagerService managerService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IMonitorLogService monitorLogService;
	@Autowired
	private IBranchOfficeService branchOfficeService;

	/**
	 * 功能：快捷功能
	 * */
	@RequestMapping(value = "/showLink", method = RequestMethod.GET)
	public ModelAndView showLink(Integer platId, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/manager");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("manager/showLink.do"));
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("toUrl", String.valueOf("manager/showLink.do"));
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	@RequestMapping(value = "/showOnlineLink", method = RequestMethod.GET)
	public ModelAndView showOnlineLink(Integer platId, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/managerOnline");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("manager/showOnlineLink.do"));
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("toUrl", String.valueOf("manager/showOnlineLink.do"));
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	@RequestMapping(value = "/onlineShow", method = RequestMethod.GET)
	public ModelAndView onlineShow(Integer platId, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/managerOnline");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("manager/onlineShow.do"));
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("toUrl", String.valueOf("manager/onlineShow.do"));
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(Integer platId, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/manager");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("manager/show.do"));
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("toUrl", String.valueOf("manager/show.do"));
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	@RequestMapping(value = "/showLeave", method = RequestMethod.GET)
	public ModelAndView showLeave(Integer platId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/leaveManager");
		return mav;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPagesOffline", method = RequestMethod.POST)
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, String genrer, String toUrl, String fastArg, String genre, Integer branchs,
			HttpServletRequest request) {
		NPageResult pages = null;
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (users != null) {
			List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
			List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf(toUrl));
			pages = this.managerService.readPages(pageSize, curPage, sortName, sortOrder, genrer, super.searchFilter(request, branchs), users, platList, attsList, genre, fastArg);
		}
		return pages;
	}

	/**
	 * 功能：添加修改
	 * */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultMsg save(Integer id, String aliasname, String realname, Integer genre, String phone, String qq, String positions, String entryTime, Integer platId,
			Integer channelsid, Integer empid, String sex, String bankCard, String bankCardAccount, Float foodSubsidy, Float otherSubsidy, HttpServletRequest request)
			throws Exception {
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		RoleUsers users = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Managers post = new Managers();
		post.setOperator(users.getUser());
		post.setBranchs(branchs);
		post.setPhone(phone);
		post.setQq(qq);
		post.setId(id);
		post.setAliasname(aliasname);
		post.setRealname(realname);
		post.setGenre(LineGenres.OFFLINE);
		post.setSex(Enum.valueOf(Sex.class, sex));
		post.setPositions(positions);
		post.setFoodSubsidy(foodSubsidy);
		post.setBankCard(bankCard);
		post.setBankCardAccount(bankCardAccount);
		if (entryTime != null && entryTime.trim().length() > 0)
			post.setEntryTime(Constants.convert(entryTime, Constants.format1));
		if (empid != null && empid.intValue() > 0) {
			Employee eid = this.employeeService.readEmployee(empid);
			post.setEmployee(eid);
			post.setLabour(ActoresLabour.SYSTEM);
			post.setNumber(eid.getNumber());
			BigDecimal $basicSalary = new BigDecimal(eid.getSalary() != null ? eid.getSalary() : 0.00).add(new BigDecimal(eid.getOtherSubsidy() != null ? eid.getOtherSubsidy()
					: 0.0));
			post.setBasicSalary($basicSalary.floatValue());
			BigDecimal $probationSalary = new BigDecimal(eid.getSalary() != null ? eid.getSalary() : 0.00).add(new BigDecimal(eid.getProbationSalary() != null ? eid
					.getProbationSalary() : 0.00));
			post.setProbationSalary($probationSalary.floatValue());
			if (eid.getGenrer() == Genres.ZHENGSHI) {
				post.setGenrer(Genres.ZHENGSHI);
			} else {
				post.setGenrer(Genres.SHIYONG);
			}
		} else {
			post.setGenrer(Genres.ZHENGSHI);
			post.setProbationSalary(0.00f);
		}
		if (this.managerService.addManager(post, request)) {
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能：编辑
	 * */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ManagersBean read(Integer id) {
		ManagersBean managers = this.managerService.readManagersBean(id);
		if (managers != null) {
			return managers;
		}
		return null;
	}

	/**
	 * 功能：查找员工
	 * */
	@ResponseBody
	@RequestMapping(value = "/empEdit", method = RequestMethod.GET)
	public ResultMsg readEmp(Integer id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.intValue() > 0) {
			if (!this.managerService.readManager(id)) {
				Employee post = this.employeeService.readEmployee(id);
				if (post != null) {
					ManagersBean bean = new ManagersBean();
					bean.setId(post.getId());
					bean.setAliasname(post.getAliasname());
					bean.setIdcard(post.getIdcard());
					bean.setQq(post.getQq());
					bean.setPhone(post.getPhone());
					bean.setIdImage(post.getIdImage());
					bean.setAddress(post.getLiveAdress());
					bean.setRealname(post.getName());
					bean.setEntryTime(post.getJoinDate());
					bean.setBankCard(post.getBankCard());
					bean.setBankCardAccount(post.getBankAddress());
					bean.setSex(post.getSex().toString());
					if (post.getJoinDate() != null && post.getRegularDate() != null) {
						String brefe = String.format(Locale.US, "%tj", post.getRegularDate());
						String after = String.format(Locale.US, "%tj", post.getJoinDate());
						if (brefe != null && after != null && Integer.valueOf(brefe) > Integer.valueOf(after)) {
							bean.setProbation(((Integer.valueOf(brefe) - Integer.valueOf(after)) / 30) + "");
						}
					}

					Float probationSalary = Float.valueOf(0);
					if (post.getProbationSalary() != null)
						probationSalary = post.getProbationSalary();
					if (post.getOtherSubsidy() != null)
						probationSalary += post.getOtherSubsidy();
					bean.setProbationSalary(probationSalary.toString());
					Float basicSalary = Float.valueOf(0);
					if (post.getSalary() != null)
						basicSalary = post.getSalary();
					if (post.getOtherSubsidy() != null)
						basicSalary += post.getOtherSubsidy();
					bean.setBasicSalary(basicSalary.toString());
					msg.setData(bean);
				}
				msg.setResultStatus(200);
			} else {
				msg.setResultStatus(101);
			}
		}
		return msg;
	}

	/**
	 * 功能：删除
	 * */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResultMsg delete(Integer id, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("errors");
		if (this.managerService.deleteManagers(id, request)) {
			msg.setResultStatus(200);
			msg.setResultMessage("success");
		}
		return msg;
	}

	/**
	 * 功能 ： 离职
	 * */
	@ResponseBody
	@RequestMapping(value = "/saveLeave", method = RequestMethod.POST)
	public ResultMsg saveLeave(Integer id, String leaveReasons, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		Managers manager = this.managerService.readManagers(id);
		if (manager != null) {
			manager.setLeaveReasons(leaveReasons);
			manager.setGenrer(Genres.LIZHI);
			if (this.managerService.addLeaveManager(manager)) {
				this.monitorLogService.logsUpdate(request, "助理: " + manager.getRealname() + " ID: " + manager.getId() + "（离职）");
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能：添加修改
	 * */
	@ResponseBody
	@RequestMapping(value = "/saveOnline", method = RequestMethod.POST)
	public ResultMsg saveOnline(@RequestBody ManagerOnlineBean bean, HttpServletRequest request) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		Managers post = new Managers();
		post.setOperator(users.getUser());
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		post.setBranchs(branchs);
		post.setPhone(bean.getPhone());
		post.setQq(bean.getQq());
		if (bean.getId() != null && bean.getId().trim().length() > 0)
			post.setId(Integer.valueOf(bean.getId()));
		post.setAliasname(bean.getAliasname());
		post.setRealname(bean.getRealname());
		post.setGenre(LineGenres.ONLINE);
		post.setSex(Enum.valueOf(Sex.class, bean.getSex()));
		post.setPositions(bean.getPositions());
		if (bean.getBasicSalary() != null && bean.getBasicSalary().trim().length() > 0)
			post.setBasicSalary(Float.valueOf(bean.getBasicSalary()));
		if (bean.getFoodSubsidy() != null && bean.getFoodSubsidy().trim().length() > 0)
			post.setFoodSubsidy(Float.valueOf(bean.getFoodSubsidy()));
		post.setBankCard(bean.getBankCard());
		post.setBankCardAccount(bean.getBankCardAccount());
		if (bean.getEntryTime() != null && bean.getEntryTime().trim().length() > 0)
			post.setEntryTime(Constants.convert(bean.getEntryTime(), Constants.format1));
		if (bean.getEmpid() != null && bean.getEmpid().trim().length() > 0) {
			Employee eid = this.employeeService.readEmployee(Integer.valueOf(bean.getEmpid()));
			post.setEmployee(eid);
			post.setLabour(ActoresLabour.SYSTEM);
			post.setNumber(eid.getNumber());
			if (eid.getGenrer() == Genres.ZHENGSHI) {
				post.setGenrer(Genres.ZHENGSHI);
			}
			if (eid.getJoinDate() != null && eid.getRegularDate() != null) {
				String brefe = String.format(Locale.US, "%tj", eid.getRegularDate());
				String after = String.format(Locale.US, "%tj", eid.getJoinDate());
				if (brefe != null && after != null && Integer.valueOf(brefe) > Integer.valueOf(after)) {
					post.setProbation(((Integer.valueOf(brefe) - Integer.valueOf(after)) / 30));
				}
			}
		} else {
			post.setGenrer(Genres.ZHENGSHI);
			post.setProbationSalary(0.00f);
		}
		if (post.getGenre() == LineGenres.ONLINE && this.managerService.checkManager(post)) {
			msg.setResultStatus(101);
			return msg;
		}
		if (this.managerService.addManager(post, Integer.valueOf(bean.getEntryPlatform()!=null?bean.getEntryPlatform():"0"),Integer.valueOf(bean.getChannel()!=null?bean.getChannel():"0") ,request)) {
			msg.setResultStatus(200);
		}
		return msg;
	}
	
	/**
	 * 功能：编辑全部详情
	 * */
	@ResponseBody
	@RequestMapping(value = "/editmanager", method = RequestMethod.GET)
	public ResultMsg editManager(Integer id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.intValue() > 0) {
			ManagersBean manager = this.managerService.editManagerById(id);
			if (manager != null) {
				msg.setData(manager);
				msg.setResultStatus(200);
			}
		}
		return msg;
	}
	
	/**
	 * 功能：编辑全部详情
	 * */
	@ResponseBody
	@RequestMapping(value = "/editmanagerOnline", method = RequestMethod.GET)
	public ResultMsg editManagerOnline(Integer id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.intValue() > 0) {
			ManagerOnlineBean manager = this.managerService.editManagerOnlineById(id);
			if (manager != null) {
				msg.setData(manager);
				msg.setResultStatus(200);
			}
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkPhone", method = RequestMethod.POST)
	public ResultMsg checkPhone(String id, String phone) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("success");
		msg.setResultStatus(200);
		if (phone != null && phone.trim().length() > 0) {
			String regExp = "^1[0-9]{10}$";
			Pattern p = Pattern.compile(regExp);
			Matcher m = p.matcher(phone);
			if (m.find()) {
				List<Managers> listMan = this.managerService.readAllManagersByPhone(id, phone);
				if (listMan != null && listMan.size() > 0) {
					msg.setResultMessage("error");
					msg.setResultStatus(100);
				}
			} else {
				msg.setResultMessage("error");
				msg.setResultStatus(100);
			}
		}
		return msg;
	}
}
