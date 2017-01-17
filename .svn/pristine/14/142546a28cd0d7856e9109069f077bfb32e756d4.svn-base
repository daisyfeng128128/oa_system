package com.baofeng.work;

public enum WorkNames {

	MANAGER("manager"),
	ACTORES("actores"),
	EMPLOYEE_ZHUANZHENG("employee_zhuanzheng"),
	EMPLOYEE_ADDACTORES("employee_addActores"),
	ACTORES_MID("actores_mid"),
	MANAGER_MID("manager_mid"), 
	PLATFORMS("platforms"),
	EMPLOYEE_LIZHI("employee_lizhi"),
	MANAGER_LIZHI("manager_lizhi"),
	MANAGER_DEL("manager_del"),
	CHECK_SALARYONLINE("check_salaryOnline"),
	CHECK_SALARYTHIRDMANAGER("check_SalaryThirdManager"),
	FIN_SALARY_OFFLINE("fin_salary_offline"),
	MANAGER_PDEL("manager_pDel"), 
	ACTORES_PDEL("actores_pDel"),
	CHECK_SALARY_FINANCIALREPORTS("check_salary_financialReports"),
	FIN_SALARY_EMPLOYEE("fin_salary_employee"),
	ADVANCEDSALARY("advancedSalary"),
	EMPLOYEE_TALENT("employee_talent"),
	EMPLOYEE_ADDMANAGERS("employee_addManagers"),
	MASTER_STANDBY("master_standby"),
	OPERATION("operation"),
	MIDADJUST("midAdjust"),
	BIGOUT("bigOut"),
	MAKEOUTLAY("makeOutlay"),
	MAKETOTALOUTLAY("makeTotalOutlay"),
	FINSALARIESVALIDATION("finSalariesValidation"),
	FINSALARIESACTORESOFFICE("finSalariesActoresOffline"),
	FINSALARIESMANAGEROFFLINE("finSalariesManagerOffline"),
	FINSALARIESACTORESONLINE("finSalariesActoresOnline"),
	FINSALARIESMANAGER("finSalariesManager"),
	FINSALARIESTALENT("finSalariesTalent"),
	FINSALARIESBILLED("finSalariesBilled"),
	EMPATTENDANCEEMPLOYEE("empAttendanceEmployee"),
	COSTCHECK("costCheck"),
	FINEARINGSINCOME("finEaringsIncome"), 
	SALARIESSTATISTICS("salariesStatistics"),
	FINSALARIES("finSalaries"),
	FINSALARIESONLINE("finSalariesOnline"),
	MAKEMANAGER("makeManager"),
	MAKEONLINE("makeOnline"),
	MAKEOFFINE("makeOffline"), 
	MAKEALL("makeAll"),
	EMPLOYEE_TRANSFER("employee_zhuangang"), 
	ACTORES_TRANSFER("actores_zhuangang"), 
	MANAGER_TRANSFER("mannager_zhuangang"), 
	TALENT_TRANSFER("talent_zhuangang");	
	
	private String values;

	private WorkNames(String values) {
		this.values = values;
	}

	public static WorkNames nvalueOf(String value) {
		for (WorkNames names : WorkNames.values()) {
			if (names.values.equals(value)) {
				return names;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return this.values;
	}
}
