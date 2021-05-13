package com.example.shop.entity;

import com.example.shop.entity.parent.NameEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Rank extends NameEntity {

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "link_rank_characteristic",
            joinColumns = @JoinColumn(name = "rank_id"),
            inverseJoinColumns = @JoinColumn(name = "characteristic_id"),
            foreignKey = @ForeignKey(name = "fk_rank_to_characteristic")
    )
    private List<Characteristic> characteristics;

    @ManyToMany(mappedBy = "ranks",fetch = FetchType.LAZY)
    private List<Product> products;
}
