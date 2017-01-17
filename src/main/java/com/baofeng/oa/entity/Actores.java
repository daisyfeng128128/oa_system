package com.baofeng.oa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.ActoresSigned;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.oa.entity.BaseEnums.Settlement;
import com.baofeng.utils.CustomDateSerializerFormat2;

/**
 * 功能：艺人基础表
 * */
@Entity
@Table(name = "actores")
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class Actores extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -5030240164164579598L;

	/** 性别 */
	private Sex sex;
	/** 员工号 */
	private Integer number;
	/** 真实姓名 */
	private String realname;
	/** 艺名 */
	private String aliasname;
	/** 联系地址 */
	private String address;
	/** 身份证号码 */
	private String idcard;
	/** 身份证扫描件 */
	private String idImage;
	/** 艺人类型 */
	private LineGenres genre;
	/** 联系电话 */
	private String phone;
	/** QQ */
	private String qq;
	/** 电子邮件 */
	private String email;
	/** 状态 */
	private Genres genrer = Genres.SHIYONG;
	/** 入职时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date entryTime = new Date();
	/** 保底工资 */
	@Column(precision = 8, scale = 2)
	private Float minimumGuarantee = new Float(0);
	/** 试用工资 */
	@Column(precision = 8, scale = 2)
	private Float probationSalary = new Float(0);
	/** 正式工资 */
	@Column(precision = 8, scale = 2)
	private Float basicSalary = new Float(0);
	/** 试用期 */
	private Integer probation = new Integer(0);
	/** 经济签约 */
	private ActoresSigned signed = ActoresSigned.NO;
	/** 劳务员工 */
	private ActoresLabour labour = ActoresLabour.OTHER;
	/** 员工关联 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private Employee employee;
	/** 提成比例 */
	private Integer pushMoney = 0;
	/** 结算方式 */
	private Settlement settl;
	/** 分成银行 */
	private String shareBankCard;
	/** 分成银行开户地址 */
	private String shareBankAddress;
	/** 银行帐号 */
	private String bankCard;
	/** 银行开户地址 */
	private String bankAddress;
	/** 视频链接 */
	private String linkUrl;

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
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

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getAliasname() {
		return aliasname;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
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

	public LineGenres getGenre() {
		return genre;
	}

	public void setGenre(LineGenres genre) {
		this.genre = genre;
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

	public Genres getGenrer() {
		return genrer;
	}

	public void setGenrer(Genres genrer) {
		this.genrer = genrer;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public Float getMinimumGuarantee() {
		return minimumGuarantee;
	}

	public void setMinimumGuarantee(Float minimumGuarantee) {
		this.minimumGuarantee = minimumGuarantee;
	}

	public Float getProbationSalary() {
		return probationSalary;
	}

	public void setProbationSalary(Float probationSalary) {
		this.probationSalary = probationSalary;
	}

	public Float getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(Float basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Integer getProbation() {
		return probation;
	}

	public void setProbation(Integer probation) {
		this.probation = probation;
	}

	public ActoresSigned getSigned() {
		return signed;
	}

	public void setSigned(ActoresSigned signed) {
		this.signed = signed;
	}

	public ActoresLabour getLabour() {
		return labour;
	}

	public void setLabour(ActoresLabour labour) {
		this.labour = labour;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getPushMoney() {
		return pushMoney;
	}

	public void setPushMoney(Integer pushMoney) {
		this.pushMoney = pushMoney;
	}

	public Settlement getSettl() {
		return settl;
	}

	public void setSettl(Settlement settl) {
		this.settl = settl;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
}
