package com.example.wishlistproject.dto;

public class wishlistDTO {

    private String listID;
    private String listName;
    private String listImageURL;

    public wishlistDTO(String listID, String listName, String listImageURL, String userID) {
        this.listID = listID;
        this.listName = listName;
        this.listImageURL = listImageURL;
    }

    public wishlistDTO(String listName, String listImageURL) {
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

    public String getListID() {
        return listID;
    }

    public void setListID(String listID) {
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
