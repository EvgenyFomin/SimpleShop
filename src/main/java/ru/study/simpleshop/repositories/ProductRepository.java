package ru.study.simpleshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.simpleshop.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductname(String productname);
}
