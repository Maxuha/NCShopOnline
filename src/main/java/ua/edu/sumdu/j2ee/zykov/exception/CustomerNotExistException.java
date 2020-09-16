package ua.edu.sumdu.j2ee.zykov.exception;

public class CustomerNotExistException extends NotExistException {
    public CustomerNotExistException(Long customerId) {
        super(String.format("Customer %d not exist", customerId));
    }

    public CustomerNotExistException(Long customerId, Throwable cause) {
        super(String.format("Customer %d not exist", customerId), cause);
    }
}
