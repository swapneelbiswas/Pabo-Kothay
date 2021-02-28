package com.example.pabokothay;

public class User {

    public String fullName,email,password;
    public User(){

    }
    public User(String fullName,String email){
        this.fullName=fullName;
        this.email=email;
    }
    public User(String fullName,String email,String password){
        this.fullName=fullName;
        this.email=email;
        this.password=password;
    }
}
