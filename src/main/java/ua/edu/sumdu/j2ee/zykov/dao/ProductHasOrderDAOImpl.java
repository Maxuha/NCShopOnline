package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.mapper.ProductHasOrderMapper;
import ua.edu.sumdu.j2ee.zykov.model.ProductHasOrder;

import java.util.List;

@Repository
public class ProductHasOrderDAOImpl implements ProductHasOrderDAO {
    private final JdbcTemplate jdbcTemplate;

    public ProductHasOrderDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ProductHasOrder> findAll() {
        String sql = "SELECT * FROM product_has_order LEFT JOIN \"order\" o on product_has_order.order_id = o.id" +
                " LEFT JOIN product p on product_has_order.product_id = p.id LEFT JOIN \"user\" u on o.user_id = u.id" +
                " LEFT JOIN category c on p.category_id = c.id LEFT JOIN image i on c.image_id = i.id" +
                " LEFT JOIN shipper s on p.shipper_id = s.user_id LEFT JOIN customer c2 on o.user_id = c2.user_id;";
        return jdbcTemplate.query(sql, new ProductHasOrderMapper());
    }

    @Override
    public List<ProductHasOrder> findByProduct(int id) {
        String sql = "SELECT * FROM product_has_order LEFT JOIN \"order\" o on product_has_order.order_id = o.id" +
                " LEFT JOIN product p on product_has_order.product_id = p.id LEFT JOIN \"user\" u on o.user_id = u.id" +
                " LEFT JOIN category c on p.category_id = c.id LEFT JOIN image i on c.image_id = i.id" +
                " LEFT JOIN shipper s on p.shipper_id = s.user_id LEFT JOIN customer c2 on o.user_id = c2.user_id WHERE product_id = ?;";
        return jdbcTemplate.query(sql, new ProductHasOrderMapper(), id);
    }

    @Override
    public ProductHasOrder findByOrder(int id) {
        String sql = "SELECT * FROM product_has_order LEFT JOIN \"order\" o on product_has_order.order_id = o.id" +
                " LEFT JOIN product p on product_has_order.product_id = p.id LEFT JOIN \"user\" u on o.user_id = u.id" +
                " LEFT JOIN category c on p.category_id = c.id LEFT JOIN image i on c.image_id = i.id" +
                " LEFT JOIN shipper s on p.shipper_id = s.user_id LEFT JOIN customer c2 on o.user_id = c2.user_id WHERE o.id = ?;";
        return jdbcTemplate.queryForObject(sql, new ProductHasOrderMapper(), id);
    }

    @Override
    public ProductHasOrder save(ProductHasOrder productHasOrder) {
        String sql = "INSERT INTO product_has_order (product_id, order_id, count) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, productHasOrder.getProduct().getId(), productHasOrder.getOrder().getId(), productHasOrder.getCount());
        return productHasOrder;
    }

    @Override
    public ProductHasOrder update(ProductHasOrder productHasOrder) {
        String sql = "UPDATE product_has_order SET product_id = ?, count = ? WHERE order_id = ?";
        jdbcTemplate.update(sql, productHasOrder.getProduct().getId(), productHasOrder.getCount(), productHasOrder.getOrder().getId());
        return productHasOrder;
    }

    @Override
    public ProductHasOrder delete(ProductHasOrder productHasOrder) {
        String sql = "DELETE FROM product_has_order WHERE order_id = ?";
        jdbcTemplate.update(sql, productHasOrder.getOrder().getId());
        return productHasOrder;
    }
}
