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
@Table(name = "salaryPlatIncome")
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class FinPlatfromsIncome extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -4089207073815209194L;
	/** 平台Id */
	private Integer platId;
	/** 平台名称 */
	private String platName;
	/** 平台收入 */
	private BigDecimal income;
	/** 线下工资 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "saOff_id")
	private FinSalaryOffline salaryOffline;
	/** 线上工资 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "saOn_id")
	private FinSalaryOnline salaryOnline;

	public Integer getPlatId() {
		return platId;
	}

	public String getPlatName() {
		return platName;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setPlatId(Integer platId) {
		this.platId = platId;
	}

	public void setPlatName(String platName) {
		this.platName = platName;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public FinSalaryOffline getSalaryOffline() {
		return salaryOffline;
	}

	public void setSalaryOffline(FinSalaryOffline salaryOffline) {
		this.salaryOffline = salaryOffline;
	}

	public FinSalaryOnline getSalaryOnline() {
		return salaryOnline;
	}

	public void setSalaryOnline(FinSalaryOnline salaryOnline) {
		this.salaryOnline = salaryOnline;
	}
}
