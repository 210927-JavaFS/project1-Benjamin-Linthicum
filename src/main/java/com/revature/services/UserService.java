package com.revature.services;

import com.revature.models.*;
import com.revature.DAOs.UserDAOImpl;
import com.revature.DAOs.UserDAO;

import java.util.List;

public class UserService {
    
    private UserDAO userDao = new UserDAOImpl();

    public List<User> getAllUsers(){
        return userDao.findAllUsers();
    }

    public User getUser(int id){
        User user = userDao.findById(id);
        if(user != null){
            return user;
        }
        else{
            return new User();
        }
    }

    public boolean addUser(User user){
        return userDao.addUser(user);
    }

    public boolean updateUser(User user){
        return userDao.updateUser(user);
    }

    public boolean deleteUser(int id){
        User user = getUser(id);
        return userDao.deleteUser(user);
    }

}
