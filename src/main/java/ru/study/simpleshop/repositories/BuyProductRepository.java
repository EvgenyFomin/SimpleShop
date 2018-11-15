package ru.study.simpleshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.simpleshop.models.BuyProduct;

@Repository
public interface BuyProductRepository extends JpaRepository<BuyProduct, Long> {

}
