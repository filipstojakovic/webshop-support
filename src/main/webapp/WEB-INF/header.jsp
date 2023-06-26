<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <header class="mdl-layout__header">
        <div class="mdl-layout__header-row">
            <span class="mdl-layout-title">Customer support</span>
            <% if (userBean.isLoggedIn()) { %>
            <button onclick="location.href='messages.jsp'"
                    style="margin-left: 10px"
                    class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent">
                Messages
            </button>
            <%}%>
            <div class="mdl-layout-spacer"></div>
            <nav class="mdl-navigation">
                <% if (userBean.isLoggedIn()) { %>
                <span class="mdl-layout-title"
                      style="padding-right: 10px"><%=userBean.getFullName()%></span>
                <button onclick="location.href='logout.jsp'"
                        class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent">
                    Logout
                </button>
                <%}%>
            </nav>
        </div>
    </header>
</div>
