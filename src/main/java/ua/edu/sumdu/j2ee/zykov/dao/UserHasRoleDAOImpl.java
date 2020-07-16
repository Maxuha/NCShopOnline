package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.mapper.UserHasRoleMapper;
import ua.edu.sumdu.j2ee.zykov.model.Role;
import ua.edu.sumdu.j2ee.zykov.model.User;

import java.util.List;

@Repository
public class UserHasRoleDAOImpl implements UserHasRoleDAO {
    private final JdbcTemplate jdbcTemplate;

    public UserHasRoleDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> getRoleByUserId(Integer userId) {
        String query = "SELECT role.name FROM role, user_has_role WHERE user_has_role.user_id = ? AND user_has_role.role_id = role.id";
        return jdbcTemplate.query(query, new UserHasRoleMapper(), userId);
    }

    @Override
    public void save(User user, Role role) {
        String query = "INSERT INTO user_has_role (user_id, role_id) VALUES (?, ?);";
        Integer roleId = getRoleIdByName(role.name());
        jdbcTemplate.update(query, user.getId(), roleId);
    }

    private Integer getRoleIdByName(String name) {
        String query = "SELECT id FROM role WHERE name = ?";
        return jdbcTemplate.queryForObject(
                query, new Object[] { name }, Integer.class);
    }
}
