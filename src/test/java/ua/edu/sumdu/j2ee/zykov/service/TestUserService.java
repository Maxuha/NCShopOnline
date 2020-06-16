package ua.edu.sumdu.j2ee.zykov.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.edu.sumdu.j2ee.zykov.dao.UserDAO;
import ua.edu.sumdu.j2ee.zykov.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestUserService {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDAO userDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_save_user_it_should_return_user() {
        User user = new User("maksym", "parolyaNet0");
        userService.addUser(user);
        verify(userDAO, times(1)).save(user);
    }

    @Test
    public void get_all_users() {
        List<User> users = new ArrayList<>();
        User user1 = new User("maksym", "parolyaNet0");
        User user2 = new User("vasya", "parolyaNet1");
        User user3 = new User("petya", "parolyaNet2");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        when(userDAO.findAll()).thenReturn(users);
        List<User> userList = userService.getUserAll();
        assertEquals(3, userList.size());
        verify(userDAO, times(1)).findAll();
    }

    @Test
    public void get_by_id_user() {
        when(userDAO.findById(3)).thenReturn(new User("maksym",  "parolyaNet0"));
        User user = userService.getUserById(3);
        assertEquals("maksym", user.getUserName());
        assertEquals("parolyaNet0", user.getPassword());
    }

    @Test
    public void get_by_username_user() {
        when(userDAO.findByUserName("maksym")).thenReturn(new User("maksym",  "parolyaNet0"));
        User user = userService.getUserByUserName("maksym");
        assertEquals("maksym", user.getUserName());
        assertEquals("parolyaNet0", user.getPassword());
    }

    @Test
    public void when_update_user_it_should_return_user() {
        User user = new User(1, "maksym", "parolyaNet");
        userService.updateUser(user);
        verify(userDAO, times(1)).update(user);
    }

    @Test
    public void when_delete_user_it_should_return_user() {
        User user = new User(1, "maksym", "parolyaNet0");
        userService.deleteUser(user);
        verify(userDAO, times(1)).delete(user);
    }
}
