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
import com.baofeng.oa.entity.BaseEnums.DepPass;
import com.baofeng.oa.entity.BaseEnums.Punishment;
import com.baofeng.utils.CustomDateSerializerFormat2;

/**
 * 功能：违纪处理
 * */
@Entity
@Table(name = "disciplinary")
public class Disciplinary extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 处罚人姓名 */
	private String name;
	/** 艺名 */
	private String aliasname;
	/** 员工号 */
	private Integer num;
	/** 处罚时间 */
	private Date applyDT;
	/** 职位 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "pos_id")
	private Positions position;
	/** 部门 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "dep_id")
	private Department depart;
	/** 申请人 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private Employee employee;
	/** 处罚金额 */
	private Float disciplinary = new Float(0.00);
	/** 制单人 */
	private String fname;
	/** 处罚事项 */
	private String punishmentItems;
	/** 处罚备注 */
	private String punishRemarks;
	/** 交款状态 */
	private Punishment punishment = Punishment.FAILED;
	/** 交款形式 */
	private String punishmentType;
	/** 缴纳金额*/
	private Float punishMoney = new Float(0.00);
	/** 剩余金额*/
	private Float residualMoney = new Float(0.00);
	public Float getResidualMoney() {
		return residualMoney;
	}
	public void setResidualMoney(Float residualMoney) {
		this.residualMoney = residualMoney;
	}
	/** 交钱完成时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date completeDT;
	/** 缴纳人 */
	private String payPerson;
	/** 收款人 */
	private String payee;
	/** 交款备注*/
	private String payRemarks;
	/** 部门确认*/
	private DepPass depPass = DepPass.FAILED;
	/** 拒绝信息*/
	private String rejecrMessage;
	
	private Integer process;

	
	public Integer getProcess() {
		return process;
	}
	public void setProcess(Integer process) {
		this.process = process;
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Date getApplyDT() {
		return applyDT;
	}
	public void setApplyDT(Date applyDT) {
		this.applyDT = applyDT;
	}
	public Positions getPosition() {
		return position;
	}
	public void setPosition(Positions position) {
		this.position = position;
	}
	public Department getDepart() {
		return depart;
	}
	public void setDepart(Department depart) {
		this.depart = depart;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Float getDisciplinary() {
		return disciplinary;
	}
	public void setDisciplinary(Float disciplinary) {
		this.disciplinary = disciplinary;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getPunishmentItems() {
		return punishmentItems;
	}
	public void setPunishmentItems(String punishmentItems) {
		this.punishmentItems = punishmentItems;
	}
	public String getPunishRemarks() {
		return punishRemarks;
	}
	public void setPunishRemarks(String punishRemarks) {
		this.punishRemarks = punishRemarks;
	}
	public Punishment getPunishment() {
		return punishment;
	}
	public void setPunishment(Punishment punishment) {
		this.punishment = punishment;
	}
	public String getPunishmentType() {
		return punishmentType;
	}
	public void setPunishmentType(String punishmentType) {
		this.punishmentType = punishmentType;
	}
	public Float getPunishMoney() {
		return punishMoney;
	}
	public void setPunishMoney(Float punishMoney) {
		this.punishMoney = punishMoney;
	}
	public Date getCompleteDT() {
		return completeDT;
	}
	public void setCompleteDT(Date completeDT) {
		this.completeDT = completeDT;
	}
	public String getPayPerson() {
		return payPerson;
	}
	public void setPayPerson(String payPerson) {
		this.payPerson = payPerson;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getPayRemarks() {
		return payRemarks;
	}
	public void setPayRemarks(String payRemarks) {
		this.payRemarks = payRemarks;
	}
	public DepPass getDepPass() {
		return depPass;
	}
	public void setDepPass(DepPass depPass) {
		this.depPass = depPass;
	}
	public String getRejecrMessage() {
		return rejecrMessage;
	}
	public void setRejecrMessage(String rejecrMessage) {
		this.rejecrMessage = rejecrMessage;
	}
	

}
