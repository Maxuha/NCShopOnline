package ua.edu.sumdu.j2ee.zykov.exception;

import ua.edu.sumdu.j2ee.zykov.model.EntityField;

public class CategoryNotExistException extends NotExistException {
    public CategoryNotExistException(EntityField entityField) {
        super(String.format("Category with %s %d not exist", entityField.getField(), entityField.getId()));
    }

    public CategoryNotExistException(EntityField entityField, Throwable cause) {
        super(String.format("Category with %s %d not exist", entityField.getField(), entityField.getId()), cause);
    }
}
