package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.exception.ShipperNotExistException;
import ua.edu.sumdu.j2ee.zykov.mapper.ShipperMapper;
import ua.edu.sumdu.j2ee.zykov.model.EntityField;
import ua.edu.sumdu.j2ee.zykov.model.Shipper;

import java.util.List;

@Repository
public class ShipperDAOImpl implements ShipperDAO {
    private final JdbcTemplate jdbcTemplate;

    public ShipperDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Shipper> findAll() {
        String sql = "SELECT * FROM shipper LEFT JOIN \"user\" ON shipper.user_id = \"user\".id;";
        return jdbcTemplate.query(sql, new ShipperMapper());
    }

    @Override
    public Shipper findById(int id) throws ShipperNotExistException {
        String sql = "SELECT * FROM shipper LEFT JOIN \"user\" ON shipper.user_id = \"user\".id WHERE user_id = ?;";
        final String field = "id";
        try {
            return jdbcTemplate.queryForObject(sql, new ShipperMapper(), id);
        } catch (DataAccessException e) {
            throw new ShipperNotExistException(new EntityField(id, field), e);
        }
    }

    @Override
    public Shipper save(Shipper shipper) {
        String sql = "INSERT INTO shipper (user_id, company_name) VALUES (?, ?);";
        jdbcTemplate.update(sql, shipper.getUser().getId(), shipper.getCompanyName());
        return shipper;
    }

    @Override
    public Shipper update(Shipper shipper) {
        String sql = "UPDATE shipper SET company_name = ? WHERE user_id = ?;";
        jdbcTemplate.update(sql, shipper.getCompanyName(), shipper.getUser().getId());
        return shipper;
    }

    @Override
    public Shipper delete(Shipper shipper) {
        String sql = "DELETE FROM shipper WHERE user_id = ?";
        jdbcTemplate.update(sql, shipper.getUser().getId());
        return shipper;
    }
}
