package com.baofeng.oa.bean;



public class FinSalariesTalentBean {
	/** id */
	private String id;
	/** 姓名 */
	private String name ;
	/** 艺名 */
	private String aliasname;
	/** 性别 */
	private String sex;
	/** 星探费 */
	private String taSalary;
	/** 发放状态 */
	private String bill;
	/** 银行卡号 */
	private String bankCard;
	/** 开户行地址 */
	private String bankAddress;
	/** 其他补贴 */
	private String otherSubsidy;
	/** 其他扣款 */
	private String otherDeduct;
	/** 实际支出 */
	private String realExpenditure;
	/** 实发工资 */
	private String realitySalary;
	/** 备注 */
	private String remarks;
	

	
	
	public String getRealExpenditure() {
		return realExpenditure;
	}
	public void setRealExpenditure(String realExpenditure) {
		this.realExpenditure = realExpenditure;
	}
	public String getRealitySalary() {
		return realitySalary;
	}
	public void setRealitySalary(String realitySalary) {
		this.realitySalary = realitySalary;
	}
	public String getOtherSubsidy() {
		return otherSubsidy;
	}
	public void setOtherSubsidy(String otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}
	public String getOtherDeduct() {
		return otherDeduct;
	}
	public void setOtherDeduct(String otherDeduct) {
		this.otherDeduct = otherDeduct;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTaSalary() {
		return taSalary;
	}
	public void setTaSalary(String taSalary) {
		this.taSalary = taSalary;
	}
	
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public String getBill() {
		return bill;
	}
	public void setBill(String bill) {
		this.bill = bill;
	}
	
	
}
