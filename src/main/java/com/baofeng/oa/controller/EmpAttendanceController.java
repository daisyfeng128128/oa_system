package com.baofeng.oa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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
import com.baofeng.oa.bean.EmpAttendanceBean;
import com.baofeng.oa.bean.TreeviewBean;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.EmpAttendance;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IDepartmentService;
import com.baofeng.oa.service.IEmpAttendanceService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.utils.Constants;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.ResultMsg;

/**
 * 部门管理
 */
@Controller
@RequestMapping("/empAttendance")
public class EmpAttendanceController extends BaseController {

	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IEmpAttendanceService empAttendanceService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IBranchOfficeService branchOfficeService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(String date, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/empAttendance");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("empAttendance/show.do"));
		date = date == null ? Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7) : date;
		String vdate = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		String $current = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		mav.addObject("isEdit", true);
		if (!date.equals($current)) {
			mav.addObject("isEdit", false);
		}
		mav.addObject("attsList", attsList);
		mav.addObject("date", date);
		mav.addObject("vdate", vdate);
		return mav;
	}

	/**
	 * 功能：修改
	 * */
	@ResponseBody
	@RequestMapping(value = "/readDateList", method = RequestMethod.POST)
	public ResultMsg readDateList(HttpServletRequest request) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultStatus(100);
		Object[] rest = new Object[2];
		List<String> dateList = this.empAttendanceService.readFinSalaryEmpDate(super.searchFilter(request));
		List<RoleDetailsAtts> areaList = super.platformsRoleDetails(request, String.valueOf("branchOff/show.do"));
		if (dateList != null && dateList.size() > 0) {
			msg.setData(dateList);
			rest[0] = dateList;
		}
		rest[1] = areaList;
		msg.setData(rest);
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/readTree", method = RequestMethod.GET)
	public ResultMsg readTree(String date, HttpServletRequest request) {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("errors");
		msg.setResultStatus(100);
		try {
			List<TreeviewBean> list = this.departmentService.readTreeViewBeans(super.searchFilter(request));
			if (list != null && list.size() > 0) {
				msg.setData(list);
				msg.setResultMessage("success");
				msg.setResultStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 功能：分页数据
	 * */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/readPages", method = RequestMethod.POST)
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, String id, String date, String fastArg, HttpServletRequest request) {
		NPageResult pages = null;
		pages = this.empAttendanceService.readPages(pageSize, curPage, sortName, sortOrder, id, date, fastArg, super.searchFilter(request));
		return pages;
	}

	/**
	 * 功能：修改
	 * */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResultMsg save(HttpServletRequest request, @RequestBody List<EmpAttendanceBean> listBean) throws Exception {
		ResultMsg msg = new ResultMsg();
		msg.setResultMessage("error");
		msg.setResultStatus(100);
		if (listBean != null && listBean.size() > 0) {
			for (EmpAttendanceBean bean : listBean) {
				EmpAttendance emp = this.empAttendanceService.readAttendanceById(bean.getId());
				if (emp != null) {
					if (bean.getAttendance() != null && bean.getAttendance().trim().length() > 0 && Float.valueOf(bean.getAttendance()) > Float.valueOf(31)) {
						emp.setAttendance(Float.valueOf(31));
					} else if (bean.getAttendance() != null && bean.getAttendance().trim().length() > 0 && Float.valueOf(bean.getAttendance()) < Float.valueOf(0)) {
						emp.setAttendance(Float.valueOf(0));
					} else if (bean.getAttendance() != null && bean.getAttendance().trim().length() > 0) {
						emp.setAttendance(Float.valueOf(bean.getAttendance()));
					}
					emp.setRemark(bean.getRemark());
					if (this.empAttendanceService.addEmpAttendance(emp, request)) {
						msg.setResultMessage("success");
						msg.setResultStatus(200);
					}
				}
			}
		}
		return msg;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView upload(@RequestParam MultipartFile[] upload, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView(Constants.COREWEB_BUILDITEMS + "/empAttendance");
		List<RoleDetailsAtts> attsList = super.attributeRoleDetails(request, String.valueOf("empAttendance/show.do"));
		String date = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		String vdate = Constants.convert(Constants.convert(date, Constants.format7), Constants.format9);
		String $current = Constants.convert(DateUtils.addMonths(new Date(), -1), Constants.format7);
		Integer branchs = getSessionAttribute(request, Constants.CURRENT_BRANCHS);
		mav.addObject("isEdit", true);
		if (!date.equals($current)) {
			mav.addObject("isEdit", false);
		}
		mav.addObject("attsList", attsList);
		mav.addObject("date", date);
		mav.addObject("vdate", vdate);
		final String EXTENSION_XLS = ".xls";
		final String EXTENSION_XLSX = ".xlsx";
		List<Map<Integer, String>> result = new ArrayList<Map<Integer, String>>();
		if (upload != null && upload.length > 0) {
			for (MultipartFile $file : upload) {
				String ext = $file.getOriginalFilename().substring($file.getOriginalFilename().indexOf("."), $file.getOriginalFilename().length());
				Workbook workbook = null;
				if (!$file.isEmpty()) {
					if (ext.endsWith(EXTENSION_XLS)) {
						CommonsMultipartFile cf = (CommonsMultipartFile) $file;
						DiskFileItem fi = (DiskFileItem) cf.getFileItem();
						File f = fi.getStoreLocation();
						InputStream is = new FileInputStream(f);
						workbook = new HSSFWorkbook(is);
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
					} else if (ext.endsWith(EXTENSION_XLSX)) {
						OPCPackage pkg = OPCPackage.open($file.getInputStream());
						XSSFWorkbook xwb = new XSSFWorkbook(pkg);
						XSSFSheet sheet = xwb.getSheetAt(0);
						XSSFRow row;
						String cell;
						for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
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
		}
		BranchOffice $ = this.branchOfficeService.readBranchOffice(branchs);
		if (result != null && result.size() > 0) {
			Date $date1 = CustomDateUtils.Handler.currentMonthFirstDay(DateUtils.addMonths(new Date(), -1));
			Date $date2 = CustomDateUtils.Handler.currentMonthEndDay(DateUtils.addMonths(new Date(), -1));
			for (Map<Integer, String> post : result) {
				try {
					String numbers = post.get(0);
					String attendance = post.get(2);
					if (numbers != null && numbers.trim().length() > 0) {
						String $numberHead = numbers.substring(0, 2);
						String number = numbers.substring(2, numbers.length());
						if (!$.getNumberHead().equals($numberHead))
							continue;
						EmpAttendance empAttendance = this.empAttendanceService.readAllEmpAttendanceByDateByNumber(Float.valueOf(number).intValue(), branchs, $date1, $date2);
						if (empAttendance != null) {
							try {
								empAttendance.setAttendance(Float.valueOf(attendance));
								this.empAttendanceService.addEmpAttendance(empAttendance, request);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							empAttendance = new EmpAttendance();
							if (number != null && number.trim().length() > 0) {
								try {
									float fl = Float.valueOf(number);
									Integer $number = (int) fl;
									empAttendance.setBranchs(branchs);
									empAttendance.setNumber($number);
									empAttendance.setCreateDT($date1);
									empAttendance.setAttendance(Float.valueOf(attendance));
									Employee emp = this.employeeService.readEmployeeByNumber($number);
									if (emp != null) {
										empAttendance.setEmployee(emp);
										this.empAttendanceService.addEmpAttendance(empAttendance, request);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return mav;
	}
}
