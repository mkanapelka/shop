package com.max.shop.entity;

import com.max.shop.entity.parent.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NamedEntityGraph(name = "SubCategory.characteristics",
        attributeNodes = @NamedAttributeNode("characteristics")
)
public class SubProductCategory extends BaseEntity {

    @Column(unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "link_subcategory_characteristic",
            joinColumns = @JoinColumn(name = "subcategory_id"),
            inverseJoinColumns = @JoinColumn(name = "characteristic_id"),
            foreignKey = @ForeignKey(name = "fk_subcategory_to_characteristic")
    )
    private List<Characteristic> characteristics;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productCategory_id")
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "subProductCategory", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Product> products;
}
