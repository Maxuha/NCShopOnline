package ua.edu.sumdu.j2ee.zykov.exception;

import ua.edu.sumdu.j2ee.zykov.model.EntityField;

public class ProductHasOrderNotExistException extends NotExistException {
    public ProductHasOrderNotExistException(EntityField entityField) {
        super(String.format("Product has order with %s %d not exist", entityField.getField(), entityField.getId()));
    }

    public ProductHasOrderNotExistException(EntityField entityField, Throwable cause) {
        super(String.format("Product has order with %s %d not exist", entityField.getField(), entityField.getId()), cause);
    }
}
