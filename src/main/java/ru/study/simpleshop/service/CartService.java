package ru.study.simpleshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.study.simpleshop.models.Cart;
import ru.study.simpleshop.models.User;
import ru.study.simpleshop.repositories.CartRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public void addCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
    }

    public void removeAllByUser(User user) {
        Cart cart = user.getCart();
        cart.getBuyProducts().clear();
        cartRepository.save(cart);
    }
}
