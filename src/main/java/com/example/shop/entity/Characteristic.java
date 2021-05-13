package com.example.shop.entity;

import com.example.shop.entity.parent.NameEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Characteristic extends NameEntity {

    @ManyToMany(mappedBy = "characteristics",fetch = FetchType.LAZY)
    private List<Rank> ranks;
}
