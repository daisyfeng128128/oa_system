package com.baofeng.commons.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：操作日志
 * 
 * @author RENLIANGRONG
 */
@Entity
@Table(name = "monitorLog")
public class MonitorLog extends BaseInteger implements Serializable {

	private static final long serialVersionUID = 2982963449205777591L;

	/** 日志内容 */
	@Column(length = 2000)
	private String log;
	
	/** IP*/
	private String ip;
	
	/** 日志类别 */
	private Integer type;
	
	


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
