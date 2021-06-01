package com.maks.shop.specification;

import com.maks.shop.dto.request.ProductCriteriaDto;
import com.maks.shop.entity.Characteristic;
import com.maks.shop.entity.Product;
import com.maks.shop.entity.ProductCategory;
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

            if (StringUtils.isNotBlank(productCriteria.getCategoryName())) {
                Join<Product, ProductCategory> productToProductCategory = root.join("productCategories", JoinType.INNER);
                predicates.add(cb.equal(productToProductCategory.get("name"), productCriteria.getCategoryName()));
            }

            if (StringUtils.isNotBlank(productCriteria.getCharacteristicName())) {
                Join<Product, Characteristic> productToCharacteristic = root.join("characteristics", JoinType.INNER);
                predicates.add(cb.equal(productToCharacteristic.get("name"), productCriteria.getCharacteristicName()));
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

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }



}
