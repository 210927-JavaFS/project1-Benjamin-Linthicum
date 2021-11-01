package com.revature.DAOs;

import com.revature.models.User;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.utils.HibernateUtil;

public interface UserDAO {
    
    public List<User> findAllUsers();
    public User findByUsername(String username);
    public boolean addUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);

}
