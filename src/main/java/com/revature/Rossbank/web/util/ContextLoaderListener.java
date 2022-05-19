package com.revature.Rossbank.web.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Rossbank.daos.AccountsDao;
import com.revature.Rossbank.daos.UserDao;
import com.revature.Rossbank.services.AccountServices;
import com.revature.Rossbank.services.UserServices;
import com.revature.Rossbank.web.servlets.AccountServlet;
import com.revature.Rossbank.web.servlets.AuthServlet;
import com.revature.Rossbank.web.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ObjectMapper mapper = new ObjectMapper();


        UserDao userDao = new UserDao();
        UserServices userServices = new UserServices(userDao);
        AccountsDao accountsDao = new AccountsDao();
        AccountServices accountServices = new AccountServices(accountsDao);

        AuthServlet authServlet = new AuthServlet(userServices, mapper);
        UserServlet userServlet = new UserServlet(userServices, mapper);
        AccountServlet accountServlet = new AccountServlet(accountServices, mapper);


        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("AccountServlet", accountServlet).addMapping("/accounts/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
