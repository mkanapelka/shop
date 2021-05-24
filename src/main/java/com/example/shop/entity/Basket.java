package com.example.shop.entity;

import com.example.shop.entity.parent.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Basket extends BaseEntity {

    private String orderNumber;
    private double totalCost;
    private int quantityProduct;

    @ManyToMany(mappedBy = "orders",fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToOne
    private User user;
}
