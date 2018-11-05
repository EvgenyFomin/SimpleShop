package ru.study.simpleshop.dao.impls.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.study.simpleshop.dao.UserProductDAO;

public class UserProductDAOImpl implements UserProductDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void remove(Long user_id, Long product_id) {
        jdbcTemplate.update("delete from user_product where user_id = ? and product_id = ?", user_id, product_id);
    }

    @Override
    public void removeAllByUserId(Long user_id) {
        jdbcTemplate.update("delete from user_product where user_id = ?", user_id);
    }

    @Override
    public void save(Long user_id, Long product_id) {
        jdbcTemplate.update("insert into user_product values (?, ?)", user_id, product_id);
    }
}
