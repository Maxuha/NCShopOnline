package ua.edu.sumdu.j2ee.zykov.exception;

import ua.edu.sumdu.j2ee.zykov.model.EntityField;

public class ImageToProductNotExistException extends NotExistException {
    public ImageToProductNotExistException(EntityField entityField) {
        super(String.format("Image to product with %s %d not exist", entityField.getField(), entityField.getId()));
    }

    public ImageToProductNotExistException(EntityField entityField, Throwable cause) {
        super(String.format("Image to product with %s %d not exist", entityField.getField(), entityField.getId()), cause);
    }
}
