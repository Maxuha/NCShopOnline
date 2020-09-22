package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.zykov.dao.ShipperDAO;
import ua.edu.sumdu.j2ee.zykov.exception.CustomerNotExistException;
import ua.edu.sumdu.j2ee.zykov.exception.ShipperNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.Shipper;

import java.util.List;

@Service
public class ShipperServiceImpl implements ShipperService {
    private final ShipperDAO shipperDAO;

    public ShipperServiceImpl(ShipperDAO shipperDAO) {
        this.shipperDAO = shipperDAO;
    }

    @Override
    public List<Shipper> getShipperAll() {
        return shipperDAO.findAll();
    }

    @Override
    public Shipper getShipperById(int id) throws ShipperNotExistException {
        return shipperDAO.findById(id);
    }

    @Override
    public Shipper addShipper(Shipper shipper) {
        return shipperDAO.save(shipper);
    }

    @Override
    public Shipper updateShipper(Shipper shipper) {
        return shipperDAO.update(shipper);
    }

    @Override
    public Shipper deleteShipper(Shipper shipper) {
        return shipperDAO.delete(shipper);
    }
}
