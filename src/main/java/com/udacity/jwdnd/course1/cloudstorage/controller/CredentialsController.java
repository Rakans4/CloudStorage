package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping("/home")
public class CredentialsController {
    private CredentialsService credentialsService;
    private EncryptionService encryptionService;
    private UserService userService;

    public CredentialsController(CredentialsService credentialsService, EncryptionService encryptionService, UserService userService) {
        this.credentialsService = credentialsService;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    @PostMapping("add-credentials")
    public String addCredentials(Authentication authentication, Credentials credentials, Model model) {
        if(credentials.getCredentialid() != null){
            String encodedPassword = this.encryptionService.encryptValue(credentials.getPassword(), credentials.getKey());
            credentials.setPassword(encodedPassword);
            this.credentialsService.updateCredentials(credentials);
            model.addAttribute("success", true);
        } else {
            //validations should be here
            SecureRandom random = new SecureRandom();
            byte[] key = new byte[16];
            random.nextBytes(key);
            String encodedKey = Base64.getEncoder().encodeToString(key);
            String encodedPassword = this.encryptionService.encryptValue(credentials.getPassword(), encodedKey);
            credentials.setKey(encodedKey);
            credentials.setPassword(encodedPassword);
            credentials.setUserid(this.userService.getUser(authentication.getPrincipal().toString()).getUserid());
            this.credentialsService.addCredentials(credentials);
            model.addAttribute("success", true);
        }
        return "result";
    }

    @GetMapping("/delete-credentials/{credentialid}")
    public String deleteNote(@PathVariable Integer credentialid, Model model) {
        this.credentialsService.deleteCredentials(credentialid);
        model.addAttribute("success", true);
        return "result";
    }

}
