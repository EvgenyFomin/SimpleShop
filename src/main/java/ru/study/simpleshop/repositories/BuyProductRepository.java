package ru.study.simpleshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.simpleshop.models.BuyProduct;
import ru.study.simpleshop.models.Cart;

@Repository
public interface BuyProductRepository extends JpaRepository<BuyProduct, Long> {
    void removeAllByCart(Cart cart);
}
