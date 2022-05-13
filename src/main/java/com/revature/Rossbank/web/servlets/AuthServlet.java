package com.revature.Rossbank.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Rossbank.exceptions.AuthenticationException;
import com.revature.Rossbank.exceptions.InvalidRequestException;
import com.revature.Rossbank.models.BankUser;
import com.revature.Rossbank.services.UserServices;
import com.revature.Rossbank.web.dto.LoginCreds;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet("/auth") // this requires a default No-Args constructor
public class AuthServlet extends HttpServlet {

    private final UserServices userServices;
    // ObjectMapper provided by jackson
    private final ObjectMapper mapper;

    public AuthServlet(UserServices userServices, ObjectMapper mapper){
        this.userServices = userServices;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            // The jackson library has the ObjectMapper with methods to readValues from the HTTPRequest body as an input stream and assign it to the class

            LoginCreds loginCreds = mapper.readValue(req.getInputStream(), LoginCreds.class);

            BankUser authBankUser = userServices.authenticateUser(loginCreds.getEmail(), loginCreds.getPassword());

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authUser", authBankUser);

            resp.getWriter().write("We found you in our database and logged you in. Welcome! ");
        } catch (AuthenticationException | InvalidRequestException e){
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e){
            resp.setStatus(500);
            resp.getWriter().write(e.getMessage());
        }
    }

}
