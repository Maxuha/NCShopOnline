package ua.edu.sumdu.j2ee.zykov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.model.Image;
import ua.edu.sumdu.j2ee.zykov.service.ImageService;

import java.util.List;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageRestControllerApi {
    private static final Logger logger = LoggerFactory.getLogger(ImageRestControllerApi.class);
    private final ImageService imageService;

    public ImageRestControllerApi(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Image> getAllImages() {
        logger.info("Request to receive all images");
        return imageService.getImageAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Image getByIdImage(@PathVariable int id) {
        logger.info("Request to receive image by id {}", id);
        return imageService.getImageById(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Image createImage(@RequestBody Image image) {
        logger.info("Request to create new image {}", image);
        return imageService.addImage(image);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Image updateImage(@RequestBody Image image) {
        logger.info("Request to update image {}", image);
        return imageService.updateImage(image);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Image removeImage(@RequestBody Image image) {
        logger.info("Request to delete image {}", image);
        return imageService.deleteImage(image);
    }
}
