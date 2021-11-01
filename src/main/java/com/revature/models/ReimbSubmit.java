package com.revature.models;

import com.revature.models.Reimbursement.*;
import java.sql.Timestamp;
import com.revature.DAOs.UserDAOImpl;

public class ReimbSubmit {
    
    public double amount;
    public String description;
    public String author;
    public ReimburseType type;

    public Reimbursement convertToReimbursement() {
        Reimbursement reimbursement = new Reimbursement();
        reimbursement.setAmount(amount);
        reimbursement.setDescription(description);
        reimbursement.setType(type);
        reimbursement.setAuthor(new UserDAOImpl().findByUsername(author));
        reimbursement.setSubmitted(new Timestamp(System.currentTimeMillis()));
        reimbursement.setStatus(ReimburseStatus.Pending);
        return reimbursement;
    }
}
