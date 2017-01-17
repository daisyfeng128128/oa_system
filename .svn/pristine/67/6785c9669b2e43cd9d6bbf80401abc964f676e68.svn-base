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
import com.baofeng.oa.entity.BaseEnums.LineGenres;

/**
 * 功能：频道助理工资支出表
 * */
@Entity
@Table(name = "platformsManagerOutlay")
public class PlatformsManagerOutlay extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 8649256233685391431L;

	/** 员工号 */
	private Integer number;
	/** 姓名 */
	private String name;
	/** 艺名 */
	private String aliasname;
	/** 类型(线上,线下) */
	private LineGenres genre;
	/** 管理岗位 */
	private String positions;
	/** 出勤天数 */
	@Column(precision = 8, scale = 2)
	private Float attendance = new Float(0);
	/** 基本底薪 */
	@Column(precision = 8, scale = 2)
	private Float basicSalary = new Float(0);
	/** 提成本 */
	@Column(precision = 8, scale = 2)
	private Float pushMoney = new Float(0);
	/** 餐补 */
	@Column(precision = 8, scale = 2)
	private Float mealAllowance = new Float(0);
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
	/** 助理人员表Id */
	private String managerId;
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

	public void make() {
		
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

	public String getAliasname() {
		return aliasname;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}

	public LineGenres getGenre() {
		return genre;
	}

	public void setGenre(LineGenres genre) {
		this.genre = genre;
	}

	public String getPositions() {
		return positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

	public Float getAttendance() {
		return attendance;
	}

	public void setAttendance(Float attendance) {
		this.attendance = attendance;
	}

	public Float getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(Float basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Float getPushMoney() {
		return pushMoney;
	}

	public void setPushMoney(Float pushMoney) {
		this.pushMoney = pushMoney;
	}

	public Float getMealAllowance() {
		return mealAllowance;
	}

	public void setMealAllowance(Float mealAllowance) {
		this.mealAllowance = mealAllowance;
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

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
