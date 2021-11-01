package com.revature.models;

import com.revature.models.User.*;

public class UserRegistration {
    
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String email;
    public User_Role role;

    public User convertToUser(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRole(role);
        return user;
    }

}
