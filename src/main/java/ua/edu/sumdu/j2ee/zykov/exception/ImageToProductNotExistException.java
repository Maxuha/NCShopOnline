package ua.edu.sumdu.j2ee.zykov.exception;

public class ImageToProductNotExistException extends NotExistException {

    public ImageToProductNotExistException(Long imageToProductId, Throwable cause) {
        super(String.format("Image to product %d not exist", imageToProductId), cause);
    }
}
