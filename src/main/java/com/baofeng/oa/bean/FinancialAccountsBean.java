package com.baofeng.oa.bean;

import java.io.Serializable;

/**
 * 财务支出
 * */
@SuppressWarnings("serial")
public class FinancialAccountsBean implements Serializable {

	/** 科目 */
	private String id;
	/** 项目名称 */
	private String name;
	/** 金额 */
	private String number;
	/** 备注 */
	private String remark;
	/** 日期 */
	private String date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
