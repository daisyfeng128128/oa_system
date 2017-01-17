package com.baofeng.commons.bean;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat1;

/**
 * 功能：帐号与权限
 * */
@SuppressWarnings("serial")
public class RoleUsersBean implements Serializable {

	private String id;
	/** 登录帐号 */
	private String accounts;
	/** 联系电话 */
	private String phone;
	/** 电子邮件 */
	private String email;
	/** 角色名称 */
	private String roles;
	/** 帐号状态 */
	private String status;
	/** 用户Id */
	private String userId;
	/** 权限Id */
	private String roleId;
	/** 创建时间 */
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date createDT;
	/** 新密码 */
	private String pwd;
	/** 员工号 */
	private String num;
	

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Date getCreateDT() {
		return createDT;
	}

	public void setCreateDT(Date createDT) {
		this.createDT = createDT;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
