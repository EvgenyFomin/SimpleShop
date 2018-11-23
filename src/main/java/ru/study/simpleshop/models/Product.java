package ru.study.simpleshop.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "You must add a product name")
    private String productname;

    @NotBlank(message = "You must add a description")
    private String description;

    @OneToMany(mappedBy = "product")
    private List<BuyProduct> buyProducts;

    private Long price;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Comment> comments;

    @OneToMany(mappedBy = "product")
    private List<Image> images;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<BuyProduct> getBuyProducts() {
        return buyProducts;
    }

    public void setBuyProducts(List<BuyProduct> buyProducts) {
        this.buyProducts = buyProducts;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
