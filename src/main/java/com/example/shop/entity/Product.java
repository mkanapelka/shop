package com.example.shop.entity;

import com.example.shop.entity.parent.NameEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Product extends NameEntity {

    private String vendorCode;
    private Double cost;
    private Integer quantity;
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "join_category_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"),
            foreignKey = @ForeignKey(name = "fk_product_to_category")
    )
    private List<Category> categoryList;
}
