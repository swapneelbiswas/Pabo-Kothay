package com.example.pabokothay;

public class FurnitureData {
    private String Shopdescribe;
    private String ShopName;
    private String price;
    private int  image;
    private String shopkeeperId;
    private float rating;

    public FurnitureData() {

    }

    public FurnitureData(String shopdescribe, String shopName, String price, int image, String shopkeeperId, float rating) {
        Shopdescribe = shopdescribe;
        ShopName = shopName;
        this.price = price;
        this.image = image;
        this.shopkeeperId = shopkeeperId;
        this.rating = rating;
    }

    public FurnitureData(String shopdescribe, String shopName, String price, int image) {
        this.Shopdescribe = shopdescribe;
        this.ShopName = shopName;
        this.price = price;
        this.image = image;
    }

    public FurnitureData(String shopdescribe, String shopName) {
        Shopdescribe = shopdescribe;
        ShopName = shopName;
    }

    public FurnitureData(String shopdescribe, String shopName, String price) {
        Shopdescribe = shopdescribe;
        ShopName = shopName;
        this.price = price;
    }

    public String getShopdescribe() {
        return Shopdescribe;
    }

    public void setShopdescribe(String shopdescribe) {
        this.Shopdescribe = shopdescribe;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
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
