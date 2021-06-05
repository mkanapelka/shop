package com.max.shop.specification;

import com.max.shop.dto.request.CategoryCriteriaDto;
import com.max.shop.dto.request.SubCategoryCriteriaDto;
import com.max.shop.entity.Characteristic;
import com.max.shop.entity.ProductCategory;
import com.max.shop.entity.SubProductCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class SubCategorySpecification {

    public static Specification<SubProductCategory> buildListFilter(SubCategoryCriteriaDto subCategoryCriteria) {
        return ((root, query, cb) -> {
            if (subCategoryCriteria == null) {
                return null;
            }

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(subCategoryCriteria.getCharacteristicName())) {
                Join<SubProductCategory, Characteristic> subCategoryToCharacteristic
                        = root.join("characteristics", JoinType.INNER);
                predicates.add(cb.equal(subCategoryToCharacteristic.get("name"), subCategoryCriteria.getCharacteristicName()));
            }

            if (StringUtils.isNotBlank(subCategoryCriteria.getName())) {
                predicates.add(cb.equal(root.get("name"), subCategoryCriteria.getName()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
