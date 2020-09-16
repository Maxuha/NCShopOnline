package ua.edu.sumdu.j2ee.zykov.exception;

public class CategoryNotExistException extends NotExistException {
    public CategoryNotExistException(Long categoryId, Throwable cause) {
        super(String.format("Category %d not exist", categoryId), cause);
    }
}
