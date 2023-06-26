package org.etfbl.support.webshopsupport.dao;

import org.etfbl.support.webshopsupport.config.DatabaseConnection;
import org.etfbl.support.webshopsupport.dto.User;
import org.etfbl.support.webshopsupport.dto.UserSupport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSupportDao {

    public static final String SELECT_BY_USERNAME = "select * from user_support where username=?";

    public static UserSupport selectByUsername(String username) {
        UserSupport user = null;
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

    public static UserSupport getUserFromResultSet(ResultSet rs) throws SQLException {
        return getUserFromResultSet(rs, "id");
    }

    public static UserSupport getUserFromResultSet(ResultSet rs, String idColumnName) throws SQLException {
        return new UserSupport(rs.getLong(idColumnName), rs.getString("username"), rs.getString("password"), rs.getString(
                "first_name"), rs.getString("last_name"));
    }

}
