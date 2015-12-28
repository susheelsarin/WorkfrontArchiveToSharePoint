package com.aurotech.workfront.sharepoint.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String domain;

    @Column(name = "point_of_contact_email")
    private String pointOfContactEmail;

    @Column(name = "is_active")
    private boolean isActive;

    public Customer() {
    }

    public Customer(String name, String domain) {
        super();
        this.name = name;
        this.domain = domain;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setPointOfContactEmail(String pointOfContactEmail) {
        this.pointOfContactEmail = pointOfContactEmail;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public boolean equals(Object o) {
        if ((o instanceof Customer)
                && ((Customer) o).getId().longValue() == id.longValue())
            return true;
        return false;
    }

    public int hashCode() {
        return id.intValue();
    }
}
