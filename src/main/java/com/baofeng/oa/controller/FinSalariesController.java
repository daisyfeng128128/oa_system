package com.baofeng.oa.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.bean.FinSalariesBean;
import com.baofeng.oa.bean.FinSalaryBean;
import com.baofeng.oa.entity.BaseEnums.Grant;
import com.baofeng.oa.entity.Department;
import com.baofeng.oa.entity.FinSalaries;
import com.baofeng.oa.entity.FinSalariesOnline;
import com.baofeng.oa.entity.FinSalariesTalent;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.service.IDepartmentService;
import com.baofeng.oa.service.IFinSalariesOnlineService;
import com.baofeng.oa.service.IFinSalariesService;
import com.baofeng.oa.service.IFinSalariesTalentService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

/**
 * 功能： 员工薪资
 * */
@Controller
@RequestMapping("/finSalaries")
public class FinSalariesController extends BaseController {
	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private IPlatformsService platformsService;
	@Autowired
	private IFinSalariesService finSalariesService;
	@Autowired
	private IFinSalariesOnlineService finSalariesOnlineService;
	@Autowired
	private IFinSalariesTalentService finSalariesTalentService;
	@Autowired
	private IPlatformsMonthsOutlayService platformsMonthsOutlayService;
	@Autowired
	private IDepartmentService departmentService;

