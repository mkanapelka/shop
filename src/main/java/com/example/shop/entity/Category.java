package com.example.shop.entity;

import com.example.shop.entity.parent.NameEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Category extends NameEntity {

    @OneToMany(mappedBy = "characteristic", cascade = CascadeType.ALL)
    private List<Characteristic> characteristicList;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "join_product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"),
            foreignKey = @ForeignKey(name = "fk_category_to_product")
    )
    private List<Product> productList;
}
