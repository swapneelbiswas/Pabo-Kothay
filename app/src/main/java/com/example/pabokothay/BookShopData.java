package com.example.pabokothay;

public class BookShopData {
    private String Shopdescribe;
    private String ShopName;
    private String price;
    private String  image;
    private String shopkeeperId;
    private float rating;

    public BookShopData() {

    }

    public BookShopData(String shopdescribe, String shopName, String price, String image, String shopkeeperId, float rating) {
        Shopdescribe = shopdescribe;
        ShopName = shopName;
        this.price = price;
        this.image = image;
        this.shopkeeperId = shopkeeperId;
        this.rating = rating;
    }

    public BookShopData(String shopdescribe, String shopName, String price, String shopkeeperId) {
        Shopdescribe = shopdescribe;
        ShopName = shopName;
        this.price = price;
        this.shopkeeperId = shopkeeperId;
    }


    public BookShopData(String shopdescribe, String shopName, String price) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
