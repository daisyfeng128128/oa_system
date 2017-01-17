package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;

/**
 * 功能：公司运营支出
 * */
@Entity
@Table(name = "financialOPEX")
public class FinancialOPExpenditure extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 5620388828806731905L;

	/** 项目名称 */
	private String name;
	/** 支出金额 */
	private BigDecimal expenditure = new BigDecimal(0.00);
	/** 备注 */
	private String remarks;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reports_id")
	private FinancialReports reports;

	public String getName() {
		return name;
	}

	public BigDecimal getExpenditure() {
		return expenditure;
	}

	public String getRemarks() {
		return remarks;
	}

	public FinancialReports getReports() {
		return reports;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setExpenditure(BigDecimal expenditure) {
		this.expenditure = expenditure;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setReports(FinancialReports reports) {
		this.reports = reports;
	}
}
