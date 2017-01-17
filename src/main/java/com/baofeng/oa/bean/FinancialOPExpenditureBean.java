package com.baofeng.oa.bean;


public class FinancialOPExpenditureBean {
	private Integer id;
	/** 项目名称 */
	private String name;
	/** 支出金额 */
	private String expenditure;
	/** 备注 */
	private String remarks;

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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(String expenditure) {
		this.expenditure = expenditure;
	}

}
