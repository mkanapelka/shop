package com.example.shop.entity;

import com.example.shop.entity.parent.NameEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Product extends NameEntity {

    @Column(unique = true)
    private String vendorCode;

    private Double cost;
    private Integer quantity;
    private String description;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "link_product_rank",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "rank_id"),
            foreignKey = @ForeignKey(name = "fk_product_to_rank")
    )
    private List<Rank> ranks;
}
