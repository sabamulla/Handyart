package com.example.signin.models;

import java.io.Serializable;

public class MyCartModel implements Serializable {

    String currentTime;
    String currentDate;
    String id;
    String price;
    String img_url;
    String name;
    String maxprice;
    String description;
    String ratings;

    public MyCartModel() {

    }

    public MyCartModel(String currentTime, String currentDate, String id, String price, String img_url, String name,String maxprice,  String description,String ratings) {
        this.currentTime = currentTime;
        this.currentDate = currentDate;
        this.id = id;
        this.price = price;
        this.img_url = img_url;
        this.name = name;
        this.maxprice=maxprice;
        this.description=description;
        this.ratings=ratings;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(String maxprice) {
        this.maxprice = maxprice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }
}
