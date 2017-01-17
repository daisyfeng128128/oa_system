package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;

/**
 * 功能：财务报表
 * */
@Entity
@Table(name = "financialReports")
public class FinancialReports extends BaseInteger implements Serializable {

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
	
	//支出明细////////////////////////////////////////////////////////////////////////
	
	/**维护费支出*/
	private BigDecimal maintenance = new BigDecimal(0.00);
	/**报表运营支出*/
	private BigDecimal roperate = new BigDecimal(0.00);
	/**报表其他支出*/
	private BigDecimal rother = new BigDecimal(0.00);
	/**礼物返回*/
	private BigDecimal giftback = new BigDecimal(0.00);
	/**公司运营支出*/
	private BigDecimal operate = new BigDecimal(0.00);
	/**线上艺人支出*/
	private BigDecimal onlineActs = new BigDecimal(0.00);
	/**线下管理支出*/
	private BigDecimal onlineMans = new BigDecimal(0.00);
	/**线上星探支出*/
	private BigDecimal onlineTalent = new BigDecimal(0.00);
	/**线下员工支出*/
	private BigDecimal offlineEmps = new BigDecimal(0.00);
	/**工资合计*/
	private BigDecimal allTotal = new BigDecimal(0.00);
	/**财务支出*/
	private BigDecimal financing = new BigDecimal(0.00);
	/**财务支出合计*/
	private BigDecimal financingTotal = new BigDecimal(0.00);
	/**公司支出合计*/
	private BigDecimal totalOutlays = new BigDecimal(0.00);
	
