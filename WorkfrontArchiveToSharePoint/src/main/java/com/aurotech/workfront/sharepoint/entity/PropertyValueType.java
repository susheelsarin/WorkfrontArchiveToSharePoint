package com.aurotech.workfront.sharepoint.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name="property_value_type")
public class PropertyValueType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String type;

	
	public PropertyValueType(){}

	public PropertyValueType(String type) {
		super();
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
