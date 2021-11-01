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
import javax.persistence.EnumType;
import java.sql.Timestamp;
import javax.persistence.SecondaryTables;
import javax.persistence.SecondaryTable;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Enumerated;

@Entity
@Table (name = "ERS_REIMBURSEMENT")
public class Reimbursement {
    
    public enum ReimburseStatus { Pending, Approved, Denied};
	public enum ReimburseType { LODGING, TRAVEL, FOOD, OTHER};

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
    @Enumerated(EnumType.STRING)
	@Column(name="REIMB_STATUS", nullable=false)
	private ReimburseStatus status;
	
	@Enumerated(EnumType.STRING)
	@Column(name="REIMB_TYPE", nullable=false)
	private ReimburseType type;

    public Reimbursement(int id, double amount, Timestamp submitted, Timestamp resolved, String description, User author, User resolver, ReimburseStatus status, ReimburseType type){
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

    public ReimburseStatus getStatus(){
        return status;
    }

    public void setStatus(ReimburseStatus status){
        this.status = status;
    }

    public ReimburseType getType(){
        return type;
    }

    public void setType(ReimburseType type){
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
