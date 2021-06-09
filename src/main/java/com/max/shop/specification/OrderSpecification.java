package com.max.shop.specification;

import com.max.shop.dto.request.OrderCriteriaDto;
import com.max.shop.entity.Order;
import com.max.shop.entity.ProductInOrder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class OrderSpecification {

    public static Specification<Order> buildListFilter(OrderCriteriaDto orderCriteria) {
        return ((root, query, cb) -> {
            if (orderCriteria == null) {
                return null;
            }

            List<Predicate> predicates = new ArrayList<>();

            if (orderCriteria.getProductId() != null) {
                Join<Order, ProductInOrder> orderToProductInOrder = root.join("productInOrders", JoinType.INNER);
                predicates.add(cb.equal(orderToProductInOrder.get("productId"), orderCriteria.getProductId()));
            }

            if (StringUtils.isNotBlank(orderCriteria.getStatus())) {
                predicates.add(cb.equal(root.get("status"), orderCriteria.getStatus()));
            }

            if (orderCriteria.getQuantityProduct1() != 0) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("quantityProduct"), orderCriteria.getQuantityProduct1()));
            }

            if (orderCriteria.getQuantityProduct2() != 0) {
                predicates.add(cb.lessThanOrEqualTo(root.get("quantityProduct"), orderCriteria.getQuantityProduct2()));
            }

            if (orderCriteria.getTotalCost1() != 0) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("totalCost"), orderCriteria.getTotalCost1()));
            }

            if (orderCriteria.getTotalCost2() != 0) {
                predicates.add(cb.lessThanOrEqualTo(root.get("totalCost"), orderCriteria.getTotalCost2()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    public static Specification<Order> fetchProductInOrder() {
        return BaseSpecification.withFetch("productInOrders");
    }
}
