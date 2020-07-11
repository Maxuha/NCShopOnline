package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.mapper.ImageMapper;
import ua.edu.sumdu.j2ee.zykov.model.Image;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

@Repository
public class ImageDAOImpl implements ImageDAO {
    private final JdbcTemplate jdbcTemplate;

    public ImageDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Image> findAll() {
        String sql = "SELECT * FROM image;";
        return jdbcTemplate.query(sql, new ImageMapper());
    }

    @Override
    public Image findById(int id) {
        String sql = "SELECT * FROM image WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new ImageMapper(), id);
    }

    @Override
    public Image save(Image image) {
        String sql = "INSERT INTO image (image) VALUES (?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, image.getImage());
            return ps;
        }, keyHolder);
        image.setId((Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id"));
        return image;
    }

    @Override
    public Image update(Image image) {
        String sql = "UPDATE image SET image = ? WHERE id = ?;";
        image.setId(jdbcTemplate.update(sql, image.getImage(), image.getId()));
        return image;
    }

    @Override
    public Image delete(Image image) {
        String sql = "DELETE FROM image WHERE id = ?";
        image.setId(jdbcTemplate.update(sql, image.getId()));
        return image;
    }
}
