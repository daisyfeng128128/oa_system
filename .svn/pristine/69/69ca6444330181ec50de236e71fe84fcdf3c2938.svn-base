package com.baofeng.oa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.baofeng.commons.entity.BaseInteger;

/**
 * 功能：成本校验
 * */

@Entity
@Table(name = "costCheck")
public class CostCheck extends BaseInteger implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 艺人成本说明 */
	private String costArtists ; 
	/** 艺人ID*/
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "act_id")
	private Actores actores;
	public String getCostArtists() {
		return costArtists;
	}
	public void setCostArtists(String costArtists) {
		this.costArtists = costArtists;
	}
	public Actores getActores() {
		return actores;
	}
	public void setActores(Actores actores) {
		this.actores = actores;
	}
}
