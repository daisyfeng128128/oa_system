package com.baofeng.oa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.oa.bean.FinSalariesBean;
import com.baofeng.oa.bean.FinSalaryBean;
import com.baofeng.oa.dao.FinSalariesDAO;
import com.baofeng.oa.dao.ManagerDAO;
import com.baofeng.oa.entity.Actores;
import com.baofeng.oa.entity.BaseEnums.Grant;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.Department;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.FinPlatformsIncome;
import com.baofeng.oa.entity.FinSalaries;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmpAttendanceService;
import com.baofeng.oa.service.IFinSalariesService;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Service("finSalariesService")
public class FinSalariesServiceImpl implements IFinSalariesService {

	@Autowired
	private ManagerDAO managerDAO;
	@Autowired
	private FinSalariesDAO finSalariesDAO;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private IEmpAttendanceService attendanceService;

	@Override
	public boolean findValidation(Employee emp, Date date1, Date date2) {
		return this.finSalariesDAO.findValidation(emp.getNumber(), emp.getBranchs(), date1, date2);
	}

	@Override
	public boolean saveFinSalaries(FinSalaries finSalaries) {
		if (finSalaries != null) {
			return this.finSalariesDAO.saveFinSalaries(finSalaries);
		}
		return false;

	}

	@Override
	public FinSalaries findFinSalariesByNumber(Integer number, Integer branchs, Date $date1, Date $date2) {
		return this.finSalariesDAO.findFinSalariesByNumber(number, branchs, $date1, $date2);
	}

	@Override
	public FinPlatformsIncome findPlatformsIncomeById(Integer salariesId, String platformsId) {
		return this.finSalariesDAO.findPlatformsIncomeById(salariesId, platformsId);
	}

	@Override
	public void savePlatformsIncome(FinPlatformsIncome incomeDetails) {
		this.finSalariesDAO.savePlatformsIncome(incomeDetails);
	}

	@Override
	public List<FinSalaries> findAllFinSalaries(Date $date1, Date $date2) {
		return this.finSalariesDAO.findAllFinSalaries($date1, $date2);
	}

	@Override
	public List<FinPlatformsIncome> findIncomeList(Integer salariesId) {
		return this.finSalariesDAO.findIncomeList(salariesId);
	}

