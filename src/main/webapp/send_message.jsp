<%@ page import="org.etfbl.support.webshopsupport.dto.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="messageService" class="org.etfbl.support.webshopsupport.service.MessageService" scope="session"/>
<jsp:useBean id="userSupportBean" type="org.etfbl.support.webshopsupport.bean.UserSupportBean" scope="session"/>
<jsp:useBean id="messageBean" class="org.etfbl.support.webshopsupport.bean.MessageBean" scope="request"/>
<jsp:useBean id="mailService" class="org.etfbl.support.webshopsupport.service.MailService" scope="session"/>
<jsp:setProperty property="*" name="messageBean"/>

<%
    if (!(userSupportBean.isLoggedIn())) response.sendRedirect("login.jsp");

    String action = request.getParameter("action");
    if ("send".equals(action)) {
        mailService.sendMail(messageBean.getEmail(), messageBean.getTitle(), messageBean.getContent());
        response.sendRedirect("messages.jsp");
    }
    Long messageId = Long.parseLong(request.getParameter("id"));
    Message message = messageService.openMessage(messageId);
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

<div class="mdl-layout mdl-layout--fixed-header">
    <%@include file="WEB-INF/header.jsp" %>
    <main class="mdl-layout__content">
        <div class="page-content">

            <h4>Send Message:</h4>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <label class="mdl-textfield__label" for="name">Full name</label>
                <input class="mdl-textfield__input" type="text" id="name" value="<%=message.getUser().getFullName()%>"
                       readonly>
            </div>

            <form method="post" action="send_message.jsp?action=send&id=<%=messageId%>" class="form-container">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <label class="mdl-textfield__label" for="email">Email</label>
                    <input class="mdl-textfield__input" type="text" id="email" name="email"
                           value="<%=message.getUser().getEmail()%>"
                           readonly>
                </div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <label class="mdl-textfield__label" for="title">Title</label>
                    <input class="mdl-textfield__input" type="text" id="title" name="title" required>
                </div>
                <div class="mdl-textfield mdl-js-textfield">
                    <label class="mdl-textfield__label" for="content">Content</label>
                    <textarea class="mdl-textfield__input" type="text" id="content" name="content" required></textarea>
                </div>

                <button type="submit" name="sendMessage"
                        class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
                    Send Message
                </button>
            </form>

        </div>
    </main>
</div>

</body>
</html>
