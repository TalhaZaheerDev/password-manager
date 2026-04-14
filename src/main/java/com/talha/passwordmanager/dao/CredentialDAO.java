package com.talha.passwordmanager.dao;

import com.talha.passwordmanager.config.DBConnection;
import com.talha.passwordmanager.util.CryptoUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CredentialDAO {

    public void addCredential(int userId, String website, String username, String password) throws Exception {
        String encrypted = CryptoUtil.encrypt(password);

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO credentials(user_id, website, username, password) VALUES (?, ?, ?, ?)"
        );

        ps.setInt(1, userId);
        ps.setString(2, website);
        ps.setString(3, username);
        ps.setString(4, encrypted);

        ps.executeUpdate();
        conn.close();
    }

    public List<String> getCredentials(int userId) throws Exception {
        List<String> list = new ArrayList<>();

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(
                "SELECT website, username, password FROM credentials WHERE user_id=?"
        );

        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String decrypted = CryptoUtil.decrypt(rs.getString("password"));
            list.add(rs.getString("website") + " | " + rs.getString("username") + " | " + decrypted);
        }

        conn.close();
        return list;
    }

    public List<String> searchByWebsite(int userId, String website) throws Exception {
        List<String> list = new ArrayList<>();

        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(
                "SELECT id, website, username, password FROM credentials WHERE user_id=? AND website ILIKE ?"
        );

        ps.setInt(1, userId);
        ps.setString(2, "%" + website + "%");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String decrypted = CryptoUtil.decrypt(rs.getString("password"));
            list.add(rs.getInt("id") + " | " + rs.getString("website") + " | " + rs.getString("username") + " | " + decrypted);
        }

        conn.close();
        return list;
    }

    public void deleteCredential(int id) throws Exception {
        Connection conn = DBConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM credentials WHERE id=?"
        );

        ps.setInt(1, id);
        ps.executeUpdate();

        conn.close();
    }
}