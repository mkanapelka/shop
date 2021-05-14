package com.example.shop.entity;

import com.example.shop.entity.parent.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Order extends BaseEntity {

    private String orderNumber;

    @ManyToMany(mappedBy = "orders",fetch = FetchType.LAZY)
    private List<Product> products;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",foreignKey = "fk_order_to_user")
    private User user;
}
