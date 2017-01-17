package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;

/**
 * 功能：员工工资(场控人员)
 * */
@Entity
@Table(name = "salaryThirdManager")
public class FinSalaryThirdManager extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -1110588130550778099L;
	/** 场控ID */
	private Integer thirdManagerId;
	/** 姓名 */
	private String name;
	/** 马甲 */
	private String majia;
	/** 应付工资 */
	private BigDecimal basicSalary = new BigDecimal(0.00);
	/** 其他补贴 */
	private BigDecimal otherSubsidy = new BigDecimal(0.00);
	/** 其他扣除 */
	private BigDecimal otherDeduct = new BigDecimal(0.00);
	/** 实发工资 */
	private BigDecimal realitySalary = new BigDecimal(0.00);
	/** 备注 */
	private String remarks;

	public String getName() {
		return name;
	}

	public String getMajia() {
		return majia;
	}

	public Integer getThirdManagerId() {
		return thirdManagerId;
	}

	public void setThirdManagerId(Integer thirdManagerId) {
		this.thirdManagerId = thirdManagerId;
	}

	public BigDecimal getBasicSalary() {
		return basicSalary;
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

	public String getRemarks() {
		return remarks;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMajia(String majia) {
		this.majia = majia;
	}

	public void setBasicSalary(BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
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

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
