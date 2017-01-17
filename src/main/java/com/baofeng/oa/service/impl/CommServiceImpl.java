package com.baofeng.oa.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofeng.commons.service.IRolesService;
import com.baofeng.commons.service.IUsersService;
import com.baofeng.oa.dao.PlatformsMonthsOutlayDAO;
import com.baofeng.oa.dao.PlatformsMonthsTotalOutlayDAO;
import com.baofeng.oa.service.CommService;
import com.baofeng.oa.service.IActoresService;
import com.baofeng.oa.service.IBigtopService;
import com.baofeng.oa.service.IBranchOfficeService;
import com.baofeng.oa.service.ICostCheckService;
import com.baofeng.oa.service.IEmpAttendanceService;
import com.baofeng.oa.service.IEmpTransferService;
import com.baofeng.oa.service.IEmployeeService;
import com.baofeng.oa.service.IFinEarningsIncomeService;
import com.baofeng.oa.service.IFinSalariesOnlineService;
import com.baofeng.oa.service.IFinSalariesService;
import com.baofeng.oa.service.IFinSalariesTalentService;
import com.baofeng.oa.service.IFinancialAccountsService;
import com.baofeng.oa.service.IFinancialReportsService;
import com.baofeng.oa.service.IManagerService;
import com.baofeng.oa.service.IPlatformsActivityOutlayService;
import com.baofeng.oa.service.IPlatformsActoresService;
import com.baofeng.oa.service.IPlatformsManagerOutlayService;
import com.baofeng.oa.service.IPlatformsManagerService;
import com.baofeng.oa.service.IPlatformsMonthsOutlayService;
import com.baofeng.oa.service.IPlatformsMonthsService;
import com.baofeng.oa.service.IPlatformsOfflineIncomeService;
import com.baofeng.oa.service.IPlatformsOnlineIncomeService;
import com.baofeng.oa.service.IPlatformsService;
import com.baofeng.oa.service.ISalariesStatisticsService;
import com.baofeng.oa.service.ISalaryOnlineService;
import com.baofeng.oa.service.ITalentService;
import com.baofeng.work.WorkManagers;

@Service("commService")
public class CommServiceImpl implements CommService {
	@Autowired
	private IBigtopService bigtopService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IActoresService actoresService;
	@Autowired
	private IUsersService usersService;
	@Autowired
	private IRolesService rolesService;
	@Autowired
	private IManagerService managerService;
	@Autowired
	private IPlatformsService platformsService;
	@Autowired
	private IPlatformsActoresService platformsActoresService;
	@Autowired
	private IPlatformsManagerService platformsManagerService;
	@Autowired
	private IPlatformsOfflineIncomeService offlineIncomeService;
	@Autowired
	private IPlatformsMonthsService platformsMonthsService;
	@Autowired
	private IPlatformsMonthsOutlayService platformsMonthsOutlayService;
	@Autowired
	private IPlatformsManagerOutlayService platformsManagerOutlayService;
	@Autowired
	private ISalaryOnlineService salaryOnlineService;
	@Autowired
	private IPlatformsOnlineIncomeService onlineIncome;
	@Autowired
	private IFinancialReportsService financialReportsService;
	@Autowired
	private IEmpAttendanceService empAttendanceService;
	@Autowired
	private IFinSalariesService finSalariesService;
	@Autowired
	private IFinSalariesOnlineService finSalariesOnlineService;
	@Autowired
	private IFinancialAccountsService financialAccountsService;
	@Autowired
	private ITalentService talentService;
	@Autowired
	private IFinSalariesTalentService finSalariesTalentService;
	@Autowired
	private IFinEarningsIncomeService finEarningsIncomeService;
	@Autowired
	private ISalariesStatisticsService salariesStatisticsService;
	@Autowired
	private IPlatformsActivityOutlayService activityOutlayService;
	@Autowired
	private PlatformsMonthsOutlayDAO platformsMonthsOutlayDAO;
	@Autowired
	private PlatformsMonthsTotalOutlayDAO platformsMonthsTotalOutlayDAO;
	@Autowired
	private ICostCheckService costCheckService;
	@Autowired
	private IBranchOfficeService branchOfficeService;
	@Autowired
	private WorkManagers workManagers;
	@Autowired
	private IEmpTransferService empTransferService;

	@Override
	public Map<String, Object> getService() {
		Map<String, Object> service = new HashMap<String, Object>();
		service.put(IBigtopService.class.getName(), this.bigtopService);
		service.put(IBranchOfficeService.class.getName(), this.branchOfficeService);
		service.put(IPlatformsService.class.getName(), this.platformsService);
		service.put(IFinancialReportsService.class.getName(), this.financialReportsService);
		service.put(ISalaryOnlineService.class.getName(), this.salaryOnlineService);
		service.put(IPlatformsOfflineIncomeService.class.getName(), this.offlineIncomeService);
		service.put(IPlatformsActivityOutlayService.class.getName(), this.activityOutlayService);
		service.put(IPlatformsMonthsOutlayService.class.getName(), this.platformsMonthsOutlayService);
		service.put(IPlatformsOnlineIncomeService.class.getName(), this.onlineIncome);
		service.put(IPlatformsManagerOutlayService.class.getName(), this.platformsManagerOutlayService);
		service.put(IPlatformsMonthsService.class.getName(), this.platformsMonthsService);
		service.put(IPlatformsManagerService.class.getName(), this.platformsManagerService);
		service.put(IPlatformsActoresService.class.getName(), this.platformsActoresService);
		service.put(IManagerService.class.getName(), this.managerService);
		service.put(IRolesService.class.getName(), this.rolesService);
		service.put(IUsersService.class.getName(), this.usersService);
		service.put(IActoresService.class.getName(), this.actoresService);
		service.put(IEmployeeService.class.getName(), this.employeeService);
		service.put(IEmpAttendanceService.class.getName(), this.empAttendanceService);
		service.put(IFinSalariesService.class.getName(), this.finSalariesService);
		service.put(IFinSalariesOnlineService.class.getName(), this.finSalariesOnlineService);
		service.put(IFinancialAccountsService.class.getName(), this.financialAccountsService);
		service.put(ITalentService.class.getName(), this.talentService);
		service.put(IFinSalariesTalentService.class.getName(), this.finSalariesTalentService);
		service.put(IFinEarningsIncomeService.class.getName(), this.finEarningsIncomeService);
		service.put(ISalariesStatisticsService.class.getName(), this.salariesStatisticsService);
		service.put(PlatformsMonthsOutlayDAO.class.getName(), this.platformsMonthsOutlayDAO);
		service.put(IEmpTransferService.class.getName(),this.empTransferService);
		service.put(ICostCheckService.class.getName(), this.costCheckService);
		service.put(PlatformsMonthsTotalOutlayDAO.class.getName(), this.platformsMonthsTotalOutlayDAO);
		service.put(WorkManagers.class.getName(), this.workManagers);
		return service;
	}

}
