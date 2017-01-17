package com.baofeng.oa.bean;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat1;

public class DepartmentBean {
	
	/** 部门名称 */
	private String name;
	/** 部门经理 */
	private String manager;
	/** 父级部门 */
	private String parent;
	/**创建时间*/
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date createDT;
	/** ID*/
	private Integer id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public Date getCreateDT() {
		return createDT;
	}
	public void setCreateDT(Date createDT) {
		this.createDT = createDT;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
