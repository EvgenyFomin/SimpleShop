package ru.study.simpleshop.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.study.simpleshop.models.BuyProduct;
import ru.study.simpleshop.models.Product;
import ru.study.simpleshop.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class ControllerUtils {
    static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );

        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    static Map<Product, Integer> getProductIntegerMap(User user) {
        Map<Product, Integer> productMap = new HashMap<>();
        for (BuyProduct currentBuyProduct : user.getCart().getBuyProducts()) {
            Product product = currentBuyProduct.getProduct();
            if (productMap.containsKey(product)) {
                int count = productMap.get(product);
                productMap.put(product, ++count);
            } else {
                productMap.put(product, 1);
            }
        }
        return productMap;
    }
}
