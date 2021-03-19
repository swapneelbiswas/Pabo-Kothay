package com.example.pabokothay;

public class ShopData {
    private String Shopdescribe;
    private String ShopName;
    private String price;
    private int  image;


    public ShopData() {

    }
    public ShopData(String shopdescribe, String shopName, String price, int image) {
        this.Shopdescribe = shopdescribe;
        ShopName = shopName;
        this.price = price;
        this.image = image;
    }

    public ShopData(String shopdescribe, String shopName) {
        Shopdescribe = shopdescribe;
        ShopName = shopName;
    }

    public ShopData(String shopdescribe, String shopName, String price) {
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
}
