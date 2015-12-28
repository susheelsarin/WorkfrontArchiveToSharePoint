package com.aurotech.workfront.sharepoint.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer_system")
public class CustomerSystem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "system_id")
	private System system;
	
	@Column(name = "start_date")
	private String startDate;
	
	@Column(name = "end_date")
	private String endDate;

	@OneToMany(mappedBy = "customerSystem", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<CustomerSystemProperty> properties;
	
	public CustomerSystem() {
	}

	public CustomerSystem(Customer customer, System system) {
		super();
		this.customer = customer;
		this.system = system;
	}

	public Long getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<CustomerSystemProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<CustomerSystemProperty> properties) {
		this.properties = properties;
	}

}
