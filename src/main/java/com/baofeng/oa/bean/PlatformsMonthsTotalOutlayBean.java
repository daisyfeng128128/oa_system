package com.baofeng.oa.bean;

import java.io.Serializable;

/**
 * 功能：月报总表数据
 * */
@SuppressWarnings("serial")
public class PlatformsMonthsTotalOutlayBean implements Serializable {

	private String id;
	/** 上月后台收入 */
	private String lastMonths = String.valueOf("0.00");
	/** 当月后台收入 */
	private String currentMonths = String.valueOf("0.00");
	/** 环比增长率 */
	private String growthRate = String.valueOf("0.00%");
	
	/** 上月线上收入 */
	private String lastOnlineIncome = String.valueOf("0.00");
	/** 当月线上收入 */
	private String onlineIncome = String.valueOf("0.00");
	/** 线上环比增长率 */
	private String onlineRate = String.valueOf("0.00%");

	/** 上月线下收入 */
	private String lastOfflineIncome = String.valueOf("0.00");
	/** 当月线下收入 */
	private String offlineIncome = String.valueOf("0.00");
	/** 线下比增长率 */
	private String offlineRate = String.valueOf("0.00%");

	/** 线上支出 */
	private String onlineOutlay = String.valueOf("0.00");
	/** 线下支出 */
	private String offlineOutlay = String.valueOf("0.00");
	/** 线上艺人工资支出 */
	private String actOnlineOutlay = String.valueOf("0.00");
	/** 线下艺人工资支出 */
	private String actOfflineOutlay = String.valueOf("0.00");
	/** 维护费 */
	private String maintain = String.valueOf("0.00");
	/** 转会费 */
	private String transferIncome = String.valueOf("0.00");
	/** 活动支出 */
	private String activity = String.valueOf("0.00");
	/** 劳务费支出 */
	private String laborService = String.valueOf("0.00");
	/** 分红 */
	private String divident = String.valueOf("0.00");
	/** 平台合计支出 */
	private String outlay = String.valueOf("0.00");
	/** 平台净利润 */
	private String netIncome = String.valueOf("0.00");
	/** 平台总收入 */
	private String totalIncome = String.valueOf("0.00");
	/** 备注 */
	private String remarks;
	/** 平台ID */
	private String platId;
	/** 日期 */
	private String date;
	/** 自充金额 */
	private String selftop = String.valueOf("0.00");
	/** 公会返比 */
	private String society = String.valueOf("0.00");
	/** 主播返比 */
	private String anchors = String.valueOf("0.00");

	public String getId() {
		return id;
	}

	public String getLastMonths() {
		return lastMonths;
	}

	public String getCurrentMonths() {
		return currentMonths;
	}

	public String getGrowthRate() {
		return growthRate;
	}

	public String getOnlineOutlay() {
		return onlineOutlay;
	}

	public String getOfflineOutlay() {
		return offlineOutlay;
	}

	public String getActOnlineOutlay() {
		return actOnlineOutlay;
	}

	public String getActOfflineOutlay() {
		return actOfflineOutlay;
	}

	public String getMaintain() {
		return maintain;
	}

	public String getActivity() {
		return activity;
	}

	public String getDivident() {
		return divident;
	}

	public String getOutlay() {
		return outlay;
	}

	public String getNetIncome() {
		return netIncome;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getPlatId() {
		return platId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLastMonths(String lastMonths) {
		this.lastMonths = lastMonths;
	}

	public void setCurrentMonths(String currentMonths) {
		this.currentMonths = currentMonths;
	}

	public void setGrowthRate(String growthRate) {
		this.growthRate = growthRate;
	}

	public void setOnlineOutlay(String onlineOutlay) {
		this.onlineOutlay = onlineOutlay;
	}

	public void setOfflineOutlay(String offlineOutlay) {
		this.offlineOutlay = offlineOutlay;
	}

	public void setActOnlineOutlay(String actOnlineOutlay) {
		this.actOnlineOutlay = actOnlineOutlay;
	}

	public void setActOfflineOutlay(String actOfflineOutlay) {
		this.actOfflineOutlay = actOfflineOutlay;
	}

	public void setMaintain(String maintain) {
		this.maintain = maintain;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public void setDivident(String divident) {
		this.divident = divident;
	}

	public void setOutlay(String outlay) {
		this.outlay = outlay;
	}

	public void setNetIncome(String netIncome) {
		this.netIncome = netIncome;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setPlatId(String platId) {
		this.platId = platId;
	}

	public String getTransferIncome() {
		return transferIncome;
	}

	public void setTransferIncome(String transferIncome) {
		this.transferIncome = transferIncome;
	}

	public String getLastOnlineIncome() {
		return lastOnlineIncome;
	}

	public String getOnlineIncome() {
		return onlineIncome;
	}

	public String getOnlineRate() {
		return onlineRate;
	}

	public String getLastOfflineIncome() {
		return lastOfflineIncome;
	}

	public String getOfflineIncome() {
		return offlineIncome;
	}

	public String getOfflineRate() {
		return offlineRate;
	}

	public void setLastOnlineIncome(String lastOnlineIncome) {
		this.lastOnlineIncome = lastOnlineIncome;
	}

	public void setOnlineIncome(String onlineIncome) {
		this.onlineIncome = onlineIncome;
	}

	public void setOnlineRate(String onlineRate) {
		this.onlineRate = onlineRate;
	}

	public void setLastOfflineIncome(String lastOfflineIncome) {
		this.lastOfflineIncome = lastOfflineIncome;
	}

	public void setOfflineIncome(String offlineIncome) {
		this.offlineIncome = offlineIncome;
	}

	public void setOfflineRate(String offlineRate) {
		this.offlineRate = offlineRate;
	}

	public String getLaborService() {
		return laborService;
	}

	public void setLaborService(String laborService) {
		this.laborService = laborService;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}

	public String getSelftop() {
		return selftop;
	}

	public void setSelftop(String selftop) {
		this.selftop = selftop;
	}

	public String getSociety() {
		return society;
	}

	public void setSociety(String society) {
		this.society = society;
	}

	public String getAnchors() {
		return anchors;
	}

	public void setAnchors(String anchors) {
		this.anchors = anchors;
	}
}
