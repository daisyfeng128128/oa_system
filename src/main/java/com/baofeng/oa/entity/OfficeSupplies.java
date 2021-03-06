package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.oa.entity.BaseEnums.Auditp;
import com.baofeng.oa.entity.BaseEnums.Purchase;
import com.baofeng.utils.CustomDateSerializerFormat2;


/**
 * 功能：日常用品申请
 * */
@Entity
@Table(name = "officeSupplies")
public class OfficeSupplies extends BaseInteger implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String depart;
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	/** 申请物品名称 */
	private String goodsname;
	/** 型号 */
	private String model;
	/** 单价 */
	private BigDecimal priceMoney = new BigDecimal(0.00);
	/** 姓名 */
	private String realname;
	/** 姓名 */
	private String remarks;

	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/** 数量 */
	private Integer numbers;
	/** 税额 */
	private BigDecimal taxMoney = new BigDecimal(0.00);
	/** 总价 */
	private BigDecimal totalMoney = new BigDecimal(0.00);
	/** 申请时间 */
	private Date applyDT;
	/** 审核状态 */
	private Auditp auditp= Auditp.PENDING;
	public Auditp getAuditp() {
		return auditp;
	}
	public void setAuditp(Auditp auditp) {
		this.auditp = auditp;
	}
	/** 采购状态 */
	private Purchase purchase = Purchase.FAILED;
	/** 采购完成时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date completeDT;
	/** 申请人 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private Employee employee;
	/***/
	private String rejectMsg;
	/** 2 电子设备  1 日用品*/
	private Integer purType;
	/** 原因/评语 */
	@Lob
	private String reason;
	
	/** Unknow 时  1 部门 2  负责人 3总公司 */
	private Integer passType;
	/** Unknow 时  1 部门 2  负责人 3总公司 */
	private Integer purchaseType;
	
	private Integer process;
	
	public Integer getProcess() {
		return process;
	}
	public void setProcess(Integer process) {
		this.process = process;
	}
	public Integer getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(Integer purchaseType) {
		this.purchaseType = purchaseType;
	}
	public Integer getPassType() {
		return passType;
	}
	public void setPassType(Integer passType) {
		this.passType = passType;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getPurType() {
		return purType;
	}
	public void setPurType(Integer purType) {
		this.purType = purType;
	}
	public String getRejectMsg() {
		return rejectMsg;
	}
	public void setRejectMsg(String rejectMsg) {
		this.rejectMsg = rejectMsg;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Integer getNumbers() {
		return numbers;
	}
	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public BigDecimal getPriceMoney() {
		return priceMoney;
	}
	public void setPriceMoney(BigDecimal priceMoney) {
		this.priceMoney = priceMoney;
	}
	public BigDecimal getTaxMoney() {
		return taxMoney;
	}
	public void setTaxMoney(BigDecimal taxMoney) {
		this.taxMoney = taxMoney;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Date getApplyDT() {
		return applyDT;
	}
	public void setApplyDT(Date applyDT) {
		this.applyDT = applyDT;
	}
	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	public Date getCompleteDT() {
		return completeDT;
	}
	public void setCompleteDT(Date completeDT) {
		this.completeDT = completeDT;
	}
	
}
