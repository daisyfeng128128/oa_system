package com.baofeng.oa.service.impl;

import java.math.BigDecimal;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.oa.dao.FinancialReportsDAO;
import com.baofeng.oa.entity.FinancialNReports;
import com.baofeng.oa.entity.FinancialOPExpenditure;
import com.baofeng.oa.entity.FinancialReports;
import com.baofeng.oa.entity.PlatformsMonths;
import com.baofeng.oa.service.IFinancialReportsService;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.SearchFilter;

@Service("financialReportsService")
public class FinancialReportsServiceImpl implements IFinancialReportsService {

	@Autowired
	private FinancialReportsDAO financialReportsDAO;

	@Override
	public FinancialNReports findFinancialReportsByDate(Integer branchs, Date months1, Date months2) {
		return this.financialReportsDAO.findFinancialReportsByDate(branchs, months1, months2);
	}

	@Override
	public boolean addFinancialReports(FinancialReports financialReports) {
		if (financialReports != null) {
			return this.financialReportsDAO.addFinancialReports(financialReports);
		}
		return false;
	}

	@Override
	public Map<String, String> readFinancialReportsByDate(String date, SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		FinancialNReports financialReports = this.financialReportsDAO.findFinancialReportsByDateNew(date1, date2, filter);
		if (financialReports != null) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", financialReports.getId().toString());
			map.put("currentIncome", financialReports.getCurrentIncome().toString());
			map.put("currentExpenditure", financialReports.getCurrentExpenditure().toString());
			map.put("currentProfit", financialReports.getCurrentProfit().toString());
			map.put("lastProfit", financialReports.getLastProfit().toString());
			map.put("cp_growthRate", financialReports.getCp_growthRate().toString());

			map.put("onlineActs", financialReports.getOnlineActs() != null ? financialReports.getOnlineActs().toString() : "0.00");
			map.put("onlineMans", financialReports.getOnlineMans() != null ? financialReports.getOnlineMans().toString() : "0.00");
			map.put("onlineTalent", financialReports.getOnlineTalent() != null ? financialReports.getOnlineTalent().toString() : "0.00");
			map.put("offlineEmps", financialReports.getOfflineEmps() != null ? financialReports.getOfflineEmps().toString() : "0.00");
			map.put("allTotal", financialReports.getAllTotal() != null ? financialReports.getAllTotal().toString() : "0.00");
			map.put("financing", financialReports.getFinancing() != null ? financialReports.getFinancing().toString() : "0.00");
			map.put("financingTotal", financialReports.getFinancingTotal() != null ? financialReports.getFinancingTotal().toString() : "0.00");
			map.put("totalOutlays", financialReports.getTotalOutlays() != null ? financialReports.getTotalOutlays().toString() : "0.00");

			map.put("richang", "0");
			map.put("office", "0");
			map.put("travel", "0");
			map.put("social", "0");
			map.put("national", "0");
			map.put("technology", "0");
			map.put("rent", "0");
			map.put("water", "0");
			map.put("docorate", "0");
			map.put("make", "0");
			map.put("other", "0");

			map.put("richangRemark", "");
			map.put("officeRemark", "");
			map.put("travelRemark", "");
			map.put("socialRemark", "");
			map.put("waterRemark", "");
			map.put("nationalRemark", "");
			map.put("technologyRemark", "");
			map.put("rentRemark", "");
			map.put("waterRemark", "");
			map.put("docorateRemark", "");
			map.put("makeRemark", "");
			map.put("otherRemark", "");
			BigDecimal total = new BigDecimal(0);
			map.put("total", total.toString());
			return map;
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("currentIncome", "0.00");
			map.put("currentExpenditure", "0.00");
			map.put("currentProfit", "0.00");
			map.put("lastProfit", "0.00");
			map.put("cp_growthRate", "0.00");

			map.put("maintenance", "0.00");
			map.put("roperate", "0.00");
			map.put("rother", "0.00");
			map.put("giftback", "0.00");
			map.put("operate", "0.00");
			map.put("onlineActs", "0.00");
			map.put("onlineMans", "0.00");
			map.put("onlineTalent", "0.00");
			map.put("allEmpls", "0.00");
			map.put("salaries", "0.00");
			map.put("financing", "0.00");
			map.put("financingTotal", "0.00");
			map.put("total", "0.00");

			map.put("richang", "0");
			map.put("office", "0");
			map.put("travel", "0");
			map.put("social", "0");
			map.put("national", "0");
			map.put("technology", "0");
			map.put("rent", "0");
			map.put("water", "0");
			map.put("docorate", "0");
			map.put("make", "0");
			map.put("other", "0");

			map.put("richangRemark", "");
			map.put("officeRemark", "");
			map.put("travelRemark", "");
			map.put("socialRemark", "");
			map.put("waterRemark", "");
			map.put("nationalRemark", "");
			map.put("technologyRemark", "");
			map.put("rentRemark", "");
			map.put("waterRemark", "");
			map.put("docorateRemark", "");
			map.put("makeRemark", "");
			map.put("otherRemark", "");
			return map;
		}
	}

	@Override
	public FinancialReports readFinancialReports(Integer id) {
		return this.financialReportsDAO.readFinancialReports(id);
	}

	@Override
	public FinancialOPExpenditure readFinancialOPExpenditure(String name, String finId) {
		return this.financialReportsDAO.readFinancialOPExpenditure(name, finId);
	}

	@Override
	public boolean save(List<FinancialOPExpenditure> list) {
		if (list != null && list.size() > 0) {
			for (FinancialOPExpenditure post : list) {
				if (!this.financialReportsDAO.save(post)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public List<FinancialOPExpenditure> findFinancialOPExpenditure(Integer finId) {
		if (finId != null) {
			return this.financialReportsDAO.findFinancialOPExpenditure(finId);
		}
		return null;
	}

	@Override
	public BigDecimal sumEmployeeRealExpenditure(Date $date1, Date $date2) {
		return this.financialReportsDAO.sumRealExpenditure($date1, $date2);
	}

	@Override
	public BigDecimal sumManagerRealExpenditure(Date $date1, Date $date2) {
		return this.financialReportsDAO.sumManagerRealExpenditure($date1, $date2);
	}

	@Override
	public BigDecimal sumActoresTonRealExpenditure(Date $date1, Date $date2) {
		return null;
	}

	@Override
	public BigDecimal sumActoresTffRealExpenditure(Date $date1, Date $date2) {
		return null;
	}

	@Override
	public FinancialNReports readReportsByDate(String date, SearchFilter filter) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		return this.financialReportsDAO.readReportsByDate(date1, date2, filter);
	}

	@Override
	public HSSFWorkbook export(FinancialNReports financialReports, List<PlatformsMonths> list) {
		String[] excelHeader = { "本月收款总额", "本月支出总计", "本月净利润", "上月净利润", "环比增长" };
		String[] $excelHeader = { "平台", "后台收入", "收入类型", "税点补贴", "平台应收款", "线上主播合计", "线下主播合计", "其他收入", "应收合计", "支出合计", "平台毛利润" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Campaign");
		HSSFRow row = sheet.createRow((int) 0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 16);
		style.setFont(font);
		int index = 0;
		HSSFCell title = row.createCell(index);
		title.setCellValue("财务对账");
		title.setCellStyle(style);
		index++;
		row = sheet.createRow(index);
		HSSFFont $font = wb.createFont();
		$font.setFontHeightInPoints((short) 12);
		style.setFont($font);
		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i);
			sheet.setColumnWidth(i, 32 * 250);
		}
		row = sheet.createRow(5);
		for (int i = 0; i < $excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue($excelHeader[i]);
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i);
			sheet.setColumnWidth(i, 32 * 250);
		}

		index++;
		if (financialReports != null) {
			row = sheet.createRow(index);
			row.createCell(0).setCellValue(financialReports.getCurrentIncome() != null ? financialReports.getCurrentIncome().toString() : "0.00");
			row.createCell(1).setCellValue(financialReports.getCurrentExpenditure() != null ? financialReports.getCurrentExpenditure().toString() : "0.00");
			row.createCell(2).setCellValue(financialReports.getCurrentProfit() != null ? financialReports.getCurrentProfit().toString() : "0.00");
			row.createCell(3).setCellValue(financialReports.getLastProfit() != null ? financialReports.getLastProfit().toString() : "0.00");
			row.createCell(4).setCellValue(financialReports.getCp_growthRate() != null ? financialReports.getCp_growthRate().toString() + "%" : "0.00%");
			index++;
		}
		index = 6;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow(index);
				row.setRowStyle(style);
				PlatformsMonths platformsMonths = list.get(i);
				row.createCell(0).setCellValue(platformsMonths.getPlatforms());
				row.createCell(1).setCellValue(platformsMonths.getBgIncome() != null ? platformsMonths.getBgIncome().toString() : "0.00");
				row.createCell(2).setCellValue(platformsMonths.getGenre());
				row.createCell(3).setCellValue(platformsMonths.getTaxSubsidy() != null ? platformsMonths.getTaxSubsidy().toString() : "0.00");
				row.createCell(4).setCellValue(platformsMonths.getReceivable() != null ? platformsMonths.getReceivable().toString() : "0.00");
				row.createCell(5).setCellValue(platformsMonths.getOnlineTotal() != null ? platformsMonths.getOnlineTotal().toString() : "0.00");
				row.createCell(6).setCellValue(platformsMonths.getOfflineTotal() != null ? platformsMonths.getOfflineTotal().toString() : "0.00");
				row.createCell(7).setCellValue(platformsMonths.getOtherIncome() != null ? platformsMonths.getOtherIncome().toString() : "0.00");
				row.createCell(8).setCellValue(platformsMonths.getIncomeTotal() != null ? platformsMonths.getIncomeTotal().toString() : "0.00");
				row.createCell(9).setCellValue(platformsMonths.getOutlayTotal() != null ? platformsMonths.getOutlayTotal().toString() : "0.00");
				row.createCell(10).setCellValue(platformsMonths.getNetProfit() != null ? platformsMonths.getNetProfit().toString() : "0.00");
				index++;
			}
		}
		index += 3;
		String[] $excelHead = { "平台", "频道应收", "频道支出", "备注" };
		row = sheet.createRow(index);
		for (int i = 0; i < $excelHead.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue($excelHead[i]);
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i);
		}
		index++;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				PlatformsMonths platformsMonths = list.get(i);
				if (platformsMonths.getChIncome().doubleValue() > 0 || platformsMonths.getChOutlay().doubleValue() > 0) {
					row = sheet.createRow(index);
					row.setRowStyle(style);
					row.createCell(0).setCellValue(platformsMonths.getPlatforms());
					row.createCell(1).setCellValue(platformsMonths.getChIncome() != null ? platformsMonths.getChIncome().toString() : "0.00");
					row.createCell(2).setCellValue(platformsMonths.getChOutlay() != null ? platformsMonths.getChOutlay().toString() : "0.00");
					row.createCell(3).setCellValue(platformsMonths.getRemarks());
					index++;
				}
			}
		}
		return wb;
	}

	@Override
	public boolean addFinancialNReports(FinancialNReports financialReports) {
		if (financialReports != null) {
			return this.financialReportsDAO.addFinancialNReports(financialReports);
		}
		return false;
	}

}
