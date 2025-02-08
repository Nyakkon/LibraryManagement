/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * @Project created on 24-05-2024 by miko
 * @Email miko@wibu.me
 *
 */

import java.io.Serializable;
import java.util.Date;
public class User implements Serializable{
    private int id;
    private String name;
    private Date dob;
    private String phoneNumber;
    private String email;
    private boolean active_user;

    public User(int id, String name, Date dob, String phoneNumber, String email, boolean active_user) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.active_user = active_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive_user() {
        return active_user;
    }

    public void setActive_user(boolean active_user) {
        this.active_user = active_user;
    }
    
    
}
