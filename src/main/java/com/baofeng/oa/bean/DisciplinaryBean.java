package com.baofeng.oa.bean;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat2;

/**
 *   功能 :违纪处理
 */
public class DisciplinaryBean implements Serializable{

	private static final long serialVersionUID = 1L;
	/** 处罚人姓名 */
	private String name;
	/** 艺名 */
	private String aliasname;
	/** 员工号 */
	private Integer num;
	/** 处罚时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date applyDT;
	/** 部门 */
	private String depName;
	/** 部门ID */
	private Integer depId;
	/** 职位 */
	private String posName;
	/** 职位ID */
	private Integer posId;
	/** 处罚金额 */
	private Float disciplinary;
	/** 制单人 */
	private String fname;
	/** 处罚事项 */
	private String punishmentItems;
	/** 处罚备注 */
	private String punishRemarks;
	/** 交款状态 */
	private String punishment;
	/** 交款形式 */
	private String punishmentType;
	/** 缴纳金额*/
	private Float punishMoney;
	
	private Float residualMoney;
	
	private Integer process;

	public Integer getProcess() {
		return process;
	}
	public void setProcess(Integer process) {
		this.process = process;
	}
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
	private String depPass;
	/** 拒绝信息*/
	private String rejecrMessage;
	private String id1;
	
	
	public String getId1() {
		return id1;
	}
	public void setId1(String id1) {
		this.id1 = id1;
	}
	public String getRejecrMessage() {
		return rejecrMessage;
	}
	public void setRejecrMessage(String rejecrMessage) {
		this.rejecrMessage = rejecrMessage;
	}
	private Integer id;
	private Integer ids;
	
	public Integer getIds() {
		return ids;
	}
	public void setIds(Integer ids) {
		this.ids = ids;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public Integer getDepId() {
		return depId;
	}
	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	public String getPosName() {
		return posName;
	}
	public void setPosName(String posName) {
		this.posName = posName;
	}
	public Integer getPosId() {
		return posId;
	}
	public void setPosId(Integer posId) {
		this.posId = posId;
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
	public String getPunishment() {
		return punishment;
	}
	public void setPunishment(String punishment) {
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
	public String getDepPass() {
		return depPass;
	}
	public void setDepPass(String depPass) {
		this.depPass = depPass;
	}
	
}
