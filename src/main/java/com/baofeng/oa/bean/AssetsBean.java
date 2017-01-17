package com.baofeng.oa.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat2;
import com.baofeng.utils.CustomDateSerializerFormat3;

/**
 *   功能 :固定资产
 */
public class AssetsBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 当前时间 */
	@JsonSerialize(using = CustomDateSerializerFormat3.class)
	private Date thisMonth;
	private String thisMonth1;
	public String getThisMonth1() {
		return thisMonth1;
	}
	public void setThisMonth1(String thisMonth1) {
		this.thisMonth1 = thisMonth1;
	}
	/** 固定资产 */
	private BigDecimal assetsMoney;
	/** 提交文件*/
	private String file;
	/** 备注 */
	private String remarks;
	/** 审阅意见*/
	private String opinion;
	/** 提交时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date completeDT;
	
	private Integer empId;
	
	private String empName;
	
	private String branchs;
	private String branchsMsg;
	
	/** 审核状态 */
	private String headPass;
	
	public String getHeadPass() {
		return headPass;
	}
	public void setHeadPass(String headPass) {
		this.headPass = headPass;
	}
	public String getBranchsMsg() {
		return branchsMsg;
	}
	public void setBranchsMsg(String branchsMsg) {
		this.branchsMsg = branchsMsg;
	}
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBranchs() {
		return branchs;
	}
	public void setBranchs(String branchs) {
		this.branchs = branchs;
	}
	public Date getCompleteDT() {
		return completeDT;
	}
	public void setCompleteDT(Date completeDT) {
		this.completeDT = completeDT;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
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
