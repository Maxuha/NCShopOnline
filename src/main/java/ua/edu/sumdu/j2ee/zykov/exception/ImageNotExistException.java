package ua.edu.sumdu.j2ee.zykov.exception;

public class ImageNotExistException extends NotExistException {
    public ImageNotExistException(Long imageId, Throwable cause) {
        super(String.format("Image %d not exist", imageId), cause);
    }
}
