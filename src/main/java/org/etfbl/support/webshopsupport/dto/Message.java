package org.etfbl.support.webshopsupport.dto;

import java.util.Date;

public class Message {

    private Long id;
    private User user;
    private String title;
    private String message;
    private Date date;
    private Boolean isRead;

    public Message() {
    }

    public Message(Long id, User user, String title, String message, Date date, Boolean isRead) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.message = message;
        this.date = date;
        this.isRead = isRead;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }
}
