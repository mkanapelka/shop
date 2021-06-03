package com.max.shop.entity;

import com.max.shop.entity.parent.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class SubProductCategory extends BaseEntity {

    @Column(unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "link_subcategory_characteristic",
            joinColumns = @JoinColumn(name = "subcategory_id"),
            inverseJoinColumns = @JoinColumn(name = "characteristic_id"),
            foreignKey = @ForeignKey(name = "fk_subcategory_to_characteristic")
    )
    private List<Characteristic> characteristics;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productCategory_id")
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "subProductCategory", fetch = FetchType.LAZY)
    private List<Product> products;
}
