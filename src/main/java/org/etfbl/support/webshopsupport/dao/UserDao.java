package org.etfbl.support.webshopsupport.dao;

import org.etfbl.support.webshopsupport.config.DatabaseConnection;
import org.etfbl.support.webshopsupport.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    public static final String SELECT_BY_USERNAME = "select * from user_support where username=?";

    public static User selectByUsername(String username) {
        User user = null;
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection, SELECT_BY_USERNAME, false, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getString(
                        "first_name"), rs.getString("last_name"));
            }
            statement.close();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return user;
    }
}
