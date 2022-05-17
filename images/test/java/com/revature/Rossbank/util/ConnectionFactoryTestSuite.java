package com.revature.Rossbank.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionFactoryTestSuite {

    @Test
    public void test_getConnection_givenProvidedCredentials_returnValidConnection(){
        // Arrange & Acting
        try(Connection conn = ConnectionFactory.getInstance().getConnection();){
            System.out.println(conn);
            System.out.println("You are successfully connected to database 100%");

            // Assert
            Assertions.assertNotNull(conn);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
