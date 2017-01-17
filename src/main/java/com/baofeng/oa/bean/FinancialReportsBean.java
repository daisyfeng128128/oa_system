package com.baofeng.oa.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FinancialReportsBean {
	
	private Integer id;
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
	/** 运营支出 */
	private List<FinancialOPExpenditureBean> details = new ArrayList<FinancialOPExpenditureBean>();
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public List<FinancialOPExpenditureBean> getDetails() {
		return details;
	}
	public void setDetails(List<FinancialOPExpenditureBean> details) {
		this.details = details;
	}
	
	
	
	
}
