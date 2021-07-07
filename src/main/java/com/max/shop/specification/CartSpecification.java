package com.max.shop.specification;

import com.max.shop.entity.Cart;
import com.max.shop.entity.SubProductCategory;
import com.max.shop.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

public class CartSpecification {

    public static Specification<Cart> findCartByUserId(Long id) {
        return (root, query, cb) -> {
            if (id != null) {
                Join<Cart, User> cartToUser = root.join("user", JoinType.INNER);
                return cb.equal(cartToUser.get("id"), id);
            } else return null;
        };
    }


//    public static Specification<Cart> fetchProducts() {
//        return ((root, query, cb) -> {
//            root.fetch("productInCarts", JoinType.LEFT).fetch("product", JoinType.LEFT);
//            return null;
//        });
//    }

    public static Specification<Cart> fetchProducts() {
        return BaseSpecification.withFetch("productInCarts","product");
    }
}
