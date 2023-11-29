
package com.draekk.todomaster.controllers;

import com.draekk.todomaster.models.User;
import com.draekk.todomaster.persistence.PersistenceController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeneralController {
    
    PersistenceController pc = new PersistenceController();

    public User processUser(String name, String lastName, String email, String username, String password) {

        

    }

    private String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            
            StringBuilder stringBuilder = new StringBuilder();
            for(byte b : hashedBytes){
                stringBuilder.append(String.format("%02x", b));
            }
            
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
