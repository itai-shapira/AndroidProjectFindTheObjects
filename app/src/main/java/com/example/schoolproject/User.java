package com.example.schoolproject;

// Handles the app's ssers
public class User {
    private String userName, userPwd, userPhone, userFoundObjects;

    // Constructor
    public User(String userName, String userPwd, String userPhone, String userFoundObjects) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userPhone = userPhone;
        this.userFoundObjects = userFoundObjects;
    }

    // Constructor for the Register Activity
    public User(String userName, String userPwd, String userPhone) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userPhone = userPhone;
        userFoundObjects = "000000";
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
    public String getUserFoundObjects() {
        return userFoundObjects;
    }
    // Changes the amount of objects the user has found to input (saved in the way it is saved in the database)
    public void setUserFoundObjects(String userFoundObjects) {
        this.userFoundObjects = userFoundObjects;
    }
    // Returns the amount of objects the user has found out of the total objects
    public String getFoundObjectsString() {
        int alreadyFound = 0;
        for (int i = 0; i < userFoundObjects.length(); i++) {
            if (userFoundObjects.charAt(i) == '1') {
                alreadyFound++;
            }
        }
        return "" + alreadyFound + "/" + userFoundObjects.length();
    }
}
