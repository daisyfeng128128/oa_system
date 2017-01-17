package com.baofeng.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.service.IMenuitemService;
import com.baofeng.oa.bean.BranchOfficeBean;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 功能： 子公司管理
 * */
@Controller
@RequestMapping("/branchOff")
public class BranchOfficeController extends BaseController {

	@Autowired
	private IMenuitemService menuitemService;
	@Autowired
	private IBranchOfficeService branchOfficeService;

	/** 子公司管理 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/branchOffice");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("branchOff/show.do"));
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, HttpServletRequest request) {
		NPageResult pages = null;
		pages = this.branchOfficeService.readAllPages(pageSize, curPage, sortName, sortOrder, super.searchFilter(request));
		return pages;
	}

	/**
	 * 修改
	 * */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ResultMsg edit(String id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.trim().length() > 0) {
			BranchOfficeBean bean = this.branchOfficeService.readBranch(Integer.valueOf(id));
			if (bean != null) {
				msg.setData(bean);
				msg.setResultMessage("success");
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 保存
	 * */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultMsg save(@RequestBody BranchOfficeBean bean, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		BranchOffice branch = new BranchOffice();
		if (bean.getId() != null && bean.getId().trim().length() > 0)
			branch.setId(Integer.valueOf(bean.getId()));
		branch.setAddress(bean.getAddress());
		branch.setEmail(bean.getEmail());
		branch.setBranchs(branchs);
		branch.setIslogin(Integer.valueOf(bean.getIslogin()));
		branch.setNumberHead(bean.getNumberHead());
		branch.setTelPhone(bean.getTelPhone());
		branch.setName(bean.getName());
		branch.setIpgp(bean.getIpgp());
		if (this.branchOfficeService.addBranch(branch)) {
			this.menuitemService.updateMentItemPlatforms("branchOff/show.do", branch.getIpgp(), branch.getId() + "", branch.getBranchs(), branch.getCreateDT());
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能 ：删除
	 * */
	@ResponseBody
	@RequestMapping("/delete")
	public ResultMsg delete(String id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.trim().length() > 0) {
			if (this.branchOfficeService.delete(Integer.valueOf(id))) {
				this.menuitemService.deleteMentItemPlatforms("branchOff/show.do", id + "");
				msg.setResultMessage("success");
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping("/readAllBranchs")
	public List<BranchOfficeBean> readAllBranchs() {
		List<BranchOfficeBean> list = this.branchOfficeService.readAllBranchsByLogin();
		return list;
	}

	/** 获取分公司社保基数 */
	@ResponseBody
	@RequestMapping("/editSoc")
	public ResultMsg editSoc(HttpServletRequest request, String id) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.trim().length() > 0) {
			JSONObject socJson = this.branchOfficeService.readBranchOfficeSoc(Integer.valueOf(id));
			if (socJson != null) {
				msg.setResultStatus(200);
				msg.setData(socJson);
			}
		}

		return msg;
	}

	/** 保存社保基数 */
	@ResponseBody
	@RequestMapping("/saveSoc")
	public ResultMsg saveSoc(HttpServletRequest request, @RequestBody BranchOfficeBean bean) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (bean != null && bean.getId() != null && bean.getId().trim().length() > 0) {
			if (this.branchOfficeService.updateBranchs(bean)) {
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

}
