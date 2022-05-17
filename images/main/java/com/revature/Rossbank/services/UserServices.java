package com.revature.Rossbank.services;

import com.revature.Rossbank.daos.UserDao;
import com.revature.Rossbank.exceptions.AuthenticationException;
import com.revature.Rossbank.exceptions.InvalidRequestException;
import com.revature.Rossbank.exceptions.ResourcePersistanceException;
import com.revature.Rossbank.models.user;
import com.revature.Rossbank.util.logging.Logger;

import java.io.IOException;


public class UserServices {

    private UserDao userDao = new UserDao();

    public UserServices(UserDao userDao) {
    }

    public user[] readUsers(){
        user[] users = new user[0];
        try {
            users = userDao.findAll();
            System.out.println("All users have been found here are the results: \n");
            for (int i = 0; i < users.length; i++) {
                user user = users[i];
                System.out.println(user.toString());
            }
        } catch (IOException | NullPointerException e) {
            // e.printStackTrace();
        }
        return users;
    }

    // TODO: Implement me to check that the email is not already in our database.
    public boolean validateEmail(String email){
        return userDao.checkEmail(email);
    }
    public boolean validatePassword(String email, String password){
        return userDao.checkPassword(email, password);
    }
    public boolean validateEmailNotUsed(String email){
        userDao.checkEmail(email);
        return false;
    }
    public void getAccounts(String email) {

    }

    public user registerUser(user newUser){
        if(!validateUserInput(newUser)){ // checking if false
            System.out.println("User was not validated");
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateEmailNotUsed(newUser.getEmail());

        user persistedUser = userDao.create(newUser);

        if(persistedUser == null){
            throw new RuntimeException();
        }

        System.out.println("User has been registered: " + newUser+ " Login with user now");
        return persistedUser;
    }

    private boolean validateUserInput(user newUser) {
        if(newUser == null) return false;
        if(newUser.getFirstName() == null || newUser.getFirstName().trim().equals("")) return false;
        if(newUser.getLastName() == null || newUser.getLastName().trim().equals("")) return false;
        if(newUser.getEmail() == null || newUser.getEmail().trim().equals("")) return false;
        return newUser.getPassword() != null || !newUser.getPassword().trim().equals("");
    }
    public user authenticateUser(String email, String password){

        if(password == null || password.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
        }

        user authenticatedUser = userDao.authenticateUser(email, password);

        if (authenticatedUser == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return authenticatedUser;

    }

    public user readUserById(String email) {
        Logger logger = null;
        user user = new user();
        try {
            user = userDao.findById(email);
        }catch (ResourcePersistanceException e){
            logger.warn("Id was not found");
        }
        return user;
    }

    public boolean validateInput(user user) {
        return false;
    }

    public user create(user user) {
        return user;
    }
}
