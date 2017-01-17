package com.baofeng.oa.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：树数据对像
 * */
@SuppressWarnings("serial")
public class TreeviewBean implements Serializable {

	private Integer id;

	private String text;

	private List<TreeviewBean> nodes = new ArrayList<TreeviewBean>();

	public String getText() {
		return text;
	}

	public List<TreeviewBean> getNodes() {
		return nodes;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setNodes(List<TreeviewBean> nodes) {
		this.nodes = nodes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
