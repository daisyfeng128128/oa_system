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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.bean.FinSalariesOnlineEditBean;
import com.baofeng.oa.entity.BaseEnums.Grant;
import com.baofeng.oa.entity.FinPlatformsIncome;
import com.baofeng.oa.entity.FinSalariesOnline;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.service.IFinSalariesOnlineService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.Message;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

/**
 * 功能：线上管理、线上艺人工资
 * */
@Controller
@RequestMapping("/finSalariesOnline")
public class FinSalariesOnlineController extends BaseController {
	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private IFinSalariesOnlineService finSalariesOnlineService;
	@Autowired
	private IPlatformsService platformsService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView showOnlineLink(String date, Integer all, Integer type, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/finSalariesOnline");
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

	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(HttpServletRequest request, int pageSize, int curPage, String sortName, String sortOrder, String date, Integer type, String fastArg, Integer all,
			String plat, String sex) {
		NPageResult<?> pages = this.finSalariesOnlineService.readPages(pageSize, curPage, sortName, sortOrder, date, type, fastArg, all, plat, sex, super.searchFilter(request));
		return pages;
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public FinSalariesOnlineEditBean edit(String id) {
		if (id != null && id.trim().length() > 0) {
			FinSalariesOnline post = this.finSalariesOnlineService.readFinSalariesOnline(id);
			if (post != null) {
				FinSalariesOnlineEditBean bean = new FinSalariesOnlineEditBean();
				bean.setId(post.getId());
				bean.setName(post.getName());
				bean.setAliasname(post.getAliasname());
				bean.setAttendance(String.format("%.2f", post.getAttendance()) + "天");
				bean.setBasicSalary(String.format("%.2f", post.getBasicSalary()));
				bean.setDeductTax(String.format("%.2f", post.getDeductTax()));
				bean.setIndividualIncomeTax(String.format("%.2f", post.getIndividualIncomeTax()));
				bean.setActoresIncomeTax(String.format("%.2f", post.getActoresIncomeTax() != null ? post.getActoresIncomeTax() : new BigDecimal(0.00)));
				bean.setOtherDeduct(String.format("%.2f", post.getOtherDeduct()));
				bean.setOtherSubsidy(String.format("%.2f", post.getOtherSubsidy()));
				bean.setPlatDeduct(String.format("%.2f", post.getPlatDeduct()));
				bean.setPlatSubsidy(String.format("%.2f", post.getPlatSubsidy()));
				bean.setPushMoney(String.format("%.2f", post.getPushMoney()) + "%");
				bean.setPushMoneySalary(String.format("%.2f", post.getPushMoneySalary()));
				bean.setRealitySalary(String.format("%.2f", post.getRealitySalary()));
				bean.setTaxableSalary(String.format("%.2f", post.getTaxableSalary()));
				bean.setRemark(post.getRemarks() != null ? post.getRemarks() : "");
				bean.setTotalIncome(String.format("%.2f", post.getTotalIncome() != null ? post.getTotalIncome() : new BigDecimal(0.00)));
				Map<String, String> map = new HashMap<String, String>();
				if (post.getDetails() != null && post.getDetails().size() > 0) {
					for (FinPlatformsIncome income : post.getDetails()) {
						map.put("plat" + income.getPlatId(), income.getIncome() != null ? income.getIncome().toString() : "0.00");
					}
				}
				bean.setDetails(map);
				return bean;
			}
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public ResultMsg save(HttpServletRequest request, String id, String otherDeduct, String otherSubsidy, String remarks) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		FinSalariesOnline fin = new FinSalariesOnline();
		fin.setBranchs(branchs);
		if (id != null && id.trim().length() > 0)
			fin.setId(Integer.valueOf(id));
		if (otherDeduct != null && otherDeduct.trim().length() > 0)
			fin.setOtherDeduct(new BigDecimal(otherDeduct));
		if (otherSubsidy != null && otherSubsidy.trim().length() > 0)
			fin.setOtherSubsidy(new BigDecimal(otherSubsidy));
		fin.setRemarks(remarks);
		if (this.finSalariesOnlineService.addFinSalariesOnline(fin)) {
			msg.setResultMessage("success");
			msg.setResultStatus(200);
		}
		return msg;
	}

	/**
	 * 计算工资
	 * */
	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ResultMsg check(String date, String type) {
		ResultMsg rsg = new ResultMsg();
		rsg.setResultStatus(100);
		Message msg = new Message();
		FinSalariesOnline post = new FinSalariesOnline();
		post.setCreateDT(Constants.convert(date, Constants.format7));
		msg.setData(post);
		if (type != null && Integer.valueOf(type) == 4) 
			this.workManagers.onEvents(WorkNames.FINSALARIESACTORESONLINE, post);
		else if (type != null && Integer.valueOf(type) == 3) 
			this.workManagers.onEvents(WorkNames.FINSALARIESMANAGER, post);
		
		Constants.Queue.add(msg);
		return rsg;
	}

	/** 发放工资 */
	@ResponseBody
	@RequestMapping(value = "/payoff", method = RequestMethod.POST)
	public ResultMsg payoff(String id) {
		ResultMsg rsg = new ResultMsg();
		rsg.setResultStatus(100);
		if (id != null && id.trim().length() > 0) {
			List<String> list = null;
			if (id.indexOf(",") != -1) {
				String[] ids = id.split(",");
				list = Arrays.asList(ids);
			} else {
				list = new ArrayList<String>();
				list.add(id);
			}
			if (list != null && list.size() > 0) {
				if (this.finSalariesOnlineService.updateFinSalariesOnline(list)) {
					rsg.setResultStatus(200);
					rsg.setResultMessage("success");
				}
			}
		}
		return rsg;
	}

	@RequestMapping(value = "/partExcel", method = RequestMethod.GET)
	public void export(HttpServletRequest request, HttpServletResponse response, String date, String plat, String sex) throws Exception {
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		List<FinSalariesOnline> list = this.finSalariesOnlineService.readAllFinSalariesOnline(date, plat, sex, branchs);
		HSSFWorkbook wb = this.finSalariesOnlineService.partExcel(list);
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
			List<FinSalariesOnline> listFin = new ArrayList<FinSalariesOnline>();
			if (list != null && list.size() > 0) {
				for (String $id : list) {
					FinSalariesOnline salary = this.finSalariesOnlineService.readFinSalariesOnline($id);
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
				for (FinSalariesOnline $post : listFin) {
					rsmsg += $post.getName();
					rsmsg += ",";
				}
				rsmsg = rsmsg.substring(0, rsmsg.length() - 1);
				msg.setResultMessage(rsmsg);
			}
		}
		return msg;
	}
}
