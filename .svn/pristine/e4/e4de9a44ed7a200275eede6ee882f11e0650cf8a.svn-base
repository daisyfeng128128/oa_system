package com.baofeng.commons.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 描述：实体类Id(integer)
 */
@MappedSuperclass
@SuppressWarnings("serial")
public class BaseInteger extends BaseEntity implements Serializable {
	
	@Id
	@Column(length = 32)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
