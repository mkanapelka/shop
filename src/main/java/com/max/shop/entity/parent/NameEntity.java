package com.max.shop.entity.parent;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@Getter
@Setter
public abstract class NameEntity extends BaseEntity {

    @Column(unique = true)
    private String name;
}
