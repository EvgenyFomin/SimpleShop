package ru.study.simpleshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.study.simpleshop.models.Product;
import ru.study.simpleshop.models.User;
import ru.study.simpleshop.repositories.ProductRepository;
import ru.study.simpleshop.service.UserService;

import java.util.HashMap;
import java.util.Map;

@SessionAttributes("productMap")
@Transactional
@Controller
public class UserController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @ModelAttribute("productMap")
    private Map<Product, Integer> productMap() {
        return new HashMap<>();
    }

    @GetMapping("/basket")
    public String basket(@AuthenticationPrincipal User user,
                         @ModelAttribute("productMap") Map<Product, Integer> productMap,
                         @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                         Model model) {

        user = userService.findByUsername(user.getUsername());

        productMap = ControllerUtils.getProductIntegerMap(user);

        Page<Product> page = productRepository.findAll(pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/basket");
        model.addAttribute("productMap", productMap);
        return "basket";
    }

    @PostMapping("/basket")
    public String placeAnOrder(@RequestParam String list) {
        return "index";
    }

    @PostMapping("/updateProductMap")
    public @ResponseBody
    String updateProductMap(@RequestParam String productId,
                            @RequestParam String behaviour,
                            @ModelAttribute("productMap") Map<Product, Integer> productMap,
                            Model model) {

        for (Map.Entry<Product, Integer> currentMap : productMap.entrySet()) {
            if (currentMap.getKey().getId() == Integer.parseInt(productId)) {
                int count = currentMap.getValue();
                productMap.put(currentMap.getKey(), behaviour.equals("+") ? ++count : --count);
                model.addAttribute("productMap", productMap);
                return productId + " " + String.valueOf(count);
            }
        }

        return "";
    }
}