	@Override
	public List<String> readFinSalaryDate() {
		return this.finSalariesDAO.readFinSalaryDate();
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readAllSalaryEmployee(int pageSize, int curPage, String sortName, String sortOrder, String id, String date, SearchFilter filter, Integer type,
			String fastArg) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		NPageResult rows = this.finSalariesDAO.readAllPages(pageSize, curPage, sortName, sortOrder, id, date1, date2, fastArg, filter);
		if (rows != null && rows.getData().size() > 0) {
			List<FinSalaryBean> list = new ArrayList<FinSalaryBean>();
			BranchOffice branchs = null;
			for (Object o : rows.getData()) {
				FinSalaries post = (FinSalaries) o;
				FinSalaryBean bean = new FinSalaryBean();
				bean.setId(post.getId());
				if (branchs == null)
					branchs = this.branchOfficeService.readBranchOffice(post.getBranchs());
				if (branchs != null) {
					bean.setNumber(branchs.getNumberHead() + String.format("%04d", post.getNumber()));
				} else {
					bean.setNumber(String.format("%04d", post.getNumber()));
				}
				bean.setName(post.getName());
				bean.setBilled(post.getBilled().toString());
				bean.setRealExpenditure(post.getRealExpenditure());
				if (post.getEmployee().getBankCard() != null) {
					bean.setBankCard(post.getEmployee().getBankCard().replaceAll("(.{4})", "$1 "));
				}
				if (post.getIcbcBank() != null) {
					bean.setIcbcBank(post.getIcbcBank());
				}
				bean.setIcbcBank(post.getIcbcBank());
				bean.setBocomBank(post.getBocomBank());

				bean.setBankAddress(post.getEmployee().getBankAddress());
				bean.setAliasname(post.getEmployee().getAliasname());
				bean.setBasicSalary(post.getBasicSalary());
				bean.setRealitySalary(post.getRealitySalary());
				bean.setRemarks(post.getRemarks());
				list.add(bean);
			}
			rows.setData(list);
		}
		return rows;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, String date, Integer type, String fastArg, Integer all, String plat, String sex,
			SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		List<Integer> listId = new ArrayList<Integer>();
		if (plat != null && plat.trim().length() > 0) {
			listId = this.finSalariesDAO.readFinPlatformsIncome(plat);
		}
		NPageResult page = this.finSalariesDAO.readPages(pageSize, curPage, sortName, sortOrder, date1, date2, type, fastArg, all, listId, sex, filter);
		if (page != null && page.getData() != null && page.getData().size() > 0) {
			List<FinSalariesBean> listBean = new ArrayList<FinSalariesBean>();
			BranchOffice branchs = null;
			for (Object o : page.getData()) {
				FinSalaries post = (FinSalaries) o;
				FinSalariesBean bean = new FinSalariesBean();
				bean.setBilled(post.getBilled().toString());
				bean.setBasicSalary(String.format("%.2f", post.getBasicSalary() != null ? post.getBasicSalary().floatValue() : 0.0f));
				bean.setId(post.getId());
				if (post.getDetails() != null && post.getDetails().size() > 1)
					bean.setName("<span style='color:#e35d00'>" + post.getName() + "</span>");
				else
					bean.setName(post.getName());
				bean.setBilled(post.getBilled().toString());
				if (branchs == null) {
					branchs = this.branchOfficeService.readBranchOffice(post.getBranchs());
				}
				if (branchs != null) {
					bean.setNumber(branchs.getNumberHead() + String.format("%04d", post.getNumber()));
				} else {
					bean.setNumber(String.format("%04d", post.getNumber()));
				}

				bean.setAliasname(post.getEmployee().getAliasname());
				bean.setRealitySalary(String.format("%.2f", post.getRealitySalary() != null ? post.getRealitySalary().floatValue() : 0.0f));
				bean.setRemark(post.getRemarks());
				bean.setDeptName(post.getDeptName());
				bean.setPositionsName(post.getPositionsName());
				bean.setIcbcBank(String.format("%.2f", post.getIcbcBank() != null ? post.getIcbcBank() : 0.0f));
				bean.setBocomBank(String.format("%.2f", post.getBocomBank() != null ? post.getBocomBank() : 0.0f));
				bean.setRealExpenditure(String.format("%.2f", post.getRealExpenditure() != null ? post.getRealExpenditure().floatValue() : 0.0f));
				if (post.getEmployee().getBankCard() != null)
					bean.setBankCard(post.getEmployee().getBankCard().replaceAll("(.{4})", "$1 "));
				bean.setBankAddress(post.getEmployee().getBankAddress());
				listBean.add(bean);
			}
			page.setData(listBean);
		}
		return page;
	}

