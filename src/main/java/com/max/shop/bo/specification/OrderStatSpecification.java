package com.max.shop.bo.specification;

import com.max.shop.dto.request.OrderStatCriteriaDto;
import com.max.shop.entity.stat.OrderStat;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class OrderStatSpecification {

    public static Specification<OrderStat> buildListFilterForOrderStat(OrderStatCriteriaDto orderStatCriteria) {
        return ((root, query, cb) -> {
            if (orderStatCriteria == null) {
                return null;
            }

            List<Predicate> predicates = new ArrayList<>();

            if (orderStatCriteria.getUserId() != null) {
                predicates.add(cb.equal(root.get("userId"), orderStatCriteria.getUserId()));
            }

            if (orderStatCriteria.getDateFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dateViews"), orderStatCriteria.getDateFrom()));
            }

            if (orderStatCriteria.getDateTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dateViews"), orderStatCriteria.getDateTo()));
            }

            if (orderStatCriteria.getQuantityOrdersFrom() != 0) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("quantityOrders"), orderStatCriteria.getQuantityOrdersFrom()));
            }

            if (orderStatCriteria.getQuantityOrdersTo() != 0) {
                predicates.add(cb.lessThanOrEqualTo(root.get("quantityOrders"), orderStatCriteria.getQuantityOrdersTo()));
            }

            if (orderStatCriteria.getTotalCostFrom() != 0) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("totalCost"), orderStatCriteria.getTotalCostFrom()));
            }

            if (orderStatCriteria.getTotalCostTo() != 0) {
                predicates.add(cb.lessThanOrEqualTo(root.get("totalCost"), orderStatCriteria.getTotalCostTo()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
