package com.revature.services;

import com.revature.models.*;
import com.revature.DAOs.ReimbursementDAOImpl;
import com.revature.DAOs.ReimbursementDAO;

import java.util.List;

public class ReimbursementService {
    
    private ReimbursementDAO reimbursementDao = new ReimbursementDAOImpl();

    public List<Reimbursement> getAllReimbursements() {
        return reimbursementDao.findAllReimbursements();
    }

    public Reimbursement getReimbursement(int id) {
        Reimbursement reimbursement = reimbursementDao.findById(id);
        if(reimbursement != null){
            return reimbursement;
        }
        else{
            return new Reimbursement();
        }
    }

    public boolean addReimbursement(Reimbursement reimbursement) {
        return reimbursementDao.addReimbursement(reimbursement);
    }

    public boolean updateReimbursement(Reimbursement reimbursement) {
        return reimbursementDao.updateReimbursement(reimbursement);
    }

    public boolean deleteReimbursement(int id) {
        Reimbursement reimbursement = getReimbursement(id);
        return reimbursementDao.deleteReimbursement(reimbursement);
    }

    public List<Reimbursement> getReimbursementsByStatus(int statusId){
        return reimbursementDao.findReimbursementsByStatus(statusId);
    }
}
