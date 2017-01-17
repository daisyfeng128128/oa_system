package com.baofeng.oa.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat1;

/**
 * 功能：直播平台项目管理
 * */
public class ProjectManagementBean implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 数据Id */
	private Integer id;
	/** 平台名称 */
	private String name;
	/** 项目名称 */
	private String projectName;
	/** 日期 */
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date date;
	/** 平台经理 */
	private String projectManager;
	/** 备注 */
	private String remarks;
	/** 项目中的平台 */
	private List<String> projectPlat;
	
	private Integer empidP;
	private Integer empids;
	private String[] platIds;
	
	public Integer getEmpidP() {
		return empidP;
	}
	public void setEmpidP(Integer empidP) {
		this.empidP = empidP;
	}
	public Integer getEmpids() {
		return empids;
	}
	public void setEmpids(Integer empids) {
		this.empids = empids;
	}
	public String[] getPlatIds() {
		return platIds;
	}
	public void setPlatIds(String[] platIds) {
		this.platIds = platIds;
	}
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
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<String> getProjectPlat() {
		return projectPlat;
	}
	public void setProjectPlat(List<String> projectPlat) {
		this.projectPlat = projectPlat;
	} 
	
}
