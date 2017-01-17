package com.baofeng.oa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baofeng.commons.controller.BaseController;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.oa.bean.PlatformsOfflineIncomeBean;
import com.baofeng.oa.entity.BaseEnums.PlatformGenre;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Platforms;
import com.baofeng.oa.entity.PlatformsMonthsOutlay;
import com.baofeng.oa.entity.PlatformsOfflineIncome;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmpAttendanceService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.oa.service.IPlatformsOfflineIncomeService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.ResultMsg;

/**
 * 功能 ：平台线下艺人收入
 * */
@Controller
@RequestMapping("/offlineIncome")
public class PlatformsOfflineIncomeController extends BaseController {

	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IPlatformsService platformsService;
	@Autowired
	private IPlatformsActoresService actoresService;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IEmpAttendanceService empAttendanceService;
	@Autowired
	private IPlatformsOfflineIncomeService offlineIncomeService;
	@Autowired
	private IPlatformsMonthsOutlayService platformsMonthsOutlayService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(String platId, Integer mId, Integer branchs, String date, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/offlineIncome");
		List<String> exs = new ArrayList<String>(Arrays.asList("pass", "audit", "reject", "finalpass"));
		if (platId != null && platId.trim().length() > 0) {
			Platforms platforms = this.platformsService.readPlatforms(Integer.valueOf(platId));
			if (platforms != null && platforms.getFormGenre() != PlatformGenre.ZHIBOJIAN) {
				exs.add("impr");
			}
		}
		List<RoleDetailsAtts> platList = super.platformsRoleDetails(request, null);
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("outlay/show.do"), exs.toArray(new String[exs.size()]));
		boolean isEdit = super.isHaveAttribute(request, String.valueOf("outlay/show.do"), String.valueOf("pass"));
		if (date == null) {
			List<String> dateList = this.platformsMonthsOutlayService.readAllPlatformsMonthsOutlayDate();
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
		mav.addObject("isEdit", isEdit);
		mav.addObject("mId", mId);
		mav.addObject("date", date);
		mav.addObject("dateMsg", dateMsg);
		mav.addObject("maxmum", 30);
		mav.addObject("platId", platId);
		mav.addObject("attsList", attsList);
		mav.addObject("platList", platList);
		mav.addObject("branchs", branchs);
		mav.addObject("branchsMsg", branchsMsg);
		return mav;
	}

