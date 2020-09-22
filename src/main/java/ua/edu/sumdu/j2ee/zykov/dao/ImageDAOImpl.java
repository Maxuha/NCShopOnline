package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.exception.ImageNotExistException;
import ua.edu.sumdu.j2ee.zykov.mapper.ImageMapper;
import ua.edu.sumdu.j2ee.zykov.model.EntityField;
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
    public Image findById(int id) throws ImageNotExistException {
        final String field = "id";
        String sql = "SELECT * FROM image WHERE id = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, new ImageMapper(), id);
        } catch (DataAccessException e) {
            throw new ImageNotExistException(new EntityField(id, field), e);
        }
    }

    @Override
    public Image save(Image image) {
        String sql = "SELECT * FROM image WHERE image = ?;";
        List<Image> list = jdbcTemplate.query(sql, new ImageMapper(), image.getImage());
        if (list.size() == 0) {
            sql = "INSERT INTO image (image) VALUES (?);";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            String finalSql = sql;
            Image finalImage = image;
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(finalSql, new String[]{"id"});
                ps.setString(1, finalImage.getImage());
                return ps;
            }, keyHolder);
            image.setId((Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id"));
        } else {
            image = list.get(0);
        }
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
