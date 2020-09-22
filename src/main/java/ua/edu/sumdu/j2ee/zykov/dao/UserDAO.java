package ua.edu.sumdu.j2ee.zykov.dao;


import ua.edu.sumdu.j2ee.zykov.exception.UserNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findById(int id) throws UserNotExistException;
    User findByUserName(String username);
    User save(User user);
    User update(User user);
    User delete(User user);
}
