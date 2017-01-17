package com.baofeng.commons.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;

/**
 * 功能：页面权限
 * */
@Entity
@Table(name = "roleDetailsAtts")
public class RoleDetailsAtts extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -7534411042909475792L;

	public enum Enabled {
		NO, YES
	}

	/** 菜单页面权限Id */
	@Transient
	private Integer itemMId;
	/** 操作名称 */
	private String opName;
	/** 操作键值 */
	private String opkey;
	/** 操作权限 */
	private Enabled views = Enabled.YES;
	/** 角色页面 */
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "prd_id")
	private RoleDetails roleDetails;

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public String getOpkey() {
		return opkey;
	}

	public void setOpkey(String opkey) {
		this.opkey = opkey;
	}

	public RoleDetails getRoleDetails() {
		return roleDetails;
	}

	public void setRoleDetails(RoleDetails roleDetails) {
		this.roleDetails = roleDetails;
	}

	public Integer getItemMId() {
		return itemMId;
	}

	public void setItemMId(Integer itemMId) {
		this.itemMId = itemMId;
	}

	public Enabled getViews() {
		return views;
	}

	public void setViews(Enabled views) {
		this.views = views;
	}
}
