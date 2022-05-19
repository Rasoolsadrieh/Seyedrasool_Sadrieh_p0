package com.revature.Rossbank.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Rossbank.exceptions.ResourcePersistanceException;
import com.revature.Rossbank.models.user;
import com.revature.Rossbank.services.UserServices;
import com.revature.Rossbank.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UserServlet extends HttpServlet {
    private final UserServices userServices;
    private final ObjectMapper mapper;
    private final Logger logger = Logger.getLogger();

    public UserServlet(UserServices userServices, ObjectMapper mapper) {
        this.userServices = userServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!checkAuth(req, resp)) return;

        if (req.getParameter("email") != null) {
            user user;
            try {
                user = userServices.readUserById(req.getParameter("email")); // EVERY PARAMETER RETURN FROM A URL IS A STRING

            } catch (ResourcePersistanceException e) {
                resp.setStatus(404);
                return;
            }
            String payload = mapper.writeValueAsString(user);
            resp.getWriter().write(payload);
            return;
        }
        resp.getWriter().write("no returns");

        List<user> users = Arrays.asList(userServices.readUsers());
        String payload = mapper.writeValueAsString(users);

        resp.getWriter().write(payload);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // Making a new User
        resp.getWriter().write("");
        user newUser = mapper.readValue(req.getInputStream(), user.class); // from JSON to Java Object (user)
        resp.getWriter().write("");
        user persistedUser = userServices.registerUser(newUser);
        resp.getWriter().write("");

        String payload = mapper.writeValueAsString(persistedUser); // Mapping from Java Object (user) to JSON

        resp.getWriter().write("NEW USER IS REGISTERED IN OUR SYSTEM. THE INFORMATION IS: \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    protected boolean checkAuth (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("authUser") == null){
            resp.getWriter().write("UNAUTHORIZED REQUEST. YOU ARE NOT LOGGED IN. PLEASE LOG IN FIRST. ");
            resp.setStatus(401);
            return false;
        }
        return true;

    }


}
