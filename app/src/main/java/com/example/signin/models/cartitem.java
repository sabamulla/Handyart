package com.example.signin.models;

public class cartitem {

    private AllproductsModel product;
   private int quantity;

    public cartitem(AllproductsModel product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public AllproductsModel getProduct() {
        return product;
    }

    public void setProduct(AllproductsModel product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
