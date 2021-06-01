package com.maks.shop.entity;

import com.maks.shop.entity.parent.NameEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Characteristic extends NameEntity {

//    @ManyToMany(mappedBy = "characteristics",fetch = FetchType.LAZY)
//    private List<ProductCategory> products;
}
