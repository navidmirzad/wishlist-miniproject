package com.example.wishlistproject.service;

import com.example.wishlistproject.dto.WishDTO;
import com.example.wishlistproject.dto.wishlistDTO;
import com.example.wishlistproject.model.User;
import com.example.wishlistproject.model.Wish;
import com.example.wishlistproject.model.Wishlist;
import com.example.wishlistproject.repositories.wishlistRepositoryDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class wishlistService {

    private wishlistRepositoryDB wishlistRepositoryDB;

    public wishlistService(wishlistRepositoryDB wishlistRepositoryDB) {
        this.wishlistRepositoryDB = wishlistRepositoryDB;
    }

    public List<String> getWishLists() {
        return wishlistRepositoryDB.getWishLists();
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

    public void createWishlist(wishlistDTO wishlist) {
        wishlistRepositoryDB.createWishlist(wishlist);
    }

    public List<WishDTO> getWishes() {
        return wishlistRepositoryDB.getWishes();
    }

}
