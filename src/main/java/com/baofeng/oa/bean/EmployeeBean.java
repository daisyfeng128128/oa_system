package com.baofeng.oa.bean;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat2;

public class EmployeeBean {
	private Integer id;
	/** 性别 */
	private String sex;
	/** 是否艺人 */
	private String actores;
	/** 是否交金*/
	private String jiaojin;
	/** 姓名 */
	private String name;
	/** 姓名拼音 */
	private String namepy;
	/** 艺名 */
	private String aliasname;
	/** 入职介绍人 */
	private String introducer;
	/** 入职日期 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date joinDate;
	/** 转正日期 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date regularDate;
	/** 身份证号 */
	private String idcard;
	/** 身份证扫描件 */
	private String idImage;
	/** 户籍所在地 */
	private String hostRegister;
	/** 生日 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date birth;
	/** 紧急联系人 */
	private String emergencyContact;
	/** 紧急联系人电话 */
	private String emergencyPhone;
	/** 现居地址 */
	private String liveAdress;
	/** 户籍地址 */
	private String hostAddress;
	/** 上级主管 */
	private String empName;
	/** 上级主管ID */
	private Integer empId;
	/** 部门 */
	private String depName;
	/** 部门ID */
	private Integer depId;
	/** 职位 */
	private String posName;
	/** 职位ID */
	private Integer posId;
	/** email */
	private String email;
	/** 电话 */
	private String phone;
	/** qq */
	private String qq;
	/** 类型 */
	private String genrer;
	/** String date */
	private String birthday;
	/** String 入职 */
	private String incomeday;
	/** String 转正 */
	private String outday;
	/** String 员工号 */
	private String num;
	/** 搜索数据表类型 */
	private String loadTable;
	/** 搜索员工表 */
	private String seachEmp;
	/** 搜索员工表艺人 */
	private String seachAct;

	// /////////////////////////////////////////////////////////////////////
	/** 合同到期时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date contractDT;
	/** 合同到期时间 */
	private String contractDT2;
	/** 公积金帐号 */
	private String cpfAccounts;
	/** 户口类型 */
	private String account;
	/** 银行帐号 */
	private String bankCard;
	/** 银行开户地址 */
	private String bankAddress;
	/** 底薪 */
	private Float basicSalary;
	/** 试用期工资 */
	private Float probationSalary;
	/** 转正薪水 */
	private Float salary;
	/** 其他补贴 */
	private Float otherSubsidy;
	/** 公积金基数 */
	private Float PFcardinalNumber = new Float(210);
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
	/** 所在分公司 */
	private String branchs;
	/**  艺人保底*/
	private Float actoresBSalary;
	/**  饭补*/
	private Float foodSubsidyE;
	/**  公司层次的其他补贴*/
	private Float otherSubsidyE;
	/**  岗位津贴*/
	private Float jobSubsidyE;
	/** 是否交社保*/
	private String socialSecurity;
	/** 员工类型 */
	private String employeType;
	/** 提成比例 */
	private String pushMoney;

	public String getPushMoney() {
		return pushMoney;
	}

	public void setPushMoney(String pushMoney) {
		this.pushMoney = pushMoney;
	}

	public String getEmployeType() {
		return employeType;
	}

	public void setEmployeType(String employeType) {
		this.employeType = employeType;
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

	public String getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public String getBranchs() {
		return branchs;
	}

	public void setBranchs(String branchs) {
		this.branchs = branchs;
	}

	public String getHostAddress() {
		return hostAddress;
	}

	public Date getContractDT() {
		return contractDT;
	}

	public String getActores() {
		return actores;
	}

	public String getJiaojin() {
		return jiaojin;
	}

	public void setJiaojin(String jiaojin) {
		this.jiaojin = jiaojin;
	}

	public Float getOtherSubsidy() {
		return otherSubsidy;
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

	public void setOtherSubsidy(Float otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}

	public void setActores(String actores) {
		this.actores = actores;
	}

	public void setContractDT(Date contractDT) {
		this.contractDT = contractDT;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getContractDT2() {
		return contractDT2;
	}

	public void setContractDT2(String contractDT2) {
		this.contractDT2 = contractDT2;
	}

	public String getCpfAccounts() {
		return cpfAccounts;
	}

	public void setCpfAccounts(String cpfAccounts) {
		this.cpfAccounts = cpfAccounts;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public Float getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(Float basicSalary) {
		this.basicSalary = basicSalary;
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

	public String getShareBankAddress() {
		return shareBankAddress;
	}

	public void setShareBankAddress(String shareBankAddress) {
		this.shareBankAddress = shareBankAddress;
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

	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getIncomeday() {
		return incomeday;
	}

	public void setIncomeday(String incomeday) {
		this.incomeday = incomeday;
	}

	public String getOutday() {
		return outday;
	}

	public void setOutday(String outday) {
		this.outday = outday;
	}

	public String getGenrer() {
		return genrer;
	}

	public void setGenrer(String genrer) {
		this.genrer = genrer;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}

	public Integer getPosId() {
		return posId;
	}

	public void setPosId(Integer posId) {
		this.posId = posId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLoadTable() {
		return loadTable;
	}

	public void setLoadTable(String loadTable) {
		this.loadTable = loadTable;
	}

	public String getSeachEmp() {
		return seachEmp;
	}

	public void setSeachEmp(String seachEmp) {
		this.seachEmp = seachEmp;
	}

	public String getSeachAct() {
		return seachAct;
	}

	public void setSeachAct(String seachAct) {
		this.seachAct = seachAct;
	}

}
