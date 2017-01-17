package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;

/** 工资统计表 */
@Entity
@Table(name = "salariesStatistics")
public class SalariesStatistics extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 部门名 */
	private String twoName;
	/** 人数 */
	private Integer peopleNum;
	/** 工资合计 */
	private BigDecimal salariesTotal = new BigDecimal(0.00);

	public String getTwoName() {
		return twoName;
	}

	public void setTwoName(String twoName) {
		this.twoName = twoName;
	}

	public Integer getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}

	public BigDecimal getSalariesTotal() {
		return salariesTotal;
	}

	public void setSalariesTotal(BigDecimal salariesTotal) {
		this.salariesTotal = salariesTotal;
	}

}
