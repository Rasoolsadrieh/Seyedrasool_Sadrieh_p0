package com.revature.Rossbank.services;

import com.revature.Rossbank.daos.AccountsDao;
import com.revature.Rossbank.exceptions.ResourcePersistanceException;
import com.revature.Rossbank.models.account;
import com.revature.Rossbank.util.logging.Logger;

import java.io.IOException;

public class AccountServices {
    private AccountsDao accountsDao = new AccountsDao();

    public AccountServices(AccountsDao accountsDao) {
    }

    public account[] readAccounts(String email) {
        account[] accounts = new account[0];
        try {
            accounts = accountsDao.findAll(email);
            for (int i = 0; i < accounts.length; i++) {
                account account = accounts[i];
                System.out.println(account.toString());
            }
        } catch (IOException | NullPointerException e) {
            // e.printStackTrace();
        }
        return accounts;
    }
    public void deposit(String value, String id){
        accountsDao.deposit(value, id);
    }

    public boolean registerAccount(account newAccount){
        if(!validateAccountInput(newAccount)){ // checking if false
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateAccountInput(newAccount);

        account persistedAccount = accountsDao.create(newAccount);

        if(persistedAccount == null){
            throw new RuntimeException();
        }
        System.out.println("Account has been registered: " + newAccount);
        return true;
    }
    private boolean validateAccountInput(account newAccount) {
        if(newAccount == null) return false;
        if(newAccount.getAccountID() == 0) return false;
        if(newAccount.getAccountName() == null || newAccount.getAccountName().trim().equals("")) return false;
        return newAccount.getEmail() != null || !newAccount.getEmail().trim().equals("");
    }
    public account readAccountById(String id) {
        Logger logger = null;
        account account = new account();
        try {
            account = accountsDao.findById(id);
        }catch (ResourcePersistanceException e){
            logger.warn("Id was not found");
        }
        return account;
    }
}
