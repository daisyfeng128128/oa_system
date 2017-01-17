package com.baofeng.oa.bean;

import java.util.List;



public class MenusItemBean {
	
	private Integer id ;
	/** 菜单名称 */
	private String name;
	/** 访问地址 */
	private String url;
	/** 父类菜单 */
	private String parentid;
	/** 父类菜单名 */
	private String parentName;
	/** 页面divId */
	private String divid;
	/** 排序显示 */
	private Integer indexs;
	/** 开发中 */
	private String dev;

	/** 页面详细权限 */
	private List<String> details;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
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

	public String getDev() {
		return dev;
	}

	public void setDev(String dev) {
		this.dev = dev;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
	
}
