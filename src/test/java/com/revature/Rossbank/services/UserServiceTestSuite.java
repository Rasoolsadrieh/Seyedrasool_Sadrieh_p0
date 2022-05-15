package com.revature.Rossbank.services;

import com.revature.Rossbank.daos.UserDao;
import com.revature.Rossbank.models.BankUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class UserServiceTestSuite {

    UserServices sut;
    UserDao mockUserDao;

    @BeforeEach
    public void testPrep(){
        // in order to specify and mock a dao
        mockUserDao = mock(UserDao.class);
        sut = new UserServices(mockUserDao);
    }

    @Test
    public void test_validateInput_givenValidUser_returnTrue(){

        // Arrange
        BankUser bankUser = new BankUser(1,"valid", "valid", "valid","valid","valid");

        // Act
        boolean actualResult = sut.validateInput(bankUser);

        // Assert
        Assertions.assertTrue(actualResult);

    }

    @Test
    public void test_create_givenValidUser_returnsUser(){
        // Arrange
        BankUser bankUser = new BankUser(1,"pie", "pie", "pie","pie","pie");
        // THe below code ensures that the services can continue execution and get expected results from the dao without any issues
        when(mockUserDao.create(bankUser)).thenReturn(bankUser);

        // Act
        BankUser actualBankUser = sut.create(bankUser);

        // Assert
        Assertions.assertEquals(1 , actualBankUser.getId());
        Assertions.assertEquals("pie", actualBankUser.getFname());
        Assertions.assertEquals("pie", actualBankUser.getLname());
        Assertions.assertEquals("pie", actualBankUser.getPassword());
        Assertions.assertEquals("pie", actualBankUser.getEmail());
        Assertions.assertEquals("pie", actualBankUser.getDob());
        // Mockito is verifying that the creation method was executed only once!
        verify(mockUserDao, times(1)).create(bankUser);
    }

    @Test
    @Disabled
    public void test3(){

    }

}
