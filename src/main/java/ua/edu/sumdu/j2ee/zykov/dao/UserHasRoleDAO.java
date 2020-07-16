package ua.edu.sumdu.j2ee.zykov.dao;

import ua.edu.sumdu.j2ee.zykov.model.Role;
import ua.edu.sumdu.j2ee.zykov.model.User;

import java.util.List;

public interface UserHasRoleDAO {
    List<Role> getRoleByUserId(Integer userId);
    void save(User user, Role role);
}
