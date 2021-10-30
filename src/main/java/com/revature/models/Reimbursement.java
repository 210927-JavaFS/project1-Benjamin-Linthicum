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
import java.sql.Timestamp;

@Entity
public class Reimbursement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REIMB_ID")
    private int id;
    @Column(name = "REIMB_AMOUNT")
    private double amount;
    @Column(name = "REIMB_SUBMITTED")
    private Timestamp submitted;
    @Column(name = "REIMB_RESOLVED")
    private Timestamp resolved;
    @Column(name = "REIMB_DESCRIPTION")
    private String description;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="ERS_USERS_ID")
    @Column(name = "REIMB_AUTHOR")
    private User author;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="ERS_USERS_ID")
    @Column(name = "REIMB_RESOLVER")
    private User resolver;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="REIMB_STATUS_ID")
    @Column(name = "REIMB_STATUS_ID")
    private Status status;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="REIMB_TYPE_ID")
    @Column(name = "REIMB_TYPE_ID")
    private ReimbursementType type;

    public Reimbursement(int id, double amount, Timestamp submitted, Timestamp resolved, String description, User author, User resolver, Status status){
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.status = status;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public double getamount(){
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

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public ReimbursementType getType(){
        return type;
    }

    public void setType(ReimbursementType type){
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
