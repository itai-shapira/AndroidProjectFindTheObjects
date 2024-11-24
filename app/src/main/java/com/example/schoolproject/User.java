package com.example.schoolproject;

// Handles Users
public class User {
    private String userName, userPwd, userLastName, userPhone;

    public User(String userName, String userPwd, String userPhone) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userPhone = userPhone;
    }
    public User(String userName, String userPwd) {
        this.userName = userName;
        this.userPwd = userPwd;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPwd() {
        return userPwd;
    }
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    public String getUserPhone() {
        return userPhone;
    }
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
