<%@ page import="org.etfbl.support.webshopsupport.dto.User" %>
<%@ page import="org.etfbl.support.webshopsupport.dto.UserSupport" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:useBean id="userSupportBean" class="org.etfbl.support.webshopsupport.bean.UserSupportBean" scope="session"/>
<jsp:useBean id="userService" class="org.etfbl.support.webshopsupport.service.UserService" scope="application"/>
<jsp:useBean id="loginBean" class="org.etfbl.support.webshopsupport.bean.LoginBean" scope="request"/>
<jsp:setProperty property="username" name="loginBean" param="username"/>
<jsp:setProperty property="password" name="loginBean" param="password"/>

<!DOCTYPE html>
<%
    if (request.getParameter("submit") != null) {
        UserSupport userSupport = userService.loginUser(loginBean.getUsername(), loginBean.getPassword());
        if (userSupport != null) {
            userSupportBean.setId(userSupport.getId());
            userSupportBean.setUsername(userSupport.getUsername());
            userSupportBean.setFirstName(userSupport.getFirstName());
            userSupportBean.setLastName(userSupport.getLastName());
            userSupportBean.setLoggedIn(true);

            session.setAttribute("notification", "");
            response.sendRedirect("messages.jsp");
        } else {
            session.setAttribute("notification", "Invalid username or password");
            userSupportBean.setLoggedIn(false);
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
<div class="mdl-layout mdl-layout--fixed-header">
    <%@include file="WEB-INF/header.jsp" %>
    <main class="mdl-layout__content">
        <div class="page-content">
            <h3>Login</h3>
            <form action="login.jsp" method="post" class="center">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <label class="mdl-textfield__label" for="username">Username</label>
                    <input class="mdl-textfield__input" type="text" id="username" name="username" required>
                </div>
                <br>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
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
        </div>
    </main>
</div>
</body>
</html>
