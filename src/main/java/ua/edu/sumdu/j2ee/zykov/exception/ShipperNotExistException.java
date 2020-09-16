package ua.edu.sumdu.j2ee.zykov.exception;

public class ShipperNotExistException extends NotExistException {
    public ShipperNotExistException(Long shipperId, Throwable cause) {
        super(String.format("Product %d not exist", shipperId), cause);
    }
}
