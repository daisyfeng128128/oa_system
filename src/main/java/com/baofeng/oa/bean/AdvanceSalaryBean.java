package com.baofeng.oa.bean;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat1;

/**
 * 功能：预支薪资(艺人)
 * */
public class AdvanceSalaryBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	/** 真实姓名 */
	private String realname;
	/** 艺名 */
	private String aliasname;
	/** 性别 */
	private String sex;
	/** 所属平台 */
	private String platName;
	/** 艺人类型 */
	private String genre;
	/** 薪资 */
	private String sbasicSalary;
	/** 借支金额*/
	private String advanceSalary;
	/** 申请状态 */
	private String advance;	
	/**还款金额*/
	private String RepaySalary;
	/** 还款状态 */
	private  String repay;
	
	/** 申请人 */
	private String employee;
	/** 备注 */
	private String remarks;
	/** 创建时间 */
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date createDT;
	/** 通过时间 */
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date passTime;
	/** 还款时间 */
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date repayTime;
	
	public String getRepay() {
		return repay;
	}
	public void setRepay(String repay) {
		this.repay = repay;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPlatName() {
		return platName;
	}
	public void setPlatName(String platName) {
		this.platName = platName;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getSbasicSalary() {
		return sbasicSalary;
	}
	public void setSbasicSalary(String sbasicSalary) {
		this.sbasicSalary = sbasicSalary;
	}
	public String getAdvanceSalary() {
		return advanceSalary;
	}
	public void setAdvanceSalary(String advanceSalary) {
		this.advanceSalary = advanceSalary;
	}
	public String getAdvance() {
		return advance;
	}
	public void setAdvance(String advance) {
		this.advance = advance;
	}
	public String getRepaySalary() {
		return RepaySalary;
	}
	public void setRepaySalary(String repaySalary) {
		RepaySalary = repaySalary;
	}

	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getCreateDT() {
		return createDT;
	}
	public void setCreateDT(Date createDT) {
		this.createDT = createDT;
	}
	public Date getPassTime() {
		return passTime;
	}
	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}
	public Date getRepayTime() {
		return repayTime;
	}
	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}
	
}
