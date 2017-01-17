package com.baofeng.oa.bean;

import java.io.Serializable;

import com.baofeng.oa.entity.BaseEnums.Audit;

@SuppressWarnings("serial")
public class PlatformsOfflineIncomeBean implements Serializable {

	private Integer id;
	/** 员工号 */
	private String number;
	/** 艺名 */
	private String aliasname;
	/** 姓名 */
	private String name;
	/** 当月收入 */
	private String income;
	/** 税点 */
	private String taxPoint;
	/** 平台税率 */
	private String tax;
	/** 公返还礼物 */
	private String restitutionGift;
	/** 底薪 */
	private String basicSalary;
	private String realBasicSalary;
	/** 净收入 */
	private String netIncome;
	private String realNetIncome;
	/** 劳务服务费 */
	private String laborService;
	/** 提成 */
	private String pushMoney;
	private String realPushMoney;
	/** 出勤天数 */
	private String attendance;
	/** 备注 */
	private String remarks;
	/** 所在频道 */
	private String channel;
	/** 所在平台 */
	private String platforms;
	/** 艺人表Id */
	private String actoresId;
	/** 所在频道Id */
	private String channelId;
	/** 所在平台 */
	private String platformsId;
	/** 平台补贴 */
	private String platSubsidy;
	/** 平台扣除 */
	private String platDeduct;
	/** 实际收入 */
	private String netOffincome;
	/** 审核状态 */
	private Audit audit = Audit.WRITE;

	
	
	public String getLaborService() {
		return laborService;
	}

	public void setLaborService(String laborService) {
		this.laborService = laborService;
	}

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

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getTaxPoint() {
		return taxPoint;
	}

	public void setTaxPoint(String taxPoint) {
		this.taxPoint = taxPoint;
	}

	public String getRestitutionGift() {
		return restitutionGift;
	}

	public void setRestitutionGift(String restitutionGift) {
		this.restitutionGift = restitutionGift;
	}

	public String getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}

	public String getRealBasicSalary() {
		return realBasicSalary;
	}

	public void setRealBasicSalary(String realBasicSalary) {
		this.realBasicSalary = realBasicSalary;
	}

	public String getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(String netIncome) {
		this.netIncome = netIncome;
	}

	public String getRealNetIncome() {
		return realNetIncome;
	}

	public void setRealNetIncome(String realNetIncome) {
		this.realNetIncome = realNetIncome;
	}

	public String getPushMoney() {
		return pushMoney;
	}

	public void setPushMoney(String pushMoney) {
		this.pushMoney = pushMoney;
	}

	public String getRealPushMoney() {
		return realPushMoney;
	}

	public void setRealPushMoney(String realPushMoney) {
		this.realPushMoney = realPushMoney;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
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

	public String getActoresId() {
		return actoresId;
	}

	public void setActoresId(String actoresId) {
		this.actoresId = actoresId;
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

	public String getPlatSubsidy() {
		return platSubsidy;
	}

	public void setPlatSubsidy(String platSubsidy) {
		this.platSubsidy = platSubsidy;
	}

	public String getPlatDeduct() {
		return platDeduct;
	}

	public void setPlatDeduct(String platDeduct) {
		this.platDeduct = platDeduct;
	}

	public String getNetOffincome() {
		return netOffincome;
	}

	public void setNetOffincome(String netOffincome) {
		this.netOffincome = netOffincome;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}
}
