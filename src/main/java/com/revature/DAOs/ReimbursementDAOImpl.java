package com.revature.DAOs;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.utils.HibernateUtil;

import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO{

    @Override
    public List<Reimbursement> findAllReimbursements(){
        Session session = HibernateUtil.getSession();
        return session.createQuery("FROM ERS_REIMBURSEMENT").list();
    }

    @Override
    public Reimbursement findById(int id){
        Session session = HibernateUtil.getSession();
        return session.get(Reimbursement.class, id);
    }

    @Override
    public boolean addReimbursement(Reimbursement reimbursement){
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.save(reimbursement);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateReimbursement(Reimbursement reimbursement){
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.merge(reimbursement);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteReimbursement(Reimbursement reimbursement){
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.delete(reimbursement);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Reimbursement> findReimbursementsByStatus(Status status){
        Session session = HibernateUtil.getSession();
        return session.createQuery("FROM ERS_REIMBURSEMENT WHERE REIMB_STATUS_ID = " + status.getId()).list();
    }
    
}
