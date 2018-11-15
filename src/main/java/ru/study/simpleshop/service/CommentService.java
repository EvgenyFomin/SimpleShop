package ru.study.simpleshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.study.simpleshop.models.Comment;
import ru.study.simpleshop.models.Product;
import ru.study.simpleshop.models.User;
import ru.study.simpleshop.repositories.CommentRepository;

import java.util.Date;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public void save(Comment comment, User user, Product product) {
        comment.setAuthor(user);
        comment.setProduct(product);
        comment.setDate(new Date().toString());

        commentRepository.save(comment);
    }
}
