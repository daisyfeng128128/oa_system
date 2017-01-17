package com.baofeng.oa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.commons.entity.BaseInteger;
import com.baofeng.commons.entity.Operator.Sex;
import com.baofeng.oa.entity.BaseEnums.Interviews;
import com.baofeng.oa.entity.BaseEnums.LineGenres;
import com.baofeng.utils.CustomDateSerializerFormat1;

/**
 * 功能：面试管理
 * */
@Entity
@Table(name = "interview")
public class Interview extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 姓名 */
	private String name;
	/** 艺名 */
	private String aliasname;
	/** 照片 */
	private String photo;
	/** 视频照片 */
	private String videoPhoto;
	/** 性别 */
	private Sex sex;
	/** 联系电话 */
	private String phone;
	/** 开播时间 */
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date launchTime;
	/** QQ */
	private String qq;
	/** 试播平台 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "plat_id")
	private Platforms plat;
	/** 介绍人 */
	private String introducer;
	/** 频道交接人 */
	private String chTransition;
	/** 视频连接 */
	private String linkUrl;
	/** 状态跟踪 */
	@Column(length = 1000)
	private String statusTracking;
	/** 备注 */
	private String remark;
	/** 线上/线下 */
	private LineGenres genre = LineGenres.ONLINE;
	/** 跟踪结果 */
	private String trackingResults;
	/** 面试状态 */
	private Interviews interview = Interviews.PRETEST;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAliasname() {
		return aliasname;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Platforms getPlat() {
		return plat;
	}

	public void setPlat(Platforms plat) {
		this.plat = plat;
	}

	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}

	public String getChTransition() {
		return chTransition;
	}

	public void setChTransition(String chTransition) {
		this.chTransition = chTransition;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getStatusTracking() {
		return statusTracking;
	}

	public void setStatusTracking(String statusTracking) {
		this.statusTracking = statusTracking;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public LineGenres getGenre() {
		return genre;
	}

	public void setGenre(LineGenres genre) {
		this.genre = genre;
	}

	public String getTrackingResults() {
		return trackingResults;
	}

	public void setTrackingResults(String trackingResults) {
		this.trackingResults = trackingResults;
	}

	public Interviews getInterview() {
		return interview;
	}

	public void setInterview(Interviews interview) {
		this.interview = interview;
	}

	public String getVideoPhoto() {
		return videoPhoto;
	}

	public void setVideoPhoto(String videoPhoto) {
		this.videoPhoto = videoPhoto;
	}

	public Date getLaunchTime() {
		return launchTime;
	}

	public void setLaunchTime(Date launchTime) {
		this.launchTime = launchTime;
	}

}
