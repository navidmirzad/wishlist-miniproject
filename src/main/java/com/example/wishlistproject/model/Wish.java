package com.example.wishlistproject.model;

import java.util.List;

public class Wish {

    private String wishID;
    private String wishName;
    private String wishLink;
    private String wishDescription;
    private double wishPrice;
    private int wishCount;
    private int listID;
    List<String> wishLists;

    public Wish(String wishID, String wishName, String wishLink, String wishDescription,
                double wishPrice, int wishCount, int listID, List wishLists) {
        this.listID = listID;
        this.wishName = wishName;
        this.wishLink = wishLink;
        this.wishDescription = wishDescription;
        this.wishPrice = wishPrice;
        this.wishCount = wishCount;
        this.listID = listID;
        this.wishLists = wishLists;
    }

    public Wish() {

    }

    public String getWishID() {
        return wishID;
    }

    public String getWishName() {
        return wishName;
    }

    public String getWishLink() {
        return wishLink;
    }

    public String getWishDescription() {
        return wishDescription;
    }

    public double getWishPrice() {
        return wishPrice;
    }

    public int getWishCount() {
        return wishCount;
    }

    public int getListID() {
        return listID;
    }

    public List<String> getWishLists() {
        return wishLists;
    }

    public void setWishID(String wishID) {
        this.wishID = wishID;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public void setWishLink(String wishLink) {
        this.wishLink = wishLink;
    }

    public void setWishDescription(String wishDescription) {
        this.wishDescription = wishDescription;
    }

    public void setWishPrice(double wishPrice) {
        this.wishPrice = wishPrice;
    }

    public void setWishCount(int wishCount) {
        this.wishCount = wishCount;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public void setWishLists(List<String> wishLists) {
        this.wishLists = wishLists;
    }

    @Override
    public String toString() {
        return "Wish{" +
                "wishID='" + wishID + '\'' +
                ", wishName='" + wishName + '\'' +
                ", wishLink='" + wishLink + '\'' +
                ", wishDescription='" + wishDescription + '\'' +
                ", wishPrice=" + wishPrice +
                ", wishCount=" + wishCount +
                ", listID=" + listID +
                ", wishLists=" + wishLists +
                '}';
    }
}
