package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import com.baofeng.commons.entity.BaseInteger;

/**
 * 功能：线上/线下艺人(平台收入)
 * */
@Entity
@Table(name = "platformIncomeSalary")
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class FinPlatformsIncome extends BaseInteger implements Serializable {
	private static final long serialVersionUID = 2893086615346013458L;

	/** 平台Id */
	private Integer platId;
	/** 平台名称 */
	private String platName;
	/** 平台收入 */
	private BigDecimal income = new BigDecimal(0.00);
	/** 平台扣除 */
	private BigDecimal platDeduct = new BigDecimal(0.00);
	/** 平台补贴 */
	private BigDecimal platSubsidy = new BigDecimal(0.00);
	/** 线上工资 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "saOn_id")
	private FinSalariesOnline finSalariesOnline;
	/** 线下工资 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "saOff_id")
	private FinSalaries finSalaries;
	/** 收益 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "earningsOff_id")
	private FinEarningsIncome finEarningsIncome;

	public FinEarningsIncome getFinEarningsIncome() {
		return finEarningsIncome;
	}

	public void setFinEarningsIncome(FinEarningsIncome finEarningsIncome) {
		this.finEarningsIncome = finEarningsIncome;
	}

	public Integer getPlatId() {
		return platId;
	}

	public void setPlatId(Integer platId) {
		this.platId = platId;
	}

	public String getPlatName() {
		return platName;
	}

	public void setPlatName(String platName) {
		this.platName = platName;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public FinSalariesOnline getFinSalariesOnline() {
		return finSalariesOnline;
	}

	public void setFinSalariesOnline(FinSalariesOnline finSalariesOnline) {
		this.finSalariesOnline = finSalariesOnline;
	}

	public FinSalaries getFinSalaries() {
		return finSalaries;
	}

	public void setFinSalaries(FinSalaries finSalaries) {
		this.finSalaries = finSalaries;
	}

	public BigDecimal getPlatDeduct() {
		return platDeduct;
	}

	public void setPlatDeduct(BigDecimal platDeduct) {
		this.platDeduct = platDeduct;
	}

	public BigDecimal getPlatSubsidy() {
		return platSubsidy;
	}

	public void setPlatSubsidy(BigDecimal platSubsidy) {
		this.platSubsidy = platSubsidy;
	}
}
