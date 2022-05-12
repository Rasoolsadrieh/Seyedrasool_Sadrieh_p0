package com.revature.Rossbank.web.dto;

public class LoginCreds {

        private String email;
        private String password;

        // JACKSON REQUIRES A NO ARG CONSTRUCTOR

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
