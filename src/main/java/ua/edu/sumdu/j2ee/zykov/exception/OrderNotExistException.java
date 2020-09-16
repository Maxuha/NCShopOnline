package ua.edu.sumdu.j2ee.zykov.exception;

public class OrderNotExistException extends NotExistException {
    public OrderNotExistException(Long order) {
        super(String.format("Order %d not exist", order));
    }

    public OrderNotExistException(Long order, Throwable cause) {
        super(String.format("Order %d not exist", order), cause);
    }
}
