package com.example.wishlistproject.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class wishlistRepositoryDB {

    @Value("${spring.datasource.url}")
    private String db_url;

    @Value("${spring.datasource.username}")
    private String uid;

    @Value("${spring.datasource.password}")
    private String pwd;

    public List<String> getWishLists() {
        List<String> wishLists = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(db_url, uid, pwd)) {
            String SQL = "SELECT listID, listName FROM wish_lists;";
            PreparedStatement preparedStatement = con.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                wishLists.add(resultSet.getString("listName"));
            }

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return wishLists;
    }

    // Need to create addWish that communicates with Controller


}
