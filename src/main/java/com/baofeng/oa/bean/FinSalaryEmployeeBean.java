package com.baofeng.oa.bean;

import java.io.Serializable;

/**
 * 功能：非艺人工资数据对像
 * */
@SuppressWarnings("serial")
public class FinSalaryEmployeeBean implements Serializable {

	private String id;
	/** 员工号 */
	private String number;
	/** 姓名 */
	private String name;
	/** 部门名称 */
	private String deptName;
	/** 职位 */
	private String positionsName;
	/** 基本底薪 */
	private String basicSalary;
	/** 奖金 */
	private String bonus;
	// 个人部分//////////////////////////////////////////////
	/** 养老金 */
	private String pn_pension;
	/** 医疗保险 */
	private String pn_medicare;
	/** 失业保险 */
	private String pn_unemployment;
	/** 公积金 */
	private String pn_provident;
	/** 补充公积金 */
	private String pn_replenishProvident;
	/** 社保合计 */
	private String pn_totalSocial;

	// 公司部分/////////////////////////////////////////////////
	/** 养老金 */
	private String cp_pension;
	/** 医疗保险 */
	private String cp_medicare;
	/** 失业保险 */
	private String cp_unemployment;
	/** 公积金 */
	private String cp_provident;
	/** 补充公积金 */
	private String cp_replenishProvident;
	/** 工伤保险 */
	private String cp_occupationalInjury;
	/** 生育保险 */
	private String cp_fertility;
	/** 社保合计 */
	private String cp_totalSocial;

	/** 计税工资 */
	private String taxableSalary;
	/** 个人所得税 */
	private String individualIncomeTax;
	/** 其他补贴 */
	private String otherSubsidy;
	/** 其他扣除 */
	private String otherDeduct;
	/** 实际支出 */
	private String realExpenditure;
	/** 实发工资 */
	private String realitySalary;
	private String bill;
	/** 备注 */
	private String remarks;
	/** 迟到 */
	private String beLate ;
	/** 事假 */
	private String casualLeave;
	/** 病假 */
	private String sickLeave;
	/** 报销费用 */
	private String applyFee;

	
	
	public String getBeLate() {
		return beLate;
	}

	public void setBeLate(String beLate) {
		this.beLate = beLate;
	}

	public String getCasualLeave() {
		return casualLeave;
	}

	public void setCasualLeave(String casualLeave) {
		this.casualLeave = casualLeave;
	}

	public String getSickLeave() {
		return sickLeave;
	}

	public void setSickLeave(String sickLeave) {
		this.sickLeave = sickLeave;
	}

	public String getApplyFee() {
		return applyFee;
	}

	public void setApplyFee(String applyFee) {
		this.applyFee = applyFee;
	}

	public String getBill() {
		return bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
	}

	public String getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getDeptName() {
		return deptName;
	}

	public String getPositionsName() {
		return positionsName;
	}

	public String getBasicSalary() {
		return basicSalary;
	}

	public String getBonus() {
		return bonus;
	}

	public String getPn_pension() {
		return pn_pension;
	}

	public String getPn_medicare() {
		return pn_medicare;
	}

	public String getPn_unemployment() {
		return pn_unemployment;
	}

	public String getPn_provident() {
		return pn_provident;
	}

	public String getPn_replenishProvident() {
		return pn_replenishProvident;
	}

	public String getPn_totalSocial() {
		return pn_totalSocial;
	}

	public String getCp_pension() {
		return cp_pension;
	}

	public String getCp_medicare() {
		return cp_medicare;
	}

	public String getCp_unemployment() {
		return cp_unemployment;
	}

	public String getCp_provident() {
		return cp_provident;
	}

	public String getCp_replenishProvident() {
		return cp_replenishProvident;
	}

	public String getCp_occupationalInjury() {
		return cp_occupationalInjury;
	}

	public String getCp_fertility() {
		return cp_fertility;
	}

	public String getCp_totalSocial() {
		return cp_totalSocial;
	}

	public String getIndividualIncomeTax() {
		return individualIncomeTax;
	}

	public String getOtherSubsidy() {
		return otherSubsidy;
	}

	public String getOtherDeduct() {
		return otherDeduct;
	}

	public String getRealExpenditure() {
		return realExpenditure;
	}

	public String getRealitySalary() {
		return realitySalary;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setPositionsName(String positionsName) {
		this.positionsName = positionsName;
	}

	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public void setPn_pension(String pn_pension) {
		this.pn_pension = pn_pension;
	}

	public void setPn_medicare(String pn_medicare) {
		this.pn_medicare = pn_medicare;
	}

	public void setPn_unemployment(String pn_unemployment) {
		this.pn_unemployment = pn_unemployment;
	}

	public void setPn_provident(String pn_provident) {
		this.pn_provident = pn_provident;
	}

	public void setPn_replenishProvident(String pn_replenishProvident) {
		this.pn_replenishProvident = pn_replenishProvident;
	}

	public void setPn_totalSocial(String pn_totalSocial) {
		this.pn_totalSocial = pn_totalSocial;
	}

	public void setCp_pension(String cp_pension) {
		this.cp_pension = cp_pension;
	}

	public void setCp_medicare(String cp_medicare) {
		this.cp_medicare = cp_medicare;
	}

	public void setCp_unemployment(String cp_unemployment) {
		this.cp_unemployment = cp_unemployment;
	}

	public void setCp_provident(String cp_provident) {
		this.cp_provident = cp_provident;
	}

	public void setCp_replenishProvident(String cp_replenishProvident) {
		this.cp_replenishProvident = cp_replenishProvident;
	}

	public void setCp_occupationalInjury(String cp_occupationalInjury) {
		this.cp_occupationalInjury = cp_occupationalInjury;
	}

	public void setCp_fertility(String cp_fertility) {
		this.cp_fertility = cp_fertility;
	}

	public void setCp_totalSocial(String cp_totalSocial) {
		this.cp_totalSocial = cp_totalSocial;
	}

	public void setIndividualIncomeTax(String individualIncomeTax) {
		this.individualIncomeTax = individualIncomeTax;
	}

	public void setOtherSubsidy(String otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}

	public void setOtherDeduct(String otherDeduct) {
		this.otherDeduct = otherDeduct;
	}

	public void setRealExpenditure(String realExpenditure) {
		this.realExpenditure = realExpenditure;
	}

	public void setRealitySalary(String realitySalary) {
		this.realitySalary = realitySalary;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTaxableSalary() {
		return taxableSalary;
	}

	public void setTaxableSalary(String taxableSalary) {
		this.taxableSalary = taxableSalary;
	}

}
