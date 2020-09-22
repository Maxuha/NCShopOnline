package ua.edu.sumdu.j2ee.zykov.service;


import ua.edu.sumdu.j2ee.zykov.exception.UserNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.User;

import java.util.List;

public interface UserService {
    List<User> getUserAll();
    User getUserById(int id) throws UserNotExistException;
    User getUserByUserName(String username);
    User addUser(User user);
    User updateUser(User user);
    User deleteUser(User user);
}
