package com.baofeng.commons.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * 功能：系统菜单
 * */
@Entity
@Table(name = "menuitem")
public class MenuItem extends BaseInteger implements Serializable {

	private static final long serialVersionUID = -5046721141875217907L;

	public enum Dev {
		YES, NO
	}

	/** 菜单名称 */
	private String name;
	/** 访问地址 */
	private String url;
	/** 父类菜单 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id")
	private MenuItem item;
	/** 页面divId */
	private String divid;
	/** 排序显示 */
	private Integer indexs;
	/** 开发中 */
	private Dev dev = Dev.YES;

	/** 页面详细权限 */
	@JsonManagedReference
	@OrderBy("createDT ASC")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id")
	private List<MenuItemDetails> details = new ArrayList<MenuItemDetails>();

	public MenuItem() {
	}

	public MenuItem(String name, String url, MenuItem item, String divid, Integer indexs, Dev dev, String... args) {
		super();
		this.name = name;
		this.url = url;
		this.item = item;
		this.divid = divid;
		this.indexs = indexs;
		this.dev = dev;
		if (args.length > 0) {
			for (String p : args) {
				String[] p1 = p.split(":");
				MenuItemDetails details = new MenuItemDetails();
				if (p1.length > 0)
					details.setOpName(p1[0]);
				if (p1.length > 1)
					details.setOpkey(p1[1]);
				if (p1.length > 2)
					details.setViews(p1[2]);
				details.setItem(this);
				this.getDetails().add(details);
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MenuItem getItem() {
		return item;
	}

	public void setItem(MenuItem item) {
		this.item = item;
	}

	public String getDivid() {
		return divid;
	}

	public void setDivid(String divid) {
		this.divid = divid;
	}

	public Integer getIndexs() {
		return indexs;
	}

	public void setIndexs(Integer indexs) {
		this.indexs = indexs;
	}

	public List<MenuItemDetails> getDetails() {
		return details;
	}

	public void setDetails(List<MenuItemDetails> details) {
		this.details = details;
	}

	public Dev getDev() {
		return dev;
	}

	public void setDev(Dev dev) {
		this.dev = dev;
	}
}
