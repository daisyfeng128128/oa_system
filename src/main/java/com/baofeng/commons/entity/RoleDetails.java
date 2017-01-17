package com.baofeng.commons.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * 功能：角色权限详细
 * */
@Entity
@Table(name = "roleDetails")
public class RoleDetails extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -8549832189682469167L;

	/** 菜单Id */
	@Transient
	private Integer itemId;
	/** 访问地址 */
	private String itemUrl;
	/** 角色 */
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rd_id")
	private Roles role;
	/** 页面权限 */
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "prd_id")
	private List<RoleDetailsAtts> details = new ArrayList<RoleDetailsAtts>();

	public RoleDetails() {
		super();
	}

	public RoleDetails(Integer itemId, String itemUrl, Roles role, List<RoleDetailsAtts> details) {
		super();
		this.itemId = itemId;
		this.itemUrl = itemUrl;
		this.role = role;
		this.details = details;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public String getItemUrl() {
		return itemUrl;
	}

	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}

	public List<RoleDetailsAtts> getDetails() {
		return details;
	}

	public void setDetails(List<RoleDetailsAtts> details) {
		this.details = details;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
}
