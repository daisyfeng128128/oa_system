package com.baofeng.oa.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;

/**
 * 功能： 转正审核
 * */
@Entity
@Table(name = "positiveReview")
public class PositiveReview extends BaseInteger {

	private static final long serialVersionUID = 1L;

	/** 员工号 */
	private Integer number;
	/** 姓名 */
	private String name;
	/** 艺名 */
	private String nickName;
	/** 入职日期 */
	private Date joinDate;
	/** 所属平台 */
	private String platName;
	/** 延长原因/评语 */
	@Lob
	private String reason;
	/** 审批人 */
	private String exAppPer;
	/** 申请人 */
	private String applicant;
	/** 状态 0 待转正 1 转正完成  2 拒绝 3 审核完成   */
	private Integer type;
	/** 转正日期 */
	private Date confirmationDate;
	/** 艺人/管理  :  1 /2 */
	private Integer empType;
	/** 平台ID */
	private Integer platId;
	
	public Integer getPlatId() {
		return platId;
	}

	public void setPlatId(Integer platId) {
		this.platId = platId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getPlatName() {
		return platName;
	}

	public void setPlatName(String platName) {
		this.platName = platName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getExAppPer() {
		return exAppPer;
	}

	public void setExAppPer(String exAppPer) {
		this.exAppPer = exAppPer;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public Integer getEmpType() {
		return empType;
	}

	public void setEmpType(Integer empType) {
		this.empType = empType;
	}

}
