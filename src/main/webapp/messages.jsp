<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.etfbl.support.webshopsupport.dto.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="org.etfbl.support.webshopsupport.dao.MessageDao" %>
<%@ page import="java.util.ArrayList" %>

<jsp:useBean id="userSupportBean" type="org.etfbl.support.webshopsupport.bean.UserSupportBean" scope="session"/>
<%! List<Message> messages = new ArrayList<>();
%>
<%
    if (!(userSupportBean.isLoggedIn())) response.sendRedirect("login.jsp");
    String action = request.getParameter("action");
    if (action != null && "search".equals(action)) {
        String search = request.getParameter("searchInput");
        messages = MessageDao.findByMessageContent(search);
    } else {
        messages = MessageDao.findAll();
    }
%>
<jsp:useBean id="messageService" class="org.etfbl.support.webshopsupport.service.MessageService"/>
<!DOCTYPE html>
<html>
<head>
    <title>Messages</title>
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
            <div class="search-container">
                <form method="post" action="messages.jsp?action=search">
                    <div class="search-container">
                        <i class="material-icons">search</i>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" type="text" id="searchInput" name="searchInput">
                            <label class="mdl-textfield__label" for="searchInput">Message content...</label>
                        </div>
                        <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"
                                style="margin-left: 10px">
                            Search
                        </button>
                    </div>
                </form>
                <button onclick="location.href='messages.jsp';"
                        style="margin-left: 10px"
                        class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
                    Show all
                </button>
            </div>
            <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="width: 100%">
                <thead>
                <tr>
                    <th class="mdl-data-table__cell--non-numeric">From</th>
                    <th class="mdl-data-table__cell--non-numeric">Title</th>
                    <th class="mdl-data-table__cell--non-numeric">Message</th>
                    <th class="mdl-data-table__cell--non-numeric">Date</th>
                </tr>
                </thead>
                <tbody>
                <% for (Message message : messages) {%>
                    <tr class="<%=message.getRead()?"is-read":"not-read"%>"
                        onclick="location.href='send_message.jsp?id=<%=message.getId()%>'">
                        <td class="mdl-data-table__cell--non-numeric"><%=message.getUser().getFullName()%>
                        </td>
                        <td class="mdl-data-table__cell--non-numeric"><%=message.getTitle()%>
                        </td>
                        <td class="mdl-data-table__cell--non-numeric"><%=message.getMessage()%>
                        </td>
                        <td class="mdl-data-table__cell--non-numeric"><%=message.getDate()%>
                        </td>
                        <td class="mdl-data-table__cell--non-numeric"><a class="<%=message.getRead()?"is-read":"not-read"%>"
                                                                         href="send_message.jsp?id=<%=message.getId()%>">Send
                            message
                            &gt&gt</a></td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </main>
</div>
</body>
</html>
