package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;

/**
 * 功能：新财务报表
 * */
@Entity
@Table(name = "financialNReports")
public class FinancialNReports extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 7578906932236343520L;

	/** 本月收款总额 */
	private BigDecimal currentIncome = new BigDecimal(0.00);
	/** 本月支出总计 */
	private BigDecimal currentExpenditure = new BigDecimal(0.00);
	/** 本月净利润 */
	private BigDecimal currentProfit = new BigDecimal(0.00);
	/** 上月净利润 */
	private BigDecimal lastProfit = new BigDecimal(0.00);
	/** 公司收入环比增长 */
	private BigDecimal cp_growthRate = new BigDecimal(0.00);
	/** 线上艺人工资支出 */
	private BigDecimal onlineActs = new BigDecimal(0.00);
	/** 线上管理工资支出 */
	private BigDecimal onlineMans = new BigDecimal(0.00);
	/** 线上星探工资支出 */
	private BigDecimal onlineTalent = new BigDecimal(0.00);
	/** 线下员工工资支出 */
	private BigDecimal offlineEmps = new BigDecimal(0.00);
	/** 工资合计 */
	private BigDecimal allTotal = new BigDecimal(0.00);
	/** 财务支出 */
	private BigDecimal financing = new BigDecimal(0.00);
	/** 财务支出合计 */
	private BigDecimal financingTotal = new BigDecimal(0.00);
	/** 公司支出合计 */
	private BigDecimal totalOutlays = new BigDecimal(0.00);

	public BigDecimal getCurrentIncome() {
		return currentIncome;
	}

	public void setCurrentIncome(BigDecimal currentIncome) {
		this.currentIncome = currentIncome;
	}

	public BigDecimal getCurrentExpenditure() {
		return currentExpenditure;
	}

	public void setCurrentExpenditure(BigDecimal currentExpenditure) {
		this.currentExpenditure = currentExpenditure;
	}

	public BigDecimal getCurrentProfit() {
		return currentProfit;
	}

	public void setCurrentProfit(BigDecimal currentProfit) {
		this.currentProfit = currentProfit;
	}

	public BigDecimal getLastProfit() {
		return lastProfit;
	}

	public void setLastProfit(BigDecimal lastProfit) {
		this.lastProfit = lastProfit;
	}

	public BigDecimal getCp_growthRate() {
		return cp_growthRate;
	}

	public void setCp_growthRate(BigDecimal cp_growthRate) {
		this.cp_growthRate = cp_growthRate;
	}

	public BigDecimal getOnlineActs() {
		return onlineActs;
	}

	public void setOnlineActs(BigDecimal onlineActs) {
		this.onlineActs = onlineActs;
	}

	public BigDecimal getOnlineMans() {
		return onlineMans;
	}

	public void setOnlineMans(BigDecimal onlineMans) {
		this.onlineMans = onlineMans;
	}

	public BigDecimal getOnlineTalent() {
		return onlineTalent;
	}

	public void setOnlineTalent(BigDecimal onlineTalent) {
		this.onlineTalent = onlineTalent;
	}

	public BigDecimal getOfflineEmps() {
		return offlineEmps;
	}

	public void setOfflineEmps(BigDecimal offlineEmps) {
		this.offlineEmps = offlineEmps;
	}

	public BigDecimal getAllTotal() {
		return allTotal;
	}

	public void setAllTotal(BigDecimal allTotal) {
		this.allTotal = allTotal;
	}

	public BigDecimal getFinancing() {
		return financing;
	}

	public void setFinancing(BigDecimal financing) {
		this.financing = financing;
	}

	public BigDecimal getFinancingTotal() {
		return financingTotal;
	}

	public void setFinancingTotal(BigDecimal financingTotal) {
		this.financingTotal = financingTotal;
	}

	public BigDecimal getTotalOutlays() {
		return totalOutlays;
	}

	public void setTotalOutlays(BigDecimal totalOutlays) {
		this.totalOutlays = totalOutlays;
	}
}
