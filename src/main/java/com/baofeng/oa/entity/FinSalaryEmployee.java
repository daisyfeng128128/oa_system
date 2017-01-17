package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.oa.entity.BaseEnums.Account;
import com.baofeng.oa.entity.BaseEnums.Grant;

/**
 * 功能：员工工资(非艺人)
 * */
@Entity
@Table(name = "salaryEmployee")
public class FinSalaryEmployee extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -681181364389933974L;

	/** 员工号 */
	private Integer number;
	/** 姓名 */
	private String name;
	/** 部门名称 */
	private String deptName;
	/** 职位 */
	private String positionsName;
	/** 户口类型 */
	private Account account;
	/** 基本底薪 */
	private BigDecimal basicSalary = new BigDecimal(0.00);
	/** 奖金 */
	private BigDecimal bonus = new BigDecimal(0.00);
	
	// 个人部分//////////////////////////////////////////////
	/** 养老金 */
	private BigDecimal pn_pension = new BigDecimal(0.00);
	/** 医疗保险 */
	private BigDecimal pn_medicare = new BigDecimal(0.00);
	/** 失业保险 */
	private BigDecimal pn_unemployment = new BigDecimal(0.00);
	/** 公积金 */
	private BigDecimal pn_provident = new BigDecimal(0.00);
	/** 补充公积金 */
	private BigDecimal pn_replenishProvident = new BigDecimal(0.00);
	/** 社保合计 */
	private BigDecimal pn_totalSocial = new BigDecimal(0.00);

	// 公司部分/////////////////////////////////////////////////
	/** 养老金 */
	private BigDecimal cp_pension = new BigDecimal(0.00);
	/** 医疗保险 */
	private BigDecimal cp_medicare = new BigDecimal(0.00);
	/** 失业保险 */
	private BigDecimal cp_unemployment = new BigDecimal(0.00);
	/** 公积金 */
	private BigDecimal cp_provident = new BigDecimal(0.00);
	/** 补充公积金 */
	private BigDecimal cp_replenishProvident = new BigDecimal(0.00);
	/** 工伤保险 */
	private BigDecimal cp_occupationalInjury = new BigDecimal(0.00);
	/** 生育保险 */
	private BigDecimal cp_fertility = new BigDecimal(0.00);
	
	/** 社保合计 */
	private BigDecimal cp_totalSocial = new BigDecimal(0.00);
	/** 计税工资 */
	private BigDecimal taxableSalary = new BigDecimal(0.00);
	/** 个人所得税 */
	private BigDecimal individualIncomeTax = new BigDecimal(0.00);
	/** 其他补贴 */
	private BigDecimal otherSubsidy = new BigDecimal(0.00);
	/** 其他扣除 */
	private BigDecimal otherDeduct = new BigDecimal(0.00);
	/** 迟到 */
	private BigDecimal beLate = new BigDecimal(0.00);
	/** 事假 */
	private BigDecimal casualLeave = new BigDecimal(0.00);
	/** 病假 */
	private BigDecimal sickLeave = new BigDecimal(0.00);
	/** 报销费用 */
	private BigDecimal applyFee = new BigDecimal(0.00);
	/** 实际支出 */
	private BigDecimal realExpenditure = new BigDecimal(0.00);
	/** 实发工资 */
	private BigDecimal realitySalary = new BigDecimal(0.00);
	/** 备注 */
	private String remarks;
	/** 发放工资状态 */
	private Grant billed  = Grant.UNBILLED;
	
	
	public BigDecimal getBeLate() {
		return beLate;
	}

	public void setBeLate(BigDecimal beLate) {
		this.beLate = beLate;
	}

	public BigDecimal getCasualLeave() {
		return casualLeave;
	}

	public void setCasualLeave(BigDecimal casualLeave) {
		this.casualLeave = casualLeave;
	}

	public BigDecimal getSickLeave() {
		return sickLeave;
	}

	public void setSickLeave(BigDecimal sickLeave) {
		this.sickLeave = sickLeave;
	}

	public BigDecimal getApplyFee() {
		return applyFee;
	}

	public void setApplyFee(BigDecimal applyFee) {
		this.applyFee = applyFee;
	}

	public Grant getBilled() {
		return billed;
	}

	public void setBilled(Grant billed) {
		this.billed = billed;
	}

	public Integer getNumber() {
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

	public BigDecimal getBasicSalary() {
		return basicSalary;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public BigDecimal getPn_pension() {
		return pn_pension;
	}

	public BigDecimal getPn_medicare() {
		return pn_medicare;
	}

	public BigDecimal getPn_unemployment() {
		return pn_unemployment;
	}

	public BigDecimal getPn_provident() {
		return pn_provident;
	}

	public BigDecimal getPn_replenishProvident() {
		return pn_replenishProvident;
	}

	public BigDecimal getPn_totalSocial() {
		return pn_totalSocial;
	}

	public BigDecimal getCp_pension() {
		return cp_pension;
	}

	public BigDecimal getCp_medicare() {
		return cp_medicare;
	}

	public BigDecimal getCp_unemployment() {
		return cp_unemployment;
	}

	public BigDecimal getCp_provident() {
		return cp_provident;
	}

	public BigDecimal getCp_replenishProvident() {
		return cp_replenishProvident;
	}

	public BigDecimal getCp_occupationalInjury() {
		return cp_occupationalInjury;
	}

	public BigDecimal getCp_fertility() {
		return cp_fertility;
	}

	public BigDecimal getCp_totalSocial() {
		return cp_totalSocial;
	}

	public BigDecimal getIndividualIncomeTax() {
		return individualIncomeTax;
	}

	public BigDecimal getOtherSubsidy() {
		return otherSubsidy;
	}

	public BigDecimal getOtherDeduct() {
		return otherDeduct;
	}

	public BigDecimal getRealExpenditure() {
		return realExpenditure;
	}

	public BigDecimal getRealitySalary() {
		return realitySalary;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setNumber(Integer number) {
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

	public void setBasicSalary(BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public void setPn_pension(BigDecimal pn_pension) {
		this.pn_pension = pn_pension;
	}

	public void setPn_medicare(BigDecimal pn_medicare) {
		this.pn_medicare = pn_medicare;
	}

	public void setPn_unemployment(BigDecimal pn_unemployment) {
		this.pn_unemployment = pn_unemployment;
	}

	public void setPn_provident(BigDecimal pn_provident) {
		this.pn_provident = pn_provident;
	}

	public void setPn_replenishProvident(BigDecimal pn_replenishProvident) {
		this.pn_replenishProvident = pn_replenishProvident;
	}

	public void setPn_totalSocial(BigDecimal pn_totalSocial) {
		this.pn_totalSocial = pn_totalSocial;
	}

	public void setCp_pension(BigDecimal cp_pension) {
		this.cp_pension = cp_pension;
	}

	public void setCp_medicare(BigDecimal cp_medicare) {
		this.cp_medicare = cp_medicare;
	}

	public void setCp_unemployment(BigDecimal cp_unemployment) {
		this.cp_unemployment = cp_unemployment;
	}

	public void setCp_provident(BigDecimal cp_provident) {
		this.cp_provident = cp_provident;
	}

	public void setCp_replenishProvident(BigDecimal cp_replenishProvident) {
		this.cp_replenishProvident = cp_replenishProvident;
	}

	public void setCp_occupationalInjury(BigDecimal cp_occupationalInjury) {
		this.cp_occupationalInjury = cp_occupationalInjury;
	}

	public void setCp_fertility(BigDecimal cp_fertility) {
		this.cp_fertility = cp_fertility;
	}

	public void setCp_totalSocial(BigDecimal cp_totalSocial) {
		this.cp_totalSocial = cp_totalSocial;
	}

	public void setIndividualIncomeTax(BigDecimal individualIncomeTax) {
		this.individualIncomeTax = individualIncomeTax;
	}

	public void setOtherSubsidy(BigDecimal otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}

	public void setOtherDeduct(BigDecimal otherDeduct) {
		this.otherDeduct = otherDeduct;
	}

	public void setRealExpenditure(BigDecimal realExpenditure) {
		this.realExpenditure = realExpenditure;
	}

	public void setRealitySalary(BigDecimal realitySalary) {
		this.realitySalary = realitySalary;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public BigDecimal getTaxableSalary() {
		return taxableSalary;
	}

	public void setTaxableSalary(BigDecimal taxableSalary) {
		this.taxableSalary = taxableSalary;
	}

}
