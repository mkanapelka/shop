package com.maks.shop.entity;

import com.maks.shop.entity.parent.NameEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class ProductCategory extends NameEntity {

//    @ManyToMany(mappedBy = "characteristics",fetch = FetchType.LAZY)
//    private List<ProductCategory> products;

}
