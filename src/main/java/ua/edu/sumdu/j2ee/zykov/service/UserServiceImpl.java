package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.zykov.dao.UserDAO;
import ua.edu.sumdu.j2ee.zykov.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getUserAll() {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public User getUserByUserName(String username) {
        return userDAO.findByUserName(username);
    }

    @Override
    public User addUser(User user) {
        return userDAO.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userDAO.update(user);
    }

    @Override
    public User deleteUser(User user) {
        return userDAO.delete(user);
    }
}
