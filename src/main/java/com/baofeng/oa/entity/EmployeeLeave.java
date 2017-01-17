package com.baofeng.oa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.utils.CustomDateSerializerFormat2;

/**
 * 功能：员工离职表
 * */
@Entity
@Table(name = "employeeLeave")
public class EmployeeLeave extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 2799859778046314904L;
	/** 离职原因 */
	@Lob
	private String leaveMsg;
	/**最后状态*/
	private Genres genrer;
	/** 离职日期 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date leaveTime;
	/** 员工档案 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_id")
	private Employee employee;
 
	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getLeaveMsg() {
		return leaveMsg;
	}

	public void setLeaveMsg(String leaveMsg) {
		this.leaveMsg = leaveMsg;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Genres getGenrer() {
		return genrer;
	}

	public void setGenrer(Genres genrer) {
		this.genrer = genrer;
	}

}
