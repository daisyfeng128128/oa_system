package com.baofeng.oa.bean;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat1;
import com.baofeng.utils.CustomDateSerializerFormat2;

/**
 * 功能：艺人数据
 * */
@SuppressWarnings("serial")
public class ActoresBean implements Serializable {

	private Integer id;
	/** 性别 */
	private String sex;
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
	private String genre;
	/** 联系电话 */
	private String phone;
	/** qq */
	private String qq;
	/** 电子邮件 */
	private String email;
	/** 状态 */
	private String genrer;
	/** 入职时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date entryTime = new Date();
	/** 开播时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date beLiveTime = new Date();
	/** 保底工资 */
	private Float minimumGuarantee;
	/** 试用期工资 */
	private Float probationSalary;
	/** 底薪 */
	private Float basicSalary;
	/** 试用期 */
	private Integer probation;
	/** 转正状态 */
	private String regular;
	/** 经济签约 */
	private String signed;
	/** 劳务员工 */
	private String labour;
	/**  平台类型*/
	private String formGenre;
	/** 提成比例 */
	private Integer pushMoney;
	/** 结算方式 */
	private String settl;
	private String loadTable;
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date createDT;

	@Override
	public int hashCode() {
		if (number != null)
			return number.hashCode() + 37;
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ActoresBean) {
			ActoresBean bean = (ActoresBean) obj;
			return bean.getNumber() == bean.getNumber();
		}
		return super.equals(obj);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
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

	public String getGenrer() {
		return genrer;
	}

	public void setGenrer(String genrer) {
		this.genrer = genrer;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public Date getBeLiveTime() {
		return beLiveTime;
	}

	public void setBeLiveTime(Date beLiveTime) {
		this.beLiveTime = beLiveTime;
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

	public String getRegular() {
		return regular;
	}

	public void setRegular(String regular) {
		this.regular = regular;
	}

	public String getSigned() {
		return signed;
	}

	public void setSigned(String signed) {
		this.signed = signed;
	}

	public String getLabour() {
		return labour;
	}

	public void setLabour(String labour) {
		this.labour = labour;
	}

	public Integer getPushMoney() {
		return pushMoney;
	}

	public void setPushMoney(Integer pushMoney) {
		this.pushMoney = pushMoney;
	}

	public String getSettl() {
		return settl;
	}

	public void setSettl(String settl) {
		this.settl = settl;
	}

	public String getLoadTable() {
		return loadTable;
	}

	public void setLoadTable(String loadTable) {
		this.loadTable = loadTable;
	}

	public Date getCreateDT() {
		return createDT;
	}

	public void setCreateDT(Date createDT) {
		this.createDT = createDT;
	}

	public String getFormGenre() {
		return formGenre;
	}

	public void setFormGenre(String formGenre) {
		this.formGenre = formGenre;
	}
}
