package com.maks.shop.entity;

import com.maks.shop.entity.parent.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Cart extends BaseEntity {

    private String orderNumber;
    private int totalCost;
    private int quantityProduct;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "link_cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"),
            foreignKey = @ForeignKey(name = "fk_cart_to_product")
    )
    private List<Product> products;

    @OneToOne
    private User user;
}

