package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {
    private CredentialsMapper credentialsMapper;

    public CredentialsService(CredentialsMapper credentialsMapper) {
        this.credentialsMapper = credentialsMapper;
    }

    public List<Credentials> getAllCredentials(Integer userid) {
        return this.credentialsMapper.getAllCredentials(userid);
    }

    public void addCredentials(Credentials credentials) {
        this.credentialsMapper.insert(credentials);
    }

    public void updateCredentials(Credentials credentials) {
        this.credentialsMapper.update(credentials);
    }

    public boolean credentialsExist(Integer credentialid) {
        Credentials credentials = this.credentialsMapper.getCredentials(credentialid);
        return credentials.getCredentialid() != null;
    }

    public void deleteCredentials(Integer credentialid) {
        this.credentialsMapper.delete(credentialid);
    }
}
