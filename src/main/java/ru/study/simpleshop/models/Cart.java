package ru.study.simpleshop.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart")
    private List<BuyProduct> buyProducts;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public List<BuyProduct> getBuyProducts() {
        return buyProducts;
    }

    public void setBuyProducts(List<BuyProduct> buyProducts) {
        this.buyProducts = buyProducts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
