package com.example.wishlistproject.model;

public class Wish {

    private String wishName;
    private String wishLink;
    private String wishDescription;
    private double wishPrice;
    private int wishCount;

    public Wish(String wishName, String wishLink, String wishDescription, double wishPrice, int wishCount) {
        this.wishName = wishName;
        this.wishLink = wishLink;
        this.wishDescription = wishDescription;
        this.wishPrice = wishPrice;
        this.wishCount = wishCount;
    }

    public Wish() {

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

    @Override
    public String toString() {
        return "Wish{" +
                "wishName='" + wishName + '\'' +
                ", wishLink='" + wishLink + '\'' +
                ", wishDescription='" + wishDescription + '\'' +
                ", wishPrice=" + wishPrice +
                ", wishCount=" + wishCount +
                '}';
    }
}
