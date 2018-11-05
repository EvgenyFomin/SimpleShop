package ru.study.simpleshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.study.simpleshop.models.Comment;
import ru.study.simpleshop.models.Product;
import ru.study.simpleshop.models.User;
import ru.study.simpleshop.repositories.CommentRepository;
import ru.study.simpleshop.repositories.ProductRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/")
    public String view() {
        return "index";
    }

    @GetMapping("/products/{name}")
    public String oneProduct(@PathVariable String name, Model model) {
        model.addAttribute("prodList", productRepository.findAll());
        Product product = productRepository.findByProductname(name);
        model.addAllAttributes(new HashMap<String, Object>() {{
            put("productname", product.getProductname());
            put("description", product.getDescription());
            put("count", product.getCount());
            put("comments", product.getComments());
        }});
        return "product";
    }

    @GetMapping("/products")
    public String noProduct(Model model) {
        model.addAttribute("prodList", productRepository.findAll());
        return "productList";
    }

    @PostMapping("/products")
    public String leaveComments(@AuthenticationPrincipal User user,
                                @ModelAttribute @Valid Comment comment,
                                @RequestParam String productname,
                                BindingResult bindingResult,
                                Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "product";
        }

        comment.setAuthor(user);
        comment.setProduct(productRepository.findByProductname(productname));
        commentRepository.save(comment);

//        comment.setDate(new Date()); TODO
        return "redirect:/products/" + productname;
    }

//    @GetMapping("/registration")
//    public String getRegistration() {
//        return "registr";
//    }
//
//    @PostMapping("/registration")
//    public String postRegistration(@ModelAttribute User user) {
//        userDAO.save(user);
//        return "redirect:/login";
//    }
}
