package com.baofeng.commons.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 功能：角色用户
 * */
@Entity
@Table(name = "roleUsers")
public class RoleUsers extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -2386696082419954700L;
	/** 用户 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Operator user;
	/** 角色 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "roleUsers_details", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Roles> details = new HashSet<Roles>();

	public Operator getUser() {
		return user;
	}

	public void setUser(Operator user) {
		this.user = user;
	}

	public Set<Roles> getDetails() {
		return details;
	}

	public void setDetails(Set<Roles> details) {
		this.details = details;
	}
}
