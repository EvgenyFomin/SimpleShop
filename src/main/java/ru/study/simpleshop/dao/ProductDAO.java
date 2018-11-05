package ru.study.simpleshop.dao;

import ru.study.simpleshop.models.Comment;
import ru.study.simpleshop.models.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll();

    Product findByName(String name);

    void changeCount(Product product, Long count);

    void save(Product product);

    void addComment(Comment comment, Product product);
}
