package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.mapper.ImageToProductMapper;
import ua.edu.sumdu.j2ee.zykov.model.ImageToProduct;

import java.util.List;

@Repository
public class ImageToProductDAOImpl implements ImageToProductDAO {
    private final JdbcTemplate jdbcTemplate;

    public ImageToProductDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ImageToProduct> findAll() {
        String sql = "SELECT * FROM image_to_product LEFT JOIN image i on image_to_product.image_id = i.id LEFT JOIN product p on image_to_product.product_id = p.id LEFT JOIN shipper s on p.shipper_id = s.user_id LEFT JOIN category c on p.category_id = c.id LEFT JOIN \"user\" u on s.user_id = u.id LEFT JOIN image ON c.image_id = image.id;";
        return jdbcTemplate.query(sql, new ImageToProductMapper());
    }

    @Override
    public List<ImageToProduct> findByProductId(int productId) {
        String sql = "SELECT * FROM image_to_product LEFT JOIN image i on image_to_product.image_id = i.id LEFT JOIN product p on image_to_product.product_id = p.id LEFT JOIN shipper s on p.shipper_id = s.user_id LEFT JOIN category c on p.category_id = c.id LEFT JOIN \"user\" u on s.user_id = u.id LEFT JOIN image ON c.image_id = image.id WHERE product_id = ?;";
        return jdbcTemplate.query(sql, new ImageToProductMapper(), productId);
    }

    @Override
    public ImageToProduct save(ImageToProduct imageToProduct) {
        String sql = "INSERT INTO image_to_product (product_id, image_id) VALUES (?, ?);";
        jdbcTemplate.update(sql, imageToProduct.getProduct().getId(), imageToProduct.getImage().getId());
        return imageToProduct;
    }

    @Override
    public ImageToProduct update(ImageToProduct imageToProduct) {
        String sql = "UPDATE image_to_product SET image_id = ? WHERE product_id = ? AND image_id = ?";
        jdbcTemplate.update(sql, imageToProduct.getImage().getId(), imageToProduct.getProduct().getId(), imageToProduct.getImage().getId());
        return imageToProduct;
    }

    @Override
    public ImageToProduct delete(ImageToProduct imageToProduct) {
        String sql = "DELETE FROM image_to_product WHERE image_id = ?";
        jdbcTemplate.update(sql, imageToProduct.getImage().getId());
        return imageToProduct;
    }
}
