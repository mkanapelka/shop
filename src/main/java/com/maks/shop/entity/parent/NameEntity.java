package com.maks.shop.entity.parent;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@Getter
@Setter
public abstract class NameEntity extends BaseEntity {

    @Column(unique = true)
    private String name;
}
