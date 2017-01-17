package com.baofeng.oa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import com.baofeng.commons.entity.BaseInteger;

/**
 * 功能：频道助理表
 * */
@Entity
@Table(name = "platformsManager")
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class PlatformsManager extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -779654217910146556L;

	/** 所属平台 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "plat_id")
	private Platforms plat;
	/** 所属 频道 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "chan_id")
	private PlatformsChannels channel;
	/** 管理助理 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "manager_id")
	private Managers manager;

	public Platforms getPlat() {
		return plat;
	}

	public void setPlat(Platforms plat) {
		this.plat = plat;
	}

	public PlatformsChannels getChannel() {
		return channel;
	}

	public void setChannel(PlatformsChannels channel) {
		this.channel = channel;
	}

	public Managers getManager() {
		return manager;
	}

	public void setManager(Managers manager) {
		this.manager = manager;
	}
}
