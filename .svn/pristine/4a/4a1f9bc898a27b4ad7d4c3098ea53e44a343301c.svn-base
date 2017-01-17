package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.oa.entity.BaseEnums.Grant;

/**
 * 功能：员工工资(频道助理)
 * */
@Entity
@Table(name = "salaryManager")
public class FinSalaryManager extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 5365244310335365632L;
	/** 员工号 */
	private Integer number;
	/** 姓名 */
	private String name;
	/** 艺名 */
	private String aliasname;
	/** 出勤天数 */
	private Float attendance = new Float(0);
	/** 基本工资 */
	private BigDecimal basicSalary = new BigDecimal(0.00);
	/** 提成工资 */
	private BigDecimal pushMoney = new BigDecimal(0.00);
	
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

	/** 社保 */
	@Deprecated
	private BigDecimal socialSecurity = new BigDecimal(0.00);
	/** 公司社保支出 */
	@Deprecated
	private BigDecimal socialSecurityOutlay = new BigDecimal(0.00);
	/** 公司实际支出 */
	@Deprecated
	private BigDecimal netSalaryOutlay = new BigDecimal(0.00);

	/** 计税工资 */
	private BigDecimal taxableSalary = new BigDecimal(0.00);
	/** 个人所得税 */
	private BigDecimal individualIncomeTax = new BigDecimal(0.00);

	/** 迟到 */
	private BigDecimal beLate = new BigDecimal(0.00);
	/** 事假 */
	private BigDecimal casualLeave = new BigDecimal(0.00);
	/** 病假 */
	private BigDecimal sickLeave = new BigDecimal(0.00);
	/** 其他扣除 */
	private BigDecimal otherLeave = new BigDecimal(0.00);
	/** 平台扣除 */
	private BigDecimal platLeave = new BigDecimal(0.00);
	/** 餐补 */
	private BigDecimal foodSubsidy = new BigDecimal(0.00);
	/** 其他补贴 */
	private BigDecimal otherSubsidy = new BigDecimal(0.00);
	/** 平台补贴 */
	private BigDecimal platSubsidy = new BigDecimal(0.00);
	/** 报销费用 */
	private BigDecimal applyFee = new BigDecimal(0.00);
	/** 实发工资 */
	private BigDecimal realitySalary = new BigDecimal(0.00);
	
	/** 备注 */
	private String remarks;
	/** 助理id */
	private Integer managersid;
	/** 发放工资状态 */
	private Grant billed = Grant.UNBILLED;

	public Integer getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getAliasname() {
		return aliasname;
	}

	public BigDecimal getBasicSalary() {
		return basicSalary;
	}

	public BigDecimal getPushMoney() {
		return pushMoney;
	}

	public BigDecimal getSocialSecurity() {
		return socialSecurity;
	}

	public BigDecimal getBeLate() {
		return beLate;
	}

	public BigDecimal getCasualLeave() {
		return casualLeave;
	}

	public BigDecimal getSickLeave() {
		return sickLeave;
	}

	public BigDecimal getOtherLeave() {
		return otherLeave;
	}

	public BigDecimal getFoodSubsidy() {
		return foodSubsidy;
	}

	public BigDecimal getOtherSubsidy() {
		return otherSubsidy;
	}

	public BigDecimal getApplyFee() {
		return applyFee;
	}

	public BigDecimal getRealitySalary() {
		return realitySalary;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}

	public void setBasicSalary(BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
	}

	public void setPushMoney(BigDecimal pushMoney) {
		this.pushMoney = pushMoney;
	}

	public void setSocialSecurity(BigDecimal socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public void setBeLate(BigDecimal beLate) {
		this.beLate = beLate;
	}

	public void setCasualLeave(BigDecimal casualLeave) {
		this.casualLeave = casualLeave;
	}

	public void setSickLeave(BigDecimal sickLeave) {
		this.sickLeave = sickLeave;
	}

	public void setOtherLeave(BigDecimal otherLeave) {
		this.otherLeave = otherLeave;
	}

	public void setFoodSubsidy(BigDecimal foodSubsidy) {
		this.foodSubsidy = foodSubsidy;
	}

	public void setOtherSubsidy(BigDecimal otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}

	public void setApplyFee(BigDecimal applyFee) {
		this.applyFee = applyFee;
	}

	public void setRealitySalary(BigDecimal realitySalary) {
		this.realitySalary = realitySalary;
	}

	public Integer getManagersid() {
		return managersid;
	}

	public void setManagersid(Integer managersid) {
		this.managersid = managersid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Float getAttendance() {
		return attendance;
	}

	public void setAttendance(Float attendance) {
		this.attendance = attendance;
	}

	public BigDecimal getSocialSecurityOutlay() {
		return socialSecurityOutlay;
	}

	public void setSocialSecurityOutlay(BigDecimal socialSecurityOutlay) {
		this.socialSecurityOutlay = socialSecurityOutlay;
	}

	public BigDecimal getNetSalaryOutlay() {
		return netSalaryOutlay;
	}

	public void setNetSalaryOutlay(BigDecimal netSalaryOutlay) {
		this.netSalaryOutlay = netSalaryOutlay;
	}

	public BigDecimal getPlatLeave() {
		return platLeave;
	}

	public void setPlatLeave(BigDecimal platLeave) {
		this.platLeave = platLeave;
	}

	public BigDecimal getPlatSubsidy() {
		return platSubsidy;
	}

	public void setPlatSubsidy(BigDecimal platSubsidy) {
		this.platSubsidy = platSubsidy;
	}

	public Grant getBilled() {
		return billed;
	}

	public void setBilled(Grant billed) {
		this.billed = billed;
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

	public BigDecimal getTaxableSalary() {
		return taxableSalary;
	}

	public BigDecimal getIndividualIncomeTax() {
		return individualIncomeTax;
	}

	public void setTaxableSalary(BigDecimal taxableSalary) {
		this.taxableSalary = taxableSalary;
	}

	public void setIndividualIncomeTax(BigDecimal individualIncomeTax) {
		this.individualIncomeTax = individualIncomeTax;
	}
}
