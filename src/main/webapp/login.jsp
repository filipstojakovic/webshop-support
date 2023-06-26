<%@ page import="org.etfbl.support.webshopsupport.dto.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:useBean id="userBean" class="org.etfbl.support.webshopsupport.bean.UserBean" scope="session"/>
<jsp:useBean id="userService" class="org.etfbl.support.webshopsupport.service.UserService" scope="application"/>
<jsp:useBean id="loginBean" class="org.etfbl.support.webshopsupport.bean.LoginBean" scope="request"/>
<jsp:setProperty property="username" name="loginBean" param="username"/>
<jsp:setProperty property="password" name="loginBean" param="password"/>

<!DOCTYPE html>
<%
    if (request.getParameter("submit") != null) {
        User user = userService.loginUser(loginBean.getUsername(), loginBean.getPassword());
        if (user != null) {
            userBean.setId(user.getId());
            userBean.setUsername(user.getUsername());
            userBean.setFirstName(user.getFirstName());
            userBean.setLastName(user.getLastName());
            userBean.setLoggedIn(true);

            session.setAttribute("notification", "");
            response.sendRedirect("messages.jsp");
        } else {
            session.setAttribute("notification", "Invalid username or password");
            userBean.setLoggedIn(false);
        }
    } else {
        session.setAttribute("notification", "");
    }
%>
<html>
<head>
    <title>Webshop support</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <link rel="stylesheet" href="styles/style.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</head>
<body>
<%@include file="WEB-INF/header.jsp" %>
<h3>Login</h3>
<form action="login.jsp" method="post" class="center">
    <div class="mdl-textfield mdl-js-textfield">
        <label class="mdl-textfield__label" for="username">Username</label>
        <input class="mdl-textfield__input" type="text" id="username" name="username" required>
    </div>
    <br>
    <div class="mdl-textfield mdl-js-textfield">
        <label class="mdl-textfield__label" for="password">Password</label>
        <input class="mdl-textfield__input" type="password" id="password" name="password" required>
    </div>
    <br>
    <p style="color: red"><%=session.getAttribute("notification").toString()%>
    </p>
    <button type="submit" name="submit"
            class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
        Login
    </button>
</form>
</body>
</html>
