package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.oa.entity.BaseEnums.Grant;

@Entity
@Table(name = "finSalariesTalent")
public class FinSalariesTalent extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 姓名 */
	private String name ;
	/** 艺名 */
	private String aliasname;
	/** 性别 */
	private Sex sex;
	/** 星探费 */
	private BigDecimal taSalary = new BigDecimal(0.00);
	/** 发放状态 */
	private Grant bill = Grant.UNBILLED;
	/** 银行卡号 */
	private String bankCard;
	/** 开户行地址 */
	private String bankAddress;
	/**实体星探数据*/
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "talent_id")
	private Talent talent;
	
	/** 其他补贴 */
	private BigDecimal otherSubsidy = new BigDecimal(0.00);
	/** 其他扣款 */
	private BigDecimal otherDeduct = new BigDecimal(0.00);
	/** 备注 */
	private String remarks;
	/** 实际支出 */
	private BigDecimal realExpenditure = new BigDecimal(0.00);
	/** 实发工资 */
	private BigDecimal realitySalary = new BigDecimal(0.00);
	
	
	
	public BigDecimal getRealExpenditure() {
		return realExpenditure;
	}
	public void setRealExpenditure(BigDecimal realExpenditure) {
		this.realExpenditure = realExpenditure;
	}
	public BigDecimal getRealitySalary() {
		return realitySalary;
	}
	public void setRealitySalary(BigDecimal realitySalary) {
		this.realitySalary = realitySalary;
	}
	public BigDecimal getOtherSubsidy() {
		return otherSubsidy;
	}
	public void setOtherSubsidy(BigDecimal otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}
	public BigDecimal getOtherDeduct() {
		return otherDeduct;
	}
	public void setOtherDeduct(BigDecimal otherDeduct) {
		this.otherDeduct = otherDeduct;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Talent getTalent() {
		return talent;
	}
	public void setTalent(Talent talent) {
		this.talent = talent;
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
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public BigDecimal getTaSalary() {
		return taSalary;
	}
	public void setTaSalary(BigDecimal taSalary) {
		this.taSalary = taSalary;
	}
	public Grant getBill() {
		return bill;
	}
	public void setBill(Grant bill) {
		this.bill = bill;
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
	
	
	

}
