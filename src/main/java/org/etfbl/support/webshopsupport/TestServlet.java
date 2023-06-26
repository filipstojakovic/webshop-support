package org.etfbl.support.webshopsupport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.etfbl.support.webshopsupport.service.MailService;

import java.io.IOException;
import java.security.GeneralSecurityException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    private final MailService mailService;

    public TestServlet() throws GeneralSecurityException, IOException {
        mailService = new MailService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var writer = resp.getWriter();
        try {
            //TODO: make async
            mailService.sendMail("filip.stojakovic1@gmail.com", "test", "test2");
        } catch (Exception e) {
            System.err.println("Unable to send message: " + e.getMessage());
            throw new RuntimeException(e);
        }
        writer.println("<h1>hello</h1>");
    }

}
