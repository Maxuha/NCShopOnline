package ua.edu.sumdu.j2ee.zykov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.exception.ShipperNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.Shipper;
import ua.edu.sumdu.j2ee.zykov.service.ShipperService;

import java.util.List;

@RestController
@RequestMapping("/api/shipper")
@CrossOrigin(origins = "http://localhost:3000")
public class ShipperRestControllerApi {
    private static final Logger logger = LoggerFactory.getLogger(ShipperRestControllerApi.class);
    private final ShipperService shipperService;

    public ShipperRestControllerApi(ShipperService shipperService) {
        this.shipperService = shipperService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Shipper> getAllShipper() {
        logger.info("Request to receive shippers");
        return shipperService.getShipperAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getByIdShipper(@PathVariable int id) {
        logger.info("Request to receive shipper by id {}", id);
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = ResponseEntity.ok().body(shipperService.getShipperById(id));
        } catch (ShipperNotExistException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Shipper createShipper(@RequestBody Shipper shipper) {
        logger.info("Request to create new shipper {}", shipper);
        return shipperService.addShipper(shipper);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateShipper(@RequestBody Shipper shipper) {
        logger.info("Request to update shipper {}", shipper);
        ResponseEntity<?> responseEntity;
        try {
            Shipper shipperFromDb = shipperService.getShipperById(shipper.getUser().getId());
            shipperFromDb.setCompanyName(shipper.getCompanyName());
            shipperFromDb.setUser(shipper.getUser());
            responseEntity = ResponseEntity.ok().body(shipperService.updateShipper(shipperFromDb));
        } catch (ShipperNotExistException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> removeUser(@PathVariable Integer id) {
        logger.info("Request to remove shipper {}", id);
        ResponseEntity<?> responseEntity;
        try {
            Shipper shipperFromDb = shipperService.getShipperById(id);
            responseEntity = ResponseEntity.ok().body(shipperService.deleteShipper(shipperFromDb));
        } catch (ShipperNotExistException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
