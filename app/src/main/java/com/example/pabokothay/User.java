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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
