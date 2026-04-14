package com.talha.passwordmanager.app;

import com.talha.passwordmanager.config.DBConnection;
import com.talha.passwordmanager.dao.CredentialDAO;
import com.talha.passwordmanager.model.User;
import com.talha.passwordmanager.service.AuthService;
import com.talha.passwordmanager.util.CryptoUtil;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        AuthService authService = new AuthService();
        CredentialDAO dao = new CredentialDAO();

        System.out.println("1. Signup\n2. Login");
        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user;

        if (choice == 1) {
            authService.signup(username, password);
            System.out.println("Signup successful");
            return;
        } else {
            user = authService.login(username, password);

            if (user == null) {
                System.out.println("Invalid credentials");
                return;
            }
        }

        int userId = user.getId();

        while (true) {
            System.out.println("1. Add\n2. View\n3. Exit");
            int c = sc.nextInt();
            sc.nextLine();

            if (c == 1) {
                System.out.print("Website: ");
                String site = sc.nextLine();

                System.out.print("Username: ");
                String u = sc.nextLine();

                System.out.print("Password: ");
                String p = sc.nextLine();

                dao.addCredential(userId, site, u, p);
            } else if (c == 2) {
                dao.getCredentials(userId).forEach(System.out::println);
            } else break;
        }
    }
}