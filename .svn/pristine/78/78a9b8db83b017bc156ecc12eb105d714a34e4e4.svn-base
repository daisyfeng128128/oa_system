package com.baofeng.commons.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * 功能：角色表
 * */
@Entity
@Table(name = "roles")
public class Roles extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -5736041321635050728L;

	/** 角色名称 */
	private String name;
	/** 角色描述 */
	private String described;
	/** 权限详细 */
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rd_id")
	private List<RoleDetails> details = new ArrayList<RoleDetails>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RoleDetails> getDetails() {
		return details;
	}

	public void setDetails(List<RoleDetails> details) {
		this.details = details;
	}

	public String getDescribed() {
		return described;
	}

	public void setDescribed(String described) {
		this.described = described;
	}

}
