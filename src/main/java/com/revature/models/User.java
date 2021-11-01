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
import javax.persistence.Table;
import javax.persistence.SecondaryTable;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Table (name = "ERS_USERS")
@SecondaryTable(name = "ERS_USER_ROLES", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ERS_USER_ROLE_ID"))
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ERS_USERS_ID")
    private int id;    
    @Column(name = "ERS_USER_NAME", unique = true, nullable = false)
    private String username;
    @Column(name = "ERS_PASSWORD", nullable = false)
    private String password;
    @Column(name = "USER_FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "USER_LAST_NAME", nullable = false)
    private String lastName;
    @Column(name = "USER_EMAIL", unique = true, nullable = false)
    private String email;
    @Column(name = "USER_ROLE", table = "ERS_USER_ROLES")
    private String role;

    public User(int id, String username, String password, String firstName, String lastName, String email, String role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public User(){
        super();
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

    public String getRole(){
        return role;
    }

    public void setRole(String role){
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
