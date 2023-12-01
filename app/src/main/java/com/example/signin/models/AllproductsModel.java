package com.example.signin.models;

import java.io.Serializable;

public class AllproductsModel implements Serializable {

    String pid;
    String description;
    String name;
    int ratings;
    String category;
    String type;
    int price;
    String img_url;
    int quantity;
    int maxprice;

    public AllproductsModel() {
    }

    public AllproductsModel(String description, String name,String pid, int ratings, String category, String type, int price, String img_url,int quantity,int maxprice) {
        this.description = description;
        this.name = name;
        this.ratings = ratings;
        this.category = category;
        this.type = type;
        this.price = price;
        this.img_url = img_url;
        this.quantity = quantity;
        this.pid=pid;
        this.maxprice=maxprice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(int maxprice) {
        this.maxprice = maxprice;
    }
}
