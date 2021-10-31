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

@Entity (name = "ERS_REIMBURSEMENT_TYPE")
public class ReimbursementType {
    
    @Id
    @Column(name = "REIMB_STATUS_ID")
    private int id;
    @Column(name = "REIMB_TYPE", nullable = false)
    private String type;

    public ReimbursementType(int id, String type){
        this.id = id;
        this.type = type;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }
}
