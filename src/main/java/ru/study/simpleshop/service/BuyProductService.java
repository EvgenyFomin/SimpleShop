package ru.study.simpleshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.study.simpleshop.models.BuyProduct;
import ru.study.simpleshop.models.Cart;
import ru.study.simpleshop.models.Product;
import ru.study.simpleshop.repositories.BuyProductRepository;

@Service
public class BuyProductService {
    @Autowired
    private BuyProductRepository buyProductRepository;

    public void addBuyProduct(Cart cart, Product product) {
        BuyProduct buyProduct = new BuyProduct();
        buyProduct.setProduct(product);
        buyProduct.setCart(cart);

        buyProductRepository.save(buyProduct);
    }

    public void removeAllByCart(Cart cart) {
        buyProductRepository.removeAllByCart(cart);
    }
}
