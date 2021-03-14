package com.example.pabokothay;

public class Shopkeeper {
    public String fullName,email,password,number,shopName,description,price;
    public Shopkeeper(){
    }

    public Shopkeeper(String fullName,String email,String password,String number,String shopName,String description,String price){
        this.fullName=fullName;
        this.email=email;
        this.password=password;
        this.number=number;
        this.shopName=shopName;
        this.price=price;
        this.description=description;
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
}

