package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.oa.entity.BaseEnums.PlatIncomeGenre;
import com.baofeng.oa.entity.BaseEnums.PlatformGenre;

/**
 * 功能:项目管理
 * */
@Entity
@Table(name = "project")
@JsonIgnoreProperties({ "thirdManager", "manager", "channels", "actores" })
public class ProjectManagement extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 项目名称 */
	private String projectName;
	/** 项目类型 */
	private Integer projectNum;
	/** 项目包含的平台 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "project_id")
	@JoinTable(name = "ProjectPlats_details", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "plat_id"))
	private List<Platforms> projectPlat = new ArrayList<Platforms>();
	/** 平台名称 */
	private String platName;
	/** 平台类型 */
	private PlatformGenre formGenre;
	/** 收入类型 */
	private PlatIncomeGenre incomeGenre;
	/** 备注 */
	private String remarks;
	/** 项目管理人 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_id")
	private Employee projectManager;
	
	private BigDecimal tax = new BigDecimal(0.00);
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPlatName() {
		return platName;
	}
	
	public Integer getProjectNum() {
		return projectNum;
	}
	
	public List<Platforms> getProjectPlat() {
		return projectPlat;
	}

	public Employee getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(Employee projectManager) {
		this.projectManager = projectManager;
	}


	public void setProjectPlat(List<Platforms> projectPlat) {
		this.projectPlat = projectPlat;
	}


	public void setProjectNum(Integer projectNum) {
		this.projectNum = projectNum;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public void setPlatName(String platName) {
		this.platName = platName;
	}

	public PlatformGenre getFormGenre() {
		return formGenre;
	}

	public void setFormGenre(PlatformGenre formGenre) {
		this.formGenre = formGenre;
	}
	
	public PlatIncomeGenre getIncomeGenre() {
		return incomeGenre;
	}

	public void setIncomeGenre(PlatIncomeGenre incomeGenre) {
		this.incomeGenre = incomeGenre;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
