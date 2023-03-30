package com.example.wishlistproject.repositories;

import com.example.wishlistproject.dto.WishDTO;
import com.example.wishlistproject.model.User;
import com.example.wishlistproject.model.Wishlist;
import com.example.wishlistproject.model.Wish;
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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(db_url,uid,pwd);
    }

    public List<String> getWishLists() {
        List<String> wishLists = new ArrayList<>();

        try (Connection con = getConnection()) {
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

    public void createWish(WishDTO wish) {

        try (Connection con = getConnection()) {
            // ID's
            int listID = 0;

            // find listID
            String findListID = "select listID from wish_lists where listName = ?;";
            PreparedStatement pstmt = con.prepareStatement(findListID);
            pstmt.setString(1, wish.getListName());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                listID = rs.getInt("listID");
            }

            String createWish = "insert into wishes (wishName, wishLink, wishImageURL, wishDescription, wishPrice, wishCount, listID) "
                    + "values(?, ?, ?, ?, ?, ?, ?);";

            pstmt = con.prepareStatement(createWish, Statement.RETURN_GENERATED_KEYS); // return autoincremented keys
            pstmt.setString(1, wish.getWishName());
            pstmt.setString(2, wish.getWishLink());
            pstmt.setString(3, wish.getWishImageURL());
            pstmt.setString(4, wish.getWishDescription());
            pstmt.setDouble(5, wish.getWishPrice());
            pstmt.setInt(6, wish.getWishCount());
            pstmt.setInt(7, listID);
            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(User user) {

        try (Connection con = getConnection()) {

            String insertUser = "INSERT INTO users(userName,userPassword,firstName,lastName,birthDate,gender,email,phoneNumber)\n" +
                    "VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(insertUser);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getBirthdate());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setString(7, user.getEmail());
            preparedStatement.setInt(8, user.getPhoneNumber());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
