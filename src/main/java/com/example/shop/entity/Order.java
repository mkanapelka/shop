package com.example.shop.entity;

import com.example.shop.entity.parent.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user_order")
public class Order extends BaseEntity {

    private String orderNumber;
    private int totalCost;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "link_product_order",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"),
            foreignKey = @ForeignKey(name = "fk_order_to_product")
    )
    private List<Product> products;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
