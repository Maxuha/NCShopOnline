package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.zykov.dao.UserHasRoleDAO;
import ua.edu.sumdu.j2ee.zykov.model.Role;
import ua.edu.sumdu.j2ee.zykov.model.User;

@Service
public class UserHasRoleServiceImpl implements UserHasRoleService {
    private final UserHasRoleDAO userHasRoleDAO;

    public UserHasRoleServiceImpl(UserHasRoleDAO userHasRoleDAO) {
        this.userHasRoleDAO = userHasRoleDAO;
    }

    @Override
    public void addUserHasRole(User user, Role role) {
        userHasRoleDAO.save(user, role);
    }
}
