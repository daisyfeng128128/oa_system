package com.baofeng.oa.bean;

import java.io.Serializable;

/**
 * 功能：平台流水数据对像
 * */
public class PlatformsMonthsBean implements Serializable {

	private static final long serialVersionUID = -1464645436978411601L;

	private String id;
	/** 平台ID */
	private String platId;
	/** 所在平台 */
	private String platforms;
	/** 平台后台收入 */
	private String bgIncome;
	/** 平台类型 */
	private String genre;
	/** 税点补贴 */
	private String taxSubsidy;
	/** 平台应收款 */
	private String receivable;
	/** 线上主播合计 */
	private String onlineTotal;
	/** 线下主播合计 */
	private String offlineTotal;
	/** 应收合计 */
	private String incomeTotal;
	/** 支出合计 */
	private String outlayTotal;
	/** 其他收入 */
	private String otherIncome;
	/** 平台净利润 */
	private String netProfit;
	/** 频道应收 */
	private String chIncome ;
	/** 频道支出 */
	private String chOutlay ;
	/** 备注 */
	private String remarks;
	/** 营收总表 */
	private String mId;

	
	public String getChIncome() {
		return chIncome;
	}

	public void setChIncome(String chIncome) {
		this.chIncome = chIncome;
	}

	public String getChOutlay() {
		return chOutlay;
	}

	public void setChOutlay(String chOutlay) {
		this.chOutlay = chOutlay;
	}

	public String getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}

	public String getPlatId() {
		return platId;
	}

	public void setPlatId(String platId) {
		this.platId = platId;
	}

	public String getPlatforms() {
		return platforms;
	}

	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	public String getBgIncome() {
		return bgIncome;
	}

	public void setBgIncome(String bgIncome) {
		this.bgIncome = bgIncome;
	}

	public String getTaxSubsidy() {
		return taxSubsidy;
	}

	public void setTaxSubsidy(String taxSubsidy) {
		this.taxSubsidy = taxSubsidy;
	}

	public String getReceivable() {
		return receivable;
	}

	public void setReceivable(String receivable) {
		this.receivable = receivable;
	}

	public String getOnlineTotal() {
		return onlineTotal;
	}

	public void setOnlineTotal(String onlineTotal) {
		this.onlineTotal = onlineTotal;
	}

	public String getOfflineTotal() {
		return offlineTotal;
	}

	public void setOfflineTotal(String offlineTotal) {
		this.offlineTotal = offlineTotal;
	}

	public String getIncomeTotal() {
		return incomeTotal;
	}

	public void setIncomeTotal(String incomeTotal) {
		this.incomeTotal = incomeTotal;
	}

	public String getOutlayTotal() {
		return outlayTotal;
	}

	public void setOutlayTotal(String outlayTotal) {
		this.outlayTotal = outlayTotal;
	}

	public String getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(String netProfit) {
		this.netProfit = netProfit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
