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
 * 功能：员工工资(线上)
 * */
@Entity
@Table(name = "finSalariesOnline")
public class FinSalariesOnline extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -6615950941899966981L;

	/** 姓名 */
	private String name;
	/** 艺名 */
	private String aliasname;
	/** 实体数据Id */
	private Integer entityId;
	/** 实体数据 */
	private String entityClass;
	/** 税后总收入 */
	private BigDecimal totalIncome = new BigDecimal(0.00);
	/** 平台收入 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "saOn_id")
	private List<FinPlatformsIncome> details = new ArrayList<FinPlatformsIncome>();
	/** 出勤天数 */
	private BigDecimal attendance = new BigDecimal(0.00);
	/** 基本底薪 */
	private BigDecimal basicSalary = new BigDecimal(0.00);
	/** 提成比例 */
	private BigDecimal pushMoney = new BigDecimal(0.00);
	/** 提成工资 */
	private BigDecimal pushMoneySalary = new BigDecimal(0.00);
	/** 平台补贴 */
	private BigDecimal platSubsidy = new BigDecimal(0.00);
	/** 平台扣除 */
	private BigDecimal platDeduct = new BigDecimal(0.00);
	/** 其他补贴 */
	private BigDecimal otherSubsidy = new BigDecimal(0.00);
	/** 餐补 */
	private BigDecimal foodSubsidy = new BigDecimal(0.00);
	/** 其他扣除 */
	private BigDecimal otherDeduct = new BigDecimal(0.00);
	/** 分成扣税 */
	private BigDecimal deductTax = new BigDecimal(0.00);
	/** 计税工资 */
	private BigDecimal taxableSalary = new BigDecimal(0.00);
	/** 个人所得税 */
	private BigDecimal individualIncomeTax = new BigDecimal(0.00);
	/** 艺人所得税 */
	private BigDecimal actoresIncomeTax = new BigDecimal(0.00);
	/** 实发工资 */
	private BigDecimal realitySalary = new BigDecimal(0.00);
	/** 公司支出 */
	private BigDecimal realExpenditure = new BigDecimal(0.00);
	/** 实际盈亏 */
	private BigDecimal netProfit = new BigDecimal(0.00);
	/** 发放工资状态 */
	private Grant billed = Grant.UNBILLED;
	/** 备注 */
	private String remarks;

	public String getName() {
		return name;
	}

	public String getAliasname() {
		return aliasname;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public BigDecimal getAttendance() {
		return attendance;
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

	public BigDecimal getPlatSubsidy() {
		return platSubsidy;
	}

	public BigDecimal getPlatDeduct() {
		return platDeduct;
	}

	public BigDecimal getOtherSubsidy() {
		return otherSubsidy;
	}

	public BigDecimal getOtherDeduct() {
		return otherDeduct;
	}

	public BigDecimal getDeductTax() {
		return deductTax;
	}

	public BigDecimal getTaxableSalary() {
		return taxableSalary;
	}

	public BigDecimal getIndividualIncomeTax() {
		return individualIncomeTax;
	}

	public BigDecimal getRealitySalary() {
		return realitySalary;
	}

	public BigDecimal getNetProfit() {
		return netProfit;
	}

	public Grant getBilled() {
		return billed;
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

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public void setAttendance(BigDecimal attendance) {
		this.attendance = attendance;
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

	public void setPlatSubsidy(BigDecimal platSubsidy) {
		this.platSubsidy = platSubsidy;
	}

	public void setPlatDeduct(BigDecimal platDeduct) {
		this.platDeduct = platDeduct;
	}

	public void setOtherSubsidy(BigDecimal otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}

	public void setOtherDeduct(BigDecimal otherDeduct) {
		this.otherDeduct = otherDeduct;
	}

	public void setDeductTax(BigDecimal deductTax) {
		this.deductTax = deductTax;
	}

	public void setTaxableSalary(BigDecimal taxableSalary) {
		this.taxableSalary = taxableSalary;
	}

	public void setIndividualIncomeTax(BigDecimal individualIncomeTax) {
		this.individualIncomeTax = individualIncomeTax;
	}

	public void setRealitySalary(BigDecimal realitySalary) {
		this.realitySalary = realitySalary;
	}

	public void setNetProfit(BigDecimal netProfit) {
		this.netProfit = netProfit;
	}

	public void setBilled(Grant billed) {
		this.billed = billed;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<FinPlatformsIncome> getDetails() {
		return details;
	}

	public void setDetails(List<FinPlatformsIncome> details) {
		this.details = details;
	}

	public Integer getEntityId() {
		return entityId;
	}

	public String getEntityClass() {
		return entityClass;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	public BigDecimal getFoodSubsidy() {
		return foodSubsidy;
	}

	public void setFoodSubsidy(BigDecimal foodSubsidy) {
		this.foodSubsidy = foodSubsidy;
	}

	public BigDecimal getRealExpenditure() {
		return realExpenditure;
	}

	public void setRealExpenditure(BigDecimal realExpenditure) {
		this.realExpenditure = realExpenditure;
	}

	public BigDecimal getActoresIncomeTax() {
		return actoresIncomeTax;
	}

	public void setActoresIncomeTax(BigDecimal actoresIncomeTax) {
		this.actoresIncomeTax = actoresIncomeTax;
	}
}
