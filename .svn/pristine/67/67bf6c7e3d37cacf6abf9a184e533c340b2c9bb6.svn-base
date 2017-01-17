package com.baofeng.oa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.commons.entity.Operator.Sex;

/**
 * 功能：场控表
 * */
@Entity
@Table(name = "platformsThirdManager")
public class PlatformsThirdManager extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -4507704288009184978L;

	/** 马甲 */
	private String majia;
	/** 性别 */
	private Sex sex;
	/** 真实姓名 */
	private String realname;
	/** 场控岗位 */
	private String positions;
	/** 小时工资 */
	@Column(precision = 8, scale = 2)
	private Float hourlyWage;
	/** 所属平台 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "plat_id")
	private Platforms plat;
	/** 所属频道 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "chan_id")
	private PlatformsChannels channel;

	public String getMajia() {
		return majia;
	}

	public void setMajia(String majia) {
		this.majia = majia;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPositions() {
		return positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

	public Float getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(Float hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public PlatformsChannels getChannel() {
		return channel;
	}

	public void setChannel(PlatformsChannels channel) {
		this.channel = channel;
	}

	public Platforms getPlat() {
		return plat;
	}

	public void setPlat(Platforms plat) {
		this.plat = plat;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}
}
