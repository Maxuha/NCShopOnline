package ua.edu.sumdu.j2ee.zykov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.model.Shipper;
import ua.edu.sumdu.j2ee.zykov.service.ShipperService;

import java.util.List;

@RestController
@RequestMapping("/api/shipper")
public class ShipperRestControllerApi {
    private final ShipperService shipperService;

    public ShipperRestControllerApi(ShipperService shipperService) {
        this.shipperService = shipperService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Shipper> getAllShipper() {
        return shipperService.getShipperAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Shipper getByIdShipper(@PathVariable int id) {
        return shipperService.getShipperById(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Shipper createShipper(@RequestBody Shipper shipper) {
        System.out.println(shipper.getUser().getId());
        return shipperService.addShipper(shipper);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Shipper updateShipper(@RequestBody Shipper shipper) {
        return shipperService.updateShipper(shipper);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Shipper removeUser(@RequestBody Shipper shipper) {
        return shipperService.deleteShipper(shipper);
    }
}
