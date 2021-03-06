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
    private double totalCost;

    @ManyToMany(mappedBy = "orders",fetch = FetchType.LAZY)
    private List<Product> products;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
