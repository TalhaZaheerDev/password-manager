package com.talha.passwordmanager.app;

import com.talha.passwordmanager.model.User;
import com.talha.passwordmanager.service.AuthService;
import com.talha.passwordmanager.service.CredentialService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        AuthService authService = new AuthService();
        CredentialService service = new CredentialService();

        System.out.println("1. Signup\n2. Login");
        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user;

        if (choice == 1) {
            try {
                authService.signup(username, password);
                System.out.println("Signup successful. Please login.");
                return;
            } catch (Exception e) {
                System.out.println("Signup failed: " + e.getMessage());
                return;
            }
        } else {
            user = authService.login(username, password);

            if (user == null) {
                System.out.println("Invalid credentials");
                return;
            }
        }

        int userId = user.getId();

        while (true) {
            System.out.println("\n1. Add\n2. View\n3. Search\n4. Delete\n5. Exit");
            int c = sc.nextInt();
            sc.nextLine();

            try {
                switch (c) {
                    case 1:
                        System.out.print("Website: ");
                        String site = sc.nextLine();

                        System.out.print("Username: ");
                        String u = sc.nextLine();

                        System.out.print("Password: ");
                        String p = sc.nextLine();

                        service.add(userId, site, u, p);
                        System.out.println("Saved.");
                        break;

                    case 2:
                        service.getAll(userId).forEach(System.out::println);
                        break;

                    case 3:
                        System.out.print("Website: ");
                        String w = sc.nextLine();

                        service.search(userId, w).forEach(System.out::println);
                        break;

                    case 4:
                        System.out.print("Enter credential ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        service.delete(id);
                        System.out.println("Deleted.");
                        break;

                    case 5:
                        System.out.println("Bye.");
                        return;

                    default:
                        System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}