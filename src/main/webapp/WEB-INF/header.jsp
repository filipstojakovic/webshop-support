<header class="mdl-layout__header">
    <div class="mdl-layout__header-row">
        <span class="mdl-layout-title">Customer support</span>
        <% if (userSupportBean.isLoggedIn()) { %>
        <button onclick="location.href='messages.jsp'"
                style="margin-left: 10px"
                class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent">
            Messages
        </button>
        <%}%>
        <div class="mdl-layout-spacer"></div>
        <nav class="mdl-navigation">
            <% if (userSupportBean.isLoggedIn()) { %>
            <span class="mdl-layout-title"
                  style="padding-right: 10px"><%=userSupportBean.getFullName()%></span>
            <button onclick="location.href='logout.jsp'"
                    class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent">
                Logout
            </button>
            <%}%>
        </nav>
    </div>
</header>
