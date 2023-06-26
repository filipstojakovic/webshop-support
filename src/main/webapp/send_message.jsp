<%@ page import="org.etfbl.support.webshopsupport.dto.Message" %>
<%@ page import="org.etfbl.support.webshopsupport.dao.MessageDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userBean" type="org.etfbl.support.webshopsupport.bean.UserBean" scope="session"/>
<%
    if (!(userBean.isLoggedIn())) response.sendRedirect("login.jsp");
    Long messageId = Long.parseLong(request.getParameter("id"));
    Message message = MessageDao.findById(messageId);
    if (message == null) response.sendRedirect("messages.jsp");
%>
<html>
<head>
    <title>Send Message</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <link href="styles/style.css" type="text/css" rel="stylesheet">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</head>
<body>
<%@include file="WEB-INF/header.jsp" %>

<h3>Send Message:</h3>
<label>
    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <label class="mdl-textfield__label" for="name">Full name</label>
        <input class="mdl-textfield__input" type="text" id="name" value="<%=message.getUser().getFullName()%>" readonly>
    </div>
    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <label class="mdl-textfield__label" for="name">Email</label>
        <input class="mdl-textfield__input" type="text" id="name" value="<%=message.getUser().g%>" readonly>
    </div>

</label>

</body>
</html>
