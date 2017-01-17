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

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.bean.TalentBean;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.Talent;
import com.baofeng.oa.service.ITalentService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 功能：星探管理
 * */
@Controller
@RequestMapping("/talent")
public class TalentController extends BaseController {

	@Autowired
	private ITalentService talentService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/talent");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("talent/show.do"));
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, String fastArg, HttpServletRequest request) {
		NPageResult pages = null;
		pages = this.talentService.readAllPages(pageSize, curPage, sortName, sortOrder, fastArg, super.searchFilter(request));
		return pages;
	}

	/**
	 * 保存
	 * */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultMsg save(HttpServletRequest request, @RequestBody TalentBean bean) {
		ResultMsg msg = new ResultMsg();
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		Talent talent = new Talent();
		talent.setBranchs(branchs);
		talent.setAliasname(bean.getAliasname());
		talent.setBankAddress(bean.getBankAddress());
		talent.setBankCard(bean.getBankCard());
		if (bean.getId() != null && bean.getId().trim().length() > 0)
			talent.setId(Integer.valueOf(bean.getId()));
		talent.setName(bean.getName());
		talent.setPhone(bean.getPhone());
		talent.setQq(bean.getQq());
		talent.setSex(Integer.valueOf(bean.getSex()) == 1 ? Sex.WOMAN : Sex.MAN);
		talent.setGenrer(LineGenres.ONLINE);
		if (this.talentService.addTalent(talent)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
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
			TalentBean bean = this.talentService.readTalent(Integer.valueOf(id));
			if (bean != null) {
				msg.setData(bean);
				msg.setResultMessage("success");
				msg.setResultStatus(200);
			}
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
			if (this.talentService.delete(Integer.valueOf(id))) {
				msg.setResultMessage("success");
				msg.setResultStatus(200);
			}
		}
		return msg;
	}
	/**
	 * 功能 :获取姓名
	 * */
	@ResponseBody
	@RequestMapping("/findAllTalent")
	public List<Talent> findAllTalent() {
		List<Talent> list = this.talentService.findAllTalent();
		return list;
	}
}
