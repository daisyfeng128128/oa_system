package com.baofeng.oa.bean;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat2;

/**
 *   功能 :日常用品申请
 */
public class OfficeSuppliesBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 真实姓名 */
	private String realname;
	/** 申请物品名称 */
	private String goodsname;
	/** 型号 */
	private String model;
	/** 单价 */
	private String priceMoney;
	/** 数量 */
	private String numbers;
	/** 税额 */
	private String taxMoney;
	/** 总价 */
	private String totalMoney;
	/** 申请时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date applyDT;
	/** 审核状态 */
	private String auditp;
	public String getAuditp() {
		return auditp;
	}
	public void setAuditp(String auditp) {
		this.auditp = auditp;
	}
	/** 采购完成时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date completeDT;
	/** 备注 */
	private String remarks;
	/** 采购状态 */
	private String purchase;
	/** 部门 */
	private String depart;
	/** String 员工号 */
	private String num;
	/** 2 电子设备  1 日用品*/
	private Integer purType;
	
	private Integer process;
	
	public Integer getProcess() {
		return process;
	}
	public void setProcess(Integer process) {
		this.process = process;
	}
	private Integer passType;
	
	public Integer getPassType() {
		return passType;
	}
	public void setPassType(Integer passType) {
		this.passType = passType;
	}
	private String reason;
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	private String rejectMsg;
	private String branchs;
	
	public String getBranchs() {
		return branchs;
	}
	public void setBranchs(String branchs) {
		this.branchs = branchs;
	}
	private Integer id;
	
	public Date getApplyDT() {
		return applyDT;
	}
	public void setApplyDT(Date applyDT) {
		this.applyDT = applyDT;
	}
	public Date getCompleteDT() {
		return completeDT;
	}
	public void setCompleteDT(Date completeDT) {
		this.completeDT = completeDT;
	}
	
	public String getRejectMsg() {
		return rejectMsg;
	}
	public void setRejectMsg(String rejectMsg) {
		this.rejectMsg = rejectMsg;
	}
	
	public Integer getPurType() {
		return purType;
	}
	public void setPurType(Integer purType) {
		this.purType = purType;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getPurchase() {
		return purchase;
	}
	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
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
	public String getPriceMoney() {
		return priceMoney;
	}
	public void setPriceMoney(String priceMoney) {
		this.priceMoney = priceMoney;
	}
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	public String getTaxMoney() {
		return taxMoney;
	}
	public void setTaxMoney(String taxMoney) {
		this.taxMoney = taxMoney;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

}
