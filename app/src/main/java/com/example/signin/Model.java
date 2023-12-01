package com.example.signin;

public class Model {
//    public String name;
//    public String email;
//    public String number;
//    public String City;
//    public String Address;
//    public String Pincode;
//
//    public String passw;
    public String Img_url;

    public Model() {
    }

    public Model(String img_url) {
//        this.name = name;
//        this.email = email;
//        this.number = number;
//        City = city;
//        Address = address;
//        Pincode = pincode;
//        this.passw = passw;
        this.Img_url = img_url;
    }

    public String getImg_url() {
        return Img_url;
    }

    public void setImg_url(String img_url) {
        Img_url = img_url;
    }
}
