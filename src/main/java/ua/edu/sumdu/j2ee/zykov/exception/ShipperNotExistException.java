package ua.edu.sumdu.j2ee.zykov.exception;

import ua.edu.sumdu.j2ee.zykov.model.EntityField;

public class ShipperNotExistException extends NotExistException {
    public ShipperNotExistException(EntityField entityField) {
        super(String.format("Product with %s %d not exist", entityField.getField(), entityField.getId()));
    }

    public ShipperNotExistException(EntityField entityField, Throwable cause) {
        super(String.format("Product with %s %d not exist", entityField.getField(), entityField.getId()), cause);
    }
}
