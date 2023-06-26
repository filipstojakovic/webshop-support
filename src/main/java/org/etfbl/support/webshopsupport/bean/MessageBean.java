package org.etfbl.support.webshopsupport.bean;

import java.io.Serializable;

public class MessageBean implements Serializable {

    private static final long serialVersionUID = 3189872055327403476L;

    private String email;
    private String title;
    private String content;

    public MessageBean() {
    }

    public MessageBean(String email, String subject, String content) {
        this.email = email;
        this.title = subject;
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageBean that = (MessageBean) o;

        if (!email.equals(that.email)) return false;
        if (!title.equals(that.title)) return false;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }
}
