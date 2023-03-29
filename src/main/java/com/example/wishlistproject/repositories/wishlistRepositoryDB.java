package com.example.wishlistproject.repositories;

import com.example.wishlistproject.model.User;
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
