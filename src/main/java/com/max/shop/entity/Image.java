package com.max.shop.entity;

import com.max.shop.entity.parent.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Image extends BaseEntity {

    private byte[] bigImageArray;
}
