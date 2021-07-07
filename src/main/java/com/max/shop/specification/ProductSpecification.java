package com.max.shop.specification;

import com.max.shop.dto.request.ProductCriteriaDto;
import com.max.shop.entity.Product;
import com.max.shop.entity.ProductStatus;
import com.max.shop.entity.SubProductCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> buildListFilter(ProductCriteriaDto productCriteria) {
        return ((root, query, cb) -> {
            if (productCriteria == null) {
                return null;
            }

            List<Predicate> predicates = new ArrayList<>();
//            predicates.add(cb.equal(root.get("status"), ProductStatus.AVAILABLE.name()));

            if (StringUtils.isNotBlank(productCriteria.getSubCategoryName())) {
                Join<Product, SubProductCategory> productToSubCategory =
                    root.join("subProductCategory", JoinType.INNER);
                predicates.add(cb.equal(productToSubCategory.get("name"), productCriteria.getSubCategoryName()));
            }

            if (productCriteria.getCost1() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("cost"), productCriteria.getCost1()));
            }

            if (productCriteria.getCost2() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("cost"), productCriteria.getCost2()));
            }

            if (productCriteria.getQuantity1() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("quantity"), productCriteria.getQuantity1()));
            }

            if (productCriteria.getQuantity2() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("quantity"), productCriteria.getQuantity2()));
            }

            if (StringUtils.isNotBlank(productCriteria.getVendorCode())) {
                predicates.add(cb.equal(root.get("vendorCode"), productCriteria.getVendorCode()));
            }

            if (StringUtils.isNotBlank(productCriteria.getDescription())) {
                predicates.add(cb.equal(root.get("description"), productCriteria.getDescription()));
            }

            if (StringUtils.isNotBlank(productCriteria.getName())) {
                predicates.add(cb.equal(root.get("name"), productCriteria.getName()));
            }
            predicates.add(cb.equal(root.get("status"), ProductStatus.AVAILABLE));

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    public static Specification<Product> buildSingleFilter(Long id) {
        return ((root, query, cb) -> {
            if (id == null) {
                return null;
            }

            List<Predicate> predicates = new ArrayList<>();
//            predicates.add(cb.equal(root.get("status"), ProductStatus.AVAILABLE.name()));
            predicates.add(cb.equal(root.get("status"), ProductStatus.MODERATION));
            predicates.add(cb.equal(root.get("id"), id));
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    public static Specification<Product> fetchCharacteristics() {
        return BaseSpecification.withFetch("subProductCategory", "characteristics");
    }

}
