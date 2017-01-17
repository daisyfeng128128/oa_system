package com.baofeng.oa.bean;

import java.io.Serializable;

import com.baofeng.oa.entity.BaseEnums.Audit;

/**
 * 功能：频道助理数据
 * */
@SuppressWarnings("serial")
public class PlatformsManagerOutlayBean implements Serializable {

	private Integer id;
	/** 员工号 */
	private String number;
	/** 姓名 */
	private String name;
	/** 艺名 */
	private String aliasname;
	/** 类型(线上,线下) */
	private String genre;
	/** 管理岗位 */
	private String positions;
	/** 出勤天数 */
	private Float attendance;
	/** 基本底薪 基准 */
	private Float basicSalary;
	/** 计算后底薪 */
	private String realBasicSalary;
	/** 提成本 */
	private Float pushMoney;
	/** 餐补 */
	private Float mealAllowance;
	/** 计算后餐补 */
	private String realMealAllowance;
	/** 其他补贴 */
	private Float otherAllowance;
	/** 扣除 */
	private Float deduct;
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
	/** 合计 */
	private String total;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
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

	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	public String getRealBasicSalary() {
		return realBasicSalary;
	}

	public void setRealBasicSalary(String realBasicSalary) {
		this.realBasicSalary = realBasicSalary;
	}

	public String getRealMealAllowance() {
		return realMealAllowance;
	}

	public void setRealMealAllowance(String realMealAllowance) {
		this.realMealAllowance = realMealAllowance;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}
