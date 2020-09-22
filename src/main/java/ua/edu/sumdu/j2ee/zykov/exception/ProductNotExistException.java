package ua.edu.sumdu.j2ee.zykov.exception;

import ua.edu.sumdu.j2ee.zykov.model.EntityField;

public class ProductNotExistException extends NotExistException {
    public ProductNotExistException(EntityField entityField) {
        super(String.format("Product with %s %d not exist", entityField.getField(), entityField.getId()));
    }

    public ProductNotExistException(EntityField entityField, Throwable cause) {
        super(String.format("Product with %s %d not exist", entityField.getField(), entityField.getId()), cause);
    }
}
