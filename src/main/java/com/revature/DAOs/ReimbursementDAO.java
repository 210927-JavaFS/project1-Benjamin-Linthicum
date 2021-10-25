package com.revature.DAOs;

import com.revature.models.Reimbursement;
import java.util.List;

public interface ReimbursementDAO {
    
    public List<Reimbursement> findAllReimbursements();
    public Reimbursement findById(int id);
    public boolean addReimbursement(Reimbursement reimbursement);
    public boolean updateReimbursement(Reimbursement reimbursement);
    public boolean deleteReimbursement(Reimbursement reimbursement);

}
