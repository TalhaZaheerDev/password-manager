package com.talha.passwordmanager.service;

import org.mindrot.jbcrypt.BCrypt;

public class AuthService {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verifyPassword(String input, String hash) {
        return BCrypt.checkpw(input, hash);
    }
}