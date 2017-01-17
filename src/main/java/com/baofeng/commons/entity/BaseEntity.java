package com.baofeng.commons.entity;

import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.baofeng.utils.CustomDateSerializerFormat1;

/**
 * 描述：实体基类
 * 
 * @author RENLIANGRONG
 */
@MappedSuperclass
@SuppressWarnings("serial")
public class BaseEntity implements java.io.Serializable {

	public enum EntityStatus {
		DELETED, // 已删除
		NORMAL;// 正常
	}

	/** 实体创建者 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operator_id")
	private Operator operator;

	/** 分公司 */
	private Integer branchs = Integer.valueOf(0);

	/** 实体状态（业务无关） */
	private EntityStatus status = EntityStatus.NORMAL;

	/** 创建时间（业务无关） */
	@JsonSerialize(using = CustomDateSerializerFormat1.class)
	private Date createDT = new Date();

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public EntityStatus getStatus() {
		return status;
	}

	public void setStatus(EntityStatus status) {
		this.status = status;
	}

	public Date getCreateDT() {
		return createDT;
	}

	public void setCreateDT(Date createDT) {
		this.createDT = createDT;
	}

	public Integer getBranchs() {
		return branchs;
	}

	public void setBranchs(Integer branchs) {
		this.branchs = branchs;
	}

}
