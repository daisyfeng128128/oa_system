package com.baofeng.oa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.utils.CustomDateSerializerFormat2;

/**
 * 功能：平台艺人表
 * */
@Entity
@Table(name = "platformsActores")
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class PlatformsActores extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -5030240164164579598L;
	/** 所在平台 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "plat_id")
	private Platforms plat;
	/** 所属频道 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "chan_id")
	private PlatformsChannels channel;
	/** 艺人 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "act_id")
	private Actores actores;
	/** 提成比例 */
	private Integer pushMoney;
	/** 开播时间 */
	@JsonSerialize(using = CustomDateSerializerFormat2.class)
	private Date beLiveTime = new Date();
	/** 艺人成本 */
	private BigDecimal costArtists = new BigDecimal(0);
	/** 主平台(0:否,1:是) */
	private Integer mainPlatform;
	/** 房间号 */
	private String number;

	public Platforms getPlat() {
		return plat;
	}

	public void setPlat(Platforms plat) {
		this.plat = plat;
	}

	public Actores getActores() {
		return actores;
	}

	public void setActores(Actores actores) {
		this.actores = actores;
	}

	public PlatformsChannels getChannel() {
		return channel;
	}

	public void setChannel(PlatformsChannels channel) {
		this.channel = channel;
	}

	public Integer getPushMoney() {
		return pushMoney;
	}

	public void setPushMoney(Integer pushMoney) {
		this.pushMoney = pushMoney;
	}

	public Date getBeLiveTime() {
		return beLiveTime;
	}

	public void setBeLiveTime(Date beLiveTime) {
		this.beLiveTime = beLiveTime;
	}

	public BigDecimal getCostArtists() {
		return costArtists;
	}

	public void setCostArtists(BigDecimal costArtists) {
		this.costArtists = costArtists;
	}

	public Integer getMainPlatform() {
		return mainPlatform;
	}

	public void setMainPlatform(Integer mainPlatform) {
		this.mainPlatform = mainPlatform;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
