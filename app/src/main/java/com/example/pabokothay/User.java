package com.example.pabokothay;

public class User {

    public String fullName,email,password,number,imageUrl;
    public User(){
    }
    public User(String fullName,String email,String number,String password){
        this.fullName=fullName;
        this.email=email;
        this.password=password;
        this.number=number;

    }

    public User(String fullName,String email,String number,String password,String imageUrl){
        this.fullName=fullName;
        this.email=email;
        this.password=password;
        this.number=number;
        this.imageUrl="https://i.imgur.com/tGbaZCY.jpg";
    }
}
