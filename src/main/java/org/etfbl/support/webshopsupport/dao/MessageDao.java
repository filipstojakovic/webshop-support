package org.etfbl.support.webshopsupport.dao;

import org.etfbl.support.webshopsupport.config.DatabaseConnection;
import org.etfbl.support.webshopsupport.dto.Message;
import org.etfbl.support.webshopsupport.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {

    public static final String SELECT_ALL = "select cs.*, date, u.id as user_id, username, first_name, " + "last_name, " +
            "email" + " from contact_support cs " + "JOIN user u ON cs.user_id=u.id";
    public static final String SELECT_BY_MESSAGE_ID = SELECT_ALL + " where cs.id=?";
    public static final String UPDATE_MESSAGE_READ = "update contact_support set is_read=true where id=?";
    public static final String SELECT_BY_MESSAGE_CONTENT = SELECT_ALL + " where message like ?";
    public static final String ORDER_BY_DATE_AND_IS_READ = " order by date desc";

    public static List<Message> findAll() {

        List<Message> messages = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL + ORDER_BY_DATE_AND_IS_READ);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Message message = extractMessage(rs);
                messages.add(message);
            }
            statement.close();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return messages;
    }

    public static Message findById(Long messageId) {
        Message message = null;
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Object[] values = {messageId};
            PreparedStatement statement = DAOUtil.prepareStatement(connection, SELECT_BY_MESSAGE_ID, false, values);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                message = extractMessage(rs);
            }
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return message;
    }

    private static User extractUser(ResultSet rs) throws SQLException {
        return new User(rs.getLong("user_id"), rs.getString("username"), rs.getString("first_name"), rs.getString(
                "last_name"), rs.getString("email"));
    }

    public static List<Message> findByMessageContent(String content) {
        if (content == null || "".equals(content)) {
            return findAll();
        }
        List<Message> messages = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Object[] values = {"%" + content.trim() + "%"};
            PreparedStatement statement = DAOUtil.prepareStatement(connection, SELECT_BY_MESSAGE_CONTENT + ORDER_BY_DATE_AND_IS_READ, false,
                    values);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Message message = extractMessage(rs);
                messages.add(message);
            }
            statement.close();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return messages;
    }

    private static Message extractMessage(ResultSet rs) throws SQLException {
        User user = extractUser(rs);
        return new Message(rs.getLong("id"), user, rs.getString("title"), rs.getString("message"), rs.getDate("date")
                , rs.getBoolean("is_read"));
    }

    public static void readMessage(Long messageId) {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_MESSAGE_READ);
            statement.setLong(1, messageId);
            int rowsAffected = statement.executeUpdate();
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
