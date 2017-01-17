package com.baofeng.oa.bean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.baofeng.oa.entity.BaseEnums.Account;
import com.baofeng.oa.entity.Employee;

/**
 * 功能：员工工资
 * */
public class FinSalaryBean {
	private Integer id;

	/** 是否艺人 */
	private Integer isActore = 0;
	/** 员工号 */
	private String number;
	/** 姓名 */
	private String name;
	/** 艺名 */
	private String aliasname;
	/** 部门名称 */
	private String deptName;
	/** 职位 */
	private String positionsName;
	/** 户口类型 */
	private Account account;
	/** 实体数据 */
	private Employee employee;
	/** 出勤天数 */
	private BigDecimal attendance = new BigDecimal(0.00);
	/** 基本工资 */
	private BigDecimal basicSalary = new BigDecimal(0.00);
	/** 补贴工资 */
	private BigDecimal subsidySalary = new BigDecimal(0.00);
	/** 提成比例 */
	private BigDecimal pushMoney = new BigDecimal(0.00);
	/** 提成工资 */
	private BigDecimal pushMoneySalary = new BigDecimal(0.00);
	/** 主播提成 */
	private BigDecimal pushMoneyActores = new BigDecimal(0.00);
	/** 奖金 */
	private BigDecimal bonus = new BigDecimal(0.00);

	/** 工商银行 */
	private BigDecimal icbcBank = new BigDecimal(0.00);
	/** 交通银行 */
	private BigDecimal bocomBank = new BigDecimal(0.00);
	/** 税后总收入 */
	private BigDecimal totalIncome = new BigDecimal(0.00);
	/** 平台收入 */
	private Map<String, String> details = new HashMap<String, String>();
	/** 是否交社保 */
	private Boolean isSocial = new Boolean(false);
	/** 是否交公积金 */
	private Boolean isProvident = new Boolean(false);

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
	/** 艺人所得税 */
	private BigDecimal actoresIncomeTax = new BigDecimal(0.00);
	/** 报销费用 */
	private BigDecimal applyFee = new BigDecimal(0.00);
	/** 平台扣除 */
	private BigDecimal platLeave = new BigDecimal(0.00);
	/** 平台补贴 */
	private BigDecimal platSubsidy = new BigDecimal(0.00);
	/** 其他补贴 */
	private BigDecimal otherSubsidy = new BigDecimal(0.00);
	/** 其他扣除 */
	private BigDecimal otherDeduct = new BigDecimal(0.00);
	/** 餐补 */
	private BigDecimal foodSubsidy = new BigDecimal(0.00);
	/** 交通补贴 */
	private BigDecimal trafficSubsidy = new BigDecimal(0.00);
	/** 加项总计 */
	private BigDecimal add_totalSocial = new BigDecimal(0.00);

	/** 迟到 */
	private BigDecimal beLate = new BigDecimal(0.00);
	/** 岗位津贴 */
	private BigDecimal postAllowance = new BigDecimal(0.00);
	/** 其他补贴(公司) */
	private BigDecimal companyOtheSub = new BigDecimal(0.00);
	/** 事假 */
	private BigDecimal casualLeave = new BigDecimal(0.00);
	/** 病假 */
	private BigDecimal sickLeave = new BigDecimal(0.00);
	/** 实际支出 */
	private BigDecimal realExpenditure = new BigDecimal(0.00);
	/** 实发工资 */
	private BigDecimal realitySalary = new BigDecimal(0.00);
	/** 实际盈亏 */
	private BigDecimal netProfit = new BigDecimal(0.00);
	/** 加项总计 */
	private BigDecimal dec_totalSocial = new BigDecimal(0.00);
	/** 发放工资状态 */
	private String billed;
	/** 备注 */
	private String remarks;
	/** 银行帐号 */
	private String bankCard;
	/** 银行开户地址 */
	private String bankAddress;

	public BigDecimal getPostAllowance() {
		return postAllowance;
	}

	public void setPostAllowance(BigDecimal postAllowance) {
		this.postAllowance = postAllowance;
	}

