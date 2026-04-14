package com.talha.passwordmanager.service;

import com.talha.passwordmanager.dao.CredentialDAO;
import java.util.List;

public class CredentialService {

    private CredentialDAO dao = new CredentialDAO();

    public void add(int userId, String website, String username, String password) throws Exception {
        dao.addCredential(userId, website, username, password);
    }

    public List<String> getAll(int userId) throws Exception {
        return dao.getCredentials(userId);
    }

    public List<String> search(int userId, String website) throws Exception {
        return dao.searchByWebsite(userId, website);
    }

    public void delete(int id) throws Exception {
        dao.deleteCredential(id);
    }
}