	@Override
	public FinSalaryBean readSalary(Integer id) {
		if (id != null && id > 0) {
			FinSalaryBean bean = new FinSalaryBean();
			FinSalaries post = this.finSalariesDAO.readFinSalaryById(id);
			if (post != null) {
				if (post.getIsActores() != null ? post.getIsActores() : false) {
					bean.setIsActore(11);
					Map<String, String> map = new HashMap<String, String>();
					if (post.getDetails() != null && post.getDetails().size() > 0) {
						for (FinPlatformsIncome income : post.getDetails()) {
							map.put("plat" + income.getPlatId(), income.getIncome() != null ? income.getIncome().toString() : "");
						}
					}
					bean.setDetails(map);
				}
				bean.setCompanyOtheSub(post.getCompanyOtheSub() != null ? post.getCompanyOtheSub() : new BigDecimal(0.00));
				bean.setPostAllowance(post.getPostAllowance() !=null ? post.getPostAllowance() : new BigDecimal(0.00));
				BranchOffice branchs = this.branchOfficeService.readBranchOffice(post.getBranchs());
				bean.setId(post.getId());
				if (branchs != null)
					bean.setNumber(branchs.getNumberHead() + String.format("%04d", post.getNumber()));
				else
					bean.setNumber(String.format("%04d", post.getNumber()));
				bean.setName(post.getName());
				bean.setDeptName(post.getDeptName());
				bean.setPositionsName(post.getPositionsName());
				bean.setTotalIncome(post.getTotalIncome());
				bean.setSubsidySalary(post.getSubsidySalary() != null ? post.getSubsidySalary() : new BigDecimal(0));
				bean.setRealitySalary(post.getRealitySalary());
				bean.setTaxableSalary(post.getTaxableSalary() != null ? post.getTaxableSalary() : new BigDecimal(0));

				bean.setBasicSalary(post.getBasicSalary());
				bean.setAttendance(post.getAttendance());
				bean.setPushMoney(post.getPushMoney());
				bean.setPushMoneySalary(post.getPushMoneySalary());
				bean.setPushMoneyActores(post.getPushMoneyActores() != null ? post.getPushMoneyActores() : new BigDecimal(0));
				bean.setBonus(post.getBonus());
				bean.setTotalIncome(post.getTotalIncome());

				bean.setPn_pension(post.getPn_pension());
				bean.setPn_medicare(post.getPn_medicare());
				bean.setPn_unemployment(post.getPn_unemployment());
				bean.setPn_provident(post.getPn_provident());
				bean.setPn_replenishProvident(post.getPn_replenishProvident());
				bean.setPn_totalSocial(post.getPn_totalSocial());
				bean.setCp_pension(post.getCp_pension());
				bean.setCp_medicare(post.getCp_medicare());
				bean.setCp_unemployment(post.getCp_unemployment());
				bean.setCp_provident(post.getCp_provident());
				bean.setCp_replenishProvident(post.getCp_replenishProvident());
				bean.setCp_occupationalInjury(post.getCp_occupationalInjury());
				bean.setCp_fertility(post.getCp_fertility());
				bean.setCp_totalSocial(post.getCp_totalSocial());

				BigDecimal add_totals = new BigDecimal(0);
				add_totals = add_totals.add(post.getApplyFee());
				add_totals = add_totals.add(post.getFoodSubsidy());
				add_totals = add_totals.add(post.getOtherSubsidy());
				add_totals = add_totals.add(post.getPlatSubsidy());
				add_totals = add_totals.add(post.getBonus());
				add_totals = add_totals.add(bean.getCompanyOtheSub());
				add_totals = add_totals.add(bean.getPostAllowance());
				if (post.getPushMoneySalary() != null)
					add_totals = add_totals.add(post.getPushMoneySalary());
				bean.setAdd_totalSocial(add_totals);

				BigDecimal dec_totals = new BigDecimal(0);
				dec_totals = dec_totals.add(post.getBeLate());
				dec_totals = dec_totals.add(post.getPlatLeave());
				dec_totals = dec_totals.add(post.getSickLeave());
				dec_totals = dec_totals.add(post.getCasualLeave());
				dec_totals = dec_totals.add(post.getOtherDeduct());

				bean.setDec_totalSocial(dec_totals);

				bean.setTaxableSalary(post.getTaxableSalary());
				bean.setIndividualIncomeTax(post.getIndividualIncomeTax());
				bean.setActoresIncomeTax(post.getActoresIncomeTax());
				bean.setApplyFee(post.getApplyFee());
				bean.setPlatLeave(post.getPlatLeave());
				bean.setPlatSubsidy(post.getPlatSubsidy());
				bean.setOtherDeduct(post.getOtherDeduct());
				bean.setOtherSubsidy(post.getOtherSubsidy());
				bean.setFoodSubsidy(post.getFoodSubsidy());
				bean.setTrafficSubsidy(post.getTrafficSubsidy());
				bean.setBeLate(post.getBeLate());
				bean.setCasualLeave(post.getCasualLeave());
				bean.setSickLeave(post.getSickLeave());
				bean.setRealExpenditure(post.getRealExpenditure());
				bean.setRealitySalary(post.getRealitySalary());
				bean.setNetProfit(post.getNetProfit());
				bean.setIcbcBank(post.getIcbcBank() != null ? post.getIcbcBank() : new BigDecimal(0.00));
				bean.setBocomBank(post.getBocomBank() != null ? post.getBocomBank() : new BigDecimal(0.00));
				if (post.getBilled() != null && post.getBilled() == Grant.PUBLISHED) {
					bean.setBilled("已发 ");
				} else {
					bean.setBilled("未发 ");
				}
				bean.setRemarks(post.getRemarks());
			}
			return bean;
		}
		return null;
	}

