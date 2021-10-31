package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;

@Entity (name = "ERS_USER_ROLES")
public class Role {
    
    @Id
    @Column(name = "ERS_USER_ROLE_ID")
    private int id;
    @Column(name = "USER_ROLE")
    private String role;

    public Role(int id, String role){
        this.id = id;
        this.role = role;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    @Override
    public String toString(){
        return role;
    }

}
