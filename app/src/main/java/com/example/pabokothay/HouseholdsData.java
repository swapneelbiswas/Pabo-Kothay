package com.example.pabokothay;

public class HouseholdsData {
    public String shopdescribe;
    public  String shopName;
    public  String price;
    private String shopkeeperId;
    private float rating;
    private int  image;


    public HouseholdsData() {

    }

    public HouseholdsData(String shopdescribe, String shopName, String price, String shopkeeperId, float rating, int image) {
        this.shopdescribe = shopdescribe;
        this.shopName = shopName;
        this.price = price;
        this.shopkeeperId = shopkeeperId;
        this.rating = rating;
        this.image = image;
    }

    public HouseholdsData(String shopdescribe, String shopName, String price, int image) {
        this.shopdescribe = shopdescribe;
        this.shopName = shopName;
        this.price = price;
        this.image = image;
    }

    public HouseholdsData(String shopdescribe, String shopName) {
        this.shopdescribe = shopdescribe;
        this.shopName = shopName;
    }

    public HouseholdsData(String shopdescribe, String shopName, String price) {
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
