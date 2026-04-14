package com.talha.passwordmanager.service;

import com.talha.passwordmanager.dao.CredentialDAO;
import java.util.List;

public class CredentialService {

    private CredentialDAO dao = new CredentialDAO();

    public void add(int userId, String website, String username, String password, String masterPassword) throws Exception {
        dao.addCredential(userId, website, username, password, masterPassword);
    }

    public List<String> getAll(int userId, String masterPassword) throws Exception {
        return dao.getCredentials(userId, masterPassword);
    }

    public List<String> search(int userId, String website, String masterPassword) throws Exception {
        return dao.searchByWebsite(userId, website, masterPassword);
    }

    public void delete(int id) throws Exception {
        dao.deleteCredential(id);
    }
}