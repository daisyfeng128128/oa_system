package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import com.baofeng.commons.entity.BaseInteger;

/**
 * 功能：大型充值明细
 * */
@Entity
@Table(name = "bigtopDetails")
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class BigTopDetails extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer platId;
	/** 申请费用 */
	private BigDecimal askfees;
	/** 支出费用 */
	private BigDecimal payfees;
	/** 结余费用 */
	private BigDecimal cashfees;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "outlay_id")
	private PlatformsMaintainOutlay outlay;

	public Integer getPlatId() {
		return platId;
	}

	public void setPlatId(Integer platId) {
		this.platId = platId;
	}

	public BigDecimal getAskfees() {
		return askfees;
	}

	public void setAskfees(BigDecimal askfees) {
		this.askfees = askfees;
	}

	public BigDecimal getPayfees() {
		return payfees;
	}

	public void setPayfees(BigDecimal payfees) {
		this.payfees = payfees;
	}

	public BigDecimal getCashfees() {
		return cashfees;
	}

	public void setCashfees(BigDecimal cashfees) {
		this.cashfees = cashfees;
	}

	public PlatformsMaintainOutlay getOutlay() {
		return outlay;
	}

	public void setOutlay(PlatformsMaintainOutlay outlay) {
		this.outlay = outlay;
	}
}
