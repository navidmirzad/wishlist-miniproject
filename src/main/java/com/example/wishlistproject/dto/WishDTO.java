package com.example.wishlistproject.dto;

public class WishDTO {

    private int wishID;
    private String wishName;
    private String wishLink;
    private String wishDescription;
    private double wishPrice;
    private int wishCount;
    private String listName;
    private int listID;

    public WishDTO() {

    }

    public WishDTO(int wishID, String wishName, String wishLink, String wishDescription,
                   double wishPrice, int wishCount, String listName, int listID) {
        this.wishID = wishID;
        this.wishName = wishName;
        this.wishLink = wishLink;
        this.wishDescription = wishDescription;
        this.wishPrice = wishPrice;
        this.wishCount = wishCount;
        this.listName = listName;
        this.listID = listID;
    }

    public int getWishID() {
        return wishID;
    }

    public void setWishID(int wishID) {
        this.wishID = wishID;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public String getWishLink() {
        return wishLink;
    }

    public void setWishLink(String wishLink) {
        this.wishLink = wishLink;
    }

    public String getWishDescription() {
        return wishDescription;
    }

    public void setWishDescription(String wishDescription) {
        this.wishDescription = wishDescription;
    }

    public double getWishPrice() {
        return wishPrice;
    }

    public void setWishPrice(double wishPrice) {
        this.wishPrice = wishPrice;
    }

    public int getWishCount() {
        return wishCount;
    }

    public void setWishCount(int wishCount) {
        this.wishCount = wishCount;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }
}
