package ua.edu.sumdu.j2ee.zykov.exception;

public class UserNotExistException extends NotExistException {
    public UserNotExistException(Integer userId) {
        super(String.format("User %d not exist", userId));
    }

    public UserNotExistException(Integer userId, Throwable cause) {
        super(String.format("User %d not exist", userId), cause);
    }
}
