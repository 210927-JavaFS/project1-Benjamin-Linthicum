package com.revature.models;
import java.sql.Timestamp;
import java.util.Objects;
import com.revature.models.Reimbursement.*;

// scrubs user passwords from reimbursements, allowing them to be passed safely to frontend
public class SanitizedReimbursement {
    public int id;
    public double amount;
    public Timestamp submitted;
    public Timestamp resolved;
    public String description;
    public String author; // only the username
    public String resolver; // only the username
    public ReimburseStatus status;
    public ReimburseType type;

    public SanitizedReimbursement(Reimbursement r){
        id = r.getId();
        amount = r.getAmount();
        submitted = r.getSubmitted();
        resolved = r.getResolved();
        description = r.getDescription();
        author = r.getAuthor().getUsername();
        if(Objects.isNull(r.getResolver())){
            resolver = "N/A";
        }
        else{
            resolver = r.getResolver().getUsername();
        }
        status = r.getStatus();
        type = r.getType();
    }

    public SanitizedReimbursement(){
    }

    public Reimbursement convertToReimbursement(){
        Reimbursement r = new Reimbursement();
        r.setId(id);
        r.setAmount(amount);
        r.setSubmitted(submitted);
        r.setResolved(resolved);
        r.setDescription(description);
        r.setStatus(status);
        r.setType(type);
        return r;
    }
}
