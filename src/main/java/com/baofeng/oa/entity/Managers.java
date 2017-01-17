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
import com.baofeng.commons.entity.Operator;
import com.baofeng.commons.entity.Operator.Genres;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.oa.entity.BaseEnums.ActoresLabour;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.utils.CustomDateSerializerFormat2;

/**
 * 功能：管理助理人员表
 * */
@Entity
@Table(name = "managers")
public class Managers extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 6492179411555750685L;

	/** 员工号 */
	private Integer number;
	/** 艺名 */
	private String aliasname;
	/** 联系电话 */
	private String phone;
	/** 联系QQ */
	private String qq;
	/** 真实姓名 */
	private String realname;
	/** 性别 */
	private Sex sex;
	/** 工资卡 */
	private String bankCard;
	/** 开户行 */
	private String bankCardAccount;
	/** 现居地址 */
	private String address;
	/** 身份证号码 */
	private String idcard;
	/** 身份证扫描件 */
	private String idImage;
	/** 类型(线上,线下) */
	private LineGenres genre;
	/** 管理岗位 */
	private String positions;
	/** 入职时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date entryTime = new Date();
	/** 试用期 */
	private Integer probation = new Integer(0);
	/** 试用工资 */
	private Float probationSalary = new Float(0.00);
	/** 正式工资 */
	private Float basicSalary = new Float(0.00);
	/** 餐补 */
	private Float foodSubsidy = new Float(0.00);
	/** 劳务员工 */
	private ActoresLabour labour = ActoresLabour.OTHER;
	/** 员工关联 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private Employee employee;
	/** 状态 */
	private Genres genrer = Operator.Genres.SHIYONG;
	/** 离职原因 */
	private String leaveReasons;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
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

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
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

	public LineGenres getGenre() {
		return genre;
	}

	public void setGenre(LineGenres genre) {
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

	public Integer getProbation() {
		return probation;
	}

	public void setProbation(Integer probation) {
		this.probation = probation;
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

	public Float getFoodSubsidy() {
		return foodSubsidy;
	}

	public void setFoodSubsidy(Float foodSubsidy) {
		this.foodSubsidy = foodSubsidy;
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

	public String getLeaveReasons() {
		return leaveReasons;
	}

	public void setLeaveReasons(String leaveReasons) {
		this.leaveReasons = leaveReasons;
	}

	public Genres getGenrer() {
		return genrer;
	}

	public void setGenrer(Genres genrer) {
		this.genrer = genrer;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getBankCardAccount() {
		return bankCardAccount;
	}

	public void setBankCardAccount(String bankCardAccount) {
		this.bankCardAccount = bankCardAccount;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

}
