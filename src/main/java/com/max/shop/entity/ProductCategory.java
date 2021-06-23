package com.max.shop.entity;

import com.max.shop.entity.parent.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory extends BaseEntity {

    @Column(unique = true)
    private String name;
    private String thumbnail;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<SubProductCategory> subProductCategories;

}
