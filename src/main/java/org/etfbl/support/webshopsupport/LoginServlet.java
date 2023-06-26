package org.etfbl.support.webshopsupport;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.etfbl.support.webshopsupport.config.DatabaseConnection;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String rawPassword = request.getParameter("password");

        try {
            // Execute query to validate user's credentials
            String sql = "SELECT * FROM user WHERE username='" + username + "'";
            ResultSet rs = DatabaseConnection.getInstance().executeQuery(sql);

            // Check if credentials are valid
            if (rs.next()) {
                String encodedPassword = rs.getString("password");
                if (passwordEncoder.matches(rawPassword, encodedPassword)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);

                    // Redirect to success page
                    response.sendRedirect("success.jsp");
                } else {
                    dispatchErrorMessage(request, response);
                }
                // Store user's session information
            } else {
                // Display error message
                dispatchErrorMessage(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void dispatchErrorMessage(HttpServletRequest request,
                                             HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorMessage", "Invalid username or password");
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
}