	/**
	 * 功能：获取所有
	 * */
	@ResponseBody
	@RequestMapping(value = "/readAllPlatformsOfflineIncome", method = RequestMethod.POST)
	public List<PlatformsOfflineIncomeBean> readAllPlatformsOfflineIncome(HttpServletRequest request, String platId, Integer mId, Integer branchs, String date) {
		List<PlatformsOfflineIncomeBean> list = new ArrayList<PlatformsOfflineIncomeBean>();
		try {
			PlatformsMonthsOutlay bean = this.platformsMonthsOutlayService.readPlatformsMonthsOutlay(platId, mId, date, searchFilter(request, branchs));
			if (bean != null && bean.getId() != null) {
				list = this.offlineIncomeService.readAllPlatformsOfflineIncome(platId, Integer.valueOf(bean.getId()), date, super.searchFilter(request, branchs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 功能：保存
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, @RequestBody List<PlatformsOfflineIncomeBean> listBean) throws Exception {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/offlineIncome");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("outlay/show.do"), "pass", "reject", "audit", "finalpass");
		mav.addObject("attsList", attsList);
		List<PlatformsOfflineIncome> list = new ArrayList<PlatformsOfflineIncome>();
		if (listBean != null && listBean.size() > 0) {
			for (PlatformsOfflineIncomeBean bean : listBean) {
				PlatformsOfflineIncome income = new PlatformsOfflineIncome();
				income.setIncome(new BigDecimal(String.valueOf(bean.getIncome() == null ? "0.00" : bean.getIncome())));
				income.setPlatDeduct(new BigDecimal(String.valueOf(bean.getPlatDeduct() == null ? "0.00" : bean.getPlatDeduct())));
				income.setPlatSubsidy(new BigDecimal(String.valueOf(bean.getPlatSubsidy() == null ? "0.00" : bean.getPlatSubsidy())));
				income.setId(bean.getId());
				income.setDeductTax(new BigDecimal(String.valueOf(bean.getTaxPoint() == null ? "0.00" : bean.getTaxPoint())));
				income.setDuductGift(new BigDecimal(String.valueOf(bean.getRestitutionGift() == null ? "0.00" : bean.getRestitutionGift())));
				income.setAttendance(Float.valueOf(bean.getAttendance() == null ? "0.00" : bean.getAttendance()));
				income.setRemarks(bean.getRemarks());
				list.add(income);
			}
		}
		if (this.offlineIncomeService.update(list)) {
			mav.addObject("result", "success");
		}
		return mav;
	}

	/**
	 * 功能：
	 * */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public PlatformsOfflineIncomeBean edit(@RequestBody PlatformsOfflineIncomeBean bean) {
		PlatformsOfflineIncomeBean post = this.offlineIncomeService.editOff(bean);
		return post;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, String date, Integer mId, String platId) throws Exception {
		PlatformsMonthsOutlay bean = this.platformsMonthsOutlayService.readPlatformsMonthsOutlay(platId, mId, date, searchFilter(request));
		List<PlatformsOfflineIncomeBean> listOff = this.offlineIncomeService
				.readAllPlatformsOfflineIncome(platId, Integer.valueOf(bean.getId()), date, super.searchFilter(request));
		HSSFWorkbook wb = this.offlineIncomeService.export(listOff);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + new Date().toString() + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResultMsg upload(@RequestParam MultipartFile[] upload, HttpServletRequest request) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		final String EXTENSION_XLS = ".xls";
		final String EXTENSION_XLSX = ".xlsx";
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		List<Map<Integer, String>> result = new ArrayList<Map<Integer, String>>();
		List<String> list = new ArrayList<String>();
		List<String> list1 = new ArrayList<String>();
		if (upload != null && upload.length > 0) {
			for (MultipartFile $file : upload) {
				String ext = $file.getOriginalFilename().substring($file.getOriginalFilename().indexOf("."), $file.getOriginalFilename().length());
				Workbook workbook = null;
				if (!$file.isEmpty()) {
					CommonsMultipartFile cf = (CommonsMultipartFile) $file;
					DiskFileItem fi = (DiskFileItem) cf.getFileItem();
					File f = fi.getStoreLocation();
					InputStream is = new FileInputStream(f);
					if (ext.endsWith(EXTENSION_XLS)) {
						workbook = new HSSFWorkbook(is);
					} else if (ext.endsWith(EXTENSION_XLSX)) {
						workbook = new XSSFWorkbook(is);
					}
					Sheet sheet = workbook.getSheetAt(0);
					Row row;
					String cell;
					for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
						Map<Integer, String> map = new HashMap<Integer, String>();
						row = sheet.getRow(i);
						for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
							cell = row.getCell(j).toString();
							map.put(j, cell);
						}
						result.add(map);
					}
				}
			}
		}
		if (result != null && result.size() > 0) {
			for (Map<Integer, String> post : result) {
				try {
					String channel = post.get(0);
					String attendance = post.get(2);
					String income = post.get(3);
					String deductTax = post.get(4);
					String duductGift = post.get(5);
					String platSubsidy = post.get(6);
					String platDeduct = post.get(7);
					if (channel != null && channel.trim().length() > 0) {
						PlatformsOfflineIncome lineIncome = this.offlineIncomeService.readAllPlatformsOfflineIncome(String.valueOf(Float.valueOf(channel).intValue()), branchs);
						if (lineIncome != null) {
							try {
								lineIncome.setAttendance(Float.valueOf(attendance == null ? "0.00" : attendance));
								lineIncome.setBranchs(branchs);
								if (income != null && income.trim().length() > 0) {
									lineIncome.setIncome(new BigDecimal(income));
								}
								if (deductTax != null && deductTax.trim().length() > 0) {
									lineIncome.setDeductTax(new BigDecimal(deductTax));
								}
								if (duductGift != null && duductGift.trim().length() > 0) {
									lineIncome.setDuductGift(new BigDecimal(duductGift));
								}
								if (platSubsidy != null && platSubsidy.trim().length() > 0) {
									lineIncome.setPlatSubsidy(new BigDecimal(platSubsidy));
								}
								if (platDeduct != null && platDeduct.trim().length() > 0) {
									lineIncome.setPlatDeduct(new BigDecimal(platDeduct));
								}
								if (this.offlineIncomeService.addPlatformsOfflineIncome(lineIncome, request)) {
									msg.setResultMessage("success");
									msg.setResultStatus(200);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							list.add(channel);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list1.add(String.valueOf(Float.valueOf(list.get(i)).intValue()));
			}
			msg.setData(list1);
			msg.setResultStatus(101);
		}
		return msg;
	}
}