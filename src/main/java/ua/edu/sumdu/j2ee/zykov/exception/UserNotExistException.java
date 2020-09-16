package ua.edu.sumdu.j2ee.zykov.exception;

import ua.edu.sumdu.j2ee.zykov.model.EntityField;

public class UserNotExistException extends NotExistException {
    public UserNotExistException(EntityField entityField) {
        super(String.format("User %s %d not exist", entityField.getField(), entityField.getId()));
    }

    public UserNotExistException(EntityField entityField, Throwable cause) {
        super(String.format("User %s %d not exist", entityField.getField(), entityField.getId()), cause);
    }
}
