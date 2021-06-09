package com.max.shop.entity;

import com.max.shop.entity.parent.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user_order")
@EqualsAndHashCode(callSuper = false)
public class Order extends BaseEntity {

//    private String orderNumber;
    private int totalCost;
    private int quantityProduct;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "link_product_order",
//        joinColumns = @JoinColumn(name = "product_id"),
//        inverseJoinColumns = @JoinColumn(name = "order_id"),
//        foreignKey = @ForeignKey(name = "fk_order_to_product")
//    )
//    private List<Product> products;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;
}
