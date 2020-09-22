package ua.edu.sumdu.j2ee.zykov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.exception.UserNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.User;
import ua.edu.sumdu.j2ee.zykov.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestControllerApi {
    private static final Logger logger = LoggerFactory.getLogger(UserRestControllerApi.class);
    private final UserService userService;

    public UserRestControllerApi(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUser() {
        logger.info("Request to receive all users");
        return userService.getUserAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getByIdUser(@PathVariable int id) {
        logger.info("Request to receive user by id {}", id);
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = ResponseEntity.ok().body(userService.getUserById(id));
        } catch (UserNotExistException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public User getByUserNameUser(@RequestParam String username) {
        logger.info("Request to receive user by username {}", username);
        return userService.getUserByUserName(username);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public User createUser(@RequestBody User user) {
        logger.info("Request to create new user {}", user);
        return userService.addUser(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        logger.info("Request to update user {}", user);
        ResponseEntity<?> responseEntity;
        try {
            User userFromDb = userService.getUserById(user.getId());
            userFromDb.setUserName(user.getUserName());
            userFromDb.setPassword(user.getPassword());
            responseEntity = ResponseEntity.ok().body(userService.updateUser(userFromDb));
        } catch (UserNotExistException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> removeUser(@PathVariable Integer id) {
        logger.info("Request to remove user {}", id);
        ResponseEntity<?> responseEntity;
        try {
            User userFromDb = userService.getUserById(id);
            responseEntity = ResponseEntity.ok().body(userFromDb);
        } catch (UserNotExistException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
