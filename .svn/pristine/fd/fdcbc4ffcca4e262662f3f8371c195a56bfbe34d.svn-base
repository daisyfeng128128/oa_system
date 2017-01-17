package com.baofeng.oa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import com.baofeng.commons.entity.BaseInteger;
 

/**
 * 功能：艺人离职表
 * */
@Entity
@Table(name = "actoresLeave")
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class ActoresLeave extends BaseInteger implements Serializable{
 
	private static final long serialVersionUID = -6053805432038526581L;
	/** 离职原因 */
	@Lob
	private String leaveMsg;
 
	/** 艺人 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "act_id")
	private Actores actores;

	public String getLeaveMsg() {
		return leaveMsg;
	}

	public void setLeaveMsg(String leaveMsg) {
		this.leaveMsg = leaveMsg;
	}

	public Actores getActores() {
		return actores;
	}

	public void setActores(Actores actores) {
		this.actores = actores;
	}
  
	
}
