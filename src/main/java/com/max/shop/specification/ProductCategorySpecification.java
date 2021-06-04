package com.max.shop.specification;

import com.max.shop.dto.request.CategoryCriteriaDto;
import com.max.shop.entity.ProductCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProductCategorySpecification {

    public static Specification<ProductCategory> buildListFilter(CategoryCriteriaDto categoryCriteria) {
        return ((root, query, cb) -> {
            if (categoryCriteria == null) {
                return null;
            }

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(categoryCriteria.getName())) {
                predicates.add(cb.equal(root.get("name"), categoryCriteria.getName()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
