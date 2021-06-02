package com.maks.shop.entity;

import com.maks.shop.entity.parent.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SmallImage extends BaseEntity {

    private byte[] smallImageArray;

    @OneToOne
    private Product product;
}
