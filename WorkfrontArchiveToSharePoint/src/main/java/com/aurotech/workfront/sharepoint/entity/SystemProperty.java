package com.aurotech.workfront.sharepoint.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name="system_property")
public class SystemProperty {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "system_id")
	private System system;
	
	private String name;
	
	@Column(name="display_name")
	private String displayName;
	
	private String description;
	
	@Column(name="default_value")
	private String defaultValue;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "value_type_id")
	private PropertyValueType valueType;
	
	public SystemProperty () {}

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public PropertyValueType getValueType() {
		return valueType;
	}

	public void setValueType(PropertyValueType valueType) {
		this.valueType = valueType;
	}

	public Long getId() {
		return id;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
