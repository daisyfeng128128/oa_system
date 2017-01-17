package com.baofeng.oa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.oa.entity.BaseEnums.EmpTransfer;
import com.baofeng.oa.entity.BaseEnums.EmployeType;
import com.baofeng.utils.CustomDateSerializerFormat1;

/**
 * 功能：员工转岗表
 * */
@Entity
@Table(name = "employeeTransfer")
public class EmployeeTransfer extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 员工号 */
	private Integer number;
	/** 姓名 */
	private String name;
	/** 艺名 */
	private String aliasname;
	/** 转岗日期 */
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date transferDate;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "oldep_id")
	private Department oldDepart;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "oldpos_id")
	private Positions oldPosition;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "pos_id")
	private Positions newPosition;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "dep_id")
	private Department newDepart;
	/** 上级主管 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "super_id")
	private Employee supervisor;
	/** 员工类型 */
	private EmployeType employeType = EmployeType.EMPLOYEE;
	/** 创建人 */
	private String createName;
	/** 申请状态 */
	private EmpTransfer empTransfer = EmpTransfer.UNKNOWN;
	/** 员工关联 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private Employee employee;
	/** 原部门 */
	private String oldDepartName;
	/** 原职位 */
	private String oldPositionName;
	/** 新部门 */
	private String newDepartName;
	/** 新职位 */
	private String newPositionName;
	/** 上级主管姓名 */
	private String supervisorName;

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getNewDepartName() {
		return newDepartName;
	}

	public void setNewDepartName(String newDepartName) {
		this.newDepartName = newDepartName;
	}

	public String getNewPositionName() {
		return newPositionName;
	}

	public void setNewPositionName(String newPositionName) {
		this.newPositionName = newPositionName;
	}

	public String getOldDepartName() {
		return oldDepartName;
	}

	public void setOldDepartName(String oldDepartName) {
		this.oldDepartName = oldDepartName;
	}

	public String getOldPositionName() {
		return oldPositionName;
	}

	public void setOldPositionName(String oldPositionName) {
		this.oldPositionName = oldPositionName;
	}

	public Employee getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public EmpTransfer getEmpTransfer() {
		return empTransfer;
	}

	public void setEmpTransfer(EmpTransfer empTransfer) {
		this.empTransfer = empTransfer;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

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

	public String getAliasname() {
		return aliasname;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public Department getOldDepart() {
		return oldDepart;
	}

	public void setOldDepart(Department oldDepart) {
		this.oldDepart = oldDepart;
	}

	public Positions getOldPosition() {
		return oldPosition;
	}

	public void setOldPosition(Positions oldPosition) {
		this.oldPosition = oldPosition;
	}

	public Positions getNewPosition() {
		return newPosition;
	}

	public void setNewPosition(Positions newPosition) {
		this.newPosition = newPosition;
	}

	public Department getNewDepart() {
		return newDepart;
	}

	public void setNewDepart(Department newDepart) {
		this.newDepart = newDepart;
	}

	public EmployeType getEmployeType() {
		return employeType;
	}

	public void setEmployeType(EmployeType employeType) {
		this.employeType = employeType;
	}

}
