package ua.edu.sumdu.j2ee.zykov.exception;

import ua.edu.sumdu.j2ee.zykov.model.EntityField;

public class OrderNotExistException extends NotExistException {
    public OrderNotExistException(EntityField entityField) {
        super(String.format("Order with %s %d not exist", entityField.getField(), entityField.getId()));
    }

    public OrderNotExistException(EntityField entityField, Throwable cause) {
        super(String.format("Order with %s %d not exist", entityField.getField(), entityField.getId()), cause);
    }
}
