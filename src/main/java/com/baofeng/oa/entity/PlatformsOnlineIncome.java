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
 * 功能：平台线上艺人收入表
 * */
@Entity
@Table(name = "platformsOnlineIncome")
public class PlatformsOnlineIncome extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 1916407275473168189L;

	/** 艺名 */
	private String aliasname;
	/** 姓名 */
	private String name;
	/** 出勤天数 */
	private BigDecimal attendance = new BigDecimal(0.00);
	/** 保底工资 */
	private BigDecimal minimumGuarantee = new BigDecimal(0.00);
	/** 实际工资 */
	private BigDecimal basicSalaires = new BigDecimal(0.00);
	/** 后台收入 */
	private BigDecimal bgIncome = new BigDecimal(0.00);
	/** 劳务服务费 */
	private BigDecimal laborService = new BigDecimal(0.00);
	/** 分成扣税 */
	private BigDecimal deductTax = new BigDecimal(0.00);
	/** 礼物返还 */
	private BigDecimal duductGift = new BigDecimal(0.00);
	/** 主播提成 */
	private BigDecimal pushMoney = new BigDecimal(0.00);
	/** 提成工资 */
	private BigDecimal pushMoneySalaires = new BigDecimal(0.00);
	/** 主播收入 */
	private BigDecimal actoresSalary = new BigDecimal(0.00);
	/** 净流水 */
	private BigDecimal netIncome = new BigDecimal(0.00);
	/** 实际盈亏 */
	private BigDecimal netProfit = new BigDecimal(0.00);
	/** 平台补贴 */
	private BigDecimal platSubsidy = new BigDecimal(0.00);
	/** 平台扣除 */
	private BigDecimal platDeduct = new BigDecimal(0.00);
	/** 备注 */
	private String remarks;
	/** 所在频道 */
	private String channel;
	/** 所在平台 */
	private String platforms;
	/** 关联艺人Id */
	private String pactId;
	/** 所在频道Id */
	private String channelId;
	/** 所在平台 */
	private String platformsId;
	/** 审核状态 */
	private Audit audit = Audit.WRITE;
	/** 营收总表 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "months_id")
	private PlatformsMonthsOutlay months;

	public void make(BigDecimal baseRate) {
		if (attendance != null && attendance.doubleValue() > 0) {
			BigDecimal $salaires = new BigDecimal(0.00);
			if (attendance.doubleValue() >= Double.valueOf(30).doubleValue()) {
				$salaires = minimumGuarantee;
			} else
				$salaires = minimumGuarantee.divide(new BigDecimal(30), 3, BigDecimal.ROUND_HALF_UP).multiply(attendance);
			setBasicSalaires($salaires);
		} else {
			setBasicSalaires(new BigDecimal(0.00));
		}

		if (bgIncome != null && bgIncome.doubleValue() > 0) {
			BigDecimal $temp = bgIncome.subtract(deductTax).subtract(duductGift);
			BigDecimal $pushSalaries = $temp.multiply(pushMoney == null ? new BigDecimal(0.00) : pushMoney).divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP);
			BigDecimal $ladortax = $pushSalaries.add(platSubsidy).multiply(baseRate).divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP);
			setLaborService($ladortax);
			setPushMoneySalaires($pushSalaries);
		} else {
			setLaborService(new BigDecimal(0.00));
			setPushMoneySalaires(new BigDecimal(0.00));
		}

		if (pushMoneySalaires.add(platSubsidy).subtract(laborService).doubleValue() < basicSalaires.doubleValue()) {
			pushMoneySalaires = basicSalaires;
		}

		setActoresSalary((pushMoneySalaires == null ? new BigDecimal(0.00) : pushMoneySalaires).add(platSubsidy == null ? new BigDecimal(0.00) : platSubsidy)
				.subtract(platDeduct == null ? new BigDecimal(0.00) : platDeduct).subtract(laborService == null ? new BigDecimal(0.00) : laborService));

		setNetIncome((bgIncome == null ? new BigDecimal(0.00) : bgIncome).subtract(
				new BigDecimal(Math.max((actoresSalary == null ? new BigDecimal(0.00) : actoresSalary).doubleValue(), 0))).subtract(
				laborService == null ? new BigDecimal(0.00) : laborService));
	}

	public String getAliasname() {
		return aliasname;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAttendance() {
		return attendance;
	}

	public void setAttendance(BigDecimal attendance) {
		this.attendance = attendance;
	}

	public BigDecimal getMinimumGuarantee() {
		return minimumGuarantee;
	}

	public void setMinimumGuarantee(BigDecimal minimumGuarantee) {
		this.minimumGuarantee = minimumGuarantee;
	}

	public BigDecimal getBasicSalaires() {
		return basicSalaires;
	}

	public void setBasicSalaires(BigDecimal basicSalaires) {
		this.basicSalaires = basicSalaires;
	}

	public BigDecimal getBgIncome() {
		return bgIncome;
	}

	public void setBgIncome(BigDecimal bgIncome) {
		this.bgIncome = bgIncome;
	}

	public BigDecimal getLaborService() {
		return laborService;
	}

	public void setLaborService(BigDecimal laborService) {
		this.laborService = laborService;
	}

	public BigDecimal getDeductTax() {
		return deductTax;
	}

	public void setDeductTax(BigDecimal deductTax) {
		this.deductTax = deductTax;
	}

	public BigDecimal getDuductGift() {
		return duductGift;
	}

	public void setDuductGift(BigDecimal duductGift) {
		this.duductGift = duductGift;
	}

	public BigDecimal getPushMoney() {
		return pushMoney;
	}

	public void setPushMoney(BigDecimal pushMoney) {
		this.pushMoney = pushMoney;
	}

	public BigDecimal getPushMoneySalaires() {
		return pushMoneySalaires;
	}

	public void setPushMoneySalaires(BigDecimal pushMoneySalaires) {
		this.pushMoneySalaires = pushMoneySalaires;
	}

	public BigDecimal getActoresSalary() {
		return actoresSalary;
	}

	public void setActoresSalary(BigDecimal actoresSalary) {
		this.actoresSalary = actoresSalary;
	}

	public BigDecimal getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(BigDecimal netIncome) {
		this.netIncome = netIncome;
	}

	public BigDecimal getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(BigDecimal netProfit) {
		this.netProfit = netProfit;
	}

	public BigDecimal getPlatSubsidy() {
		return platSubsidy;
	}

	public void setPlatSubsidy(BigDecimal platSubsidy) {
		this.platSubsidy = platSubsidy;
	}

	public BigDecimal getPlatDeduct() {
		return platDeduct;
	}

	public void setPlatDeduct(BigDecimal platDeduct) {
		this.platDeduct = platDeduct;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPlatforms() {
		return platforms;
	}

	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	public String getPactId() {
		return pactId;
	}

	public void setPactId(String pactId) {
		this.pactId = pactId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getPlatformsId() {
		return platformsId;
	}

	public void setPlatformsId(String platformsId) {
		this.platformsId = platformsId;
	}

	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	public PlatformsMonthsOutlay getMonths() {
		return months;
	}

	public void setMonths(PlatformsMonthsOutlay months) {
		this.months = months;
	}
}