	@Override
	public FinSalaries readFinSalaries(String string) {
		FinSalaries salary = this.finSalariesDAO.readFinSalariesOff(string);
		if (salary != null) {
			return salary;
		}
		return null;
	}

	@Override
	public boolean addFinSalaries(FinSalaries post) {
		if (post != null) {
			if (post != null && post.getId() != null && post.getId() > 0) {
				FinSalaries $post = this.readFinSalaries(post.getId().toString());
				Date date1 = CustomDateUtils.Handler.currentMonthFirstDay($post.getCreateDT());
				Date date2 = CustomDateUtils.Handler.currentMonthEndDay($post.getCreateDT());
				Float attednance = attendanceService.findAttendanceByNumberAndDate($post.getNumber(), $post.getBranchs(), date1, date2);
				if ($post != null) {
					$post.setId(post.getId());
					$post.setOtherSubsidy(post.getOtherSubsidy());
					$post.setPn_pension(post.getPn_pension());
					$post.setPn_medicare(post.getPn_medicare());
					$post.setPn_unemployment(post.getPn_unemployment());
					$post.setPn_provident(post.getPn_provident());
					$post.setPn_replenishProvident(post.getPn_replenishProvident());
					$post.setCp_pension(post.getCp_pension());
					$post.setCp_unemployment(post.getCp_unemployment());
					$post.setCp_provident(post.getCp_provident());
					$post.setCp_fertility(post.getCp_fertility());
					$post.setApplyFee(post.getApplyFee());
					$post.setOtherSubsidy(post.getOtherSubsidy());
					$post.setBonus(post.getBonus());
					$post.setSickLeave(post.getSickLeave());
					$post.setBeLate(post.getBeLate());
					$post.setCp_medicare(post.getCp_medicare());
					$post.setCp_occupationalInjury(post.getCp_occupationalInjury());
					$post.setOtherDeduct(post.getOtherDeduct());
					$post.setRemarks(post.getRemarks());
					$post.setAttendance(new BigDecimal(attednance));
					post = $post;
					$post.make(true);
				}
				if (this.finSalariesDAO.addFinSalary(post)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean addSalariesOffline(FinSalaries post) {
		if (post != null) {
			return this.finSalariesDAO.addFinSalary(post);
		}
		return false;
	}

	@Override
	public List<FinSalaries> readAllFinSalariess(String date, SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		List<FinSalaries> list = this.finSalariesDAO.readAllFinSalariess(date1, date2, filter);
		return list;
	}

	@Override
	public HSSFWorkbook export(List<FinSalaries> list) {
		String[] excelHeader = { "序号", "员工号", "姓名", "艺名", "部门", "职位", "工商银行", "交通银行", "实发工资", "公司实际支出", "个人社保支出", "公司社保支出", "发放状态", "工资卡账号", "开户地址" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Campaign");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 12);
		style.setFont(font);

		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i);
			sheet.setColumnWidth(i, 32 * 150);
		}
		BranchOffice branchs = null;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow(i + 1);
				row.setRowStyle(style);
				FinSalaries finSalaries = list.get(i);
				row.createCell(0).setCellValue(i + 1);
				if (branchs == null)
					branchs = this.branchOfficeService.readBranchOffice(finSalaries.getBranchs());
				if (branchs != null)
					row.createCell(1).setCellValue(branchs.getNumberHead() + String.format("%04d", finSalaries.getNumber()));
				else
					row.createCell(1).setCellValue(String.format("%04d", finSalaries.getNumber()));
				row.createCell(2).setCellValue(finSalaries.getName());
				row.createCell(3).setCellValue(finSalaries.getAliasname());
				row.createCell(4).setCellValue(finSalaries.getDeptName());
				row.createCell(5).setCellValue(finSalaries.getPositionsName());
				row.createCell(6).setCellValue(finSalaries.getIcbcBank() != null ? finSalaries.getIcbcBank().toString() : "0.0");
				row.createCell(7).setCellValue(finSalaries.getBocomBank() != null ? finSalaries.getBocomBank().toString() : "0.0");
				row.createCell(8).setCellValue(finSalaries.getRealitySalary() != null ? finSalaries.getRealitySalary().toString() : "0.0");
				row.createCell(9).setCellValue(finSalaries.getRealExpenditure() != null ? finSalaries.getRealExpenditure().toString() : "0.0");
				row.createCell(10).setCellValue(finSalaries.getPn_totalSocial() != null ? finSalaries.getPn_totalSocial().toString() : "0.0");
				row.createCell(11).setCellValue(finSalaries.getCp_totalSocial() != null ? finSalaries.getCp_totalSocial().toString() : "0.0");
				row.createCell(12).setCellValue(finSalaries.getBilled() == Grant.UNBILLED ? "未发" : "已发");
				row.createCell(13).setCellValue(finSalaries.getEmployee().getBankCard());
				row.createCell(14).setCellValue(finSalaries.getEmployee().getBankAddress());
			}
		}
		return wb;
	}

	@Override
	public HSSFWorkbook exportPart(List<FinSalaries> listActoresOff) {
		String[] excelHeader = { "员工号", "姓名", "艺名", "部门", "职位", "工行", "交行", "实发工资", "公司实际支出工资", "发放状态", "工资卡账号", "开户行地址" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet1 = wb.createSheet("艺人主播(线下)");
		HSSFRow row1 = sheet1.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 12);
		style.setFont(font);
		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row1.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
			sheet1.autoSizeColumn(i);
			sheet1.setColumnWidth(i, 32 * 150);
		}
		BranchOffice branchs = null;
		if (listActoresOff != null && listActoresOff.size() > 0) {
			for (int i = 0; i < listActoresOff.size(); i++) {
				row1 = sheet1.createRow(i + 1);
				row1.setRowStyle(style);
				FinSalaries finSalaries = listActoresOff.get(i);
				if (branchs == null)
					branchs = this.branchOfficeService.readBranchOffice(finSalaries.getBranchs());
				if (branchs != null)
					row1.createCell(0).setCellValue(branchs.getNumberHead() + String.format("%04d", finSalaries.getNumber()));
				else
					row1.createCell(0).setCellValue(String.format("%04d", finSalaries.getNumber()));
				row1.createCell(1).setCellValue(finSalaries.getName());
				row1.createCell(2).setCellValue(finSalaries.getAliasname());
				row1.createCell(3).setCellValue(finSalaries.getDeptName());
				row1.createCell(4).setCellValue(finSalaries.getPositionsName());
				row1.createCell(5).setCellValue(finSalaries.getIcbcBank() != null ? finSalaries.getIcbcBank().toString() : "0.0");
				row1.createCell(6).setCellValue(finSalaries.getBocomBank() != null ? finSalaries.getBocomBank().toString() : "0.0");
				row1.createCell(7).setCellValue(finSalaries.getRealitySalary() != null ? finSalaries.getRealitySalary().toString() : "0.0");
				row1.createCell(8).setCellValue(finSalaries.getRealExpenditure() != null ? finSalaries.getRealExpenditure().toString() : "0.0");
				row1.createCell(9).setCellValue(finSalaries.getBilled() == Grant.UNBILLED ? "未发" : finSalaries.getBilled() == Grant.PUBLISHED ? "已发" : "未通过");
				row1.createCell(10).setCellValue(finSalaries.getEmployee().getBankCard());
				row1.createCell(11).setCellValue(finSalaries.getEmployee().getBankAddress());
			}
		}
		return wb;
	}

