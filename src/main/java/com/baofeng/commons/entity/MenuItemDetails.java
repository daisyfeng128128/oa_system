package com.baofeng.commons.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

/**
 * 功能：系统菜单页面操作权限
 * */
@Entity
@Table(name = "menuitemDetails")
public class MenuItemDetails extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 456534836225705365L;

	/** 操作名称 */
	private String opName;
	/** 操作键值 */
	private String opkey;
	/** 是否显示 */
	private String views = "Y";
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private MenuItem item;

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public String getOpkey() {
		return opkey;
	}

	public void setOpkey(String opkey) {
		this.opkey = opkey;
	}

	public MenuItem getItem() {
		return item;
	}

	public void setItem(MenuItem item) {
		this.item = item;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

}
