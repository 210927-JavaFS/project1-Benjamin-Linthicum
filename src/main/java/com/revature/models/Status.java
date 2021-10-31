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

@Entity (name = "ERS_REIMBURSEMENT_STATUS")
public class Status {
   
    @Id
    @Column(name = "REIMB_STATUS_ID")
    private int id;
    @Column(name = "REIMB_STATUS", nullable = false)
    private String status;

    public Status(int id, String status){
        this.id = id;
        this.status = status;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public String toString(){
        return status;
    }

}
