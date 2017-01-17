package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Where;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.oa.entity.BaseEnums.PlatIncomeGenre;
import com.baofeng.oa.entity.BaseEnums.PlatformGenre;

/**
 * 功能：直播平台
 * */
@Entity
@Table(name = "platforms")
@JsonIgnoreProperties({ "thirdManager", "manager", "channels", "actores" })
public class Platforms extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 3584132251557424863L;
	/** 平台名称 */
	private String platName;
	/** 平台类型 */
	private PlatformGenre formGenre;
	/** 收入类型 */
	private PlatIncomeGenre incomeGenre;
	/** 备注 */
	private String remarks;
	/** 平台总监 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_id")
	private Employee platManager;
	/** 平台艺人 */
	@OneToMany(cascade = CascadeType.ALL)
	@Where(clause = "status=1")
	@JoinColumn(name = "plat_id")
	private List<PlatformsActores> actores = new ArrayList<PlatformsActores>();
	/** 子频道号 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "plat_id")
	private List<PlatformsChannels> channels = new ArrayList<PlatformsChannels>();
	/** 频道助理 */
	@OneToMany(cascade = CascadeType.ALL)
	@Where(clause = "status=1")
	@JoinColumn(name = "plat_id")
	private List<PlatformsManager> manager = new ArrayList<PlatformsManager>();
	/** 场控人员 */
	@OneToMany(cascade = CascadeType.ALL)
	@Where(clause = "status=1")
	@JoinColumn(name = "plat_id")
	private List<PlatformsThirdManager> thirdManager = new ArrayList<PlatformsThirdManager>();
	/** 平台税率 */
	private BigDecimal tax = new BigDecimal(0.00);

	public String getPlatName() {
		return platName;
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

	public List<PlatformsChannels> getChannels() {
		return channels;
	}

	public void setChannels(List<PlatformsChannels> channels) {
		this.channels = channels;
	}

	public PlatIncomeGenre getIncomeGenre() {
		return incomeGenre;
	}

	public void setIncomeGenre(PlatIncomeGenre incomeGenre) {
		this.incomeGenre = incomeGenre;
	}

	public Employee getPlatManager() {
		return platManager;
	}

	public void setPlatManager(Employee platManager) {
		this.platManager = platManager;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<PlatformsThirdManager> getThirdManager() {
		return thirdManager;
	}

	public void setThirdManager(List<PlatformsThirdManager> thirdManager) {
		this.thirdManager = thirdManager;
	}

	public List<PlatformsManager> getManager() {
		return manager;
	}

	public void setManager(List<PlatformsManager> manager) {
		this.manager = manager;
	}

	public List<PlatformsActores> getActores() {
		return actores;
	}

	public void setActores(List<PlatformsActores> actores) {
		this.actores = actores;
	}
}
