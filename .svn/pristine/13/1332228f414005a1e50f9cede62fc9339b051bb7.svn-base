package com.baofeng.oa.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baofeng.oa.entity.EmpAttendance;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

public interface IEmpAttendanceService {

	List<EmpAttendance> readAllEmpAttendanceByDate(String date);

	@SuppressWarnings("rawtypes")
	NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, String id, String date, String fast, SearchFilter searchFilter);

	EmpAttendance readAttendanceById(Integer id);

	List<EmpAttendance> findAllEmpAttendanceByDate(String date);

	Float findAttendanceByNumberAndDate(Integer number, Integer branchs, String date);

	Float findAttendanceByNumberAndDate(Integer number, Integer branchs, Date $date1, Date $date2);

	List<String> readFinSalaryEmpDate(SearchFilter filter);

	void addEmpAttendanceList(List<EmpAttendance> attList);

	void addEmpAttendanceList(List<EmpAttendance> attList, HttpServletRequest request);

	boolean addEmpAttendance(EmpAttendance atte);

	boolean addEmpAttendance(EmpAttendance emp, HttpServletRequest request);

	EmpAttendance readAllEmpAttendanceByDateByNumber(Integer number, Integer branchs, Date date1, Date date2);

	EmpAttendance readEmpAttendanceByDateByNumber(Integer number, Integer branchs, Date createDT);

	boolean updateEmpAttendance(EmpAttendance empa);
}
