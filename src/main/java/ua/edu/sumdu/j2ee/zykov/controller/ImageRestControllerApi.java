package ua.edu.sumdu.j2ee.zykov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.exception.ImageNotExistException;
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
    public ResponseEntity<?> getByIdImage(@PathVariable int id) {
        logger.info("Request to receive image by id {}", id);
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = ResponseEntity.ok().body(imageService.getImageById(id));
        } catch (ImageNotExistException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            logger.warn("Image by id {} not exist", id);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Image createImage(@RequestBody Image image) {
        logger.info("Request to create new image {}", image);
        return imageService.addImage(image);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateImage(@RequestBody Image image) {
        logger.info("Request to update image {}", image);
        ResponseEntity<?> responseEntity;
        try {
            Image imageFromDb = imageService.getImageById(image.getId());
            imageFromDb.setImage(image.getImage());
            responseEntity = ResponseEntity.ok().body(imageService.updateImage(imageFromDb));
        } catch (ImageNotExistException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            logger.warn("Image by id {} not exist", image.getId());
        }
        return responseEntity;
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> removeImage(@PathVariable Integer id) {
        logger.info("Request to delete image {}", id);
        ResponseEntity<?> responseEntity;
        try {
            Image imageFromDb = imageService.getImageById(id);
            responseEntity = ResponseEntity.ok().body(imageService.deleteImage(imageFromDb));
        } catch (ImageNotExistException e ) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            logger.warn("Image by id {} not exist", id);
        }
        return responseEntity;
    }
}
