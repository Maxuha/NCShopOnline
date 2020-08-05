package ua.edu.sumdu.j2ee.zykov.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.edu.sumdu.j2ee.zykov.dao.ShipperDAO;
import ua.edu.sumdu.j2ee.zykov.model.Shipper;
import ua.edu.sumdu.j2ee.zykov.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestShipperService {

    @InjectMocks
    private ShipperServiceImpl shipperService;

    @Mock
    private ShipperDAO shipperDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /*@Test
    public void when_save_shipper_it_should_return_shipper() {
        User user = new User(1, "maksym", "parolyaNet0");
        Shipper shipper = new Shipper(user, "NetCracker");
        shipperService.addShipper(shipper);
        verify(shipperDAO, times(1)).save(shipper);
    }

    @Test
    public void get_all_shippers() {
        User user1 = new User(1, "maksym", "parolyaNet0");
        User user2 = new User(2, "vasya", "parolyaNet1");
        User user3 = new User(3, "petya", "parolyaNet2");
        List<Shipper> shippers = new ArrayList<>();
        Shipper shipper1 = new Shipper(user1, "NetCracker");
        Shipper shipper2 = new Shipper(user2, "McDonalds");
        Shipper shipper3 = new Shipper(user3, "Mercedes");
        shippers.add(shipper1);
        shippers.add(shipper2);
        shippers.add(shipper3);
        when(shipperDAO.findAll()).thenReturn(shippers);
        List<Shipper> shipperAll = shipperService.getShipperAll();
        assertEquals(3, shipperAll.size());
        verify(shipperDAO, times(1)).findAll();
    }

    @Test
    public void get_by_id_shipper() {
        User user = new User(1, "maksym", "parolyaNet0");
        when(shipperDAO.findById(1)).thenReturn(new Shipper(user, "NetCracker"));
        Shipper shipper = shipperService.getShipperById(1);
        assertEquals(user, shipper.getUser());
        assertEquals("NetCracker", shipper.getCompanyName());
    }

    @Test
    public void when_update_shipper_it_should_return_shipper() {
        User user = new User(1, "maksym", "parolyaNet0");
        Shipper shipper = new Shipper(user, "NetCracker");
        shipperService.updateShipper(shipper);
        verify(shipperDAO, times(1)).update(shipper);
    }

    @Test
    public void when_delete_shipper_it_should_return_shipper() {
        User user = new User(1, "maksym", "parolyaNet0");
        Shipper shipper = new Shipper(user, "Maksym Zykov");
        shipperService.deleteShipper(shipper);
        verify(shipperDAO, times(1)).delete(shipper);
    }*/
}
