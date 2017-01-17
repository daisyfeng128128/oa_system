package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.oa.entity.BaseEnums.Audit;

/**
 * 功能：频道充值
 * */
@Entity
@Table(name = "platformsMaintainOutlay")
public class PlatformsMaintainOutlay extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 7636428549858475850L;
	/** 员工号 */
	private Integer number;
	/** 姓名 */
	private String name;
	/** 支出内容 */
	private String outlay;
	/** 个人礼物 */
	@Column(precision = 8, scale = 2)
	private Float personalGift = new Float(0);
	/** 转会费 */
	@Column(precision = 8, scale = 2)
	private Float transferFee = new Float(0);
	/** 大型充值 */
	@Column(precision = 8, scale = 2)
	private Float topup = new Float(0);
	/** 频道返还 */
	@Column(precision = 8, scale = 2)
	private Float restitution = new Float(0);
	/** 纯支出 */
	@Column(precision = 8, scale = 2)
	private Float netOutlay = new Float(0);
	/** 税点 */
	private BigDecimal tax = new BigDecimal(0.00);
	/** 备注 */
	private String remarks;
	/** 所在频道 */
	private String channel;
	/** 所在平台 */
	private String platforms;
	/** 员工档案表Id */
	private String employeeId;
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

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOutlay() {
		return outlay;
	}

	public void setOutlay(String outlay) {
		this.outlay = outlay;
	}

	public Float getPersonalGift() {
		return personalGift;
	}

	public void setPersonalGift(Float personalGift) {
		this.personalGift = personalGift;
	}

	public Float getTransferFee() {
		return transferFee;
	}

	public void setTransferFee(Float transferFee) {
		this.transferFee = transferFee;
	}

	public Float getTopup() {
		return topup;
	}

	public void setTopup(Float topup) {
		this.topup = topup;
	}

	public Float getRestitution() {
		return restitution;
	}

	public void setRestitution(Float restitution) {
		this.restitution = restitution;
	}

	public Float getNetOutlay() {
		return netOutlay;
	}

	public void setNetOutlay(Float netOutlay) {
		this.netOutlay = netOutlay;
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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
