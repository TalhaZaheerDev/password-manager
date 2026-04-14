package com.talha.passwordmanager.dao;

import com.talha.passwordmanager.config.DBConnection;
import com.talha.passwordmanager.model.User;

import java.sql.*;

public class UserDAO {

    public void saveUser(String username, String hash) throws Exception {
        Connection conn = DBConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users(username, master_password_hash) VALUES (?, ?)"
        );

        ps.setString(1, username);
        ps.setString(2, hash);
        ps.executeUpdate();

        conn.close();
    }

    public User findByUsername(String username) throws Exception {
        Connection conn = DBConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM users WHERE username=?"
        );

        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPasswordHash(rs.getString("master_password_hash"));
            return user;
        }

        conn.close();
        return null;
    }
}
