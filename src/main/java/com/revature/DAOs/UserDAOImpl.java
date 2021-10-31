package com.revature.DAOs;

import com.revature.models.User;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.revature.utils.HibernateUtil;

public class UserDAOImpl implements UserDAO{
    
    @Override
    public List<User> findAllUsers(){
        Session session = HibernateUtil.getSession();
        return session.createQuery("FROM ERS_USERS").list();
    }

    @Override
    public User findByUsername(String username){
        Session session = HibernateUtil.getSession();
        return session.get(User.class, username);
    }

    @Override
    public boolean addUser(User user){
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User user){
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(User user){
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

}
