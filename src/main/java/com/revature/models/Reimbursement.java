package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
public class Reimbursement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;
    private Timestamp submitted;
    private Timestamp resolved;
    private String description;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="users_id")
    private User author;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="users_id")
    private User resolver;
    private String status;

    public Reimbursement(int id, double amount, Timestamp submitted, Timestamp resolved, String description, User author, User resolver, String status){
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

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public String toString(){
        String result = "id: " + id;
        result += ", amount: " + amount;
        result += ", submitted: " + submitted;
        result += ", resolved: " + resolved;
        result += ", description: " + description;
        result += ", author: " + author.getName();
        result += ", resolver: " + resolver.getName();
        result += ", status: " + status;
        return result;
    }

}
