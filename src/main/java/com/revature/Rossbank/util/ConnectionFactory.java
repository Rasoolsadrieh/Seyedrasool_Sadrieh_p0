package com.revature.Rossbank.util;

// Design Patterns

/*
    Singleton Design Pattern
    - Creational Pattern
    - Restricts that only a signle instance of this class can be made within the application
    - Constructor cannot be invoked outside of this class
    - Eager or Lazy singleton

    Factory Design Pattern
    - Creational PAttern
    - used to abstract away the creation/instantiation of the class

 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final ConnectionFactory connectionFactory = new ConnectionFactory(); // instead Eager Singleton
    private Properties prop = new Properties();

    // specifically a singleton bc of the private constructor
    private ConnectionFactory(){
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            prop.load(loader.getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        // Reflections are just viewing a class
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Need some way for out ConnectionFactory to be obtained by other classes
    public static ConnectionFactory getInstance(){
        return connectionFactory;
    }

    // Once we getInstance() we are able to execute getConnection to return a Connection to our database
    public Connection getConnection() {

        Connection conn = null;

        // String url = "jdbc:postgresql://localhost:5432/postgres"; // default url will connect you to public
        // TODO: WE NEED TO FIX THIS
        // make sure currentSchema name lowercase
//        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=rossbank"; // default url will connect you to public
//        String user = "postgres";
//        String password = "password";

        try {
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("You Are Connected To the Database. 100%");
        }

        return conn;
    }
}
