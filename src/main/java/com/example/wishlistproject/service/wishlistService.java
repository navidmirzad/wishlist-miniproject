package com.example.wishlistproject.service;

import com.example.wishlistproject.model.User;
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

    public void createUser(User user) {
        wishlistRepositoryDB.createUser(user);
    }

    //addwish service redirect to wishRepositoryDB


}
