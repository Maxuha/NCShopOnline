package ua.edu.sumdu.j2ee.zykov.exception;

public class ProductNotExistException extends NotExistException {
    public ProductNotExistException(Integer productId) {
        super(String.format("Product %d not exist", productId));
    }

    public ProductNotExistException(Integer productId, Throwable cause) {
        super(String.format("Product %d not exist", productId), cause);
    }
}
