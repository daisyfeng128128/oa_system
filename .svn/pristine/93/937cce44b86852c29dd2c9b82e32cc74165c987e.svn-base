package com.baofeng.work;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.baofeng.oa.service.CommService;

/**
 * 功能：后台工作
 * */
public class WorkManagers {

	@Autowired
	private CommService commService;

	public static ConcurrentHashMap<WorkNames, BaseObservable> events = new ConcurrentHashMap<WorkNames, BaseObservable>();

	public synchronized boolean onEvents(WorkNames names, Object... args) {
		if (events != null && events.size() == 0)
			this.onLoadEventListener();
		if (events.containsKey(names)) {
			BaseObservable base = events.get(names);
			base.setChanged();
			base.notifyObservers(args);
			return true;
		}
		return false;
	}

	protected boolean addEventListener(WorkNames names, BaseObserver observer) {
		if (!events.containsKey(names)) {
			BaseObservable baseObs = new BaseObservable();
			baseObs.addObserver(observer);
			events.put(names, baseObs);
			return true;
		}
		return false;
	}

	protected void onLoadEventListener() {
		this.addEventListener(WorkNames.MAKEALL, new MakeAllOnWorkListener(commService));
		this.addEventListener(WorkNames.FINSALARIES, new FinSalariesOnWorkListener(commService));
		this.addEventListener(WorkNames.EMPATTENDANCEEMPLOYEE, new EmpAttendanceEmployeeOnWorkListener(commService));
		this.addEventListener(WorkNames.EMPLOYEE_ADDACTORES, new EmployeeActoresOnWorkListener(commService));
		this.addEventListener(WorkNames.MANAGER, new PlatformsManagerOnWorkListener(commService));
		this.addEventListener(WorkNames.ACTORES, new PlatformsActoresOnWorkListener(commService));
		this.addEventListener(WorkNames.EMPLOYEE_ZHUANZHENG, new EmployeeGenrerOnWorkListener(commService));
		this.addEventListener(WorkNames.EMPLOYEE_TRANSFER, new EmpTransferOnWorkListener(commService));
		this.addEventListener(WorkNames.ACTORES_TRANSFER, new ActTransferOnWorkListener(commService));
		this.addEventListener(WorkNames.MANAGER_TRANSFER, new ManTransferOnWorkListener(commService));
		this.addEventListener(WorkNames.TALENT_TRANSFER, new TalTransferOnWorkListener(commService));
		this.addEventListener(WorkNames.ACTORES_MID, new MidActoresOnWorkListener(commService));
		this.addEventListener(WorkNames.MANAGER_MID, new MidManagersOnWorkListener(commService));
		this.addEventListener(WorkNames.PLATFORMS, new PlatformsOnWorkListener(commService));
		this.addEventListener(WorkNames.EMPLOYEE_LIZHI, new EmployeeLeaveOnWorkListener(commService));
		this.addEventListener(WorkNames.MANAGER_LIZHI, new LeavelManagersOnWorkListener(commService));
		this.addEventListener(WorkNames.MANAGER_DEL, new DelManagersOnWorkListener(commService));
		this.addEventListener(WorkNames.ADVANCEDSALARY, new MidAdvancetOnWorkListener(commService));
		this.addEventListener(WorkNames.EMPLOYEE_TALENT, new EmployeeTalentOnWorkListener(commService));
		this.addEventListener(WorkNames.EMPLOYEE_ADDMANAGERS, new EmployeeManagerOnWorkListener(commService));
		this.addEventListener(WorkNames.MASTER_STANDBY, new BigTopOnWorkListener(commService));
		this.addEventListener(WorkNames.OPERATION, new OperationOnWorkListener(commService));
		this.addEventListener(WorkNames.MIDADJUST, new MidAdjustOnWorkListener(commService));
		this.addEventListener(WorkNames.ACTORES_PDEL, new DelPlatformsActoresOnWorkListener(commService));
		this.addEventListener(WorkNames.BIGOUT, new BigOutOnWorkListener(commService));
		this.addEventListener(WorkNames.MANAGER_PDEL, new DelPlatformsManagerOnWorkListener(commService));
		this.addEventListener(WorkNames.CHECK_SALARY_FINANCIALREPORTS, new ReportsOnWorkListener(commService));
		this.addEventListener(WorkNames.MAKEOUTLAY, new MakeOutlayOnWorkListener(commService));
		this.addEventListener(WorkNames.MAKETOTALOUTLAY, new MakeTotalOutlayOnWorkListener(commService));
		this.addEventListener(WorkNames.FINSALARIESVALIDATION, new FinSalariesValidationOnWorkListener(commService));
		this.addEventListener(WorkNames.FINSALARIESACTORESOFFICE, new FinSalariesActoresOfflineOnWorkListener(commService));
		this.addEventListener(WorkNames.FINSALARIESMANAGEROFFLINE, new FinSalariesManagerOfflineOnWorkListener(commService));
		this.addEventListener(WorkNames.FINSALARIESACTORESONLINE, new FinSalariesActoresOnlineOnWorkListener(commService));
		this.addEventListener(WorkNames.FINSALARIESMANAGER, new FinSalariesManagerOnlineWorkListener(commService));
		this.addEventListener(WorkNames.FINSALARIESTALENT, new FinSalariesTalentOnWorkListener(commService));
		this.addEventListener(WorkNames.FINSALARIESBILLED, new FinSalariesBilledOnWorkListener(commService));
		this.addEventListener(WorkNames.COSTCHECK, new CostCheckOnWorkListener(commService));
		this.addEventListener(WorkNames.FINEARINGSINCOME, new FinEaringsIncomeOnWorkListener(commService));
		this.addEventListener(WorkNames.SALARIESSTATISTICS, new SalariesStatisticsOnWorkListener(commService));
		this.addEventListener(WorkNames.FINSALARIESONLINE, new FinSalariesOnlineOnWorkListener(commService));
		this.addEventListener(WorkNames.MAKEMANAGER, new MakeManagerOnWorkListener(commService));
		this.addEventListener(WorkNames.MAKEONLINE, new MakeOnlineOnWorkListener(commService));
		this.addEventListener(WorkNames.MAKEOFFINE, new MakeOfflineOnWorkListener(commService));
	}
}
