package com.baofeng.oa.bean;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat2;

/**
 * 功能 ： 转正审核
 * */
public class PositiveReviewBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	/** 员工号 */
	private String number;
	/** 姓名 */
	private String name;
	/** 艺名 */
	private String nickName;
	/** 入职日期 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date joinDate;
	/** 所属平台 */
	private String platName;
	/** 延长原因/评语 */
	private String reason;
	/** 审批人 */
	private String exAppPer;
	/** 申请人 */
	private String applicant;
	/** 状态 */
	private Integer type;
	/** 转正日期 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date confirmationDate;
	/** 艺人/管理 */
	private Integer empType;
	/** 是否延长试用期 0： 是 ，1否 */
	private Integer isExtend;
	/** 底薪 */
	private String salary;
	/** 提成比例 */
	private String pushMoney;
	/** int 比例 */
	private String intPush;
	
	
	public String getIntPush() {
		return intPush;
	}

	public void setIntPush(String intPush) {
		this.intPush = intPush;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getPushMoney() {
		return pushMoney;
	}

	public void setPushMoney(String pushMoney) {
		this.pushMoney = pushMoney;
	}

	public Integer getIsExtend() {
		return isExtend;
	}

	public void setIsExtend(Integer isExtend) {
		this.isExtend = isExtend;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
