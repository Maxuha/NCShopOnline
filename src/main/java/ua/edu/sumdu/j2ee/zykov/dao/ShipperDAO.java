package ua.edu.sumdu.j2ee.zykov.dao;


import ua.edu.sumdu.j2ee.zykov.exception.ShipperNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.Shipper;

import java.util.List;

public interface ShipperDAO {
    List<Shipper> findAll();
    Shipper findById(int id) throws ShipperNotExistException;
    Shipper save(Shipper shipper);
    Shipper update(Shipper shipper);
    Shipper delete(Shipper shipper);
}
