package com.baofeng.oa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.commons.entity.RoleUsers;
import com.baofeng.commons.entity.Roles;
import com.baofeng.oa.entity.BaseEnums.Overdue;

/**
 * 功能：分公司登陆表
 * */
@Entity
@Table(name = "branchUser")
public class BranchUser extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 员工号 */
	private Integer number;
	/** 名字 */
	private String name;
	/** 联系电话 */
	private String phone;
	/** 电子邮件 */
	private String email;
	/** 所在子公司 */
	private String ownBranchs;
	/** 目标子公司 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "tarBranchs_id")
	private BranchOffice tarBranchs;
	/** 部门 */
	private String depart;
	/** 职位 */
	private String positions;
	/** 角色权限 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "branchUsers_details", joinColumns = @JoinColumn(name = "branch_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Roles> details = new HashSet<Roles>();
	/** 角色权限 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "roles_id")
	private Roles role;
	/** 账号状态 yes: 过期 no： 正常 */
	private Overdue overdues = Overdue.NO;
	/** 结束时间 */
	private Date endTime;
	/** 关联员工 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "roleUser_id")
	private RoleUsers user;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getOwnBranchs() {
		return ownBranchs;
	}

	public void setOwnBranchs(String ownBranchs) {
		this.ownBranchs = ownBranchs;
	}

	public BranchOffice getTarBranchs() {
		return tarBranchs;
	}

	public void setTarBranchs(BranchOffice tarBranchs) {
		this.tarBranchs = tarBranchs;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getPositions() {
		return positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

	public Overdue getOverdues() {
		return overdues;
	}

	public void setOverdues(Overdue overdues) {
		this.overdues = overdues;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public RoleUsers getUser() {
		return user;
	}

	public void setUser(RoleUsers user) {
		this.user = user;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public Set<Roles> getDetails() {
		return details;
	}

	public void setDetails(Set<Roles> details) {
		this.details = details;
	}

}
