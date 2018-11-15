package ru.study.simpleshop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.simpleshop.models.Product;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);

    Product findByProductname(String productname);

    Page<Product> findAllByProductname(Set<String> products, Pageable pageable);
}