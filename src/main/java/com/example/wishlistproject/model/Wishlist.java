package com.example.wishlistproject.model;

public class Wishlist {

    private int listID;
    private String listName;
    private int userID;

    public Wishlist(int listID, String listName, int userID) {
        this.listID = listID;
        this.listName = listName;
        this.userID = userID;
    }

    public Wishlist() {

    }

    public int getListID() {
        return listID;
    }

    public String getListName() {
        return listName;
    }

    public int getUserID() {
        return userID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "listID=" + listID +
                ", listName='" + listName + '\'' +
                ", userID=" + userID +
                '}';
    }

}
