package com.baofeng.oa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;

/**
 * 功能：平台月报表审核记录
 * */
@Entity
@Table(name = "platformsAuditsMsg")
public class PlatformsAudits extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -5496514294311460437L;
	/** 平台Id */
	private String platId;
	/** 所在平台 */
	private String platforms;
	/***/
	private String rejectMsg;
	/** 备注 */
	private String remarks;
	/** 平台负责人 */
	private String platManager;
	/** 营收总表 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "months_id")
	private PlatformsMonthsOutlay months;

	public String getPlatId() {
		return platId;
	}

	public void setPlatId(String platId) {
		this.platId = platId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPlatManager() {
		return platManager;
	}

	public void setPlatManager(String platManager) {
		this.platManager = platManager;
	}

	public PlatformsMonthsOutlay getMonths() {
		return months;
	}

	public void setMonths(PlatformsMonthsOutlay months) {
		this.months = months;
	}

	public String getPlatforms() {
		return platforms;
	}

	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	public String getRejectMsg() {
		return rejectMsg;
	}

	public void setRejectMsg(String rejectMsg) {
		this.rejectMsg = rejectMsg;
	}
}
