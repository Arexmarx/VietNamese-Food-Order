/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author Nguyễn Nhật Trường
 */
public class Account implements Serializable{
    private int accid;
    private String userName;
    private String password;
    private String phone;
    private String role;
    private String fullName;
    private String email;
    private boolean isActive;

    public Account() {
    }

    public Account(int accid, String userName, String password, String phone, String role, String fullName, String email, boolean isActive) {
        this.accid = accid;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.fullName = fullName;
        this.email = email;
        this.isActive = isActive;
    }

    public Account(String userName, String password, String phone, String fullName, String email) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.fullName = fullName;
        this.email = email;
    }

    public int getAccid() {
        return accid;
    }

    public void setAccid(int accid) {
        this.accid = accid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Account{" + "accid=" + accid + ", userName=" + userName + ", password=" + password + ", phone=" + phone + ", role=" + role + ", fullName=" + fullName + ", email=" + email + ", isActive=" + isActive + '}';
    }
}
