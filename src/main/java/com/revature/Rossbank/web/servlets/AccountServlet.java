package com.revature.Rossbank.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Rossbank.exceptions.ResourcePersistanceException;
import com.revature.Rossbank.models.account;
import com.revature.Rossbank.services.AccountServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AccountServlet extends HttpServlet {
    private final AccountServices accountServices;
    private final ObjectMapper mapper;

    public AccountServlet(AccountServices accountServices,ObjectMapper mapper) {
        this.accountServices = accountServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkAuth(req, resp)) return;

        if (req.getParameter("id") != null) {
            account account;
            try {
                account = accountServices.readAccountById(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING

            } catch (ResourcePersistanceException e) {
                resp.setStatus(404);
                return;
            }
            String payload = mapper.writeValueAsString(account);
            resp.getWriter().write(payload);
            resp.setStatus(201);
            return;
        }

        List<account> accounts = Arrays.asList(accountServices.readAccounts(req.getParameter("email")));
        String payload = mapper.writeValueAsString(accounts);

        resp.getWriter().write(payload);
    }
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkAuth(req, resp)) return;
        //TODO: Add account auth
        String payload = "";
        if (req.getParameter("id") != null || req.getParameter("value") != null) {
            account account;
            try {
                accountServices.deposit(req.getParameter("value"), req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
                account = accountServices.readAccountById(req.getParameter("id"));

            } catch (ResourcePersistanceException e) {
                resp.setStatus(404);
                return;
            }
            payload = mapper.writeValueAsString(account);
            resp.getWriter().write(payload);
            resp.setStatus(201);
            return;
        }
        resp.getWriter().write("Invalid value or id");


    }
    protected boolean checkAuth (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("authUser") == null){
            resp.getWriter().write("Unauthorized request - not logged in ");
            resp.setStatus(401);
            return true;
        }
        return false;

    }

}
