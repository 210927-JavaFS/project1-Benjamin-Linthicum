package com.revature.DAOs;
import com.revature.models.Reimbursement;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.utils.HibernateUtil;

import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO{

    public List<Reimbursement> findAllReimbursements(){
        return null; // TODO
    }
    public Reimbursement findById(int id){
        return null; // TODO
    }
    public boolean addReimbursement(Reimbursement reimbursement){
        return false; //TODO
    }
    public boolean updateReimbursement(Reimbursement reimbursement){
        return false; //TODO
    }
    public boolean deleteReimbursement(Reimbursement reimbursement){
        return false; //TODO
    }
    
}
