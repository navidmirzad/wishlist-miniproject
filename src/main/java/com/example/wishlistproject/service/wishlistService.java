package com.example.wishlistproject.service;

import com.example.wishlistproject.dto.WishDTO;
import com.example.wishlistproject.dto.wishlistDTO;
import com.example.wishlistproject.model.User;
import com.example.wishlistproject.model.Wish;
import com.example.wishlistproject.model.Wishlist;
import com.example.wishlistproject.repositories.wishlistRepositoryDB;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class wishlistService {

    private wishlistRepositoryDB wishlistRepositoryDB;

    public wishlistService(wishlistRepositoryDB wishlistRepositoryDB) {
        this.wishlistRepositoryDB = wishlistRepositoryDB;
    }

    public void createWish(WishDTO wish) {
        wishlistRepositoryDB.createWish(wish);
    }

    public void deleteWish(int id) {
        wishlistRepositoryDB.deleteWish(id);
    }

    public WishDTO findWishById(int id) {
        return wishlistRepositoryDB.findWishById(id);
    }

    public void editWish(int id, WishDTO editedWish) {
        wishlistRepositoryDB.editWish(id, editedWish);
    }

    public void createUser(User user) {
        wishlistRepositoryDB.createUser(user);
    }

    public void createWishlist(int id, wishlistDTO wishlist) {
        wishlistRepositoryDB.createWishlist(id, wishlist);
    }

    public wishlistDTO findWishListById(int listid) {
        return wishlistRepositoryDB.findWishListById(listid);
    }

    public void editWishlist(int listid, wishlistDTO editedWishlist) {
        wishlistRepositoryDB.editWishlist(listid,editedWishlist);
    }

    public List<WishDTO> getWishes(int listid) {
        return wishlistRepositoryDB.getWishes(listid);
    }

  /*  public boolean checkLogin(String username, String password) {
        return wishlistRepositoryDB.checkLogin(username, password);
    }*/

    public User getUser(String uid) {
        return wishlistRepositoryDB.getUser(uid);
    }

    public User getUserById(int id) {
        return wishlistRepositoryDB.getUserById(id);
    }

    public void deleteAccount(int id) {
        wishlistRepositoryDB.deleteAccount(id);
    }

    public void deleteWishlist(int id) {
        wishlistRepositoryDB.deleteWishlist(id);
    }
    public List<wishlistDTO> getWishlists(int id) {
        return wishlistRepositoryDB.getWishlists(id);
    }

}