package com.baofeng.oa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.oa.entity.BaseEnums.LineGenres;

/**
 * 星探表
 * */
@Entity
@Table(name = "talent")
public class Talent extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 姓名 */
	private String name;
	/** 员工号 */
	private String number;
	/** 艺名 */
	private String aliasname;
	/** 性别 */
	private Sex sex;
	/** 联系电话 */
	private String phone;
	/** QQ */
	private String qq;
	/** 工资卡卡号 */
	private String bankCard;
	/** 工资卡开户地址 */
	private String bankAddress;
	/** 线上/线下 */
	private LineGenres genrer;
	/** 状态 */
	private Genres tgenres = Genres.SHIYONG;
	/** 员工关联 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private Employee employee;

	public LineGenres getGenrer() {
		return genrer;
	}

	public void setGenrer(LineGenres genrer) {
		this.genrer = genrer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Genres getTgenres() {
		return tgenres;
	}

	public void setTgenres(Genres tgenres) {
		this.tgenres = tgenres;
	}
}
