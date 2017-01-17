package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.oa.entity.BaseEnums.Account;
import com.baofeng.oa.entity.BaseEnums.EmployeType;
import com.baofeng.oa.entity.BaseEnums.Grant;
import com.baofeng.utils.BuildIncomeTax;
import com.baofeng.utils.Constants;

/**
 * 功能：员工工资
 * */
@Entity
@Table(name = "finSalaries")
public class FinSalaries extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -5815379626394737408L;

	/** 员工号 */
	private Integer number;
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
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private Employee employee;
	/** 出勤天数 */
	private BigDecimal attendance = new BigDecimal(0.00);
	/** 工商银行 */
	private BigDecimal icbcBank = new BigDecimal(0.00);
	/** 交通银行 */
	private BigDecimal bocomBank = new BigDecimal(0.00);
	/** 基本底薪 */
	private BigDecimal basicSalary = new BigDecimal(0.00);
	/** 工资补贴 */
	private BigDecimal subsidySalary = new BigDecimal(0.00);
	/** 提成比例 */
	private BigDecimal pushMoney = new BigDecimal(0.00);
	/** 管理提成 */
	private BigDecimal pushMoneySalary = new BigDecimal(0.00);
	/** 主播工资 */
	private BigDecimal pushMoneyActores = new BigDecimal(0.00);
	/** 奖金 */
	private BigDecimal bonus = new BigDecimal(0.00);
	/** 税后总收入 */
	private BigDecimal totalIncome = new BigDecimal(0.00);
	/** 平台收入 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "saOff_id")
	private List<FinPlatformsIncome> details = new ArrayList<FinPlatformsIncome>();

	/** 是否是艺人 */
	private Boolean isActores = new Boolean(false);
	/** 是否交社保 */
	private Boolean isSocial = new Boolean(false);
	/** 是否交公积金 */
	private Boolean isProvident = new Boolean(false);
	/** 员工类型 */
	private EmployeType employeType;

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
	/** 迟到 */
	private BigDecimal beLate = new BigDecimal(0.00);
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
	/** 发放工资状态 */
	private Grant billed = Grant.UNBILLED;
	/** 岗位津贴 */
	private BigDecimal postAllowance = new BigDecimal(0.00);
	/** 其他补贴(公司) */
	private BigDecimal companyOtheSub = new BigDecimal(0.00);
	/** 备注 */
	private String remarks;

	/** 计算 */
	public void make(boolean tax6) {

		if (attendance.floatValue() < Constants.DEFAULT_ATTENDANCE) {
			BigDecimal $atts = new BigDecimal(Constants.DEFAULT_ATTENDANCE - attendance.doubleValue());
			BigDecimal $salaries = basicSalary.add(subsidySalary);
			BigDecimal casualLeave = $salaries.divide(new BigDecimal(Constants.DEFAULT_ATTENDANCE), 4, BigDecimal.ROUND_HALF_UP).multiply($atts);
			setCasualLeave(casualLeave);
		} else {
			setCasualLeave(new BigDecimal(0.00));
		}

		setCp_totalSocial((cp_pension == null ? new BigDecimal(0.00) : cp_pension).add(cp_medicare == null ? new BigDecimal(0.00) : cp_medicare)
				.add(cp_unemployment == null ? new BigDecimal(0.00) : cp_unemployment).add(cp_occupationalInjury == null ? new BigDecimal(0.00) : cp_occupationalInjury)
				.add(cp_fertility == null ? new BigDecimal(0.00) : cp_fertility).add(cp_provident == null ? new BigDecimal(0.00) : cp_provident));

		setPn_totalSocial((pn_pension == null ? new BigDecimal(0.00) : pn_pension).add(pn_medicare == null ? new BigDecimal(0.00) : pn_medicare)
				.add(pn_unemployment == null ? new BigDecimal(0.00) : pn_unemployment).add(pn_provident == null ? new BigDecimal(0.00) : pn_provident));

		if (isSocial) {
			setTaxableSalary(basicSalary.subtract(pn_totalSocial));
		} else {
			setTaxableSalary(new BigDecimal(0.00));
		}
		setIndividualIncomeTax(BuildIncomeTax.makeTax(new BigDecimal(3500), taxableSalary));
		setRealitySalary(basicSalary.add(subsidySalary == null ? new BigDecimal(0.00) : subsidySalary).add(pushMoneySalary == null ? new BigDecimal(0.00) : pushMoneySalary)
				.add(pushMoneyActores == null ? new BigDecimal(0.00) : pushMoneyActores).add(bonus == null ? new BigDecimal(0.00) : bonus)
				.add(applyFee == null ? new BigDecimal(0.00) : applyFee).add(platSubsidy == null ? new BigDecimal(0.00) : platSubsidy)
				.add(otherSubsidy == null ? new BigDecimal(0.00) : otherSubsidy).add(foodSubsidy == null ? new BigDecimal(0.00) : foodSubsidy)
				.add(trafficSubsidy == null ? new BigDecimal(0.00) : trafficSubsidy).add(companyOtheSub == null ? new BigDecimal(0.00) : companyOtheSub)
				.add(postAllowance == null ? new BigDecimal(0.00) : postAllowance).subtract(pn_totalSocial == null ? new BigDecimal(0.00) : pn_totalSocial)
				.subtract(actoresIncomeTax == null ? new BigDecimal(0.00) : actoresIncomeTax).subtract(individualIncomeTax == null ? new BigDecimal(0.00) : individualIncomeTax)
				.subtract(platLeave == null ? new BigDecimal(0.00) : platLeave).subtract(otherDeduct == null ? new BigDecimal(0.00) : otherDeduct)
				.subtract(beLate == null ? new BigDecimal(0.00) : beLate).subtract(casualLeave == null ? new BigDecimal(0.00) : casualLeave)
				.subtract(sickLeave == null ? new BigDecimal(0.00) : sickLeave));

		setRealExpenditure(realitySalary.add(individualIncomeTax == null ? new BigDecimal(0.00) : individualIncomeTax)
				.add(pn_totalSocial == null ? new BigDecimal(0.00) : pn_totalSocial).add(cp_totalSocial == null ? new BigDecimal(0.00) : cp_totalSocial)
				.add(actoresIncomeTax == null ? new BigDecimal(0.00) : actoresIncomeTax));
		if (isActores) {
			setNetProfit(totalIncome.subtract(realExpenditure));
		}
		if (isSocial) {
			if (subsidySalary.doubleValue() == 0 && basicSalary.doubleValue() > 3500) {
				setIcbcBank(realitySalary);
				setBocomBank(new BigDecimal(0.00));
			} else {
				BigDecimal icbcBank = taxableSalary.subtract(individualIncomeTax);
				BigDecimal bocomBank = realitySalary.subtract(icbcBank);
				if (bocomBank.doubleValue() < 0) {
					setIcbcBank(icbcBank.add(bocomBank));
					setBocomBank(new BigDecimal(0.00));
				} else {
					setIcbcBank(icbcBank);
					setBocomBank(bocomBank);
				}
			}
		} else {
			setIcbcBank(new BigDecimal(0.00));
			setBocomBank(realitySalary);
		}
		if (!isActores && tax6) {
			if (bocomBank != null && bocomBank.doubleValue() > 0) {
				BigDecimal tax = bocomBank.multiply(new BigDecimal(6)).divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP);
				setBocomBank(bocomBank.subtract(tax));
				setIndividualIncomeTax(individualIncomeTax.add(tax));
				setRealitySalary(realitySalary.subtract(tax));
			}
		}
	}

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

	public Account towns() {
		return account;
	}

	public BigDecimal getAttendance() {
		return attendance;
	}

	public BigDecimal getBasicSalary() {
		return basicSalary;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public Boolean getIsSocial() {
		return isSocial;
	}

	public Boolean getIsProvident() {
		return isProvident;
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

	public BigDecimal getTaxableSalary() {
		return taxableSalary;
	}

	public BigDecimal getIndividualIncomeTax() {
		return individualIncomeTax;
	}

	public BigDecimal getPlatLeave() {
		return platLeave;
	}

	public BigDecimal getPlatSubsidy() {
		return platSubsidy;
	}

	public BigDecimal getOtherSubsidy() {
		return otherSubsidy;
	}

	public BigDecimal getOtherDeduct() {
		return otherDeduct;
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

	public BigDecimal getApplyFee() {
		return applyFee;
	}

	public BigDecimal getRealExpenditure() {
		return realExpenditure;
	}

	public BigDecimal getRealitySalary() {
		return realitySalary.doubleValue() < new BigDecimal(0.00).doubleValue() ? new BigDecimal(0.00) : realitySalary;
	}

	public Grant getBilled() {
		return billed;
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

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setAttendance(BigDecimal attendance) {
		this.attendance = attendance;
	}

	public void setBasicSalary(BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public void setIsSocial(Boolean isSocial) {
		this.isSocial = isSocial;
	}

	public void setIsProvident(Boolean isProvident) {
		this.isProvident = isProvident;
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

	public void setTaxableSalary(BigDecimal taxableSalary) {
		this.taxableSalary = taxableSalary;
	}

	public void setIndividualIncomeTax(BigDecimal individualIncomeTax) {
		this.individualIncomeTax = individualIncomeTax;
	}

	public void setPlatLeave(BigDecimal platLeave) {
		this.platLeave = platLeave;
	}

	public void setPlatSubsidy(BigDecimal platSubsidy) {
		this.platSubsidy = platSubsidy;
	}

	public void setOtherSubsidy(BigDecimal otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}

	public void setOtherDeduct(BigDecimal otherDeduct) {
		this.otherDeduct = otherDeduct;
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

	public void setApplyFee(BigDecimal applyFee) {
		this.applyFee = applyFee;
	}

	public void setRealExpenditure(BigDecimal realExpenditure) {
		this.realExpenditure = realExpenditure;
	}

	public void setRealitySalary(BigDecimal realitySalary) {
		this.realitySalary = realitySalary;
	}

	public void setBilled(Grant billed) {
		this.billed = billed;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getPushMoney() {
		return pushMoney;
	}

	public void setPushMoney(BigDecimal pushMoney) {
		this.pushMoney = pushMoney;
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

	public BigDecimal getPushMoneySalary() {
		return pushMoneySalary;
	}

	public void setPushMoneySalary(BigDecimal pushMoneySalary) {
		this.pushMoneySalary = pushMoneySalary;
	}

	public BigDecimal getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(BigDecimal netProfit) {
		this.netProfit = netProfit;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public List<FinPlatformsIncome> getDetails() {
		return details;
	}

	public void setDetails(List<FinPlatformsIncome> details) {
		this.details = details;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Boolean getIsActores() {
		return isActores;
	}

	public void setIsActores(Boolean isActores) {
		this.isActores = isActores;
	}

	public BigDecimal getSubsidySalary() {
		return subsidySalary;
	}

	public void setSubsidySalary(BigDecimal subsidySalary) {
		this.subsidySalary = subsidySalary;
	}

	public String getAliasname() {
		return aliasname;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
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

	public BigDecimal getPushMoneyActores() {
		return pushMoneyActores;
	}

	public void setPushMoneyActores(BigDecimal pushMoneyActores) {
		this.pushMoneyActores = pushMoneyActores;
	}

	public BigDecimal getActoresIncomeTax() {
		return actoresIncomeTax;
	}

	public void setActoresIncomeTax(BigDecimal actoresIncomeTax) {
		this.actoresIncomeTax = actoresIncomeTax;
	}

	public EmployeType getEmployeType() {
		return employeType;
	}

	public void setEmployeType(EmployeType employeType) {
		this.employeType = employeType;
	}
}
