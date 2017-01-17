package com.baofeng.oa.controller;

import java.io.File;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.oa.bean.InterviewBean;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.Account;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.EmployeType;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Department;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.Interview;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.Positions;
import com.baofeng.oa.service.IActoresService;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IDepartmentService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IInterviewService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.oa.service.IPositionsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.ResultMsg;
import com.baofeng.utils.SearchFilter;

@Controller
@RequestMapping("/interview")
public class InterviewController extends BaseController {

	@Autowired
	private IInterviewService interviewService;
	@Autowired
	private IPlatformsService platformsService;
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IPositionsService positionsService;
	@Autowired
	private IActoresService actoresService;
	@Autowired
	private IBranchOfficeService branchOfficeService;

	/** 跳转 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(String date, Integer branchs, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/interviewHref");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("interview/show.do"));
		if (date == null) {
			List<String> dateList = this.interviewService.readAllInterviewDate();
			if (dateList != null && dateList.size() > 0) {
				date = dateList.get(0);
			}
		}
		date = date == null ? String.format("%s-%s", String.format("%tY", new Date()), String.format("%tm", new Date())) : date;
		String dateMsg = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("date", date);
		mav.addObject("dateMsg", dateMsg);
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/** 面试总表 */
	@RequestMapping(value = "/showAll", method = RequestMethod.GET)
	public ModelAndView showAll(String date, Integer branchs, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/interviewAll");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("interview/show.do"));
		if (date == null) {
			List<String> dateList = this.interviewService.readAllInterviewDate();
			if (dateList != null && dateList.size() > 0) {
				date = dateList.get(0);
			}
		}

		date = date == null ? String.format("%s-%s", String.format("%tY", new Date()), String.format("%tm", new Date())) : date;
		String dateMsg = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("date", date);
		mav.addObject("dateMsg", dateMsg);
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);

