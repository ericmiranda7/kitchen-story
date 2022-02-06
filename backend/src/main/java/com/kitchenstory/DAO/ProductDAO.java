package com.kitchenstory.DAO;

import com.kitchenstory.model.Product;
import com.kitchenstory.service.BrandService;
import com.kitchenstory.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO extends AbstractDAO<Product> {
    private final CategoryService categoryService;
    private final BrandService brandService;

    protected ProductDAO(JdbcTemplate jdbcTemplate, @Autowired CategoryService categoryService, @Autowired BrandService brandService) {
        super(jdbcTemplate);
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    @Override
    public List<Product> getAll() {
        return this.jdbcTemplate.query("SELECT * FROM product", (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt(1));
            product.setName(rs.getString(2));
            product.setCategory(categoryService.getCategoryById(rs.getInt(3)).toString());
            product.setPrice(rs.getInt(4));
            product.setBrand(brandService.getBrandById(rs.getInt(5)).toString());
            product.setDescription(rs.getString(6));
            product.setImg(rs.getString(7));
            return product;
        });
    }

    @Override
    public Product getOneById(int id) {
        return null;
    }

    @Override
    public Product add(Product product) throws DuplicateKeyException {
        this.jdbcTemplate.update("INSERT INTO product VALUES (?, ?, ?, ?, ?, ?, ?)",
                product.getId(), product.getName(), product.getCategory(), product.getPrice(), product.getBrand(), product.getDescription(), product.getImg());
        return product;
    }

    public void deleteProduct(int id) {
        this.jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
    }
}
