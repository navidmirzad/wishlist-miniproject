package com.example.wishlistproject.dto;

public class wishlistDTO {

    private int listID;
    private String listName;
    private String listImageURL;

    public wishlistDTO(int listID, String listName, String listImageURL) {
        this.listID = listID;
        this.listName = listName;
        this.listImageURL = listImageURL;
    }

    public wishlistDTO() {

    }

    public String getListName() {
        return listName;
    }

    public String getListImageURL() {
        return listImageURL;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setListImageURL(String listImageURL) {
        this.listImageURL = listImageURL;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    @Override
    public String toString() {
        return "wishlistDTO{" +
                "listID='" + listID + '\'' +
                ", listName='" + listName + '\'' +
                ", listImageURL='" + listImageURL + '\'' +
                '}';
    }
}
