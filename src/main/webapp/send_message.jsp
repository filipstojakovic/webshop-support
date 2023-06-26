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
</head>
<body>
<h3>Send Message:</h3>
<label>
    Full name:
    <input type="text" value="<%= message.getUser().getFirstName() +" " + message.getUser().getLastName()%>"/>

    <form>

    </form>

</label>

</body>
</html>
