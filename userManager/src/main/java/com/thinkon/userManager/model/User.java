package com.thinkon.userManager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

//User information

@Entity
@Table(name = "usersdb")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "USERNAME NEEDED")
    private String userName;

    @Column(nullable = false)
    @NotBlank(message = "FIRST NAME NEEDED")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "LAST NAME NEEDED")
    private String lastName;

    @Column(nullable = false)
    @NotBlank(message = "EMAIL NEEDED")
    @Email(message = "INVALID EMAIL")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "PHONE NUMBER NEEDED")
    @Pattern (regexp = "\\+?[0-9]{7,15}", message = "INVALID PHONE NUMBER")
    private String phoneNumber;

    //Constructors


    public User(){

    }

    public User(String userName, String firstName, String lastName, String email, String phoneNumber){
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    //Getters

    public String getUserName(){
        return userName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public Long getID(){
        return id;
    }

    //Setters

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setID(Long id){
        this.id = id;
    }
}
