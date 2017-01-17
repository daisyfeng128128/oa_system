package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.oa.entity.BaseEnums.Audit;

/**
 * 功能：平台月流水
 * */
@Entity
@Table(name = "platformsMonths")
public class PlatformsMonths extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -8255924868917674655L;
	
	/** 平台ID */
	private String platId;
	/** 所在平台 */
	private String platforms;
	/** 收入类型 */
	private String genre;
	/** 频道应收 */
	private BigDecimal chIncome = new BigDecimal(0.00);
	/** 频道支出 */
	private BigDecimal chOutlay = new BigDecimal(0.00);
	/** 平台后台收入 */
	private BigDecimal bgIncome = new BigDecimal(0.00);
	/** 税点补贴 */
	private BigDecimal taxSubsidy = new BigDecimal(0.00);
	/** 平台应收款 */
	private BigDecimal receivable = new BigDecimal(0.00);
	/** 线上主播合计 */
	private BigDecimal onlineTotal = new BigDecimal(0.00);
	/** 线下主播合计 */
	private BigDecimal offlineTotal = new BigDecimal(0.00);
	/** 硬件支出 */
	private BigDecimal hardTotal = new BigDecimal(0.00);
	/** 应收合计 */
	private BigDecimal incomeTotal = new BigDecimal(0.00);
	/** 支出合计 */
	private BigDecimal outlayTotal = new BigDecimal(0.00);
	/** 平台净利润 */
	private BigDecimal netProfit = new BigDecimal(0.00);
	/** 其他收入 */
	private BigDecimal otherIncome = new BigDecimal(0.00);
	/** 备注 */
	private String remarks;
	/** 审核状态 */
	private Audit audit = Audit.WRITE;
	/** 营收总表 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "months_id")
	private PlatformsMonthsOutlay months;

	public BigDecimal getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(BigDecimal otherIncome) {
		this.otherIncome = otherIncome;
	}

	public String getPlatId() {
		return platId;
	}

	public String getPlatforms() {
		return platforms;
	}

	public String getGenre() {
		return genre;
	}

	public BigDecimal getBgIncome() {
		return bgIncome;
	}

	public BigDecimal getTaxSubsidy() {
		return taxSubsidy;
	}

	public BigDecimal getReceivable() {
		return receivable;
	}

	public BigDecimal getOnlineTotal() {
		return onlineTotal;
	}

	public BigDecimal getOfflineTotal() {
		return offlineTotal;
	}

	public BigDecimal getIncomeTotal() {
		return incomeTotal;
	}

	public BigDecimal getOutlayTotal() {
		return outlayTotal;
	}

	public BigDecimal getNetProfit() {
		return netProfit;
	}

	public String getRemarks() {
		return remarks;
	}

	public Audit getAudit() {
		return audit;
	}

	public PlatformsMonthsOutlay getMonths() {
		return months;
	}

	public void setPlatId(String platId) {
		this.platId = platId;
	}

	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setBgIncome(BigDecimal bgIncome) {
		this.bgIncome = bgIncome;
	}

	public void setTaxSubsidy(BigDecimal taxSubsidy) {
		this.taxSubsidy = taxSubsidy;
	}

	public void setReceivable(BigDecimal receivable) {
		this.receivable = receivable;
	}

	public void setOnlineTotal(BigDecimal onlineTotal) {
		this.onlineTotal = onlineTotal;
	}

	public void setOfflineTotal(BigDecimal offlineTotal) {
		this.offlineTotal = offlineTotal;
	}

	public void setIncomeTotal(BigDecimal incomeTotal) {
		this.incomeTotal = incomeTotal;
	}

	public void setOutlayTotal(BigDecimal outlayTotal) {
		this.outlayTotal = outlayTotal;
	}

	public void setNetProfit(BigDecimal netProfit) {
		this.netProfit = netProfit;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	public void setMonths(PlatformsMonthsOutlay months) {
		this.months = months;
	}

	public BigDecimal getHardTotal() {
		return hardTotal;
	}

	public void setHardTotal(BigDecimal hardTotal) {
		this.hardTotal = hardTotal;
	}

	public BigDecimal getChIncome() {
		return chIncome;
	}

	public BigDecimal getChOutlay() {
		return chOutlay;
	}

	public void setChIncome(BigDecimal chIncome) {
		this.chIncome = chIncome;
	}

	public void setChOutlay(BigDecimal chOutlay) {
		this.chOutlay = chOutlay;
	}
}
