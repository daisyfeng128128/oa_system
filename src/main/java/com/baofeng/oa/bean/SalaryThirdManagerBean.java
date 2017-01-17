package com.baofeng.oa.bean;
 
import java.io.Serializable;
 
public class SalaryThirdManagerBean  implements Serializable {

	/**
	 * 财务： 场控工资
	 */
	private static final long serialVersionUID = 5449171444453338868L;
	 
	/** 场控ID */
	private Integer thirdManagerId;
	/** 姓名 */
	private String name;
	/** 马甲 */
	private String majia;
	/** 应付工资 */
	private String basicSalary;
	/** 其他补贴 */
	private String otherSubsidy;
	/** 其他扣除 */
	private String otherDeduct;
	/** 实发工资 */
	private String realitySalary;
	/** 备注 */
	private String remarks;
	public Integer getThirdManagerId() {
		return thirdManagerId;
	}
	public void setThirdManagerId(Integer thirdManagerId) {
		this.thirdManagerId = thirdManagerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMajia() {
		return majia;
	}
	public void setMajia(String majia) {
		this.majia = majia;
	} 
	public String getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}
	public String getOtherSubsidy() {
		return otherSubsidy;
	}
	public void setOtherSubsidy(String otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}
	public String getOtherDeduct() {
		return otherDeduct;
	}
	public void setOtherDeduct(String otherDeduct) {
		this.otherDeduct = otherDeduct;
	}
	public String getRealitySalary() {
		return realitySalary;
	}
	public void setRealitySalary(String realitySalary) {
		this.realitySalary = realitySalary;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


}
