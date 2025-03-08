package com.example.schoolproject;

// Handles Users
public class User {
    private String userName, userPwd, userPhone, userFoundObjects;

    public User(String userName, String userPwd, String userPhone, String userFoundObjects) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userPhone = userPhone;
        this.userFoundObjects = userFoundObjects;
    }
    public User(String userName, String userPwd, String userPhone) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userPhone = userPhone;
        userFoundObjects = "000000";
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
    public String getUserFoundObjects() {
        return userFoundObjects;
    }
    public void setUserFoundObjects(String userFoundObjects) {
        this.userFoundObjects = userFoundObjects;
    }
    public Boolean[] getFoundObjectsArray() {
        Boolean[] foundObjects_arr = new Boolean[6];
        for (int i = 0; i < userFoundObjects.length(); i++) {
            if ((int)userFoundObjects.charAt(i) == 1)
                foundObjects_arr[i] = true;
        }
        return foundObjects_arr;
    }
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
