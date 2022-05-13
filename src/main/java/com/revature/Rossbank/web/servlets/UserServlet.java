package com.revature.Rossbank.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Rossbank.exceptions.ResourcePersistanceException;
import com.revature.Rossbank.models.BankUser;
import com.revature.Rossbank.services.UserServices;
import com.revature.Rossbank.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.revature.Rossbank.web.servlets.Authable.checkAuth;

//@WebServlet("/Users")
public class UserServlet extends HttpServlet implements Authable {

    private final UserServices userServices;
    private final ObjectMapper mapper;
    private final Logger logger = Logger.getLogger();

    public UserServlet(UserServices userServices, ObjectMapper mapper) {
        this.userServices = userServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp)) return;
//        String pathInfo = req.getPathInfo();
//        String[] pathParts = pathInfo.split("/");
//        System.out.println(pathParts[0] + pathParts[1] + pathParts[2]);


        if(req.getParameter("id") != null && req.getParameter("email") != null){
            resp.getWriter().write("Hey you have the following id and email " + req.getParameter("id") + " " + req.getParameter("email") );
            return;
        }

        if(req.getParameter("id") != null){
            BankUser bankUser;
            try {
                bankUser = userServices.readById(req.getParameter("id")); // EVERY PARAMETER RETURN FROM A URL IS A STRING
            } catch (ResourcePersistanceException e){
                logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(bankUser);
            resp.getWriter().write(payload);
            return;
        }

        List<BankUser> bankUsers = userServices.readAll();
        String payload = mapper.writeValueAsString(bankUsers);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