		return mav;
	}

	/** 平台审核 */
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public ModelAndView checkShow(String date, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/interviewCheck");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("interview/show.do"));
		if (date == null) {
			List<String> dateList = this.interviewService.readAllInterviewDate();
			if (dateList != null && dateList.size() > 0) {
				date = dateList.get(0);
			}
		}
		date = date == null ? String.format("%s-%s", String.format("%tY", new Date()), String.format("%tm", new Date())) : date;
		String dateMsg = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("date", date);
		mav.addObject("dateMsg", dateMsg);
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/** 待处理 */
	@RequestMapping(value = "/pending", method = RequestMethod.GET)
	public ModelAndView pendingShow(String date, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/interviewPending");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("interview/show.do"));
		if (date == null) {
			List<String> dateList = this.interviewService.readAllInterviewDate();
			if (dateList != null && dateList.size() > 0) {
				date = dateList.get(0);
			}
		}
		date = date == null ? String.format("%s-%s", String.format("%tY", new Date()), String.format("%tm", new Date())) : date;
		String dateMsg = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("date", date);
		mav.addObject("dateMsg", dateMsg);
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/** 待入职 */
	@RequestMapping(value = "/waitEntry", method = RequestMethod.GET)
	public ModelAndView waitShow(String date, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/interviewWaitEntry");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("interview/show.do"));
		if (date == null) {
			List<String> dateList = this.interviewService.readAllInterviewDate();
			if (dateList != null && dateList.size() > 0) {
				date = dateList.get(0);
			}
		}
		date = date == null ? String.format("%s-%s", String.format("%tY", new Date()), String.format("%tm", new Date())) : date;
		String dateMsg = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("date", date);
		mav.addObject("dateMsg", dateMsg);
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/** 未通过 */
	@RequestMapping(value = "/notpass", method = RequestMethod.GET)
	public ModelAndView notShow(String date, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/interviewNotPass");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("interview/show.do"));
		if (date == null) {
			List<String> dateList = this.interviewService.readAllInterviewDate();
			if (dateList != null && dateList.size() > 0) {
				date = dateList.get(0);
			}
		}
		date = date == null ? String.format("%s-%s", String.format("%tY", new Date()), String.format("%tm", new Date())) : date;
		String dateMsg = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("date", date);
		mav.addObject("dateMsg", dateMsg);
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/** 已通过 */
	@RequestMapping(value = "/ypass", method = RequestMethod.GET)
	public ModelAndView passShow(String date, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/interviewPass");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("interview/show.do"));
		if (date == null) {
			List<String> dateList = this.interviewService.readAllInterviewDate();
			if (dateList != null && dateList.size() > 0) {
				date = dateList.get(0);
			}
		}
		date = date == null ? String.format("%s-%s", String.format("%tY", new Date()), String.format("%tm", new Date())) : date;
		String dateMsg = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("date", date);
		mav.addObject("dateMsg", dateMsg);
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/** 初试 */
	@RequestMapping(value = "/preTest", method = RequestMethod.GET)
	public ModelAndView preTestShow(String date, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/interviewPreTest");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("interview/show.do"));
		if (date == null) {
			List<String> dateList = this.interviewService.readAllInterviewDate();
			if (dateList != null && dateList.size() > 0) {
				date = dateList.get(0);
			}
		}
		date = date == null ? String.format("%s-%s", String.format("%tY", new Date()), String.format("%tm", new Date())) : date;
		String dateMsg = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("date", date);
		mav.addObject("dateMsg", dateMsg);
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/**
	 * 功能：获取头部数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/readBaseTabCommon", method = RequestMethod.POST)
	public ResultMsg readBaseTabCommon(HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		List<String> dateList = this.interviewService.readAllInterviewDate();
		List<RoleDetailsAtts> areaList = super.platformsRoleDetails(request, String.valueOf("branchOff/show.do"));
		Object[] rest = new Object[2];
		rest[0] = dateList;
		rest[1] = areaList;
		msg.setData(rest);
		msg.setResultStatus(200);
		return msg;
	}

	/**
	 * 功能： 获取分页数据
	 * */

	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, String date, Integer branchs, Integer type, String fastArg,
			HttpServletRequest request) {
		boolean flag = super.isHaveAttribute(request, String.valueOf("interview/show.do"), "all");
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		NPageResult<?> pages = null;
		if (users != null) {
			List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
			pages = this.interviewService.readPages(pageSize, curPage, sortName, sortOrder, date, type, fastArg, platList, super.searchFilter(request, branchs), flag);
		}
		return pages;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPagesSkip", method = RequestMethod.POST)
	public PageResult<?> readPagesSkip(int page, int rows, String filter, HttpServletRequest request) {
		PageResult<?> pages = null;
		SearchFilter $filter = new SearchFilter();
		pages = this.interviewService.readAllPagesSkip(rows, page, $filter, filter);
		return pages;
	}

	/**
	 * 功能：调整平台
	 * */
	@ResponseBody
	@RequestMapping("addPlat")
	public ResultMsg addPlat(String id, String chTransition, String plat) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		Interview inter = new Interview();
		Platforms $plat = null;
		if (plat != null && plat.trim().length() > 0) {
			$plat = this.platformsService.readPlatforms(Integer.valueOf(plat));
		}
		inter.setPlat($plat);
		if (id != null && id.trim().length() > 0)
			inter.setId(Integer.valueOf(id));
		inter.setChTransition(chTransition);
		if (this.interviewService.addPlat(inter)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;

	}

	/**
	 * 功能：添加修改
	 * */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultMsg save(String id, String linkUrl, String name, String aliasname, String sex, String plat, String phone, String qq, String introducer, String chTransition,
			String genre, String remark, @RequestParam MultipartFile[] photo, @RequestParam MultipartFile[] videoPhoto, HttpServletRequest request) throws Exception {
		RoleUsers users = (RoleUsers) request.getSession().getAttribute(Constants.CURRENT_USER);
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		Interview post = new Interview();
		post.setBranchs(branchs);
		if (photo != null && photo.length > 0) {
			for (MultipartFile $file : photo) {
				if (!$file.isEmpty()) {
					String $name = $file.getOriginalFilename();
					String ext = $name.substring($name.lastIndexOf("."), $name.length());
					String sha1 = DigestUtils.sha1Hex($file.getInputStream());
					String fileName = sha1 + ext;
					String path = Constants.DEFAULT_UPLOADIMAGEPATH + "/" + Constants.sha1ToPath(sha1, "/");
					Constants.mkdirs(path);
					FileUtils.copyInputStreamToFile($file.getInputStream(), new File(path, fileName));
					post.setPhoto(Constants.sha1ToPath(sha1, "/") + "/" + fileName);
				}
			}
		}
		if (videoPhoto != null && videoPhoto.length > 0) {
			for (MultipartFile $file : videoPhoto) {
				if (!$file.isEmpty()) {
					String $name = $file.getOriginalFilename();
					String ext = $name.substring($name.lastIndexOf("."), $name.length());
					String sha1 = DigestUtils.sha1Hex($file.getInputStream());
					String fileName = sha1 + ext;
					String path = Constants.DEFAULT_UPLOADIMAGEPATH + "/" + Constants.sha1ToPath(sha1, "/");
					Constants.mkdirs(path);
					FileUtils.copyInputStreamToFile($file.getInputStream(), new File(path, fileName));
					post.setVideoPhoto(Constants.sha1ToPath(sha1, "/") + "/" + fileName);
				}
			}
		}
		if (id != null && id.trim().length() > 0) {
			post.setId(Integer.valueOf(id));
		}
		post.setAliasname(aliasname);
		post.setChTransition(chTransition);
		post.setGenre(Enum.valueOf(LineGenres.class, genre));
		post.setIntroducer(introducer);
		post.setLinkUrl(linkUrl);
		post.setName(name);
		post.setPhone(phone);
		if (plat != null && plat.trim().length() > 0) {
			Platforms platforms = this.platformsService.readPlatforms(Integer.valueOf(plat));
			post.setPlat(platforms);
		}

		post.setQq(qq);
		post.setRemark(remark);
		post.setSex(Enum.valueOf(Sex.class, sex));
		post.setStatusTracking(users.getUser().getAccounts() + ":创建了此面试人");

		if (this.interviewService.addInterview(post)) {
			msg.setResultStatus(200);
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping("/edit")
	public ResultMsg edit(String id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(200);
		Interview inter = this.interviewService.readInterview(id);
		if (inter != null) {
			InterviewBean bean = new InterviewBean();
			bean.setLaunchTime(inter.getLaunchTime());
			bean.setAliasname(inter.getAliasname());
			bean.setChTransition(inter.getChTransition() != null ? inter.getChTransition() : "无");
			bean.setGenre(inter.getGenre().toString());
			bean.setId(inter.getId().toString());
			bean.setIntroducer(inter.getIntroducer());
			bean.setLinkUrl(inter.getLinkUrl());
			bean.setName(inter.getName());
			bean.setPhone(inter.getPhone());
			if (inter != null && inter.getPlat() != null) {
				bean.setPlat(inter.getPlat().getPlatName());
				bean.setPlatId(inter.getPlat().getId().toString());
			} else {
				bean.setPlat("无");
				bean.setPlatId("");
			}
			bean.setQq(inter.getQq());
			bean.setRemark(inter.getRemark());
			bean.setSex(inter.getSex().toString());
			bean.setTrackingResults(inter.getTrackingResults());
			msg.setData(bean);
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能：删除
	 * */
	@ResponseBody
	@RequestMapping("/delete")
	public ResultMsg delete(String id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		if (this.interviewService.delete(id)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能：线下入职
	 * */
	@ResponseBody
	@RequestMapping(value = "/empsave", method = RequestMethod.POST)
	public ResultMsg save(Integer id, String name, String password, String namepy, Integer sex, String actores, String idcard, String hostRegister, String aliasname, String birth,
			String phone, String qq, String email, String liveAdress, String hostAddress, String emergencyContact, String emergencyPhone, Integer depid, Integer posid,
			String regularDate, Integer empid, String introducer, String joinDate, Integer genrer, String contractDT, String cpfAccounts, String account, String bankCard,
			String bankAddress, String probationSalary, String salary, String otherSubsidy, String PFcardinalNumber, String interId, String SScardinalNumber, String shareBankCard,
			String plat, String shareBankAddress, String pushMoney, String jiaojin, String speciality, String remarks, HttpServletRequest request) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		Employee post = new Employee();
		post.setBranchs(branchs);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (id != null && id > 0) {
			post.setId(id);
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
		post.setCpfAccounts(cpfAccounts);
		post.setBankCard(bankCard);
		post.setBankAddress(bankAddress);
		post.setSalary(Float.valueOf(salary));
		if (probationSalary != null) {
			post.setProbationSalary(Float.valueOf(probationSalary));
		}
		post.setOtherSubsidy(Float.valueOf(otherSubsidy));
		post.setShareBankCard(shareBankCard);
		post.setShareBankAddress(shareBankAddress);
		post.setSpeciality(speciality);
		post.setRemarks(remarks);
		post.setEmployeType(EmployeType.ACTORES);
		if (pushMoney != null && pushMoney.trim().length() > 0) {
			post.setPushMoney(new BigDecimal(pushMoney));
		}

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
		if (genrer == 1) {
			post.setGenrer(Genres.SHIYONG);
		} else if (genrer == 2) {
			post.setGenrer(Genres.ZHENGSHI);
		} else if (genrer == 3) {
			post.setGenrer(Genres.PENDING);
		} else {
			post.setGenrer(Genres.LIZHI);
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
			if (this.interviewService.addInterviewSuccess(interId, plat)) {
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能 ： 未入职
	 * */
	@ResponseBody
	@RequestMapping("/noEntry")
	public ResultMsg noEntry(String id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		if (this.interviewService.addNoEntry(id)) {
			msg.setResultMessage("success");
		}
		return msg;
	}

	/**
	 * 功能：平台审核 拒收
	 * */
	@ResponseBody
	@RequestMapping("/addReje")
	public ResultMsg addReje(String id, String track, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		msg.setResultMessage("error");
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (users != null) {
			if (this.interviewService.addReje(id, track, users)) {
				msg.setResultStatus(200);
				msg.setResultMessage("success");
			}
		}

		return msg;
	}

	/**
	 * 功能： 平台审核录取
	 * */
	@ResponseBody
	@RequestMapping("/addEmpl")
	public ResultMsg addEmpl(String id, String launchTime, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		msg.setResultMessage("error");
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (users != null) {
			if (this.interviewService.addEmpl(id, launchTime, users)) {
				msg.setResultStatus(200);
				msg.setResultMessage("success");
			}
		}

		return msg;
	}

	/**
	 * 功能：线上录用
	 * */
	@ResponseBody
	@RequestMapping(value = "/saveActores", method = RequestMethod.POST)
	public ResultMsg saveActores(Integer id, String linkUrl, Integer empid, Integer platId, Integer channelsId, String loadTable, String realname, String aliasname, String phone,
			String qq, String sex, String genre, String settl, String entryTime, String beLiveTime, Integer probation, Integer probationSalary, Integer minimumGuarantee,
			Integer basicSalary, Integer pushMoney, String t, String signed, @RequestParam MultipartFile[] idImage, String idcard, String address, String bankCard,
			String bankAddress, String interId, String shareBankCard, String shareBankAddress, Integer channel, Integer plat, String beliveTime, HttpServletRequest request)
			throws Exception {
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		Actores post = new com.baofeng.oa.entity.Actores();
		post.setBranchs(branchs);
		post.setId(id);
		post.setOperator(users.getUser());
		if (loadTable.equals(Employee.class.getName())) {
			if (empid != null && empid.intValue() > 0) {
				Employee emp = this.employeeService.readEmployee(empid);
				if (emp != null) {
					post.setEmployee(emp);
					post.setNumber(emp.getNumber());
					post.setLabour(ActoresLabour.SYSTEM);
					if (emp.getGenrer() == Genres.ZHENGSHI) {
						post.setGenrer(Genres.ZHENGSHI);
					} else {
						post.setGenrer(Genres.SHIYONG);
					}
				}
			}
		}
		if (idImage != null && idImage.length > 0) {
			for (MultipartFile $file : idImage) {
				if (!$file.isEmpty()) {
					String $name = $file.getOriginalFilename();
					String ext = $name.substring($name.lastIndexOf("."), $name.length());
					String sha1 = DigestUtils.sha1Hex($file.getInputStream());
					String fileName = sha1 + ext;
					String path = Constants.DEFAULT_UPLOADIMAGEPATH + File.separator + Constants.sha1ToPath(sha1, File.separator);
					Constants.mkdirs(path);
					FileUtils.copyInputStreamToFile($file.getInputStream(), new File(path, fileName));
					post.setIdImage(Constants.sha1ToPath(sha1, "/") + "/" + fileName);
				}
			}
		}
		post.setLinkUrl(linkUrl);
		post.setRealname(realname);
		post.setAliasname(aliasname);
		post.setPhone(phone);
		post.setQq(qq);
		post.setSex(Enum.valueOf(Sex.class, sex));
		if ("OFFLINE".equals(t)) {
			post.setProbationSalary(Float.valueOf(probationSalary));
			post.setGenre(LineGenres.OFFLINE);
		} else {
			post.setGenrer(Genres.ZHENGSHI);
			post.setGenre(LineGenres.ONLINE);
		}
		if (minimumGuarantee != null)
			post.setMinimumGuarantee(Float.valueOf(minimumGuarantee));
		if (basicSalary != null)
			post.setBasicSalary(Float.valueOf(basicSalary));
		post.setPushMoney(pushMoney);
		post.setIdcard(idcard);
		post.setAddress(address);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (entryTime != null && entryTime.trim().length() > 0) {
			Date date = sdf.parse(entryTime);
			post.setEntryTime(date);
		}

		post.setShareBankCard(shareBankCard);
		post.setShareBankAddress(shareBankAddress);
		post.setBankCard(bankCard);
		post.setBankAddress(bankAddress);
		if (this.actoresService.addLastNewActores(post, pushMoney, empid, beLiveTime, loadTable, channel, plat, beliveTime, request)) {
			if (this.interviewService.addInter(interId, users, beliveTime)) {
				msg.setResultStatus(200);
			}
		}

		return msg;
	}

	/**
	 * 功能 ： 未通过 设置回访结果
	 * */
	@ResponseBody
	@RequestMapping("/addTrackingResults")
	public ResultMsg addTrackingResults(String id, String trackingResults) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		if (this.interviewService.addTrackingResults(id, trackingResults)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能 : 初试表未通过
	 * */
	@ResponseBody
	@RequestMapping("/noPassPre")
	public ResultMsg noPassPre(String id, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (users != null) {
			if (this.interviewService.updateNoPassPre(id, users)) {
				msg.setResultMessage("success");
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 初试表 通过
	 * */
	@ResponseBody
	@RequestMapping("/addPrePass")
	public ResultMsg addPrePass(String id, String chTransition, String plat, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		msg.setResultMessage("error");
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (id != null && id.trim().length() > 0) {
			if (this.interviewService.addPrePass(id, chTransition, plat, users)) {
				msg.setResultMessage("success");
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 导出Excel
	 * */
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, String date) throws Exception {
		/** 查询面试总表 */
		List<Interview> listOff = this.interviewService.readAllInterview(date, super.searchFilter(request));
		HSSFWorkbook wb = this.interviewService.export(listOff);
		response.setContentType("application/vnd.ms-excel");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String $date = sdf.format(new Date());
		response.setHeader("Content-disposition", "attachment;filename=" + $date + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

}
