package org.etfbl.support.webshopsupport.bean;

import java.io.Serializable;

public class UserBean implements Serializable {

    private static final long serialVersionUID = 1213893714706607554L;

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean loggedIn = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBean userBean = (UserBean) o;

        if (loggedIn != userBean.loggedIn) return false;
        if (!id.equals(userBean.id)) return false;
        if (!username.equals(userBean.username)) return false;
        if (!firstName.equals(userBean.firstName)) return false;
        return lastName.equals(userBean.lastName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (loggedIn ? 1 : 0);
        return result;
    }
}
