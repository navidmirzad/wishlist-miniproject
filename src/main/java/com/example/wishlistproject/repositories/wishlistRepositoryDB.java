package com.example.wishlistproject.repositories;

import com.example.wishlistproject.dto.WishDTO;
import com.example.wishlistproject.dto.wishlistDTO;
import com.example.wishlistproject.model.User;
import jakarta.servlet.http.HttpSession;
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

    public List<wishlistDTO> getWishlists(int id) {
        List<wishlistDTO> wishLists = new ArrayList<>();

        try (Connection con = getConnection()) {


            String sql = "SELECT listID, listName, listImageURL, userid FROM wish_lists WHERE userid = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                wishLists.add(new wishlistDTO(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3)));
            }

            return wishLists;
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
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

    public void createWishlist(int id, wishlistDTO wishlist) {

        try (Connection con = getConnection()) {

            String insertList = "INSERT INTO wish_lists (listName, listImageURL, userid) \n" +
                    "VALUES(?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(insertList);
            preparedStatement.setString(1, wishlist.getListName());
            preparedStatement.setString(2, wishlist.getListImageURL());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public wishlistDTO findWishListById(int listid) {

        wishlistDTO wishlist = null;

        try(Connection con = getConnection()) {
            String sql = "SELECT * FROM wish_lists WHERE listid = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,listid);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                wishlist = new wishlistDTO();
                wishlist.setListID(resultSet.getInt("listid"));
                wishlist.setListName(resultSet.getString("listName"));
                wishlist.setListImageURL(resultSet.getString("listImageURL"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishlist;
    }

    public void editWishlist(int listid, wishlistDTO editedWishlist) {

        try (Connection con = getConnection()) {

            //find wishlist and set it to editedWishlist
            String sql = "UPDATE wish_lists SET listName = ?, listImageURL = ? WHERE listid = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, editedWishlist.getListName());
            preparedStatement.setString(2, editedWishlist.getListImageURL());
            preparedStatement.setInt(3, listid);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<WishDTO> getWishes(int listid) {

        List<WishDTO> wishes = new ArrayList<>();

        try (Connection con = getConnection()){
            String sql = "SELECT wishid, wishname, wishlink, wishimageurl, wishdescription, wishprice, wishcount\n" +
                    "FROM wishes WHERE listid = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,listid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {

                wishes.add(new WishDTO(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDouble(6),
                        resultSet.getInt(7)));

            }
            return wishes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public WishDTO findWishById(int id) {

        WishDTO wish = null;

        try(Connection con = getConnection()) {
           String sql = "SELECT * FROM wishes WHERE wishid = ?";
           PreparedStatement preparedStatement = con.prepareStatement(sql);
           preparedStatement.setInt(1,id);
           ResultSet resultSet = preparedStatement.executeQuery();

           if (resultSet.next()) {
               wish = new WishDTO();
               wish.setWishID(resultSet.getInt("wishid"));
               wish.setWishName(resultSet.getString("wishName"));
               wish.setWishLink(resultSet.getString("wishLink"));
               wish.setWishImageURL(resultSet.getString("wishImageURL"));
               wish.setWishDescription(resultSet.getString("wishDescription"));
               wish.setWishPrice(resultSet.getInt("wishPrice"));
               wish.setWishCount(resultSet.getInt("wishCount"));
               wish.setListID(resultSet.getInt("listId"));
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wish;
    }

    public void editWish(int id, WishDTO editedWish) {


        try (Connection con = getConnection()) {

            // ID's
            int listID = 0;

            // find listID
            String findListID = "select listID from wish_lists where listName = ?;";
            PreparedStatement pstmt = con.prepareStatement(findListID);
            pstmt.setString(1, editedWish.getListName());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                listID = rs.getInt("listID");
            }

            //find wish and set it to editedWish
            String sql = "UPDATE wishes SET wishName = ?, wishLink = ?, wishimageURL = ?, wishDescription = ?, wishPrice = ?, wishCount = ?, listid = ? WHERE wishid = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,editedWish.getWishName());
            preparedStatement.setString(2,editedWish.getWishLink());
            preparedStatement.setString(3,editedWish.getWishImageURL());
            preparedStatement.setString(4, editedWish.getWishDescription());
            preparedStatement.setDouble(5, editedWish.getWishPrice());
            preparedStatement.setInt(6, editedWish.getWishCount());
            preparedStatement.setInt(7, listID);
            preparedStatement.setInt(8, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWish(int id) {

        try (Connection con = getConnection()){
            String sql = "DELETE FROM wishes WHERE wishid = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteWishlist(int id) {

        try (Connection conn = getConnection()){
            try (PreparedStatement pstmt1 = conn.prepareStatement("DELETE FROM wishes WHERE listid = ?")) {
                pstmt1.setInt(1,id);
                pstmt1.execute();
            }
            try (PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM wish_lists WHERE listid = ?")){
                pstmt2.setInt(1,id);
                pstmt2.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

/*    public boolean checkLogin(String username, String password) {

        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM users WHERE userName = ? AND userPassword = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            String uid = null;
            String pwd = null;

            if (rs.next()) {
                uid = rs.getString("userName");
                pwd = rs.getString("userPassword");
            }

            if (uid.equals(username) && (pwd.equals(password))) {
                return true;
            } else return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    public User getUser(String userName) {
       // User user = new User();

        try (Connection con = getConnection()) {
            String SQL = "SELECT userid, userName, userPassword from users where userName = ?";
            PreparedStatement preparedStatement = con.prepareStatement(SQL);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                int userID = resultSet.getInt("userid");
                String userName1 = resultSet.getString("userName");
                String userPassword1 = resultSet.getString("userPassword");
                if (userName1.equals(userName)) {
                    return new User(userID,userName1,userPassword1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public User getUserById(int id) {

        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM users WHERE userid = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new User(resultSet.getInt("userid"),
                        resultSet.getString("userName"),
                        resultSet.getString("userPassword"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("birthDate"),
                        resultSet.getString("gender"),
                        resultSet.getString("email"),
                        resultSet.getInt("phoneNumber"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void deleteAccount(int id) {

        try (Connection con = getConnection()){

            String sqlWishes = "DELETE FROM wishes WHERE listid IN (SELECT listid FROM wish_lists WHERE userid = ?)";
            PreparedStatement psWishes = con.prepareStatement(sqlWishes);
            psWishes.setInt(1, id);
            psWishes.executeUpdate();

            String sqlWishlist = "DELETE FROM wish_lists WHERE userid = ?";
            PreparedStatement preparedStatementList = con.prepareStatement(sqlWishlist);
            preparedStatementList.setInt(1, id);
            preparedStatementList.execute();

            String sqlUser = "DELETE FROM users WHERE userid = ?";
            PreparedStatement preparedStatementUser = con.prepareStatement(sqlUser);
            preparedStatementUser.setInt(1,id);
            preparedStatementUser.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editAccount(int id, User editedUser) {

        try (Connection con = getConnection()) {

            //find wish and set it to editedWish
            String sql = "UPDATE users SET userName = ?, userPassword = ?, firstName = ?, lastName = ?, birthDate = ?, gender = ?, email = ?, phoneNumber = ? WHERE userid = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, editedUser.getUserName());
            preparedStatement.setString(2, editedUser.getUserPassword());
            preparedStatement.setString(3, editedUser.getFirstName());
            preparedStatement.setString(4, editedUser.getLastName());
            preparedStatement.setString(5, editedUser.getBirthdate());
            preparedStatement.setString(6, editedUser.getGender());
            preparedStatement.setString(7, editedUser.getEmail());
            preparedStatement.setInt(8, editedUser.getPhoneNumber());
            preparedStatement.setInt(9, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
