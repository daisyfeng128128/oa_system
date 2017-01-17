package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.EmpAttendanceBean;
import com.baofeng.oa.dao.EmpAttendanceDAO;
import com.baofeng.oa.entity.BranchOffice;
import com.baofeng.oa.entity.EmpAttendance;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.IEmpAttendanceService;
import com.baofeng.utils.CustomDateUtils;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;

@Service("empAttendanceService")
public class EmpAttendanceServiceImpl implements IEmpAttendanceService {

	@Autowired
	private EmpAttendanceDAO empAttendanceDAO;
	@Autowired
	private IMonitorLogService monitorLogService;
	@Autowired
	private IBranchOfficeService branchOfficeService;

	@Override
	public List<EmpAttendance> readAllEmpAttendanceByDate(String date) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		return this.empAttendanceDAO.readAllEmpAttendanceByDate(date1, date2);

	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult readPages(int pageSize, int curPage, String sortName, String sortOrder, String id, String date, String fast, SearchFilter searchFilter) {

		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		NPageResult rows = this.empAttendanceDAO.readPages(pageSize, curPage, sortName, sortOrder, id, date1, date2, fast, searchFilter);
		List<EmpAttendanceBean> listBean = new ArrayList<EmpAttendanceBean>();
		if (rows.getData() != null && rows.getData().size() > 0) {
			BranchOffice branchOffice = null;
			for (Object o : rows.getData()) {
				EmpAttendance emp = (EmpAttendance) o;
				EmpAttendanceBean bean = new EmpAttendanceBean();
				if (emp.getAttendance() != null)
					bean.setAttendance(emp.getAttendance().toString());
				if (emp.getEmployee().getDepart() != null)
					bean.setDepartName(emp.getEmployee().getDepart().getName());
				bean.setId(emp.getId());
				bean.setName(emp.getEmployee().getName());
				bean.setAliasname(emp.getEmployee().getAliasname());
				if (branchOffice == null)
					branchOffice = this.branchOfficeService.readBranchOffice(emp.getEmployee().getBranchs());
				if (branchOffice != null)
					bean.setNumber(branchOffice.getNumberHead() + String.format("%04d", emp.getEmployee().getNumber()));
				else
					bean.setNumber(String.format("%04d", emp.getEmployee().getNumber()));
				bean.setPosiName(emp.getEmployee().getPosition().getName());
				bean.setRemark(emp.getRemark());
				listBean.add(bean);
			}
		}
		rows.setData(listBean);
		return rows;
	}

	@Override
	public void addEmpAttendanceList(List<EmpAttendance> attList, HttpServletRequest request) {
		if (attList != null && attList.size() > 0) {
			for (EmpAttendance empAtt : attList) {
				this.addEmpAttendance(empAtt, request);
			}
		}
	}

	@Override
	public void addEmpAttendanceList(List<EmpAttendance> attList) {
		if (attList != null && attList.size() > 0) {
			for (EmpAttendance empAtt : attList) {
				this.empAttendanceDAO.addEmpAttendance(empAtt);
			}
		}
	}

	@Override
	public boolean addEmpAttendance(EmpAttendance atte) {
		return this.empAttendanceDAO.addEmpAttendance(atte);
	}

	@Override
	public boolean addEmpAttendance(EmpAttendance emp, HttpServletRequest request) {
		if (emp != null) {
			if (emp.getId() != null && emp.getId().intValue() > 0) {
				this.monitorLogService.logsUpdate(request, emp.getEmployee().getName() + "的考勤:" + emp.getAttendance());
			} else {
				this.monitorLogService.logsAdd(request, emp.getEmployee().getName() + "的考勤:" + emp.getAttendance());
			}
			if (this.empAttendanceDAO.addEmpAttendance(emp)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public EmpAttendance readAttendanceById(Integer id) {
		if (id != null && id.intValue() > 0) {
			return this.empAttendanceDAO.readAttendanceById(id);
		}
		return null;
	}

	@Override
	public List<EmpAttendance> findAllEmpAttendanceByDate(String date) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		return this.empAttendanceDAO.findAllEmpAttendanceByDate(date1, date2);
	}

	@Override
	public Float findAttendanceByNumberAndDate(Integer number, Integer branchs, String date) {
		Date date1 = CustomDateUtils.Handler.currentMonthFirstDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		Date date2 = CustomDateUtils.Handler.currentMonthEndDay(CustomDateUtils.format(date, CustomDateUtils.format7));
		if (number != null && number.intValue() > 0) {
			EmpAttendance emp = this.empAttendanceDAO.readAttendanceByNumberAndDate(number, branchs, date1, date2);
			if (emp != null) {
				return emp.getAttendance() != null ? emp.getAttendance() : 0.00f;
			}
		}
		return 0.00f;
	}

	@Override
	public Float findAttendanceByNumberAndDate(Integer number, Integer branchs, Date $date1, Date $date2) {
		EmpAttendance attendance = this.empAttendanceDAO.readAttendanceByNumberAndDate(number, branchs, $date1, $date2);
		if (attendance != null) {
			return attendance.getAttendance() == null ? 0.00f : attendance.getAttendance();
		}
		return 0.00f;
	}

	@Override
	public EmpAttendance readAllEmpAttendanceByDateByNumber(Integer number, Integer branchs, Date date1, Date date2) {
		if (number != null && number.intValue() > 0 && branchs != null && branchs.intValue() > 0) {
			try {
				EmpAttendance emp = this.empAttendanceDAO.readAllEmpAttendanceByDateByNumber(number, branchs, date1, date2);
				return emp;
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public List<String> readFinSalaryEmpDate(SearchFilter filter) {
		return this.empAttendanceDAO.readFinSalaryEmpDate(filter);
	}

	@Override
	public EmpAttendance readEmpAttendanceByDateByNumber(Integer number, Integer branchs, Date createDT) {
		return this.empAttendanceDAO.readEmpAttendance(number, branchs, CustomDateUtils.Handler.currentBeginDay(createDT), CustomDateUtils.Handler.currentEndDay(createDT));
	}

	@Override
	public boolean updateEmpAttendance(EmpAttendance empa) {
		return this.empAttendanceDAO.updateEmpAttendance(empa);
	}

}
