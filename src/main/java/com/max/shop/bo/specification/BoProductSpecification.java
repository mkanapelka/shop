package com.max.shop.bo.specification;

import com.max.shop.dto.request.ProductCriteriaBoDto;
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

public class BoProductSpecification {

    public static Specification<Product> buildListFilter(ProductCriteriaBoDto productCriteria) {
        return ((root, query, cb) -> {
            if (productCriteria == null) {
                return null;
            }

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(productCriteria.getSubCategoryName())) {
                Join<Product, SubProductCategory> productToSubCategory = root.join("subProductCategory", JoinType.INNER);
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

            if(productCriteria.getStatus() != null){
                predicates.add(cb.equal(root.get("status"), productCriteria.getStatus()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
