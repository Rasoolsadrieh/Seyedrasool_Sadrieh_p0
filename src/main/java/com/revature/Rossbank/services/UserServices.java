package com.revature.Rossbank.services;

import com.revature.Rossbank.daos.UserDao;
import com.revature.Rossbank.exceptions.AuthenticationException;
import com.revature.Rossbank.exceptions.InvalidRequestException;
import com.revature.Rossbank.exceptions.ResourcePersistanceException;
import com.revature.Rossbank.models.BankUser;
import com.revature.Rossbank.util.logging.Logger;

import java.io.IOException;
import java.util.List;

public class UserServices implements Serviceable<BankUser>{

    private UserDao UserDao;
    private Logger logger = Logger.getLogger();

    public UserServices(UserDao UserDao) {
        this.UserDao = UserDao;
    }

    @Override
    public List<BankUser> readAll(){
        logger.info("Begin reading Users in our file database.");


        try {
            // TODO: What UserDao intellisense telling me?
            List<BankUser> bankUsers = UserDao.findAll();
            logger.info("All Users have been found here are the results:");
            return bankUsers;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BankUser readById(String id) throws ResourcePersistanceException{

        BankUser bankUser = UserDao.findById(id);
        return bankUser;
    }

    @Override
    public BankUser update(BankUser updatedObject) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public boolean validateEmailNotUsed(String email){
        return UserDao.checkEmail(email);
    }
    
    public BankUser create(BankUser newBankUser){
        logger.info("User trying to be registered: " + newBankUser);
        if(!validateInput(newBankUser)){ // checking if false
            // TODO: throw - what's this keyword?
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }

        // TODO: Will implement with JDBC (connecting to our database)
        if(validateEmailNotUsed(newBankUser.getEmail())){
            throw new InvalidRequestException("User email is already in use. Please try again with another email or login into previous made account.");
        }

        BankUser persistedBankUser = UserDao.create(newBankUser);

        if(persistedBankUser == null){
            throw new ResourcePersistanceException("User was not persisted to the database upon registration");
        }
        logger.info("User has been persisted: " + newBankUser);
        return persistedBankUser;
    }

    @Override
    public boolean validateInput(BankUser newBankUser) {
        logger.debug("Validating User: " + newBankUser);
        if(newBankUser == null) return false;

        if(newBankUser.getFname() == null || newBankUser.getFname().trim().equals("")) return false;
        if(newBankUser.getLname() == null || newBankUser.getLname().trim().equals("")) return false;
        if(newBankUser.getEmail() == null || newBankUser.getEmail().trim().equals("")) return false;
        if(newBankUser.getPassword() == null || newBankUser.getPassword().trim().equals("")) return false;
        return newBankUser.getDob() != null || !newBankUser.getDob().trim().equals("");
    }

    public BankUser authenticateUser(String email, String password){

        if(password == null || password.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
        }

        BankUser authenticatedBankUser = UserDao.authenticateBankUser(email, password);

        if (authenticatedBankUser == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return authenticatedBankUser;

    }
}
