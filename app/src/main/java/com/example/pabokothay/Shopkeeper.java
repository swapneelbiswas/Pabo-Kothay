package com.example.pabokothay;

public class Shopkeeper {
    public String fullName,email,password,number,shopName;
    public Shopkeeper(){
    }

    public Shopkeeper(String fullName,String email,String password,String number,String shopName){
        this.fullName=fullName;
        this.email=email;
        this.password=password;
        this.number=number;
        this.shopName=shopName;
    }
    public Shopkeeper(String fullName,String email){
        this.fullName=fullName;
        this.email=email;
    }
}

