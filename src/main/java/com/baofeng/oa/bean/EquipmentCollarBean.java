package com.baofeng.oa.bean;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat2;

/**
 *   功能 :电子设备申请
 */
public class EquipmentCollarBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 真实姓名 */
	private String realname;
	private String aliasname;
	/** 部门 */
	private String depart;
	/** 申请物品名称 */
	private String goodsname;
	/** 型号 */
	private String model;
	/** 数量 */
	/** 申请时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date applyDT;
	private String reason;
	private String branchs;

	/** 审核状态 */
	private String auditp;
	/** 采购状态 */
	private String grantEquip;
	/** 采购完成时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date completeDT;
	/** 使用人 */
	private String username;
	/** 放置地点 */
	private String place;
	/** 备注 */
	private String remarks;
	/** String 员工号 */
	private String num;
	/** 2 电子设备  1 日用品*/
	private Integer passType;
	
	private Integer id;
	
	private String rejectMsg;
	/** 数量 */
	private String numbers;
	
	private Integer process;


	public Integer getProcess() {
		return process;
	}
	public void setProcess(Integer process) {
		this.process = process;
	}
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	public String getRejectMsg() {
		return rejectMsg;
	}
	public void setRejectMsg(String rejectMsg) {
		this.rejectMsg = rejectMsg;
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
	public String getAliasname() {
		return aliasname;
	}
	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
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
	public Date getApplyDT() {
		return applyDT;
	}
	public void setApplyDT(Date applyDT) {
		this.applyDT = applyDT;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getBranchs() {
		return branchs;
	}
	public void setBranchs(String branchs) {
		this.branchs = branchs;
	}
	public String getAuditp() {
		return auditp;
	}
	public void setAuditp(String auditp) {
		this.auditp = auditp;
	}
	public String getGrantEquip() {
		return grantEquip;
	}
	public void setGrantEquip(String grantEquip) {
		this.grantEquip = grantEquip;
	}
	public Date getCompleteDT() {
		return completeDT;
	}
	public void setCompleteDT(Date completeDT) {
		this.completeDT = completeDT;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public Integer getPassType() {
		return passType;
	}
	public void setPassType(Integer passType) {
		this.passType = passType;
	}

	
}
