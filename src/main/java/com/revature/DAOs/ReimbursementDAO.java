package com.revature.DAOs;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.ReimbursementType;
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
    public List<Reimbursement> findReimbursementsByStatus(Status status);

    public static boolean populateStatuses(){
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(new Status(1, "Pending"));
            session.saveOrUpdate(new Status(2, "Approved"));
            session.saveOrUpdate(new Status(3, "Denied"));
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean populateTypes(){
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(new ReimbursementType(1, "LODGING"));
            session.saveOrUpdate(new ReimbursementType(2, "TRAVEL"));
            session.saveOrUpdate(new ReimbursementType(3, "FOOD"));
            session.saveOrUpdate(new ReimbursementType(4, "OTHER"));
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

}
