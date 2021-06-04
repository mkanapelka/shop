package com.max.shop.entity;

import com.max.shop.entity.parent.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_image",
            joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_id")
    private Set<Long> images;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subProductCategory_id")
    private SubProductCategory subProductCategory;

    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.MODERATION;
}