	/** 员工薪资 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(String date, Integer type, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/finSalaryAll");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("finSalaries/show.do"));
		if (date == null)
			date = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		String vdate = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		String $current = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		List<Platforms> list = this.platformsService.findAllPlatforms(null);
		List<HashMap<String, String>> lst = new ArrayList<HashMap<String, String>>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i += 2) {
				Platforms platforms1 = list.get(i);
				Platforms platforms2 = null;
				if (i + 1 >= list.size()) {
					platforms2 = new Platforms();
				} else {
					platforms2 = list.get(i + 1);
				}
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("aname", platforms1.getPlatName());
				map.put("aid", "plat" + platforms1.getId());
				map.put("bname", platforms2.getPlatName());
				map.put("bid", "plat" + platforms2.getId());
				lst.add(map);
			}
		}
		mav.addObject("isEdit", true);
		if (!date.equals($current)) {
			mav.addObject("isEdit", false);
		}
		mav.addObject("list", lst);
		mav.addObject("date", date);
		mav.addObject("vdate", vdate);
		mav.addObject("attsList", attsList);
		return mav;
	}

	/** 线下管理工资 */
	@RequestMapping(value = "/actshow", method = RequestMethod.GET)
	public ModelAndView showOnlineLink(String date, Integer all, Integer type, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/finSalaries");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("finSalaries/actshow.do"));
		if (date == null)
			date = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		type = type != null ? type : 1;
		String vdate = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		String $current = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		List<Platforms> list = this.platformsService.findAllPlatforms(null);
		all = all != null ? all : 0;
		List<HashMap<String, String>> lst = new ArrayList<HashMap<String, String>>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i += 2) {
				Platforms platforms1 = list.get(i);
				Platforms platforms2 = null;
				if (i + 1 >= list.size()) {
					platforms2 = new Platforms();
				} else {
					platforms2 = list.get(i + 1);
				}
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("aname", platforms1.getPlatName());
				map.put("aid", "plat" + platforms1.getId());
				map.put("bname", platforms2.getPlatName());
				map.put("bid", "plat" + platforms2.getId());
				lst.add(map);
			}
		}
		mav.addObject("isEdit", true);
		if (!date.equals($current)) {
			mav.addObject("isEdit", false);
		}
		mav.addObject("all", all);
		mav.addObject("list", lst);
		mav.addObject("type", type);
		mav.addObject("date", date);
		mav.addObject("vdate", vdate);
		mav.addObject("attsList", attsList);
		return mav;
	}

	/** 线下管理工资 */
	@RequestMapping(value = "/showSalaries", method = RequestMethod.GET)
	public ModelAndView showSalaries(String date, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/finSalariesShow");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("finSalaries/showSalaries.do"));
		if (date == null)
			date = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		String vdate = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		String $current = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		List<Platforms> list = this.platformsService.findAllPlatforms(null);
		List<HashMap<String, String>> lst = new ArrayList<HashMap<String, String>>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i += 2) {
				Platforms platforms1 = list.get(i);
				Platforms platforms2 = null;
				if (i + 1 >= list.size()) {
					platforms2 = new Platforms();
				} else {
					platforms2 = list.get(i + 1);
				}
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("aname", platforms1.getPlatName());
				map.put("aid", "plat" + platforms1.getId());
				map.put("bname", platforms2.getPlatName());
				map.put("bid", "plat" + platforms2.getId());
				lst.add(map);
			}
		}
		mav.addObject("isEdit", true);
		if (!date.equals($current)) {
			mav.addObject("isEdit", false);
		}
		mav.addObject("list", lst);
		mav.addObject("date", date);
		mav.addObject("vdate", vdate);
		mav.addObject("attsList", attsList);
		return mav;
	}

	/**
	 * 功能：日期
	 * */
	@ResponseBody
	@RequestMapping(value = "/readDateList", method = RequestMethod.POST)
	public ResultMsg readDateList(HttpServletRequest request) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Object[] rest = new Object[2];
		List<String> dateList = this.finSalariesService.readFinSalaryDate();
		List<RoleDetailsAtts> areaList = super.platformsRoleDetails(request, String.valueOf("branchOff/show.do"));
		rest[0] = dateList;
		rest[1] = areaList;
		msg.setData(rest);
		return msg;
	}

	/***
	 * 功能 ： 工资查询页
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPagesShowSalaries", method = RequestMethod.POST)
	public NPageResult readPagesShowSalaries(HttpServletRequest request, int pageSize, int curPage, String sortName, String sortOrder, String date, String name) {
		NPageResult<?> pages = this.finSalariesService.readPagesShowSalaries(pageSize, curPage, sortName, sortOrder, date, name, super.searchFilter(request));
		return pages;
	}

	/**
	 * 功能：获取所有
	 * */
	@ResponseBody
	@RequestMapping(value = "/readAllSalary", method = RequestMethod.POST)
	public NPageResult<?> readAllPlatformsMonths(int pageSize, int curPage, String sortName, String sortOrder, String id, String date, HttpServletRequest request, Integer type,
			String fastArg) {
		NPageResult<?> pages = this.finSalariesService.readAllSalaryEmployee(pageSize, curPage, sortName, sortOrder, id, date, super.searchFilter(request), type, fastArg);
		return pages;
	}

	/***
	 * 功能 ： 获取所有线下艺人或主播
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(HttpServletRequest request, int pageSize, int curPage, String sortName, String sortOrder, String date, Integer type, String fastArg, Integer all,
			String plat, String sex) {
		NPageResult<?> pages = this.finSalariesService.readPages(pageSize, curPage, sortName, sortOrder, date, type, fastArg, all, plat, sex, super.searchFilter(request));
		return pages;
	}

	/**
	 * 功能：编辑
	 * */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public FinSalaryBean edit(Integer id) {
		if (id != null) {
			FinSalaryBean post = this.finSalariesService.readSalary(id);
			return post;
		} else {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultMsg save(HttpServletRequest request, @RequestBody FinSalaryBean bean) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		FinSalaries post = new FinSalaries();
		if (bean != null) {
			post.setId(bean.getId());
			post.setBranchs(branchs);
			post.setPn_pension(bean.getPn_pension() != null ? bean.getPn_pension() : new BigDecimal(0.00));
			post.setPn_medicare(bean.getPn_medicare() != null ? bean.getPn_medicare() : new BigDecimal(0.00));
			post.setPn_unemployment(bean.getPn_unemployment() != null ? bean.getPn_unemployment() : new BigDecimal(0.00));
			post.setPn_provident(bean.getPn_provident() != null ? bean.getPn_provident() : new BigDecimal(0.00));
			post.setCp_pension(bean.getCp_pension() != null ? bean.getCp_pension() : new BigDecimal(0.00));
			post.setCp_unemployment(bean.getCp_unemployment() != null ? bean.getCp_unemployment() : new BigDecimal(0.00));
			post.setCp_provident(bean.getCp_provident() != null ? bean.getCp_provident() : new BigDecimal(0.00));
			post.setCp_fertility(bean.getCp_fertility() != null ? bean.getCp_fertility() : new BigDecimal(0.00));
			post.setApplyFee(bean.getApplyFee() != null ? bean.getApplyFee() : new BigDecimal(0.00));
			post.setOtherSubsidy(bean.getOtherSubsidy() != null ? bean.getOtherSubsidy() : new BigDecimal(0.00));
			post.setBonus(bean.getBonus() != null ? bean.getBonus() : new BigDecimal(0.00));
			post.setSickLeave(bean.getSickLeave() != null ? bean.getSickLeave() : new BigDecimal(0.00));
			post.setBeLate(bean.getBeLate() != null ? bean.getBeLate() : new BigDecimal(0.00));
			post.setCp_medicare(bean.getCp_medicare() != null ? bean.getCp_medicare() : new BigDecimal(0.00));
			post.setCp_occupationalInjury(bean.getCp_occupationalInjury() != null ? bean.getCp_occupationalInjury() : new BigDecimal(0.00));
			post.setOtherDeduct(bean.getOtherDeduct() != null ? bean.getOtherDeduct() : new BigDecimal(0.00));
			post.setRemarks(bean.getRemarks());
		}
		if (this.finSalariesService.addFinSalaries(post)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ResultMsg check(String date, String type) {
		ResultMsg rsg = new ResultMsg();
		rsg.setResultStatus(100);

		// 验证平台艺人
		FinSalaries $vfins = new FinSalaries();
		$vfins.setCreateDT(Constants.convert(date, Constants.format7));
		this.workManagers.onEvents(WorkNames.FINSALARIESVALIDATION, $vfins);

		// 计算线下艺人
		FinSalaries post = new FinSalaries();
		post.setCreateDT(Constants.convert(date, Constants.format7));
		this.workManagers.onEvents(WorkNames.FINSALARIESACTORESOFFICE, post);
		// 计算线下管理
		this.workManagers.onEvents(WorkNames.FINSALARIESMANAGEROFFLINE, post);

		// 计算线上艺人
		FinSalariesOnline $post = new FinSalariesOnline();
		$post.setCreateDT(Constants.convert(date, Constants.format7));
		this.workManagers.onEvents(WorkNames.FINSALARIESACTORESONLINE, $post);

		// 计算线上管理
		this.workManagers.onEvents(WorkNames.FINSALARIESMANAGER, $post);
		// 计算星探
		FinSalariesTalent talent = new FinSalariesTalent();
		talent.setCreateDT(Constants.convert(date, Constants.format7));
		this.workManagers.onEvents(WorkNames.FINSALARIESTALENT, talent);
		// 计算可发
		this.workManagers.onEvents(WorkNames.FINSALARIESBILLED, post);
		return rsg;
	}

	@ResponseBody
	@RequestMapping(value = "/savem", method = RequestMethod.POST)
	public ResultMsg save(HttpServletRequest request, @RequestBody List<FinSalariesBean> listBean) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		if (listBean != null && listBean.size() > 0) {
			for (FinSalariesBean bean : listBean) {
				FinSalaries post = this.finSalariesService.readFinSalaries(bean.getId().toString());
				if (post != null) {
					post.setRemarks(bean.getRemark());
				}
				if (!this.finSalariesService.addSalariesOffline(post)) {
					return msg;
				}
			}
		}
		msg.setResultMessage("success");
		msg.setResultStatus(200);
		return msg;
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public void export(HttpServletRequest request, HttpServletResponse response, String date) throws Exception {
		List<FinSalaries> list = this.finSalariesService.readAllFinSalariess(date, super.searchFilter(request));
		HSSFWorkbook wb = this.finSalariesService.export(list);
		response.setContentType("application/vnd.ms-excel");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String $date = sdf.format(new Date());
		response.setHeader("Content-disposition", "attachment;filename=" + $date + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, String date) throws Exception {
		/** 查询线下管理 */
		List<FinSalaries> listOff = this.finSalariesService.readAllFinSalariessManager(date, super.searchFilter(request));
		/** 查询线下艺人 */
		List<FinSalaries> listActoresOff = this.finSalariesService.readAllFinSalariessActores(date, super.searchFilter(request));
		/** 查询线上管理 */
		List<FinSalariesOnline> listOnline = this.finSalariesOnlineService.readAllFinSalariesOnlineManager(date, super.searchFilter(request));
		/** 查询线上艺人 */
		List<FinSalariesOnline> listActoresOnline = this.finSalariesOnlineService.readAllFinSalariesOnlineActores(date, super.searchFilter(request));
		/** 查询星探 */
		List<FinSalariesTalent> listTalent = this.finSalariesTalentService.readAllFinSalariesTalent(date, super.searchFilter(request));
		HSSFWorkbook wb = this.finSalariesOnlineService.export(listOff, listActoresOff, listOnline, listActoresOnline, listTalent);
		response.setContentType("application/vnd.ms-excel");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String $date = sdf.format(new Date());
		response.setHeader("Content-disposition", "attachment;filename=" + $date + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping(value = "/partExcel", method = RequestMethod.GET)
	public void partExcel(HttpServletRequest request, HttpServletResponse response, String date, String sex, String plat) throws Exception {
		/** 查询线下艺人 */
		List<FinSalaries> listActoresOff = this.finSalariesService.readAllFinSalariesOffActores(date, sex, plat, super.searchFilter(request));
		HSSFWorkbook wb = this.finSalariesService.exportPart(listActoresOff);
		response.setContentType("application/vnd.ms-excel");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String $date = sdf.format(new Date());
		response.setHeader("Content-disposition", "attachment;filename=" + $date + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@ResponseBody
	@RequestMapping(value = "grant", method = RequestMethod.POST)
	public ResultMsg grant(String id, String date) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.trim().length() > 0) {
			List<String> list = null;
			if (id.indexOf(",") != -1) {
				String[] ids = id.split(",");
				list = Arrays.asList(ids);
			} else {
				list = new ArrayList<String>();
				list.add(id);
			}
			if (this.finSalariesService.saveFinSalaryOfflineByList(list, date)) {
				msg.setResultStatus(200);
			}
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "grantCheck", method = RequestMethod.POST)
	public ResultMsg grantCheck(String id, String date) {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		if (id != null && id.trim().length() > 0) {
			List<String> list = null;
			if (id.indexOf(",") != -1) {
				String[] ids = id.split(",");
				list = Arrays.asList(ids);
			} else {
				list = new ArrayList<String>();
				list.add(id);
			}
			List<FinSalaries> listFin = new ArrayList<FinSalaries>();
			if (list != null && list.size() > 0) {
				for (String $id : list) {
					FinSalaries salary = this.finSalariesService.readFinSalaryById(Integer.valueOf($id));
					if (salary != null) {
						if (salary.getBilled() != null && salary.getBilled() == Grant.UNKNOWN) {
							listFin.add(salary);
						}
					}
				}
			}
			if (listFin == null || listFin.size() == 0) {
				msg.setResultMessage("success");
				msg.setResultStatus(200);
			} else if (listFin != null && listFin.size() > 0) {
				msg.setResultStatus(101);
				String rsmsg = "";
				for (FinSalaries $post : listFin) {
					rsmsg += $post.getName();
					rsmsg += ",";
				}
				rsmsg = rsmsg.substring(0, rsmsg.length() - 1);
				msg.setResultMessage(rsmsg);
			}
		}
		return msg;
	}
	
	@RequestMapping(value = "/exportFinSalariesExcel", method = RequestMethod.GET)
	public void exportFinSalariesExcel(HttpServletRequest request, HttpServletResponse response, String date) throws Exception {
		List<Department> listdep = this.departmentService.readAllDepartment( super.searchFilter(request));
		Map<String,List<FinSalaries>> map = this.finSalariesService.readAllFinSalariessbyDep(date, super.searchFilter(request),listdep);
		HSSFWorkbook wb = this.finSalariesOnlineService.exportFinSalaries(map,listdep);
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
