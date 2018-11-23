package ru.study.simpleshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.study.simpleshop.models.Product;
import ru.study.simpleshop.repositories.ProductRepository;
import ru.study.simpleshop.service.ImageService;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@Transactional
public class AdminController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageService imageService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/add/product")
    public String getNewProduct() {
        return "addNewProduct";
    }

    @PostMapping("/add/product")
    public String postNewProduct(@ModelAttribute @Valid Product product,
                                 @RequestParam("files") MultipartFile[] multipartFiles,
                                 BindingResult bindingResult,
                                 Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "addNewProduct";
        }

        productRepository.save(product);

        if (multipartFiles.length != 0) {

            uploadPath += File.separator + "Products" + File.separator + product.getProductname();
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            for (MultipartFile currentFile : multipartFiles) {
                String advancedUuid = UUID.randomUUID().toString() + "." + currentFile.getOriginalFilename();
                currentFile.transferTo(new File(uploadPath + "/" + currentFile.getOriginalFilename()));
                imageService.save(currentFile.getOriginalFilename(), uploadPath, advancedUuid, product);
            }
        }

        return "redirect:/products";
    }
}
