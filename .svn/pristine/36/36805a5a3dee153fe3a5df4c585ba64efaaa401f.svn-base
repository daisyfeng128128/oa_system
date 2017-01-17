package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.oa.entity.BaseEnums.Audit;

/**
 * 功能：平台营收总表
 * */
@Entity
@Table(name = "platformsMonthsOutlay")
public class PlatformsMonthsOutlay extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -6260461772306791836L;
	/** 上月后台收入 */
	private BigDecimal lastMonths = new BigDecimal(0.00);
	/** 当月后台收入 */
	private BigDecimal currentMonths = new BigDecimal(0.00);
	/** 环比增长率 */
	private BigDecimal growthRate = new BigDecimal(0.00);
	/** 上月线上收入 */
	private BigDecimal lastOnlineIncome = new BigDecimal(0.00);
	/** 当月线上收入 */
	private BigDecimal onlineIncome = new BigDecimal(0.00);
	/** 线上环比增长率 */
	private BigDecimal onlineRate = new BigDecimal(0.00);
	/** 上月线下收入 */
	private BigDecimal lastOfflineIncome = new BigDecimal(0.00);
	/** 当月线下收入 */
	private BigDecimal offlineIncome = new BigDecimal(0.00);
	/** 线下比增长率 */
	private BigDecimal offlineRate = new BigDecimal(0.00);
	/** 线上管理工资支出 */
	private BigDecimal onlineOutlay = new BigDecimal(0.00);
	/** 线下管理工资支出 */
	private BigDecimal offlineOutlay = new BigDecimal(0.00);
	/** 线上艺人工资支出 */
	private BigDecimal actOnlineOutlay = new BigDecimal(0.00);
	/** 线下艺人工资支出 */
	private BigDecimal actOfflineOutlay = new BigDecimal(0.00);
	/** 维护费用 */
	private BigDecimal maintain = new BigDecimal(0.00);
	/** 其他收入 */
	private BigDecimal transferIncome = new BigDecimal(0.00);
	/** 活动支出 */
	private BigDecimal activity = new BigDecimal(0.00);
	/** 其他支出 */
	private BigDecimal divident = new BigDecimal(0.00);
	/** 平台总收入 */
	private BigDecimal totalIncome = new BigDecimal(0.00);
	/** 平台合计支出 */
	private BigDecimal outlay = new BigDecimal(0.00);
	/** 平台净利润 */
	private BigDecimal netIncome = new BigDecimal(0.00);
	/** 劳务服务费 */
	private BigDecimal laborService = new BigDecimal(0.00);
	/** 备注 */
	@Lob
	private String remarks;
	/** 平台ID */
	private String platId;
	/** 平台名称 */
	private String platforms;
	/** 审核状态 */
	private Audit audit = Audit.WRITE;
	/** 自充金额 */
	private BigDecimal selftop = new BigDecimal(0.00);
	/** 公会返比 */
	private BigDecimal society = new BigDecimal(0.00);
	/** 主播返比 */
	private BigDecimal anchors = new BigDecimal(0.00);

	public void make() {
		setOutlay((onlineOutlay == null ? new BigDecimal(0.00) : onlineOutlay).add(offlineOutlay == null ? new BigDecimal(0.00) : offlineOutlay)
				.add(actOnlineOutlay == null ? new BigDecimal(0.00) : actOnlineOutlay).add(actOfflineOutlay == null ? new BigDecimal(0.00) : actOfflineOutlay)
				.add(maintain == null ? new BigDecimal(0.00) : maintain).add(activity == null ? new BigDecimal(0.00) : activity)
				.add(divident == null ? new BigDecimal(0.00) : divident).add(laborService == null ? new BigDecimal(0.00) : laborService));

		setTotalIncome((currentMonths == null ? new BigDecimal(0.00) : currentMonths).add(onlineIncome == null ? new BigDecimal(0.00) : onlineIncome)
				.add(offlineIncome == null ? new BigDecimal(0.00) : offlineIncome).add(transferIncome == null ? new BigDecimal(0.00) : transferIncome));

		setNetIncome(totalIncome.subtract(outlay));
	}

	public BigDecimal getLastMonths() {
		return lastMonths;
	}

	public BigDecimal getCurrentMonths() {
		return currentMonths;
	}

	public BigDecimal getGrowthRate() {
		return growthRate;
	}

	public BigDecimal getOnlineOutlay() {
		return onlineOutlay;
	}

	public BigDecimal getOfflineOutlay() {
		return offlineOutlay;
	}

	public BigDecimal getActOnlineOutlay() {
		return actOnlineOutlay;
	}

	public BigDecimal getActOfflineOutlay() {
		return actOfflineOutlay;
	}

	public BigDecimal getMaintain() {
		return maintain;
	}

	public BigDecimal getActivity() {
		return activity;
	}

	public BigDecimal getDivident() {
		return divident;
	}

	public BigDecimal getOutlay() {
		return outlay;
	}

	public BigDecimal getNetIncome() {
		return netIncome;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getPlatId() {
		return platId;
	}

	public String getPlatforms() {
		return platforms;
	}

	public Audit getAudit() {
		return audit;
	}

	public void setLastMonths(BigDecimal lastMonths) {
		this.lastMonths = lastMonths;
	}

	public void setCurrentMonths(BigDecimal currentMonths) {
		this.currentMonths = currentMonths;
	}

	public void setGrowthRate(BigDecimal growthRate) {
		this.growthRate = growthRate;
	}

	public void setOnlineOutlay(BigDecimal onlineOutlay) {
		this.onlineOutlay = onlineOutlay;
	}

	public void setOfflineOutlay(BigDecimal offlineOutlay) {
		this.offlineOutlay = offlineOutlay;
	}

	public void setActOnlineOutlay(BigDecimal actOnlineOutlay) {
		this.actOnlineOutlay = actOnlineOutlay;
	}

	public void setActOfflineOutlay(BigDecimal actOfflineOutlay) {
		this.actOfflineOutlay = actOfflineOutlay;
	}

	public void setMaintain(BigDecimal maintain) {
		this.maintain = maintain;
	}

	public void setActivity(BigDecimal activity) {
		this.activity = activity;
	}

	public void setDivident(BigDecimal divident) {
		this.divident = divident;
	}

	public void setOutlay(BigDecimal outlay) {
		this.outlay = outlay;
	}

	public void setNetIncome(BigDecimal netIncome) {
		this.netIncome = netIncome;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setPlatId(String platId) {
		this.platId = platId;
	}

	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	public BigDecimal getTransferIncome() {
		return transferIncome;
	}

	public void setTransferIncome(BigDecimal transferIncome) {
		this.transferIncome = transferIncome;
	}

	public BigDecimal getLastOnlineIncome() {
		return lastOnlineIncome;
	}

	public BigDecimal getOnlineIncome() {
		return onlineIncome;
	}

	public BigDecimal getOnlineRate() {
		return onlineRate;
	}

	public BigDecimal getLastOfflineIncome() {
		return lastOfflineIncome;
	}

	public BigDecimal getOfflineIncome() {
		return offlineIncome;
	}

	public BigDecimal getOfflineRate() {
		return offlineRate;
	}

	public void setLastOnlineIncome(BigDecimal lastOnlineIncome) {
		this.lastOnlineIncome = lastOnlineIncome;
	}

	public void setOnlineIncome(BigDecimal onlineIncome) {
		this.onlineIncome = onlineIncome;
	}

	public void setOnlineRate(BigDecimal onlineRate) {
		this.onlineRate = onlineRate;
	}

	public void setLastOfflineIncome(BigDecimal lastOfflineIncome) {
		this.lastOfflineIncome = lastOfflineIncome;
	}

	public void setOfflineIncome(BigDecimal offlineIncome) {
		this.offlineIncome = offlineIncome;
	}

	public void setOfflineRate(BigDecimal offlineRate) {
		this.offlineRate = offlineRate;
	}

	public BigDecimal getLaborService() {
		return laborService;
	}

	public void setLaborService(BigDecimal laborService) {
		this.laborService = laborService;
	}

	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public BigDecimal getSelftop() {
		return selftop;
	}

	public void setSelftop(BigDecimal selftop) {
		this.selftop = selftop;
	}

	public BigDecimal getSociety() {
		return society;
	}

	public void setSociety(BigDecimal society) {
		this.society = society;
	}

	public BigDecimal getAnchors() {
		return anchors;
	}

	public void setAnchors(BigDecimal anchors) {
		this.anchors = anchors;
	}
}
