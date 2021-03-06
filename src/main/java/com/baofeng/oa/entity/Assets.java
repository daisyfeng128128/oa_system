package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.oa.entity.BaseEnums.HeadPass;
import com.baofeng.utils.CustomDateSerializerFormat2;
import com.baofeng.utils.CustomDateSerializerFormat3;

/**
 * 功能：固定资产
 * */
@Entity
@Table(name = "assets")
public class Assets extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 固定资产的时间 */
	@JsonSerialize(using = CustomDateSerializerFormat3.class)
	private Date thisMonth;
	/** 固定资产 */
	private BigDecimal assetsMoney = new BigDecimal(0.00);
	/** 提交人 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private Employee employee;
	/** 提交文件*/
	private String file;
	/** 备注 */
	private String remarks;
	/** 审阅意见*/
	private String opinion;
	/** 提交时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date completeDT;
	
	private HeadPass headPass = HeadPass.FAILED;
	
	public HeadPass getHeadPass() {
		return headPass;
	}
	public void setHeadPass(HeadPass headPass) {
		this.headPass = headPass;
	}
	public Date getCompleteDT() {
		return completeDT;
	}
	public void setCompleteDT(Date completeDT) {
		this.completeDT = completeDT;
	}
	public Date getThisMonth() {
		return thisMonth;
	}
	public void setThisMonth(Date thisMonth) {
		this.thisMonth = thisMonth;
	}
	public BigDecimal getAssetsMoney() {
		return assetsMoney;
	}
	public void setAssetsMoney(BigDecimal assetsMoney) {
		this.assetsMoney = assetsMoney;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
}
