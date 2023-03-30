package com.example.wishlistproject.dto;

public class wishlistDTO {

    private String listName;
    private String listImageURL;

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

    @Override
    public String toString() {
        return "wishlistDTO{" +
                "listName='" + listName + '\'' +
                ", listImageURL='" + listImageURL + '\'' +
                '}';
    }
}
