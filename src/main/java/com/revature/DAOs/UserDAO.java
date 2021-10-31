package com.revature.DAOs;

import com.revature.models.User;
import com.revature.models.Role;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.utils.HibernateUtil;

public interface UserDAO {
    
    public List<User> findAllUsers();
    public User findById(int id);
    public boolean addUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);

    public static boolean populateRoles(){
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(new Role(1, "Employee"));
            session.saveOrUpdate(new Role(2, "Manager"));
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

}
