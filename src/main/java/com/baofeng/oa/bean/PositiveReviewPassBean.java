package com.baofeng.oa.bean;

import java.io.Serializable;

public class PositiveReviewPassBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	/** 转正评语 */
	private String editApply;
	/** 调整薪资 */
	private String newSalary;
	/** 调整提成 */
	private String  newPushMoney;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEditApply() {
		return editApply;
	}
	public void setEditApply(String editApply) {
		this.editApply = editApply;
	}
	public String getNewSalary() {
		return newSalary;
	}
	public void setNewSalary(String newSalary) {
		this.newSalary = newSalary;
	}
	public String getNewPushMoney() {
		return newPushMoney;
	}
	public void setNewPushMoney(String newPushMoney) {
		this.newPushMoney = newPushMoney;
	}
	
}
