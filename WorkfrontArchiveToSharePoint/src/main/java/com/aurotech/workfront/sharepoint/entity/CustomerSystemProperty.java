package com.aurotech.workfront.sharepoint.entity;

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
@Table(name="customer_system_property")
public class CustomerSystemProperty {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_system_id")
	private CustomerSystem customerSystem;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "system_property_id")
	private SystemProperty systemProperty;

	private String value;
	
	public CustomerSystemProperty(){}

	public Long getId() {
		return id;
	}

	public CustomerSystem getCustomerSystem() {
		return customerSystem;
	}

	public void setCustomerSystem(CustomerSystem customerSystem) {
		this.customerSystem = customerSystem;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public SystemProperty getSystemProperty() {
		return systemProperty;
	}

	public void setSystemProperty(SystemProperty systemProperty) {
		this.systemProperty = systemProperty;
	}

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
