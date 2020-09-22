package ua.edu.sumdu.j2ee.zykov.exception;

import ua.edu.sumdu.j2ee.zykov.model.EntityField;

public class CustomerNotExistException extends NotExistException {
    public CustomerNotExistException(EntityField entityField) {
        super(String.format("Customer with %s %d not exist", entityField.getField(), entityField.getId()));
    }

    public CustomerNotExistException(EntityField entityField, Throwable cause) {
        super(String.format("Customer with %s %d not exist", entityField.getField(), entityField.getId()), cause);
    }
}
