package ua.edu.sumdu.j2ee.zykov.service;


import ua.edu.sumdu.j2ee.zykov.model.Shipper;

import java.util.List;

public interface ShipperService {
    List<Shipper> getShipperAll();
    Shipper getShipperById(int id);
    Shipper addShipper(Shipper shipper);
    Shipper updateShipper(Shipper shipper);
    Shipper deleteShipper(Shipper shipper);
}
