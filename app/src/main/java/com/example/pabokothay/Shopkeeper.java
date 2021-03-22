package com.example.pabokothay;

public class Shopkeeper {
    public String fullName,email,password,number,shopName,description,price,imageUrl;
    public Shopkeeper(){
    }

    public Shopkeeper(String fullName,String email,String password,String number,String shopName,String description){
        this.fullName=fullName;
        this.email=email;
        this.password=password;
        this.number=number;
        this.shopName=shopName;
        this.description=description;
    }
    public Shopkeeper(String fullName,String email,String number,String password,String imageUrl){
        this.fullName=fullName;
        this.email=email;
        this.password=password;
        this.number=number;
        this.imageUrl=imageUrl;
    }
    public Shopkeeper(String fullName,String email){
        this.fullName=fullName;
        this.email=email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

