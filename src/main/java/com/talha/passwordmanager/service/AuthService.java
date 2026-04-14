package com.talha.passwordmanager.service;

import com.talha.passwordmanager.dao.UserDAO;
import com.talha.passwordmanager.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {

    private UserDAO userDAO = new UserDAO();

    // existing methods (keep)
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verifyPassword(String input, String hash) {
        return BCrypt.checkpw(input, hash);
    }

    // new: signup
    public void signup(String username, String password) throws Exception {
        String hash = hashPassword(password);
        userDAO.saveUser(username, hash);
    }

    // new: login
    public User login(String username, String password) throws Exception {
        User user = userDAO.findByUsername(username);

        if (user != null && verifyPassword(password, user.getPasswordHash())) {
            return user;
        }

        return null;
    }
}