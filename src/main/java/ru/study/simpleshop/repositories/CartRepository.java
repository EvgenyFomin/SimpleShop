package ru.study.simpleshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.simpleshop.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
