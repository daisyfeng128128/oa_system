package com.baofeng.oa.entity;

import java.io.Serializable;
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
import com.baofeng.oa.entity.BaseEnums.GrantEquip;
import com.baofeng.utils.CustomDateSerializerFormat2;


/**
 * 功能：电子设备申请
 * */
@Entity
@Table(name = "equipmentCollar")
public class EquipmentCollar extends BaseInteger implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/** 申请物品名称 */
	private String goodsname;
	/** 型号 */
	private String model;
	/** 数量 */
	private Integer numbers;
	/** 申请时间 */
	private Date applyDT;
	/** 审核状态 */
	private Auditp auditp= Auditp.PENDING;
	/** 发放状态 */
	private GrantEquip grantEquip = GrantEquip.FAILED;
	/** 采购完成时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date completeDT;
	/** 使用人 */
	private String username;
	/** 放置地点 */
	private String place;
	/** 备注 */
	private String remarks;
	/** 申请人 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private Employee employee;
	/** 姓名 */
	private String realname;
	/** 艺名 */
	private String aliasname;
	/***/
	private String rejectMsg;
	/** 部门 */
	private String depart;
	/** 原因/评语 */
	@Lob
	private String reason;
	
	/** 发放人 */
	@Lob
	private String issuer;
	
	private Integer process;

	public Integer getProcess() {
		return process;
	}

	public void setProcess(Integer process) {
		this.process = process;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
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

	public Integer getNumbers() {
		return numbers;
	}

	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}

	public Date getApplyDT() {
		return applyDT;
	}

	public void setApplyDT(Date applyDT) {
		this.applyDT = applyDT;
	}

	public Auditp getAuditp() {
		return auditp;
	}

	public void setAuditp(Auditp auditp) {
		this.auditp = auditp;
	}

	public GrantEquip getGrantEquip() {
		return grantEquip;
	}

	public void setGrantEquip(GrantEquip grantEquip) {
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public String getRejectMsg() {
		return rejectMsg;
	}

	public void setRejectMsg(String rejectMsg) {
		this.rejectMsg = rejectMsg;
	}
	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
