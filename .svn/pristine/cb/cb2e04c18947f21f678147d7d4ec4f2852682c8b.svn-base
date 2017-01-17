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
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.oa.entity.BaseEnums.Adjust;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.utils.CustomDateSerializerFormat1;

/**
 * 功能：薪资调整(艺人)
 * */
@Entity
@Table(name = "finSalariesAdjusts")
public class FinSalariesAdjusts extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 真实姓名 */
	private String realname;
	/** 艺名 */
	private String aliasname;
	/** 性别 */
	private Sex sex;
	/** 平台Id */
	private String platId;
	/** 所属平台 */
	private String platName;
	/** 艺人类型 */
	private LineGenres genre;
	/** 原薪资 */
	private BigDecimal sbasicSalary;
	/** 调整薪资 */
	private BigDecimal nbasicSalary;
	/** 原提成 */
	private BigDecimal spushMoney;
	/** 调整提成 */
	private BigDecimal npushMoney;
	/** 调整原因 */
	private String adjustMsg;
	
	/** 调薪对像 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "act_id")
	private Actores actores;
	/** 申请状态 */
	private Adjust adjust = Adjust.UNKNOWN;
	/** 申请人 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private Employee employee;
	/** 备注 */
	private String remarks;
	
	/** 通过时间 */
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date passTime = new Date();

	
	
	
	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
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

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getPlatName() {
		return platName;
	}

	public void setPlatName(String platName) {
		this.platName = platName;
	}

	public LineGenres getGenre() {
		return genre;
	}

	public void setGenre(LineGenres genre) {
		this.genre = genre;
	}

	public BigDecimal getSbasicSalary() {
		return sbasicSalary;
	}

	public void setSbasicSalary(BigDecimal sbasicSalary) {
		this.sbasicSalary = sbasicSalary;
	}

	public BigDecimal getNbasicSalary() {
		return nbasicSalary;
	}

	public void setNbasicSalary(BigDecimal nbasicSalary) {
		this.nbasicSalary = nbasicSalary;
	}

	public BigDecimal getSpushMoney() {
		return spushMoney;
	}

	public void setSpushMoney(BigDecimal spushMoney) {
		this.spushMoney = spushMoney;
	}

	public BigDecimal getNpushMoney() {
		return npushMoney;
	}

	public void setNpushMoney(BigDecimal npushMoney) {
		this.npushMoney = npushMoney;
	}

	public String getAdjustMsg() {
		return adjustMsg;
	}

	public void setAdjustMsg(String adjustMsg) {
		this.adjustMsg = adjustMsg;
	}

	public Adjust getAdjust() {
		return adjust;
	}

	public void setAdjust(Adjust adjust) {
		this.adjust = adjust;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Actores getActores() {
		return actores;
	}

	public void setActores(Actores actores) {
		this.actores = actores;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPlatId() {
		return platId;
	}

	public void setPlatId(String platId) {
		this.platId = platId;
	}
}
