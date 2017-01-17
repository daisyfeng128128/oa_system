package com.baofeng.commons.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * 功能：员工用户
 * */
@Entity
@Table(name = "operator")
@Inheritance(strategy = InheritanceType.JOINED)
public class Operator extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -1655312026015533684L;

	public enum Genres {
		SHIYONG, ZHENGSHI, LIZHI, MANAGER, SUPERS, PENDING, TRANSFER
	}

	public enum Sex {
		WOMAN, MAN, OTHER
	}

	public enum Login {
		YES, NO
	}

	public enum Actores {
		YES, NO
	}

	/** 员工号 */
	private Integer number;
	/** 登录帐号 */
	private String accounts;
	/** 登录密码 */
	private String password;
	/** 联系电话 */
	private String phone;
	/** QQ */
	private String qq;
	/** 电子邮件 */
	private String email;
	/** 艺人 */
	private Actores actores = Actores.NO;
	/** 状态 */
	private Genres genrer = Operator.Genres.SHIYONG;
	/** 性别 */
	private Sex sex;
	/** 允许登陆 */
	private Login islogin = Operator.Login.NO;

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Genres getGenrer() {
		return genrer;
	}

	public void setGenrer(Genres genrer) {
		this.genrer = genrer;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Login getIslogin() {
		return islogin;
	}

	public void setIslogin(Login islogin) {
		this.islogin = islogin;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Actores getActores() {
		return actores;
	}

	public void setActores(Actores actores) {
		this.actores = actores;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
}
