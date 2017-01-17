package com.baofeng.oa.bean;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat1;

public class EmpTransferBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String number;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	/** 真实姓名 */
	private String name;
	/** 艺名 */
	private String aliasname;
	/** 原部门 */
	private String oldDepart;
	/** 原职位 */
	private String oldPosition;
	/** 上级主管 */
	private String empName;
	/** 原部门 */
	private String oldDepartName;
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
	/** 原职位 */
	private String oldPositionName;
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getPosName() {
		return posName;
	}
	public void setPosName(String posName) {
		this.posName = posName;
	}

	/** 上级主管ID */
	private String empId;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getPosId() {
		return posId;
	}
	public void setPosId(String posId) {
		this.posId = posId;
	}
	/** 部门 */
	private String depName;
	/** 部门ID */
	private String depId;
	/** 职位 */
	private String posName;
	/** 职位ID */
	private String posId;
	/** 员工类型*/
	private String employeType;
	/** 创建人*/
	private String createName;
	/** 申请状态 */
	private String empTransfer;
	/** 转职日期 */
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date transferDate;
	/** 新部门 */
	private String newDepartName;
	/** 新职位 */
	private String newPositionName;
	/** 上级主管姓名 */
	private String supervisorName;
	
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
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
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
	public String getOldDepart() {
		return oldDepart;
	}
	public void setOldDepart(String oldDepart) {
		this.oldDepart = oldDepart;
	}
	public String getOldPosition() {
		return oldPosition;
	}
	public void setOldPosition(String oldPosition) {
		this.oldPosition = oldPosition;
	}

	public String getEmployeType() {
		return employeType;
	}
	public void setEmployeType(String employeType) {
		this.employeType = employeType;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getEmpTransfer() {
		return empTransfer;
	}
	public void setEmpTransfer(String empTransfer) {
		this.empTransfer = empTransfer;
	}
	public Date getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	
}
