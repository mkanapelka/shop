package com.max.shop.entity;

import com.max.shop.entity.parent.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class ProductCategory extends BaseEntity {

    @Column(unique = true)
    private String name;
    private String thumbnail;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY)
    private List<SubProductCategory> subProductCategories;

}
