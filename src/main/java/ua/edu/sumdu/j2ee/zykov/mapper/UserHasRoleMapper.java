package ua.edu.sumdu.j2ee.zykov.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.zykov.model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserHasRoleMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        return Role.valueOf(resultSet.getString("name"));
    }
}
