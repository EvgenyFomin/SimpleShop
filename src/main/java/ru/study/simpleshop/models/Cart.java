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

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public List<BuyProduct> getBuyProducts() {
        return buyProducts;
    }

    public void setBuyProducts(List<BuyProduct> buyProducts) {
        this.buyProducts = buyProducts;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
