package com.baofeng.oa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.oa.entity.BaseEnums.Audit;

/**
 * 功能：平台场控工资支出表
 * */
@Entity
@Table(name = "platformsThirdManagerOutlay")
public class PlatformsThirdManagerOutlay extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -8591211514272641016L;
	/** 马甲 */
	private String majia;
	/** 姓名 */
	private String name;
	/** 场控岗位 */
	private String positions;
	/** 本月工时 */
	@Column(precision = 8, scale = 2)
	private Float monthHours = new Float(0);
	/** 小时工资 */
	@Column(precision = 8, scale = 2)
	private Float hourSalary = new Float(0);
	/** 应付工资 */
	@Column(precision = 8, scale = 2)
	private Float payment = new Float(0);
	/** 其他补贴 */
	@Column(precision = 8, scale = 2)
	private Float otherAllowance = new Float(0);
	/** 扣除 */
	@Column(precision = 8, scale = 2)
	private Float deduct = new Float(0);
	/** 合计 */
	@Column(precision = 8, scale = 2)
	private Float total = new Float(0);
	/** 备注 */
	private String remarks;
	/** 所在频道 */
	private String channel;
	/** 所在平台 */
	private String platforms;
	/** 场控表Id */
	private String thirdManagerId;
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

	public String getMajia() {
		return majia;
	}

	public void setMajia(String majia) {
		this.majia = majia;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPositions() {
		return positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

	public Float getMonthHours() {
		return monthHours;
	}

	public void setMonthHours(Float monthHours) {
		this.monthHours = monthHours;
	}

	public Float getHourSalary() {
		return hourSalary;
	}

	public void setHourSalary(Float hourSalary) {
		this.hourSalary = hourSalary;
	}

	public Float getPayment() {
		return payment;
	}

	public void setPayment(Float payment) {
		this.payment = payment;
	}

	public Float getOtherAllowance() {
		return otherAllowance;
	}

	public void setOtherAllowance(Float otherAllowance) {
		this.otherAllowance = otherAllowance;
	}

	public Float getDeduct() {
		return deduct;
	}

	public void setDeduct(Float deduct) {
		this.deduct = deduct;
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

	public String getThirdManagerId() {
		return thirdManagerId;
	}

	public void setThirdManagerId(String thirdManagerId) {
		this.thirdManagerId = thirdManagerId;
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

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public PlatformsMonthsOutlay getMonths() {
		return months;
	}

	public void setMonths(PlatformsMonthsOutlay months) {
		this.months = months;
	}
}
