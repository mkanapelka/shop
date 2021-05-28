package com.example.shop.entity;

import com.example.shop.entity.parent.NameEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class ProductCategory extends NameEntity {

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "link_product_category_characteristic",
            joinColumns = @JoinColumn(name = "product_category_id"),
            inverseJoinColumns = @JoinColumn(name = "characteristic_id"),
            foreignKey = @ForeignKey(name = "fk_product_category_to_characteristic")
    )
    private List<Characteristic> characteristics;

}
