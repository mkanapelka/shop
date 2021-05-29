package com.example.shop.entity;

import com.example.shop.entity.parent.NameEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class ProductCategory extends NameEntity {

//    @ManyToMany(mappedBy = "characteristics",fetch = FetchType.LAZY)
//    private List<ProductCategory> products;

}
