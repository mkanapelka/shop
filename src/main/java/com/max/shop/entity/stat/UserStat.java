package com.max.shop.entity.stat;

import com.max.shop.entity.parent.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStat{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    private Long userId;
    private Long productId;
    private Integer quantityViews;

    @LastModifiedDate
    private LocalDate dateViews;
}
