package ru.study.simpleshop.dao.impls.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.study.simpleshop.dao.CommentDAO;
import ru.study.simpleshop.dao.ProductDAO;
import ru.study.simpleshop.models.Comment;
import ru.study.simpleshop.models.Product;

import java.util.LinkedList;
import java.util.List;

@Component
public class ProductDAOImpl implements ProductDAO {
    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> findAll() {
        List<Product> products = jdbcTemplate.query("select * from products", new BeanPropertyRowMapper<>(Product.class));
        if (products.size() > 0) {
            for (Product currentProduct : products) {
                currentProduct.setComments(commentDAO.findByProductId(currentProduct.getId()));
            }
        }
        return products;
    }

    @Override
    public Product findByName(String name) {
        Product product = jdbcTemplate.query("select * from products where name = ?",
                new Object[]{name},
                new BeanPropertyRowMapper<>(Product.class)
        ).stream().findAny().orElse(null);

        if (product != null) {
            product.setComments(commentDAO.findByProductId(product.getId()));
        }
        return product;
    }

    @Override
    public void changeCount(Product product, Long count) {
        jdbcTemplate.update("update products set count = ? where id = ?", product.getCount() + count, product.getId());
    }

    @Override
    public void save(Product product) {
        jdbcTemplate.update("insert into products (name, description, count) values (?, ?, ?)",
                product.getProductname(), product.getDescription(), product.getCount());
    }

    @Override
    public void addComment(Comment comment, Product product) {
        try {
            product.getComments().add(comment);
        } catch (NullPointerException e) {
            product.setComments(new LinkedList<Comment>() {{
                add(comment);
            }});
        }
    }
}