	public BigDecimal getCompanyOtheSub() {
		return companyOtheSub;
	}

	public void setCompanyOtheSub(BigDecimal companyOtheSub) {
		this.companyOtheSub = companyOtheSub;
	}

	public BigDecimal getSubsidySalary() {
		return subsidySalary;
	}

	public void setSubsidySalary(BigDecimal subsidySalary) {
		this.subsidySalary = subsidySalary;
	}

	public Integer getIsActore() {
		return isActore;
	}

	public String getAliasname() {
		return aliasname;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}

	public void setIsActore(Integer isActore) {
		this.isActore = isActore;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPushMoneyActores() {
		return pushMoneyActores;
	}

	public void setPushMoneyActores(BigDecimal pushMoneyActores) {
		this.pushMoneyActores = pushMoneyActores;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BigDecimal getIcbcBank() {
		return icbcBank;
	}

	public void setIcbcBank(BigDecimal icbcBank) {
		this.icbcBank = icbcBank;
	}

	public BigDecimal getBocomBank() {
		return bocomBank;
	}

	public void setBocomBank(BigDecimal bocomBank) {
		this.bocomBank = bocomBank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPositionsName() {
		return positionsName;
	}

	public void setPositionsName(String positionsName) {
		this.positionsName = positionsName;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getBilled() {
		return billed;
	}

	public void setBilled(String billed) {
		this.billed = billed;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public BigDecimal getAttendance() {
		return attendance;
	}

	public void setAttendance(BigDecimal attendance) {
		this.attendance = attendance;
	}

	public BigDecimal getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
	}

	public BigDecimal getPushMoney() {
		return pushMoney;
	}

	public void setPushMoney(BigDecimal pushMoney) {
		this.pushMoney = pushMoney;
	}

	public BigDecimal getPushMoneySalary() {
		return pushMoneySalary;
	}

	public void setPushMoneySalary(BigDecimal pushMoneySalary) {
		this.pushMoneySalary = pushMoneySalary;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public Boolean getIsSocial() {
		return isSocial;
	}

	public void setIsSocial(Boolean isSocial) {
		this.isSocial = isSocial;
	}

	public Boolean getIsProvident() {
		return isProvident;
	}

	public void setIsProvident(Boolean isProvident) {
		this.isProvident = isProvident;
	}

	public BigDecimal getPn_pension() {
		return pn_pension;
	}

	public void setPn_pension(BigDecimal pn_pension) {
		this.pn_pension = pn_pension;
	}

	public BigDecimal getPn_medicare() {
		return pn_medicare;
	}

	public void setPn_medicare(BigDecimal pn_medicare) {
		this.pn_medicare = pn_medicare;
	}

	public BigDecimal getPn_unemployment() {
		return pn_unemployment;
	}

	public void setPn_unemployment(BigDecimal pn_unemployment) {
		this.pn_unemployment = pn_unemployment;
	}

	public BigDecimal getPn_provident() {
		return pn_provident;
	}

	public void setPn_provident(BigDecimal pn_provident) {
		this.pn_provident = pn_provident;
	}

	public BigDecimal getPn_replenishProvident() {
		return pn_replenishProvident;
	}

	public void setPn_replenishProvident(BigDecimal pn_replenishProvident) {
		this.pn_replenishProvident = pn_replenishProvident;
	}

	public BigDecimal getPn_totalSocial() {
		return pn_totalSocial;
	}

	public void setPn_totalSocial(BigDecimal pn_totalSocial) {
		this.pn_totalSocial = pn_totalSocial;
	}

	public BigDecimal getCp_pension() {
		return cp_pension;
	}

	public void setCp_pension(BigDecimal cp_pension) {
		this.cp_pension = cp_pension;
	}

	public BigDecimal getCp_medicare() {
		return cp_medicare;
	}

	public void setCp_medicare(BigDecimal cp_medicare) {
		this.cp_medicare = cp_medicare;
	}

	public BigDecimal getCp_unemployment() {
		return cp_unemployment;
	}

	public void setCp_unemployment(BigDecimal cp_unemployment) {
		this.cp_unemployment = cp_unemployment;
	}

	public BigDecimal getCp_provident() {
		return cp_provident;
	}

	public void setCp_provident(BigDecimal cp_provident) {
		this.cp_provident = cp_provident;
	}

	public BigDecimal getCp_replenishProvident() {
		return cp_replenishProvident;
	}

	public void setCp_replenishProvident(BigDecimal cp_replenishProvident) {
		this.cp_replenishProvident = cp_replenishProvident;
	}

	public BigDecimal getCp_occupationalInjury() {
		return cp_occupationalInjury;
	}

	public void setCp_occupationalInjury(BigDecimal cp_occupationalInjury) {
		this.cp_occupationalInjury = cp_occupationalInjury;
	}

	public BigDecimal getCp_fertility() {
		return cp_fertility;
	}

	public void setCp_fertility(BigDecimal cp_fertility) {
		this.cp_fertility = cp_fertility;
	}

	public BigDecimal getCp_totalSocial() {
		return cp_totalSocial;
	}

	public void setCp_totalSocial(BigDecimal cp_totalSocial) {
		this.cp_totalSocial = cp_totalSocial;
	}

	public BigDecimal getTaxableSalary() {
		return taxableSalary;
	}

	public void setTaxableSalary(BigDecimal taxableSalary) {
		this.taxableSalary = taxableSalary;
	}

	public BigDecimal getIndividualIncomeTax() {
		return individualIncomeTax;
	}

	public void setIndividualIncomeTax(BigDecimal individualIncomeTax) {
		this.individualIncomeTax = individualIncomeTax;
	}

	public BigDecimal getApplyFee() {
		return applyFee;
	}

	public void setApplyFee(BigDecimal applyFee) {
		this.applyFee = applyFee;
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

	public BigDecimal getOtherSubsidy() {
		return otherSubsidy;
	}

	public void setOtherSubsidy(BigDecimal otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}

	public BigDecimal getOtherDeduct() {
		return otherDeduct;
	}

	public void setOtherDeduct(BigDecimal otherDeduct) {
		this.otherDeduct = otherDeduct;
	}

	public BigDecimal getFoodSubsidy() {
		return foodSubsidy;
	}

	public void setFoodSubsidy(BigDecimal foodSubsidy) {
		this.foodSubsidy = foodSubsidy;
	}

	public BigDecimal getTrafficSubsidy() {
		return trafficSubsidy;
	}

	public void setTrafficSubsidy(BigDecimal trafficSubsidy) {
		this.trafficSubsidy = trafficSubsidy;
	}

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

	public BigDecimal getRealExpenditure() {
		return realExpenditure;
	}

	public void setRealExpenditure(BigDecimal realExpenditure) {
		this.realExpenditure = realExpenditure;
	}

	public BigDecimal getRealitySalary() {
		return realitySalary;
	}

	public void setRealitySalary(BigDecimal realitySalary) {
		this.realitySalary = realitySalary;
	}

	public BigDecimal getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(BigDecimal netProfit) {
		this.netProfit = netProfit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public Map<String, String> getDetails() {
		return details;
	}

	public void setDetails(Map<String, String> details) {
		this.details = details;
	}

	public BigDecimal getAdd_totalSocial() {
		return add_totalSocial;
	}

	public void setAdd_totalSocial(BigDecimal add_totalSocial) {
		this.add_totalSocial = add_totalSocial;
	}

	public BigDecimal getDec_totalSocial() {
		return dec_totalSocial;
	}

	public void setDec_totalSocial(BigDecimal dec_totalSocial) {
		this.dec_totalSocial = dec_totalSocial;
	}

	public BigDecimal getActoresIncomeTax() {
		return actoresIncomeTax == null ? new BigDecimal(0.00) : actoresIncomeTax;
	}

	public void setActoresIncomeTax(BigDecimal actoresIncomeTax) {
		this.actoresIncomeTax = actoresIncomeTax;
	}
}
