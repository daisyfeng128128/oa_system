package com.baofeng.oa.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
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
import com.baofeng.commons.service.IRolesService;
import com.baofeng.oa.bean.ActoresBean;
import com.baofeng.oa.bean.ActoresOfflineBean;
import com.baofeng.oa.bean.ActoresOnlineBean;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.ActoresSigned;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.service.IActoresService;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 功能： 艺人管理
 * */

@Controller
@RequestMapping("/actoresOnline")
public class ActoresController extends BaseController {
	@Autowired
	private IActoresService actoresService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IRolesService rolesService;

	/**
	 * 功能：快捷链接
	 * */
	@RequestMapping(value = "/showOnlineLink", method = RequestMethod.GET)
	public ModelAndView showOnlineLink(Integer branchs,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/actoresOnline");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("actoresOnline/showOnlineLink.do"));
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("toUrl", String.valueOf("actoresOnline/showOnlineLink.do"));
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/**
	 * 功能：快捷链接
	 * */
	@RequestMapping(value = "/showOfflineLink", method = RequestMethod.GET)
	public ModelAndView showOfflineLink(Integer branchs,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/actoresOffline");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("actoresOnline/showOfflineLink.do"));
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("toUrl", String.valueOf("actoresOnline/showOfflineLink.do"));
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	@RequestMapping(value = "/showOnline", method = RequestMethod.GET)
	public ModelAndView showOnline(Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/actoresOnline");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("actoresOnline/showOnline.do"));
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("toUrl", String.valueOf("actoresOnline/showOnline.do"));
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;

	}

	@RequestMapping(value = "/showOffline", method = RequestMethod.GET)
	public ModelAndView showOffline(Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/actoresOffline");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("actoresOnline/showOffline.do"));
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("toUrl", String.valueOf("actoresOnline/showOffline.do"));
		mav.addObject("attsList", attsList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	@RequestMapping(value = "/showLeave", method = RequestMethod.GET)
	public ModelAndView showLeave(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/actoresLeave");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("actoresOnline/showLeave.do"));
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 功能：直播系统艺人分页
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPagesActoresOnline", method = RequestMethod.POST)
	public NPageResult<?> readPagesPlatformActores(int pageSize, int curPage, String sortName, String sortOrder, String genre, String isleave, String toUrl, String srealname,
			String saliasname, String sphone, String fastArg, String snumber, Integer branchs, HttpServletRequest request) {
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (users != null) {
			List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
			List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf(toUrl));
			NPageResult<?> pages = this.actoresService.readPagesActoresOnline(pageSize, curPage, sortName, sortOrder, genre, isleave, super.searchFilter(request, branchs),
					platList, attsList, users, srealname, saliasname, sphone, fastArg, snumber);
			return pages;
		}
		return null;
	}
	
	/**
	 * 功能：直播系统离职艺人分页
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPagesActoresLeave", method = RequestMethod.POST)
	public NPageResult<?> readPagesActoresLeave(int pageSize, int curPage, String sortName, String sortOrder, Integer branchs, HttpServletRequest request) {
		NPageResult<?> pages = null;
		pages = this.actoresService.readPagesActoresLeave(pageSize, curPage, sortName, sortOrder, super.searchFilter(request, branchs));
		return pages;
	}

	/**
	 * 功能：查找员工或艺人
	 * */
	@ResponseBody
	@RequestMapping(value = "/empEdit", method = RequestMethod.GET)
	public ResultMsg empEdit(Integer id, String num, String loadTable) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.intValue() > 0 && num.length() > 0) {
			Integer number = new Integer(num);
			int i = number.intValue();
			if (this.actoresService.readActoresByNum(i)) {
				msg.setResultStatus(101);
				msg.setData("");
				return msg;
			}

			if (loadTable.equals(Employee.class.getName())) {
				Employee emp = this.employeeService.readEmployee(id);
				if (emp != null) {
					ActoresBean bean = new ActoresBean();
					bean.setId(emp.getId());
					bean.setRealname(emp.getName());
					bean.setAliasname(emp.getAliasname());
					bean.setPhone(emp.getPhone());
					bean.setQq(emp.getQq());
					bean.setIdcard(emp.getIdcard());
					bean.setSex(emp.getSex().toString());
					bean.setEntryTime(emp.getJoinDate());
					bean.setGenre(LineGenres.OFFLINE.toString());
					bean.setGenrer(emp.getGenrer().toString());
					Float $salary = emp.getSalary() == null ? 0.00f : emp.getSalary();
					Float $probationSalary = emp.getProbationSalary() == null ? 0.00f : emp.getProbationSalary();
					Float $otherSubsidy = emp.getOtherSubsidy() == null ? 0.00f : emp.getOtherSubsidy();
					bean.setProbationSalary($salary + $probationSalary);
					bean.setBasicSalary($salary + $otherSubsidy);
					bean.setLoadTable(String.valueOf(1));
					msg.setResultStatus(200);
					msg.setData(bean);
				}
			}
		}
		return msg;
	}

	/**
	 * 功能：修改视频链接
	 * */
	@ResponseBody
	@RequestMapping(value = "/editActoresLinkUrl", method = RequestMethod.POST)
	public ResultMsg editActoresLinkUrl(Integer id, String linkUrl) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Actores post = new Actores();
		post.setId(id);
		post.setLinkUrl(linkUrl);
		if (this.actoresService.addActoresLinkUrl(post)) {
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能：添加修改
	 * */
	@ResponseBody
	@RequestMapping(value = "/saveActores", method = RequestMethod.POST)
	public ResultMsg saveActores(Integer id, String linkUrl, Integer empid, Integer platId, Integer channelsId, String loadTable, String realname, String aliasname, String phone,
			String qq, String sex, String genre, String settl, String entryTime, String beLiveTime, Integer probation, Integer probationSalary, Integer minimumGuarantee,
			Integer basicSalary, Integer pushMoney, String t, String signed, @RequestParam MultipartFile[] idImage, String idcard, String address, String bankCard,
			String bankAddress, String shareBankCard, String shareBankAddress, Integer plat, Integer channel, String beliveTime, HttpServletRequest request) throws Exception {
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Actores post = new Actores();
		post.setId(id);
		post.setBranchs(branchs);
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
					String path = Constants.DEFAULT_UPLOADIMAGEPATH + "/" + Constants.sha1ToPath(sha1, "/");
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
		if (post.getGenre() == LineGenres.ONLINE && this.actoresService.checkActores(post)) {
			msg.setResultStatus(101);
			return msg;
		}
		if (this.actoresService.addLastNewActores(post, pushMoney, empid, beLiveTime, loadTable, channel, plat, beliveTime, request)) {
			msg.setResultStatus(200);
		}

		return msg;
	}

	/**
	 * 功能：编辑查看全部
	 * */
	@ResponseBody
	@RequestMapping(value = "/editActores", method = RequestMethod.GET)
	public ResultMsg edit(Integer id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.intValue() > 0) {
			ActoresOnlineBean pact = this.actoresService.editActoresById(id);
			if (pact != null) {
				msg.setData(pact);
				msg.setResultStatus(200);
			}
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value = "/editActoresoffline", method = RequestMethod.GET)
	public ResultMsg editOffline(Integer id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.intValue() > 0) {
			ActoresOfflineBean pact = this.actoresService.editActoresofflineById(id);
			if (pact != null) {
				msg.setData(pact);
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能：编辑
	 * */
	@ResponseBody
	@RequestMapping(value = "/editActoresByphone", method = RequestMethod.GET)
	public ResultMsg editActoresByphone(String phone) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (phone != null && phone.length() > 0) {
			Actores post = this.actoresService.readActoresByPhone(phone);
			if (post != null) {
				ActoresOnlineBean bean = new ActoresOnlineBean();
				bean.setId(post.getId());
				if (post.getNumber() != null && post.getNumber().intValue() > 0)
					bean.setNumber(String.format("%04d", post.getNumber()));
				else
					bean.setNumber("N/A");

				bean.setAddress(post.getAddress());
				bean.setPhone(post.getPhone());
				bean.setQq(post.getQq());
				bean.setIdcard(post.getIdcard());
				bean.setRealname(post.getRealname());
				bean.setAliasname(post.getAliasname());
				bean.setEmail(post.getEmail());
				bean.setEntryTime(post.getEntryTime());
				bean.setBankAddress(post.getBankAddress());
				bean.setBankCard(post.getBankCard());
				bean.setShareBankAddress(post.getShareBankAddress());
				bean.setShareBankCard(post.getShareBankCard());
				bean.setSex(post.getSex() == Sex.MAN ? "男" : "女");
				bean.setGenre(post.getGenre() == LineGenres.ONLINE ? "线上艺人" : "线下艺人");

				if (post.getGenrer() == Genres.SHIYONG) {
					bean.setGenrer("试用");
				}
				if (post.getGenrer() == Genres.LIZHI) {
					bean.setGenrer("离职");
				}

				if (post.getProbation() != null) {
					bean.setProbation(post.getProbation().intValue() == 0 ? "-" : post.getProbation().toString());
				} else {
					bean.setProbation("-");
				}
				bean.setCreateDT(post.getCreateDT());

				bean.setProbationSalary(post.getProbationSalary() == null ? "0.00" : String.format("%.2f", post.getProbationSalary()));
				bean.setBasicSalary(post.getBasicSalary() == null ? "0.00" : String.format("%.2f", post.getBasicSalary()));
				bean.setMinimumGuarantee(post.getMinimumGuarantee() == null ? "0.00" : String.format("%.2f", post.getMinimumGuarantee()));
				bean.setPushMoney(post.getPushMoney() == null ? "-" : String.format("%d%% %n", post.getPushMoney()));
				bean.setSigned(post.getLabour() == ActoresLabour.SYSTEM ? "公司签约" : (post.getSigned() == ActoresSigned.YES ? "经济签约" : "还未签约"));
				if (bean != null) {
					msg.setData(bean);
					msg.setResultStatus(200);
				}
			}
		}
		return msg;
	}

	/**
	 * 功能：艺人转正
	 * */
	@ResponseBody
	@RequestMapping(value = "/updateProbationer", method = RequestMethod.POST)
	public ResultMsg updateProbationer(Integer id, Integer type, String reason, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("errors");
		if (this.actoresService.updateActorsOnline(id, type, reason, request)) {
			msg.setResultStatus(200);
			msg.setResultMessage("success");
		}
		return msg;
	}

	/**
	 * 功能：艺人离职
	 * */
	@RequestMapping(value = "/updateActoresLeave", method = RequestMethod.POST)
	public ModelAndView updateActoresLeave(Integer id, Integer type, String reason, String t, HttpServletRequest request) {
		ModelAndView mav = null;
		if ("OFFLINE".equals(t)) {
			mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/actoresOffline");
			List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("actoresOnline/showOffline.do"));
			mav.addObject("attsList", attsList);
		} else {
			mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/actoresOnline");
			List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("actoresOnline/showOnline.do"));
			mav.addObject("attsList", attsList);
		}
		if (this.actoresService.updateActorsOnline(id, type, reason, request)) {
			mav.addObject("result", "success");
		}
		return mav;
	}

	/**
	 * 功能：删除
	 * */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResultMsg delete(Integer id, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("errors");
		if (this.actoresService.deleteActores(id, request)) {
			msg.setResultStatus(200);
			msg.setResultMessage("success");
		}
		return msg;
	}

}
