package com.revature.Rossbank.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Rossbank.exceptions.AuthenticationException;
import com.revature.Rossbank.exceptions.InvalidRequestException;
import com.revature.Rossbank.models.user;
import com.revature.Rossbank.services.UserServices;
import com.revature.Rossbank.web.dto.LoginCreds;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
            LoginCreds loginCreds = mapper.readValue(req.getInputStream(), LoginCreds.class);

            user authUser = userServices.authenticateUser(loginCreds.getEmail(), loginCreds.getPassword());

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authUser", authUser);

            resp.getWriter().write("You have successfully logged in!");
        } catch (AuthenticationException | InvalidRequestException e){
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e){
            resp.setStatus(409);
            resp.getWriter().write(e.getMessage());
        }
    }

}

