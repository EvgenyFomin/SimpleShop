package ru.study.simpleshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.study.simpleshop.models.Product;
import ru.study.simpleshop.models.User;

@Service
public class UserProductService {
    @Autowired
    private BuyProductService buyProductService;

    public void addProduct(User user, Product product) {
        buyProductService.addBuyProduct(user.getCart(), product);
    }

    public void removeAllByUser(User user) {

    }
}
