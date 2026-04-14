package com.talha.passwordmanager.app;

import com.talha.passwordmanager.dao.CredentialDAO;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        CredentialDAO dao = new CredentialDAO();

        int userId = 1; // assume logged in

        while (true) {
            System.out.println("1. Add\n2. View\n3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Website: ");
                String site = sc.nextLine();

                System.out.print("Username: ");
                String user = sc.nextLine();

                System.out.print("Password: ");
                String pass = sc.nextLine();

                dao.addCredential(userId, site, user, pass);
            }

            else if (choice == 2) {
                List<String> creds = dao.getCredentials(userId);
                creds.forEach(System.out::println);
            }

            else break;
        }
    }
}