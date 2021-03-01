package com.example.pabokothay;

public class User {

    public String fullName,email,password,number;
    public User(){
    }

    public User(String fullName,String email,String number,String password){
        this.fullName=fullName;
        this.email=email;
        this.password=password;
        this.number=number;
    }
}
