package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.oa.entity.BaseEnums.LineGenres;

/**
 * 功能： 收益明细
 * */
@Entity
@Table(name="finEarningsIncome")
public class FinEarningsIncome  extends BaseInteger implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 员工号 */
	private Integer number;
	/** 姓名 */
	private String name;
	/** 艺名 */
	private String aliasname;
	/** 税后总业绩 */
	private BigDecimal totalIncome = new BigDecimal(0.00);
	/** 底薪 */
	private BigDecimal basicSalary = new BigDecimal(0.00);
	/** 提成比例 */
	private BigDecimal pushMoney=new BigDecimal(0.00);
	/** 提成工资 */
	private BigDecimal pushMoneySalary=new BigDecimal(0.00);
	/** 线上/线下 */
	private LineGenres genre;
	/** 实体数据 id*/
	private Integer actores_id;
	/** 平台收入 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "earningsOff_id")
	private List<FinPlatformsIncome> details = new ArrayList<FinPlatformsIncome>();
	
	
	public LineGenres getGenre() {
		return genre;
	}
	public void setGenre(LineGenres genre) {
		this.genre = genre;
	}
	public Integer getActores_id() {
		return actores_id;
	}
	public void setActores_id(Integer actores_id) {
		this.actores_id = actores_id;
	}
	public List<FinPlatformsIncome> getDetails() {
		return details;
	}
	public void setDetails(List<FinPlatformsIncome> details) {
		this.details = details;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
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
	public BigDecimal getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}
	public BigDecimal getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
	}
	public BigDecimal getPushMoney() {
		return pushMoney;
	}
	public void setPushMoney(BigDecimal pushMoney) {
		this.pushMoney = pushMoney;
	}
	public BigDecimal getPushMoneySalary() {
		return pushMoneySalary;
	}
	public void setPushMoneySalary(BigDecimal pushMoneySalary) {
		this.pushMoneySalary = pushMoneySalary;
	}
	
	
}
