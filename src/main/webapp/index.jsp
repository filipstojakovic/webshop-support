<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<jsp:useBean id="userSupportBean" class="org.etfbl.support.webshopsupport.bean.UserSupportBean" scope="session"/>
<%
    if (userSupportBean.isLoggedIn()) {
        response.sendRedirect("messages.jsp");
    } else {
        response.sendRedirect("login.jsp");
    }
%>
<html>
<head>
    <title>Webshop support</title>
</head>
<body>

</body>
</html>
