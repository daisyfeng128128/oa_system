package com.baofeng.oa.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat1;

/**
 * 功能：直播平台数据对像
 * */
public class PlatformsBean implements Serializable {

	private static final long serialVersionUID = 6696557599279842208L;
	/** 数据Id */
	private Integer id;
	/** 平台名称 */
	private String name;
	/** 频道 */
	private List<String> chars;
	/** 举办活动 */
	private List<String> acts;
	/** 日期 */
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date date;
	/** 平台经理 */
	private String platManager;
	/** 平台类型 */
	private String formGenre;
	/** 收入类型 */
	private String incomeGenre;
	/** 平台税率 */
	private String tax;
	/** 备注 */
	private String remarks;

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
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

	public List<String> getChars() {
		return chars;
	}

	public void setChars(List<String> chars) {
		this.chars = chars;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPlatManager() {
		return platManager;
	}

	public void setPlatManager(String platManager) {
		this.platManager = platManager;
	}

	public String getFormGenre() {
		return formGenre;
	}

	public void setFormGenre(String formGenre) {
		this.formGenre = formGenre;
	}

	public String getIncomeGenre() {
		return incomeGenre;
	}

	public void setIncomeGenre(String incomeGenre) {
		this.incomeGenre = incomeGenre;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<String> getActs() {
		return acts;
	}

	public void setActs(List<String> acts) {
		this.acts = acts;
	}
}
