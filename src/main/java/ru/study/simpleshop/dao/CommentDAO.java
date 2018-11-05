package ru.study.simpleshop.dao;

import ru.study.simpleshop.models.Comment;

import java.util.List;

public interface CommentDAO {
    List<Comment> findByUserId(Long user_id);

    List<Comment> findByProductId(Long product_id);

    void save(Comment comment);
}
