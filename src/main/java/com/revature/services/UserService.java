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

    public User getUser(String username){
        User user = userDao.findByUsername(username);
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

    public boolean deleteUser(String username){
        User user = getUser(username);
        return userDao.deleteUser(user);
    }

    public boolean login(UserDTO userDto){
        User user = userDao.findByUsername(userDto.username);

        if(user!=null && (String.valueOf(userDto.password.hashCode())==user.getPassword())){
            return true;
        }

        return false;
    }

}
