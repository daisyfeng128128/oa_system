package com.baofeng.oa.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.baofeng.oa.entity.FinPlatfromsIncome;

public class SalaryOnlineBean  implements Serializable {

	/**
	 * 财务： 线上艺人工资
	 */
	private static final long serialVersionUID = 8875325652947557418L;

	/** ID*/
	private Integer id;
	/** 姓名 */
	private String name;
	/** 艺名 */
	private String aliasname;
	/** 平台收入 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "salay_id")
	private List<FinPlatfromsIncome> income = new ArrayList<FinPlatfromsIncome>();
	/** 税后总收入 */
	private String totalIncome;
	/** 基本保底 */
	private String basicSalary;
	/** 提成比例 */
	private String pushMoney;
	/** 提成奖金 */
	private String pushMoneySalary;
	/** 其他补贴 */
	private String otherSubsidy;
	/** 其他扣除 */
	private String otherDeduct;
	/** 分成扣税 */
	private String deductTax;
	/** 实发工资 */
	private String realitySalary;
	/** 出勤天数 */
	private String attendance;
	/** 实际盈亏 */
	private String netProfit;
	/** 艺人Id */
	private Integer actoresId;
	/** 备注 */
	private String remarks;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAliasname() {
		return aliasname;
	}
	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}
	public List<FinPlatfromsIncome> getIncome() {
		return income;
	}
	public void setIncome(List<FinPlatfromsIncome> income) {
		this.income = income;
	}
	public String getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}
	public String getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}
	
	public String getDeductTax() {
		return deductTax;
	}
	public void setDeductTax(String deductTax) {
		this.deductTax = deductTax;
	}
	public Integer getActoresId() {
		return actoresId;
	}
	public void setActoresId(Integer actoresId) {
		this.actoresId = actoresId;
	}
	public String getPushMoney() {
		return pushMoney;
	}
	public void setPushMoney(String pushMoney) {
		this.pushMoney = pushMoney;
	}
	public String getPushMoneySalary() {
		return pushMoneySalary;
	}
	public void setPushMoneySalary(String pushMoneySalary) {
		this.pushMoneySalary = pushMoneySalary;
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
	public String getRealitySalary() {
		return realitySalary;
	}
	public void setRealitySalary(String realitySalary) {
		this.realitySalary = realitySalary;
	}
	public String getNetProfit() {
		return netProfit;
	}
	public void setNetProfit(String netProfit) {
		this.netProfit = netProfit;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
