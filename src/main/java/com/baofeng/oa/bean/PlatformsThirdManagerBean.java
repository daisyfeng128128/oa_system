package com.baofeng.oa.bean;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat1;

/**
 * 功能：场控人员
 * */
@SuppressWarnings("serial")
public class PlatformsThirdManagerBean implements Serializable {

	private Integer id;
	/** 马甲 */
	private String majia;
	/** 性别 */
	private String sex;
	/** 真实姓名 */
	private String realname;
	/** 场控岗位 */
	private String positions;
	/** 小时工资 */
	private Float hourlyWage;
	/** 所属平台 */
	private String plat;
	/** 所属频道 */
	private String channel;
	/** 创建时间 */
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date createDT;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMajia() {
		return majia;
	}

	public void setMajia(String majia) {
		this.majia = majia;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public String getPlat() {
		return plat;
	}

	public void setPlat(String plat) {
		this.plat = plat;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Date getCreateDT() {
		return createDT;
	}

	public void setCreateDT(Date createDT) {
		this.createDT = createDT;
	}
}
