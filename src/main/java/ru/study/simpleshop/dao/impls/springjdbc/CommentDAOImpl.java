package ru.study.simpleshop.dao.impls.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.study.simpleshop.dao.CommentDAO;
import ru.study.simpleshop.models.Comment;

import java.util.List;

@Component
public class CommentDAOImpl implements CommentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Comment> findByUserId(Long user_id) {
        return jdbcTemplate.query("select * from comments where user_id = ?",
                new Object[]{user_id},
                new BeanPropertyRowMapper<>(Comment.class));
    }

    @Override
    public List<Comment> findByProductId(Long product_id) {
        return jdbcTemplate.query("select * from comments where product_id = ?",
                new Object[]{product_id},
                new BeanPropertyRowMapper<>(Comment.class));
    }

    @Override
    public void save(Comment comment) {
        jdbcTemplate.update("insert into comments (message, user_id, product_id) values (?, ?, ?)",
                comment.getMessage(), comment.getAuthor().getId(), comment.getProduct().getId());
    }
}