	@Override
	public List<FinSalaries> readAllFinSalariessManager(String date, SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		return this.finSalariesDAO.readAllFinSalariessManager(date1, date2, filter);
	}

	@Override
	public List<FinSalaries> readAllFinSalariessActores(String date, SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		return this.finSalariesDAO.readAllFinSalariessActores(date1, date2, filter);
	}

	@Override
	public List<FinSalaries> readAllFinSalariesOffActores(String date, String sex, String plat, SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		List<Integer> listId = new ArrayList<Integer>();
		if (plat != null && plat.trim().length() > 0) {
			listId = this.finSalariesDAO.readFinPlatformsIncome(plat);
		}
		return this.finSalariesDAO.readAllFinSalariesOffActores(date1, date2, listId, sex, filter);
	}

	@Override
	public boolean saveFinSalaryOfflineByList(List<String> list, String date) {
		if (list != null && list.size() > 0) {
			for (String id : list) {
				FinSalaries salary = this.finSalariesDAO.readFinSalaryById(Integer.valueOf(id));
				if (salary != null && salary.getBilled() != Grant.UNKNOWN) {
					salary.setBilled(Grant.PUBLISHED);
					if (!this.finSalariesDAO.addFinSalary(salary)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public FinSalaries readFinSalaryById(Integer id) {
		return this.finSalariesDAO.readFinSalaryById(id);
	}

	@Override
	public BigDecimal findSumAllEmployees(Integer branchs, Date $date1, Date $date2) {
		return this.finSalariesDAO.findSumAllEmployees(branchs, $date1, $date2);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readPagesShowSalaries(int pageSize, int curPage, String sortName, String sortOrder, String date, String name, SearchFilter searchFilter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		NPageResult page = this.finSalariesDAO.readPagesShowSalaries(pageSize, curPage, sortName, sortOrder, date1, date2, name, searchFilter);
		if (page != null && page.getData() != null && page.getData().size() > 0) {
			List<FinSalariesBean> listBean = new ArrayList<FinSalariesBean>();
			BranchOffice branchs = null;
			for (Object o : page.getData()) {
				FinSalaries post = (FinSalaries) o;
				FinSalariesBean bean = new FinSalariesBean();
				bean.setId(post.getId());
				bean.setName(post.getName());
				if (branchs == null) {
					branchs = this.branchOfficeService.readBranchOffice(post.getBranchs());
				}
				if (branchs != null) {
					bean.setNumber(branchs.getNumberHead() + String.format("%04d", post.getNumber()));
				} else {
					bean.setNumber(String.format("%04d", post.getNumber()));
				}
				bean.setAliasname(post.getEmployee().getAliasname());
				bean.setDeptName(post.getDeptName());
				bean.setPositionsName(post.getPositionsName());
				listBean.add(bean);
			}
			page.setData(listBean);
		}
		return page;
	}

	@Override
	public List<FinPlatformsIncome> findPlatformsIncomeByList(Date $date1, Date $date2) {
		return this.finSalariesDAO.findPlatformsIncomeByList($date1, $date2);
	}

	@Override
	public List<FinSalaries> findFinSalariesByActores(Class<Actores> clazz, Date $date1, Date $date2) {
		return this.finSalariesDAO.findFinSalariesByActores(clazz, $date1, $date2);
	}

	@Override
	public Map<String, List<FinSalaries>> readAllFinSalariessbyDep(String date,SearchFilter searchFilter, List<Department> listdep) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Map<String, List<FinSalaries>> list = (Map<String, List<FinSalaries>>) this.finSalariesDAO.readAllFinSalariessbyDep(date1, date2, searchFilter,listdep);
		return list;
	}

}
