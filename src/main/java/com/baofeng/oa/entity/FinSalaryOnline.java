package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.oa.entity.BaseEnums.Grant;

/**
 * 功能：员工工资(线上艺人)
 * */
@Entity
@Table(name = "salaryOnline")
public class FinSalaryOnline extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 3644236103135544649L;

	/** 姓名 */
	private String name;
	/** 艺名 */
	private String aliasname;
	/** 平台收入 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "saOn_id")
	private List<FinPlatfromsIncome> income = new ArrayList<FinPlatfromsIncome>();
	/** 税后总收入 */
	private BigDecimal totalIncome = new BigDecimal(0.00);
	/** 基本保底 */
	private BigDecimal basicSalary = new BigDecimal(0.00);
	/** 提成比例 */
	private BigDecimal pushMoney = new BigDecimal(0.00);
	/** 提成奖金 */
	private BigDecimal pushMoneySalary = new BigDecimal(0.00);

	/** 平台补贴 */
	private BigDecimal platSubsidy = new BigDecimal(0.00);
	/** 平台扣除 */
	private BigDecimal platDeduct = new BigDecimal(0.00);

	/** 其他补贴 */
	private BigDecimal otherSubsidy = new BigDecimal(0.00);
	/** 其他扣除 */
	private BigDecimal otherDeduct = new BigDecimal(0.00);

	/** 分成扣税 */
	private BigDecimal deductTax = new BigDecimal(0.00);
	/** 实发工资 */
	private BigDecimal realitySalary = new BigDecimal(0.00);
	/** 实际盈亏 */
	private BigDecimal netProfit = new BigDecimal(0.00);
	/** 艺人Id */
	private Integer actoresId;
	/** 出勤天数 */
	private BigDecimal attendance = new BigDecimal(0.0);
	/** 备注 */
	private String remarks;
	/** 发放工资状态 */
	private Grant billed  = Grant.UNBILLED;

	public Grant getBilled() {
		return billed;
	}

	public void setBilled(Grant billed) {
		this.billed = billed;
	}

	public String getName() {
		return name;
	}

	public String getAliasname() {
		return aliasname;
	}

	public BigDecimal getAttendance() {
		return attendance;
	}

	public void setAttendance(BigDecimal attendance) {
		this.attendance = attendance;
	}

	public List<FinPlatfromsIncome> getIncome() {
		return income;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public BigDecimal getBasicSalary() {
		return basicSalary;
	}

	public BigDecimal getPushMoney() {
		return pushMoney;
	}

	public BigDecimal getPushMoneySalary() {
		return pushMoneySalary;
	}

	public BigDecimal getOtherSubsidy() {
		return otherSubsidy;
	}

	public BigDecimal getOtherDeduct() {
		return otherDeduct;
	}

	public BigDecimal getRealitySalary() {
		return realitySalary;
	}

	public BigDecimal getNetProfit() {
		return netProfit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}

	public void setIncome(List<FinPlatfromsIncome> income) {
		this.income = income;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public void setBasicSalary(BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
	}

	public void setPushMoney(BigDecimal pushMoney) {
		this.pushMoney = pushMoney;
	}

	public void setPushMoneySalary(BigDecimal pushMoneySalary) {
		this.pushMoneySalary = pushMoneySalary;
	}

	public void setOtherSubsidy(BigDecimal otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}

	public void setOtherDeduct(BigDecimal otherDeduct) {
		this.otherDeduct = otherDeduct;
	}

	public void setRealitySalary(BigDecimal realitySalary) {
		this.realitySalary = realitySalary;
	}

	public void setNetProfit(BigDecimal netProfit) {
		this.netProfit = netProfit;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getDeductTax() {
		return deductTax;
	}

	public Integer getActoresId() {
		return actoresId;
	}

	public void setDeductTax(BigDecimal deductTax) {
		this.deductTax = deductTax;
	}

	public void setActoresId(Integer actoresId) {
		this.actoresId = actoresId;
	}

	public BigDecimal getPlatSubsidy() {
		return platSubsidy;
	}

	public BigDecimal getPlatDeduct() {
		return platDeduct;
	}

	public void setPlatSubsidy(BigDecimal platSubsidy) {
		this.platSubsidy = platSubsidy;
	}

	public void setPlatDeduct(BigDecimal platDeduct) {
		this.platDeduct = platDeduct;
	}

}
