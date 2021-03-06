package com.baofeng.oa.bean;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat1;
import com.baofeng.utils.CustomDateSerializerFormat2;

public class ManagersBean implements Serializable {
	private static final long serialVersionUID = 6996668120011775816L;

	private Integer id;
	/** 员工号 */
	private String number;
	/** 艺名 */
	private String aliasname;
	/** 真实姓名 */
	private String realname;
	/** 性别 */
	private String sex;
	/** 现居地址 */
	private String address;
	/** 身份证号码 */
	private String idcard;
	/** 身份证扫描件 */
	private String idImage;
	/** 类型(线上,线下) */
	private String genre;
	/** 管理岗位 */
	private String positions;
	/** 入职时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date entryTime = new Date();
	/** 试用期 */
	private String probation;
	/** 试用期工资 */
	private String probationSalary;
	/** 转正 */
	private String genrer;
	/** 底薪 */
	private String basicSalary;
	/** 餐补 */
	private String foodSubsidy;
	/** 其他补贴 */
	private String otherSubsidy;
	/** 联系电话 */
	private String phone;
	/** 联系QQ */
	private String qq;
	/** 离职原因 */
	private String leaveReasons;
	/** 工资卡卡号 */
	private String bankCard;
	/** 开户行 */
	private String bankCardAccount;
	/** 创建时间 */
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date createDT;
	/** 员工ID */
	private String empid;
	

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAliasname() {
		return aliasname;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPositions() {
		return positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public String getProbation() {
		return probation;
	}

	public void setProbation(String probation) {
		this.probation = probation;
	}

	public String getProbationSalary() {
		return probationSalary;
	}

	public void setProbationSalary(String probationSalary) {
		this.probationSalary = probationSalary;
	}

	public String getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}

	public String getFoodSubsidy() {
		return foodSubsidy;
	}

	public void setFoodSubsidy(String foodSubsidy) {
		this.foodSubsidy = foodSubsidy;
	}

	public String getOtherSubsidy() {
		return otherSubsidy;
	}

	public void setOtherSubsidy(String otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}

	public Date getCreateDT() {
		return createDT;
	}

	public void setCreateDT(Date createDT) {
		this.createDT = createDT;
	}

	public String getGenrer() {
		return genrer;
	}

	public void setGenrer(String genrer) {
		this.genrer = genrer;
	}

	public String getLeaveReasons() {
		return leaveReasons;
	}

	public void setLeaveReasons(String leaveReasons) {
		this.leaveReasons = leaveReasons;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankCardAccount() {
		return bankCardAccount;
	}

	public void setBankCardAccount(String bankCardAccount) {
		this.bankCardAccount = bankCardAccount;
	}
}
