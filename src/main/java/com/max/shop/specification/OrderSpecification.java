package com.max.shop.specification;

import com.max.shop.dto.request.OrderCriteriaForAdminDto;
import com.max.shop.dto.request.OrderCriteriaForUserDto;
import com.max.shop.entity.Order;
import com.max.shop.entity.User;
import com.max.shop.exception.WrongOrderException;
import com.max.shop.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderSpecification {
    public static Specification<Order> buildListFilterForUser(OrderCriteriaForUserDto orderCriteria) {
        return ((root, query, cb) -> {
            if (orderCriteria == null) {
                return null;
            }

            List<Predicate> predicates = new ArrayList<>();

            if (Objects.equals(orderCriteria.getUserId(), SecurityUtil.getUserId())) {
                Join<Order, User> orderToUser = root.join("user", JoinType.INNER);
                predicates.add(cb.equal(orderToUser.get("id"), orderCriteria.getUserId()));
            } else{
                throw new WrongOrderException();
            }

            if (orderCriteria.getDateCreatedFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("created"), orderCriteria.getDateCreatedFrom()));
            }
            if (orderCriteria.getDateCreatedTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("created"), orderCriteria.getDateCreatedTo()));
            }

            if (orderCriteria.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), orderCriteria.getStatus()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    public static Specification<Order> buildListFilterForAdmin(OrderCriteriaForAdminDto orderCriteria) {
        return ((root, query, cb) -> {
            if (orderCriteria == null) {
                return null;
            }

            List<Predicate> predicates = new ArrayList<>();

            if (orderCriteria.getUserId() != null) {
                Join<Order, User> orderToUser = root.join("user", JoinType.INNER);
                predicates.add(cb.equal(orderToUser.get("id"), orderCriteria.getUserId()));
            }

            if (StringUtils.isNotBlank(orderCriteria.getStatus())) {
                predicates.add(cb.equal(root.get("status"), orderCriteria.getStatus()));
            }

            if (orderCriteria.getTotalCost1() != 0) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("cost"), orderCriteria.getTotalCost1()));
            }

            if (orderCriteria.getTotalCost2() != 0) {
                predicates.add(cb.lessThanOrEqualTo(root.get("cost"), orderCriteria.getTotalCost2()));
            }

            if (orderCriteria.getQuantityProduct1() != 0) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("quantity"), orderCriteria.getQuantityProduct1()));
            }

            if (orderCriteria.getQuantityProduct2() != 0) {
                predicates.add(cb.lessThanOrEqualTo(root.get("quantity"), orderCriteria.getQuantityProduct2()));
            }

            if (orderCriteria.getDateCreatedFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("created"), orderCriteria.getDateCreatedFrom()));
            }
            if (orderCriteria.getDateCreatedTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("created"), orderCriteria.getDateCreatedTo()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }



    public static Specification<Order> findOrderById(Long id) {
        return (root, query, cb) -> {
            if (id != null) {
                return cb.equal(root.get("id"), id);
            } else return null;
        };
    }

    public static Specification<Order> fetchProducts() {
        return ((root, query, cb) -> {
            root.fetch("productInOrders", JoinType.LEFT).fetch("product", JoinType.LEFT);
            return null;
        });
    }
}
