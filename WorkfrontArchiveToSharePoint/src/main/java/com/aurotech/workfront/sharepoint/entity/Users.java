package com.aurotech.workfront.sharepoint.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name="users")
public class Users {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    
    @ManyToOne
    @JoinColumn(name = "roles_id")
    private Roles role;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column
    private String email;
    
    @Column(name = "is_active")
    private boolean isActive;

    
    public Long getId() {
        return id;
}
    
    public Roles getRole() {
        return role;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public boolean isActive() {
        return isActive;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
                    
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
}       

}
