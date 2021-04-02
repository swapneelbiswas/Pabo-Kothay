package com.example.pabokothay;

public class ShopType {
    public static String shopType;
    public static String shopID;

    public ShopType() {
    }

    public static String getShopID() {
        return shopID;
    }

    public static void setShopID(String shopID) {
        ShopType.shopID = shopID;
    }

    public ShopType(String shopType) {
        this.shopType = shopType;
    }
    public String getShopType() {
        return shopType;
    }
    public void setShopType(String shopType) {
        this.shopType = shopType;
    }
}
