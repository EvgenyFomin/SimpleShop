package ru.study.simpleshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.study.simpleshop.models.Product;
import ru.study.simpleshop.repositories.ProductRepository;

import javax.validation.Valid;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/add/product")
    public String getNewProduct() {
        return "addNewProduct";
    }

    @PostMapping("/add/product")
    public String postNewProduct(@ModelAttribute @Valid Product product,
                                 BindingResult bindingResult,
                                 Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "addNewProduct";
        }

        productRepository.save(product);
        return "redirect:/products";
    }
}
