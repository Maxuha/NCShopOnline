package ua.edu.sumdu.j2ee.zykov.exception;

public class ProductHasOrderNotExistException extends NotExistException {
    public ProductHasOrderNotExistException(Integer productHasOrderId) {
        super(String.format("Product has order %d not exist", productHasOrderId));
    }

    public ProductHasOrderNotExistException(Integer productHasOrderId, Throwable cause) {
        super(String.format("Product has order %d not exist", productHasOrderId), cause);
    }
}
