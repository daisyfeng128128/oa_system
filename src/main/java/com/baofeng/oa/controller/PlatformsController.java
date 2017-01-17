package com.baofeng.oa.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.baofeng.commons.service.IMenuitemService;
import com.baofeng.oa.bean.PlatformsBean;
import com.baofeng.oa.entity.BaseEnums.PlatIncomeGenre;
import com.baofeng.oa.entity.BaseEnums.PlatformGenre;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsChannels;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.PageResult;
import com.baofeng.utils.ResultMsg;
import com.baofeng.utils.SearchFilter;

/**
 * 直播平台
 */
@Controller
@RequestMapping("/plats")
public class PlatformsController extends BaseController {

	@Autowired
	private IPlatformsService platformsService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IMenuitemService menuitemService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(String gid, String linktype, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/platforms");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("plats/show.do"));
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 功能：平台列表
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPagesPlat", method = RequestMethod.POST)
	public NPageResult<?> readPagesPlat(int pageSize, int curPage, String sortName, String sortOrder, Integer did, String filter, HttpServletRequest request) {
		NPageResult<?> pages = this.platformsService.PagesPlat(pageSize, curPage, sortName, sortOrder, did, null, filter);
		return pages;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, String name, HttpServletRequest request) {
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("plats/show.do"));
		NPageResult pages = this.platformsService.readAllPages(pageSize, curPage, sortName, sortOrder, name, platList, attsList, null);
		return pages;
	}

	/**
	 * 功能：添加修改
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(Integer id, String platName, Integer formGenre, String[] channels, Integer empid, Integer empids, Integer incomeGenre, String remarks, String tax,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/platforms");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("plats/show.do"));
		mav.addObject("attsList", attsList);
		Platforms post = new Platforms();
		if (id != null && id.intValue() > 0)
			post.setId(id);
		post.setPlatName(platName);
		if (formGenre != null) {
			post.setFormGenre(formGenre == 1 ? PlatformGenre.ZHIBOJIAN : PlatformGenre.PAIMAI);
		}
		if (incomeGenre != null) {
			post.setIncomeGenre(incomeGenre == 0 ? PlatIncomeGenre.SHUIQIAN : incomeGenre == 1 ? PlatIncomeGenre.SHUIHOU : PlatIncomeGenre.KAIPIAOSHUI);
		}
		if (empid != null) {
			Employee emp = this.employeeService.readEmployee(empid);
			post.setPlatManager(emp);
		} else if (empids != null) {
			Employee emp = this.employeeService.readEmployee(empids);
			post.setPlatManager(emp);
		}
		post.setTax(tax == null ? new BigDecimal(0.00) : tax.trim().length() > 0 ? new BigDecimal(tax) : new BigDecimal(0.00));
		post.setTax(post.getTax().setScale(2, BigDecimal.ROUND_HALF_UP));
		post.setRemarks(remarks);
		if (this.platformsService.addPlatforms(post, channels, request)) {
			this.menuitemService.updateMentItemPlatforms("plats/show.do", platName, post.getId() + "", post.getBranchs(), post.getCreateDT());
			mav.addObject("result", "success");
		}
		return mav;
	}

	/**
	 * 功能：编辑
	 * */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public PlatformsBean read(Integer id) {
		PlatformsBean bean = new PlatformsBean();
		Platforms plat = this.platformsService.readPlatforms(id);
		if (plat != null) {
			bean.setId(plat.getId());
			bean.setName(plat.getPlatName());
			bean.setTax(String.format("%.2f", plat.getTax() != null ? plat.getTax() : new BigDecimal(0.00)));
			if (plat.getPlatManager() != null)
				bean.setPlatManager(plat.getPlatManager().getId().toString());
			bean.setFormGenre(plat.getFormGenre().toString());
			bean.setIncomeGenre(plat.getIncomeGenre().toString());
			if (plat.getFormGenre() == PlatformGenre.PAIMAI) {
				Set<String> chanList = new HashSet<String>();
				if (plat.getChannels() != null && plat.getChannels().size() > 0) {
					for (PlatformsChannels channel : plat.getChannels()) {
						chanList.add(channel.getChannels());
					}
				}
				bean.setChars(new ArrayList<String>(chanList));
			}
			bean.setRemarks(plat.getRemarks());
		}
		return bean;
	}

	/**
	 * 功能：删除
	 * */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResultMsg delete(Integer id, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (this.platformsService.deletePlatforms(id, request)) {
			this.menuitemService.deleteMentItemPlatforms("plats/show.do", id + "");
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 功能：获取平台频道号
	 * */
	@ResponseBody
	@RequestMapping(value = "/getChannels", method = RequestMethod.POST)
	public ResultMsg getChannels(@RequestBody Platforms platforms) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("errors");
		try {
			if (platforms != null && platforms.getId() != null && platforms.getId().intValue() > 0) {
				Platforms $platforms = this.platformsService.readPlatforms(platforms.getId());
				if ($platforms != null && $platforms.getFormGenre() == PlatformGenre.PAIMAI) {
					List<PlatformsChannels> list = $platforms.getChannels();
					if (list != null && list.size() > 0) {
						msg.setData(list);
						msg.setResultStatus(200);
						msg.setResultMessage("success");
					} else {
						msg.setResultStatus(100);
					}
				} else {
					msg.setResultStatus(100);
				}
			}
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return msg;
		}
	}

	/**
	 * 功能：频道删除验证
	 * */
	@ResponseBody
	@RequestMapping(value = "/validationChannel", method = RequestMethod.GET)
	public ResultMsg validationChannel(String id, String channels) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.trim().length() > 0 && channels != null && channels.trim().length() > 0) {
			if (!this.platformsService.readPlatformsByChan(id, channels)) {
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/readPagesSkip", method = RequestMethod.POST)
	public PageResult<?> readPagesSkip(int page, int rows, String filter, HttpServletRequest request) {
		PageResult<?> pages = null;
		RoleUsers users = (RoleUsers) request.getSession(false).getAttribute(Constants.CURRENT_USER);
		if (users != null) {
			List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
			pages = this.platformsService.readAllPagesSkip(rows, page, null, filter, platList);
		}

		return pages;
	}

	/**
	 * 功能： 获取频道
	 **/
	@ResponseBody
	@RequestMapping("/readChannel")
	public List<PlatformsChannels> readChannel(String id) {
		List<PlatformsChannels> list = new ArrayList<PlatformsChannels>();
		if (id != null && id.trim().length() > 0) {
			Platforms plat = this.platformsService.findPlatforms(Integer.valueOf(id));
			list = plat.getChannels();
		}
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/readPagesAllPlat", method = RequestMethod.POST)
	public PageResult<?> readPagesAllPlat(int page, int rows, String filter, HttpServletRequest request) {
		PageResult<?> pages = null;
		SearchFilter $filter = super.searchFilter(request);
		pages = this.platformsService.readPagesAllPlat(rows, page, $filter, filter);
		return pages;
	}
}
