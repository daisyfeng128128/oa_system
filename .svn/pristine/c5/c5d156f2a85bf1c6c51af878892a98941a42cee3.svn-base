package com.baofeng.oa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.baofeng.oa.bean.PlatformsManagerBean;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.Managers;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsChannels;
import com.baofeng.oa.entity.PlatformsManager;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IManagerService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsManagerService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 功能： 频道助理
 * */
@Controller
@RequestMapping("/platManager")
public class PlatformsManagerController extends BaseController {

	@Autowired
	private IPlatformsActoresService actoresService;
	@Autowired
	private IPlatformsService platformsService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IManagerService managerService;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IPlatformsManagerService platformsManagerService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(Integer platId, Integer branchs, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/platManager");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("platManager/show.do"));
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		branchs = branchs == null ? super.getSessionAttribute(request, Constants.CURRENT_BRANCHS) : branchs;
		String branchsMsg = String.valueOf("");
		BranchOffice boffice = this.branchOfficeService.readBranchOffice(branchs);
		if (boffice != null)
			branchsMsg = boffice.getIpgp();
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		mav.addObject("platId", platId);
		mav.addObject("platList", platList);
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, Integer platId, String fastArg, Integer branchs, HttpServletRequest request) {
		NPageResult<?> pages = this.platformsManagerService.readPages(pageSize, curPage, sortName, sortOrder, platId, fastArg, super.searchBranchsFilter(request, branchs));
		return pages;
	}

	/**
	 * 功能：添加修改
	 * */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultMsg save(Integer platId, String channelId, Integer managerId, HttpServletRequest request) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		PlatformsManager post = new PlatformsManager();
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		post.setBranchs(branchs);
		if (channelId != null && channelId.trim().length() > 0) {
			PlatformsChannels channel = this.platformsManagerService.readPlatformsChannels(Integer.valueOf(channelId));
			post.setChannel(channel);
		}
		if (platId != null && platId.intValue() > 0) {
			Platforms plat = this.platformsService.readPlatforms(platId);
			post.setPlat(plat);
		}
		if (managerId != null && managerId.intValue() > 0) {
			Managers manager = this.managerService.readManagers(managerId);
			post.setManager(manager);
		}
		if (this.platformsManagerService.addPlatManager(post, request)) {
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能：编辑
	 * */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public PlatformsManager read(Integer id) {
		PlatformsManager platformsManager = this.platformsManagerService.readPlatformsManager(id);
		if (platformsManager != null) {
			return platformsManager;
		}
		return null;
	}

	/**
	 * 功能：查找员工或艺人
	 * */
	@ResponseBody
	@RequestMapping(value = "/empEdit", method = RequestMethod.GET)
	public ResultMsg read(Integer id, Integer loadTable, Integer platId, Integer channelId) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.intValue() > 0 && platId != null && platId.intValue() > 0) {
			if (!this.platformsManagerService.readPlatformsManager(id, platId, channelId)) {
				if (loadTable != null && loadTable == 1) {
					Employee post = this.employeeService.readEmployee(id);
					if (post != null) {
						PlatformsManagerBean bean = new PlatformsManagerBean();
						bean.setId(post.getId());
						bean.setAliasname(post.getAliasname());
						bean.setIdcard(post.getIdcard());
						bean.setIdImage(post.getIdImage());
						bean.setAddress(post.getLiveAdress());
						bean.setRealname(post.getName());
						bean.setEntryTime(post.getJoinDate());
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
				} else if (loadTable != null && loadTable == 2) {
					Actores post = this.actoresService.readActores(id);
					if (post != null) {
						PlatformsManagerBean bean = new PlatformsManagerBean();
						bean.setId(post.getId());
						bean.setAliasname(post.getAliasname());
						bean.setIdcard(post.getIdcard());
						bean.setIdImage(post.getIdImage());
						bean.setAddress(post.getAddress());
						bean.setRealname(post.getRealname());
						bean.setEntryTime(post.getEntryTime());
						msg.setData(bean);
					}
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
		if (this.platformsManagerService.deletePlatformsManager(id, request)) {
			msg.setResultStatus(200);
			msg.setResultMessage("success");
		}
		return msg;
	}

	/**
	 * 功能：验证是否已存在
	 * */
	@ResponseBody
	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public ResultMsg verify(Integer id, Integer platId, String channelId) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (channelId != null && !channelId.equals("undefined")) {
			if (this.platformsManagerService.verify(id, platId, Integer.valueOf(channelId))) {
				msg.setResultStatus(200);
				msg.setResultMessage("不可重复添加");
			}
		} else {
			if (this.platformsManagerService.verify(id, platId, null)) {
				msg.setResultStatus(200);
				msg.setResultMessage("不可重复添加");
			}
		}
		return msg;
	}

	public static void main(String[] args) {
		String brefe = String.format(Locale.US, "%tj", Constants.convert("2015-08-01", Constants.format1));
		String after = String.format(Locale.US, "%tj", new Date());
		System.out.println(Integer.valueOf(brefe) - Integer.valueOf(after));
	}

	/**
	 * 功能：搜索用户
	 * */
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public List<PlatformsManagerBean> seachOperator(@RequestBody PlatformsManagerBean bean, HttpServletRequest request) {
		List<PlatformsManagerBean> result = new ArrayList<PlatformsManagerBean>();
		Integer branchId = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		if (bean != null && bean.getRealname().trim().length() > 0) {
			result = this.platformsManagerService.readPlatformsManagerBeanList(bean.getRealname(), super.searchFilter(request), branchId);
		}
		return result;
	}

	/**
	 * 功能：获取头部数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/readBaseTabCommon", method = RequestMethod.POST)
	public ResultMsg readBaseTabCommon(HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		List<RoleDetailsAtts> areaList = super.platformsRoleDetails(request, String.valueOf("branchOff/show.do"));
		msg.setData(areaList);
		msg.setResultStatus(200);
		return msg;
	}
}
