package ua.edu.sumdu.j2ee.zykov.exception;

public class CategoryNotExistException extends NotExistException {
    public CategoryNotExistException(Integer categoryId) {
        super(String.format("Category %d not exist", categoryId));
    }

    public CategoryNotExistException(Integer categoryId, Throwable cause) {
        super(String.format("Category %d not exist", categoryId), cause);
    }
}
