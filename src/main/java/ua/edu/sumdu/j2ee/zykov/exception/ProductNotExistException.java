package ua.edu.sumdu.j2ee.zykov.exception;

public class ProductNotExistException extends NotExistException {
    public ProductNotExistException(Long productId) {
        super(String.format("Product %d not exist", productId));
    }

    public ProductNotExistException(Long productId, Throwable cause) {
        super(String.format("Product %d not exist", productId), cause);
    }
}
