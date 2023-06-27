package org.etfbl.support.webshopsupport.bean;

import java.io.Serializable;

public class LoginBean implements Serializable {

    private static final long serialVersionUID = -4021922494651503066L;

    private String username;
    private String password;

    public LoginBean() {
    }

    public LoginBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
