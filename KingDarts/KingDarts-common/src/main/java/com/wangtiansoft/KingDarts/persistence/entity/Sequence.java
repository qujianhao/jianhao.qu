package com.wangtiansoft.KingDarts.persistence.entity;

import javax.persistence.Table;

import com.wangtiansoft.KingDarts.persistence.base.BaseEntity;

@Table(name = "darts_sequence")
public class Sequence extends BaseEntity {
	
	private String name;
	private Integer value;//当前序列值
	private Integer next;//下一序列值
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Integer getNext() {
		return next;
	}
	public void setNext(Integer next) {
		this.next = next;
	}

	
}
