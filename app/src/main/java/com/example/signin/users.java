package com.example.signin;

public class users {

    public String name;
    public String email;
    public String number;
    public String City;
    public String Address;
    public String Pincode;
//    public String passw;

    public users() {


    }

    public users(String name, String email, String number, String city, String address, String pincode) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.City = city;

        this.Address=address;
        Pincode = pincode;
//        this.passw = passw;
    }

}

