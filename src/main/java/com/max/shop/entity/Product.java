package com.max.shop.entity;

import com.max.shop.entity.parent.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


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

    private Long thumbnailId;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<Image> images;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subProductCategory_id")
    private SubProductCategory subProductCategory;

}
