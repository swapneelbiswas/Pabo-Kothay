package com.example.pabokothay;

public class SportsData {
    public String shopdescribe;
    public  String shopName;
    public  String price;
    private int  image;
    private String shopkeeperId;
    private float rating;


    public SportsData() {

    }

    public SportsData(String shopdescribe, String shopName, String price, int image, String shopkeeperId, float rating) {
        this.shopdescribe = shopdescribe;
        this.shopName = shopName;
        this.price = price;
        this.image = image;
        this.shopkeeperId = shopkeeperId;
        this.rating = rating;
    }

    public SportsData(String shopdescribe, String shopName, String price, int image) {
        this.shopdescribe = shopdescribe;
        this.shopName = shopName;
        this.price = price;
        this.image = image;
    }

    public SportsData(String shopdescribe, String shopName) {
        this.shopdescribe = shopdescribe;
        this.shopName = shopName;
    }

    public SportsData(String shopdescribe, String shopName, String price) {
        this.shopdescribe = shopdescribe;
        this.shopName = shopName;
        this.price = price;
    }

    public String getShopdescribe() {
        return shopdescribe;
    }

    public void setShopdescribe(String shopdescribe) {
        this.shopdescribe = shopdescribe;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getShopkeeperId() {
        return shopkeeperId;
    }

    public void setShopkeeperId(String shopkeeperId) {
        this.shopkeeperId = shopkeeperId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
