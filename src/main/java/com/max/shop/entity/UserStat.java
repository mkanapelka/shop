package com.max.shop.entity;

import com.max.shop.entity.parent.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.function.Supplier;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStat extends BaseEntity{

    private Long userId;
    private Long productId;
    private Integer quantityViews;

    @LastModifiedDate
    private LocalDate dateViews;
}
