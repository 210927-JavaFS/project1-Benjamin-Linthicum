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
import javax.persistence.Table;
import java.sql.Timestamp;
import javax.persistence.SecondaryTables;
import javax.persistence.SecondaryTable;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Table (name = "ERS_REIMBURSEMENT")
@SecondaryTables({
    @SecondaryTable(name = "ERS_REIMBURSEMENT_STATUS", pkJoinColumns=@PrimaryKeyJoinColumn(name="REIMB_STATUS_ID")),
    @SecondaryTable(name = "ERS_REIMBURSEMENT_TYPE", pkJoinColumns=@PrimaryKeyJoinColumn(name="REIMB_TYPE_ID"))
})
public class Reimbursement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REIMB_ID")
    private int id;
    @Column(name = "REIMB_AMOUNT", nullable = false)
    private double amount;
    @Column(name = "REIMB_SUBMITTED", nullable = false)
    private Timestamp submitted;
    @Column(name = "REIMB_RESOLVED")
    private Timestamp resolved;
    @Column(name = "REIMB_DESCRIPTION")
    private String description;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="REIMB_AUTHOR", nullable = false)
    private User author;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="REIMB_RESOLVER")
    private User resolver;
    @Column(name="REIMB_STATUS", table="ERS_REIMBURSEMENT_STATUS", nullable = false)
    private String status;
    @Column(name="REIMB_TYPE", table="ERS_REIMBURSEMENT_TYPE", nullable = false)
    private String type;

    public Reimbursement(int id, double amount, Timestamp submitted, Timestamp resolved, String description, User author, User resolver, String status, String type){
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.status = status;
        this.type = type;
    }

    public Reimbursement(){
        super();
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public double getAmount(){
        return amount;
    }
    
    public void setAmount(double amount){
        this.amount = amount;
    }

    public Timestamp getSubmitted(){
        return submitted;
    }

    public void setSubmitted(Timestamp submitted){
        this.submitted = submitted;
    }

    public Timestamp getResolved(){
        return resolved;
    }

    public void setResolved(Timestamp resolved){
        this.resolved = resolved;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public User getAuthor(){
        return author;
    }

    public void setAuthor(User author){
        this.author = author;
    }

    public User getResolver(){
        return resolver;
    }

    public void setResolver(User resolver){
        this.resolver = resolver;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        String result = "id: " + id;
        result += ", amount: " + amount;
        result += ", submitted: " + submitted;
        result += ", resolved: " + resolved;
        result += ", description: " + description;
        result += ", author: " + author.getUsername();
        result += ", resolver: " + resolver.getUsername();
        result += ", status: " + status;
        result += ", type: " + type;
        return result;
    }

}
