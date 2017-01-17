package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.oa.entity.BaseEnums.Decide;

/**
 * 功能：财务对账 财务支出
 * */
@Entity
@Table(name = "fiscalCheck")
public class FinancialAccounts extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -8431064176520182661L;

	/** 一级名称 */
	private String oneSubjectKey;
	/** 二级科目 */
	private String twoSubjectMsg;
	/** 二级名称 */
	private String twoSubject;
	/** 财务支出值 */
	@Lob
	private String finValues;
	/** 财务支出 */
	private BigDecimal fiscalCharges = new BigDecimal(0.00);
	/** 财务对账 */
	private BigDecimal fiscalCheck = new BigDecimal(0.00);
	/** 备注 */
	private String remark;
	/** 判断 */
	private Decide decide = Decide.NO;
	/** 显示顺序 */
	private Integer indexs;

	public Decide getDecide() {
		return decide;
	}

	public void setDecide(Decide decide) {
		this.decide = decide;
	}

	public String getTwoSubject() {
		return twoSubject;
	}

	public void setTwoSubject(String twoSubject) {
		this.twoSubject = twoSubject;
	}

	public BigDecimal getFiscalCharges() {
		return fiscalCharges;
	}

	public void setFiscalCharges(BigDecimal fiscalCharges) {
		this.fiscalCharges = fiscalCharges;
	}

	public BigDecimal getFiscalCheck() {
		return fiscalCheck;
	}

	public void setFiscalCheck(BigDecimal fiscalCheck) {
		this.fiscalCheck = fiscalCheck;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOneSubjectKey() {
		return oneSubjectKey;
	}

	public void setOneSubjectKey(String oneSubjectKey) {
		this.oneSubjectKey = oneSubjectKey;
	}

	public String getTwoSubjectMsg() {
		return twoSubjectMsg;
	}

	public void setTwoSubjectMsg(String twoSubjectMsg) {
		this.twoSubjectMsg = twoSubjectMsg;
	}

	public String getFinValues() {
		return finValues;
	}

	public void setFinValues(String finValues) {
		this.finValues = finValues;
	}

	public Integer getIndexs() {
		return indexs;
	}

	public void setIndexs(Integer indexs) {
		this.indexs = indexs;
	}
}
