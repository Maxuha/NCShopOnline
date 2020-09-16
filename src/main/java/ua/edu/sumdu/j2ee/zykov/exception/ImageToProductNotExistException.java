package ua.edu.sumdu.j2ee.zykov.exception;

public class ImageToProductNotExistException extends NotExistException {
    public ImageToProductNotExistException(Integer imageToProductId) {
        super(String.format("Image to product %d not exist", imageToProductId));
    }

    public ImageToProductNotExistException(Integer imageToProductId, Throwable cause) {
        super(String.format("Image to product %d not exist", imageToProductId), cause);
    }
}
