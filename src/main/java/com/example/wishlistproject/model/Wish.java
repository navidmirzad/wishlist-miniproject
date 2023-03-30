package com.example.wishlistproject.model;

import java.util.List;

public class Wish {

    private int wishID;
    private String wishName;
    private String wishLink;
    private String wishImageURL;
    private String wishDescription;
    private double wishPrice;
    private int wishCount;
    private int listID;
    List<String> wishLists;

    public Wish(int wishID, String wishName, String wishLink, String wishImageURL, String wishDescription,
                double wishPrice, int wishCount, int listID) {

        this.wishID = wishID;
        this.wishName = wishName;
        this.wishLink = wishLink;
        this.wishImageURL = wishImageURL;
        this.wishDescription = wishDescription;
        this.wishPrice = wishPrice;
        this.wishCount = wishCount;
        this.listID = listID;
    }

    public Wish() {

    }

    public String getWishImageURL() {
        return wishImageURL;
    }

    public int getWishID() {
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

    public void setWishID(int wishID) {
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

    public void setWishImageURL(String wishImageURL) {
        this.wishImageURL = wishImageURL;
    }

    @Override
    public String toString() {
        return "Wish{" +
                "wishID=" + wishID +
                ", wishName='" + wishName + '\'' +
                ", wishLink='" + wishLink + '\'' +
                ", wishImageURL='" + wishImageURL + '\'' +
                ", wishDescription='" + wishDescription + '\'' +
                ", wishPrice=" + wishPrice +
                ", wishCount=" + wishCount +
                ", listID=" + listID +
                ", wishLists=" + wishLists +
                '}';
    }
}
