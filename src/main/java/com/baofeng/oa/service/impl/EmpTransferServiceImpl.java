package com.baofeng.oa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.entity.Operator.Actores;
import com.baofeng.commons.entity.RoleDetailsAtts;
import com.baofeng.commons.service.IMonitorLogService;
import com.baofeng.oa.bean.EmpTransferBean;
import com.baofeng.oa.dao.EmpTransferDAO;
import com.baofeng.oa.entity.BaseEnums.EmpTransfer;
import com.baofeng.oa.entity.BaseEnums.EmployeType;
import com.baofeng.oa.entity.Employee;
import com.baofeng.oa.entity.EmployeeTransfer;
import com.baofeng.oa.service.IEmpTransferService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.utils.IBaseDAO;
import com.baofeng.utils.NPageResult;
import com.baofeng.utils.SearchFilter;
import com.baofeng.work.WorkManagers;
import com.baofeng.work.WorkNames;

@Service("empTransferService")
public class EmpTransferServiceImpl implements IEmpTransferService {

	@Autowired
	private EmpTransferDAO empTransferDAO;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private IMonitorLogService monitorLogService;
	@Autowired
	private IBaseDAO baseDAO;

	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NPageResult<?> readPages(int pageSize, int curPage, String sortName, String sortOrder, SearchFilter searchFilter, String ads, List<RoleDetailsAtts> platList) {
		NPageResult rows = this.empTransferDAO.readPages(pageSize, curPage, sortName, sortOrder, searchFilter, ads, platList);
		if (rows != null && rows.getData().size() > 0) {
			List<EmpTransferBean> beanList = new ArrayList<EmpTransferBean>();
			for (Object o : rows.getData()) {
				EmployeeTransfer employeeTra = (EmployeeTransfer) o;
				EmpTransferBean bean = new EmpTransferBean();
				bean.setId(employeeTra.getId().toString());
				bean.setNumber(employeeTra.getNumber().toString());
				bean.setName(employeeTra.getName());
				bean.setAliasname(employeeTra.getAliasname());
				bean.setNewDepartName(employeeTra.getNewDepartName());
				bean.setNewPositionName(employeeTra.getNewPositionName());
				bean.setSupervisorName(employeeTra.getSupervisorName());
				bean.setOldDepartName(employeeTra.getOldDepartName());
				bean.setOldPositionName(employeeTra.getOldPositionName());
				bean.setEmployeType(employeeTra.getEmployeType().toString());
				bean.setCreateName(employeeTra.getCreateName());
				bean.setEmpTransfer(employeeTra.getEmpTransfer() == EmpTransfer.UNKNOWN ? "处理中" : "完成转岗");
				bean.setTransferDate(employeeTra.getTransferDate());
				beanList.add(bean);
			}
			rows.setData(beanList);
		}
		return rows;
	}

	@Override
	public EmployeeTransfer readTraEmployee(Integer number) {
		if (number != null && number > 0) {
			EmployeeTransfer employeeTra = this.empTransferDAO.readTraEmployee(number);
			return employeeTra;
		}
		return null;
	}

	public List<EmployeeTransfer> readTraEmployee(Date $date1, Date $date2) {
		return this.empTransferDAO.readTraEmployeeByDate($date1, $date2);
	}

	@Override
	public boolean saveEmployeeTra(EmployeeTransfer employeeT) {
		return this.empTransferDAO.saveEmployeeTra(employeeT);
	}

	@Override
	public boolean updatePassedEmpTransfer(String ids, HttpServletRequest request) {
		String[] $ids = ids.split(",");
		for (String number : $ids) {
			EmployeeTransfer employeeTransfer = this.empTransferDAO.readTraEmployee(Integer.valueOf(number));
			Employee post = this.employeeService.readEmployeebyNumber(Integer.valueOf(number), employeeTransfer.getBranchs());

			if (post != null) {
				if (this.empTransferDAO.saveEmployeeTra(employeeTransfer)) {
					if (post.getEmployeType() == EmployeType.ACTORES || post.getActores() == Actores.YES) {
						this.workManagers.onEvents(WorkNames.ACTORES_TRANSFER, employeeTransfer);
					} else if (post.getEmployeType() == EmployeType.MANAGER) {
						this.workManagers.onEvents(WorkNames.MANAGER_TRANSFER, employeeTransfer);
					} else if (post.getEmployeType() == EmployeType.TALENT) {
						this.workManagers.onEvents(WorkNames.TALENT_TRANSFER, employeeTransfer);
					} else {
						this.workManagers.onEvents(WorkNames.EMPLOYEE_TRANSFER, employeeTransfer);
					}
				}
			}
		}
		return true;
	}

}
