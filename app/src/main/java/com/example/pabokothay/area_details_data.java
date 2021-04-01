package com.example.pabokothay;

public class area_details_data {
    public String shopdescribe;
    public  String shopName;
    public  String price;
    public int  image;
    public String shopkeeperId;
    public float rating;

    public area_details_data() {

    }

    public area_details_data(String shopdescribe, String shopName, String price, int image, float rating) {
        this.shopdescribe = shopdescribe;
        this.shopName = shopName;
        this.price = price;
        this.image = image;
        this.rating = rating;
    }

    public area_details_data(String shopdescribe, String shopName, String price, int image, String shopkeeperId, float rating) {
        this.shopdescribe = shopdescribe;
        this.shopName = shopName;
        this.price = price;
        this.image = image;
        this.shopkeeperId = shopkeeperId;
        this.rating = rating;
    }

    public area_details_data(String shopdescribe, String shopName, String price, int image) {
        this.shopdescribe = shopdescribe;
        this.shopName = shopName;
        this.price = price;
        this.image = image;
    }

    public area_details_data(String shopdescribe, String shopName) {
        this.shopdescribe = shopdescribe;
        this.shopName = shopName;
    }

    public area_details_data(String shopdescribe, String shopName, String price) {
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

    public float getRating() { return rating;
    }

    public String getShopkeeperId() {
        return shopkeeperId;
    }

    public void setShopkeeperId(String shopkeeperId) {
        this.shopkeeperId = shopkeeperId;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
