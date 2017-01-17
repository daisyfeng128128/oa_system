package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import com.baofeng.commons.entity.Operator;
import com.baofeng.oa.entity.BaseEnums.Account;
import com.baofeng.oa.entity.BaseEnums.EmployeType;

/**
 * 功能：员工表
 * */
@Entity
@Table(name = "employee")
@JsonIgnoreProperties({ "supervisor", "depart" })
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class Employee extends Operator implements Serializable {

	private static final long serialVersionUID = 8818416668526131290L;

	/** 姓名 */
	private String name;
	/** 姓名拼音 */
	private String namepy;
	/** 艺名 */
	private String aliasname;
	/** 入职介绍人 */
	private String introducer;
	/** 入职日期 */
	private Date joinDate;
	/** 转正日期 */
	private Date regularDate;
	/** 离职日期 */
	private Date leaveTime;
	/** 身份证号 */
	private String idcard;
	/** 身份证扫描件 */
	private String idImage;
	/** 户籍所在地 */
	private String hostRegister;
	/** 生日 */
	private Date birth;
	/** 紧急联系人 */
	private String emergencyContact;
	/** 紧急联系人电话 */
	private String emergencyPhone;
	/** 现居地址 */
	private String liveAdress;
	/** 户籍地址 */
	private String hostAddress;
	/** 职位 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "pos_id")
	private Positions position;
	/** 部门 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "dep_id")
	private Department depart;
	/** 上级主管 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "super_id")
	private Employee supervisor;

	// /////////////////////////////////////////////////////////////////////
	/** 合同到期时间 */
	private Date contractDT;
	/** 户口类型 */
	private Account account = Account.CHENGZHEN;
	/** 员工类型 */
	private EmployeType employeType = EmployeType.EMPLOYEE;
	/** 银行帐号 */
	private String bankCard;
	/** 银行开户地址 */
	private String bankAddress;
	/** 试用补贴 */
	private Float probationSalary = new Float(0.00);
	/** 转正补贴 */
	private Float otherSubsidy = new Float(0.00);
	/** 基本工资 */
	private Float salary = new Float(0.00);
	/** 公积金帐号 */
	private String cpfAccounts;
	/** 公积金基数 */
	private Float PFcardinalNumber = new Float(3000);
	/** 社保基数 */
	private Float SScardinalNumber = new Float(3271);
	/** 分成银行 */
	private String shareBankCard;
	/** 分成银行开户地址 */
	private String shareBankAddress;
	/** 特长、技能 */
	private String speciality;
	/** 备注 */
	private String remarks;
	/** 提成 */
	private BigDecimal pushMoney = new BigDecimal(0.00);
	/** 艺人保底 */
	private Float actoresBSalary = new Float(0.00);
	/** 饭补 */
	private Float foodSubsidyE = new Float(0.00);
	/** 其他补贴 */
	private Float otherSubsidyE = new Float(0.00);
	/** 岗位津贴 */
	private Float jobSubsidyE = new Float(0.00);
	/** 是否交公积金 0 不交 1 交 */
	private Integer jiaojin = 0;
	/** 是否交社保 0 不交 1 交 */
	private Integer socialSecurity = 0;

	public Integer getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(Integer socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public Float getActoresBSalary() {
		return actoresBSalary;
	}

	public void setActoresBSalary(Float actoresBSalary) {
		this.actoresBSalary = actoresBSalary;
	}

	public Float getFoodSubsidyE() {
		return foodSubsidyE;
	}

	public void setFoodSubsidyE(Float foodSubsidyE) {
		this.foodSubsidyE = foodSubsidyE;
	}

	public Float getOtherSubsidyE() {
		return otherSubsidyE;
	}

	public void setOtherSubsidyE(Float otherSubsidyE) {
		this.otherSubsidyE = otherSubsidyE;
	}

	public Float getJobSubsidyE() {
		return jobSubsidyE;
	}

	public void setJobSubsidyE(Float jobSubsidyE) {
		this.jobSubsidyE = jobSubsidyE;
	}

	public EmployeType getEmployeType() {
		return employeType;
	}

	public void setEmployeType(EmployeType employeType) {
		this.employeType = employeType;
	}

	public BigDecimal getPushMoney() {
		return pushMoney;
	}

	public void setPushMoney(BigDecimal pushMoney) {
		this.pushMoney = pushMoney;
	}

	public Integer getJiaojin() {
		return jiaojin;
	}

	public void setJiaojin(Integer jiaojin) {
		this.jiaojin = jiaojin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamepy() {
		return namepy;
	}

	public void setNamepy(String namepy) {
		this.namepy = namepy;
	}

	public String getAliasname() {
		return aliasname;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}

	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getRegularDate() {
		return regularDate;
	}

	public void setRegularDate(Date regularDate) {
		this.regularDate = regularDate;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getIdImage() {
		return idImage;
	}

	public void setIdImage(String idImage) {
		this.idImage = idImage;
	}

	public String getHostRegister() {
		return hostRegister;
	}

	public void setHostRegister(String hostRegister) {
		this.hostRegister = hostRegister;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getLiveAdress() {
		return liveAdress;
	}

	public void setLiveAdress(String liveAdress) {
		this.liveAdress = liveAdress;
	}

	public String getHostAddress() {
		return hostAddress;
	}

	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
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

	public Employee getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
	}

	public Date getContractDT() {
		return contractDT;
	}

	public void setContractDT(Date contractDT) {
		this.contractDT = contractDT;
	}

	public String getCpfAccounts() {
		return cpfAccounts;
	}

	public void setCpfAccounts(String cpfAccounts) {
		this.cpfAccounts = cpfAccounts;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public Float getProbationSalary() {
		return probationSalary;
	}

	public void setProbationSalary(Float probationSalary) {
		this.probationSalary = probationSalary;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public String getShareBankCard() {
		return shareBankCard;
	}

	public void setShareBankCard(String shareBankCard) {
		this.shareBankCard = shareBankCard;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getShareBankAddress() {
		return shareBankAddress;
	}

	public void setShareBankAddress(String shareBankAddress) {
		this.shareBankAddress = shareBankAddress;
	}

	public Float getOtherSubsidy() {
		return otherSubsidy;
	}

	public void setOtherSubsidy(Float otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	public Float getPFcardinalNumber() {
		return PFcardinalNumber;
	}

	public void setPFcardinalNumber(Float pFcardinalNumber) {
		PFcardinalNumber = pFcardinalNumber;
	}

	public Float getSScardinalNumber() {
		return SScardinalNumber;
	}

	public void setSScardinalNumber(Float sScardinalNumber) {
		SScardinalNumber = sScardinalNumber;
	}
}
