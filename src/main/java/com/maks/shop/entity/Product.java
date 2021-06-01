package com.maks.shop.entity;

import com.maks.shop.entity.parent.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends BaseEntity {

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String vendorCode;
    private Integer cost;
    private Integer quantity;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "link_product_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "product_category_id"),
            foreignKey = @ForeignKey(name = "fk_product_to_product_category")
    )
    private List<ProductCategory> productCategories;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "link_product_characteristic",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "characteristic_id"),
            foreignKey = @ForeignKey(name = "fk_product_to_characteristic")
    )
    private List<Characteristic> characteristics;

}
