package com.example.schoolproject;

// Handles the app's users
public class User {
    private String userName, userPwd, userPhone, userGamesWon, userShowInstructions;

    // Constructor
    public User(String userName, String userPwd, String userPhone, String userGamesWon, String userShowInstructions) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userPhone = userPhone;
        this.userGamesWon = userGamesWon;
        this.userShowInstructions = userShowInstructions;
    }

    // Constructor for the Register Fragment
    public User(String userName, String userPwd, String userPhone) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userPhone = userPhone;
        userGamesWon = "0";
        userShowInstructions = "" + true;
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
    // Returns the amount of objects the user has found (as a string like it is saved in the database)
    public String getUserGamesWon() {
        return userGamesWon;
    }
    // Changes if to show the user the instructions screen to input (saved as a string like it is saved in the database)
    public void setUserGamesWon(String userGamesWon) {
        this.userGamesWon = userGamesWon;
    }
    // Returns if to show the user the instructions screen (as a string like it is saved in the database)
    public String getUserShowInstructions() {
        return userShowInstructions;
    }
    // Changes the amount of objects the user has found to input (saved as a string like it is saved in the database)
    public void setUserShowInstructions(String userShowInstructions) {
        this.userShowInstructions = userShowInstructions;
    }
}