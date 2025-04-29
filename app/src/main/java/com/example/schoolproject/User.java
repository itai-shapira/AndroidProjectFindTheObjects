package com.example.schoolproject;

// Handles the app's users
public class User {
    private String userName, userPwd, userPhone, userGamesWon;

    // Constructor
    public User(String userName, String userPwd, String userPhone, String userGamesWon) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userPhone = userPhone;
        this.userGamesWon = userGamesWon;
    }

    // Constructor for the Register Activity
    public User(String userName, String userPwd, String userPhone) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userPhone = userPhone;
        userGamesWon = "0";
    }

    // Constructor for the login Activity
    public User(String userName, String userPwd) {
        this.userName = userName;
        this.userPwd = userPwd;
    }

    // Returns username
    public String getUserName() {
        return userName;
    }
    // Changes username to input
    public void setUserName(String userName) {
        this.userName = userName;
    }
    // Returns user password
    public String getUserPwd() {
        return userPwd;
    }
    // Changes user password to input
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    // Returns user phone number
    public String getUserPhone() {
        return userPhone;
    }
    // Changes user phone number to input
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    // Returns the amount of objects the user has found (in the way it is saved in the database)
    public String getUserGamesWon() {
        return userGamesWon;
    }
    // Changes the amount of objects the user has found to input (saved in the way it is saved in the database)
    public void setUserGamesWon(String userGamesWon) {
        this.userGamesWon = userGamesWon;
    }
}
