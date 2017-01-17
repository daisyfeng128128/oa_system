package com.baofeng.oa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;

/**
 * 功能：职位表
 * */
@Entity
@Table(name = "positions")
public class Positions extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -1120160428222553120L;

	/** 职位名称 */
	private String name;
	/** 职位描述 */
	private String described;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribed() {
		return described;
	}

	public void setDescribed(String described) {
		this.described = described;
	}
}
