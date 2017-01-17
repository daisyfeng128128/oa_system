package com.baofeng.oa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseEntity;

/**
 * 功能：子公司
 * */
@Entity
@Table(name = "branchOffice")
@Inheritance(strategy = InheritanceType.JOINED)
public class BranchOffice extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -3972280907811932497L;

	@Id
	@Column(length = 32)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/** 公司名称 */
	private String name;
	/** 公司简称 */
	private String ipgp;
	/** 员工前缀 */
	private String numberHead;
	/** 联系电话 */
	private String telPhone;
	/** 公司地址 */
	private String address;
	/** 电子邮箱 */
	private String email;
	/** 是否登陆 0 不能登录 1登录*/
	private Integer islogin=0;
	/** 社保基数 */
	@Column(length = 1000)
	private String socialsecurity;
	
	public String getSocialsecurity() {
		return socialsecurity;
	}

	public void setSocialsecurity(String socialsecurity) {
		this.socialsecurity = socialsecurity;
	}

	public String getIpgp() {
		return ipgp;
	}

	public void setIpgp(String ipgp) {
		this.ipgp = ipgp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumberHead() {
		return numberHead;
	}

	public void setNumberHead(String numberHead) {
		this.numberHead = numberHead;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIslogin() {
		return islogin;
	}

	public void setIslogin(Integer islogin) {
		this.islogin = islogin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
