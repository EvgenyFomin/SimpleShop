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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.study.simpleshop.models.Product;
import ru.study.simpleshop.models.User;
import ru.study.simpleshop.repositories.ProductRepository;
import ru.study.simpleshop.service.UserService;

import java.util.Map;

@Transactional
@Controller
public class UserController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/basket")
    public String basket(@AuthenticationPrincipal User user,
                         @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                         Model model) {

        user = userService.findByUsername(user.getUsername());

        Map<Product, Integer> productMap = ControllerUtils.getProductIntegerMap(user);

        Page<Product> page = productRepository.findAll(pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/basket");
        model.addAttribute("productMap", productMap);
        return "basket";
    }

    @PostMapping("/basket")
    public String placeAnOrder() {
        return "index";
    }
}
