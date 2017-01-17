package com.baofeng.oa.bean;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat1;

/**
 * 功能：调薪申请对像
 * */
public class FinSalariesAdjustsBean implements Serializable {

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
	/** 原薪资 */
	private String sbasicSalary;
	/** 调整薪资 */
	private String nbasicSalary;
	/** 原提成 */
	private String spushMoney;
	/** 调整提成 */
	private String npushMoney;
	/** 调整原因 */
	private String adjustMsg;
	/** 调薪对像 */
	private String actores;
	/** 申请状态 */
	private String adjust;
	/** 申请人 */
	private String employee;
	/** 备注 */
	private String remarks;
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date createDT;
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date passTime;
	
	

	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
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

	public String getNbasicSalary() {
		return nbasicSalary;
	}

	public void setNbasicSalary(String nbasicSalary) {
		this.nbasicSalary = nbasicSalary;
	}

	public String getSpushMoney() {
		return spushMoney;
	}

	public void setSpushMoney(String spushMoney) {
		this.spushMoney = spushMoney;
	}

	public String getNpushMoney() {
		return npushMoney;
	}

	public void setNpushMoney(String npushMoney) {
		this.npushMoney = npushMoney;
	}

	public String getAdjustMsg() {
		return adjustMsg;
	}

	public void setAdjustMsg(String adjustMsg) {
		this.adjustMsg = adjustMsg;
	}

	public String getActores() {
		return actores;
	}

	public void setActores(String actores) {
		this.actores = actores;
	}

	public String getAdjust() {
		return adjust;
	}

	public void setAdjust(String adjust) {
		this.adjust = adjust;
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

}
