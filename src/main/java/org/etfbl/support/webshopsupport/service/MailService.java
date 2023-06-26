package org.etfbl.support.webshopsupport.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.codec.binary.Base64;
import org.etfbl.support.webshopsupport.config.GmailCredential;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import static jakarta.mail.Message.RecipientType.TO;

public class MailService {

    private final Gmail service;

    public MailService() throws GeneralSecurityException, IOException {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        service = new Gmail.Builder(httpTransport, jsonFactory, GmailCredential.getCredentials(httpTransport, jsonFactory))
                .setApplicationName("webshop")
                .build();
    }

    public void sendMail(String toEmail, String subject, String message) throws Exception {
        //TODO: maybe make async new Thread().start();
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress("filip.stojakovic@student.etf.unibl.org")); //TODO: extract mail
        email.addRecipient(TO, new InternetAddress(toEmail));
        email.setSubject(subject);
        email.setText(message);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message msg = new Message();
        msg.setRaw(encodedEmail);

        try {
            msg = service.users().messages().send("me", msg).execute();
            System.out.println("Message to: " + toEmail);
            System.out.println(msg.toPrettyString());
        } catch (GoogleJsonResponseException ex) {
            GoogleJsonError error = ex.getDetails();
            if (error.getCode() == 403) {
                System.err.println("Unable to send message: " + ex.getDetails());
            } else {
                throw ex;
            }
        }
    }
}
