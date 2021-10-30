package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;

@Entity (name = "ERS_USERS")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ERS_USERS_ID")
    private int id;    
    @Column(name = "ERS_USER_NAME")
    private String username;
    @Column(name = "ERS_PASSWORD")
    private String password;
    @Column(name = "USER_FIRST_NAME")
    private String firstName;
    @Column(name = "USER_LAST_NAME")
    private String lastName;
    @Column(name = "USER_EMAIL")
    private String email;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="ERS_USER_ROLE_ID")
    @Column(name = "USER_ROLE_ID")
    private Role role;

    public User(int id, String username, String password, String firstName, String lastName, String email, Role role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Role getRole(){
        return role;
    }

    public void setRole(Role role){
        this.role = role;
    }

    @Override
    public String toString(){
        String result = "id: " + id;
        result += ", username: " + username;
        result += ", password: " + password;
        result += ", first name: " + firstName;
        result += ", last name: " + lastName;
        result += ", email: " + email;
        result += ", role: " + role;
        return result;
    }
}
