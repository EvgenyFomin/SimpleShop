package ru.study.simpleshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.study.simpleshop.models.Image;
import ru.study.simpleshop.models.Product;
import ru.study.simpleshop.repositories.ImageRepository;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public void save(String filename, String path, String advancedUuid, Product product) {
        Image image = new Image();
        image.setFilename(filename);
        image.setPath(path);
        image.setAdvancedUuid(advancedUuid);
        image.setProduct(product);

        imageRepository.save(image);
    }
}
