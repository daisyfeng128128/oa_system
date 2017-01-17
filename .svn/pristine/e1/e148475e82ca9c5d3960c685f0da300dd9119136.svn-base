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
import com.baofeng.oa.entity.BaseEnums.Advance;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BaseEnums.Repay;
import com.baofeng.utils.CustomDateSerializerFormat1;


/**
 * 功能：预支薪资(艺人)
 * */
@Entity
@Table(name = "advanceSalary")
public class AdvanceSalary extends BaseInteger implements Serializable{
	
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
	/** 借支金额*/
	private BigDecimal advanceSalary;
	
	/** 借支对像 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "act_id")
	private Actores actores;
	/** 申请状态 */
	private Advance advance = Advance.UNKNOWN;
	
	/**还款金额*/
	private BigDecimal RepaySalary;
	/** 还款状态 */
	private  Repay repay = Repay.NOTPASS;
	
	/** 申请人 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private Employee employee;
	/** 备注 */
	private String remarks;
	
	/** 通过时间 */
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date passTime;
	/** 还款时间 */
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date repayTime;
	

	public Date getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
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

	public String getPlatId() {
		return platId;
	}

	public void setPlatId(String platId) {
		this.platId = platId;
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

	public BigDecimal getAdvanceSalary() {
		return advanceSalary;
	}

	public void setAdvanceSalary(BigDecimal advanceSalary) {
		this.advanceSalary = advanceSalary;
	}

	public Actores getActores() {
		return actores;
	}

	public void setActores(Actores actores) {
		this.actores = actores;
	}

	public Advance getAdvance() {
		return advance;
	}

	public void setAdvance(Advance advance) {
		this.advance = advance;
	}

	public BigDecimal getRepaySalary() {
		return RepaySalary;
	}

	public void setRepaySalary(BigDecimal repaySalary) {
		RepaySalary = repaySalary;
	}

	public Repay getRepay() {
		return repay;
	}

	public void setRepay(Repay repay) {
		this.repay = repay;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	
}
