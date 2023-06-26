package org.etfbl.support.webshopsupport.dao;

import org.etfbl.support.webshopsupport.config.DatabaseConnection;
import org.etfbl.support.webshopsupport.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public static final String SELECT_BY_USERNAME = "select * from user_support where username=?";

    public static User selectByUsername(String username) {
        User user = null;
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection, SELECT_BY_USERNAME, false, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = getUserFromResultSet(rs);
            }
            statement.close();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return user;
    }

    public static User getUserFromResultSet(ResultSet rs) throws SQLException {
        return getUserFromResultSet(rs, "id");
    }

    public static User getUserFromResultSet(ResultSet rs, String idColumnName) throws SQLException {
        return new User(rs.getLong(idColumnName), rs.getString("username"), rs.getString("password"), rs.getString(
                "first_name"), rs.getString("last_name"));
    }

}
