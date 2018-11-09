package ru.study.simpleshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.study.simpleshop.models.Comment;
import ru.study.simpleshop.models.Product;
import ru.study.simpleshop.models.User;
import ru.study.simpleshop.repositories.CommentRepository;
import ru.study.simpleshop.repositories.ProductRepository;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("currentProduct")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommentRepository commentRepository;

    @ModelAttribute("currentProduct")
    public Product currentProduct() {
        return new Product();
    }

    @GetMapping("{name}")
    public String oneProduct(@PathVariable String name,
                             @ModelAttribute("currentProduct") Product product,
                             Model model) {
        model.addAttribute("prodList", productRepository.findAll());
        product = productRepository.findByProductname(name);
        model.addAttribute("currentProduct", product);
        return "product";
    }

    @GetMapping
    public String mainProduct(@PageableDefault Pageable pageable,
                              Model model) {

        Page<Product> page = productRepository.findAll(pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/products");
        return "productList";
    }

    @PostMapping
    public String leaveComments(@AuthenticationPrincipal User user,
                                @ModelAttribute @Valid Comment comment,
                                BindingResult bindingResult,
                                Model model,
                                @ModelAttribute("currentProduct") Product product,
                                SessionStatus sessionStatus) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("prodList", productRepository.findAll());
            model.addAttribute("currentProduct", product);
            return "product";
        }

        comment.setAuthor(user);
        comment.setProduct(product);
        commentRepository.save(comment);

        sessionStatus.setComplete();

//        comment.setDate(new Date()); TODO
        return "redirect:/products/" + product.getProductname();
    }
}
