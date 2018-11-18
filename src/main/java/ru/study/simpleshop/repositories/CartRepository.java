package ru.study.simpleshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.simpleshop.models.Cart;
import ru.study.simpleshop.models.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    void removeAllByUser(User user);
}
