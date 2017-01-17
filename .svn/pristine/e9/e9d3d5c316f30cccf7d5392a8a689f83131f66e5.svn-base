package com.baofeng.oa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.baofeng.commons.entity.BaseInteger;

/**
 * 功能：排麦频道
 * */
@Entity
@Table(name = "platformsChannels")
@JsonIgnoreProperties({ "plat", "manager", "thirdManager", "actores" })
public class PlatformsChannels extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -8418754933586724301L;

	/** 频道号 */
	private String channels;
	/** 频道备注 */
	private String remarks;
	/** 所属平台 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "plat_id")
	private Platforms plat;
	/** 频道管理 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "chan_id")
	private List<PlatformsManager> manager = new ArrayList<PlatformsManager>();
	/** 频道场控 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "chan_id")
	private List<PlatformsThirdManager> thirdManager = new ArrayList<PlatformsThirdManager>();
	/** 频道艺人 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "chan_id")
	private List<PlatformsActores> actores = new ArrayList<PlatformsActores>();

	public String getChannels() {
		return channels;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Platforms getPlat() {
		return plat;
	}

	public void setPlat(Platforms plat) {
		this.plat = plat;
	}

	public List<PlatformsManager> getManager() {
		return manager;
	}

	public void setManager(List<PlatformsManager> manager) {
		this.manager = manager;
	}

	public List<PlatformsThirdManager> getThirdManager() {
		return thirdManager;
	}

	public void setThirdManager(List<PlatformsThirdManager> thirdManager) {
		this.thirdManager = thirdManager;
	}

	public List<PlatformsActores> getActores() {
		return actores;
	}

	public void setActores(List<PlatformsActores> actores) {
		this.actores = actores;
	}
}
