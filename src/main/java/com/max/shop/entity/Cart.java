package com.max.shop.entity;

import com.max.shop.entity.parent.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseEntity {

    private int totalCost;
    private int quantityProduct;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    private List<ProductInCart> productInCarts;
}

