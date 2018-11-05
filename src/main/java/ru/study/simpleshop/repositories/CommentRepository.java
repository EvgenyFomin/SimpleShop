package ru.study.simpleshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.simpleshop.models.Comment;
import ru.study.simpleshop.models.Product;
import ru.study.simpleshop.models.User;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByAuthor(User user);

    List<Comment> findByProduct(Product product);
}
