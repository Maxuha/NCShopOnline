package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.mapper.ProductMapper;
import ua.edu.sumdu.j2ee.zykov.model.Product;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private final JdbcTemplate jdbcTemplate;

    public ProductDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM product LEFT JOIN category c on product.category_id = c.id LEFT JOIN shipper s on product.shipper_id = s.user_id LEFT JOIN image i on c.image_id = i.id LEFT JOIN \"user\" u on s.user_id = u.id;";
        return jdbcTemplate.query(sql, new ProductMapper());
    }

    @Override
    public Product findById(int id) {
        String sql = "SELECT * FROM product LEFT JOIN category c on product.category_id = c.id LEFT JOIN shipper s on product.shipper_id = s.user_id LEFT JOIN image i on c.image_id = i.id LEFT JOIN \"user\" u on s.user_id = u.id WHERE product.id = ?;";
        return jdbcTemplate.queryForObject(sql, new ProductMapper(), id);
    }

    @Override
    public Product save(Product product) {
        String sql = "INSERT INTO product (title, description, price, discount, category_id, shipper_id) VALUES (?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, product.getTitle(), product.getDescription(), product.getPrice(), product.getDiscount(), product.getCategory().getId(), product.getShipper().getUser().getId());
        return product;
    }

    @Override
    public Product update(Product product) {
        String sql = "UPDATE product SET title = ?, description = ?, price = ?, discount = ?, category_id = ?, shipper_id = ?";
        jdbcTemplate.update(sql, product.getTitle(), product.getDescription(), product.getPrice(), product.getDiscount(), product.getCategory().getId(), product.getShipper().getUser().getId());
        return product;
    }

    @Override
    public Product delete(Product product) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, product.getId());
        return product;
    }
}
