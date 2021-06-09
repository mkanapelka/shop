package com.max.shop.entity;

import com.max.shop.entity.parent.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user_order")
@EqualsAndHashCode(callSuper = false)
public class Order extends BaseEntity {

    private int totalCost;
    private int quantityProduct;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<ProductInOrder> productInOrders;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;
}
