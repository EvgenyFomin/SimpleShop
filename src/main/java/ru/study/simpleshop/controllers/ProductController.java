package ru.study.simpleshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.study.simpleshop.models.Comment;
import ru.study.simpleshop.models.Product;
import ru.study.simpleshop.models.User;
import ru.study.simpleshop.repositories.ProductRepository;
import ru.study.simpleshop.service.CommentService;
import ru.study.simpleshop.service.UserProductService;

import javax.validation.Valid;
import java.io.File;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("currentProduct")
@RequestMapping("/products")
@Transactional
public class ProductController {
    @Autowired
    private UserProductService userProductService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CommentService commentService;

    @Value("${upload.path}")
    private String uploadPath;

    @ModelAttribute("currentProduct")
    public Product currentProduct() {
        return new Product();
    }

    @GetMapping("{name}")
    public String oneProduct(@AuthenticationPrincipal User user,
                             @PathVariable String name,
                             @ModelAttribute("currentProduct") Product product,
                             Model model) {

        model.addAttribute("prodList", productRepository.findAll());
        product = productRepository.findByProductname(name);
        String productname = product.getProductname();

        if (product.getImages() != null && product.getImages().size() != 0) {
            model.addAttribute("images",
                    product.getImages()
                            .stream()
                            .map(x -> productname + File.separator + x.getFilename())
                            .collect(Collectors.toList()));
        }

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

        commentService.save(comment, user, product);

        sessionStatus.setComplete();

        return "redirect:/products/" + product.getProductname();
    }

    @PostMapping("addToCart")
    public String addProduct(@AuthenticationPrincipal User user,
                             @ModelAttribute("currentProduct") Product product,
                             SessionStatus sessionStatus) {

        userProductService.addProduct(user, product);
        return "redirect:/products/" + product.getProductname();
    }
}
