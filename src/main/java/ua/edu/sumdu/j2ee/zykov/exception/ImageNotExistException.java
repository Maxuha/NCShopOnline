package ua.edu.sumdu.j2ee.zykov.exception;

import ua.edu.sumdu.j2ee.zykov.model.EntityField;

public class ImageNotExistException extends NotExistException {
    public ImageNotExistException(EntityField entityField) {
        super(String.format("Image with $s %d not exist", entityField.getField(), entityField.getId()));
    }

    public ImageNotExistException(EntityField entityField, Throwable cause) {
        super(String.format("Image with $s %d not exist", entityField.getField(), entityField.getId()), cause);
    }
}
