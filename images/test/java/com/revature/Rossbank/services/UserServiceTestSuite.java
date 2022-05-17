package com.revature.Rossbank.services;

import com.revature.Rossbank.daos.UserDao;
import com.revature.Rossbank.models.user;
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
        user user = new user("valid", "valid", "valid","valid");

        // Act
        boolean actualResult = sut.validateInput(user);

        // Assert
        Assertions.assertTrue(actualResult);

    }

    @Test
    public void test_create_givenValidUser_returnsUser(){
        // Arrange
        user user = new user("pie", "pie", "pie","pie");
        // THe below code ensures that the services can continue execution and get expected results from the dao without any issues
        when(mockUserDao.create(user)).thenReturn(user);

        // Act
        user actualUser = sut.create(user);

        // Assert
        Assertions.assertEquals("pie", actualUser.getEmail());
        Assertions.assertEquals("pie", actualUser.getFirstName());
        Assertions.assertEquals("pie", actualUser.getLastName());
        Assertions.assertEquals("pie", actualUser.getPassword());

        // Mockito is verifying that the creation method was executed only once!
        verify(mockUserDao, times(1)).create(user);
    }

    @Test
    @Disabled
    public void test3(){

    }

}
