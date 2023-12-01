package com.example.signin;

public class logInDetails {

    String userFullName;
    String userName;
    String userEmail;
    String userPassword;
    String userNumber;

    public logInDetails( String userName, String userEmail, String userPassword,String number) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNumber=number;

    }

    public String getUserFullName() {

        return userFullName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }
    public String getUserNumber() {
        return userNumber;
    }




}


