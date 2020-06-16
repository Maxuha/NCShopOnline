package ua.edu.sumdu.j2ee.zykov.mapper;


import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.zykov.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUserName(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}
