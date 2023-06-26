<header class="mdl-layout__header">
    <div class="mdl-layout__header-row">
        <span class="mdl-layout-title">Customer support</span>
        <div class="mdl-layout-spacer"></div>
        <nav class="mdl-navigation mdl-layout--large-screen-only">
            <% if (userBean.isLoggedIn()) { %>
            <span class="mdl-layout-title" style="padding-right: 10px"><%=userBean.getFirstName() + " " + userBean.getLastName()%></span>
            <button onclick="location.href='logout.jsp'"
                    class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent">
                Logout
            </button>
            <%}%>
        </nav>
    </div>
</header>
