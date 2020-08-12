package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.mapper.ImageMapper;
import ua.edu.sumdu.j2ee.zykov.mapper.ProductMapper;
import ua.edu.sumdu.j2ee.zykov.model.Image;
import ua.edu.sumdu.j2ee.zykov.model.Product;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private final JdbcTemplate jdbcTemplate;

    public ProductDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll(String sortBy, String sortDir) {
        String sql = "SELECT * FROM product LEFT JOIN category c on product.category_id = c.id LEFT JOIN shipper s on product.shipper_id = s.user_id LEFT JOIN image i on c.image_id = i.id LEFT JOIN \"user\" u on s.user_id = u.id ORDER BY " + sortBy + " " + sortDir + ";";
        return jdbcTemplate.query(sql, new ProductMapper());
    }

    @Override
    public List<Product> findByTitleOrShipper(String searchText) {
        String sql = "SELECT * FROM product LEFT JOIN category c on product.category_id = c.id LEFT JOIN shipper s on product.shipper_id = s.user_id LEFT JOIN image i on c.image_id = i.id LEFT JOIN \"user\" u on s.user_id = u.id WHERE product.title LIKE  '" + searchText + "%' OR s.company_name LIKE '" + searchText + "%';";
        return jdbcTemplate.query(sql, new ProductMapper());
    }

    @Override
    public List<Product> findByCategoryId(int categoryId) {
        String sql = "SELECT * FROM product LEFT JOIN category c on product.category_id = c.id LEFT JOIN shipper s on product.shipper_id = s.user_id LEFT JOIN image i on c.image_id = i.id LEFT JOIN \"user\" u on s.user_id = u.id WHERE product.category_id = ?;";
        return jdbcTemplate.query(sql, new ProductMapper(), categoryId);
    }

    @Override
    public Product findById(int id) {
        String sql = "SELECT * FROM product LEFT JOIN category c on product.category_id = c.id LEFT JOIN shipper s on product.shipper_id = s.user_id LEFT JOIN image i on c.image_id = i.id LEFT JOIN \"user\" u on s.user_id = u.id WHERE product.id = ?;";
        return jdbcTemplate.queryForObject(sql, new ProductMapper(), id);
    }

    @Override
    public Product save(Product product) {
        String sql = "INSERT INTO product (title, description, price, discount, category_id, shipper_id) VALUES (?, ?, ?, ?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getDescription());
            ps.setFloat(3, product.getPrice());
            ps.setFloat(4, product.getDiscount());
            ps.setFloat(5, product.getCategory().getId());
            ps.setFloat(6, product.getShipper().getUser().getId());
            return ps;
        }, keyHolder);
        product.setId((Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id"));
        return product;
    }

    @Override
    public Product update(Product product) {
        String sql = "UPDATE product SET title = ?, description = ?, price = ?, discount = ?, category_id = ?, shipper_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getTitle(), product.getDescription(), product.getPrice(), product.getDiscount(), product.getCategory().getId(), product.getShipper().getUser().getId(), product.getId());
        return product;
    }

    @Override
    public Product delete(Product product) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, product.getId());
        return product;
    }
}
