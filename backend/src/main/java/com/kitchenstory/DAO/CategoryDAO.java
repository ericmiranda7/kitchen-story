package com.kitchenstory.DAO;

import com.kitchenstory.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAO extends AbstractDAO<Category> {
    protected CategoryDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public Category getOneById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM category WHERE id = ?", (rs, rowNum) -> {
            Category category = new Category();
            category.setId(rs.getInt(1));
            category.setName(rs.getString(2));
            return category;
        }, id);
    }

    @Override
    public Category add(Category category) {
        return null;
    }
}
