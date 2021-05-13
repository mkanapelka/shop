package com.example.shop.entity.parent;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.UniqueConstraint;


@MappedSuperclass
@Getter
@Setter
public abstract class NameEntity extends BaseEntity {

    @Column(unique = true)
    private String name;
}