	/** 运营支出 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "reports_id")
	private List<FinancialOPExpenditure> details = new ArrayList<FinancialOPExpenditure>();
	// 艺人部盈利统计//////////////////////////////////////////////////////////////
	/** 本月平台总收益 */
	private BigDecimal currentPlatIncome = new BigDecimal(0.00);
	/** 平台总支出 */
	private BigDecimal currentPlatExpenditure = new BigDecimal(0.00);
	/** 本月净收益 */
	private BigDecimal currentEarnings = new BigDecimal(0.00);
	/** 平台同比增长 */
	private BigDecimal act_tgrowthIncome = new BigDecimal(0.00);
	/** 平台环比增长 */
	private BigDecimal act_hgrowthIncome = new BigDecimal(0.00);
	/** 部门艺人总数 */
	private Integer totalActores;
	/** 艺人数净增 */
	private Integer increaseActores;
	/** 艺人数同比增长率 */
	private Float act_tgrowthActores;
	/** 艺人数环比增长率 */
	private Float act_hgrowthActores;
	/** 艺人盈利人数 */
	private Integer act_profitActores;
	/** 艺人盈利总额 */
	private BigDecimal act_totalProfit;
	/** 艺人盈利增长 */
	private BigDecimal act_increaseProfit;
	/** 艺人亏损人数 */
	private Integer act_lossActores;
	/** 艺人亏损总额 */
	private BigDecimal act_totalLoss;
	/** s级艺人数 */
	private Integer act_superActores;
	/** 艺人最高收入 */
	private BigDecimal act_maxIncome;
	/** 总收入最高平台 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "max_id")
	private Platforms maxPlat;
	/** 总收入最低平台 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "min_id")
	private Platforms minPlat;
	
	
	
	public BigDecimal getCurrentIncome() {
		return currentIncome;
	}

	public BigDecimal getCurrentExpenditure() {
		return currentExpenditure;
	}

	public BigDecimal getCurrentProfit() {
		return currentProfit;
	}

	public BigDecimal getLastProfit() {
		return lastProfit;
	}

	public BigDecimal getCp_growthRate() {
		return cp_growthRate;
	}

	public List<FinancialOPExpenditure> getDetails() {
		return details;
	}

	public BigDecimal getCurrentPlatIncome() {
		return currentPlatIncome;
	}

	public BigDecimal getCurrentPlatExpenditure() {
		return currentPlatExpenditure;
	}

	public BigDecimal getCurrentEarnings() {
		return currentEarnings;
	}

	public BigDecimal getAct_tgrowthIncome() {
		return act_tgrowthIncome;
	}

	public BigDecimal getAct_hgrowthIncome() {
		return act_hgrowthIncome;
	}

	public Integer getTotalActores() {
		return totalActores;
	}

	public Integer getIncreaseActores() {
		return increaseActores;
	}

	public Float getAct_tgrowthActores() {
		return act_tgrowthActores;
	}

	public Float getAct_hgrowthActores() {
		return act_hgrowthActores;
	}

	public Integer getAct_profitActores() {
		return act_profitActores;
	}

	public BigDecimal getAct_totalProfit() {
		return act_totalProfit;
	}

	public BigDecimal getAct_increaseProfit() {
		return act_increaseProfit;
	}

	public Integer getAct_lossActores() {
		return act_lossActores;
	}

	public BigDecimal getAct_totalLoss() {
		return act_totalLoss;
	}

	public Integer getAct_superActores() {
		return act_superActores;
	}

	public BigDecimal getAct_maxIncome() {
		return act_maxIncome;
	}

	public Platforms getMaxPlat() {
		return maxPlat;
	}

	public Platforms getMinPlat() {
		return minPlat;
	}

	public void setCurrentIncome(BigDecimal currentIncome) {
		this.currentIncome = currentIncome;
	}

	public void setCurrentExpenditure(BigDecimal currentExpenditure) {
		this.currentExpenditure = currentExpenditure;
	}

	public void setCurrentProfit(BigDecimal currentProfit) {
		this.currentProfit = currentProfit;
	}

	public void setLastProfit(BigDecimal lastProfit) {
		this.lastProfit = lastProfit;
	}

	public void setCp_growthRate(BigDecimal cp_growthRate) {
		this.cp_growthRate = cp_growthRate;
	}

	public void setDetails(List<FinancialOPExpenditure> details) {
		this.details = details;
	}

	public void setCurrentPlatIncome(BigDecimal currentPlatIncome) {
		this.currentPlatIncome = currentPlatIncome;
	}

	public void setCurrentPlatExpenditure(BigDecimal currentPlatExpenditure) {
		this.currentPlatExpenditure = currentPlatExpenditure;
	}

	public void setCurrentEarnings(BigDecimal currentEarnings) {
		this.currentEarnings = currentEarnings;
	}

	public void setAct_tgrowthIncome(BigDecimal act_tgrowthIncome) {
		this.act_tgrowthIncome = act_tgrowthIncome;
	}

	public void setAct_hgrowthIncome(BigDecimal act_hgrowthIncome) {
		this.act_hgrowthIncome = act_hgrowthIncome;
	}

	public void setTotalActores(Integer totalActores) {
		this.totalActores = totalActores;
	}

	public void setIncreaseActores(Integer increaseActores) {
		this.increaseActores = increaseActores;
	}

	public void setAct_tgrowthActores(Float act_tgrowthActores) {
		this.act_tgrowthActores = act_tgrowthActores;
	}

	public void setAct_hgrowthActores(Float act_hgrowthActores) {
		this.act_hgrowthActores = act_hgrowthActores;
	}

	public void setAct_profitActores(Integer act_profitActores) {
		this.act_profitActores = act_profitActores;
	}

	public void setAct_totalProfit(BigDecimal act_totalProfit) {
		this.act_totalProfit = act_totalProfit;
	}

	public void setAct_increaseProfit(BigDecimal act_increaseProfit) {
		this.act_increaseProfit = act_increaseProfit;
	}

	public void setAct_lossActores(Integer act_lossActores) {
		this.act_lossActores = act_lossActores;
	}

	public void setAct_totalLoss(BigDecimal act_totalLoss) {
		this.act_totalLoss = act_totalLoss;
	}

	public void setAct_superActores(Integer act_superActores) {
		this.act_superActores = act_superActores;
	}

	public void setAct_maxIncome(BigDecimal act_maxIncome) {
		this.act_maxIncome = act_maxIncome;
	}

	public void setMaxPlat(Platforms maxPlat) {
		this.maxPlat = maxPlat;
	}

	public void setMinPlat(Platforms minPlat) {
		this.minPlat = minPlat;
	}

	public BigDecimal getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(BigDecimal maintenance) {
		this.maintenance = maintenance;
	}

	public BigDecimal getRoperate() {
		return roperate;
	}

	public void setRoperate(BigDecimal roperate) {
		this.roperate = roperate;
	}

	public BigDecimal getRother() {
		return rother;
	}

	public void setRother(BigDecimal rother) {
		this.rother = rother;
	}

	public BigDecimal getGiftback() {
		return giftback;
	}

	public void setGiftback(BigDecimal giftback) {
		this.giftback = giftback;
	}

	public BigDecimal getOperate() {
		return operate;
	}

	public void setOperate(BigDecimal operate) {
		this.operate = operate;
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
