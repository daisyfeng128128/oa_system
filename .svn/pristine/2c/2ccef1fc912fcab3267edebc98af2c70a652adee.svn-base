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
import com.baofeng.utils.Constants;

/**
 * 功能：平台线下艺人收入表
 * */
@Entity
@Table(name = "platformsOfflineIncome")
public class PlatformsOfflineIncome extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 5650327608105412850L;
	/** 员工号 */
	private Integer number;
	/** 艺名 */
	private String aliasname;
	/** 真实姓名 */
	private String name;
	/** 当月收入 */
	private BigDecimal income = new BigDecimal(0.00);
	/** 分成扣税 */
	private BigDecimal deductTax = new BigDecimal(0.00);
	/** 礼物返还 */
	private BigDecimal duductGift = new BigDecimal(0.00);
	/** 基本底薪 */
	private BigDecimal basicSalary = new BigDecimal(0.00);
	/** 实际底薪 */
	private BigDecimal basicSalaires = new BigDecimal(0.00);
	/** 劳务服务费 */
	private BigDecimal laborService = new BigDecimal(0.00);
	/** 提成比例 */
	private BigDecimal pushMoney = new BigDecimal(0.00);
	/** 提成工资 */
	private BigDecimal pushMoneySalaires = new BigDecimal(0.00);
	/** 艺人盈亏 */
	private BigDecimal netIncome = new BigDecimal(0.00);
	/** 平台补贴 */
	private BigDecimal platSubsidy = new BigDecimal(0.00);
	/** 平台扣除 */
	private BigDecimal platDeduct = new BigDecimal(0.00);
	/** 实际收入 */
	private BigDecimal netOffincome = new BigDecimal(0.00);
	/** 出勤天数 */
	private Float attendance = new Float(0);
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
		if (attendance != null && attendance.floatValue() > 0) {
			BigDecimal $salaires = new BigDecimal(0.00);
			if (attendance >= Constants.DEFAULT_ATTENDANCE)
				$salaires = basicSalary;
			else
				$salaires = basicSalary.divide(new BigDecimal(Constants.DEFAULT_ATTENDANCE), 3, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(attendance));
			setBasicSalaires($salaires);
		} else {
			setBasicSalaires(new BigDecimal(0.00));
		}
		
		if (income != null && income.doubleValue() > 0) {
			BigDecimal $temp = income.subtract(deductTax).subtract(duductGift);
			BigDecimal $pushSalaries = $temp.multiply(pushMoney == null ? new BigDecimal(0.00) : pushMoney).divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP);
			BigDecimal $ladortax = $pushSalaries.add(platSubsidy).multiply(baseRate).divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP);
			setLaborService($ladortax);
			setPushMoneySalaires($pushSalaries);
		}
		setNetOffincome((basicSalaires == null ? new BigDecimal(0.00) : basicSalaires).add(pushMoneySalaires == null ? new BigDecimal(0.00) : pushMoneySalaires)
				.add(platSubsidy == null ? new BigDecimal(0.00) : platSubsidy).subtract(platDeduct == null ? new BigDecimal(0.00) : platDeduct)
				.subtract(laborService == null ? new BigDecimal(0.00) : laborService));
		setNetIncome((income == null ? new BigDecimal(0.00) : income).subtract(
				new BigDecimal(Math.max((netOffincome == null ? new BigDecimal(0.00) : netOffincome).doubleValue(), 0))).subtract(
				laborService == null ? new BigDecimal(0.00) : laborService));
	}

	public Integer getNumber() {
		return number;
	}

	public String getAliasname() {
		return aliasname;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public BigDecimal getDeductTax() {
		return deductTax;
	}

	public BigDecimal getDuductGift() {
		return duductGift;
	}

	public BigDecimal getBasicSalary() {
		return basicSalary;
	}

	public BigDecimal getPushMoney() {
		return pushMoney;
	}

	public BigDecimal getNetIncome() {
		return netIncome;
	}

	public Float getAttendance() {
		return attendance;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getChannel() {
		return channel;
	}

	public String getPlatforms() {
		return platforms;
	}

	public String getPactId() {
		return pactId;
	}

	public String getChannelId() {
		return channelId;
	}

	public String getPlatformsId() {
		return platformsId;
	}

	public Audit getAudit() {
		return audit;
	}

	public PlatformsMonthsOutlay getMonths() {
		return months;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public void setDeductTax(BigDecimal deductTax) {
		this.deductTax = deductTax;
	}

	public void setDuductGift(BigDecimal duductGift) {
		this.duductGift = duductGift;
	}

	public void setBasicSalary(BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
	}

	public void setPushMoney(BigDecimal pushMoney) {
		this.pushMoney = pushMoney;
	}

	public void setNetIncome(BigDecimal netIncome) {
		this.netIncome = netIncome;
	}

	public void setAttendance(Float attendance) {
		this.attendance = attendance;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	public void setPactId(String pactId) {
		this.pactId = pactId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public void setPlatformsId(String platformsId) {
		this.platformsId = platformsId;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	public void setMonths(PlatformsMonthsOutlay months) {
		this.months = months;
	}

	public BigDecimal getPlatSubsidy() {
		return platSubsidy;
	}

	public BigDecimal getPlatDeduct() {
		return platDeduct;
	}

	public void setPlatSubsidy(BigDecimal platSubsidy) {
		this.platSubsidy = platSubsidy;
	}

	public void setPlatDeduct(BigDecimal platDeduct) {
		this.platDeduct = platDeduct;
	}

	public BigDecimal getNetOffincome() {
		return netOffincome;
	}

	public void setNetOffincome(BigDecimal netOffincome) {
		this.netOffincome = netOffincome;
	}

	public BigDecimal getLaborService() {
		return laborService;
	}

	public void setLaborService(BigDecimal laborService) {
		this.laborService = laborService;
	}

	public BigDecimal getPushMoneySalaires() {
		return pushMoneySalaires;
	}

	public void setPushMoneySalaires(BigDecimal pushMoneySalaires) {
		this.pushMoneySalaires = pushMoneySalaires;
	}

	public BigDecimal getBasicSalaires() {
		return basicSalaires;
	}

	public void setBasicSalaires(BigDecimal basicSalaires) {
		this.basicSalaires = basicSalaires;
	}
}
