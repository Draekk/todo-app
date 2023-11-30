
package com.draekk.todomaster.controllers;

import com.draekk.todomaster.models.User;
import com.draekk.todomaster.persistence.PersistenceController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class GeneralController {
    
    PersistenceController pc = new PersistenceController();

    public User processUser(String name, String lastName, String email, String username, String password) {

        String encryptedPassword = encryptPassword(password);
        
        if(encryptedPassword != null && isExistingEmail(email) == null) {
            User user = new User(name, lastName, email, username, encryptedPassword);
            pc.createUser(user);
            return user;
        }
        return null;

    }
    
    public List<User> getUserList(){
        return pc.getUserList();
    }
    
    public User getLoginUser(String email, String password) {

        String encryptedLoginPassword = encryptPassword(password);
        
        User user = isExistingEmail(email);
            
        if(user != null && user.getPassword().equals(encryptedLoginPassword)){
            return user;
        }
        return null;
    }
    
    private User isExistingEmail(String email) {
        List<User> users = getUserList();
        
        for(User u : users) {
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }
    
    private User isExistingEmail(String email, List<User> users) {
        for(User u : users) {
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
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
