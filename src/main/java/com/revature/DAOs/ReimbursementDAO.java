package com.revature.DAOs;

import com.revature.models.Reimbursement;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.utils.HibernateUtil;

public interface ReimbursementDAO {
    
    public List<Reimbursement> findAllReimbursements();
    public Reimbursement findById(int id);
    public boolean addReimbursement(Reimbursement reimbursement);
    public boolean updateReimbursement(Reimbursement reimbursement);
    public boolean deleteReimbursement(Reimbursement reimbursement);
    public List<Reimbursement> findReimbursementsByStatus(String status);
    public List<Reimbursement> findReimbursementsByUsername(String username);
    public boolean approveReimbursement(int id);
    public boolean denyReimbursement(int id);

}
