package com.baofeng.oa.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baofeng.oa.entity.FinancialNReports;
import com.baofeng.oa.entity.FinancialOPExpenditure;
import com.baofeng.oa.entity.FinancialReports;
import com.baofeng.oa.entity.PlatformsMonths;
import com.baofeng.utils.SearchFilter;

public interface IFinancialReportsService {

	FinancialNReports findFinancialReportsByDate(Integer branchs, Date addMonths, Date addMonths2);

	boolean addFinancialReports(FinancialReports financialReports);

	Map<String, String> readFinancialReportsByDate(String date, SearchFilter searchFilter);

	FinancialReports readFinancialReports(Integer valueOf);

	FinancialOPExpenditure readFinancialOPExpenditure(String name, String id);

	boolean save(List<FinancialOPExpenditure> list);

	List<FinancialOPExpenditure> findFinancialOPExpenditure(Integer id);

	BigDecimal sumEmployeeRealExpenditure(Date $date1, Date $date2);

	BigDecimal sumManagerRealExpenditure(Date $date1, Date $date2);

	BigDecimal sumActoresTonRealExpenditure(Date $date1, Date $date2);

	BigDecimal sumActoresTffRealExpenditure(Date $date1, Date $date2);

	FinancialNReports readReportsByDate(String date, SearchFilter searchFilter);

	HSSFWorkbook export(FinancialNReports financialReports, List<PlatformsMonths> list);

	boolean addFinancialNReports(FinancialNReports financialReports);

}
